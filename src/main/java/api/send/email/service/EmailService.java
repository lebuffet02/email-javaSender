package api.send.email.service;

import api.send.email.dto.EmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.UnsupportedEncodingException;

@Service
@Slf4j
public class EmailService {

    @Autowired
    JavaMailSender mailSender;

    @Async
    public void sendEmailSimpleMailMessage(EmailDTO emailDTO) {
        try {
            log.info("toEmail: {} subJect: {} emailMessage: {}", emailDTO.to(), emailDTO.subject(), emailDTO.message());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(emailDTO.from());
            mailMessage.setTo(emailDTO.to());
            mailMessage.setSubject(emailDTO.subject());
            mailMessage.setText(emailDTO.message());
            mailSender.send(mailMessage);
        } catch (MailException e) {
            log.error("Exception".concat(e.getMessage()));
        }
    }

    @Async
    public void sendEmailMimeMessageHelper(EmailDTO emailDTO) {
        try {
            log.info("toEmail: {} subJect: {} emailMessage: {}", emailDTO.to(), emailDTO.subject(), emailDTO.message());
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom("lebuffet02@gmail.com", "Lucas Buffet");
            helper.setTo(emailDTO.to());
            helper.setSubject(emailDTO.subject());
            helper.setText(emailDTO.message());
            mailSender.send(message);
        } catch (MailException | MessagingException | UnsupportedEncodingException e) {
            log.error("Exception".concat(e.getMessage()));
        }
    }
}
