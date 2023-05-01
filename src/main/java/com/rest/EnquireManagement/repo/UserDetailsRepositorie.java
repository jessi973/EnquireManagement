package com.rest.EnquireManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.EnquireManagement.binding.SignUpForm;
import com.rest.EnquireManagement.entity.UserDtlsEntity; 


public interface UserDetailsRepositorie extends JpaRepository<UserDtlsEntity, Integer> {

	public UserDtlsEntity findByEmail(String sing);
	
	@Query("select p from UserDtlsEntity p where p.passWord=?1")
	public UserDtlsEntity findEmailbyPasword(String password);
	
}
