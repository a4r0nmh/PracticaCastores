package com.example.castores.controller;

import org.springframework.stereotype.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrController implements ErrorController {
    @RequestMapping("/error")
    public String handleError() {
        return "redirect:/auth/home";
    }
}
