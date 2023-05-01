package com.rest.EnquireManagement.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class Passwordutility {
	
	
	public static String paswGenerator() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random( 7, characters );
		return pwd;
	}

}
