package multipleauthprovider.example.multipleauthprovider.security.providers;


import multipleauthprovider.example.multipleauthprovider.security.authontications.UserNamePasswordAuthentication;
import multipleauthprovider.example.multipleauthprovider.services.AppUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserNameAuthProvider  implements AuthenticationProvider {
    @Autowired
    private AppUserDetailService appUserDetailService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails user = appUserDetailService.loadUserByUsername(userName);
        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UserNamePasswordAuthentication(userName,password,user.getAuthorities());
        }else{
            return   authentication;
        }
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return UserNamePasswordAuthentication.class.equals(aClass);
    }
}
