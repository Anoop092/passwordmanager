package com.avv.app.dto;

public class RequestDTO {
 private String email;
 private char[] password;
 
 public RequestDTO(String email,char[] password){
	 this.email=  email;
	 this.password = password;
	 }
    public String getEmail(){
    	return this.email;
    }
    public char[] getPassword(){
    	return this.password;
    }
}
