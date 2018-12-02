package report.service.v3.security.repository;

import report.service.v3.model.security.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByEmail(String email);
}