package com.tienda.usuario.service;

import com.tienda.usuario.entity.Usuario;
import com.tienda.usuario.exception.ResourceNotFoundException;
import com.tienda.usuario.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private static final Logger logger =
            LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {

        logger.info("Listando todos los usuarios");

        return usuarioRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {

        logger.info("Guardando usuario: {}", usuario.getEmail());

        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuario(Long id) {

        logger.info("Buscando usuario con ID: {}", id);

        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"));
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {

        logger.info("Actualizando usuario con ID: {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"));

        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setEmail(usuarioActualizado.getEmail());
        usuario.setPassword(usuarioActualizado.getPassword());
        usuario.setRol(usuarioActualizado.getRol());

        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {

        logger.warn("Eliminando usuario con ID: {}", id);

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"));

        usuarioRepository.delete(usuario);

        logger.info("Usuario eliminado correctamente");
    }

}