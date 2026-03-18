package com.example.nuevo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    @Column(nullable = false, length = 20)
    private int creditos;

    @ManyToMany(mappedBy = "cursos")
    @JsonIgnore
    private Set<Estudiante> estudiantes = new java.util.HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Curso curso)) return false;
        return java.util.Objects.equals(id, curso.id);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(id);
    }

    public Curso() {
    }

    public Curso(Long id, String nombre, int creditos, Set<Estudiante> estudiantes) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.estudiantes = estudiantes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public Set<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                ", estudiantes=" + estudiantes +
                '}';
    }
}
