package controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Vector;

import logic.Observer;
import logic.Subject;
import model.Schedule;
import dao.ScheduleDAO;

public class ScheduleController implements Observer {
	
	private List<Schedule> ScheduleList;
	
	public ScheduleController() {
	}
	
	public Vector<Vector<String>> getSchedule(Date nowDate) {
		
		ScheduleList = new ScheduleDAO().findByProperty("schDate", nowDate);
		
		Vector<Vector<String>> result = new Vector<Vector<String>>();
		for (Schedule iter:ScheduleList)
		{
			Vector<String> scheduleinfo = new Vector<String>();
			scheduleinfo.add(iter.getSchTime().toString());
			scheduleinfo.add(iter.getSchContent());
			result.add(scheduleinfo);
		}
		
		return result;
	}
	
	public void addSchedule(Date date, Time time, String scheContent) {
		Schedule newSchedule = new Schedule();
		newSchedule.setSchDate(date);
		newSchedule.setSchTime(time);
		newSchedule.setSchContent(scheContent);
		
		new ScheduleDAO().save(newSchedule);
		
		Subject.getSub().notifyObservers();
	}
	
	public void removeSchedule(int rowCount)
	{
		if (rowCount != -1)
		{
			Schedule schedule = ScheduleList.get(rowCount);
			
			new ScheduleDAO().delete(schedule);
			
			Subject.getSub().notifyObservers();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}