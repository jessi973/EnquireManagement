package com.rest.EnquireManagement.binding;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
public class EnqStatus {
	
	private Integer enquiryId;   
	private String studentName; 
	private String phone; 
	private String classMode; 
	private String courseName;
	private String enquiryStatus;
	private LocalDate createdDate;
	private LocalDate updatedDate;
	

}
