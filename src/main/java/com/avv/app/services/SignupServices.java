package com.avv.app.services;

import java.security.SecureRandom;
import java.util.Base64;

public class SignupServices {
   private String email;
   private char[] masterPassword;
   private String salt;
   
   public SignupServices(String email, char[] masterPassword){
	   this.email = email;
	   this.masterPassword = masterPassword;
   }
   public void generateSalt(){
	    byte[] salt = new byte[16];
	    SecureRandom random = new SecureRandom();
	    random.nextBytes(salt);
	    this.salt = Base64.getEncoder().encodeToString(salt);
	 }
   public void save(){
	   
   }
}
