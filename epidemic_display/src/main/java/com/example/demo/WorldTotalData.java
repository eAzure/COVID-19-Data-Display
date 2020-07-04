package com.example.demo;
//这里存放的是世界的总体数据
public class WorldTotalData {
	private Integer id;//1
	private Integer confirmedCount;//2
	private Integer confirmedIncr;//3
	private Integer curedCount;//4
	private Integer curedIncr;//5
	private Integer currentConfirmedCount;//6
	private Integer currentConfirmedIncr;//7
	private Integer dateId;//8
	private Integer deadCount;//9
	private Integer deadIncr;//10
	private Integer suspectedCount;//11
	private Integer suspectedCountIncr;//12
	
	public WorldTotalData(Integer id,Integer confirmedCount,Integer confirmedIncr,Integer curedCount,Integer curedIncr,Integer currentConfirmedCount,Integer currentConfirmedIncr,Integer dateId,Integer deadCount,Integer deadIncr,Integer suspectedCount,Integer suspectedCountIncr) {
		this.id=id;
		this.confirmedCount=confirmedCount;
		this.confirmedIncr=confirmedIncr;
		this.curedCount=curedCount;
		this.curedIncr=curedIncr;
		this.currentConfirmedCount=currentConfirmedCount;
		this.currentConfirmedIncr=currentConfirmedIncr;
		this.dateId=dateId;
		this.deadCount=deadCount;
		this.deadIncr=deadIncr;
		this.suspectedCount=suspectedCount;
		this.suspectedCountIncr=suspectedCountIncr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getConfirmedCount() {
		return confirmedCount;
	}

	public void setConfirmedCount(Integer confirmedCount) {
		this.confirmedCount = confirmedCount;
	}

	public Integer getConfirmedIncr() {
		return confirmedIncr;
	}

	public void setConfirmedIncr(Integer confirmedIncr) {
		this.confirmedIncr = confirmedIncr;
	}

	public Integer getCuredCount() {
		return curedCount;
	}

	public void setCuredCount(Integer curedCount) {
		this.curedCount = curedCount;
	}

	public Integer getCuredIncr() {
		return curedIncr;
	}

	public void setCuredIncr(Integer curedIncr) {
		this.curedIncr = curedIncr;
	}

	public Integer getCurrentConfirmedCount() {
		return currentConfirmedCount;
	}

	public void setCurrentConfirmedCount(Integer currentConfirmedCount) {
		this.currentConfirmedCount = currentConfirmedCount;
	}

	public Integer getCurrentConfirmedIncr() {
		return currentConfirmedIncr;
	}

	public void setCurrentConfirmedIncr(Integer currentConfirmedIncr) {
		this.currentConfirmedIncr = currentConfirmedIncr;
	}

	public Integer getDateId() {
		return dateId;
	}

	public void setDateId(Integer dateId) {
		this.dateId = dateId;
	}

	public Integer getDeadCount() {
		return deadCount;
	}

	public void setDeadCount(Integer deadCount) {
		this.deadCount = deadCount;
	}

	public Integer getDeadIncr() {
		return deadIncr;
	}

	public void setDeadIncr(Integer deadIncr) {
		this.deadIncr = deadIncr;
	}

	public Integer getSuspectedCount() {
		return suspectedCount;
	}

	public void setSuspectedCount(Integer suspectedCount) {
		this.suspectedCount = suspectedCount;
	}

	public Integer getSuspectedCountIncr() {
		return suspectedCountIncr;
	}

	public void setSuspectedCountIncr(Integer suspectedCountIncr) {
		this.suspectedCountIncr = suspectedCountIncr;
	}
	@Override
	public String toString() {
		return "WorldTotalData{"+
				"id="+id+
				",confirmedCount='"+confirmedCount+'\''+
				",confirmedIncr='"+confirmedIncr+'\''+
				",curedCount='"+curedCount+'\''+
				",curedIncr='"+curedIncr+'\''+
				",currentConfirmedCount='"+currentConfirmedCount+'\''+
				",currentConfirmedIncr='"+currentConfirmedIncr+'\''+
				",dateId='"+dateId+'\''+
				",deadCount='"+deadCount+'\''+
				",deadIncr='"+deadIncr+'\''+
				",suspectedCount='"+suspectedCount+'\''+
				",suspectedCountIncr="+suspectedCountIncr+
				'}';
	}
}
