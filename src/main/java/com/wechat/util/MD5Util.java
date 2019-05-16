package com.wechat.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 *
 * @author Lovelyz on 2019/03/21
 */
public class MD5Util {
        private static final int MAX_BYTE_VALUE = 255;

        private MD5Util() {
        }

        public static String encrypt(String str) {
            MessageDigest messageDigest = null;

            try {
                messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                messageDigest.update(str.getBytes("UTF-8"));
            } catch (NoSuchAlgorithmException var5) {
                var5.printStackTrace();
                return null;
            } catch (UnsupportedEncodingException var6) {
                var6.printStackTrace();
                return null;
            }

            byte[] byteArray = messageDigest.digest();
            StringBuffer md5StrBuff = new StringBuffer();

            for(int i = 0; i < byteArray.length; ++i) {
                if(Integer.toHexString(255 & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(255 & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(255 & byteArray[i]));
                }
            }

            return md5StrBuff.toString();
        }

}
