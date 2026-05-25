package com.tienda.favoritos.service;

import com.tienda.favoritos.dto.FavoritoDTO;
import com.tienda.favoritos.model.Favorito;
import com.tienda.favoritos.repository.FavoritoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FavoritoService {

    private final FavoritoRepository repository;

    public FavoritoService(FavoritoRepository repository) {
        this.repository = repository;
    }

    public Favorito agregar(FavoritoDTO dto) {

        boolean existe = repository.existsByUserIdAndSkinId(
                dto.getUserId(),
                dto.getSkinId()
        );

        if (existe) {
            throw new RuntimeException("La skin ya está en favoritos");
        }

        Favorito favorito = new Favorito();

        favorito.setUserId(dto.getUserId());
        favorito.setSkinId(dto.getSkinId());
        favorito.setNombreSkin(dto.getNombreSkin());

        log.info("Skin agregada a favoritos del usuario {}", dto.getUserId());

        return repository.save(favorito);
    }

    public List<Favorito> listar(Long userId) {

        log.info("Listando favoritos del usuario {}", userId);

        return repository.findByUserId(userId);
    }

    public void eliminar(Long id) {

        Favorito favorito = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorito no encontrado"));

        repository.delete(favorito);

        log.info("Favorito eliminado {}", id);
    }
}