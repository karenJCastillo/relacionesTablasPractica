package com.example.nuevo.Controller;

import com.example.nuevo.Model.Categoria;
import com.example.nuevo.Model.Libro;
import com.example.nuevo.Service.LibroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public ResponseEntity<List<Libro>> listar() {
        return ResponseEntity.ok(libroService.listarTodos());
    }

    @GetMapping("/{id}/buscar")
    public ResponseEntity<Libro> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(libroService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/categoria/{categoriaId}/listarPoCategoria")
    public ResponseEntity<List<Libro>> listarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(libroService.listarPorCategoria(categoriaId));
    }

    @GetMapping("/autor/{autor}/listarPorAutor")
    public ResponseEntity<List<Libro>> listarPorAutor(@PathVariable String autor) {
        return ResponseEntity.ok(libroService.listarPorAutor(autor));
    }

    @PostMapping("/{id}/crear")
    public ResponseEntity<?> crear(@RequestBody Libro libro,
                                   @RequestParam(required = false) Long categoriaId) {
        try {
            if (categoriaId != null) {
                Categoria categoria = new Categoria();
                categoria.setId(categoriaId);
                libro.setCategoria(categoria);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(libroService.guardar(libro));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/actualizar")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        try {
            return ResponseEntity.ok(libroService.actualizar(id, libro));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            libroService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}