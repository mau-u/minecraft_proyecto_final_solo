package com.tienda.reviews.repository;

import com.tienda.reviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findBySkinId(Long skinId);
}