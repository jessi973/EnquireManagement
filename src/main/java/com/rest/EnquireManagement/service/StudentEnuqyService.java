package com.rest.EnquireManagement.service;

import java.util.List;

import com.rest.EnquireManagement.binding.DashboardResponce;
import com.rest.EnquireManagement.binding.EnqStatus;
import com.rest.EnquireManagement.binding.EnquiryForm;
import com.rest.EnquireManagement.binding.EnquirySearchCriteria;
import com.rest.EnquireManagement.entity.StudentEnqEntity;
import com.rest.EnquireManagement.utility.UserException;

public interface StudentEnuqyService {
	
	public String saveStudentData(EnquiryForm enquiryForm);
	
	public DashboardResponce getResponce(Integer id)throws UserException;

	public List<EnqStatus> getAllStudent(Integer id);
	
	public String edditStudentData(Integer id, EnquiryForm data);
	
	public List<EnqStatus> searchFilter(EnquirySearchCriteria criteria ,Integer id );
}
