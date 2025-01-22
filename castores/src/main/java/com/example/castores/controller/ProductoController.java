package com.example.castores.controller;

import com.example.castores.model.Producto;
import com.example.castores.model.Usuario;
import com.example.castores.service.HistorialService;
import com.example.castores.service.ProductoService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private HistorialService historialService;

    @GetMapping("/listar")
    public String listarProducto(Model model, HttpSession session) {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        model.addAttribute("productos", productos);

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario.getIdrol() == 1) {
            return "producto";
        } else {
            return "productoal";
        }
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        return "frmProducto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(HttpSession session, @ModelAttribute Producto producto) {

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        productoService.agregarProducto(producto, usuario);

        return "redirect:/producto/listar";
    }

    @GetMapping("/filtrar")
    public String filtrarPorEstatus(Model model) {
        List<Producto> productos = productoService.obtenerTodosPorEstatus(1);
        model.addAttribute("productos", productos);

        return "salida";
    }

    @PostMapping("/editar")
    public String editarProducto(@ModelAttribute Producto producto, HttpSession session) {
        productoService.actualizarProducto(producto.getIdproducto(), producto.getCantidad());

        Usuario usuario = (Usuario) session.getAttribute("usuario");

        historialService.guardarHistorial(producto, usuario);

        return "redirect:/producto/listar";
    }

    @PostMapping("/estatus")
    public String editarProducto(@ModelAttribute Producto producto) {
        productoService.actualizarProductoEstatus(producto.getIdproducto(), producto.getEstatus());

        return "redirect:/producto/listar";
    }
}
