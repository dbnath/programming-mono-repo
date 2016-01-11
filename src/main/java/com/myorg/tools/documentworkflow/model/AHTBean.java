package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;
import java.util.Date;

public class AHTBean implements Serializable {
	
	private static final long serialVersionUID = 1518388687498835566L;
	
	private Integer agreementId;
	
	private Integer statusCode;
	
	private Integer roleId;
	
	private Date lastUpdationDate;
	
	private String isClockStart;
	
	private String isClockStop;
	
	private Date ageStartTime;
	
	private Double makerHeldTime =0.0;
	
	private Double checkerHeldTime = 0.0;
	
	private Double smeHeldTime = 0.0;
	
	private Double totalHeldTime = 0.0;
	
	private Double age = 0.0;
	

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Date getLastUpdationDate() {
		return lastUpdationDate;
	}

	public void setLastUpdationDate(Date lastUpdationDate) {
		this.lastUpdationDate = lastUpdationDate;
	}

	public String getIsClockStart() {
		return isClockStart;
	}

	public void setIsClockStart(String isClockStart) {
		this.isClockStart = isClockStart;
	}

	public String getIsClockStop() {
		return isClockStop;
	}

	public void setIsClockStop(String isClockStop) {
		this.isClockStop = isClockStop;
	}

	public Double getMakerHeldTime() {
		return makerHeldTime;
	}

	public void setMakerHeldTime(Double makerHeldTime) {
		this.makerHeldTime = makerHeldTime;
	}

	public Double getCheckerHeldTime() {
		return checkerHeldTime;
	}

	public void setCheckerHeldTime(Double checkerHeldTime) {
		this.checkerHeldTime = checkerHeldTime;
	}

	public Double getSmeHeldTime() {
		return smeHeldTime;
	}

	public void setSmeHeldTime(Double smeHeldTime) {
		this.smeHeldTime = smeHeldTime;
	}

	public Double getTotalHeldTime() {
		return totalHeldTime;
	}

	public void setTotalHeldTime(Double totalHeldTime) {
		this.totalHeldTime = totalHeldTime;
	}

	public Double getAge() {
		return age;
	}

	public void setAge(Double age) {
		this.age = age;
	}

	public Date getAgeStartTime() {
		return ageStartTime;
	}

	public void setAgeStartTime(Date ageStartTime) {
		this.ageStartTime = ageStartTime;
	}

}
