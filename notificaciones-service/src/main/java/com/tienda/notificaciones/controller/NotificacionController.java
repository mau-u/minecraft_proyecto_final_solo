package com.tienda.notificaciones.controller;

import com.tienda.notificaciones.dto.NotificacionDTO;
import com.tienda.notificaciones.model.Notificacion;
import com.tienda.notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    private final NotificacionService service;

    public NotificacionController(NotificacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Notificacion> crear(
            @Valid @RequestBody NotificacionDTO dto
    ) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notificacion>> listar(
            @PathVariable Long userId
    ) {
        return ResponseEntity.ok(service.listar(userId));
    }

    @PutMapping("/leer/{id}")
    public ResponseEntity<Notificacion> marcarLeida(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.marcarLeida(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id
    ) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}