package com.rest.EnquireManagement.service.impl;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.EnquireManagement.binding.LoginForm;
import com.rest.EnquireManagement.binding.SignUpForm;
import com.rest.EnquireManagement.binding.UnlockForm;
import com.rest.EnquireManagement.entity.StudentEnqEntity;
import com.rest.EnquireManagement.entity.UserDtlsEntity;
import com.rest.EnquireManagement.repo.StudentEnqRepositorie;
import com.rest.EnquireManagement.repo.UserDetailsRepositorie;
import com.rest.EnquireManagement.service.SingupService;
import com.rest.EnquireManagement.utility.Emailutility;
import com.rest.EnquireManagement.utility.Passwordutility;
import com.rest.EnquireManagement.utility.UserException;

@Service
public class SingupServiceimpl implements SingupService {
	
	@Autowired
    private	Emailutility emailutility;
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private StudentEnqRepositorie enqRepositorie;
	
	@Autowired
	private UserDetailsRepositorie detailsRepositorie;

	@Override
	public boolean singup(SignUpForm singform)throws UserException {
		
		UserDtlsEntity email =detailsRepositorie.findByEmail(singform.getEmail());
		
		if(email==null) {
	 	
		UserDtlsEntity entity= new UserDtlsEntity();
	    entity.setName(singform.getName());
	    entity.setEmail(singform.getEmail());
	    entity.setPhno(singform.getPhno());
	    
	    entity.setAccStatus("LOCKED");
	    
	   String pswd= Passwordutility.paswGenerator();
	   entity.setPassWord(pswd);
	   
	   String subject="TEMPORY PASSWORD";
	   String to=singform.getEmail();
	   
	   emailutility.sendEmail(subject, pswd, to);
	   
	   detailsRepositorie.save(entity);
	     
		}else {
			throw new UserException("Please enter unique email id");
		}
	 
		return true;
	}

	@Override
	public boolean unlock(UnlockForm unlockForm) throws UserException {
		UserDtlsEntity details=	detailsRepositorie.findEmailbyPasword(unlockForm.getTemporaryPassword());
	   if(details!=null) {	
		 if(details.getPassWord().equals(unlockForm.getTemporaryPassword())){
		  details.setPassWord(unlockForm.getConfirmPassword());
		  details.setAccStatus("unclocked");
		  detailsRepositorie.save(details);
		  }
	   }
		 else{ 
	       throw new UserException("Please enter correct Temporary password"); 
	       }
		return true;
	}

	@Override
	public String forgetPassword(String email) throws UserException {
		UserDtlsEntity details=detailsRepositorie.findByEmail(email);
		if(details !=null) {
		   String paswd =	details.getPassWord();
		   
		   String subject="LOGIN PASSWORD";
		   String to=email;
		   
		     emailutility.sendEmail(subject, "password :"+paswd, to);
		   
		}else {
			  throw new UserException("Please enter valid email id"); 
		}
		return "please check your email";
	}
	
	@Override
	public String login(LoginForm loginform)throws UserException {
		
		
		
		UserDtlsEntity details=detailsRepositorie.findByEmail(loginform.getEmail());
		if(details!=null) {
			
			if(details.getPassWord().equals(loginform.getPassword())) {
			
			 if(details.getAccStatus().equals("unclocked")) {
				
		    httpSession.setAttribute("userid", details.getUserId());
			
		 
				return "Login Sucess";
			}else {
				throw new UserException("Your Account is Locked");
			}
			}else {
				throw new UserException("Invalid Email or Password");
			}
			
		}else {
			throw new UserException("Invalid Email or Password");
		}
		
	}
}