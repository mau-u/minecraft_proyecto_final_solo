package com.tienda.reviews.service;

import com.tienda.reviews.dto.ReviewDTO;
import com.tienda.reviews.model.Review;
import com.tienda.reviews.repository.ReviewRepository;
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
class ReviewServiceTest {

    @Mock
    private ReviewRepository repository;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    void crear_DeberiaGuardarReview() {

        ReviewDTO dto = new ReviewDTO();
        dto.setUserId(1L);
        dto.setSkinId(10L);
        dto.setComentario("Excelente");
        dto.setPuntuacion(5);

        when(repository.save(any(Review.class)))
                .thenAnswer(i -> i.getArgument(0));

        Review resultado = reviewService.crear(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getUserId());
        assertEquals(10L, resultado.getSkinId());
        assertEquals(5, resultado.getPuntuacion());

        verify(repository).save(any(Review.class));
    }

    @Test
    void listarPorSkin_DeberiaRetornarLista() {

        Review review = new Review();

        when(repository.findBySkinId(10L))
                .thenReturn(List.of(review));

        List<Review> resultado = reviewService.listarPorSkin(10L);

        assertEquals(1, resultado.size());

        verify(repository).findBySkinId(10L);
    }

    @Test
    void eliminar_DeberiaEliminarCorrectamente() {

        Review review = new Review();
        review.setId(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(review));

        reviewService.eliminar(1L);

        verify(repository).delete(review);
    }

    @Test
    void eliminar_DeberiaLanzarExcepcion() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> reviewService.eliminar(1L)
        );

        verify(repository, never()).delete(any());
    }

}