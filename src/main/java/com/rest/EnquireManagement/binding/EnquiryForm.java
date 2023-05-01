package com.rest.EnquireManagement.binding;

import lombok.Data;

@Data
public class EnquiryForm {
  
	private Integer enquiryId;
	private String studentName; 
	private String phone; 
	private String classMode; 
	private String courseName;
	private String enquiryStatus;

}