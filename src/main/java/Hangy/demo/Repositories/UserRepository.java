package Hangy.demo.Repositories;

import Hangy.demo.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    // Méthodes utiles pour l’auth ou la recherche
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
