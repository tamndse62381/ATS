package com.ats.service;

import com.ats.entity.Users;
import com.ats.repository.UsersRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(){
        // send mail
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("nguyenduyan.annd@gmail.com");
        mail.setSubject("Test Mail");
        mail.setText("Thong tin nay!!!<br/> 123");
        javaMailSender.send(mail);
    }
}
