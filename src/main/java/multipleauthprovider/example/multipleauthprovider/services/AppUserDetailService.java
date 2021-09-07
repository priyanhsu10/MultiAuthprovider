package multipleauthprovider.example.multipleauthprovider.services;


import multipleauthprovider.example.multipleauthprovider.entities.User;
import multipleauthprovider.example.multipleauthprovider.repositories.UserRepository;
import multipleauthprovider.example.multipleauthprovider.security.model.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {
    private final UserRepository repository;

    public AppUserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s)  {
        User user = repository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException("User not found"));
    return  new AppUser(user);
    }
}
