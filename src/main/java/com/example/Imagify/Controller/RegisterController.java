package com.example.Imagify.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {
    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    @GetMapping("/")
    public String seeHomepage() {
        return "index";
    }
}
