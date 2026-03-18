package com.example.nuevo.Service;

import com.example.nuevo.Model.Categoria;
import com.example.nuevo.Model.Libro;
import com.example.nuevo.Repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + id));
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        for (Libro libro : categoria.getLibros()) {
            libro.setCategoria(categoria);
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizar(Long id, Categoria datos) {
        Categoria categoria = buscarPorId(id);
        categoria.setNombre(datos.getNombre());
        categoria.setDescripcion(datos.getDescripcion()); // ← faltaba esto

        if (datos.getLibros() != null) {
            categoria.getLibros().clear();
            for (Libro libro : datos.getLibros()) {
                libro.setCategoria(categoria);
                categoria.getLibros().add(libro);
            }
        }
        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminar(Long id) {
        Categoria categoria = buscarPorId(id);
        categoriaRepository.delete(categoria);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return categoriaRepository.existsByNombre(nombre);
    }
}