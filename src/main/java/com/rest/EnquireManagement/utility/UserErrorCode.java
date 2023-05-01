package com.rest.EnquireManagement.utility;
public enum UserErrorCode {

	INVALID_CREDENCTIAL("S404");
	String value="";
	
	UserErrorCode(String srt){
		
		this.value=srt;
	}
	
	
}
