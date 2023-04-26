package io.troof.bigpi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.troof.bigpi.model.User;

@RestController
public class UserController {
	private List<User> users = createList();
	
	private List<User> createList(){
		List<User> tempUsers = new ArrayList<User>();
		User user1 = new User();
		user1.setUsername("Fred");
		user1.setId(1);
		User user2 = new User();
		user2.setUsername("Eric");
		user1.setId(2);
		tempUsers.add(user1);
		tempUsers.add(user2);
		return tempUsers;
	}
	
	@RequestMapping(value = "/users",method = RequestMethod.GET,produces = "application/json")
	public List<User> firstPage(){
		return users;
	}
	
	@DeleteMapping(path = {"/{id}"})
	public User delete(@PathVariable("id") int id) {
		User deletedUser = null;
		for (User user : users) {
			if (user.getId() == id) {
				users.remove(user);
				deletedUser = user;
				break;
			}
		}
		return deletedUser;
	}
	
	@PostMapping
	public User create(@RequestBody User user) {
		users.add(user);
		System.out.println(user);
		return user;
	}

}
