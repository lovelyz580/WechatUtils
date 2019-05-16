package com.wechat.util;

import org.quartz.*;

import java.util.Date;

/**
 * Created by Lovelyz
 * on 2019-04-15 08:39
 */
public class QuertzUtil implements Job {


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        JobDataMap map = context.getJobDetail().getJobDataMap();
//        Memorandum memorandum = (Memorandum) map.get("memorandum");
//        System.out.println("开始执行Quertz定时推送");
//        Date now = new Date();
//        System.out.println(now + "Quertz推+送+时+间Quertz");
////		执行推送
//        String openId = memorandum.getmUid();
//        String templateId = "UIMqKoW_cRYMlO6Zs9IfHANwvJOs2WVO90poC9ZOlGM";
//        String formId = memorandum.getmFormid();
//        String url = "http://weixin.qq.com/download";
//        String carrierName = "keyword1";
//        String waybillCode = "keyword2";
//        String waybillDesc = "keyword3";
//        String waybillMark = "keyword4";
//        //获取Token
//        String token = GetTokenUtil.getAccessToken();
////        创建推送内容
//        String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId,
//                url, "#ff6600", carrierName, waybillCode, waybillDesc, waybillMark);
////		推送
//        boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
		boolean result = true;
        if (result) {
            System.out.println("推送成功");
        } else {
            System.out.println("推送失败");
        }
        System.out.println("定时执行");
        System.out.println("我的Timer名字是：");
    }

}

