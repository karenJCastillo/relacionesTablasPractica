package com.example.nuevo.Service;

import com.example.nuevo.Model.Curso;
import com.example.nuevo.Repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + id));
    }

    @Override
    public Curso buscarPorNombre(String nombre) {
        List<Curso> cursos = cursoRepository.findByNombre(nombre);
        if (!cursos.isEmpty()) {
            return cursos.get(0);
        }
        throw new RuntimeException("Curso no encontrado con nombre: " + nombre);
    }

    @Override
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Curso actualizar(Long id, Curso datos) {
        Curso curso = buscarPorId(id);
        curso.setNombre(datos.getNombre());
        curso.setCreditos(datos.getCreditos());
        return cursoRepository.save(curso);
    }

    @Override
    public void eliminar(Long id) {
        if (!cursoRepository.existsById(id)) {
            throw new RuntimeException("Curso no encontrado con id: " + id);
        }
        cursoRepository.deleteById(id);
    }

    @Override
    public boolean existePorNombre(String nombre) {
        return cursoRepository.existsByNombre(nombre);
    }
}