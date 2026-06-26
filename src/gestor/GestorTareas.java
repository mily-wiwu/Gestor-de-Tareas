package gestor;
import modelo.*;
import util.*;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

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
                tarea.setUsuario(usuario);
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

    public int totalTareas() {
        return tareas.size();
    }

    public int totalPendientes() {
        List<Tarea> pendientes = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado() == EstadoTarea.PENDIENTE) {
                pendientes.add(tarea);
            }
        }
        return pendientes.size();
    }

    public int totalProgreso() {
        List<Tarea> progreso = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado()==EstadoTarea.EN_PROGRESO) {
                progreso.add(tarea);
            }
        }
        return progreso.size();
    }

    public int totalCompletadas() {
        List<Tarea> completadas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado()==EstadoTarea.COMPLETADA) {
                completadas.add(tarea);
            }
        }

        return completadas.size();
    }

    public int totalCanceladas() {
        List<Tarea> canceladas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado()==EstadoTarea.CANCELADA) {
                canceladas.add(tarea);
            }
        }
        return canceladas.size();
    }

    public int totalVencidas() {
        List<Tarea> vencidas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.VENCIDA) {
                vencidas.add(tarea);
            }
        }
        return vencidas.size();
    }

    public int totalProximas() {
        List<Tarea> proximas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.PROXIMA_A_VENCER) {
                proximas.add(tarea);
            }
        }
        return proximas.size();
    }

    public int totalHoy() {
        List<Tarea> hoy = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.VENCE_HOY){
                hoy.add(tarea);
            }
        }
        return hoy.size();
    }

    public int totalAlta() {
        List<Tarea> altas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getPrioridad()==Prioridad.ALTA) {
                altas.add(tarea);
            }
        }
        return altas.size();
    }

    public int totalMedia() {
        List<Tarea> medias = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getPrioridad()==Prioridad.MEDIA) {
                medias.add(tarea);
            }
        }
        return medias.size();
    }

    public int totalBaja() {
        List<Tarea> bajas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getPrioridad()==Prioridad.BAJA) {
                bajas.add(tarea);
            }
        }
        return bajas.size();
    }

    public int contadorCategoria(Categoria categoria) {
        int contador = 0;
        for (Tarea tarea : tareas) {
            if (tarea.getCategoria().equals(categoria)) {
                contador++;
            }
        }
        return contador;
    }

    public ArrayList<Categoria> getCategorias() {
        return new ArrayList<>(categorias);
    }

    public int contadorUsuario(Usuario usuario) {
        int contador = 0;
        for (Tarea tarea : tareas) {
            if (tarea.getUsuario()!= null && tarea.getUsuario().getId()==usuario.getId()) {
                contador++;
            }
        }
        return contador;
    }

    public ArrayList<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public void exportarUsuario() {
        try (FileWriter writer = new FileWriter("usuario.txt")) {
            for (Usuario usuario : usuarios) {
                writer.write(usuario.getId() + ";" + usuario.getNombre());
                writer.write("\n");
            }
            System.out.println(Colores.EXITO + "Usuarios exportados exitosamente" + Colores.RESET);
        } catch (IOException e) {
            System.out.println(Colores.ERROR + "Error de exportacion: " + e.getMessage() + Colores.RESET);
        }
    }

    public void exportarCategoria() {
        try (FileWriter writer = new FileWriter("categoria.txt")){
            for (Categoria categoria : categorias) {
                writer.write(categoria.getId() + ";" + categoria.getNombre() + categoria.getDescripcion());
                writer.write("\n");
            }
            System.out.println(Colores.EXITO + "Categorias exportadas existosamente" + Colores.RESET);
        } catch (IOException e) {
            System.out.println(Colores.ERROR + "Error de exportacion: " + e.getMessage() + Colores.RESET);
        }
    }

    public void exportarTareas() {
        try (FileWriter writer = new FileWriter("tareas.txt")) {
            for (Tarea tarea : tareas) {
                writer.write(tarea.getId() + ";" + tarea.getTitulo() + ";" + tarea.getDescripcion() + ";" + tarea.getFechaCreacion() + ";" + tarea.getFechaLimite() + ";" + tarea.getEstado() + ";" + tarea.getPrioridad() + ";" + tarea.getCategoria() + ";" + tarea.getUsuario());
                writer.write("\n");
            }
            System.out.println(Colores.EXITO + "Tareas exportadas exitosamente" + Colores.RESET);
        } catch (IOException e){
            System.out.println(Colores.ERROR + "Error de exportacion: " + e.getMessage() + Colores.RESET);
        }
    }

    public void exportarTodo(){
        exportarTareas();
        exportarUsuario();
        exportarCategoria();
    }
}
