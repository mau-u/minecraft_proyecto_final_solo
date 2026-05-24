package com.tienda.inventario.service;

import com.tienda.inventario.dto.ReducirStockDTO;
import com.tienda.inventario.model.Inventario;
import com.tienda.inventario.repository.InventarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventarioService {

    private final InventarioRepository repository;

    public InventarioService(InventarioRepository repository) {
        this.repository = repository;
    }

    public Inventario crear(Inventario inventario) {
        return repository.save(inventario);
    }

    public Inventario obtenerPorSkinId(Long skinId) {
        return repository.findBySkinId(skinId)
                .orElseThrow(() -> new RuntimeException("Skin no existe en inventario"));
    }

    public Inventario reducirStock(ReducirStockDTO dto) {

        Inventario inventario = obtenerPorSkinId(dto.getSkinId());

        if (inventario.getStock() < dto.getCantidad()) {
            throw new RuntimeException("Stock insuficiente");
        }

        inventario.setStock(inventario.getStock() - dto.getCantidad());

        return repository.save(inventario);
    }
}