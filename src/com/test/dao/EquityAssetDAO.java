package com.test.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.test.model.Account;
import com.test.model.EquityAsset;
import com.test.model.User;

public interface EquityAssetDAO {
	boolean buyAsset(EquityAsset e) throws IOException, SQLException;
	ResultSet getAllAssetDetails(int portfolioId) throws IOException, SQLException;
	boolean sellAsset(String symbol, int num, int portfolioId) throws IOException, SQLException;
	ArrayList<String> getAssetNames(int portfolioId) throws IOException, SQLException;
}
