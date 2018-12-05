package report.service.v3.security.service;

import report.service.v3.model.security.Merchant;
import report.service.v3.security.repository.MerchantRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private MerchantRepository merchantRepository;

    public UserDetailsServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Merchant merchant = merchantRepository.findByEmail(email);
        if (merchant == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(merchant.getEmail(), merchant.getPassword(), emptyList());
    }
}