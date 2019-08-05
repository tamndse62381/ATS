package com.ats.ws;

import com.ats.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailWS {
    @Autowired
    private EmailService emailService;

    @GetMapping("/send")
    @CrossOrigin(origins = "*")
    public void sendEmail(){
        emailService.sendEmail();
    }


}
