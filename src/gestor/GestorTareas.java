package gestor;
import modelo.*;
import util.*;

import java.util.*;

public class GestorTareas {
    private static GestorTareas instancia;
    private Set<Usuario> usuarios;
    private Set<Categoria> categorias;
    private Set<Tarea> tareas;

    private GestorTareas() {
        usuarios = new HashSet<>();
        categorias = new HashSet<>();
        tareas = new HashSet<>();
    }

    public static GestorTareas getInstancia(){
        if (instancia==null) {
            instancia = new GestorTareas();
        }
        return instancia;
    }

    public boolean agregarTarea(Tarea tarea) {
        return tareas.add(tarea);
    }

    public boolean agregarUsuario(Usuario usuario) {
        return usuarios.add(usuario);
    }

    public Optional<Usuario> buscarUsuario(int id){
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return Optional.of(usuario);
            }
        }
        return Optional.empty();
    }

    public boolean agregarCategoria(Categoria categoria){
       return categorias.add(categoria);
    }

    public Optional<Tarea> buscarTarea(int id){
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return Optional.of(tarea);
            }
        }
        return Optional.empty();
    }


    public Optional<Categoria> buscarCategoria(int id){
        for (Categoria categoria : categorias) {
            if (categoria.getId() == id) {
                return Optional.of(categoria);
            }
        }
        return Optional.empty();
    }

    public void asignarTarea(int idUsuario, Tarea tarea){
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == (idUsuario)) {
                usuario.agregarTarea(tarea);
            }
        }
    }

    public List<Tarea> buscarPorPrioridad(Prioridad prioridad){
        List<Tarea> tareasPrioridad = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getPrioridad() == prioridad) {
                tareasPrioridad.add(tarea);
            }
        }

        return tareasPrioridad;
    }

    public List<Tarea> buscarPorEstado(EstadoTarea estado){
        List<Tarea> tareasEstado = new ArrayList<>();
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

    public List<Tarea> obtenerTareasPendientes(){
        List<Tarea> pendientes = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado() == EstadoTarea.PENDIENTE) {
                pendientes.add(tarea);
            }
        }

        return pendientes;
    }

    public List<Tarea> obtenerTareasCompletadas(){
        List<Tarea> completadas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado() == EstadoTarea.COMPLETADA) {
                completadas.add(tarea);
            }
        }

        return completadas;
    }

    public void eliminarTarea(int idTarea) {
        Optional<Tarea> tarea = buscarTarea(idTarea);
        tarea.ifPresent(tareas::remove);
    }

    public List<Tarea> mostrarCategoria(int idCategoria) {
        List<Tarea> resultado = new ArrayList<>();

        for (Tarea tarea : tareas) {
            if (tarea.getCategoria() != null &&
                    tarea.getCategoria().getId() == idCategoria) {

                resultado.add(tarea);
            }
        }

        return resultado;
    }

    public List<Tarea> obtenerTareasVencidas() {
        List<Tarea> vencidas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.VENCIDA) {
                vencidas.add(tarea);
            }
        }
        return vencidas;
    }

    public List<Tarea> obtenerTareasProximas() {
        List<Tarea> proximas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.PROXIMA_A_VENCER) {
                proximas.add(tarea);
            }
        }
        return proximas;
    }

    public List<Tarea> obtenerVenceHoy() {
        List<Tarea> venceHoy = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.VENCE_HOY) {
                venceHoy.add(tarea);
            }
        }

        return venceHoy;
    }

    public List<Tarea> obtenerEnPlazo() {
        List<Tarea> enPlazo = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.EN_PLAZO){
                enPlazo.add(tarea);
            }
        }
        return enPlazo;
    }

    public List<Tarea> obtenerTareasFuturas() {
        List<Tarea> tareaFutura = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.esFutura()) {
                tareaFutura.add(tarea);
            }
        }

        tareaFutura.sort(Comparator.comparing(Tarea::getFechaLimite));
        return tareaFutura;
    }
}
