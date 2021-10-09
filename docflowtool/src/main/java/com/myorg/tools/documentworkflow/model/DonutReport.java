package com.myorg.tools.documentworkflow.model;

import java.io.Serializable;

public class DonutReport implements Serializable {

	private static final long serialVersionUID = -533357340380192749L;
	
	private Integer value;
	
	private String label;

	/**
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Integer value) {
		this.value = value;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

}
