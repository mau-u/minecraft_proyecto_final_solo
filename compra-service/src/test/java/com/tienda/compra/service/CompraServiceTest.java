package com.tienda.compra.service;

import com.tienda.compra.entity.Compra;
import com.tienda.compra.exception.ResourceNotFoundException;
import com.tienda.compra.repository.CompraRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompraServiceTest {

    @Mock
    private CompraRepository compraRepository;

    @Mock
    private WebClient webClient;

    @InjectMocks
    private CompraService compraService;

    @Test
    void listarCompras_DeberiaRetornarLista() {

        Compra compra = new Compra();

        when(compraRepository.findAll())
                .thenReturn(List.of(compra));

        List<Compra> resultado = compraService.listarCompras();

        assertEquals(1, resultado.size());

        verify(compraRepository).findAll();
    }

    @Test
    void obtenerCompra_DeberiaRetornarCompra() {

        Compra compra = new Compra();
        compra.setId(1L);

        when(compraRepository.findById(1L))
                .thenReturn(Optional.of(compra));

        Compra resultado = compraService.obtenerCompra(1L);

        assertEquals(1L, resultado.getId());

        verify(compraRepository).findById(1L);
    }

    @Test
    void obtenerCompra_DeberiaLanzarExcepcion() {

        when(compraRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> compraService.obtenerCompra(1L)
        );

        verify(compraRepository).findById(1L);
    }

    @Test
    void eliminarCompra_DeberiaEliminarCorrectamente() {

        Compra compra = new Compra();
        compra.setId(1L);

        when(compraRepository.findById(1L))
                .thenReturn(Optional.of(compra));

        compraService.eliminarCompra(1L);

        verify(compraRepository).delete(compra);
    }
}