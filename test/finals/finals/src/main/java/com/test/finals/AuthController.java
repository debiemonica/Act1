package com.test.finals;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        if ("admin".equals(username) && "admin".equals(password)) {
            return "redirect:/coffees";
        }
        // Optionally add an error message to the model here
        return "login";
    }


    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}