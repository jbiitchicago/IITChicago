package com.test.model;

import java.io.PrintWriter;
import java.sql.*;

import com.test.view.LoginUI;

public class DBBootstrap {
 // JDBC driver name and database URL
 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
 static final String DB_URL = "jdbc:mysql://localhost/";

 //  Database credentials
 public static String USER;
 public static String PASS;
 
 public static void main(String[] args) {
 Connection conn = null;
 Statement stmt = null;
 try{
	 
	 PrintWriter writer = new PrintWriter("db.txt", "UTF-8");
	 writer.println(USER);
	 writer.println(PASS);
	 writer.close();
  
    Class.forName("com.mysql.jdbc.Driver");

    //STEP 3: Open a connection
    System.out.println("Connecting to database...");
    conn = DriverManager.getConnection(DB_URL,USER,PASS);
    
    System.out.println("Creating database...");
    stmt = conn.createStatement();
    
    String sql = "CREATE DATABASE FINAL_PROJECT";
    stmt.executeUpdate(sql);
    System.out.println("Database created successfully...");
 
    String sql1 = "USE FINAL_PROJECT";
    stmt.executeUpdate(sql1);
    System.out.println("Database changed successfully...");
    
    String sql2 = "CREATE TABLE USER (id int(11) not null auto_increment, name varchar(255),"
    		+ " username varchar(255),"
    		+ "password varchar(8), email varchar(20), mobile varchar(15), portfolioname varchar(255), "
    		+ "portfolio_id int(11), primary key (id))  ";
    stmt.executeUpdate(sql2);
    System.out.println("USER table created successfully...");
    
    String sql3 = "CREATE TABLE PORTFOLIO (id int(11) not null auto_increment, user_id int(11) not null,"
    		+ "name varchar(255),"
    		+ " account_id int(11), primary key (id))";
    stmt.executeUpdate(sql3);
    System.out.println("PORTFOLIO table created successfully...");
    
    String sql4 = "CREATE TABLE ACCOUNT (id int(11) not null auto_increment, portfolio_id int(11),"
    		+ " investment float(8,4),"
    		+ "current_bal float(8,4), margin float(8,4),"
    		+ " primary key (id))";
    stmt.executeUpdate(sql4);
    
    String sql5 = "CREATE TABLE RM (name varchar(255), user_id varchar(255), password varchar(255))";
    stmt.executeUpdate(sql5);
    System.out.println("RM table created successfully...");
    
    String sql6 = "CREATE TABLE EQUITY_ASSET (id int(11) NOT NULL AUTO_INCREMENT, "
    		+ "symbol varchar(255), units int(11), purchase_price float(8,4), "
    		+ "investment_amount float(8,4), current_value float(8,4), portfolio_id int(11) NOT NULL,"
    		+ "primary key (id))";
    stmt.executeUpdate(sql6);
    
    System.out.println("EQUITY_ASSET table created successfully...");
    
    String sql7 = "CREATE TABLE TRANSACTION (id int(11) NOT NULL AUTO_INCREMENT,timestamp DATE, "
    		+ "acc_id int(11), type varchar(1), asset_symbol varchar(255), price float(8,4),"
    		+ "primary key (id))";
    stmt.executeUpdate(sql7);
    System.out.println("TRANSACTION table created successfully...");
   
   // rs.close();
    stmt.close();
    conn.close();
    new LoginUI();
 }catch(SQLException se){
    
    se.printStackTrace();
 }catch(Exception e){
    
    e.printStackTrace();
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