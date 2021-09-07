package multipleauthprovider.example.multipleauthprovider.security.authontications;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OtpAuthentication extends  UserNamePasswordAuthentication{
    public OtpAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public OtpAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
