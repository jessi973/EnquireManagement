package com.rest.EnquireManagement.utility;

import org.springframework.http.HttpStatus;

public class UserException extends Exception {

	private UserErrorCode code;
	
	public UserException(String srt) {
		
	   super(srt);
	}
	public UserException(String srt,HttpStatus httpstatus) {
		
		   super(srt);
		}
	public UserException(String srt,Throwable t) {
		
		   super(srt,t);
		}
	
	public UserException(String srt,UserErrorCode code) {
		
		   super(srt);
		   this.code=code;
		}

	public UserErrorCode getCode() {
		return code;
	}
	
}
