package com.rest.EnquireManagement.utility;


public class ApplicationUserException {

private	UserErrorCode erroCode;
	
private	String description;

private Throwable  baseException;

public UserErrorCode getErroCode() {
	return erroCode;
}

public void setErroCode(UserErrorCode erroCode) {
	this.erroCode = erroCode;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Throwable getBaseException() {
	return baseException;
}

public void setBaseException(Throwable baseException) {
	this.baseException = baseException;
}
	
	
}
