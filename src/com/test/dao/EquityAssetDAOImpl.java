package com.test.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.model.Account;
import com.test.model.EquityAsset;
import com.test.model.User;

import Utils.MySQLConnector;

public class EquityAssetDAOImpl implements EquityAssetDAO {

	@Override
	public boolean buyAsset(EquityAsset e) throws IOException, SQLException {
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO EQUITY_ASSET VALUES(default, '" + e.getSymbol() + "'," + e.getUnits() + ","
					+ e.getPurchasePrice() + "," + e.getInvestmentAmt() + "," + e.getCurrentValue() + ","
					+ e.getPortfolioId() + ")";
			stmt.executeUpdate(sql);
			System.out.println("asset inserted successfully...");
			stmt.close();
			conn.close();

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public ResultSet getAllAssetDetails(int portfolioId) throws IOException, SQLException {
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		ResultSet rs;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM EQUITY_ASSET WHERE portfolio_id=" + portfolioId;
			rs = stmt.executeQuery(sql);
			System.out.println("account fetched successfully...");
			stmt.close();
			conn.close();

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return rs;
	}

	@Override
	public boolean sellAsset(String symbol, int num, int portfolioId) throws IOException, SQLException {
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "UPDATE EQUITY_ASSET SET UNITS=" + num + " WHERE SYMBOL LIKE '" + symbol + "' "
					+ "AND PORTFOLIO_ID=" + portfolioId;
			stmt.executeUpdate(sql);
			System.out.println("asset deleted successfully...");
			stmt.close();
			conn.close();

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public ArrayList<String> getAssetNames(int portfolioId) throws IOException, SQLException {
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		ResultSet rs;
		ArrayList<String> ass = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			String sql = "SELECT symbol FROM EQUITY_ASSET WHERE portfolio_id=" + portfolioId;
			rs = stmt.executeQuery(sql);
			while (rs.next())
				ass.add(rs.getString(1));

			System.out.println("account fetched successfully...");
			stmt.close();
			conn.close();
			return ass;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public int getBalanceUnits(int portfolioId, String assetName) throws IOException, SQLException {
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		ResultSet rs;
		int ass = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT units FROM EQUITY_ASSET WHERE portfolio_id=" + portfolioId + " " + "and symbol like '"
					+ assetName + "'";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ass += rs.getInt(1);
			}

			System.out.println("account fetched successfully...");
			stmt.close();
			conn.close();
			return ass;

		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

}
