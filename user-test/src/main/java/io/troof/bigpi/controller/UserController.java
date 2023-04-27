package io.troof.bigpi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.troof.bigpi.model.User;

@RestController
public class UserController {

	private List<User> users = createList();

	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = "application/json")
	public List<User> firstPage() {
		return users;
	}

	@DeleteMapping(path = { "/{id}" })
	public User delete(@PathVariable("id") String id) {
		User deletedEmp = null;
		for (User emp : users) {
			if (emp.getUserId().equals(id)) {
				users.remove(emp);
				deletedEmp = emp;
				break;
			}
		}
		return deletedEmp;
	}

	@PostMapping
	public User create(@RequestBody User user) {
		users.add(user);
		System.out.println(users);
		return user;
	}

	private static List<User> createList() {
		List<User> tempEmployees = new ArrayList<User>();
		User user1 = new User();
		user1.setUsername("user1");
		user1.setUserId("1");

		User user2 = new User();
		user2.setUsername("user2");
		user2.setUserId("2");
		tempEmployees.add(user1);
		tempEmployees.add(user2);
		return tempEmployees;
	}
}

