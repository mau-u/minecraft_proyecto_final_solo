package Skins.controller;

import Skins.dto.SkinRequestDTO;
import Skins.model.Skin;
import Skins.service.SkinService;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skins")
@RequiredArgsConstructor
public class SkinController {
    private final SkinService service;

    @GetMapping
    public ResponseEntity<List<Skin>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skin> buscar(@PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Skin> guardar(
            @Valid @RequestBody SkinRequestDTO dto) {

        return ResponseEntity.ok(service.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skin> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SkinRequestDTO dto) {

        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
