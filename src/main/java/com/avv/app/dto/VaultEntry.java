package com.avv.app.dto;

public class VaultEntry {
 private String website;
 private String userName;
 private String password;
 
 public VaultEntry(){};
 public VaultEntry(String website, String userName,String password){
	 this.website = website;
	 this.userName = userName;
	 this.password = password;
 }
 public String getWebsite(){
	 return this.website;
 }
 public String getUserName(){
	 return this.userName;
 }
 public String getPassword(){
	 return this.password;
 }
}
