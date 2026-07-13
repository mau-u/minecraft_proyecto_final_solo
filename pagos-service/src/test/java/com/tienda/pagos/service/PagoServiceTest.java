package com.tienda.pagos.service;

import com.tienda.pagos.dto.PagoRequestDTO;
import com.tienda.pagos.model.Pago;
import com.tienda.pagos.repository.PagoRepository;
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
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @InjectMocks
    private PagoService pagoService;

    @Test
    void procesarPago_DeberiaGuardarPago() {

        PagoRequestDTO dto = new PagoRequestDTO();
        dto.setCompraId(1L);
        dto.setMonto(15000.0);
        dto.setMetodoPago("TARJETA");

        when(pagoRepository.save(any(Pago.class)))
                .thenAnswer(i -> i.getArgument(0));

        Pago resultado = pagoService.procesarPago(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getCompraId());
        assertEquals(15000.0, resultado.getMonto());
        assertEquals("PAGADO", resultado.getEstado());

        verify(pagoRepository).save(any(Pago.class));
    }

    @Test
    void listarPagos_DeberiaRetornarLista() {

        Pago pago = new Pago();

        when(pagoRepository.findAll())
                .thenReturn(List.of(pago));

        List<Pago> resultado = pagoService.listarPagos();

        assertEquals(1, resultado.size());

        verify(pagoRepository).findAll();
    }

    @Test
    void obtenerPago_DeberiaRetornarPago() {

        Pago pago = new Pago();
        pago.setId(1L);

        when(pagoRepository.findById(1L))
                .thenReturn(Optional.of(pago));

        Pago resultado = pagoService.obtenerPago(1L);

        assertEquals(1L, resultado.getId());

        verify(pagoRepository).findById(1L);
    }

    @Test
    void obtenerPago_DeberiaLanzarExcepcion() {

        when(pagoRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> pagoService.obtenerPago(1L)
        );

        verify(pagoRepository).findById(1L);
    }

}