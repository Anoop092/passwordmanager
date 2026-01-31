package com.avv.app.dto;

import java.util.Base64;

public class UserDTO {
	public int userId;
	public String email;
	public int hashedPassword;
	public String salt;
	
	public UserDTO(int userID,String email, int hashedPassword, String salt){
		this.email = email;
		this.hashedPassword = hashedPassword;
		this.salt = salt;
		this.userId = userID;
		
	}
	public String getEmail() {return this.email;}
	public String getSalt(){ return this.salt;}
	public int getHashedpassword(){return this.hashedPassword;}
	public int getUserId(){return this.userId;}
	
	public byte[] getSaltINBytes(){
		byte[] salt;
		salt = Base64.getDecoder().decode(this.salt);
		return salt;
	}
	
	

}
