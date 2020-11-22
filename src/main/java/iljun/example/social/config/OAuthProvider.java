package iljun.example.social.config;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Arrays;

public enum OAuthProvider {

    KAKAO {
        @Override
        public ClientRegistration.Builder getBuilder(String clientId, String clientSecret, String[] scopes) {
            return getBuilder("kakao", ClientAuthenticationMethod.POST)
                    .scope(scopes)
                    .authorizationUri("https://kauth.kakao.com/oauth/authorize")
                    .tokenUri("https://kauth.kakao.com/oauth/token")
                    .userInfoUri("https://kapi.kakao.com/v2/user/me")
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .userNameAttributeName("id")
                    .clientName("kakao");
        }
    };

    private static final String DEFAULT_LOGIN_REDIRECT_URL = "{baseUrl}/login/oauth2/code/{registrationId}";

    protected final ClientRegistration.Builder getBuilder(String registrationId,
                                                          ClientAuthenticationMethod method) {

        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUri(OAuthProvider.DEFAULT_LOGIN_REDIRECT_URL);
        return builder;
    }

    public abstract ClientRegistration.Builder getBuilder(String clientId, String clientSecret, String[] scopes);

    public static OAuthProvider findOAuthProvider(String registrationId) {
        return Arrays.stream(values())
                .filter(oAuthProvider -> oAuthProvider.name().equalsIgnoreCase(registrationId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("not found oauthProvider"));
    }
}
