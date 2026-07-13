package com.tienda.wishlist.service;

import com.tienda.wishlist.dto.WishlistDTO;
import com.tienda.wishlist.model.WishlistItem;
import com.tienda.wishlist.repository.WishlistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WishlistServiceTest {

    @Mock
    private WishlistRepository repository;

    @InjectMocks
    private WishlistService wishlistService;

    @Test
    void agregar_DeberiaGuardarItem() {

        WishlistDTO dto = new WishlistDTO();
        dto.setUserId(1L);
        dto.setSkinId(10L);
        dto.setNombreSkin("Dragon");
        dto.setPrecioDeseado(4500.0);

        when(repository.existsByUserIdAndSkinId(1L, 10L))
                .thenReturn(false);

        when(repository.save(any(WishlistItem.class)))
                .thenAnswer(i -> i.getArgument(0));

        WishlistItem resultado = wishlistService.agregar(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getUserId());
        assertEquals(10L, resultado.getSkinId());

        verify(repository).save(any(WishlistItem.class));
    }

    @Test
    void agregar_DeberiaLanzarExcepcionSiExiste() {

        WishlistDTO dto = new WishlistDTO();
        dto.setUserId(1L);
        dto.setSkinId(10L);

        when(repository.existsByUserIdAndSkinId(1L, 10L))
                .thenReturn(true);

        assertThrows(
                RuntimeException.class,
                () -> wishlistService.agregar(dto)
        );

        verify(repository, never()).save(any());
    }

    @Test
    void listar_DeberiaRetornarLista() {

        WishlistItem item = new WishlistItem();

        when(repository.findByUserId(1L))
                .thenReturn(List.of(item));

        List<WishlistItem> resultado = wishlistService.listar(1L);

        assertEquals(1, resultado.size());

        verify(repository).findByUserId(1L);
    }

    @Test
    void eliminar_DeberiaEliminarCorrectamente() {

        WishlistItem item = new WishlistItem();
        item.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(item));

        wishlistService.eliminar(1L);

        verify(repository).delete(item);
    }

    @Test
    void eliminar_DeberiaLanzarExcepcion() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> wishlistService.eliminar(1L)
        );

        verify(repository, never()).delete(any());
    }
}