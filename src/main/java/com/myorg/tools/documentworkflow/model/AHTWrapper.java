package com.myorg.tools.documentworkflow.model;

import java.util.List;
import java.util.Map;

public class AHTWrapper {
	
	private List<DocWkflwProcess> docRepoList ;
	
	private Map<Integer, AHTBean> ahtMap;

	public List<DocWkflwProcess> getDocRepoList() {
		return docRepoList;
	}

	public void setDocRepoList(List<DocWkflwProcess> docRepoList) {
		this.docRepoList = docRepoList;
	}

	public Map<Integer, AHTBean> getAhtMap() {
		return ahtMap;
	}

	public void setAhtMap(Map<Integer, AHTBean> ahtMap) {
		this.ahtMap = ahtMap;
	}
	
}
