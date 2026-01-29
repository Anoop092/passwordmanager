package com.avv.app.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository {
   private String url = "jdbc:sqlite:C:\\sqllite\\passwordmanager";
   public int saveUser(String email,int hash, String salt){
	   Connection con;
	   int userId = -1;
	   String sql = "INSERT INTO users(email,password,salt)"
	   		+ "VALUES(?,?,?)";
	  //load driver
//	   try{
//		   Class.forName("org.sql.JDBC"); 
//	   }catch (ClassNotFoundException cnf) {
//	     System.err.println(cnf.getMessage());
//	}
	  // connect to database
	   try{
		   con = DriverManager.getConnection(url);
		   PreparedStatement ps = con.prepareStatement(sql);
		// prepare the statement 
		   ps.setString(1, email);
		   ps.setInt(2, hash);
		   ps.setString(3, salt);
		   // execute the statement
		  ps.executeUpdate();
		  // get user id
		  userId = getUserId(con,email);

	   }catch(Exception ex){
		   System.err.println(ex.getMessage());
	   }
	   
	   return userId;
  }
   public int getUserId(Connection con, String email) throws SQLException{
	   String query = "SELECT * FROM users;";
	   Statement st = con.createStatement();
	   ResultSet rs = st.executeQuery(query);
	   if(rs.next()){
		   return rs.getInt(1);
	   }
	   return -1;
   }
  
}
