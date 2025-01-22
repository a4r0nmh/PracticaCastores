package com.example.castores.repository;
import com.example.castores.model.Producto;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findAll();

    List<Producto> findAllByEstatus(int estatus);

    List<Producto> findById(int idproducto);

    @Modifying
    @Transactional
    @Query("UPDATE Producto p SET p.cantidad = :cantidad WHERE p.idproducto = :idproducto")
    int actualizarProducto(@Param("idproducto") int idproducto, @Param("cantidad") int cantidad);

    @Modifying
    @Transactional
    @Query("UPDATE Producto p SET p.estatus = :estatus WHERE p.idproducto = :idproducto")
    int actualizarProductoEstatus(@Param("idproducto") int idproducto, @Param("estatus") int estatus);
}
