package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import logic.Observer;
import logic.Subject;

import com.sun.awt.AWTUtilities;

public final class Client extends JFrame implements ActionListener, MouseListener, MouseMotionListener, Observer, WindowListener{
	
	private static Client window;
	
	private JLabel EXIT;
	private JLabel OnTopToggle;
	private CalendarTable calendar;
	private ScheduleTable schedule;
	
	private Date nowDate;
		
	static Point origin = new Point();
	
	private JLabel EnableDisplay;
	private JLabel ReturnNow;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			window = new Client();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Client() {
		Subject.getSub().attach(this);
		
		this.addWindowListener(this);
		
		this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0));
        this.setSize(300, 300);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		getContentPane().setLayout(null);
		
		EXIT = new JLabel("\u00D7");
		EXIT.setFont(new Font("宋体", Font.PLAIN, 24));
		EXIT.setForeground(Color.WHITE);
		EXIT.setBounds(269, 7, 24, 24);
		EXIT.addMouseListener(this);
		getContentPane().add(EXIT);
		
		OnTopToggle = new JLabel("\u2191");
		OnTopToggle.setBounds(229, 9, 20, 20);
		OnTopToggle.setForeground(Color.WHITE);
		OnTopToggle.setFont(new Font("宋体", Font.PLAIN, 20));
		OnTopToggle.addMouseListener(this);
		getContentPane().add(OnTopToggle);
		
		ReturnNow = new JLabel("\u2606");
		ReturnNow.setHorizontalAlignment(SwingConstants.CENTER);
		ReturnNow.setBounds(9, 9, 20, 20);
		ReturnNow.setForeground(Color.WHITE);
		ReturnNow.setFont(new Font("宋体", Font.PLAIN, 20));
		ReturnNow.addMouseListener(this);
		getContentPane().add(ReturnNow);
		
		EnableDisplay = new JLabel("----------------------------显示日程-------------------------------");
		EnableDisplay.setFont(new Font("华文楷体", Font.PLAIN, 12));
		EnableDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		EnableDisplay.setBounds(0, 285, 300, 15);
		EnableDisplay.addMouseListener(this);
		getContentPane().add(EnableDisplay);
				
		calendar = new CalendarTable();
		calendar.setBounds(0, 0, 300, 300);
		this.getContentPane().add(calendar);
		
		AWTUtilities.setWindowOpaque(this, true);
    	AWTUtilities.setWindowOpacity(this, 0.8f);
    	   	
    	this.setVisible(true);
    	
    	schedule = new ScheduleTable();
		schedule.setLocation(origin.x, origin.y+300);
		
		Subject.getSub().notifyObservers();
	}
	
	public static Point getPosition()
	{
		return window.getLocation();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		nowDate = calendar.getNowDate();
		schedule.setDate(nowDate);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == EXIT)
		{
			System.exit(0);
		}
		if (e.getSource() == OnTopToggle)
		{
			this.setAlwaysOnTop(!this.isAlwaysOnTop());
			schedule.setAlwaysOnTop(this.isAlwaysOnTop());
			OnTopToggle.setText(this.isAlwaysOnTop()? "↓":"↑");
		}
		if (e.getSource() == ReturnNow)
		{
			calendar.setToday();
		}
		if (e.getSource() == EnableDisplay)
		{
			schedule.setVisible(!schedule.isVisible());
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
		origin.x = e.getX();
		origin.y = e.getY();
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
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		Point p = this.getLocation();
		this.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y); 
		schedule.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y + 300);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
