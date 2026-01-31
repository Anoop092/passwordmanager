package com.avv.app.dto;

import java.util.Base64;

public class EncryptedVaultDTO {
    private byte[] iv;
    private String encryptedVault;
    private int userId;
    
    public EncryptedVaultDTO(byte[] iv,String encryptedVault,int userId){
    	this.iv = iv;
    	this.encryptedVault = encryptedVault;
    	this.userId = userId;
    }
    public byte[] getIv(){
    	return this.iv;
    }
    public String getEncryptedVault(){
    	return this.encryptedVault;
    }
    public int getUserId(){
    	return this.userId;
    }
    public String getIvInString(){
    	return Base64.getEncoder().encodeToString(this.iv);
    }
}
