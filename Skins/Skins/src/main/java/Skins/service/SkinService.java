package Skins.service;

import Skins.dto.SkinRequestDTO;
import Skins.exception.ResourceNotFoundException;
import Skins.model.Skin;
import Skins.repository.SkinRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SkinService {

    private final SkinRepository repository;

    public List<Skin> listar() {
        log.info("Listando skins");
        return repository.findAll();
    }

    public Skin buscarPorId(Long id) {
        log.info("Buscando skin con id: {}", id);

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Skin no encontrada"));
    }

    public Skin guardar(SkinRequestDTO dto) {

        log.info("Guardando skin: {}", dto.getNombre());

        Skin skin = new Skin();

        skin.setNombre(dto.getNombre());
        skin.setPrecio(dto.getPrecio());

        // valores por defecto si no los mandas
        skin.setUrlTextura("default.png");
        skin.setTipoModelo("default");
        skin.setRareza("comun");

        return repository.save(skin);
    }

    public Skin actualizar(Long id, SkinRequestDTO dto) {

        log.info("Actualizando skin con id: {}", id);

        Skin skin = buscarPorId(id);

        skin.setNombre(dto.getNombre());
        skin.setPrecio(dto.getPrecio());

        return repository.save(skin);
    }

    public void eliminar(Long id) {

        log.info("Eliminando skin con id: {}", id);

        Skin skin = buscarPorId(id);

        repository.delete(skin);
    }
}