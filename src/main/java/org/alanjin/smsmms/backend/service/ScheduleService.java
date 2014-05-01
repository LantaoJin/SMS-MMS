package org.alanjin.smsmms.backend.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class ScheduleService {
    private Map<String, MassSendTask> runingSchedules;
    private Timer timer;

    public ScheduleService() {
        this.runingSchedules = Collections
                .synchronizedMap(new HashMap<String, MassSendTask>());
        this.timer = new Timer();
    }

    public void addFixedTimeTaskAndRun(MassSendTask task, int hour, int min,
            int second) {
        if (runingSchedules.containsKey(task.getTaskName())) {
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        if (date.before(new Date())) {
            date = addDay(date, 1);
        }
        timer.schedule(task, date, 24 * 60 * 60 * 1000);
        runingSchedules.put(task.getTaskName(), task);
    }

    private Date addDay(Date date, int i) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, i);
        return startDT.getTime();
    }

    public void addPeroidTaskAndRun(MassSendTask task, Date firstTime,
            long peroid) {
        if (runingSchedules.containsKey(task.getTaskName())) {
            return;
        }
        timer.schedule(task, firstTime, peroid);
        runingSchedules.put(task.getTaskName(), task);
    }

    public boolean taskExist(String task) {
        return runingSchedules.containsKey(task);
    }

    public Map<String, MassSendTask> listRuningTasks() {
        return runingSchedules;
    }
    
    public boolean hasTask() {
        return runingSchedules.size() != 0;
    }
    
    public void stopTask(String taskName) {
        runingSchedules.remove(taskName);
    }
    
    public void close() {
        runingSchedules.clear();
        timer.cancel();
    }
}
