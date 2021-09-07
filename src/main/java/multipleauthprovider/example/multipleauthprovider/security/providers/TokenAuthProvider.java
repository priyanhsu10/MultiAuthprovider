package multipleauthprovider.example.multipleauthprovider.security.providers;

import multipleauthprovider.example.multipleauthprovider.security.authontications.TokenAuthentication;
import multipleauthprovider.example.multipleauthprovider.security.manger.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TokenAuthProvider implements AuthenticationProvider {
    @Autowired
    private TokenManager tokenManager;
    @Override
    public Authentication authenticate(Authentication authentication) {
         String token = authentication.getName();
         if(tokenManager.contains(token)){
             return new TokenAuthentication(token,null,
                     Arrays.asList(new SimpleGrantedAuthority("READ")));
         }else {
             return authentication;
         }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return TokenAuthentication.class.equals(aClass);
    }
}
