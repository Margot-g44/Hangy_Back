package Hangy.demo.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody AuthRequest request) {
    if("test".equals(request.getUsername()) && "123".equals(request.getPassword())) {
        return ResponseEntity.ok("Connexion réussie !");
    }
    return ResponseEntity.status(401).body("Identifiants invalides");
}

@PostMapping("/register")
public ResponseEntity<String> register(@RequestBody AuthRequest request) {
    return ResponseEntity.ok("Utilisateur créé : " + request.getUsername());
}


// DTO simple pour recevoir username/password/email
class AuthRequest {
    private String username;
    private String email;
    private String password;

    // getters / setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
}