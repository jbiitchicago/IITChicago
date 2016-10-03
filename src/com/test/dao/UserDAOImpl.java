package com.test.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.model.User;

import Utils.MySQLConnector;

public class UserDAOImpl implements UserDAO{
	
	
	public boolean persistUser(User u) throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			String sql = "INSERT INTO USER VALUES(default, '"+u.getName()+"','"+u.getUsername()+"','"+u.getPassword()+"','"
			+u.getEmail()+"','"+u.getMobile()+"','"+u.getPortfolioName()+"',"+u.getPortfolioId()+")";
			stmt.executeUpdate(sql);
		    System.out.println("user inserted successfully...");
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
	
	public int getUser(String username, String password) throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		int id=0;
		ResultSet rs;
		try{
			stmt = conn.createStatement();
			String sql = "SELECT id FROM USER WHERE username LIKE '"+username+"' and password LIKE '"+password+"'";
			rs = stmt.executeQuery(sql);
			while(rs.next())
				id=rs.getInt(1);
		    System.out.println("user fetched successfully...");
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
	
	public int getPortfolioId(int userId) throws IOException, SQLException {
		System.out.println(userId);
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		int id;
		ResultSet rs;
		try{
			stmt = conn.createStatement();
			String sql = "SELECT portfolio_id FROM USER WHERE id="+userId;
			rs = stmt.executeQuery(sql);
		    //System.out.println("_------------"+rs.toString()+rs.getInt("portfolio_id"));
		    while(rs.next()){
		    return rs.getInt("portfolio_id");
		    }
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
		return 0;
		
	}
	
	public boolean deleteUser(int id)throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			String sql = "DELETE FROM USER WHERE ID="+id+"";
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
	
	public boolean updateUserInfo(ArrayList<String> fieldnames, int id) throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			String sql = "UPDATE USER SET name='"+fieldnames.get(0)+"', username='"+fieldnames.get(1)+
					"', password='"+fieldnames.get(2)+"', email='"+fieldnames.get(3)+
					"', mobile='"+fieldnames.get(4)+"' WHERE ID="+id+")";
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
	public boolean updatePortfolioId( int portfolioid,int id) throws IOException, SQLException{
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		try{
			stmt = conn.createStatement();
			String sql = "UPDATE USER SET portfolio_id="+portfolioid+" WHERE ID="+id;
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
	
	@Override
	public ResultSet getAllUsers() throws IOException, SQLException {
		Connection conn = MySQLConnector.connectToDB();
		Statement stmt = null;
		ResultSet rs;
		try{
			stmt = conn.createStatement();
			String sql = "SELECT * FROM USER";
			rs = stmt.executeQuery(sql);
		    System.out.println("user fetched successfully...");
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
	
}

	 
	
