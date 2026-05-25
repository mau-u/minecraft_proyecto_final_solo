package com.tienda.notificaciones.service;

import com.tienda.notificaciones.dto.NotificacionDTO;
import com.tienda.notificaciones.model.Notificacion;
import com.tienda.notificaciones.repository.NotificacionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NotificacionService {

    private final NotificacionRepository repository;

    public NotificacionService(NotificacionRepository repository) {
        this.repository = repository;
    }

    public Notificacion crear(NotificacionDTO dto) {

        Notificacion notificacion = new Notificacion();

        notificacion.setUserId(dto.getUserId());
        notificacion.setMensaje(dto.getMensaje());
        notificacion.setLeida(false);

        log.info("Notificación creada para usuario {}", dto.getUserId());

        return repository.save(notificacion);
    }

    public List<Notificacion> listar(Long userId) {

        log.info("Listando notificaciones del usuario {}", userId);

        return repository.findByUserId(userId);
    }

    public Notificacion marcarLeida(Long id) {

        Notificacion notificacion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));

        notificacion.setLeida(true);

        log.info("Notificación marcada como leída {}", id);

        return repository.save(notificacion);
    }

    public void eliminar(Long id) {

        Notificacion notificacion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));

        repository.delete(notificacion);

        log.info("Notificación eliminada {}", id);
    }
}