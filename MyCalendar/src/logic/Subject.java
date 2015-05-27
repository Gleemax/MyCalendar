package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Subject extends Observable {
	
	List<Observer> ObserverList = new ArrayList<Observer>();
	
	public final static Subject subject = new Subject();
	
	public static Subject getSub()
	{
		return subject;
	}
	
	public void attach(Observer observer) 
	{
		ObserverList.add(observer);
	}
	
	public void detach(Observer observer)
	{
		ObserverList.remove(observer);
	}
	
	public void notifyObservers() 
	{
		for (Observer iter:ObserverList)
		{
			iter.update();
		}
	}
}
