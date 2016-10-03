package com.test.model;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.test.dao.AccountDAOImpl;
import com.test.view.AccountDetailUI;

public class Portfolio{
	
	
	private int id;
	private String name;
	private int acc;
	private int userId;
	private ArrayList<String> eq;
	
	public Portfolio(String name){
		
		this.name = name;
	}
	
	public int getAccountId(){
		return acc;
	}
	
	public void setAccountId(int a){
		acc = a;
	}
	
	public void setuserId(int id){
		userId = id;
	}
	
	public int getUserId(){
		return userId;
	}
	
	public void addEquityAsset(String e){
		eq.add(e);
	}
	
	public void deleteEquityAsset(String e){
		eq.remove(e);
	}
	 
	public void getAccountDetails(Account acc) throws IOException, SQLException{
		ResultSet rs = new AccountDAOImpl().getAccountDetails(acc.getId());
		new AccountDetailUI(rs);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public List<Stock> getGainingStocks(){
		return new ArrayList<Stock>();
	}
	
	public List<Stock> getLosingStocks(){
		return new ArrayList<Stock>();
	}
	
		

}
