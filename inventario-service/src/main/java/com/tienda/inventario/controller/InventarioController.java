package com.tienda.inventario.controller;

import com.tienda.inventario.dto.ReducirStockDTO;
import com.tienda.inventario.model.Inventario;
import com.tienda.inventario.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    @PostMapping
    public ResponseEntity<Inventario> guardar(@Valid @RequestBody Inventario inventario) {
        return ResponseEntity.ok(inventarioService.guardar(inventario));
    }

    @PutMapping("/reducir")
    public ResponseEntity<String> reducirStock(
            @Valid @RequestBody ReducirStockDTO dto
    ) {
        inventarioService.reducirStock(dto.getProductoId(), dto.getCantidad());
        return ResponseEntity.ok("Stock reducido correctamente");
    }
}