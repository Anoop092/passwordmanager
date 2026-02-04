package com.avv.app.services;

import java.util.Optional;

import javax.crypto.SecretKey;

import com.avv.app.dto.EncryptedVaultDTO;
import com.avv.app.dto.RequestDTO;
import com.avv.app.dto.SessionDTO;
import com.avv.app.dto.UserDTO;
import com.avv.app.dto.Vault;
import com.avv.app.repositories.UserRepository;
import com.avv.app.repositories.VaultRepository;
import com.avv.app.utils.UtilServices;

public class AuthServices {
  public static  SessionDTO login(RequestDTO req) throws Exception{
	  UserDTO user = null;
	  String email = req.getEmail();
	  char[] password = req.getPassword();
	  // check whether user exists or not 
	  UserRepository ur = new UserRepository();
	  user = ur.findUser(email);
	  // verify the password
	  if(!comparePassword(UtilServices.hashPassword(req.getPassword()),user.getHashedpassword())){
		  throw new Exception("password is not valid");
	  }
	  // get the vault from vault repository
	  Optional<EncryptedVaultDTO> encryptedVaultDTO = VaultRepository.getVaults(user.getUserId());
	  EncryptedVaultDTO dto = encryptedVaultDTO.get();
	  //generate the key
	  SecretKey secretKey = KeyServices.generateKey(password,user.getSalt());
	  //deCrypt the vault
	  Vault vault = CryptoServices.deCryptVault(dto,user.getSalt(),req.getPassword());
	  SessionDTO sdto = new SessionDTO(vault, user.getUserId(),secretKey,dto.getIv());
	  return sdto;
	}
  public static boolean comparePassword(int enteredPassword, int password){
	  return enteredPassword == password;
  }
}
