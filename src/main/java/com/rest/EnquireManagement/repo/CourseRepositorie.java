package com.rest.EnquireManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.EnquireManagement.entity.CourseEntity; 


public interface CourseRepositorie extends JpaRepository<CourseEntity, Integer> {

}
