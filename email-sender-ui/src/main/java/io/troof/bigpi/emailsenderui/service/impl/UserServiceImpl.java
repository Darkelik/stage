package io.troof.bigpi.emailsenderui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.troof.bigpi.emailsenderui.repository.UserRepository;
import io.troof.bigpi.emailsenderui.resource.SmallConnection;
import io.troof.bigpi.emailsenderui.resource.User;
import io.troof.bigpi.emailsenderui.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository repository;
	
	private User connectedUser;
	
	public UserServiceImpl() {
		connectedUser = null;
	}
	
	public void register(User user) {
		repository.save(user);
	}
	
	public boolean connect(SmallConnection connection) {
		List<User> users = repository.findAll();
		for (User user : users) {
			if (user.getEmail().equals(connection.getEmail()) && user.getPassword().equals(connection.getPassword())) {
				connectedUser = user;
				return true;
			}
		}
		return false;
	}
	
	public void disconnect() {
		connectedUser = null;
	}
	
	public User getConnectedUser() {
		return connectedUser;
	}

}
