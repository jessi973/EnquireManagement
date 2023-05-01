package com.rest.EnquireManagement.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.EnquireManagement.entity.EnqStatusEntity; 


public interface EnqStatusRepositorie extends  JpaRepository<EnqStatusEntity, Integer> {

}
