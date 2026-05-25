package com.tienda.pagos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long compraId;

    private Double monto;

    private String metodoPago;

    private String estado;

    private LocalDateTime fecha;
}