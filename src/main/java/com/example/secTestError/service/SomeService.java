package com.example.secTestError.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class SomeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SomeService.class);
	
	@Autowired
	private AuthenticationManager authManager;
	
	public void doSomething(){
		LOGGER.debug("authManager: {}", authManager.toString());
	}

}
