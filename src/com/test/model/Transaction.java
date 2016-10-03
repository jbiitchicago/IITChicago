package com.test.model;

import java.util.Date;

public class Transaction {
	
	public enum TransactionType {
		Buy, Sell
	}
	
	private int id;
	private Date time;
	private TransactionType type;
	private int accountId;
	
	public void setAccountId(int acc){
		accountId = acc;
	}
	
	public int getAccountId(){
		return accountId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public TransactionType getType() {
		return type;
	}
	public void setType(TransactionType type) {
		this.type = type;
	}
	

}
