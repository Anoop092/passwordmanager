package com.avv.app.services;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class KeyServices {
  private static final int iteration= 60000;
  private static final int keyLength = 256;
  private static final String strechingAlgorithm = "PBKDF2WithHmacSHA256";
  public static SecretKey generateKey(char[] password, String salt) throws Exception {
	  byte[] derivedSalt = Base64.getDecoder().decode(salt);
	  PBEKeySpec pbKey = new PBEKeySpec(password,derivedSalt,iteration,keyLength);
	  SecretKeyFactory sec = SecretKeyFactory.getInstance(strechingAlgorithm);
	  byte[] temp = sec.generateSecret(pbKey).getEncoded();
	  SecretKey secretKey = new SecretKeySpec(temp, "AES");
	  return secretKey;
	}
}
