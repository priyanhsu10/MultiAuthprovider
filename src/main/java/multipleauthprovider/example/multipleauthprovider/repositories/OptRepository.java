package multipleauthprovider.example.multipleauthprovider.repositories;

import multipleauthprovider.example.multipleauthprovider.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OptRepository extends JpaRepository<Otp,Integer> {
    Optional<Otp> findByUsername(String userName);
}
