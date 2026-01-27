package com.avv.app.utils;

import org.apache.commons.validator.routines.EmailValidator;

public class UtilServices {
  public static boolean verifyEmail(String email){
	  if(email==null || email.isEmpty()) return false;
	  EmailValidator validator = EmailValidator.getInstance();
	  return validator.isValid(email);
  }
}
