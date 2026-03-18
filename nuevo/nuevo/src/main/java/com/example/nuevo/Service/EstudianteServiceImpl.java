package com.example.nuevo.Service;

import com.example.nuevo.Model.Curso;
import com.example.nuevo.Model.Estudiante;
import com.example.nuevo.Repository.CursoRepository;
import com.example.nuevo.Repository.EstudianteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public EstudianteServiceImpl(EstudianteRepository estudianteRepository, CursoRepository cursoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante buscarPorId(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id));
    }

    @Override
    public Estudiante buscarPorCorreo(String correo) {
        List<Estudiante> estudiantes = estudianteRepository.findByCorreo(correo);
        if (!estudiantes.isEmpty()) {
            return estudiantes.get(0);
        }
        throw new RuntimeException("Estudiante no encontrado con correo: " + correo);
    }

    @Override
    public Estudiante guardar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante actualizar(Long id, Estudiante datos) {
        Estudiante estudiante = buscarPorId(id);
        estudiante.setNombre(datos.getNombre());
        estudiante.setCorreo(datos.getCorreo());
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void eliminar(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado con id: " + id);
        }
        estudianteRepository.deleteById(id);
    }

    @Override
    public Estudiante inscribirCurso(Long estudianteId, Long cursoId) {
        Estudiante estudiante = buscarPorId(estudianteId);
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + cursoId));
        if (estudiante.getCursos().contains(curso)) {
            throw new RuntimeException("El estudiante ya está inscrito en el curso con id: " + cursoId);
        }
        estudiante.getCursos().add(curso);
        return estudianteRepository.save(estudiante);
    }

    @Override
    public Estudiante retirarCurso(Long estudianteId, Long cursoId) {
        Estudiante estudiante = buscarPorId(estudianteId);
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + cursoId));
        if (!estudiante.getCursos().contains(curso)) {
            throw new RuntimeException("El estudiante no está inscrito en el curso con id: " + cursoId);
        }
        estudiante.getCursos().remove(curso);
        return estudianteRepository.save(estudiante);
    }
}