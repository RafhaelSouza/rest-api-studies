package com.studies.foodorders.infrastructure.services;

import com.studies.foodorders.domain.services.email.EmailSendingService;
import com.studies.foodorders.infrastructure.exceptions.EmailException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Component
public class EmailProcessorTemplate {

	@Autowired
	private Configuration freemarkerConfig;

	protected String templateProcess(EmailSendingService.Message message) {
		try {
			Template template = freemarkerConfig.getTemplate(message.getBody());

			return FreeMarkerTemplateUtils.processTemplateIntoString(
					template, message.getModels());
		} catch (Exception e) {
			throw new EmailException("Unable to assemble email template", e);
		}
	}
	
}
