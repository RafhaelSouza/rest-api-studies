package com.studies.foodorders.infrastructure.services;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FakeEmailSendingService extends SmtpEmailSendingService {

	@Override
	public void send(Message message) {

		String body = templateProcess(message);

		log.info("[FAKE E-MAIL] To: {}\n{}", message.getRecipients(), body);
	}

}