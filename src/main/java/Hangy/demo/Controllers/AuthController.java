
package Hangy.demo.Controllers;

import Hangy.demo.DTOs.RegisterRequest;
import Hangy.demo.Services.UserService;
import Hangy.demo.Entities.User;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        if (userService.authenticateUser(
                request.getUsername(),
                request.getPassword()
        ).isPresent()) {
            return ResponseEntity.ok("Connexion réussie !");
        }

        return ResponseEntity.status(401).body("Identifiants invalides");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {

        User user = userService.registerUser(
                request.getUsername(),
                request.getEmail(),
                request.getPassword()
        );

        return ResponseEntity.status(201)
                .body("Utilisateur créé : " + user.getUsername());
    }

    // DTO utilisé uniquement pour le login temporaire
    static class AuthRequest {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
