package com.tienda.pagos.repository;

import com.tienda.pagos.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}