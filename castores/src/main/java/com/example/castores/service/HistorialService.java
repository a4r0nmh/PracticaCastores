package com.example.castores.service;

import com.example.castores.model.Historial;
import com.example.castores.model.Producto;
import com.example.castores.model.Usuario;
import com.example.castores.repository.HistorialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HistorialService {

    @Autowired
    private HistorialRepository historialRepository;

    public List<Historial> obtenerTodoElHistorial() {
        return historialRepository.findAllWithRelations();
    }

    public void guardarHistorial(Producto producto, Usuario usuario) {
        Historial historial = new Historial();

        if (usuario.getIdrol() == 1) {
            historial.setMovimiento(Historial.Movimiento.Entrada);
        } else {
            historial.setMovimiento(Historial.Movimiento.Salida);
        }

        historial.setFecha(LocalDateTime.now());
        historial.setUsuario(usuario);
        historial.setProducto(producto);

        historialRepository.save(historial);
    }
}
