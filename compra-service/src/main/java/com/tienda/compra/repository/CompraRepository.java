package com.tienda.compra.repository;

import com.tienda.compra.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraRepository
        extends JpaRepository<Compra, Long> {
}