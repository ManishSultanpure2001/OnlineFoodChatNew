package com.onlinefoodchat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

	@Autowired

     private JavaMailSender javaMailSender;
	
	public boolean mailSender(String toEmail ,String subject ,String body ) {
		 SimpleMailMessage mailMessage
         = new SimpleMailMessage();
			/* Setting up necessary details */
         mailMessage.setFrom("manishsultanpuretube@gmail.com");
         mailMessage.setTo(toEmail);
         mailMessage.setText(body);
         mailMessage.setSubject(subject);

			/* Sending the mail */
         javaMailSender.send(mailMessage);
         System.out.println("Mail Send Successfully");
         return true;
	}
}
