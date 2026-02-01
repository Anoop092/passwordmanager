package com.avv.app.ui;

import java.util.Scanner;

import com.avv.app.dto.SessionDTO;
import com.avv.app.dto.Vault;
import com.avv.app.dto.VaultEntry;

public class SessionUI {
  private Scanner sc;
  private SessionDTO sessionDTO;
  public SessionUI(Scanner sc, SessionDTO dto){
	  this.sc = sc;
	  this.sessionDTO = dto;
  }
  public void start(){
	  System.out.println("----------------Menu---------------------");
	  System.out.println("If you want to add passwords type 'add' or 1");
	  System.out.println("If you want to delete password type 'delete' or 2");
	  System.out.println("If you want to view password type 'view' or 3");
	  System.out.println("please enter you choice");
	  String userChoice = sc.nextLine();
	  if(userChoice.equalsIgnoreCase("add") ||userChoice.equals("1")){
		  System.out.println("please enter website name: ");
		  String website = sc.nextLine();
		  System.out.println("please enter user name: ");
		  String userName = sc.nextLine();
		  System.out.println("please enter password of the website: ");
		  String password = sc.nextLine();
	  }else if(userChoice.equalsIgnoreCase("view") ||userChoice.equals("3")){
		  view();
	  }
	  
	 }
   public void add(String website, String userName, String password){
	   VaultEntry ve = new VaultEntry(website,userName,password);
	   Vault vault = sessionDTO.getVault();
	   vault.addEntries(ve);
	   
   }
   public void view(){
	   if(sessionDTO.getVault().getEntries().isEmpty()){
		   System.out.println("looks like vault is empty please add");
	   }
	   
   }
}
