package com.bishevents.service.impl;

import com.bishevents.entity.ConfirmationToken;
import com.bishevents.service.MailSenderService;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements MailSenderService {
    private final JavaMailSender mailSender;

    public MailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void sendConfirmationEmail(ConfirmationToken confirmationToken, String confirmationUrl) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(confirmationToken.getUser().getEmail()));
        message.setFrom(new InternetAddress("OnlineStore"));
        message.setSubject("Email verification");
        message.setText("For email verification click the link: <a href=\"" + confirmationUrl + "\">confirm</a>", "UTF-8", "html");
        mailSender.send(message);
    }
}
