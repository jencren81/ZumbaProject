package com.zumbamanagement.model;

public class Batch {
	int id;
	String option;
	String days;
	String time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public Batch (int id, String option, String days, String time) {
		super();
		this.id = id;
		this.option=option;
		this.days=days;
		this.time=time;
	}
	public Batch (String option, String days, String time) {
		super();
		this.option=option;
		this.days=days;
		this.time=time;
	}
	public Batch (String option, String days, String time, int id) {
		super();
		this.option=option;
		this.days=days;
		this.time=time;
		this.id = id;
	}
	public Batch() {
		// TODO Auto-generated constructor stub
	}
	
	

}
