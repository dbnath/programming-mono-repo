package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;

public class AgreementErrorType implements Serializable, ReverseMappable{
	
	private static final long serialVersionUID = 1518388688598835567L;
	
	private Integer errorTypeId;
	private String errorTypeName;
	//private String errorTypeCode;
	
	
	
	public AgreementErrorType() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.model.Mappable#getMapKey()
	 */
	@Override
	public String getMapKey() {
		return errorTypeName;
	}

	/* (non-Javadoc)
	 * @see com.myorg.tools.documentworkflow.model.ReverseMappable#getCode()
	 */
	@Override
	public Integer getCode() {
		return errorTypeId;
	}

	public Integer getErrorTypeId() {
		return errorTypeId;
	}

	public void setErrorTypeId(Integer errorTypeId) {
		this.errorTypeId = errorTypeId;
	}

	public String getErrorTypeName() {
		return errorTypeName;
	}

	public void setErrorTypeName(String errorTypeName) {
		this.errorTypeName = errorTypeName;
	}
}
