package com.test.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.model.Account;
import com.test.model.User;

import Utils.MySQLConnector;

public class AccountDAOImpl implements AccountDAO{
	
	public ResultSet getAccountDetails(int id) throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		ResultSet rs;
		try{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM ACCOUNT WHERE id="+id;
			rs = stmt.executeQuery(sql);
		    System.out.println("account fetched successfully...");
		    stmt.close();
		    conn.close();
		    
		    
		}finally{
		    try{
		       if(stmt!=null)
		          stmt.close();
		    }catch(SQLException se2){
		    }
		    try{
		       if(conn!=null)
		          conn.close();
		    }catch(SQLException se){
		       se.printStackTrace();
		    }
		 }
		return rs;
	}
	
	public boolean deleteAccount(int id)throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			String sql = "DELETE FROM ACCOINT WHERE ID="+id;
			stmt.executeUpdate(sql);
		    System.out.println("account deleted successfully...");
		    stmt.close();
		    conn.close();
		    
		    
		}finally{
		    try{
		       if(stmt!=null)
		          stmt.close();
		    }catch(SQLException se2){
		    }
		    try{
		       if(conn!=null)
		          conn.close();
		    }catch(SQLException se){
		       se.printStackTrace();
		    }
		 }
		return true;
	}
	
	@Override
	public boolean updateAccount(int portfolioId) throws IOException, SQLException {
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		ResultSet rs;
		float c=0;
		try{
			stmt = conn.createStatement();
			String sql1 = "SELECT current_bal from ACCOUNT where portfolio_id="+portfolioId;
			rs = stmt.executeQuery(sql1);
			while(rs.next()){
				c=rs.getFloat(1);
				System.out.println(c);
			}
			String sql = "UPDATE ACCOUNT SET current_bal="+(c+500F)+", "
					+ "margin="+((c+500F)*1.3)+"where portfolio_id="+portfolioId;
			stmt.executeUpdate(sql);
		    System.out.println("user updated successfully...");
		    stmt.close();
		    conn.close();
		    
		    
		}finally{
		    try{
		       if(stmt!=null)
		          stmt.close();
		    }catch(SQLException se2){
		    }
		    try{
		       if(conn!=null)
		          conn.close();
		    }catch(SQLException se){
		       se.printStackTrace();
		    }
		 }
		return true;
	}
	
	public float currentBalance(int account_id) throws IOException, SQLException {
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		ResultSet rs;
		float bal=0F;
		try{
			stmt = conn.createStatement();
			String sql = "SELECT current_bal FROM ACCOUNT WHERE id="+account_id;
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				bal = rs.getFloat(1);
			}
		    System.out.println("account fetched successfully...");
		    stmt.close();
		    conn.close();
		    
		    
		}finally{
		    try{
		       if(stmt!=null)
		          stmt.close();
		    }catch(SQLException se2){
		    }
		    try{
		       if(conn!=null)
		          conn.close();
		    }catch(SQLException se){
		       se.printStackTrace();
		    }
		 }
		return bal;
	}
	
	
	
	@Override
	public int persistAccount(Account a) throws IOException, SQLException {
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		int id =0;
		
		try{
			stmt = conn.createStatement();
			String sql = "INSERT INTO ACCOUNT VALUES(default, "+a.getPortfolioId()+","+
			a.getInvestmentTotal()+","+a.getCurrentBalance()+","+a.getMargin()+")";
			stmt.executeUpdate(sql);
		    System.out.println("account inserted successfully...");
		    String sql1 = "SELECT ID FROM ACCOUNT WHERE portfolio_id="+a.getPortfolioId();
			ResultSet rs = stmt.executeQuery(sql1);
			while(rs.next()){
				id = rs.getInt(1);
			}
		    stmt.close();
		    conn.close();
		    
		    return id;
		}finally{
		    try{
		       if(stmt!=null)
		          stmt.close();
		    }catch(SQLException se2){
		    }
		    try{
		       if(conn!=null)
		          conn.close();
		    }catch(SQLException se){
		       se.printStackTrace();
		    }
		 }
		
	}

	@Override
	public boolean updateAccount(float vcash) throws IOException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	

}
