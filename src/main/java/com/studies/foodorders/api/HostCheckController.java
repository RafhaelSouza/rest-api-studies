package com.studies.foodorders.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class HostCheckController {

	@GetMapping("/host-check")
	public String hostCheck() throws UnknownHostException {
		return "Host Address: " + InetAddress.getLocalHost().getHostAddress()
				+ " - Host Name: " + InetAddress.getLocalHost().getHostName();
	}
	
}
