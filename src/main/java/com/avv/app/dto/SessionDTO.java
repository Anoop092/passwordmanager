package com.avv.app.dto;

import javax.crypto.SecretKey;

public class SessionDTO {
	private int userId;
	private Vault vault;
	private SecretKey sk;
	private byte[] iv;
	
	public SessionDTO(Vault vault, int userId,SecretKey secretKey,byte[] iv){
		this.vault = vault;
		this.userId = userId;
		this.sk = secretKey;
		this.iv = iv;
		
	}
	public Vault getVault(){
		return this.vault;
	}
	public int getUserId(){
		return this.userId;
	}
	public SecretKey getSecretKey(){
		return this.sk;
	}
	public byte[]  getIv(){
		return this.iv;
	}

}
