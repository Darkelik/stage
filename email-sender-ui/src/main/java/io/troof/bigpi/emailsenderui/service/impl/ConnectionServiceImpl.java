package io.troof.bigpi.emailsenderui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import io.troof.bigpi.emailsenderui.repository.ConnectionRepository;
import io.troof.bigpi.emailsenderui.service.ConnectionService;

public class ConnectionServiceImpl implements ConnectionService {
	
	@Autowired
	private ConnectionRepository repository;
	
	public ConnectionServiceImpl() {
		
	}

}
