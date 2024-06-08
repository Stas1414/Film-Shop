package com.example.demo.service;

import com.example.demo.model.EmailContext;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;

@Service
public class DefaultEmailService implements EmailService {

    private final JavaMailSender emailSender;

    private SpringTemplateEngine templateEngine;

    @Autowired
    public DefaultEmailService(JavaMailSender emailSender, SpringTemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Override
    public void sendEmail(EmailContext emailContext) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(emailContext.getContext());
        String emailContent = templateEngine.process(emailContext.getTemplateLocation(),context);

        mimeMessageHelper.setTo(emailContext.getTo());
        mimeMessageHelper.setFrom(emailContext.getFrom());
        mimeMessageHelper.setSubject(emailContext.getSubject());
        mimeMessageHelper.setText(emailContent,true);
        emailSender.send(mimeMessage);
    }

}
