package com.test.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.model.Portfolio;
import com.test.model.User;

import Utils.MySQLConnector;

public class PortfolioDAOImpl implements PortfolioDAO{
	
	public int persistPortfolio(Portfolio p) throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		int id=0;
		try{
			stmt = conn.createStatement();
			String sql = "INSERT INTO PORTFOLIO VALUES(default,"+p.getUserId()+",'"+p.getName()+"',"+-1+")";
			stmt.executeUpdate(sql);
			System.out.println("port inserted successfully...");
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
	
	public void updatePortfolio(int accid, int id) throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			String sql = "UPDATE PORTFOLIO SET account_id="+accid+" WHERE ID="+id;
			stmt.executeUpdate(sql);
			System.out.println("acc updated successfully...");
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
		
	}
		
	public boolean deletePortfolio(int id)throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			String sql = "DELETE FROM PORTFOLIO WHERE ID="+id+"";
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
	
	public int getAccountIdOfPortfolio(int portfolioid) throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		ResultSet rs;
		int accId =0;
		try{
			stmt = conn.createStatement();
			String sql = "SELECT account_id FROM PORTFOLIO WHERE id="+portfolioid;
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				accId = rs.getInt("account_id");}
		    stmt.close();
		    conn.close();
		    return accId;
		    
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
	
	public int getId(int userId) throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		ResultSet rs;
		int uid =0;
		try{
			stmt = conn.createStatement();
			String sql = "SELECT id FROM PORTFOLIO WHERE user_id="+userId;
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				uid = rs.getInt(1);}
		    stmt.close();
		    conn.close();
		    return uid;
		    
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


}
