package io.troof.bigpi.emailsenderregistration.controller;

import io.troof.bigpi.emailsenderregistration.entity.User;
import io.troof.bigpi.emailsenderregistration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Controller for user. */
@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody User user) {
    return userService.saveUser(user);
  }

  @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
  public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
    return userService.confirmEmail(confirmationToken);
  }

}
