package com.rest.EnquireManagement.binding;

import lombok.Data;

@Data
public class UnlockForm {

	private String temporaryPassword;
	private String newPassword;
	private String confirmPassword;
	
}
