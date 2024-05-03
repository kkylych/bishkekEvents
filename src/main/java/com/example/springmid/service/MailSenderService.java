package com.example.springmid.service;


import com.example.springmid.entity.ConfirmationToken;
import jakarta.mail.MessagingException;

public interface MailSenderService {
    void sendConfirmationEmail(ConfirmationToken confirmationToken, String confirmationUrl) throws MessagingException;
}
