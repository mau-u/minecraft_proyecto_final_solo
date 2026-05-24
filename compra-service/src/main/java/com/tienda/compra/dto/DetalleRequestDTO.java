package com.tienda.compra.dto;

import jakarta.validation.constraints.NotNull;

public class DetalleRequestDTO {

    @NotNull(message = "La skin es obligatoria")
    private Long skinId;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer cantidad;

    public Long getSkinId() {
        return skinId;
    }

    public void setSkinId(Long skinId) {
        this.skinId = skinId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}