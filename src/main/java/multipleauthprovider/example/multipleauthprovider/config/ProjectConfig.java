package multipleauthprovider.example.multipleauthprovider.config;

import multipleauthprovider.example.multipleauthprovider.security.filters.UsernamePasswordAuthFilter;
import multipleauthprovider.example.multipleauthprovider.security.providers.OtpAuthProvider;
import multipleauthprovider.example.multipleauthprovider.security.providers.UserNameAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserNameAuthProvider userNameAuthProvider;
    @Autowired
    private OtpAuthProvider otpAuthProvider;
    @Autowired
    private UsernamePasswordAuthFilter usernamePasswordAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(userNameAuthProvider)
                .authenticationProvider(otpAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(usernamePasswordAuthFilter, BasicAuthenticationFilter.class);
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/createUser")
                .permitAll()
                .anyRequest()
                .authenticated();
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
