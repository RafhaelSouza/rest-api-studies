package com.studies.foodorders.domain.services.email;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

public interface EmailSendingService {

	void send(Message message);
	
	@Getter
	@Builder
	class Message {
		
		private Set<String> recipients;
		private String subject;
		private String body;
		
	}
	
}