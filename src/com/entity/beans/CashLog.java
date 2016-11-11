package com.entity.beans;

import com.tools.beans.BaseModel;

public class CashLog extends BaseModel{
	
	private static final long serialVersionUID = 1L;
	
	private String cashno;
	private String payname;
	public String getCashno() {
		return cashno;
	}
	public void setCashno(String cashno) {
		this.cashno = cashno;
	}
	public String getPayname() {
		return payname;
	}
	public void setPayname(String payname) {
		this.payname = payname;
	}
	
	
}
