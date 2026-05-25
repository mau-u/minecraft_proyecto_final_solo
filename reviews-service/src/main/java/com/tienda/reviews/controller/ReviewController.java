package com.tienda.reviews.controller;

import com.tienda.reviews.dto.ReviewDTO;
import com.tienda.reviews.model.Review;
import com.tienda.reviews.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Review> crear(
            @Valid @RequestBody ReviewDTO dto
    ) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping("/skin/{skinId}")
    public ResponseEntity<List<Review>> listar(
            @PathVariable Long skinId
    ) {
        return ResponseEntity.ok(service.listarPorSkin(skinId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}