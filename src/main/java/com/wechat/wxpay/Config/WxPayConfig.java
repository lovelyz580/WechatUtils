package com.wechat.wxpay.Config;

/**
 * @Description:
 * @Date: 2019/03/06
 * @Author: Lovelyz
 */
public class WxPayConfig {
    //小程序appid
    //  public static final String appid = "wxe08feaf00231abb8"; //装之梦
     public static final String appid = "wx8b47dd641f535372";  //红笺伴侣
//    public static final String appid = "wx3d73207c3f2a2b3d";  //
    //小程序密钥
    // public static final String appkey = "41498f80c03bf69b3d6093b93d07b28d";//装之梦
      public static final String appkey = "576e218e059bdc8b3177b37d51292d1d";//红笺伴侣
//    public static final String appkey = "af21d3e0bd6877b56b5ada4d18a92890";
    //微信支付的商户id
    public static final String mch_id = "1513930121";
    //微信支付的商户密钥
    public static final String mch_key = "pWqm7wNeb4cuTco8cyOxce1jnyv2nstg";
    //支付成功后的服务器回调url
    //****
    //支付成功后的服务器回调url
    public static final String notify_url = "https://??/weixin/wxNotify";
    //退款接口
    public static final String refund_url="https://api.mch.weixin.qq.com/secapi/wechat/refund";
    //签名方式
    public static final String SIGNTYPE = "MD5";
    //交易类型
    public static final String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public static final String pay_url = "https://api.mch.weixin.qq.com/wechat/unifiedorder";
    // 获取access_token的URL
    public static final String WECHAT_GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";
    // 获取openId的URL
    public static final String WECHAT_GET_OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session";
    // 获取推广二维码的URL
    public static final String  WECHAT_GET_EXTENTSION_QRCODE_URL= "https://api.weixin.qq.com/wxa/getwxacode";

}
