package com.tienda.favoritos.controller;

import com.tienda.favoritos.dto.FavoritoDTO;
import com.tienda.favoritos.model.Favorito;
import com.tienda.favoritos.service.FavoritoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    private final FavoritoService service;

    public FavoritoController(FavoritoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Favorito> agregar(
            @Valid @RequestBody FavoritoDTO dto
    ) {
        return ResponseEntity.ok(service.agregar(dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Favorito>> listar(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(service.listar(userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}