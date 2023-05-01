package com.rest.EnquireManagement.binding;

import java.util.List;

import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import com.rest.EnquireManagement.entity.UserDtlsEntity;

import lombok.Data;

@Data
public class SignUpForm {

	private String name;
	@NotEmpty
	@Email
	private String email;
	private String phno;
	
	
}
