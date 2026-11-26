package com.example.Gestor_de_tareas.modelo;
import com.example.Gestor_de_tareas.excepciones.TareaInvalidaException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class Tarea {
    private int id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaLimite;
    private TipoAsignacionFecha tipoAsignacionFecha;
    private LocalDate fechaProgramada;
    private EstadoTarea estado;
    private Prioridad prioridad;
    private Categoria categoria;
    private Usuario usuario;

    public Tarea(int id, String titulo, String descripcion, LocalDate fechaCreacion, LocalDate fechaLimite, TipoAsignacionFecha tipoAsignacionFecha, LocalDate fechaProgramada, EstadoTarea estado, Prioridad prioridad, Categoria categoria, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
        this.tipoAsignacionFecha = tipoAsignacionFecha;
        this.fechaProgramada = fechaProgramada;
        this.estado = estado;
        this.prioridad = prioridad;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDate getFechaLimite() {
        return fechaLimite;
    }

    public EstadoTarea getEstado() {
        return estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            throw new TareaInvalidaException("El título no puede estar vacío");
        }
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new TareaInvalidaException("La descripción no puede estar vacía");
        }
        this.descripcion = descripcion;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        if (fechaLimite == null) {
            throw new TareaInvalidaException("La fecha límite es obligatoria");
        }
        if (fechaLimite.isBefore(fechaCreacion)) {
            throw new TareaInvalidaException("La fecha límite no puede ser anterior a la fecha de creación");
        }
        this.fechaLimite = fechaLimite;
    }

    public void setPrioridad(Prioridad prioridad) {
        if (prioridad == null) {
            throw new TareaInvalidaException("Debe seleccionar una prioridad");
        }
        this.prioridad = prioridad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new TareaInvalidaException("Debe seleccionar una categoría");
        }
        this.categoria = categoria;
    }

    public void setEstado(EstadoTarea estado) {
        this.estado = estado;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Usuario getUsuario(){
        return usuario;
    }

    @Override
    public String toString() {
        return """
               ID: %d
               Titulo: %s
               Descripcion: %s
               Estado: %s
               Prioridad: %s
               Fecha Limite: %s 
               """.formatted(id, titulo, descripcion, estado, prioridad, fechaLimite);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarea)) return false;
        Tarea tarea = (Tarea) o;
        return id == tarea.id;
    }

    public TipoAsignacionFecha getTipoAsignacionFecha() {
        return tipoAsignacionFecha;
    }

    public void setTipoAsignacionFecha(TipoAsignacionFecha tipoAsignacionFecha) {
        this.tipoAsignacionFecha = tipoAsignacionFecha;
    }

    public LocalDate getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(LocalDate fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
