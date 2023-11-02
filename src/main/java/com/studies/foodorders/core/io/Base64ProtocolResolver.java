package com.studies.foodorders.core.io;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.Base64;

public class Base64ProtocolResolver implements ProtocolResolver, 
		ApplicationListener<ApplicationContextInitializedEvent> {

	public static final int CONTENT_AFTER_RESOLVER_PROTOCOL = 7;

	@Override
	public Resource resolve(String location, ResourceLoader resourceLoader) {
		if (location.startsWith("base64:")) {
			byte[] decodedResource = Base64.getDecoder().decode(location.substring(CONTENT_AFTER_RESOLVER_PROTOCOL));
			return new ByteArrayResource(decodedResource);
		}
		
		return null;
	}

	@Override
	public void onApplicationEvent(ApplicationContextInitializedEvent event) {
		event.getApplicationContext().addProtocolResolver(this);
	}

}
