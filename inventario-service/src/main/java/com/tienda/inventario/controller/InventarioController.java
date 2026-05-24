package com.tienda.inventario.controller;

import com.tienda.inventario.dto.ReducirStockDTO;
import com.tienda.inventario.model.Inventario;
import com.tienda.inventario.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioService service;

    public InventarioController(InventarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Inventario> crear(@RequestBody Inventario inventario) {
        return ResponseEntity.ok(service.crear(inventario));
    }

    @GetMapping("/{skinId}")
    public ResponseEntity<Inventario> obtener(@PathVariable Long skinId) {
        return ResponseEntity.ok(service.obtenerPorSkinId(skinId));
    }

    @PutMapping("/reducir")
    public ResponseEntity<Inventario> reducir(@Valid @RequestBody ReducirStockDTO dto) {
        return ResponseEntity.ok(service.reducirStock(dto));
    }

    @GetMapping
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Inventario OK");
    }
}