package com.test.model;

public class EquityAsset {
	
	private String symbol;
	private int units;
	private float purchasePrice;
	private float investmentAmt;
	private float currentValue;
	private int portfolioId;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
		this.units = units;
	}
	public float getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public float getInvestmentAmt() {
		return investmentAmt;
	}
	public void setInvestmentAmt(float investmentAmt) {
		this.investmentAmt = investmentAmt;
	}
	public float getCurrentValue() {
		return currentValue;
	}
	public void setCurrentValue(float currentValue) {
		this.currentValue = currentValue;
	}
	public int getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}
		

}
