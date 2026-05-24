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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {

    private static final Logger logger =
            LoggerFactory.getLogger(CompraService.class);

    private final CompraRepository compraRepository;

    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public List<Compra> listarCompras() {

        logger.info("Listando compras");

        return compraRepository.findAll();
    }

    public Compra obtenerCompra(Long id) {

        logger.info("Buscando compra {}", id);

        return compraRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Compra no encontrada"));
    }

    public Compra guardarCompra(CompraRequestDTO dto) {

        logger.info(
                "Creando compra para usuario {}",
                dto.getUsuarioId()
        );

        Compra compra = new Compra();

        compra.setUsuarioId(dto.getUsuarioId());

        compra.setFecha(LocalDateTime.now());

        List<DetalleCompra> detalles =
                new ArrayList<>();

        double total = 0;

        for (DetalleRequestDTO detalleDTO :
                dto.getDetalles()) {

            DetalleCompra detalle =
                    new DetalleCompra();

            detalle.setSkinId(
                    detalleDTO.getSkinId()
            );

            detalle.setCantidad(
                    detalleDTO.getCantidad()
            );

            double subtotal =
                    detalleDTO.getCantidad() * 5000;

            detalle.setSubtotal(subtotal);

            detalle.setCompra(compra);

            detalles.add(detalle);

            total += subtotal;
        }

        compra.setDetalles(detalles);

        compra.setTotal(total);

        return compraRepository.save(compra);
    }

    public void eliminarCompra(Long id) {

        Compra compra = obtenerCompra(id);

        logger.warn(
                "Eliminando compra {}",
                id
        );

        compraRepository.delete(compra);
    }
}