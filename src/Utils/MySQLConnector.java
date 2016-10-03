package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class MySQLConnector {
	
	// JDBC driver name and database URL
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	 static final String DB_URL = "jdbc:mysql://localhost/FINAL_PROJECT";

	 //  Database credentials
	 static String USER;
	 static String PASS;
	 
	 public static Connection connectToDB() throws IOException{
	 Connection conn = null;
	 try{
		 BufferedReader br = new BufferedReader(new FileReader("db.txt"));
		 try {
		     ArrayList<String> sb = new ArrayList<String>();
		     String line = br.readLine();

		     while (line != null) {
		         sb.add(line);
		         line = br.readLine();
		     }
		     USER=sb.get(0); PASS=sb.get(1);
		 } finally {
		     br.close();
		 }
		 
		 Class.forName("com.mysql.jdbc.Driver");

		    //STEP 3: Open a connection
		    System.out.println("Connecting to database...");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		  		 
	 }catch(SQLException se){
		    //Handle errors for JDBC
		    se.printStackTrace();
		 }catch(Exception e){
		    //Handle errors for Class.forName
		    e.printStackTrace();
		 }
		 System.out.println("Goodbye!");
		return conn;
		}//end main
	}//end FirstExample


