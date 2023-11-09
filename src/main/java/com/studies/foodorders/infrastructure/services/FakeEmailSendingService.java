package com.studies.foodorders.infrastructure.services;

import com.studies.foodorders.domain.services.email.EmailSendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FakeEmailSendingService implements EmailSendingService {

	@Autowired
	private EmailProcessorTemplate emailProcessorTemplate;

	@Override
	public void send(Message message) {

		String body = emailProcessorTemplate.templateProcess(message);

		log.info("[FAKE E-MAIL] To: {}\n{}", message.getRecipients(), body);
	}

}