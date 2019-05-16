package com.wechat.wxpay.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:Lovelyz
 * @Date: Created in 20:20 2018\1\9 0009
 */
public class PathUtil {
    private static String seperator = System.getProperty("file.separator");

    public static String getImgBasePath(HttpServletRequest request){
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")){
            basePath=request.getSession().getServletContext().getRealPath("/upload");
        }else{
            basePath = request.getSession().getServletContext().getRealPath("/upload");
        }
        basePath = basePath.replace("/",seperator);
        return basePath;
    }

    public static String getImagePath(int bookId){
        String imagePath = "/upload/images/item/book/"+bookId+"/";
        return imagePath.replace("/",seperator);
    }

    public static String getCodePath(HttpServletRequest request, String scence){
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")){
            basePath=request.getSession().getServletContext().getRealPath("/upload");
        }else{
            basePath = request.getSession().getServletContext().getRealPath("/upload");;
        }
        basePath = basePath.replace("/",seperator);
        String imagePath = basePath+"/upload/"+scence+"/";
        return imagePath.replace("/",seperator);
    }

}
