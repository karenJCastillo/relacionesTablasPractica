package com.example.nuevo.Controller;

import com.example.nuevo.Model.PerfilUsuario;
import com.example.nuevo.Service.PerfilUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilUsuarioController {

    private final PerfilUsuarioService perfilUsuarioService;

    public PerfilUsuarioController(PerfilUsuarioService perfilUsuarioService) {
        this.perfilUsuarioService = perfilUsuarioService;
    }

    @GetMapping
    public ResponseEntity<List<PerfilUsuario>> listar() {
        return ResponseEntity.ok(perfilUsuarioService.listarTodos());
    }

    @GetMapping("/{id}/buscar")
    public ResponseEntity<PerfilUsuario> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(perfilUsuarioService.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}/buscarPorUsuario")
    public ResponseEntity<PerfilUsuario> buscarPorUsuario(@PathVariable Long usuarioId) {
        try {
            return ResponseEntity.ok(perfilUsuarioService.buscarPorUsuarioId(usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/crear")
    public ResponseEntity<PerfilUsuario> crear(@RequestBody PerfilUsuario perfil) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(perfilUsuarioService.guardar(perfil));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/actualizar")
    public ResponseEntity<PerfilUsuario> actualizar(@PathVariable Long id, @RequestBody PerfilUsuario perfil) {
        try {
            return ResponseEntity.ok(perfilUsuarioService.actualizar(id, perfil));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/eliminar")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            perfilUsuarioService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}