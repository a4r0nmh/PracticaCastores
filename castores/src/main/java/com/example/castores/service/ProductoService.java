package com.example.castores.service;

import com.example.castores.model.Usuario;
import com.example.castores.repository.ProductoRepository;
import com.example.castores.model.Producto;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private HistorialService historialService;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    public List<Producto> obtenerTodosPorEstatus(int estatus) {
        return productoRepository.findAllByEstatus(estatus);
    }

    public List<Producto> obtenerPorId(int idproducto) {
        return productoRepository.findById(idproducto);
    }

    public void actualizarProducto(int idproducto, int cantidad) {
        int filasAfectadas = productoRepository.actualizarProducto(idproducto, cantidad);

        if (filasAfectadas == 0) {
            throw new RuntimeException("No se encontró el producto con el ID especificado");
        }
    }

    @Transactional
    public Producto agregarProducto(Producto producto, Usuario usuario) {

        Producto productoS = productoRepository.save(producto);
        
        if (producto.getCantidad() > 0) {
            historialService.guardarHistorial(producto, usuario);
        }

        return productoS;
    }
}
