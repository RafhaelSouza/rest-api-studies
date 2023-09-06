package com.studies.foodorders.infrastructure.services;

import com.studies.foodorders.core.email.EmailProperties;
import com.studies.foodorders.domain.services.email.EmailSendingService;
import com.studies.foodorders.infrastructure.exceptions.EmailException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.internet.MimeMessage;

public class SmtpEmailSendingService implements EmailSendingService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailProperties emailProperties;

	@Autowired
	private Configuration freemarkerConfig;
	
	@Override
	public void send(Message message) {
		try {
			String body = templateProcess(message);

			MimeMessage mimeMessage = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getSender());
			helper.setTo(message.getRecipients().toArray(new String[0]));
			helper.setSubject(message.getSubject());
			helper.setText(body, true);
			
			mailSender.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Unable to send email", e);
		}
	}

	protected String templateProcess(Message message) {
		try {
			Template template = freemarkerConfig.getTemplate(message.getBody());

			return FreeMarkerTemplateUtils.processTemplateIntoString(
					template, message.getModels());
		} catch (Exception e) {
			throw new EmailException("Unable to assemble email template", e);
		}
	}

}
