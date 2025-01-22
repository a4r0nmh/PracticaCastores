package com.example.castores.controller;

import com.example.castores.model.Usuario;
import com.example.castores.service.AuthService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("error", "");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String contraseña, HttpSession session, Model model) {

        if (authService.authenticate(correo, contraseña, session)) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            if (usuario.getIdrol() == 1) {
                return "/home";
            } else {
                return "/homeal";
            }

        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "¡Bienvenido a la página principal!");
        return "home";
    }

    @GetMapping("/homeal")
    public String homemeal(Model model) {
        model.addAttribute("message", "¡Bienvenido a la página principal!");
        return "homemeal";
    }
}
