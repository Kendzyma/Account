package com.accountstatement.notification_service;
import org.springframework.stereotype.Service;

@Service("email")
public class EmailService implements NotificationService {
    @Override
    public void sendEmail(String email) {

    }
}
