package com.test.model;

public class Account {
	
	private int id;
	private int portfolioId;
	private float investmentTotal;
	private float currentBalance;
	private float margin;	
	
	private float virtualCash = 5000F;
	
	public Account(){
		setInvestmentTotal(virtualCash);
		setMargin((float) (virtualCash*1.30));
		setCurrentBalance(virtualCash);
	}
	public float getVirtualCashAmont(){
		return virtualCash;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}

	public float getInvestmentTotal() {
		return investmentTotal;
	}

	public void setInvestmentTotal(float investmentTotal) {
		this.investmentTotal = investmentTotal;
	}

	public float getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(float currentBalance) {
		this.currentBalance = currentBalance;
	}

	public float getMargin() {
		return margin;
	}

	public void setMargin(float margin) {
		this.margin = margin;
	}

	
	public float getTotalPL(){
		return investmentTotal - currentBalance;
	}
	
	


}
