package com.tienda.favoritos.repository;

import com.tienda.favoritos.model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepository
        extends JpaRepository<Favorito, Long> {

    List<Favorito> findByUserId(Long userId);

    boolean existsByUserIdAndSkinId(
            Long userId,
            Long skinId
    );
}