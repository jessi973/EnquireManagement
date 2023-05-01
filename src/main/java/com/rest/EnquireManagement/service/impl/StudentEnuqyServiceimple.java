package com.rest.EnquireManagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.EnquireManagement.binding.DashboardResponce;
import com.rest.EnquireManagement.binding.EnqStatus;
import com.rest.EnquireManagement.binding.EnquiryForm;
import com.rest.EnquireManagement.binding.EnquirySearchCriteria;
import com.rest.EnquireManagement.entity.StudentEnqEntity;
import com.rest.EnquireManagement.entity.UserDtlsEntity;
import com.rest.EnquireManagement.repo.StudentEnqRepositorie;
import com.rest.EnquireManagement.repo.UserDetailsRepositorie;
import com.rest.EnquireManagement.service.StudentEnuqyService;
import com.rest.EnquireManagement.utility.UserException;

@Service
public class StudentEnuqyServiceimple implements StudentEnuqyService {
	
	@Autowired
	private StudentEnqRepositorie  enqRepositorie;
	
	@Autowired
	private UserDetailsRepositorie detailsRepositorie;

	@Autowired
	private HttpSession httpSession;
	
	
	@Override
	public String saveStudentData(EnquiryForm enquiryForm) {
      
		 StudentEnqEntity entity= new StudentEnqEntity();
		 BeanUtils.copyProperties(enquiryForm,entity);
		
	     Integer id= (Integer)	httpSession.getAttribute("userid");
	     Optional<UserDtlsEntity> details= detailsRepositorie.findById(id);
	     entity.setUser_ID(details.get());
	     enqRepositorie.save(entity);
		
		return "sucess";
	}


	@Override
	public DashboardResponce getResponce(Integer id)throws UserException {
	
		DashboardResponce dashboardResponce=new DashboardResponce();
		
	Optional<UserDtlsEntity> userEntity=	detailsRepositorie.findById(id);
	if(userEntity.isPresent()) {
		
	List<StudentEnqEntity> studentEntity=	enqRepositorie.findByuserID(userEntity.get());
	
	Integer totalCount=  studentEntity.size();
	
	dashboardResponce.setTotalEnquries(totalCount);
	 Integer enrolledCount =studentEntity.stream()
	             .filter(e->e.getEnquiryStatus().equals("Enrolled"))
	             .collect(Collectors.toList()).size();
	if(enrolledCount !=null) {
		dashboardResponce.setEnrolled(enrolledCount);	
	}
	    Integer lostCount =studentEntity.stream()
            .filter(e->e.getEnquiryStatus().equals("lost"))
            .collect(Collectors.toList()).size();
	    if(lostCount !=null) {
	    	dashboardResponce.setLostcount(lostCount);
		}
		
	//dashboardResponce.setEnrolled(enrolledCount);
	dashboardResponce.setLostcount(lostCount);
	
	}else {
		throw new UserException("Student Details Not Found");
	}
		
		return dashboardResponce;
	}


	@Override
	public List<EnqStatus> getAllStudent(Integer id) {
      
		List<EnqStatus> listStatus=new ArrayList<EnqStatus>();
		Optional<UserDtlsEntity> userEntity=detailsRepositorie.findById(id);
		if(userEntity.isPresent()) {	
		List<StudentEnqEntity> studentEntity=enqRepositorie.findByuserID(userEntity.get());
		for(StudentEnqEntity result :studentEntity) {
			EnqStatus enuries= new EnqStatus();
			enuries.setStudentName(result.getStudentName());
			enuries.setPhone(result.getPhone());
			enuries.setCourseName(result.getCourseName());
			enuries.setClassMode(result.getClassMode());
			enuries.setEnquiryStatus(result.getEnquiryStatus());
			enuries.setCreatedDate(result.getCreatedDate());
			enuries.setUpdatedDate(result.getUpdatedDate());
			listStatus.add(enuries);
		}	
	}
		return listStatus;
}


	@Override
	public String edditStudentData(Integer id, EnquiryForm data) {
		 //StudentEnqEntity entity= new StudentEnqEntity();
		 Integer userid= (Integer)	httpSession.getAttribute("userid");
		 
		 Optional<UserDtlsEntity> details= detailsRepositorie.findById(userid);
		 Optional<StudentEnqEntity> entitys= enqRepositorie.findById(id);
		 if(entitys.isPresent()) {
			 StudentEnqEntity student=  entitys.get();
			 student.setStudentName(data.getStudentName());
			 student.setPhone(data.getPhone());
			 student.setClassMode(data.getClassMode());
			 student.setCourseName(data.getCourseName());
			 student.setEnquiryStatus(data.getEnquiryStatus());
			 student.setUser_ID(details.get());
			 enqRepositorie.save(student);
		 }
	    
		return "sucess";
	}


	@Override
	public List<EnqStatus> searchFilter(EnquirySearchCriteria criteria, Integer id) {
		List<EnqStatus> enquri= new ArrayList<EnqStatus>();
		Optional<UserDtlsEntity> userEntity=	detailsRepositorie.findById(id);
		if(userEntity.isPresent()) {	
		List<StudentEnqEntity> studentEntity=	enqRepositorie.findByuserID(userEntity.get());
		if(criteria !=null && !"".equals(criteria.getClassMode())){
			
			studentEntity= studentEntity.stream()
			             .filter(e->e.getClassMode().equals(criteria.getClassMode()))
			             .collect(Collectors.toList());
		
		}
       if(criteria !=null && !"".equals(criteria.getCourseName())){
			
			studentEntity= studentEntity.stream()
			             .filter(e->e.getCourseName().equals(criteria.getCourseName()))
			             .collect(Collectors.toList());
			
		}
       if(criteria !=null && !"".equals(criteria.getEnquiryStatus())){
			
			studentEntity= studentEntity.stream()
			             .filter(e->e.getEnquiryStatus().equals(criteria.getEnquiryStatus()))
			             .collect(Collectors.toList());
			}
       for(StudentEnqEntity status :studentEntity) {
			EnqStatus en=new EnqStatus();
			en.setStudentName(status.getStudentName());
			en.setCourseName(status.getCourseName());
			en.setClassMode(status.getClassMode());
			en.setPhone(status.getPhone());
			en.setEnquiryStatus(status.getEnquiryStatus());
			en.setCreatedDate(status.getCreatedDate());
			en.setUpdatedDate(status.getUpdatedDate()); 
			enquri.add(en);
		}
       return enquri;
		}
		
		return null;
	}
}
