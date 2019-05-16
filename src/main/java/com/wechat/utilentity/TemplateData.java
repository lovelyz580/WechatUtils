package com.wechat.utilentity;

/**
 * 服务通知实体
 * @author Lovelyz on 2019/04/10
 */

public class TemplateData {
    private String value;

    private String color;

    public TemplateData() { super(); } public TemplateData(String value, String color) { super(); this.value = value; this.color = color; } public String getValue() { return value; } public void setValue(String value) { this.value = value; } public String getColor() { return color; } public void setColor(String color) { this.color = color; }
}