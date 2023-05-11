package io.troof.bigpi.emailsenderimplementation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.troof.bigpi.emailsenderimplementation.model.EmailMessage;

@Repository
public interface EmailRepository extends JpaRepository<EmailMessage, Long>{
	
}
