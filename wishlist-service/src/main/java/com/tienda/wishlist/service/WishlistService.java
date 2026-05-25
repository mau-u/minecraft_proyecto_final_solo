package com.tienda.wishlist.service;

import com.tienda.wishlist.dto.WishlistDTO;
import com.tienda.wishlist.model.WishlistItem;
import com.tienda.wishlist.repository.WishlistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class WishlistService {

    private final WishlistRepository repository;

    public WishlistService(WishlistRepository repository) {
        this.repository = repository;
    }

    public WishlistItem agregar(WishlistDTO dto) {

        boolean existe = repository.existsByUserIdAndSkinId(
                dto.getUserId(),
                dto.getSkinId()
        );

        if (existe) {
            throw new RuntimeException("La skin ya está en la wishlist");
        }

        WishlistItem item = new WishlistItem();

        item.setUserId(dto.getUserId());
        item.setSkinId(dto.getSkinId());
        item.setNombreSkin(dto.getNombreSkin());
        item.setPrecioDeseado(dto.getPrecioDeseado());

        log.info("Skin agregada a wishlist usuario {}", dto.getUserId());

        return repository.save(item);
    }

    public List<WishlistItem> listar(Long userId) {

        log.info("Listando wishlist usuario {}", userId);

        return repository.findByUserId(userId);
    }

    public void eliminar(Long id) {

        WishlistItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        repository.delete(item);

        log.info("Item eliminado wishlist {}", id);
    }
}