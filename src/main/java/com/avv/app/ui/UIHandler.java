package com.avv.app.ui;

import java.util.Scanner;

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
	 ss.save();
	 System.out.println("Successfully saved");
	 
	 
 }
}
