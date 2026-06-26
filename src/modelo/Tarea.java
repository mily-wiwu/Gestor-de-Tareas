package modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

public class Tarea {
    private int id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaLimite;
    private EstadoTarea estado;
    private Prioridad prioridad;
    private Categoria categoria;
    private Set<Tarea> tareas;
    private Usuario usuario;

    public Tarea(int id, String titulo, String descripcion, LocalDate fechaCreacion, LocalDate fechaLimite, EstadoTarea estado, Prioridad prioridad, Categoria categoria, Usuario usuario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.prioridad = prioridad;
        this.categoria = categoria;
        this.usuario = getUsuario();
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

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public Usuario getUsuario(){
        return usuario;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarea)) return false;
        Tarea tarea = (Tarea) o;
        return id == tarea.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
