package com.example.nuevo.Controller;

import com.example.nuevo.Model.Categoria;
import com.example.nuevo.Model.Libro;
import com.example.nuevo.Service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias/verLista")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    @GetMapping("/{id}/buscar")
    public ResponseEntity<Categoria> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(categoriaService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping ("/{id}/crear")
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
        if (categoriaService.existePorNombre(categoria.getNombre())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        // Asignar referencia bidireccional
        for (Libro libro : categoria.getLibros()) {
            libro.setCategoria(categoria);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.guardar(categoria));
    }

    @PutMapping("/{id}/actualizar")
    public ResponseEntity<Categoria> actualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        try {
            return ResponseEntity.ok(categoriaService.actualizar(id, categoria));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            categoriaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}