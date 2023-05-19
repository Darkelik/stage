package io.troof.bigpi.emailsenderui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.troof.bigpi.emailsenderui.resource.Connection;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long> {
  
}
