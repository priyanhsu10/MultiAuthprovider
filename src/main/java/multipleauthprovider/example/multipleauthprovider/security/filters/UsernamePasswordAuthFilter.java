package multipleauthprovider.example.multipleauthprovider.security.filters;


import multipleauthprovider.example.multipleauthprovider.entities.Otp;
import multipleauthprovider.example.multipleauthprovider.repositories.OptRepository;
import multipleauthprovider.example.multipleauthprovider.security.authontications.OtpAuthentication;
import multipleauthprovider.example.multipleauthprovider.security.authontications.UserNamePasswordAuthentication;
import multipleauthprovider.example.multipleauthprovider.security.manger.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

public class UsernamePasswordAuthFilter extends OncePerRequestFilter {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private OptRepository repository;
    @Autowired
    private TokenManager tokenManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        //step1 :username & password
        //step2 :username & otp
        String username = request.getHeader("username");
        String password = request.getHeader("password");
        String otp = request.getHeader("otp");
        if (otp == null) {
            //step 2
            Authentication auth = new UserNamePasswordAuthentication(username, password);
            auth = authenticationManager.authenticate(auth);
            if (auth.isAuthenticated()) {

                String code = createOpt(username);
                response.setHeader("otpcode", code);

            } else {
                response.setStatus(400);

            }

        } else {
            //step 2
            Authentication auth = new OtpAuthentication(username, otp);
            auth = authenticationManager.authenticate(auth);
            String token = UUID.randomUUID().toString();
            tokenManager.addToken(token);
            response.setHeader("Authorization", token);
            SecurityContextHolder.getContext().setAuthentication(auth);


        }


    }

    private String createOpt(String username) {
        Optional<Otp> existing = repository.findByUsername(username);
        String code = String.valueOf(new Random().nextInt(9999) + 1000);
        Otp otpEntity = new Otp();
        if (existing.isPresent()) {
            otpEntity = existing.get();
            otpEntity.setOptCode(code);
            repository.save(otpEntity);
        } else {

            otpEntity.setUsername(username);

            otpEntity.setOptCode(code);
        }
        repository.save(otpEntity);
        return code;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
