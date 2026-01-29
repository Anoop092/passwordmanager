package com.avv.app;
import java.util.Scanner;

import com.avv.app.ui.UIHandler;

public class App 
{
    public static void main( String[] args )
    { 
    	Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to password manager");
        System.out.println("Please enter '1' for signin or '2' for login ");
        String input = sc.nextLine().trim();
        if(input.equals("1")){
        	UIHandler ui  = new UIHandler(sc);
        	ui.signup();
        }else if(input.equals("2")){
        	// login
        }else{
        	System.out.println("Invalid input");
        }
    }
}