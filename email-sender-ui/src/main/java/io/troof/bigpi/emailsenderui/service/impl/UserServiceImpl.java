package io.troof.bigpi.emailsenderui.service.impl;

import io.troof.bigpi.emailsenderui.repository.UserRepository;
import io.troof.bigpi.emailsenderui.resource.SmallConnection;
import io.troof.bigpi.emailsenderui.resource.User;
import io.troof.bigpi.emailsenderui.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** User service implementer. */
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

  /** Connecting a user. */
  public boolean connect(SmallConnection connection) {
    List<User> users = repository.findAll();
    String email = connection.getEmail();
    String password = connection.getPassword();
    for (User user : users) {
      if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
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

  public void setRepository(UserRepository repository) {
    this.repository = repository;
  }

  public void setConnectedUser(User user) {
    this.connectedUser = user;
  }
}
