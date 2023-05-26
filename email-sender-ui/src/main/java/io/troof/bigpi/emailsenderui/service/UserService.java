package io.troof.bigpi.emailsenderui.service;

import io.troof.bigpi.emailsenderui.resource.SmallConnection;
import io.troof.bigpi.emailsenderui.resource.User;

/** User service interface. */
public interface UserService {
  public void register(User user);
  
  public boolean connect(SmallConnection connection);
  
  public void disconnect();
  
  public User getConnectedUser();
}
