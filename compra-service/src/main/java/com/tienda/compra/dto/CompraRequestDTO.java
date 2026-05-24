package com.tienda.compra.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CompraRequestDTO {

    @NotNull(message = "El usuario es obligatorio")
    private Long usuarioId;

    private List<DetalleRequestDTO> detalles;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<DetalleRequestDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleRequestDTO> detalles) {
        this.detalles = detalles;
    }
}