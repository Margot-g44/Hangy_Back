
package Hangy.demo.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {

    @NotBlank(message = "Le nom d'utilisateur est obligatoire.")
    @Size(max = 50, message = "Le nom d'utilisateur ne doit pas dépasser 50 caractères.")
    private String username;

    @NotBlank(message = "L'adresse e-mail est obligatoire.")
    @Email(message = "L'adresse e-mail n'est pas valide.")
    @Size(max = 255, message = "L'adresse e-mail est trop longue.")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 12, max = 72,
            message = "Le mot de passe doit contenir entre 12 et 72 caractères.")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
