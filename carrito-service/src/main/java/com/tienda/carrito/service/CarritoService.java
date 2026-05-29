package com.tienda.carrito.service;

import com.tienda.carrito.dto.AddItemDTO;
import com.tienda.carrito.model.Carrito;
import com.tienda.carrito.model.ItemCarrito;
import com.tienda.carrito.repository.CarritoRepository;

import org.springframework.stereotype.Service;

@Service
public class CarritoService {

    private final CarritoRepository repository;

    public CarritoService(
            CarritoRepository repository
    ) {
        this.repository = repository;
    }

    public Carrito agregarItem(
            AddItemDTO dto
    ) {

        Carrito carrito =
                repository.findByUserId(
                        dto.getUserId()
                ).orElse(new Carrito());

        carrito.setUserId(dto.getUserId());

        ItemCarrito item =
                new ItemCarrito();

        item.setSkinId(dto.getSkinId());
        item.setNombreSkin(dto.getNombreSkin());
        item.setCantidad(dto.getCantidad());
        item.setPrecio(dto.getPrecio());
        item.setCarrito(carrito);

        carrito.getItems().add(item);

        double total =
                carrito.getItems()
                        .stream()
                        .mapToDouble(i ->
                                i.getPrecio()
                                        * i.getCantidad())
                        .sum();

        carrito.setTotal(total);

        return repository.save(carrito);
    }

    public Carrito verCarrito(Long userId) {

        return repository.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Carrito no encontrado"
                        ));
    }

    public void vaciarCarrito(Long userId) {

        Carrito carrito =
                verCarrito(userId);

        carrito.getItems().clear();

        carrito.setTotal(0.0);

        repository.save(carrito);
    }
}