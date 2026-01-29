package com.avv.app.services;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import com.avv.app.repositories.UserRepository;

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
	   UserRepository ur = new UserRepository();
	   generateSalt();
	   ur.saveUser(this.email, Arrays.hashCode(this.masterPassword), this.salt);
   }
}
