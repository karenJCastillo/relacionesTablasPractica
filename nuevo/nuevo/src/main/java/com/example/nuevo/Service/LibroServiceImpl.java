package com.example.nuevo.Service;

import com.example.nuevo.Model.Categoria;
import com.example.nuevo.Model.Libro;
import com.example.nuevo.Repository.CategoriaRepository;
import com.example.nuevo.Repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;
    private final CategoriaRepository categoriaRepository;

    public LibroServiceImpl(LibroRepository libroRepository, CategoriaRepository categoriaRepository) {
        this.libroRepository = libroRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    @Override
    public Libro buscarPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con id: " + id));
    }

    @Override
    public List<Libro> listarPorCategoria(Long categoriaId) {
        return libroRepository.findByCategoriaId(categoriaId);
    }

    @Override
    public List<Libro> listarPorAutor(String autor) {
        return libroRepository.findByAutor(autor);
    }

    @Override
    public Libro guardar(Libro libro) {
        resolverCategoria(libro);
        return libroRepository.save(libro);
    }

    @Override
    public Libro actualizar(Long id, Libro datos) {
        Libro libro = buscarPorId(id);
        libro.setTitulo(datos.getTitulo());
        libro.setAutor(datos.getAutor());
        libro.setAnioPublicacion(datos.getAnioPublicacion());
        if (datos.getCategoria() != null) {
            libro.setCategoria(datos.getCategoria());
            resolverCategoria(libro);
        }
        return libroRepository.save(libro);
    }

    @Override
    public void eliminar(Long id) {
        if (!libroRepository.existsById(id)) {
            throw new RuntimeException("Libro no encontrado con id: " + id);
        }
        libroRepository.deleteById(id);
    }

    private void resolverCategoria(Libro libro) {
        if (libro.getCategoria() != null && libro.getCategoria().getId() != null) {
            Long categoriaId = libro.getCategoria().getId();
            Categoria categoria = categoriaRepository.findById(categoriaId)
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + categoriaId));
            libro.setCategoria(categoria);
        }
    }
}