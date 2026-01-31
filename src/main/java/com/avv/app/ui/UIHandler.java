package com.avv.app.ui;

import java.util.Scanner;

import com.avv.app.dto.RequestDTO;
import com.avv.app.dto.UserDTO;
import com.avv.app.services.LoginServices;
import com.avv.app.services.SignupServices;
import com.avv.app.utils.UtilServices;

public class UIHandler {
 private Scanner sc;
 public UIHandler(Scanner sc){
	 this.sc = sc;
  }
 public void signup(){
	 String email;
	 char[] masterPassword;
	 while(true){
		 System.out.println("please enter email address");
		 email = sc.nextLine().trim();
		 if(!UtilServices.verifyEmail(email)){
			 System.out.println("Please Enter valid email address: ");
			 continue;
		 }
		 System.out.println("please enter password");
		 masterPassword = sc.nextLine().trim().toCharArray();
		 if(!UtilServices.isPasswordValid(masterPassword)){
			 System.out.println("Please Enter valid password: ");
			 continue;
		 }
		 break;
	 }
	 SignupServices ss = new SignupServices(email, masterPassword);
	 try{
	 ss.save();
	 }catch(Exception e){
		 System.out.print(e.getMessage());
		 return;
	 }
	 System.out.println("Successfully saved");
}

public void login(){
	String email;
	char[] password;
	while(true){
	// taker user email and password
	System.out.println("please enter the email: ");
	email = sc.nextLine().trim();
	// verify email exists or not
	if(!UtilServices.verifyEmail(email)){
		System.out.println("please enter the valid email address: ");
		continue;
		}
	System.out.println("Please enter the password: ");
	password =sc.nextLine().toCharArray();
	if(!UtilServices.isPasswordValid(password)){
	     System.out.println("password cannot be empty");
	     continue;
	     }
	break;
	
	}
	RequestDTO req = new RequestDTO(email, password);
    // verify email exists or not
	try {
		if(!LoginServices.isUserExists(req)){
			System.out.println("email doesnot exists please signup...");
			return;
		}
	} catch (Exception e) {
		System.out.println("there was internal server error:" + e.getMessage());
		return;
	}
	// verify the password
	if(!LoginServices.verifyPassword(req)){
		System.out.println("Please verify the password");
		return;
	}
	System.out.println("Sucessfully logged in..........");
    
}
}