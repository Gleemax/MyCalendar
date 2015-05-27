package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import logic.Observer;
import logic.Subject;

import com.sun.awt.AWTUtilities;

import controller.ScheduleController;
import javax.swing.JLabel;
import java.awt.Font;

public class ScheduleTable extends JFrame implements ActionListener, MouseListener, Observer{
	
	ScheduleController Controller = new ScheduleController();
	
	private JLabel addLabel;
	private JLabel removeLabel;
	
	private Date nowDate;
	private JTable table;
	private JScrollPane scrollPane;
	
	Vector<String> columns = new Vector<String>();
	Vector<Vector<String>> values = new Vector<Vector<String>>();

	public ScheduleTable() {
		
		this.setUndecorated(true);
	    this.setBackground(new Color(0, 0, 0));
	    this.setSize(300, 300);
	    this.addMouseListener(this);
			
		AWTUtilities.setWindowOpaque(this, true);
	    AWTUtilities.setWindowOpacity(this, 0.8f);
	    
	    getContentPane().setLayout(null);
	    getContentPane().setBackground(new Color(240, 240, 240));
	    
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(15, 15, 270, 255);
	    getContentPane().add(scrollPane);
	    
	    addLabel = new JLabel("+");
	    addLabel.addMouseListener(this);
	    addLabel.setFont(new Font("Verdana", Font.BOLD, 20));
	    addLabel.setBounds(270, 275, 20, 20);
	    getContentPane().add(addLabel);
	    
	    removeLabel = new JLabel("-");
	    removeLabel.addMouseListener(this);
	    removeLabel.setFont(new Font("Verdana", Font.BOLD, 25));
	    removeLabel.setBounds(15, 275, 20, 20);
	    getContentPane().add(removeLabel);
	    
	    columns.add("时间");
	    columns.add("日程");
	    
	    Subject.getSub().attach(this);
	}

	public void setDate(Date nowDate) {
		this.nowDate = nowDate;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		values = Controller.getSchedule(nowDate);
		table = new JTable(new DefaultTableModel(values, columns));
		
		table.setShowGrid(false);
		table.setRowHeight(20);
		table.setBounds(15, 15, 270, 270);
		table.setBackground(new Color(240, 240, 240));
		
		table.getColumnModel().getColumn(0).setMinWidth(60);
		table.getColumnModel().getColumn(0).setMaxWidth(60);
		
		scrollPane.setViewportView(table);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == addLabel)
		{
			new AddScheWindow(nowDate); 
		}
		if (e.getSource() == removeLabel)
		{
			Controller.removeSchedule(table.getSelectedRow());
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
