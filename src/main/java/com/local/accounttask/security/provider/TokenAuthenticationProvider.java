package com.local.accounttask.security.provider;

import com.local.accounttask.models.Token;
import com.local.accounttask.repositories.TokensRepository;
import com.local.accounttask.security.token.TokenAuthentication;
import com.local.accounttask.security.details.ClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokensRepository tokenRepository;

    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;

        Optional<Token> tokenCandidate = tokenRepository.findOneByValue(tokenAuthentication.getName());
        if (tokenCandidate.isPresent()) {
            UserDetails userDetails = clientDetailsService.loadUserByUsername(tokenCandidate.get().getClient().getClientLogin());
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
            return tokenAuthentication;
        } else throw new IllegalArgumentException("bad token");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
