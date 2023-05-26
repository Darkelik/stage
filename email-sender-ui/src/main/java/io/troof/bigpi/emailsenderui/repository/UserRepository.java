package io.troof.bigpi.emailsenderui.repository;

import io.troof.bigpi.emailsenderui.resource.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repository containing users registered in the application. */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
}
