package multipleauthprovider.example.multipleauthprovider.security.filters;

import multipleauthprovider.example.multipleauthprovider.security.authontications.TokenAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthFilter extends OncePerRequestFilter {
   @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
      String token= httpServletRequest.getHeader("Authorization");
      if(token==null){
          filterChain.doFilter(httpServletRequest,httpServletResponse);
      }
        Authentication auth= new TokenAuthentication(token,null);
        auth= authenticationManager.authenticate(auth);
        if(auth.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(auth);
        }else {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/login");
    }
}
