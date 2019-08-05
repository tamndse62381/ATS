package com.ats.ws;

import com.ats.service.EmailService;
import com.ats.service.MailClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailWS {
    @Autowired
    private EmailService emailService;
    @Autowired
    private MailClient mailClient;

    @GetMapping("/send")
    @CrossOrigin(origins = "*")
    public void sendEmail(@RequestParam("email") String email,
                          @RequestParam("jobTitle") String jobTitle,
                          @RequestParam("userFullName") String userFullName,
                          @RequestParam("result") String result) {
        email = "tamndse62381@gmail.com";
        jobTitle = "CSS Developer";
        userFullName = "Nguyen Duc Tam";
        emailService.sendEmail(email, jobTitle, userFullName, result);
    }


}
