package ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.Observer;
import logic.Subject;

public class CalendarTable extends JPanel implements ActionListener, MouseListener, Observer{
	JLabel[] daylabel=new JLabel[49];
	JLabel lYear=new JLabel("年");
	JLabel lMonth=new JLabel("月");
	    
	Date nowDate = new Date(System.currentTimeMillis());
	Integer nowYear=nowDate.getYear()+1900;
	Integer nowMonth=nowDate.getMonth();
	
	Integer month=0;
	String year=null;
	
	boolean bool=false;
	JComboBox comboboxyear=new JComboBox();
	JComboBox comboboxmonth=new JComboBox();
	    
	JPanel pYearMonth=new JPanel();
	JPanel pDay=new JPanel();
	Calendar c=Calendar.getInstance();
	
	CalendarTable()
	{
		Subject.getSub().attach(this);
		
		for(int i=nowYear-10;i<nowYear+20;i++)
			comboboxyear.addItem(i+"");
		for(int i=1;i<13;i++)
			comboboxmonth.addItem(i+"");
		comboboxyear.setSelectedIndex(10);
		comboboxyear.addActionListener(this);
		comboboxmonth.setSelectedIndex(nowMonth);
		comboboxmonth.addActionListener(this);
		JLabel label_1 = new JLabel("    ");
		pYearMonth.setBounds(0, 0, 300, 38);
		pYearMonth.setBackground(Color.GRAY);
		pYearMonth.setForeground(Color.red);
		pDay.setBounds(5, 40, 290, 240);
	       
		pDay.setLayout(new GridLayout(7,7,10,10));
		for(int i=0;i<49;i++){
			daylabel[i]=new JLabel(" ");
			daylabel[i].setHorizontalAlignment(JLabel.CENTER);
			daylabel[i].addMouseListener(this);
			pDay.add(daylabel[i]);
		}
		setLayout(null);
		this.add(pYearMonth);
		pYearMonth.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		JLabel label = new JLabel("                ");
		pYearMonth.add(label);
		pYearMonth.add(comboboxyear);
		lYear.setFont(new Font("楷体", Font.PLAIN, 15));
		lYear.setForeground(Color.WHITE);
		pYearMonth.add(lYear);
		pYearMonth.add(comboboxmonth);
		lMonth.setFont(new Font("楷体", Font.PLAIN, 15));
		lMonth.setForeground(Color.WHITE);
		pYearMonth.add(lMonth);
		pYearMonth.add(label_1);
		this.add(pDay);   
	}
	
	public void setToday() {
		nowDate = new Date(System.currentTimeMillis());
		nowYear=nowDate.getYear()+1900;
		nowMonth=nowDate.getMonth();
		comboboxyear.setSelectedIndex(nowYear-2005);
		comboboxmonth.setSelectedIndex(nowMonth);
	}
	
	public Date getNowDate() {
		return nowDate;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub

		if (bool)
		{
			year=nowYear+"";
			month=nowDate.getMonth();
		}
		else
		{
			year=comboboxyear.getSelectedItem().toString();
	    	month=comboboxmonth.getSelectedIndex();
		}
		int year_Y=Integer.parseInt(year)-1900;
		Date dt=new Date(year_Y,month,1);
		GregorianCalendar calendar=new GregorianCalendar();
		Calendar cal=Calendar.getInstance();
		cal.set(year_Y,month,1);
        String[] week={"日","一","二","三","四","五","六"};
        int day=0,dayweek=0;
        day=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        for(int i=0;i<7;i++)
        {
            daylabel[i].setForeground(Color.black);
            daylabel[i].setText(week[i]);
            daylabel[i].setFont(new Font("楷体", Font.PLAIN, 18));
        }
	    dayweek=7+dt.getDay();
	    int count=1;
	    for(int i=dayweek;i<dayweek+day;i++,count++)
	    {
           	if(i%7==0||(i+1)%7==0)
           	{
        	   	daylabel[i].setForeground(Color.blue);
        	   	daylabel[i].setFont(new Font("Romantic", Font.PLAIN, 15));
        	   	daylabel[i].setText(count+"");
           	} 
           	else{
	           	daylabel[i].setForeground(Color.black);
	           	daylabel[i].setFont(new Font("Romantic", Font.PLAIN, 15));
	           	daylabel[i].setText(count+"");
	       	}
           
           	if(i==dayweek+nowDate.getDate()-1)
           	{
           		daylabel[i].setForeground(Color.red);
	            daylabel[i].setFont(new Font("Romantic", Font.PLAIN, 24));
	            daylabel[i].setText(count+"");
           	}
	   
       }
       
	   if(dayweek==0)
	   {
	       for(int i=day;i<49;i++)
	       {
	           daylabel[i].setText("");
	       }
	   }
	   else
	   {
	       for(int i=7;i<dayweek;i++)
	           daylabel[i].setText("");
	       for(int i=dayweek+day;i<49;i++)
	           daylabel[i].setText("");
	   }
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==comboboxyear||e.getSource()==comboboxmonth)
		{
			bool=false;
			Subject.getSub().notifyObservers();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String str = ((JLabel)e.getSource()).getText();
		if (str.compareTo("") != 0)
		{
			nowDate.setDate(Integer.parseInt(((JLabel)e.getSource()).getText()));
			Subject.getSub().notifyObservers();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
