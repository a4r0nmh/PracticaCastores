package com.example.castores.repository;

import com.example.castores.model.Historial;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Integer> {
    List<Historial> findAll();

    @Query(value = "SELECT h.idhistorial, h.movimiento, h.fecha, h.idusuario as usuarioid, h.idproducto as productoid,\n" +
            "u.idusuario, u.nombre as unombre, u.correo, u.contraseña, u.estatus as ustatus, u.idrol as rolid,\n" +
            "p.idproducto, p.nombre as pnombre, p.precio, p.cantidad, p.estatus as pstatus\n" +
            "FROM historial h JOIN usuario u ON h.idusuario = u.idusuario JOIN producto p ON h.idproducto = p.idproducto;", nativeQuery = true)
    List<Historial> findAllWithRelations();
}
