package com.rest.EnquireManagement.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.EnquireManagement.binding.DashboardResponce;
import com.rest.EnquireManagement.binding.EnqStatus;
import com.rest.EnquireManagement.binding.EnquiryForm;
import com.rest.EnquireManagement.binding.EnquirySearchCriteria;
import com.rest.EnquireManagement.entity.StudentEnqEntity;
import com.rest.EnquireManagement.service.StudentEnuqyService;
import com.rest.EnquireManagement.utility.UserException;

@RestController
public class EuquiryController {
	
	@Autowired
	private StudentEnuqyService enuqyService;
	@Autowired
	private HttpSession httpSession;
	
	@GetMapping("/dashBoard")
	public DashboardResponce dashBoardPage() throws UserException {
		Integer userid =(Integer)httpSession.getAttribute("userid");
	    DashboardResponce dashboardResponce=	enuqyService.getResponce(userid);
		
		return dashboardResponce;
	}
	@PostMapping("/enquiry")
	public ResponseEntity<String> addEnquiryPage(@RequestBody EnquiryForm enquiryForm) {
		
		ResponseEntity<String> responce = null;
		if(enquiryForm.getStudentName().trim().isEmpty() || enquiryForm.getPhone().trim().isEmpty() || 
				enquiryForm.getClassMode().trim().isEmpty() ||
	      enquiryForm.getCourseName().trim().isEmpty() || enquiryForm.getEnquiryStatus().trim().isEmpty() ) {
			
			responce = new ResponseEntity<String>("Please enter Student all details",HttpStatus.BAD_REQUEST);

		}else {
			 String status=	enuqyService.saveStudentData(enquiryForm);
			 responce = new ResponseEntity<String>(status,HttpStatus.CREATED);
		}
		
		return responce;
	}

	@GetMapping("/getEnquirys")
	public ResponseEntity<List<EnqStatus>> getEnquiryPage()throws UserException {
		ResponseEntity<List<EnqStatus>> responce= null;
		Integer userid =(Integer)httpSession.getAttribute("userid");
		List<EnqStatus> listStudents=enuqyService.getAllStudent(userid);
		if(listStudents.isEmpty()) {
			 throw new UserException("Student Data Not FOund",HttpStatus.NOT_FOUND);
		}else {
			responce=new ResponseEntity<List<EnqStatus>>(listStudents,HttpStatus.OK);
		}
		return responce;
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody EnquiryForm enquiryForm) {
		ResponseEntity<String> responce = null;
		if(enquiryForm.getStudentName().trim().isEmpty() || enquiryForm.getPhone().trim().isEmpty() || 
				enquiryForm.getClassMode().trim().isEmpty() ||
	      enquiryForm.getCourseName().trim().isEmpty() || enquiryForm.getEnquiryStatus().trim().isEmpty() ) {
			
			responce = new ResponseEntity<String>("Please enter Student all details",HttpStatus.BAD_REQUEST);

		}else {
			 String status=	enuqyService.edditStudentData(id,enquiryForm);
			 responce = new ResponseEntity<String>(status,HttpStatus.CREATED);
		}
		return responce;
	}
	@GetMapping("/logout")
	public ResponseEntity<String> logOut(){
		httpSession.removeAttribute("userid");
		return ResponseEntity.ok("logout");
		
	}
	@GetMapping("/filter/{course}/{courseMode}/{courseStatus}")
	public ResponseEntity<List<EnqStatus> > searchFilter(@PathVariable  (value="course",required = false) String courseName,
			@PathVariable(value="courseMode",required = false)String courseMode,
			@PathVariable (value="courseStatus",required = false) String courseStatus)throws UserException{
		System.out.println("enter into controller class");
		ResponseEntity<List<EnqStatus> > responce=null; 
		Integer userid =(Integer)httpSession.getAttribute("userid");
		EnquirySearchCriteria criteria= new EnquirySearchCriteria();
		criteria.setCourseName(courseName);
		criteria.setClassMode(courseMode);
		criteria.setEnquiryStatus(courseStatus);
		List<EnqStatus>filterdata=enuqyService.searchFilter(criteria, userid);
		if(filterdata.isEmpty()) {
		  throw new UserException("Not Found",HttpStatus.NOT_FOUND);	
		}else{
			responce = new ResponseEntity<List<EnqStatus>>(filterdata,HttpStatus.OK);
		}
		System.out.println("exit from controller class");

		return responce;
		
	}
	


}
