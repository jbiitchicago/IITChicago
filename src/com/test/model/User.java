package com.test.model;

public class User extends Role{
	
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	private String name;
	private String username;
	private String password;
	private String email;
	private String mobile;
	private String defaultPortfolioName;
	private int portfolioId;
	public String getDefaultPortfolioName() {
		return defaultPortfolioName;
	}

	public void setDefaultPortfolioName(String defaultPortfolioName) {
		this.defaultPortfolioName = defaultPortfolioName;
	}

	public int getPortfolioId() {
		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}
	private Portfolio portfolio; 
	
	public User(){
		super("user");
		portfolio = new Portfolio(defaultPortfolioName);
	}
		
	public void setName(String name) {
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail(){
		return email;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMobile(){
		return mobile;
	}
	
	public void setPortfolioName(String portfolio) {
		this.defaultPortfolioName = portfolio;
	}
	public String getPortfolioName(){
		return defaultPortfolioName;
	}
	

}
