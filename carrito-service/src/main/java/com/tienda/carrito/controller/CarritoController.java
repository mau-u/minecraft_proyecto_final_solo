package com.tienda.carrito.controller;

import com.tienda.carrito.dto.AddItemDTO;
import com.tienda.carrito.model.Carrito;
import com.tienda.carrito.service.CarritoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @PostMapping("/add")
    public ResponseEntity<Carrito> agregar(@Valid @RequestBody AddItemDTO dto) {
        return ResponseEntity.ok(carritoService.agregarItem(dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Carrito> ver(@PathVariable Long userId) {
        return ResponseEntity.ok(carritoService.verCarrito(userId));
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<Void> limpiar(@PathVariable Long userId) {
        carritoService.vaciarCarrito(userId);
        return ResponseEntity.noContent().build();
    }
}