package iljun.example.social.domain.account;

import iljun.example.social.config.OAuthProvider;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email")
    private String email;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    @Getter
    private OAuthProvider oauthProvider;

    @Column(name = "provider_id")
    private String providerId;

    @Builder
    public Account(String email,
                   OAuthProvider oAuthProvider,
                   String providerId) {
        this.email = email;
        this.oauthProvider = oAuthProvider;
        this.providerId = providerId;
    }
}
