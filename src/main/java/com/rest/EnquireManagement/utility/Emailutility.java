package com.rest.EnquireManagement.utility;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Emailutility {

	@Autowired
	private JavaMailSender javaMailSender; 
	
	public boolean sendEmail(String subject,String body, String to) {
		boolean status=false;
		try {
			MimeMessage mimeMessage=javaMailSender.createMimeMessage();
			MimeMessageHelper helper= new MimeMessageHelper(mimeMessage);
			helper.setSubject(subject);
			helper.setText(body ,true);
			helper.setTo(to);
			javaMailSender.send(mimeMessage);
			status=true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return status;
		
	}
	
}
