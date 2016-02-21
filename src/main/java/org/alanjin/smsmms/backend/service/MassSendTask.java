package org.alanjin.smsmms.backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.alanjin.smsmms.backend.bean.Member;
import org.alanjin.smsmms.backend.bean.MessageModel;
import org.alanjin.smsmms.backend.bean.Response;
import org.alanjin.smsmms.backend.util.BackendUtil;
import org.alanjin.smsmms.frontend.util.FrontendUtil;
import org.alanjin.smsmms.frontend.util.Lunar;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MassSendTask extends TimerTask {
	private static final Log LOG = LogFactory.getLog(MassSendTask.class);
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
            fullBirthdayString = Lunar.solarTolunar(BackendUtil
                    .fromNormalDate(new Date()));
        } else {
            fullBirthdayString = BackendUtil.fromNormalDate(new Date());
        }
        List<Member> memberToSend = memberAction.getMembersByBirthDayStr(
                fullBirthdayString, useLunar);

        List<String> toSendList = new ArrayList<String>();
        List<Member> noSendList = new ArrayList<Member>();
        for (Member toSend : memberToSend) {
            if (toSend.getPhone().isEmpty()
                    || !FrontendUtil.isMobileNO(toSend.getPhone())) {
                noSendList.add(toSend);
                continue;
            }
            toSendList.add(toSend.getPhone());
        }
        System.out.println("定时短信to Send list:" + toSendList);
        LOG.info("定时短信to Send list:" + toSendList);
        String resultJson = SenderAndReceiverService.sendSms(messageModel.getContent(), toSendList);
        Response response = null;
        try {
            response = BackendUtil.parseResponse(resultJson);
        } catch (Exception e1) {
            System.out.println("未知异常，请联系管理员！提示:" + e1.getMessage() + e1.getCause() + resultJson);
            LOG.error("未知异常，请联系管理员！返回结果" + resultJson, e1);
            return;
        }
        if (response.getCode() == 0) {
            System.out.println("定时短信群发成功!选择发送" + toSendList.size()
                    + "条,实际发送" + response.getCount() + "条!帐号扣费" + response.getFee()
                    +"元,另外有" + noSendList.size() + "个因手机号不合格而未发送.");
            LOG.info("定时短信群发成功!选择发送" + toSendList.size()
                    + "条,实际发送" + response.getCount() + "条!帐号扣费" + response.getFee()
                    +"元,另外有" + noSendList.size() + "个因手机号不合格而未发送.");
        } else {
            System.out.println("定时短信群发失败！原因:"+ response.getMsg() + ",提示:" + response.getDetail());
            LOG.info("定时短信群发失败！原因:"+ response.getMsg() + ",提示:" + response.getDetail());
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
