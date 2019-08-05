package com.ats.service;

import com.ats.entity.Users;
import com.ats.repository.UsersRepository;
import org.apache.catalina.User;
<<<<<<< HEAD
=======
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
>>>>>>> parent of ab7f401... Revert "Merge branch 'tamnd'"
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class EmailService {
    private JavaMailSender javaMailSender;
    @Autowired
    private UsersRepository usersRepository;

    String footer =
            "<p style='font-size:150%;font-family:verdana;'>" +
                    "<p><b><u>Date:</u>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;" + new SimpleDateFormat("EEEE").format(new Date()) +
                    ", &ensp;" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "  </b></p>" +
                    "<p><b><u>Time:</u>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; 2:00:00 PM </b> " + "</p>" +
                    "<p><b><u>&#272;i&#803;a &#272;i&ecirc;&#777;m:</u>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp; &#272;a&#803;i ho&#803;c FPT, " +
                    "To&#768;a nha&#768; Innovation." +
                    "<p></p>" +
                    "&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;C&ocirc;ng vi&ecirc;n ph&acirc;&#768;n m&ecirc;&#768;m Quang Trung," +
                    "Tan Chanh Hiep, Qu&acirc;&#803;n 12,Tha&#768;nh Ph&ocirc;&#769; Ho Chi Minh</b></p>" +
                    "<p>Thanks and Best Regards,</p>" +
                    "<p>Team 17</p>" +
                    "<p>Recruitment team</p>" +
                    "<p>Human Resource Dept.</p>" +
                    "<p>Team 17(Vietnam) JSC</p>" +
                    "<p>To&#768;a nha&#768; Innovation,C&ocirc;ng vi&ecirc;n ph&acirc;&#768;n m&ecirc;&#768;m " +
                    "Quang Trung ,Qu&acirc;&#803;n 12,Tha&#768;nh Ph&ocirc;&#769; Ho Chi Minh</p>" +
                    "<p>Tel: (+84) 077 761 4243</p>" +
                    "<p>Mobi: (+84) 077 761 4243</p>" +
                    "<p>Email: jobboard.system@gmail.com</p>" +
                    "<p>Website: www.globalcybersoft.com</p>" +
                    "<p>We Make it Happen. Better.</p></p>";

    private static final Logger LOGGER = LogManager.getLogger(EmailService.class);

    @Autowired
    private EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String email, String jobTitle, String userFullname, String result) {
        try {
            LOGGER.info("Begin SendEmail in EmailService with Email : " + email);
            MimeMessage message = javaMailSender.createMimeMessage();
            boolean multipart = true;
            String subject = "";
            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "UTF-8");

            String confirm = "<p>Dear Mr/Ms.<b>" + userFullname + "</b>,</p>" +
                    "<p></p>" +
                    "<p>CV Cu&#777;a Ba&#803;n &#272;a&#771; &#272;&#432;&#417;&#803;c Ch&acirc;&#769;p Nh&acirc;&#803;n Va&#768;o C&ocirc;ng Vi&ecirc;&#803;c : <b>" + jobTitle + "</b></p>";

            String deny = "<p>Dear Mr/Ms.<b>" + userFullname + "</b></p>" +
                    "<p> </p>" +
                    "<p>CV cu&#777;a ba&#803;n &#273;a&#771; bi&#803; t&#432;&#768; ch&ocirc;&#769;i va&#768;o c&ocirc;ng vi&ecirc;&#803;c : <b>" + jobTitle + "</b></p>";

            String apply = "<p>Dear Mr/Ms.<b>" + userFullname + "</b></p>" +
                    "<p> </p>" +
                    "<p>B&#7841;n &#273;&atilde; &#7913;ng tuy&#7875;n th&agrave;nh c&ocirc;ng v&agrave;o c&ocirc;ng vi&#7879;c: " + jobTitle + "</b></p>";


            if (result.equals("confirm")) {
                message.setContent(confirm + footer, "text/html");
                subject = "Confirm Mail";
            }
            if (result.equals("deny")) {
                message.setContent(deny + footer, "text/html");
                subject = "Deny Mail";
            }
            if (result.equals("apply")) {
                message.setContent(apply + footer, "text/html/css");
                subject = "Apply Mail";
            }

            helper.setTo(email);
            helper.setSubject(subject);

            this.javaMailSender.send(message);
            LOGGER.info("End SendEmail in EmailService with Email : " + email);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

    public void sendActiveUserEmail(String token, String email) {
        try {
            LOGGER.info("Begin sendActiveUserEmail in EmailService with Email : " + email);
            MimeMessage message = javaMailSender.createMimeMessage();
            boolean multipart = true;
            String subject = "Complete Registration";
            MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "UTF-8");

            String confirm = "<p>&#272;&ecirc;&#777; ki&#769;ch hoa&#803;t ta&#768;i " +
                    "khoa&#777;n, xin ba&#803;n click va&#768;o &#273;&#432;&#417;&#768;ng link: </p>" +
                    "http://localhost:8080/user/confirmUser?token=" + token;
            message.setContent(confirm, "text/html");

            helper.setTo(email);
            helper.setSubject(subject);
            this.javaMailSender.send(message);
            LOGGER.info("End sendActiveUserEmail in EmailService with Email : " + email);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }
}
