package com.tienda.favoritos.service;

import com.tienda.favoritos.dto.FavoritoDTO;
import com.tienda.favoritos.model.Favorito;
import com.tienda.favoritos.repository.FavoritoRepository;
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
class FavoritoServiceTest {

    @Mock
    private FavoritoRepository repository;

    @InjectMocks
    private FavoritoService favoritoService;

    @Test
    void agregar_DeberiaGuardarFavorito() {

        FavoritoDTO dto = new FavoritoDTO();
        dto.setUserId(1L);
        dto.setSkinId(10L);
        dto.setNombreSkin("Dragon");

        when(repository.existsByUserIdAndSkinId(1L,10L))
                .thenReturn(false);

        when(repository.save(any(Favorito.class)))
                .thenAnswer(i -> i.getArgument(0));

        Favorito resultado = favoritoService.agregar(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getUserId());
        assertEquals(10L, resultado.getSkinId());

        verify(repository).save(any(Favorito.class));
    }

    @Test
    void agregar_DeberiaLanzarExcepcionSiExiste() {

        FavoritoDTO dto = new FavoritoDTO();
        dto.setUserId(1L);
        dto.setSkinId(10L);

        when(repository.existsByUserIdAndSkinId(1L,10L))
                .thenReturn(true);

        assertThrows(
                RuntimeException.class,
                () -> favoritoService.agregar(dto)
        );

        verify(repository, never()).save(any());
    }

    @Test
    void listar_DeberiaRetornarFavoritos() {

        Favorito favorito = new Favorito();

        when(repository.findByUserId(1L))
                .thenReturn(List.of(favorito));

        List<Favorito> resultado = favoritoService.listar(1L);

        assertEquals(1, resultado.size());

        verify(repository).findByUserId(1L);
    }

    @Test
    void eliminar_DeberiaEliminarCorrectamente() {

        Favorito favorito = new Favorito();
        favorito.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(favorito));

        favoritoService.eliminar(1L);

        verify(repository).delete(favorito);
    }

    @Test
    void eliminar_DeberiaLanzarExcepcion() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> favoritoService.eliminar(1L)
        );

        verify(repository, never()).delete(any());
    }

}