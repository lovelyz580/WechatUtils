package com.wechat.wxpay.util;
import com.wechat.wxpay.Config.WxPayConfig;
import net.sf.json.JSONObject;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 获取微信小程序的token
 */
public class GetTokenUtil {
    public static String getAccessToken() {
        String APPID = WxPayConfig.appid;               //填写微信小程序的APPID
        String APPSECRET = WxPayConfig.appkey;       //填写微信小程序的APPSECRET
        System.out.println("====================获取token开始==============================");
        String url = WxPayConfig.WECHAT_GET_ACCESS_TOKEN_URL + APPID + "&secret=" + APPSECRET;
        String accessToken = null;
        String expiresIn = null;
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET");           // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.fromObject(message);
            accessToken = demoJson.getString("access_token");
            expiresIn = demoJson.getString("expires_in");
            System.out.println("accessToken====" + accessToken);
            System.out.println("expiresIn===" + expiresIn);
            System.out.println("====================获取token结束==============================");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }
}
