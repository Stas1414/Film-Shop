package com.example.demo.controller;

import com.example.demo.model.EmailContext;
import com.example.demo.model.User;
import com.example.demo.service.DefaultEmailService;
import com.example.demo.service.UserService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/email")
public class EmailController {

    private DefaultEmailService emailService;
    private UserService userService;

    @Autowired
    public EmailController(DefaultEmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping("/send")
    public String sendMessage(){
        User user =userService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        EmailContext emailContext = new EmailContext();

        emailContext.setTo(user.getEmail());
        emailContext.setSubject("Оплата");
        emailContext.setFrom("zhbnspring@gmail.com");


        try {
          emailService.sendEmail(emailContext);
        } catch (MailException mailException) {
            throw  new RuntimeException();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return "user/successful_payment";
    }
}
