package com.avv.app.ui;

import java.util.Iterator;
import java.util.Scanner;

import com.avv.app.dto.SessionDTO;
import com.avv.app.dto.Vault;
import com.avv.app.dto.VaultEntry;
import com.avv.app.services.VaultServices;
import com.fasterxml.jackson.databind.ser.impl.IteratorSerializer;

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
		  try{
			  add(website,userName,password);
			  
		  }catch(Exception ex){
			  System.err.println(ex.getClass().getName() + ":" + ex.getMessage());
		  }
	  }else if(userChoice.equalsIgnoreCase("view") ||userChoice.equals("3")){
		  view();
	  }
	  
	 }
   public void add(String website, String userName, String password) throws Exception{
	   //create new vault entry
	   VaultEntry ve = new VaultEntry(website,userName,password);
	   // save the entry in vault;
	   VaultServices vs = new VaultServices();
	   vs.add(sessionDTO, ve);   
	   
   }
   	public void view() {

		    Vault vault = sessionDTO.getVault();

		    if (vault == null || vault.getEntries() == null || vault.getEntries().isEmpty()) {
		        System.out.println("Looks like vault is empty. Please add entries.");
		        return;
		    }

		    Iterator<VaultEntry> it = vault.getEntries().iterator();

		    while (it.hasNext()) {
		        VaultEntry entry = it.next(); 
		        System.out.println(
		            entry.getWebsite() + " | " +
		            entry.getUserName() + " | " +
		            entry.getPassword()
		        );
		    }
		}

	   
   }

