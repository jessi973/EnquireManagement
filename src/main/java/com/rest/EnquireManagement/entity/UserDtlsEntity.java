package com.rest.EnquireManagement.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import lombok.Data;

@Entity
@Table(name="Userdetails")
@Data
public class UserDtlsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;			
	private String  name;			
	private String email; 
	private String phno; 
	private String passWord;	
	private String accStatus; 
	
}
