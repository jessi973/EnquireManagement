package com.rest.EnquireManagement.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.EnquireManagement.entity.StudentEnqEntity;
import com.rest.EnquireManagement.entity.UserDtlsEntity; 

public interface StudentEnqRepositorie extends JpaRepository<StudentEnqEntity, Integer>  {
	
	@Query("select e from StudentEnqEntity e where e.user_ID =?1")
	public  List<StudentEnqEntity> findByuserID(UserDtlsEntity id);

}
