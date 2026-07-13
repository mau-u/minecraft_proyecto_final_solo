package com.tienda.notificaciones.service;

import com.tienda.notificaciones.dto.NotificacionDTO;
import com.tienda.notificaciones.model.Notificacion;
import com.tienda.notificaciones.repository.NotificacionRepository;
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
class NotificacionServiceTest {

    @Mock
    private NotificacionRepository repository;

    @InjectMocks
    private NotificacionService notificacionService;

    @Test
    void crear_DeberiaGuardarNotificacion() {

        NotificacionDTO dto = new NotificacionDTO();
        dto.setUserId(1L);
        dto.setMensaje("Compra realizada");

        when(repository.save(any(Notificacion.class)))
                .thenAnswer(i -> i.getArgument(0));

        Notificacion resultado = notificacionService.crear(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getUserId());
        assertEquals("Compra realizada", resultado.getMensaje());
        assertFalse(resultado.getLeida());

        verify(repository).save(any(Notificacion.class));
    }

    @Test
    void listar_DeberiaRetornarLista() {

        Notificacion notificacion = new Notificacion();

        when(repository.findByUserId(1L))
                .thenReturn(List.of(notificacion));

        List<Notificacion> resultado = notificacionService.listar(1L);

        assertEquals(1, resultado.size());

        verify(repository).findByUserId(1L);
    }

    @Test
    void marcarLeida_DeberiaActualizarEstado() {

        Notificacion notificacion = new Notificacion();
        notificacion.setId(1L);
        notificacion.setLeida(false);

        when(repository.findById(1L))
                .thenReturn(Optional.of(notificacion));

        when(repository.save(any(Notificacion.class)))
                .thenAnswer(i -> i.getArgument(0));

        Notificacion resultado = notificacionService.marcarLeida(1L);

        assertTrue(resultado.getLeida());

        verify(repository).save(notificacion);
    }

    @Test
    void marcarLeida_DeberiaLanzarExcepcion() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> notificacionService.marcarLeida(1L)
        );

        verify(repository, never()).save(any());
    }

    @Test
    void eliminar_DeberiaEliminarCorrectamente() {

        Notificacion notificacion = new Notificacion();
        notificacion.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(notificacion));

        notificacionService.eliminar(1L);

        verify(repository).delete(notificacion);
    }

    @Test
    void eliminar_DeberiaLanzarExcepcion() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> notificacionService.eliminar(1L)
        );

        verify(repository, never()).delete(any());
    }

}