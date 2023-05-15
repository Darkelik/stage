package io.troof.bigpi.emailsenderimplementation.repository;

import io.troof.bigpi.emailsenderimplementation.model.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repository containing emails sent using the application. */
@Repository
public interface EmailRepository extends JpaRepository<EmailMessage, Long> {
  
}
