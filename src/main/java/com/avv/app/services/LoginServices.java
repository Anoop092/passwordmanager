package com.avv.app.services;

import java.util.Arrays;

import com.avv.app.dto.RequestDTO;
import com.avv.app.dto.UserDTO;
import com.avv.app.repositories.UserRepository;

public class LoginServices {
 private static UserDTO user = null;
 public static boolean isUserExists(RequestDTO req) throws Exception{
	 user = loadUser(req);
	 return user != null? true : false;
	
}
 public static UserDTO loadUser(RequestDTO req) throws Exception{
	 UserRepository ur = new UserRepository();
	 UserDTO udto = ur.findUser(req.getEmail());
	 return udto;
 }
 public static boolean verifyPassword(RequestDTO req){
	 char[] enteredPassword = req.getPassword();
	 int hassedPassword = Arrays.hashCode(enteredPassword);
	 return hassedPassword == user.getHashedpassword();
	 
 }
}
