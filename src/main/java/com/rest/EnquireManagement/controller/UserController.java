package com.rest.EnquireManagement.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.EnquireManagement.binding.DashboardResponce;
import com.rest.EnquireManagement.binding.LoginForm;
import com.rest.EnquireManagement.binding.SignUpForm;
import com.rest.EnquireManagement.binding.UnlockForm;
import com.rest.EnquireManagement.service.SingupService;
import com.rest.EnquireManagement.utility.UserException;

@RestController
@CrossOrigin("")
public class UserController {
	@Autowired
	private SingupService service;
	
	@PostMapping("/login")
	public ResponseEntity<String> loginPage(@RequestBody LoginForm loginform) throws UserException {
		
		ResponseEntity<String> responce=null;
		String srt=  service.login(loginform);
		if(srt==null) {
			responce =  new ResponseEntity<String>(srt,HttpStatus.BAD_REQUEST);  
		}else {
			responce =  new ResponseEntity<String>(srt,HttpStatus.ACCEPTED);
		}
		return responce ;
	}
	
	@PostMapping("/singup")
	public ResponseEntity<String> singUpPage(@Valid  @RequestBody SignUpForm singform) throws UserException {
		
		ResponseEntity<String> rspo=null;
		
		if(singform.getName().trim().isEmpty() ||singform.getEmail().trim().isEmpty()|| singform.getPhno().trim().isEmpty()) {
			rspo= new ResponseEntity<String>("Please enter all Details",HttpStatus.BAD_REQUEST);
		
		}else {
			service.singup(singform);
		     rspo= new ResponseEntity<String>("Check your email",HttpStatus.CREATED);
		}
		return rspo;
	}
	
	@PostMapping("/unlock")
	public ResponseEntity<String>unlockPage(@RequestBody UnlockForm unlockform) throws UserException {
		ResponseEntity<String> responce=null;
	if(unlockform.getNewPassword().trim().isEmpty() || unlockform.getConfirmPassword().trim().isEmpty()) {
		responce=new ResponseEntity<String>("Please enter new password and confirm password",HttpStatus.BAD_REQUEST);
	}else {
		  if(unlockform.getNewPassword().equals(unlockform.getConfirmPassword())) {
			  service.unlock(unlockform); 
			  responce=new ResponseEntity<String>("Please Login ",HttpStatus.CREATED);
		  }else {
			  responce=new ResponseEntity<String>("Please enter new password and confirm password as same",HttpStatus.BAD_REQUEST);
		  }
	}
		return responce;
	}
	
	@GetMapping("/forgot/{email}")
	public ResponseEntity<String>  forgotPwdPage(@PathVariable(name="email") String mail) throws UserException {
		
		  String psw=service.forgetPassword(mail);
		  
		return new ResponseEntity<String>(psw,HttpStatus.OK);
	}


}
