package io.troof.bigpi.emailsenderui.repository;

import io.troof.bigpi.emailsenderui.resource.AutoEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Repository containing auto emails sent using the application. */
@Repository
public interface EmailRepository extends JpaRepository<AutoEmail, String> {
  
}
