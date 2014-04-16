package org.alanjin.smsmms.backend.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.ofbiz.entity.GenericDelegator;
import org.smslib.GatewayException;
import org.smslib.IInboundMessageNotification;
import org.smslib.IOutboundMessageNotification;
import org.smslib.InboundMessage;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.Message.MessageEncodings;
import org.smslib.Message.MessageTypes;
import org.smslib.modem.SerialModemGateway;

public class SMSTool {
	// ������
	static SMSTool smwps = null;
	// ��ȡȫ����Ϣ
	public static final org.smslib.InboundMessage.MessageClasses ALL_MESSAGE = org.smslib.InboundMessage.MessageClasses.ALL;
	// ��ȡ�Ѷ���Ϣ
	public static final org.smslib.InboundMessage.MessageClasses READ_MESSAGE = org.smslib.InboundMessage.MessageClasses.READ;
	// ��ȡδ����Ϣ
	public static final org.smslib.InboundMessage.MessageClasses UNREAD_MESSAGE = org.smslib.InboundMessage.MessageClasses.UNREAD;
	// ��Ϣ����
	private static Service srv = null;
	// ������Ϣ�ص�ʵ����
	OutboundNotification outboundNotification = new OutboundNotification();
	// ��ȡ��Ϣ�ص�ʵ����
	InboundNotification inboundNotification = new InboundNotification();
	// ���ݿ��
	private GenericDelegator delegator = null;

	// �豸����
	private static String gateName = "SMS";

	private SMSTool() {
	}

	// �������ʵ����ֻ����һ������ʵ��

	public static SMSTool newInstance(String com)
			throws TimeoutException, GatewayException, SMSLibException,
			IOException, InterruptedException {
		if (smwps == null)
			smwps = new SMSTool();
		if (srv == null)
			smwps.open(com, gateName);
		return smwps;
	}

	public static SMSTool newInstance(String com,
			GenericDelegator delegator) throws TimeoutException,
			GatewayException, SMSLibException, IOException,
			InterruptedException {
		if (smwps == null)
			smwps = new SMSTool();
		if (srv == null)
			smwps.open(com, gateName);
		smwps.delegator = delegator;
		return smwps;
	}

	public static SMSTool newInstance(String com,
			GenericDelegator delegator, String gateName)
			throws TimeoutException, GatewayException, SMSLibException,
			IOException, InterruptedException {
		if (smwps == null)
			smwps = new SMSTool();
		if (srv == null)
			smwps.open(com, gateName);
		smwps.delegator = delegator;
		SMSTool.gateName = gateName;
		return smwps;
	}

	// �򿪶˿ڣ���������
	private void open(String com, String gateName) throws TimeoutException,
			GatewayException, SMSLibException, IOException,
			InterruptedException {
		srv = new Service();
		// comPort ������������COM1����/dev/ttyS1
		// baudRate �˿��ٶȣ�WAVECOM��9600
		// manufacturer,model �����̺��ͺ������
		SerialModemGateway gateway = new SerialModemGateway(gateName, com,
				9600, "", srv.getLogger().toString());
		gateway.setInbound(true);
		gateway.setOutbound(true);
		// gateway.setSimPin("0000");
		// gateway.setOutboundNotification(outboundNotification);

		// gateway.setInboundNotification(inboundNotification);
		// srv.setOutboundNotification(outboundNotification);
		// srv.setInboundNotification(inboundNotification);
		// srv.S.SERIAL_POLLING_INTERVAL=10;
		// srv.S.SERIAL_POLLING=true;
		srv.addGateway(gateway);
		srv.startService();
	}

	// ��ȡ��Ϣ
	public static List<org.smslib.InboundMessage> readSms(
			org.smslib.InboundMessage.MessageClasses messageType)
			throws TimeoutException, GatewayException, IOException,
			InterruptedException {
		List<InboundMessage> smss = new LinkedList<InboundMessage>();
		// InboundMessage inm=null;
		srv.readMessages(smss, messageType, gateName);
		// System.out.println(smss);
		// System.out.println(msg);
		return smss;
	}

	// ���͵�����Ϣ
	public static boolean sendSms(String mobile, String content,
			boolean tryAgain) {
		OutboundMessage msg = new OutboundMessage(mobile, content);
		msg.setEncoding(MessageEncodings.ENCUCS2);
		// msg.setStatusReport(true);
		try {
			srv.sendMessage(msg);
			// System.out.println(msg);
		} catch (InterruptedException e) {
			// TODO LOG
		} catch (Exception e) {
			// System.out.println("--"+e);
			if (tryAgain) {
				try {
					srv.sendMessage(msg);
				} catch (InterruptedException e1) {
				} catch (Exception e1) {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}

	// Ⱥ����Ϣ
	public static List<Map<String, String>> sendSms(
			List<Map<String, String>> messages, boolean tryAgain) {
		List<Map<String, String>> failList = new ArrayList<Map<String, String>>();
		Iterator<Map<String, String>> itr = messages.iterator();
		while (itr.hasNext()) {
			Map<String, String> message = (Map<String, String>) itr.next();
			String mobile = (String) message.get("mobile");
			String content = (String) message.get("content");
			if (!sendSms(mobile, content, tryAgain)) {
				failList.add(message);
			}
		}
		return failList;
	}

	// �رշ���
	public static void close() {
		try {
			srv.stopService();
		} catch (Exception ex) {
			System.out.println("**" + ex);
		}
	}

	public class OutboundNotification implements IOutboundMessageNotification {
		public void process(String gatewayId, OutboundMessage msg) {
			System.out.println("Outbound handler called from Gateway: "
					+ gatewayId);
			System.out.println(msg);
		}
	}

	public class InboundNotification implements IInboundMessageNotification {

		public void process(String arg0, MessageTypes arg1, InboundMessage arg2) {
			// TODO Auto-generated method stub
			// System.out.println(arg0);
			// System.out.println(arg1);
			// System.out.println(arg2);

		}

	}

	public static void main(String[] args) throws TimeoutException,
			GatewayException, IOException, InterruptedException {
		String mob = "13636316288";
		String content = "һֻС��ȥɽ�ﴴҵ��ũ�������һ��������ľ��������һ�Ѵ��ӣ� С������ɽ�������ϻ����ŵð����������Ӿ���ͷ���� �ϻ�˵��û���������������������Ǹ���Ա����";
		SMSTool sms = new SMSTool();// newInstance();
		try {
			sms.open("COM6", "SMS");
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SMSLibException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("1�����Ͷ���Ϣ");
		System.out.println("quit���˳�");
		String str = "";
		System.out.println("1�����Ͷ���Ϣ");

		System.out.println("quit���˳�");
		while (true) {
			System.out.print("��ѡ��   ");
			InputStreamReader stdin = new InputStreamReader(System.in);// ��������
			BufferedReader bufin = new BufferedReader(stdin);
			try {
				str = bufin.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (str.equals("quit")) {
				SMSTool.close();
				break;
			} else if (str.equals("1"))
				System.out.println(SMSTool.sendSms(mob,
						content, false));
			else {
				System.out.println("����Ϣ!");
				SMSTool.readSms(SMSTool.ALL_MESSAGE);
			}
		}
		// sms.readSms();
		// sms.sendSms(mob, content);
		// sms.close();
	}

}
