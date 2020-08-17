package com.dev_incubator.dits.service;

import com.dev_incubator.dits.service.interfaces.MailService;
import com.dev_incubator.dits.util.MessageSourceFacade;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@Transactional
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    private final MessageSourceFacade messageSource;

    @Override
    public void sendEmail(String email, String title, String msg) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            message.setContent(msg, "text/html; charset=utf-8");
            helper.setTo(email);
            helper.setSubject(title);
            mailSender.send(message);
        } catch (MessagingException e) {
            System.out.println(messageSource.getMessage("email.not.sent"));
        }
    }

    @Override
    public String getRegistrationMessage(String addresseeFirstName, String addresseeLastName, String login, String password) {
        return "<div style=\"padding: 0; margin: 0; color: #272f3a;\">\n" +
                "    <h2>Good day, " + addresseeFirstName + " " + addresseeLastName + "!</h2>\n" +
                "    <h3><p>U have registered in the DITS application. Your credentials:</p>\n" +
                "    <ul>\n" +
                "        <li>Login: " + login + "</li>\n" +
                "        <li>Password: " + password + "</li>\n" +
                "    </ul>\n" +
                "    <p>After checking your profile, you will be given access to the application. <br>\n" +
                "    The corresponding notification will be sent to this email.</p>\n" +
                "    <p>Best regards,<br>DITS team!</p></h3>\n" +
                "    <h5>This email was sent automatically, you do not need to reply to it.</h5>\n" +
                "    <img src=\"https://heilash.s3.us-east-2.amazonaws.com/dits.png\" height=\"200\" alt=\"dits\">\n" +
                "</div>";
    }

    @Override
    public String getUnblockMessage(String addresseeFirstName, String addresseeLastName) {
        return "<div style=\"padding: 0; margin: 0; color: #272f3a;\">\n" +
                "    <h2>Good day, " + addresseeFirstName + " " + addresseeLastName + "!</h2>\n" +
                "    <h3><p>Your profile in the DITS application was unlocked and you can sign in.</p>\n" +
                "    <p>Best regards,<br>DITS team!</p></h3>\n" +
                "    <h5>This email was sent automatically, you do not need to reply to it.</h5>\n" +
                "    <img src=\"https://heilash.s3.us-east-2.amazonaws.com/dits.png\" height=\"200\" alt=\"dits\">\n" +
                "</div>";
    }

    @Override
    public String getBlockMessage(String addresseeFirstName, String addresseeLastName) {
        return "<div style=\"padding: 0; margin: 0; color: #272f3a;\">\n" +
                "    <h2>Good day, " + addresseeFirstName + " " + addresseeLastName + "!</h2>\n" +
                "    <h3><p>Your profile in the DITS application was blocked and you cannot sign in.</p>\n" +
                "    <p>Best regards,<br>DITS team!</p></h3>\n" +
                "    <h5>This email was sent automatically, you do not need to reply to it.</h5>\n" +
                "    <img src=\"https://heilash.s3.us-east-2.amazonaws.com/dits.png\" height=\"200\" alt=\"dits\">\n" +
                "</div>";
    }
}
