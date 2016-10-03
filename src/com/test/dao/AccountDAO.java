package com.test.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.test.model.Account;
import com.test.model.User;

public interface AccountDAO {
	int persistAccount(Account a) throws IOException, SQLException;
	ResultSet getAccountDetails(int accountId) throws IOException, SQLException;
	boolean updateAccount(float vcash) throws IOException, SQLException;
	boolean deleteAccount(int id) throws IOException, SQLException;
	boolean updateAccount(int portfolioId) throws IOException, SQLException;

}
