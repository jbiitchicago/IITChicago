package com.test.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.test.model.Account;
import com.test.model.EquityAsset;
import com.test.model.Transaction;
import com.test.model.User;

public interface TransactionDAO {
	boolean persistTransaction(Transaction t) throws IOException, SQLException;
	ResultSet getAllTransactions(int accountId) throws IOException, SQLException;
	boolean deleteTransactions(int accountId)throws IOException, SQLException;

}
