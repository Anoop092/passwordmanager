package com.avv.app.services;

import java.nio.charset.StandardCharsets;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.avv.app.dto.EncryptedVaultDTO;
import com.avv.app.dto.Vault;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CryptoServices {
	private static final ObjectMapper objectMapper = new ObjectMapper();
   private static String vaultTOJson(Vault vault) throws Exception{
		   String json = objectMapper.writeValueAsString(vault);
		   return json;
	
	   
   }
   public static EncryptedVaultDTO encrpytVault(char[] password, String salt,Vault vault,int userId) throws Exception{
	   // generate the secret key
	   SecretKey sk = KeyServices.generateKey(password, salt);
	   // initialize iv;
	   byte[] iv = new byte[16];
	   SecureRandom random = new SecureRandom();
	   random.nextBytes(iv);
	   IvParameterSpec ivSpec = new IvParameterSpec(iv);
	   // convert vault into json format
	   String json = vaultTOJson(vault);
	   // cipher encrypt the data
	   String encryptedMessage = encyptByCipher(sk, json, ivSpec);
	   EncryptedVaultDTO eto = new EncryptedVaultDTO(iv, encryptedMessage,userId);
	   return eto;
	   
	}
    private static String encyptByCipher(SecretKey sk, String json,IvParameterSpec iv) throws Exception{
    	Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    	cipher.init(Cipher.ENCRYPT_MODE, sk, iv);
    	byte[] encrypted = cipher.doFinal(json.getBytes(StandardCharsets.UTF_8));
    	String encryptedMessage = Base64.getEncoder().encodeToString(encrypted);
    	return encryptedMessage;
    	
    	
    }
}
