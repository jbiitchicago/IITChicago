package com.test.model;

import java.util.Date;
import java.util.List;

public class Report implements ReportStructureInterface{
	
	private String id;
	private Date lastUpdated;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	@Override
	public float getLedgerBalance() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getMargin() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getTotalProfits() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getTotalLoss() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float getBalance() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Transaction> getLastTransactions() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
