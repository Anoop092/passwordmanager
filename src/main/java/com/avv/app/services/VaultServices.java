package com.avv.app.services;


import com.avv.app.dto.SessionDTO;
import com.avv.app.dto.Vault;
import com.avv.app.dto.VaultEntry;
import com.avv.app.repositories.VaultRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VaultServices {
	private ObjectMapper objectmapper = new ObjectMapper();
	//add
	public void add(SessionDTO session, VaultEntry entry) throws Exception{
		// add entry
		session.getVault().getEntries().add(entry);
		//convert it into JSON
		String vaultJson = objectmapper.writeValueAsString(session.getVault());
		//convert JSON into encrypted String
		String encryptedString = CryptoServices.encryptVault(vaultJson, session.getSecretKey(), session.getIv());
		// store updated JSON in database
		VaultRepository vr = new VaultRepository();
		// save the vault in database
		vr.updateVault(encryptedString, session.getUserId());			
	}
	//update
	// delete

}
