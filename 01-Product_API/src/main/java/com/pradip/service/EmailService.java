package com.pradip.service;

public interface EmailService {

	public boolean sendEmail(String subject, String body, String emailId);
}
