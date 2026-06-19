import java.time.LocalDate;

public class Tarea {
    private int id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaCreacion;
    private LocalDate fechaLimite;
    private EstadoTarea estado;
    private Prioridad prioridad;
    private Categoria categoria;

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

    public EstadoTarea getEstado() {
        return estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
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
