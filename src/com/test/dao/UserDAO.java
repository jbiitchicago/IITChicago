package com.test.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.test.model.User;

public interface UserDAO {
	boolean persistUser(User u) throws IOException, SQLException;
	//int getUser(String username, String password) throws IOException, SQLException;
	int getPortfolioId(int userId) throws IOException, SQLException;//Method overloading
	public boolean updateUserInfo(ArrayList<String> fieldnames, int id) throws IOException, SQLException;
	boolean deleteUser(int id) throws IOException, SQLException;
	ResultSet getAllUsers() throws IOException, SQLException;

}
