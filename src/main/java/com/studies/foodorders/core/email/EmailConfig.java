package com.studies.foodorders.core.email;

import com.studies.foodorders.domain.services.email.EmailSendingService;
import com.studies.foodorders.infrastructure.services.FakeEmailSendingService;
import com.studies.foodorders.infrastructure.services.SmtpEmailSendingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public EmailSendingService emailSendingService() {

		switch (emailProperties.getImpl()) {
			case FAKE:
				return new FakeEmailSendingService();
			case SMTP:
				return new SmtpEmailSendingService();
			default:
				return null;
		}
	}

}