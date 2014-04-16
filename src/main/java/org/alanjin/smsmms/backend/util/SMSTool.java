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
	// 自身类
	static SMSTool smwps = null;
	// 读取全部消息
	public static final org.smslib.InboundMessage.MessageClasses ALL_MESSAGE = org.smslib.InboundMessage.MessageClasses.ALL;
	// 读取已读消息
	public static final org.smslib.InboundMessage.MessageClasses READ_MESSAGE = org.smslib.InboundMessage.MessageClasses.READ;
	// 读取未读消息
	public static final org.smslib.InboundMessage.MessageClasses UNREAD_MESSAGE = org.smslib.InboundMessage.MessageClasses.UNREAD;
	// 消息服务
	private static Service srv = null;
	// 发送消息回调实现类
	OutboundNotification outboundNotification = new OutboundNotification();
	// 读取消息回调实现类
	InboundNotification inboundNotification = new InboundNotification();
	// 数据库柄
	private GenericDelegator delegator = null;

	// 设备名称
	private static String gateName = "SMS";

	private SMSTool() {
	}

	// 构造类的实例，只产生一个对象实例

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

	// 打开端口，开启服务
	private void open(String com, String gateName) throws TimeoutException,
			GatewayException, SMSLibException, IOException,
			InterruptedException {
		srv = new Service();
		// comPort 串口名，比如COM1或者/dev/ttyS1
		// baudRate 端口速度，WAVECOM是9600
		// manufacturer,model 制造商和型号随便填
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

	// 读取信息
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

	// 发送单条消息
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

	// 群发消息
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

	// 关闭服务
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
		String content = "一只小熊去山里创业，农夫给了他一把镰刀，木匠给了他一把锤子， 小熊来到山里遇到老虎，吓得把镰刀、锤子举在头顶， 老虎说：没看出来，就你这熊样还是个党员来！";
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
		System.out.println("1、发送短消息");
		System.out.println("quit、退出");
		String str = "";
		System.out.println("1、发送短消息");

		System.out.println("quit、退出");
		while (true) {
			System.out.print("请选择：   ");
			InputStreamReader stdin = new InputStreamReader(System.in);// 键盘输入
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
				System.out.println("短消息!");
				SMSTool.readSms(SMSTool.ALL_MESSAGE);
			}
		}
		// sms.readSms();
		// sms.sendSms(mob, content);
		// sms.close();
	}

}
