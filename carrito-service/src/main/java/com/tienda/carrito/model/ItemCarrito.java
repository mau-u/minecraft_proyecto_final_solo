package com.tienda.carrito.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long skinId;

    private String nombreSkin;

    private Double precio;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "carrito_id")
    @JsonIgnore
    private Carrito carrito;
}