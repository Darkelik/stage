package io.troof.bigpi.emailsenderdatabase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.troof.bigpi.emailsenderdatabase.resource.EmailMessage;

@Repository
public interface EmailRepository extends JpaRepository<EmailMessage, Long>{
	
}
