package org.alanjin.smsmms.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.bean.MessageModel;
import org.alanjin.smsmms.backend.util.Util;
import org.alanjin.smsmms.frontend.util.Lunar;

public class MassSendTask extends TimerTask {
    private String taskName;
    private MessageModel messageModel;
    private boolean useLunar;

    public MassSendTask(String taskName, boolean useLunar,
            MessageModel messageModel) {
        this.taskName = taskName;
        this.useLunar = useLunar;
        this.messageModel = messageModel;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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
        String fullBirthdayString;
        if (useLunar) {
            fullBirthdayString = Lunar.solarTolunar(Util
                    .fromNormalDate(new Date()));
        } else {
            fullBirthdayString = Util.fromNormalDate(new Date());
        }
        List<Member> memberToSend = memberAction.getMembersByBirthDayStr(
                fullBirthdayString, useLunar);

        List<Map<String, String>> messages = new ArrayList<Map<String, String>>();
        List<Map<String, String>> noneeds = new ArrayList<Map<String, String>>();
        for (Member toSend : memberToSend) {
            Map<String, String> senderPair = new HashMap<String, String>();
            senderPair.put("mobile", toSend.getPhone());
            senderPair.put("content", messageModel.getContent());
            if (!org.alanjin.smsmms.frontend.util.Util.isMobileNO(toSend
                    .getPhone())) {
                noneeds.add(senderPair);
                continue;
            }
            messages.add(senderPair);
        }
        List<Map<String, String>> failList = SenderAndReceiverService.sendSms(
                messages, true);
        for (Map<String, String> noneed : noneeds) {
            failList.add(noneed);
        }
        if (failList.size() != 0) {
            // TODO
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((taskName == null) ? 0 : taskName.hashCode());
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
        if (taskName == null) {
            if (other.taskName != null)
                return false;
        } else if (!taskName.equals(other.taskName))
            return false;
        return true;
    }
}
