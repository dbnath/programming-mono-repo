package com.myorg.tools.documentworkflow.model;

import java.util.List;
import java.util.Map;

public class AHTWrapper {
	
	private List<DocWkflwProcess> docRepoList ;
	
	private Map<String, AHTBean> ahtMap;

	public List<DocWkflwProcess> getDocRepoList() {
		return docRepoList;
	}

	public void setDocRepoList(List<DocWkflwProcess> docRepoList) {
		this.docRepoList = docRepoList;
	}

	public Map<String, AHTBean> getAhtMap() {
		return ahtMap;
	}

	public void setAhtMap(Map<String, AHTBean> ahtMap) {
		this.ahtMap = ahtMap;
	}
	
}
