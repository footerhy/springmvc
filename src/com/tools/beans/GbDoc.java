package com.tools.beans;

public class GbDoc extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	private Long  doc_id;
	private String  doc_name;
	private String  doc_path;
	public Long getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public String getDoc_path() {
		return doc_path;
	}
	public void setDoc_path(String doc_path) {
		this.doc_path = doc_path;
	}
	
}
