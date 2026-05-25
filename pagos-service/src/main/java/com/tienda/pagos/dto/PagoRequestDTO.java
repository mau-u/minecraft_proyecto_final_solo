package com.tienda.pagos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagoRequestDTO {

    @NotNull
    private Long compraId;

    @NotNull
    @Positive
    private Double monto;

    @NotBlank
    private String metodoPago;
}