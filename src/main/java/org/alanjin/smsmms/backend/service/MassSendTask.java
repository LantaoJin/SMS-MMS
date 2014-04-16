package org.alanjin.smsmms.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.bean.MessageModel;
import org.alanjin.smsmms.backend.util.SMSTool;
import org.alanjin.smsmms.backend.util.Util;

public class MassSendTask extends TimerTask {
	private String scheduleName;
	private MessageModel messageModel;
	
	public MassSendTask(String scheduleName,
			MessageModel messageModel,
			SMSAction smsAction) {
		this.scheduleName = scheduleName;
		this.messageModel = messageModel;
	}
	
	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public MessageModel getMessageModel() {
		return messageModel;
	}

	public void setMessageModel(MessageModel messageModel) {
		this.messageModel = messageModel;
	}
	
	@Override
	public void run() {
		MemberAction memberAction = MemberAction.newInstance();
		// 找到今天生日的列表，读数据库。
		String fullBirthdayString = Util.fromNormalDate(new Date());
		List<Member> memberToSend = memberAction.getMembersByBirthDay(fullBirthdayString);
		// 获取需要发的短信模版
		
		// 发短信
		List<Map<String, String>> messages = new ArrayList<Map<String, String>>();
		for (Member toSend : memberToSend) {
			Map<String, String> senderPair = new HashMap<String, String>();
			senderPair.put("mobile", toSend.getPhone());
			senderPair.put("content", toSend.getName());
			messages.add(senderPair);
		}
		List<Map<String, String>> failList = SMSTool.sendSms(messages, true);
		if (failList.size() != 0) {
			//TODO
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((scheduleName == null) ? 0 : scheduleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MassSendTask other = (MassSendTask) obj;
		if (scheduleName == null) {
			if (other.scheduleName != null)
				return false;
		} else if (!scheduleName.equals(other.scheduleName))
			return false;
		return true;
	}
}
