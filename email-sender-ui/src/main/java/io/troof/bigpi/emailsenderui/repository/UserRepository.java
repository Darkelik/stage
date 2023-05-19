package io.troof.bigpi.emailsenderui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.troof.bigpi.emailsenderui.resource.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  
}
