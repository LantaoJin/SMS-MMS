package org.alanjin.smsmms.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.bean.MessageModel;
import org.alanjin.smsmms.backend.util.SendMessageWithPortsSMSLib;
import org.alanjin.smsmms.backend.util.Util;

public class Schedule implements Runnable {
	private SendMessageWithPortsSMSLib smsTool;
	private List<Member> memberToSend;
	private MessageModel messageModel;
	private MemberAction memberAction;
	
	public Schedule(SendMessageWithPortsSMSLib smsTool,
			MessageModel messageModel,
			MemberAction memberAction,
			SMSAction smsAction) {
		this.smsTool = smsTool;
		this.memberAction = memberAction;
		this.messageModel = messageModel;
	}
	
	public MessageModel getMessageModel() {
		return messageModel;
	}

	public void setMessageModel(MessageModel messageModel) {
		this.messageModel = messageModel;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 找到今天生日的列表，读数据库。
		String fullBirthdayString = Util.fromNormalDate(new Date());
		memberToSend = memberAction.getMembersByBirthDay(fullBirthdayString);
		// 获取需要发的短信模版
		
		// 发短信
		List<Map<String, String>> messages = new ArrayList<Map<String, String>>();
		for (Member toSend : memberToSend) {
			Map<String, String> senderPair = new HashMap<String, String>();
			senderPair.put("mobile", toSend.getPhone());
			senderPair.put("content", toSend.getName());
			messages.add(senderPair);
		}
		List<Map<String, String>> failList = smsTool.sendSms(messages);
		if (failList.size() != 0) {
			//TODO
		}
	}
}
