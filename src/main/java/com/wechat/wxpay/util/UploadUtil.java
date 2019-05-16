package com.wechat.wxpay.util;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.wechat.wxpay.entity.Json;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * 上传文件
 *
 */
@Controller
@MultipartConfig
@RequestMapping("/upload")
public class UploadUtil extends HttpServlet {
    /**
     * 用户上传图片
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     *
     * @author Lovelyz on 2019/03/21
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public JSONObject uploadHeadImage(@RequestParam("file") MultipartFile file,
                                      HttpServletRequest request) throws IOException {
        Assert.notNull(file, "上传文件不能为空");

        String path = request.getServletContext().getRealPath("/upload");
        // System.currentTimeMillis() 根据系统时间产生随机数，保证上传的图片名字不一样
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String name = System.currentTimeMillis() + suffix;
        File dir = new File(path, name);
        JSONObject json = new JSONObject();
        if (!dir.exists()) {
            dir.mkdirs();
            json.put("msg", "上传成功");
            json.put("code", 1);
            json.put("name", name);
            json.put("path", path);
        } else {
            json.put("msg", "上传失败");
            json.put("code", 0);
        }
        file.transferTo(dir);
        return json;
    }

    /**
     * 用户上传视频
     *
     * @param file
     * @param request
     * @param stream
     * @return
     * @throws IOException
     *
     * @author Lovelyz on 2019/03/21
     */
    @RequestMapping("/uploadVideo")
    @ResponseBody
    public JSONObject uploadHeadVideo(@RequestParam("file") MultipartFile file,
                                      HttpServletRequest request, InputStream stream) throws IOException {
        Assert.notNull(file, "上传文件不能为空");
        String path = request.getServletContext().getRealPath("/upload/video");
        // System.currentTimeMillis() 根据系统时间产生随机数，保证上传的图片名字不一样
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String name = System.currentTimeMillis() + suffix;
        File dir = new File(path, name);
        JSONObject json = new JSONObject();
        if (!dir.exists()) {
            dir.mkdirs();
            json.put("msg", "上传成功");
            json.put("code", 1);
            json.put("name", name);
            json.put("path", path);
        } else {
            json.put("msg", "上传失败");
            json.put("code", 0);
        }
        file.transferTo(dir);
        return json;
    }
    String filename = null;
    @RequestMapping("/file")
    @ResponseBody
    public Json upload(MultipartFile file, HttpServletRequest request) throws IOException {
        Json json = new  Json();
        //获取上传文件的目录
        String path = request.getSession().getServletContext().getRealPath("/upload");
        String fileName = file.getOriginalFilename();
        if ("".equals(fileName)){
            json.setMsg("未获取到文件");
            json.setSuccess(false);
            json.setCode(500);
            json.getData();
        }else {
            //        获取后缀名
            String str = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            //生成一个新的文件名，不重复，数据库存储的就是这个文件名，不重复的
            filename = UUID.randomUUID().toString() + str;
            File dir = new File(path, filename);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            file.transferTo(dir);
            System.out.println(filename);
            json.setData(filename);
            json.setSuccess(true);
            json.setCode(200);
            json.setMsg("文件上传成功");
        }
        return json;
    }
}
