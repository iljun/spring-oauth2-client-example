package iljun.example.social.domain.account;

import iljun.example.social.config.OAuthProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByOauthProviderAndProviderId(OAuthProvider oAuthProvider, String providerId);
}
