package com.pradip.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.pradip.service.EmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired 
	private JavaMailSender mailSender;

	@Override
	public boolean sendEmail(String subject, String body, String toEmailId) {
		try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            helper.setSubject(subject);
            helper.setText(body, true);
            helper.setTo(toEmailId);
            mailSender.send(mimeMessage);
            
            return true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
