package com.stivigala.wolt.email;

import com.stivigala.wolt.dbo.confirmationtoken.ConfirmationToken;
import com.stivigala.wolt.dbo.confirmationtoken.ConfirmationTokenRepository;
import com.stivigala.wolt.dbo.user.WoltUser;
import com.stivigala.wolt.dbo.user.WoltUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.UUID;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private static final long ONE_MINUTE_IN_MILLIS=60000;

    @Value("${spring.mail.username}")
    private String from;

    public EmailService(JavaMailSender javaMailSender, ConfirmationTokenRepository confirmationTokenRepository) {
        this.javaMailSender = javaMailSender;
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Async
    public String sendConfirmationEmail(WoltUser to) {
        try {
            String token = UUID.randomUUID().toString();
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setTo(to.getEmail());
            helper.setText("Szia! Nyugodtan kattints rá erre: http://localhost:8080/confirmation?token=" + token);
            helper.setSubject("Kacsa");
            helper.setFrom(from);
            ConfirmationToken confirmationToken = new ConfirmationToken(
                    token,
                    to,
                    new Date(),
                    new Date(System.currentTimeMillis() + 15 * ONE_MINUTE_IN_MILLIS)
            );
            confirmationTokenRepository.save(confirmationToken);
            javaMailSender.send(mimeMessage);
            return token;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public WoltUser confirm(String token) throws Exception {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token).orElse(null);
        WoltUser woltUser;
        if(confirmationToken == null) {
            throw new Exception("Token not found!");
        }else if (confirmationToken.getExpireAt().before(new Date()))
            throw new Exception("Token expired!");
        else {
            woltUser = confirmationToken.getWoltUser();
        }
        return woltUser;
    }
}
