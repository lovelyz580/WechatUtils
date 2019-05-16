package com.wechat.wxpay.restful;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wechat.wxpay.util.ExcelUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.wechat.wxpay.entity.Code;


@Controller
@RequestMapping("/excel")
public class ExcelController {
    /**
     * 上传多个附件的操作类
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String doUploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        if (!file.isEmpty()) {
            try {
                // 这里将上传得到的文件保存指定目录下
                FileUtils.copyInputStreamToFile(file.getInputStream(),
                new File("d:\\upload\\file\\", System.currentTimeMillis() + file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        InputStream in = null;
        List<List<Object>> listob = null;
        in = file.getInputStream();
        listob = new ExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
        // 该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            Code vo = new Code();
            vo.setCode(String.valueOf(lo.get(0)));
            vo.setName(String.valueOf(lo.get(1)));
            vo.setDate(String.valueOf(lo.get(2)));
            vo.setMoney(String.valueOf(lo.get(3)));
            System.out.println("打印信息-->机构:" + vo.getCode() + "  名称：" + vo.getName() + "   时间：" + vo.getDate() + "   资产："
                    + vo.getMoney());
        }
        return "success"; // 上传成功则跳转至此success的信息
    }


    /**
     * 下载excel
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        request.setCharacterEncoding("UTF-8");
        String path = request.getSession().getServletContext().getRealPath("/resources/");
        String fileName = "template.xlsx";
        try {
            File f = new File(path + fileName);
            response.setContentType("application/x-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            response.setHeader("Content-Length", String.valueOf(f.length()));
            in = new BufferedInputStream(new FileInputStream(f));
            out = new BufferedOutputStream(response.getOutputStream());
            byte[] data = new byte[1024];
            int len = 0;
            while (-1 != (len = in.read(data, 0, data.length))) {
                out.write(data, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
