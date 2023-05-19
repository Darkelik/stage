package io.troof.bigpi.emailsenderui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.troof.bigpi.emailsenderui.resource.EmailMessage;

/** Repository containing emails sent using the application. */
@Repository
public interface EmailRepository extends JpaRepository<EmailMessage, Long> {
  
}
