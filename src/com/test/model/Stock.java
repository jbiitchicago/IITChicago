package com.test.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Stock {
	
	HashMap<String,Float> stockvalues;
	public ArrayList<String> getStocks() {
		return stocks;
	}

	public void setStocks(ArrayList<String> stocks) {
		this.stocks = stocks;
	}

	HashMap<String, String> stocknames;
	ArrayList<String> stocks;
	
	public Stock(){
		stocks = new ArrayList<String>();
		stocks.add("INFY");stocks.add("BIOCON");stocks.add("TATAMOTORS");
		stocks.add("VEDL");stocks.add("ICICIBANK");
		stocknames = new HashMap<String, String>();
		stocknames.put("INFY","Infosys Technologies Ltd.");
		stocknames.put("BIOCON", "Biocon Ltd.");
		stocknames.put("TATAMOTORS", "Tata Motors Limited");
		stocknames.put("VEDL", "Vedanta");
		stocknames.put("ICICIBANK", "ICICI Bank Ltd");
		stockvalues = new HashMap<String,Float>();
		stockvalues.put("INFY", 1255.90F);//testing
				
	}
	
	private void setPrices(){
		for(String stck: stocks){
			stockvalues.put(stck,getPriceForStock(stck));
		}
	}

	public static Float getPriceForStock(String stck) {
		
		return new Stock().stockvalues.get(stck);
	}
}
