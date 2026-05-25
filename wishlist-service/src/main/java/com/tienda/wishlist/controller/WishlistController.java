package com.tienda.wishlist.controller;

import com.tienda.wishlist.dto.WishlistDTO;
import com.tienda.wishlist.model.WishlistItem;
import com.tienda.wishlist.service.WishlistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService service;

    public WishlistController(WishlistService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<WishlistItem> agregar(
            @Valid @RequestBody WishlistDTO dto
    ) {
        return ResponseEntity.ok(service.agregar(dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistItem>> listar(
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