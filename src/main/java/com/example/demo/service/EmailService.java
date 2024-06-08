package com.example.demo.service;


import com.example.demo.model.EmailContext;
import jakarta.mail.MessagingException;

public interface EmailService {
    void sendEmail(EmailContext emailContext) throws MessagingException;
}
