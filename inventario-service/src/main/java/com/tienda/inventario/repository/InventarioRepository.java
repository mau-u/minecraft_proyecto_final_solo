package com.tienda.inventario.repository;

import com.tienda.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    Optional<Inventario> findBySkinId(Long skinId);
}