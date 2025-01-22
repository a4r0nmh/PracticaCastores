package com.example.castores.controller;

import com.example.castores.model.Historial;
import com.example.castores.service.HistorialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    private HistorialService historialService;

    @GetMapping("/listar")
    public String listarHistorial(Model model) {

        List<Historial> historial = historialService.obtenerTodoElHistorial();
        model.addAttribute("historial", historial);

        return "historial";
    }
}