package com.tienda.wishlist.repository;

import com.tienda.wishlist.model.WishlistItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepository extends JpaRepository<WishlistItem, Long> {

    List<WishlistItem> findByUserId(Long userId);

    boolean existsByUserIdAndSkinId(Long userId, Long skinId);
}