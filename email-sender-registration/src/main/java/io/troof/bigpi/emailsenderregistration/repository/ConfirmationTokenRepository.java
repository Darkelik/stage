package io.troof.bigpi.emailsenderregistration.repository;

import io.troof.bigpi.emailsenderregistration.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Interface for confirmation token repository. */
@Repository("confirmationTokenRepository")
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
  ConfirmationToken findByConfirmationToken(String confirmationToken);
}
