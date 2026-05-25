package com.tienda.pagos.controller;

import com.tienda.pagos.dto.PagoRequestDTO;
import com.tienda.pagos.model.Pago;
import com.tienda.pagos.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public ResponseEntity<Pago> pagar(
            @Valid
            @RequestBody
            PagoRequestDTO dto
    ) {

        return ResponseEntity.ok(
                pagoService.procesarPago(dto)
        );
    }

    @GetMapping
    public ResponseEntity<List<Pago>> listar() {

        return ResponseEntity.ok(
                pagoService.listarPagos()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> obtener(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                pagoService.obtenerPago(id)
        );
    }
}