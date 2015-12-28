package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;

public class AgreementStatus implements Serializable, ReverseMappable{
	
	private static final long serialVersionUID = 1518388688598835567L;
	
	private Integer agreementStatusId;
	private String agreementStatusName;
	private String isClockStart;
	private String isClockStop;
	
	public AgreementStatus() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.model.Mappable#getMapKey()
	 */
	@Override
	public String getMapKey() {
		return agreementStatusName;
	}

	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.model.ReverseMappable#getCode()
	 */
	@Override
	public Integer getCode() {
		return agreementStatusId;
	}


	public Integer getAgreementStatusId() {
		return agreementStatusId;
	}


	public void setAgreementStatusId(Integer agreementStatusId) {
		this.agreementStatusId = agreementStatusId;
	}


	public String getAgreementStatusName() {
		return agreementStatusName;
	}


	public void setAgreementStatusName(String agreementStatusName) {
		this.agreementStatusName = agreementStatusName;
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
	
	

}
