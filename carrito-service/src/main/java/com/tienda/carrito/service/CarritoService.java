package com.tienda.carrito.service;

import com.tienda.carrito.dto.AddItemDTO;
import com.tienda.carrito.model.Carrito;
import com.tienda.carrito.model.ItemCarrito;
import com.tienda.carrito.repository.CarritoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CarritoService {

    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    public Carrito obtenerOCrearCarrito(Long userId) {
        return carritoRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Carrito carrito = new Carrito();
                    carrito.setUserId(userId);
                    carrito.setTotal(0.0);
                    log.info("Carrito creado para usuario {}", userId);
                    return carritoRepository.save(carrito);
                });
    }

    public Carrito agregarItem(AddItemDTO dto) {

        Carrito carrito = obtenerOCrearCarrito(dto.getUserId());

        ItemCarrito item = new ItemCarrito();
        item.setSkinId(dto.getSkinId());
        item.setNombreSkin(dto.getNombreSkin());
        item.setPrecio(dto.getPrecio());
        item.setCantidad(dto.getCantidad());
        item.setCarrito(carrito);

        carrito.getItems().add(item);

        double total = carrito.getItems()
                .stream()
                .mapToDouble(i -> i.getPrecio() * i.getCantidad())
                .sum();

        carrito.setTotal(total);

        log.info("Item agregado al carrito del usuario {}", dto.getUserId());

        return carritoRepository.save(carrito);
    }

    public Carrito verCarrito(Long userId) {
        return carritoRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    public void vaciarCarrito(Long userId) {
        Carrito carrito = obtenerOCrearCarrito(userId);
        carrito.getItems().clear();
        carrito.setTotal(0.0);

        carritoRepository.save(carrito);

        log.info("Carrito vaciado para usuario {}", userId);
    }
}