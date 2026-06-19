import java.util.ArrayList;

public class GestorTareas {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Categoria> categorias;
    private ArrayList<Tarea> tareas;

    public GestorTareas() {
        usuarios = new ArrayList<>();
        categorias = new ArrayList<>();
        tareas = new ArrayList<>();
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
        System.out.println("Tarea agregada correctamente.");
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuario(int id){
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return  null;
    }

    public void agregarCategoria(Categoria categoria){
        categorias.add(categoria);
    }

    public Tarea buscarTarea(int id){
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return tarea;
            }
        }
        return null;
    }


    public Categoria buscarCategoria(int id){
        for (Categoria categoria : categorias) {
            if (categoria.getId() == id) {
                return categoria;
            }
        }
        return null;
    }

    public void asignarTarea(int idUsuario, Tarea tarea){
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == (idUsuario)) {
                usuario.agregarTarea(tarea);
            }
        }
    }

    public ArrayList<Tarea> buscarPorPrioridad(Prioridad prioridad){
        ArrayList<Tarea> tareasPrioridad = new ArrayList<Tarea>();
        for (Tarea tarea : tareas) {
            if (tarea.getPrioridad() == prioridad) {
                tareasPrioridad.add(tarea);
            }
        }

        return tareasPrioridad;
    }

    public ArrayList<Tarea> buscarPorEstado(EstadoTarea estado){
        ArrayList<Tarea> tareasEstado = new ArrayList<Tarea>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado() == estado) {
                tareasEstado.add(tarea);
            }
        }
        return tareasEstado;
    }

    public void listarCategorias() {
        for (Categoria categoria : categorias)
            System.out.println(categoria);
    }

    public void listarTareas() {
        for (Tarea tarea : tareas) {
            System.out.println(tarea);
            System.out.println("------------");
        }
    }

    public ArrayList<Tarea> obtenerTareasPendientes(){
        ArrayList<Tarea> pendientes = new ArrayList<Tarea>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado() == EstadoTarea.PENDIENTE) {
                pendientes.add(tarea);
            }
        }

        return pendientes;
    }

    public ArrayList<Tarea> obtenerTareasCompletadas(){
        ArrayList<Tarea> completadas = new ArrayList<Tarea>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado() == EstadoTarea.COMPLETADA) {
                completadas.add(tarea);
            }
        }

        return completadas;
    }
}
