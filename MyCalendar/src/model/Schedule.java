package model;

import java.sql.Time;
import java.util.Date;

/**
 * Schedule entity. @author MyEclipse Persistence Tools
 */

public class Schedule implements java.io.Serializable {

	// Fields

	private Integer schId;
	private Date schDate;
	private Time schTime;
	private String schContent;

	// Constructors

	/** default constructor */
	public Schedule() {
	}

	/** minimal constructor */
	public Schedule(Integer schId, Date schDate) {
		this.schId = schId;
		this.schDate = schDate;
	}

	/** full constructor */
	public Schedule(Integer schId, Date schDate, Time schTime, String schContent) {
		this.schId = schId;
		this.schDate = schDate;
		this.schTime = schTime;
		this.schContent = schContent;
	}

	// Property accessors

	public Integer getSchId() {
		return this.schId;
	}

	public void setSchId(Integer schId) {
		this.schId = schId;
	}

	public Date getSchDate() {
		return this.schDate;
	}

	public void setSchDate(Date schDate) {
		this.schDate = schDate;
	}

	public Time getSchTime() {
		return this.schTime;
	}

	public void setSchTime(Time schTime) {
		this.schTime = schTime;
	}

	public String getSchContent() {
		return this.schContent;
	}

	public void setSchContent(String schContent) {
		this.schContent = schContent;
	}

}