package com.studies.foodorders.infrastructure.services;

import com.studies.foodorders.core.email.EmailProperties;
import com.studies.foodorders.domain.services.email.EmailSendingService;
import com.studies.foodorders.infrastructure.exceptions.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

public class SmtpEmailSendingService implements EmailSendingService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailProperties emailProperties;

    @Autowired
    private EmailProcessorTemplate emailProcessorTemplate;

    @Override
    public void send(Message message) {
        try {
            MimeMessage mimeMessage = createMimeMessage(message);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new EmailException("Unable to send email", e);
        }
    }

    protected MimeMessage createMimeMessage(Message message) throws MessagingException {
        String body = emailProcessorTemplate.templateProcess(message);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
        helper.setFrom(emailProperties.getSender());
        helper.setTo(message.getRecipients().toArray(new String[0]));
        helper.setSubject(message.getSubject());
        helper.setText(body, true);

        return mimeMessage;
    }

}
