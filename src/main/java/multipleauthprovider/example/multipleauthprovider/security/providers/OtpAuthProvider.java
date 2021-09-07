package multipleauthprovider.example.multipleauthprovider.security.providers;

import multipleauthprovider.example.multipleauthprovider.entities.Otp;
import multipleauthprovider.example.multipleauthprovider.repositories.OptRepository;
import multipleauthprovider.example.multipleauthprovider.security.authontications.OtpAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class OtpAuthProvider implements AuthenticationProvider  {
    @Autowired
    private OptRepository repository;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName= authentication.getName();
        String otp= (String) authentication.getCredentials();
      Otp otp1  =repository.findByUsername(userName)
              .orElseThrow(()->new BadCredentialsException("Invalid Opt"));
      if(otp1.getOptCode().equals(otp)){
          return  new OtpAuthentication(userName,otp, Arrays.asList(new SimpleGrantedAuthority("READ")));
      }else {
          return  authentication;
      }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return OtpAuthentication.class.equals(aClass);
    }
}
