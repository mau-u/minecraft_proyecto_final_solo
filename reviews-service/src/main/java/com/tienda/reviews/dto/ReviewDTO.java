package com.tienda.reviews.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long skinId;

    @NotBlank
    private String comentario;

    @NotNull
    @Min(1)
    @Max(5)
    private Integer puntuacion;
}