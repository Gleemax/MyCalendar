package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Date;
import java.sql.Time;

import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.sun.awt.AWTUtilities;

import controller.ScheduleController;

public class AddScheWindow extends JFrame implements MouseListener, ActionListener, MouseMotionListener{

	static Point origin = new Point();
	
	private Date date;
	
	JEditorPane editorPane = new JEditorPane();
	JComboBox comboboxhour=new JComboBox();
	JComboBox comboboxminute=new JComboBox();
	JComboBox comboboxsecond=new JComboBox();
			
	private final JLabel hourlabel = new JLabel(":");
	private final JLabel minutelabel = new JLabel(":");
	private final JLabel timelabel = new JLabel("\u65F6\u95F4:");
	private final JPanel panel = new JPanel();
	private final JPanel contentpanel = new JPanel();
	private final JLabel contentlabel = new JLabel("\u65E5\u7A0B:");
	
	private final JLabel Confirm = new JLabel("\u221A");
	private final JLabel Cancle = new JLabel("\u00D7");
	
	public AddScheWindow(Date date)
	{
		this.date = date;
		
		this.setLocation(Client.getPosition());		
		
		this.setUndecorated(true);
	    this.setBackground(new Color(0, 0, 0));
	    this.setSize(240, 220);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		getContentPane().setLayout(null);
		
		for (Integer i=0; i<23; i++) comboboxhour.addItem(i+"");
		for (Integer i=0; i<60; i++) comboboxminute.addItem(i+"");
		for (Integer i=0; i<60; i++) comboboxsecond.addItem(i+"");
		
		comboboxhour.setBounds(40, 5, 40, 21);
		comboboxhour.setSelectedIndex(0);
		comboboxhour.addActionListener(this);
		comboboxminute.setBounds(90, 5, 40, 21);
		comboboxminute.setSelectedIndex(0);
		comboboxminute.addActionListener(this);
		comboboxsecond.setBounds(140, 5, 40, 21);
		comboboxsecond.setSelectedIndex(0);
		comboboxsecond.addActionListener(this);
		
		Confirm.setBounds(205, 185, 20, 20);
		getContentPane().add(Confirm);
		Confirm.addMouseListener(this);
		Confirm.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 20));
		Cancle.setBounds(15, 185, 20, 20);

		getContentPane().add(Cancle);
		Cancle.addMouseListener(this);
		Cancle.setFont(new Font("Verdana", Font.BOLD, 20));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 10, 220, 200);
		
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel timepanel = new JPanel();
		timepanel.setBounds(10, 10, 200, 30);
		panel.add(timepanel);
		
		timepanel.setLayout(null);
		timelabel.setBounds(5, 8, 30, 15);
		timelabel.setFont(new Font("ËÎÌו", Font.PLAIN, 12));
		timepanel.add(timelabel);
		timepanel.add(comboboxhour);
		
		hourlabel.setBounds(80, 5, 9, 21);
		hourlabel.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		timepanel.add(hourlabel);
		timepanel.add(hourlabel);
		timepanel.add(comboboxminute);
		minutelabel.setBounds(130, 5, 9, 21);
		minutelabel.setFont(new Font("ËÎÌו", Font.PLAIN, 18));
		
		timepanel.add(minutelabel);
		timepanel.add(comboboxsecond);
		contentpanel.setBounds(10, 45, 200, 125);
		
		panel.add(contentpanel);
		contentpanel.setLayout(null);
		contentlabel.setBounds(5, 5, 30, 15);
		
		contentpanel.add(contentlabel);
		
		editorPane.setBounds(40, 5, 155, 115);
		contentpanel.add(editorPane);
		
		AWTUtilities.setWindowOpaque(this, true);
    	AWTUtilities.setWindowOpacity(this, 0.8f);
    	   	
    	this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==Confirm)
		{
			Integer hour = Integer.parseInt((String)comboboxhour.getSelectedItem());
			Integer minute = Integer.parseInt((String)comboboxminute.getSelectedItem());
			Integer second = Integer.parseInt((String)comboboxsecond.getSelectedItem());
			Time now = new Time(hour, minute ,second);
			String schedule = editorPane.getText();
			new ScheduleController().addSchedule(date, now, schedule);
		}
		if (e.getSource()==Cancle)
		{
			this.dispose();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		origin.x = e.getX();
		origin.y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = this.getLocation();
		this.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y); 
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
