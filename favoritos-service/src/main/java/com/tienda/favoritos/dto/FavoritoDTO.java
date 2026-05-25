package com.tienda.favoritos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoritoDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long skinId;

    @NotBlank
    private String nombreSkin;
}