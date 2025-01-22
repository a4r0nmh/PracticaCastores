package com.example.castores.service;

import com.example.castores.model.Usuario;
import com.example.castores.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean authenticate(String correo, String contraseña, HttpSession session) {

        Usuario usuario = usuarioRepository.findByCorreo(correo);

        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            session.setAttribute("usuario", usuario);

            return true;
        } else {
            return false;
        }
    }
}
