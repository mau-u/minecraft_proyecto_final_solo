package com.tienda.compra.controller;

import com.tienda.compra.dto.CompraRequestDTO;
import com.tienda.compra.entity.Compra;
import com.tienda.compra.service.CompraService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(
            CompraService compraService
    ) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<List<Compra>>
    listarCompras() {

        return ResponseEntity.ok(
                compraService.listarCompras()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra>
    obtenerCompra(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                compraService.obtenerCompra(id)
        );
    }

    @PostMapping
    public ResponseEntity<Compra>
    guardarCompra(
            @Valid
            @RequestBody
            CompraRequestDTO dto
    ) {

        return ResponseEntity.ok(
                compraService.guardarCompra(dto)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    eliminarCompra(
            @PathVariable Long id
    ) {

        compraService.eliminarCompra(id);

        return ResponseEntity.ok(
                "Compra eliminada correctamente"
        );
    }
}