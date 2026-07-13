package com.tienda.inventario.service;

import com.tienda.inventario.model.Inventario;
import com.tienda.inventario.repository.InventarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InventarioServiceTest {

    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @Test
    void listar_DeberiaRetornarLista() {

        Inventario inventario = new Inventario();

        when(inventarioRepository.findAll())
                .thenReturn(List.of(inventario));

        List<Inventario> resultado = inventarioService.listar();

        assertEquals(1, resultado.size());

        verify(inventarioRepository).findAll();
    }

    @Test
    void guardar_DeberiaGuardarCorrectamente() {

        Inventario inventario = new Inventario();
        inventario.setProductoId(10L);
        inventario.setStock(5);

        when(inventarioRepository.save(inventario))
                .thenReturn(inventario);

        Inventario resultado = inventarioService.guardar(inventario);

        assertNotNull(resultado);
        assertEquals(10L, resultado.getProductoId());

        verify(inventarioRepository).save(inventario);
    }

    @Test
    void reducirStock_DeberiaReducirCorrectamente() {

        Inventario inventario = new Inventario();
        inventario.setProductoId(1L);
        inventario.setStock(10);

        when(inventarioRepository.findByProductoId(1L))
                .thenReturn(Optional.of(inventario));

        inventarioService.reducirStock(1L, 3);

        assertEquals(7, inventario.getStock());

        verify(inventarioRepository).save(inventario);
    }

    @Test
    void reducirStock_DeberiaLanzarExcepcionPorStock() {

        Inventario inventario = new Inventario();
        inventario.setProductoId(1L);
        inventario.setStock(2);

        when(inventarioRepository.findByProductoId(1L))
                .thenReturn(Optional.of(inventario));

        assertThrows(
                RuntimeException.class,
                () -> inventarioService.reducirStock(1L, 5)
        );

        verify(inventarioRepository, never()).save(any());
    }

    @Test
    void reducirStock_DeberiaLanzarExcepcionSiNoExiste() {

        when(inventarioRepository.findByProductoId(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> inventarioService.reducirStock(1L, 1)
        );

        verify(inventarioRepository, never()).save(any());
    }

}