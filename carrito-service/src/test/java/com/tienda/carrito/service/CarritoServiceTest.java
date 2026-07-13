package com.tienda.carrito.service;

import com.tienda.carrito.dto.AddItemDTO;
import com.tienda.carrito.model.Carrito;
import com.tienda.carrito.repository.CarritoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarritoServiceTest {

    @Mock
    private CarritoRepository repository;

    @InjectMocks
    private CarritoService carritoService;

    @Test
    void agregarItem_DeberiaAgregarCorrectamente() {

        AddItemDTO dto = new AddItemDTO();
        dto.setUserId(1L);
        dto.setSkinId(10L);
        dto.setNombreSkin("Dragon");
        dto.setCantidad(2);
        dto.setPrecio(5000.0);

        when(repository.findByUserId(1L))
                .thenReturn(Optional.empty());

        when(repository.save(any(Carrito.class)))
                .thenAnswer(i -> i.getArgument(0));

        Carrito resultado = carritoService.agregarItem(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getUserId());
        assertEquals(1, resultado.getItems().size());
        assertEquals(10000.0, resultado.getTotal());

        verify(repository).save(any(Carrito.class));
    }

    @Test
    void verCarrito_DeberiaRetornarCarrito() {

        Carrito carrito = new Carrito();
        carrito.setUserId(1L);

        when(repository.findByUserId(1L))
                .thenReturn(Optional.of(carrito));

        Carrito resultado = carritoService.verCarrito(1L);

        assertEquals(1L, resultado.getUserId());

        verify(repository).findByUserId(1L);
    }

    @Test
    void verCarrito_DeberiaLanzarExcepcion() {

        when(repository.findByUserId(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> carritoService.verCarrito(1L)
        );

        verify(repository).findByUserId(1L);
    }

    @Test
    void vaciarCarrito_DeberiaVaciarCorrectamente() {

        Carrito carrito = new Carrito();
        carrito.setUserId(1L);
        carrito.setTotal(10000.0);

        when(repository.findByUserId(1L))
                .thenReturn(Optional.of(carrito));

        carritoService.vaciarCarrito(1L);

        assertEquals(0.0, carrito.getTotal());
        assertTrue(carrito.getItems().isEmpty());

        verify(repository).save(carrito);
    }

}