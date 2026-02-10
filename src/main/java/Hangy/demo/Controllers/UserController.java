package Hangy.demo.Controllers;

import Hangy.demo.Entities.User;
import Hangy.demo.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Méthode simple login (backend sans JWT pour l'instant)
    @PostMapping("/login")
    public User login(@RequestBody User loginData) {
        Optional<User> user = userService.findByEmail(loginData.getEmail());
        if(user.isPresent() && user.get().getPasswordHash().equals(loginData.getPasswordHash())){
            return user.get();
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}