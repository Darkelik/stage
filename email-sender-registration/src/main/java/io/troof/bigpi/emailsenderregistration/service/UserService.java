package io.troof.bigpi.emailsenderregistration.service;

import io.troof.bigpi.emailsenderregistration.entity.User;
import org.springframework.http.ResponseEntity;

/** Interface for user service. */
public interface UserService {

  ResponseEntity<?> saveUser(User user);

  ResponseEntity<?> confirmEmail(String confirmationToken);
}
