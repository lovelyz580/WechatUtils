package com.wechat.wxpay.restful;
import com.wechat.wxpay.Config.WxPayConfig;
import com.wechat.wxpay.util.*;
import com.wechat.wxpay.entity.Json;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.weixin4j.WeixinException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Controller
@RequestMapping("/wechat")
public class PayController {
    private static Logger log = Logger.getLogger(PayController.class);
    private static final String appid = WxPayConfig.appid;	    //微信小程序appid
    private static final String secret = WxPayConfig.appkey;	//微信小程序密钥
    private static final String grant_type = "authorization_code";

    @ResponseBody
    @RequestMapping("/testlogin")
    public Json testlogin(String username,String password){
        Json json = new Json();
        if (username ==null ||password ==null){
            json.setData(null);
            json.setSuccess(false);
            json.setMsg("请输入帐号或密码");

        }else  if (("rootadmin").equals(username) &&("password").equals(password) ){
            json.setData("帐号："+username +"密码"+password );
            json.setSuccess(true);
            json.setMsg("登录成功");

        }
        return json;
    }

    @ResponseBody
    @RequestMapping("/prepay")
    public Json prePay(HttpServletRequest request){
        //发起微信支付
        //获取微信支付参数
        /**
         * 获取用户openid  下 统一单
         */
        String openid = "op_Pb4pDJsECgXM6wmCeKj0kMBF0";
        Json   wxpay =  createPay(openid,request);
        System.out.println(wxpay);
        return wxpay;
    }
    /**
     * 小程序后台登录，向微信平台发送获取access_token请求，并返回openId
     *
     * @param code
     * @return openid
     * @throws WeixinException
     * @throws IOException
     * @since 2019/03/06
     */
    @ResponseBody
    @RequestMapping("/login")
    public Json getuserinfo(String encryptedData, String iv, String code) {
            Json json = new Json();
            // 登录凭证不能为空
            if (code == null || code.length() == 0) {
                json.setData(null);
                json.setSuccess(false);
                json.setMsg("code 不能为空");
                return json;
            }
            // 小程序唯一标识 (在微信小程序管理后台获取)
            String Appid = WxPayConfig.appid;
            // 小程序的 app secret (在微信小程序管理后台获取)
            String Secret = WxPayConfig.appkey;
            // 授权（必填）
            String grant_type = "authorization_code";
            // 请求参数
            String params = "appid=" + Appid + "&secret=" + Secret + "&js_code=" + code + "&grant_type="
                    + grant_type;
            // 发送请求
            String sr = HttpRequest.sendGet(WxPayConfig.WECHAT_GET_OPENID_URL, params);
            // 解析相应内容（转换成json对象）
            org.json.JSONObject jsonobj = new org.json.JSONObject(sr);
            // 获取会话密钥（session_key）
            String session_key = jsonobj.get("session_key").toString();
            // 用户的唯一标识（openid）
            String openid = (String) jsonobj.get("openid");
            try {
                String result = AesCbuUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
                if (null != result) {
                    json.setMsg("解密成功");
                    json.setCode(200);
                    json.setSuccess(true);
                    org.json.JSONObject userInfoJSON = new org.json.JSONObject(result);
                    Map userInfo = new HashMap();
                    userInfo.put("openId", userInfoJSON.get("openId"));
                    userInfo.put("nickName", userInfoJSON.get("nickName"));
                    userInfo.put("gender", userInfoJSON.get("gender"));
                    userInfo.put("city", userInfoJSON.get("city"));
                    userInfo.put("province", userInfoJSON.get("province"));
                    userInfo.put("country", userInfoJSON.get("country"));
                    userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                    // 解密unionId & openId;
                    if (!userInfoJSON.isNull("unionId")) {
                        userInfo.put("unionId", userInfoJSON.get("unionId"));
                    }
                    json.setData( userInfo);
                }else {
                    json.setMsg("解密失败");
                    json.setCode(500);
                    json.setSuccess(false);
                    json.setData("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
  Map userdata = (Map) json.getData();
     String Openid = (String) userdata.get("openId");
        System.out.println(Openid);
        /**
         * 将openid 存入数据库
         *
         *
         *
         */
            return json;
    }

    /**
     * @Description: 发起微信支付
     * @param openid
     * @param request
     * @author: Lovelyz
     * @date: 2019/03/06
     */
    public Json createPay(String openid, HttpServletRequest request){
        Json json = new Json();
        try{
            //生成的随机字符串
            String nonce_str = StringUtils.getRandomStringByLength(32);
            //商品名称
            String body = "测试商品名称";
            //获取本机的ip地址
            String spbill_create_ip = IpUtils.getIpAddr(request);
            String orderNo = "123456789";
            String money = "100";//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败
            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", WxPayConfig.appid);
            packageParams.put("mch_id", WxPayConfig.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", orderNo);//商户订单号
            packageParams.put("total_fee", money);//支付金额，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WxPayConfig.notify_url);//回调地址
            packageParams.put("trade_type", WxPayConfig.TRADETYPE);
            packageParams.put("openid", openid);
            // 除去数组中的空值和签名参数
            packageParams = PayUtil.paraFilter(packageParams);
            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtil.sign(prestr, WxPayConfig.mch_key, "utf-8").toUpperCase();
            System.out.println("=======================第一次签名：" + mysign + "=====================");
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml>" + "<appid>" + WxPayConfig.appid + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + WxPayConfig.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WxPayConfig.notify_url + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + orderNo + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + money + "</total_fee>"
                    + "<trade_type>" + WxPayConfig.TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";
            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
            //调用统一下单接口，并接受返回的结果
            String result = PayUtil.httpRequest(WxPayConfig.pay_url, "POST", xml);
            System.out.println("调试模式_统一下单接口 返回XML数据：" + result);
            // 将解析结果存储在HashMap中
            Map map = PayUtil.doXMLParse(result);
            String return_code = (String) map.get("return_code");//返回状态码
            //返回给移动端需要的参数
            Map<String, Object> response = new HashMap<String, Object>();
            if(return_code == "SUCCESS" || return_code.equals(return_code)){
                // 业务结果
                String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
                response.put("nonceStr", nonce_str);
                response.put("package", "prepay_id=" + prepay_id);
                Long timeStamp = System.currentTimeMillis() / 1000;
                response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
                String stringSignTemp = "appId=" + WxPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=" + WxPayConfig.SIGNTYPE + "&timeStamp=" + timeStamp;
                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
                String paySign = PayUtil.sign(stringSignTemp, WxPayConfig.mch_key, "utf-8").toUpperCase();
                System.out.println("=======================第二次签名：" + paySign + "=====================");
                response.put("paySign", paySign);
                //更新订单信息

                //业务逻辑代码
            }
            response.put("appid", WxPayConfig.appid);
            json.setSuccess(true);
            json.setCode(200);
            json.setData(response);
        }catch(Exception e){
            e.printStackTrace();
            json.setSuccess(false);
            json.setMsg("发起失败");
        }
        return json;
    }
    /**
     * @Description:微信支付回调
     * @return
     * @author Lovelyz
     * @throws Exception
     * @throws WeixinException
     * @date 2019/03/06
     */
    @RequestMapping(value="/wxNotify")
    public void wxNotify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream)request.getInputStream()));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine())!=null){
            sb.append(line);
        }
        br.close();
        //sb为微信返回的xml
        String notityXml = sb.toString();
        String resXml = "";
        System.out.println("接收到的报文：" + notityXml);
        Map map = PayUtil.doXMLParse(notityXml);
        String returnCode = (String) map.get("return_code");
        if("SUCCESS".equals(returnCode)){
            //验证签名是否正确
            if(PayUtil.verify(PayUtil.createLinkString(map), (String)map.get("sign"), WxPayConfig.mch_key, "utf-8")){
                /**此处添加自己的业务逻辑代码start**/



                /**此处添加自己的业务逻辑代码end**/
                //通知微信服务器已经支付成功
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            }
        }else{
            resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
                    + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
        }
        System.out.println(resXml);
        System.out.println("微信支付回调数据结束");
        BufferedOutputStream out = new BufferedOutputStream(
                response.getOutputStream());
        out.write(resXml.getBytes());
        out.flush();
        out.close();
    }


    /**
     * 退款接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/refund")
    @ResponseBody
    public Json refund(HttpServletRequest request){
        Json json = new Json();
        String openid = request.getRequestedSessionId();  //获取openid
        // 调用退款接口，并接受返回的结果
        try{
            //生成的随机字符串
            String nonce_str = StringUtils.getRandomStringByLength(32);
            //商品名称
            String body = "测试商品名称";
            //获取本机的ip地址
            String spbill_create_ip = IpUtils.getIpAddr(request);
            String orderNo = "123456789";
            String money = "100";//支付金额，单位：分，这边需要转成字符串类型，否则后面的签名会失败
            Map<String, String> packageParams = new HashMap<String, String>();
            packageParams.put("appid", WxPayConfig.appid);
            packageParams.put("mch_id", WxPayConfig.mch_id);
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", body);
            packageParams.put("out_trade_no", orderNo);//商户订单号
            packageParams.put("total_fee", money);//支付金额，这边需要转成字符串类型，否则后面的签名会失败
            packageParams.put("spbill_create_ip", spbill_create_ip);
            packageParams.put("notify_url", WxPayConfig.notify_url);//回调地址
            packageParams.put("trade_type", WxPayConfig.TRADETYPE);
            packageParams.put("openid", openid);
            // 除去数组中的空值和签名参数
            packageParams = PayUtil.paraFilter(packageParams);
            String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串
            //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
            String mysign = PayUtil.sign(prestr, WxPayConfig.mch_key, "utf-8").toUpperCase();
            System.out.println("=======================第一次签名：" + mysign + "=====================");
            //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
            String xml = "<xml>" + "<appid>" + WxPayConfig.appid + "</appid>"
                    + "<body><![CDATA[" + body + "]]></body>"
                    + "<mch_id>" + WxPayConfig.mch_id + "</mch_id>"
                    + "<nonce_str>" + nonce_str + "</nonce_str>"
                    + "<notify_url>" + WxPayConfig.notify_url + "</notify_url>"
                    + "<openid>" + openid + "</openid>"
                    + "<out_trade_no>" + orderNo + "</out_trade_no>"
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>"
                    + "<total_fee>" + money + "</total_fee>"
                    + "<trade_type>" + WxPayConfig.TRADETYPE + "</trade_type>"
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";
            System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
            //退款返回，并接受返回的结果
            String result = PayUtil.doRefund(WxPayConfig.mch_id,WxPayConfig.refund_url,xml);
            System.out.println("=======================退款RESPONSE数据：" + result);
            json.setMsg("退款成功");
            json.setCode(200);
            json.setSuccess(true);
            json.setData(request);
        }catch (Exception e){
            e.printStackTrace();
        }
        return json;
    }






}















