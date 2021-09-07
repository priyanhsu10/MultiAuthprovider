package multipleauthprovider.example.multipleauthprovider.security.manger;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
@Component
public class TokenManager {
    private final Set<String> tokens;

    public TokenManager() {
        this.tokens = new HashSet<>();
    }

    public boolean contains(String token){
        return  this.tokens.contains(token);
    }

    public void addToken(String token) {
        this.tokens.add(token);
    }
}
