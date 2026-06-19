public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;

    public Categoria(int id, String nombre, String descripcion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNombre: " + nombre + "\nDescripcion: " + descripcion;
    }
}
