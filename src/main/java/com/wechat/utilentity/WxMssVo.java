package com.wechat.utilentity;

import java.util.Map;

/**
 * 接单区域表
 * 
 * 实体类
 * 
 * @author WJF on 2018/09/04
 */

public class WxMssVo {

    private String touser;//接收人的openId
    private String template_id;//模版id
    private String url;//点击模版访问url
    private String topcolor;//消息头部颜色

    public Map<String, String> getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(Map<String, String> miniprogram) {
        this.miniprogram = miniprogram;
    }

    private Map<String,String> miniprogram;  //需要跳转的页面
    private Map<String,TemplateData> data;//消息内容
    private String form_id;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopcolor() {
        return topcolor;
    }

    public void setTopcolor(String topcolor) {
        this.topcolor = topcolor;
    }

    public Map<String, TemplateData> getData() {
        return data;
    }

    public void setData(Map<String, TemplateData> data) {
        this.data = data;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }
}