package com.test.model;

import java.util.List;

public interface ReportStructureInterface {
	
	float getLedgerBalance();
	float getMargin();
	float getTotalProfits();
	float getTotalLoss();
	float getBalance();
	List<Transaction> getLastTransactions();

}
