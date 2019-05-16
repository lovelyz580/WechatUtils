package com.wechat.webcrawder;
import com.wechat.util.WechatFormUtil;
import com.wechat.wxpay.util.GetTokenUtil;

import java.util.Date;
import java.util.TimerTask;

public class Autopush extends TimerTask {


//	public Memorandum getMemorandum() {
//		return memorandum;
//	}
//
//	public void setMemorandum(Memorandum memorandum) {
//		this.memorandum = memorandum;
//	}
//
//	private Memorandum  memorandum;
//
//    //	构造方法
//	public Autopush( Memorandum  memorandum){
//		this.memorandum = memorandum;
//	}


	@Override
	public void run() {
		System.out.println("开始执行定时推送");
		Date now = new Date();
		System.out.println(now +"推+送+时+间");
//		执行推送
//		String openId = memorandum.getmUid();
		String 	templateId = "UIMqKoW_cRYMlO6Zs9IfHANwvJOs2WVO90poC9ZOlGM";
//		String	formId = memorandum.getmFormid();
		String url = "http://weixin.qq.com/download";
		String carrierName=  "keyword1";
		String waybillCode=   "keyword2";
		String waybillDesc =  "keyword3";
		String waybillMark  = "keyword4";
        //获取Token
		String token = GetTokenUtil.getAccessToken();
//        创建推送内容
//		String jsonMsg = WechatFormUtil.makeRouteMessage(openId, templateId, formId,
//				url, "#ff6600",carrierName,waybillCode,waybillDesc,waybillMark);
//		推送
//		boolean result = WechatFormUtil.sendTemplateMessage(token, jsonMsg);
//		boolean result = true;
//		if (result){
//			System.out.println("推送成功");
//		}else {
//			System.out.println("推送失败");
//		}
//		System.out.println("定时执行");
//		System.out.println("我的Timer名字是：" + memorandum.getmName());
	}
}
