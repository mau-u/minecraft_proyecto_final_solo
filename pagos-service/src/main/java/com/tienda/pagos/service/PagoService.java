package com.tienda.pagos.service;

import com.tienda.pagos.dto.PagoRequestDTO;
import com.tienda.pagos.model.Pago;
import com.tienda.pagos.repository.PagoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PagoService {

    private final PagoRepository pagoRepository;

    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public Pago procesarPago(PagoRequestDTO dto) {

        Pago pago = new Pago();

        pago.setCompraId(dto.getCompraId());
        pago.setMonto(dto.getMonto());
        pago.setMetodoPago(dto.getMetodoPago());

        pago.setEstado("PAGADO");

        pago.setFecha(LocalDateTime.now());

        log.info("Pago realizado para compra {}", dto.getCompraId());

        return pagoRepository.save(pago);
    }

    public List<Pago> listarPagos() {

        return pagoRepository.findAll();
    }

    public Pago obtenerPago(Long id) {

        return pagoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pago no encontrado"));
    }
}