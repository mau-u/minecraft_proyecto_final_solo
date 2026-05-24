package com.tienda.inventario.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReducirStockDTO {

    @NotNull
    private Long skinId;

    @NotNull
    @Min(1)
    private Integer cantidad;
}