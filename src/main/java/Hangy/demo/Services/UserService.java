package Hangy.demo.Services;

import Hangy.demo.Entities.User;
import Hangy.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        // Ici pour hasher le password
        return userRepository.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            if(updatedUser.getPasswordHash() != null){
                user.setPasswordHash(updatedUser.getPasswordHash());
            }
            return userRepository.save(user);
        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "User not found"
        ));
    }

    public User registerUser(String username, String email, String rawPassword) {

        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Cette adresse e-mail est déjà utilisée."
            );
        }

        if (userRepository.existsByUsername(username)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ce nom d'utilisateur est déjà utilisé."
            );
        }

        User user = new User();

        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(passwordEncoder.encode(rawPassword));

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> authenticateUser(String usernameOrEmail, String rawPassword) {
        Optional<User> user = userRepository.findByUsername(usernameOrEmail);

    if (user.isEmpty()) {
        user = userRepository.findByEmail(usernameOrEmail);
    }


    if (user.isPresent()
            && passwordEncoder.matches(
    rawPassword,
            user.get().getPasswordHash())) {
        return user;
    }

    // Identifiant ou mot de passe incorrect
    return Optional.empty();
}



}
