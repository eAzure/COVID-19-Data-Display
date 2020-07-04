package com.example.demo;

public class ChinaData {
	private Integer id;
	private Integer confirmedCount;
	private Integer confirmedIncr;
	private Integer curedCount;
	private Integer curedIncr;
	private Integer currentConfirmedCount;
	private Integer currentConfirmedIncr;
	private Integer dateId;
	private Integer deadCount;
	private Integer deadIncr;
	private Integer suspectedCount;
	private Integer suspectedCountIncr;
	private String provinceName;
	private String provinceShortName;
	
	public ChinaData(Integer id,Integer confirmedCount,Integer confirmedIncr,Integer curedCount,Integer curedIncr,Integer currentConfirmedCount,Integer currentConfirmedIncr,Integer dateId,Integer deadCount,Integer deadIncr,Integer suspectedCount,Integer suspectedCountIncr,String provinceName,String provinceShortName){
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
		this.provinceName=provinceName;
		this.provinceShortName=provinceShortName;
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

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceShortName() {
		return provinceShortName;
	}

	public void setProvinceShortName(String provinceShortName) {
		this.provinceShortName = provinceShortName;
	}
	
	@Override
	public String toString() {
		return "ChinaData{"+
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
				",suspectedCountIncr='"+suspectedCountIncr+'\''+
				",provinceName='"+provinceName+'\''+
				",provinceShortName="+provinceShortName+
				'}';
	}
}
