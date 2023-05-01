package com.rest.EnquireManagement.service;

import com.rest.EnquireManagement.binding.DashboardResponce;
import com.rest.EnquireManagement.binding.LoginForm;
import com.rest.EnquireManagement.binding.SignUpForm;
import com.rest.EnquireManagement.binding.UnlockForm;
import com.rest.EnquireManagement.utility.UserException;

public interface SingupService {
	
	public boolean singup(SignUpForm signUpForm )throws UserException;
	
	public boolean unlock(UnlockForm unlockForm)throws UserException;
	
	public String forgetPassword(String email)throws UserException;
	
	public String  login(LoginForm loginform)throws UserException;

}
