package com.tienda.notificaciones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificacionDTO {

    @NotNull
    private Long userId;

    @NotBlank
    private String mensaje;
}