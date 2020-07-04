package com.example.demo;

public class TripData {
	private Integer id;
	private String t_date;
	private String t_start;
	private String t_end;
	private String t_type;
	private String t_no;
	private String t_memo;
	private String t_no_sub;
	private String t_pos_start;
	private String t_pos_end;
	private String source;
	private String who;
	public TripData(Integer id,String t_date,String t_start,String t_end,String t_type,String t_no,String t_memo,String t_no_sub,String t_pos_start,String t_pos_end,String source,String who) {
		this.id=id;
		this.t_date=t_date;
		this.t_start=t_start;
		this.t_end=t_end;
		this.t_type=t_type;
		this.t_no=t_no;
		this.t_memo=t_memo;
		this.t_no_sub=t_no_sub;
		this.t_pos_start=t_pos_start;
		this.t_pos_end=t_pos_end;
		this.source=source;
		this.who=who;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getT_date() {
		return t_date;
	}
	public void setT_date(String t_date) {
		this.t_date = t_date;
	}
	public String getT_start() {
		return t_start;
	}
	public void setT_start(String t_start) {
		this.t_start = t_start;
	}
	public String getT_end() {
		return t_end;
	}
	public void setT_end(String t_end) {
		this.t_end = t_end;
	}
	public String getT_type() {
		return t_type;
	}
	public void setT_type(String t_type) {
		this.t_type = t_type;
	}
	public String getT_no() {
		return t_no;
	}
	public void setT_no(String t_no) {
		this.t_no = t_no;
	}
	public String getT_memo() {
		return t_memo;
	}
	public void setT_memo(String t_memo) {
		this.t_memo = t_memo;
	}
	public String getT_no_sub() {
		return t_no_sub;
	}
	public void setT_no_sub(String t_no_sub) {
		this.t_no_sub = t_no_sub;
	}
	public String getT_pos_start() {
		return t_pos_start;
	}
	public void setT_pos_start(String t_pos_start) {
		this.t_pos_start = t_pos_start;
	}
	public String getT_pos_end() {
		return t_pos_end;
	}
	public void setT_pos_end(String t_pos_end) {
		this.t_pos_end = t_pos_end;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getWho() {
		return who;
	}
	public void setWho(String who) {
		this.who = who;
	}
	@Override
	public String toString() {
		return "TripData{"+
				"id="+id+
				",t_date='"+t_date+'\''+
				",t_start='"+t_start+'\''+
				",t_end='"+t_end+'\''+
				",t_type='"+t_type+'\''+
				",t_no='"+t_no+'\''+
				",t_memo='"+t_memo+'\''+
				",t_no_sub='"+t_no_sub+'\''+
				",t_pos_start='"+t_pos_start+'\''+
				",t_pos_end='"+t_pos_end+'\''+
				",source='"+source+'\''+
				",who="+who+
				'}';
	}
}
