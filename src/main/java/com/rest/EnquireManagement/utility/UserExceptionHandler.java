package com.rest.EnquireManagement.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;




@RestControllerAdvice
public class UserExceptionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<ApplicationUserException> handleUserException(UserException stu){
	
		
		 ResponseEntity<ApplicationUserException> re=null;
		 ApplicationUserException code=new ApplicationUserException();
		 code.setDescription(stu.getMessage());
			code.setBaseException(stu);
			code.setErroCode(stu.getCode());
		 if(stu.getCode()==UserErrorCode.INVALID_CREDENCTIAL) {
			 
              re=new ResponseEntity<ApplicationUserException>(code,HttpStatus.INTERNAL_SERVER_ERROR); 
		 }else {
			 
			 re=new ResponseEntity<ApplicationUserException>(code,HttpStatus.BAD_REQUEST);
			 
		 }
		
		
    	return re;	
	}
	
	
}
