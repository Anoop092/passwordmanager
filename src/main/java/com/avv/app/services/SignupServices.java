package com.avv.app.services;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import com.avv.app.dto.EncryptedVaultDTO;
import com.avv.app.dto.Vault;
import com.avv.app.dto.VaultEntry;
import com.avv.app.repositories.UserRepository;
import com.avv.app.repositories.VaultRepository;

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
   public void save() throws Exception{
	   int userId;
	   UserRepository ur = new UserRepository();
	   generateSalt();
	   userId = ur.saveUser(this.email, Arrays.hashCode(this.masterPassword), this.salt);
	   if(userId == -1){
		   System.out.println("unable to save user in database.");
		   return;
	   }
	   // create new Vault
	   Vault vault = new Vault(new ArrayList<VaultEntry>());
	   // encrypt the vault
	   EncryptedVaultDTO eto = CryptoServices.encrpytVault(this.masterPassword, salt, vault,userId);
	   // save it to database
	   VaultRepository.save(eto);
	   
   }
}
