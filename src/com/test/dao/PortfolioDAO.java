package com.test.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.model.Portfolio;
import com.test.model.User;

public interface PortfolioDAO {
	int persistPortfolio(Portfolio u) throws IOException, SQLException;
	boolean deletePortfolio(int id) throws IOException, SQLException;
	int getAccountIdOfPortfolio(int portfolioid) throws IOException, SQLException;
	
}
