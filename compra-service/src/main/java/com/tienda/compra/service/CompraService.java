package com.tienda.compra.service;

import com.tienda.compra.dto.CompraRequestDTO;
import com.tienda.compra.dto.DetalleRequestDTO;
import com.tienda.compra.entity.Compra;
import com.tienda.compra.entity.DetalleCompra;
import com.tienda.compra.exception.ResourceNotFoundException;
import com.tienda.compra.repository.CompraRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {

    private static final Logger log = LoggerFactory.getLogger(CompraService.class);

    private final CompraRepository compraRepository;
    private final WebClient webClient;

    public CompraService(
            CompraRepository compraRepository,
            WebClient webClient
    ) {
        this.compraRepository = compraRepository;
        this.webClient = webClient;
    }

    public List<Compra> listarCompras() {
        log.info("Listando compras");
        return compraRepository.findAll();
    }

    public Compra obtenerCompra(Long id) {

        log.info("Buscando compra {}", id);

        return compraRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Compra no encontrada"));
    }

    public Compra guardarCompra(CompraRequestDTO dto) {

        log.info("Creando compra para usuario {}", dto.getUsuarioId());

        Compra compra = new Compra();

        compra.setUsuarioId(dto.getUsuarioId());
        compra.setFecha(LocalDateTime.now());

        List<DetalleCompra> detalles = new ArrayList<>();

        double total = 0;

        try {

            for (DetalleRequestDTO detalleDTO : dto.getDetalles()) {

                String inventarioResponse = webClient.put()
                        .uri("http://localhost:8084/inventario/reducir")
                        .bodyValue(detalleDTO)
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                log.info("Respuesta inventario: {}", inventarioResponse);

                DetalleCompra detalle = new DetalleCompra();

                detalle.setSkinId(detalleDTO.getSkinId());
                detalle.setCantidad(detalleDTO.getCantidad());

                double subtotal = detalleDTO.getCantidad() * 5000;

                detalle.setSubtotal(subtotal);

                detalle.setCompra(compra);

                detalles.add(detalle);

                total += subtotal;
            }

            compra.setDetalles(detalles);
            compra.setTotal(total);

            Compra compraGuardada = compraRepository.save(compra);

            log.info("Compra guardada correctamente");

            return compraGuardada;

        } catch (Exception e) {

            log.error("Error al crear compra: {}", e.getMessage());

            throw new RuntimeException("Error al procesar compra");
        }
    }

    public void eliminarCompra(Long id) {

        Compra compra = obtenerCompra(id);

        log.warn("Eliminando compra {}", id);

        compraRepository.delete(compra);
    }
}