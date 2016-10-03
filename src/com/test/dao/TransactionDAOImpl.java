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
import com.test.model.Transaction;
import com.test.model.User;

import Utils.MySQLConnector;

public class TransactionDAOImpl implements TransactionDAO{
	
	
	@Override
	public boolean persistTransaction(Transaction t) throws IOException, SQLException {
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			String sql = "INSERT INTO TRANSACTION VALUES(default, '"+t.getTime()+"','"+
			t.getType()+"','"+t.getAccountId()+")";
			stmt.executeUpdate(sql);
		    System.out.println("transaction inserted successfully...");
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
	public ResultSet getAllTransactions(int accountId) throws IOException, SQLException {
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		ResultSet rs;
		try{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM TRANSACTION WHERE acc_id="+accountId;
			rs = stmt.executeQuery(sql);
		    System.out.println("Transaction fetched successfully...");
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
	
	@Override
	public boolean deleteTransactions(int accountId)throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			String sql = "DELETE FROM TRANSACTION WHERE account_id="+accountId;
			stmt.executeUpdate(sql);
		    System.out.println("user deleted successfully...");
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
	

}
