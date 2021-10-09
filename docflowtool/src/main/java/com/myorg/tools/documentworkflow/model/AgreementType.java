package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;

public class AgreementType implements Serializable, ReverseMappable{
	
	private static final long serialVersionUID = 1518388688598835567L;
	
	private Integer agreementTypeId;
	private String agrementTypeName;
	
	public Integer getAgreementTypeId() {
		return agreementTypeId;
	}
	public void setAgreementTypeId(Integer agreementTypeId) {
		this.agreementTypeId = agreementTypeId;
	}
	public String getAgrementTypeName() {
		return agrementTypeName;
	}
	public void setAgrementTypeName(String agrementTypeName) {
		this.agrementTypeName = agrementTypeName;
	}
	public AgreementType() {
		super();
	}
	public AgreementType(Integer agreementTypeId, String agrementTypeName) {
		this.agreementTypeId = agreementTypeId;
		this.agrementTypeName = agrementTypeName;
	}
	
	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.model.Mappable#getMapKey()
	 */
	@Override
	public String getMapKey() {
		return agrementTypeName;
	}

	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.model.ReverseMappable#getCode()
	 */
	@Override
	public Integer getCode() {
		return agreementTypeId;
	}
	
	

}
