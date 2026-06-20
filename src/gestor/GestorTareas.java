package gestor;
import modelo.*;
import util.Colores;

import java.util.ArrayList;

public class GestorTareas {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Categoria> categorias;
    private ArrayList<Tarea> tareas;
    private EstadoTarea estado;

    public GestorTareas() {
        usuarios = new ArrayList<>();
        categorias = new ArrayList<>();
        tareas = new ArrayList<>();
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
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
        for (Categoria categoria : categorias) {
            System.out.println(Colores.INFO + categoria + Colores.RESET);
            System.out.println("------------");
        }
    }

    public void listarTareas() {
        for (Tarea tarea : tareas) {
            System.out.println(Colores.INFO + tarea + Colores.RESET);
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

    public void eliminarTarea(int idTarea) {
        for (int i=0;i<tareas.size();i++) {
            if (tareas.get(i).getId() == idTarea) {
                tareas.remove(i);
                break;
            }
        }
    }

    public ArrayList<Tarea> mostrarCategoria(int idCategoria) {
        ArrayList<Tarea> resultado = new ArrayList<>();

        for (Tarea tarea : tareas) {
            if (tarea.getCategoria() != null &&
                    tarea.getCategoria().getId() == idCategoria) {

                resultado.add(tarea);
            }
        }

        return resultado;
    }
}
