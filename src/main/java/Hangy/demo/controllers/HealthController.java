package Hangy.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")  // permet Angular dev
@RestController
public class HealthController {

    @GetMapping("/health")
    public String health() {
        return "Hangy backend is running";
    }
}
