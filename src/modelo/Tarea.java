package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Tarea {
    private int id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaLimite;
    private EstadoTarea estado;
    private Prioridad prioridad;
    private Categoria categoria;
    private ArrayList<Tarea> tareas;

    public Tarea(int id, String titulo, String descripcion, LocalDate fechaCreacion, LocalDate fechaLimite, EstadoTarea estado, Prioridad prioridad, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.prioridad = prioridad;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
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

    public void completar() {
        estado = EstadoTarea.COMPLETADA;
    }

    public void cancelar() {
        estado = EstadoTarea.CANCELADA;
    }

    public void empezar() {
        estado = EstadoTarea.EN_PROGRESO;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaLimite(LocalDate fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public SituacionTemporal getSituacionTemporal() {
        SituacionTemporal situacion;
        if (fechaLimite.isBefore(LocalDate.now())) {
            situacion = SituacionTemporal.VENCIDA;
        } else if (fechaLimite.equals(LocalDate.now())) {
            situacion = SituacionTemporal.VENCE_HOY;
        } else if (!fechaLimite.isBefore(LocalDate.now()) && !fechaLimite.isAfter(LocalDate.now().plusDays(3))) {
            situacion = SituacionTemporal.PROXIMA_A_VENCER;
        } else {
            situacion = SituacionTemporal.EN_PLAZO;
        }
        return situacion;
    }

    public long diasRestantes() {
        return ChronoUnit.DAYS.between(LocalDate.now(), fechaLimite);
    }

    public boolean esFutura() {
        return fechaLimite.isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
        return """
               ID : %d
               Titulo: %s
               Descripcion: %s
               Estado: %s
               Prioridad: %s
               Fecha Limite: %s 
               """.formatted(id, titulo, descripcion, estado, prioridad, fechaLimite);
    }
}
