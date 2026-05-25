package com.tienda.reviews.service;

import com.tienda.reviews.dto.ReviewDTO;
import com.tienda.reviews.model.Review;
import com.tienda.reviews.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public Review crear(ReviewDTO dto) {

        Review review = new Review();

        review.setUserId(dto.getUserId());
        review.setSkinId(dto.getSkinId());
        review.setComentario(dto.getComentario());
        review.setPuntuacion(dto.getPuntuacion());

        log.info("Review creada para skin {}", dto.getSkinId());

        return repository.save(review);
    }

    public List<Review> listarPorSkin(Long skinId) {

        log.info("Listando reviews de skin {}", skinId);

        return repository.findBySkinId(skinId);
    }

    public void eliminar(Long id) {

        Review review = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review no encontrada"));

        repository.delete(review);

        log.info("Review eliminada {}", id);
    }
}