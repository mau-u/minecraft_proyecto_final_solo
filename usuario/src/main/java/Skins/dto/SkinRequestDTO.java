package Skins.dto;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SkinRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;

    @NotNull(message = "La disponibilidad es obligatoria")
    private Boolean disponible;
}
