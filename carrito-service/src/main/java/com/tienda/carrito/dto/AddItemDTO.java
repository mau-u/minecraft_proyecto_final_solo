package com.tienda.carrito.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddItemDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long skinId;

    @NotBlank
    private String nombreSkin;

    @NotNull
    @Min(1)
    private Integer cantidad;

    @NotNull
    @Positive
    private Double precio;
}