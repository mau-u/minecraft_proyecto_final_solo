package Skins.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "skins")
@Getter
@Setter
@AllArgsConstructor
public class Skin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String urlTextura;

    @Column(nullable = false)
    private String tipoModelo;

    @Column(nullable = false)
    private String rareza;

    @Column(nullable = false)
    private Double precio;


    public Skin() {
    }

    public void setCategoria(@NotBlank(message = "La categoría es obligatoria") String categoria) {
    }

    public void setDisponible(@NotNull(message = "La disponibilidad es obligatoria") Boolean disponible) {

    }
}
