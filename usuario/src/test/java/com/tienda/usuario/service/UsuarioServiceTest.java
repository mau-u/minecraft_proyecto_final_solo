package com.tienda.usuario.service;

import com.tienda.usuario.entity.Usuario;
import com.tienda.usuario.exception.ResourceNotFoundException;
import com.tienda.usuario.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void guardarUsuario_DeberiaGuardarCorrectamente() {

        Usuario usuario = new Usuario();
        usuario.setNombre("Mauricio");
        usuario.setEmail("mauricio@test.cl");
        usuario.setPassword("1234");
        usuario.setRol("ADMIN");

        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario resultado = usuarioService.guardarUsuario(usuario);

        assertNotNull(resultado);
        assertEquals("Mauricio", resultado.getNombre());

        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void obtenerUsuario_DeberiaRetornarUsuario() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Mauricio");

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.obtenerUsuario(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("Mauricio", resultado.getNombre());

        verify(usuarioRepository).findById(1L);
    }

    @Test
    void obtenerUsuario_DeberiaLanzarExcepcion() {

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> usuarioService.obtenerUsuario(1L)
        );

        verify(usuarioRepository).findById(1L);
    }

    @Test
    void eliminarUsuario_DeberiaEliminarCorrectamente() {

        Usuario usuario = new Usuario();
        usuario.setId(1L);

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        usuarioService.eliminarUsuario(1L);

        verify(usuarioRepository).delete(usuario);
    }

    @Test
    void actualizarUsuario_DeberiaActualizarCorrectamente() {

        Usuario existente = new Usuario();
        existente.setId(1L);
        existente.setNombre("Juan");
        existente.setEmail("juan@test.cl");
        existente.setPassword("1234");
        existente.setRol("USER");

        Usuario actualizado = new Usuario();
        actualizado.setNombre("Mauricio");
        actualizado.setEmail("mauricio@test.cl");
        actualizado.setPassword("5678");
        actualizado.setRol("ADMIN");

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(existente));

        when(usuarioRepository.save(any(Usuario.class)))
                .thenAnswer(i -> i.getArgument(0));

        Usuario resultado = usuarioService.actualizarUsuario(1L, actualizado);

        assertEquals("Mauricio", resultado.getNombre());
        assertEquals("mauricio@test.cl", resultado.getEmail());
        assertEquals("5678", resultado.getPassword());
        assertEquals("ADMIN", resultado.getRol());

        verify(usuarioRepository).findById(1L);
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test
    void actualizarUsuario_DeberiaLanzarExcepcion() {

        Usuario actualizado = new Usuario();

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> usuarioService.actualizarUsuario(1L, actualizado)
        );

        verify(usuarioRepository).findById(1L);
    }
}