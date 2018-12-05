package report.service.v3.security.repository;

import report.service.v3.model.security.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {
    Merchant findByEmail(String email);
}