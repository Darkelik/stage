package io.troof.bigpi.emailsenderregistration.repository;

import io.troof.bigpi.emailsenderregistration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Interface for user repository. */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUserEmailIgnoreCase(String emailId);
  
  Boolean existsByUserEmail(String email);
}
