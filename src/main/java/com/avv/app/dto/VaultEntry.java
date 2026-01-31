package com.avv.app.dto;

public class VaultEntry {
 public String website;
 public String userName;
 public String password;
 
 public VaultEntry(){};
 public VaultEntry(String website, String userName,String password){
	 this.website = website;
	 this.userName = userName;
	 this.password = password;
 }
}
