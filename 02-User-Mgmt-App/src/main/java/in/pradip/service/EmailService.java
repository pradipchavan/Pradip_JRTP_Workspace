package in.pradip.service;

import org.springframework.stereotype.Service;

public interface EmailService {
    public boolean sendEmail(String subject, String body, String emailId);
}
