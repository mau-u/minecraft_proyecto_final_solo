package com.tienda.wishlist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishlistDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long skinId;

    @NotBlank
    private String nombreSkin;

    @NotNull
    @Positive
    private Double precioDeseado;
}