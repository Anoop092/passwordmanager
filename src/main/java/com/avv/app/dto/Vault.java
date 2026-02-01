package com.avv.app.dto;

import java.util.ArrayList;

public class Vault {
 private ArrayList<VaultEntry> entries;
 public Vault(ArrayList<VaultEntry> arr){
	 this.entries = arr;
 }
 public Vault(){}
 
 public ArrayList<VaultEntry> getEntries(){
	 return this.entries;
 }
 public void addEntries(VaultEntry ve){
	 this.entries.add(ve);
 }
}
