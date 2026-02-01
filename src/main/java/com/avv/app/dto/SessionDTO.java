package com.avv.app.dto;

public class SessionDTO {
	private int userId;
	private Vault vault;
	
	public SessionDTO(Vault vault, int userId){
		this.vault = vault;
		this.userId = userId;
		
	}
	public Vault getVault(){
		return this.vault;
	}
	public int getUserId(){
		return this.userId;
	}

}
