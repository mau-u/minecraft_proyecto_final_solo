package Skins.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "skins")
@Getter
@Setter
@NoArgsConstructor
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
}