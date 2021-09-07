package multipleauthprovider.example.multipleauthprovider.contrllers;


import multipleauthprovider.example.multipleauthprovider.entities.User;
import multipleauthprovider.example.multipleauthprovider.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final UserRepository repository;
    private  final PasswordEncoder passwordEncoder;

    public HelloController(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String hello() {
        return "Hello";
    }

    @PostMapping("/createUser")
    public void createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
       repository.save(user);

    }

}
