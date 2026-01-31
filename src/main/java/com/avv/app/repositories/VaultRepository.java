package com.avv.app.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.avv.app.dto.EncryptedVaultDTO;

public class VaultRepository {
   private static String url = "jdbc:sqlite:C:\\sqllite\\passwordmanager";
   private static Connection setupConnection() throws Exception{
	   Connection con;
	   con = DriverManager.getConnection(url);
	   return con;
   }
   public static void save(EncryptedVaultDTO dto){
	   try{
	   // set up connection with database
	      Connection con = setupConnection();
	   // prepare statement to execute  query
	      String query = "INSERT INTO vaults(userId,vault,iv) VALUES(?,?,?)";
	      PreparedStatement ps = con.prepareStatement(query);
	      ps.setInt(1,dto.getUserId());
	      ps.setString(2,dto.getEncryptedVault());
	      ps.setString(3,dto.getIvInString());
	   // execute the statement
	      ps.executeUpdate();
	      
	      con.close();
	      
	   }catch(Exception e){
		   System.err.println(e.getClass().getName() + e.getMessage());
	   }
	   
	   
   }
}
