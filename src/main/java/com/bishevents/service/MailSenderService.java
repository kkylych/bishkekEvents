package com.bishevents.service;


import com.bishevents.entity.ConfirmationToken;
import jakarta.mail.MessagingException;

public interface MailSenderService {
    void sendConfirmationEmail(ConfirmationToken confirmationToken, String confirmationUrl) throws MessagingException;
}
