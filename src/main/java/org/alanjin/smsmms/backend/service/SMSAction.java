package org.alanjin.smsmms.backend.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class SMSAction {
	private Map<String, MassSendTask> runingSchedules;
	private Timer timer;
	
	public SMSAction () {
		this.runingSchedules = new HashMap<String, MassSendTask>();
		this.timer = new Timer();
	}
	
	public void addFixedTimeTaskAndRun(MassSendTask task, int hour, int min, int second) {
		if (runingSchedules.containsKey(task.getScheduleName())) {
			return;
		}

		Calendar calendar = Calendar.getInstance();
	    int year = calendar.get(Calendar.YEAR);
	    int month = calendar.get(Calendar.MONTH);
	    int day = calendar.get(Calendar.DAY_OF_MONTH);
	    //定制每天执行
	    calendar.set(year, month, day, hour, min, second);
		timer.schedule(task, calendar.getTime());
		runingSchedules.put(task.getScheduleName(), task);
	}
	
	public void addPeroidTaskAndRun(MassSendTask task, Date firstTime, long peroid) {
		if (runingSchedules.containsKey(task.getScheduleName())) {
			return;
		}
		timer.schedule(task, firstTime, peroid);
		runingSchedules.put(task.getScheduleName(), task);
	}

	public boolean taskExist(String task) {
		return runingSchedules.containsKey(task);
	}
}
