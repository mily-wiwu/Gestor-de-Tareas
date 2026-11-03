package com.example.Gestor_de_tareas.servicio;

import com.example.Gestor_de_tareas.excepciones.TareaNoEncontradaException;
import com.example.Gestor_de_tareas.modelo.*;
import com.example.Gestor_de_tareas.persistencia.dao.TareaDAO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TareaService {

    private final UsuarioService usuarioService;
    private final TareaDAO tareaDAO;

    public TareaService(TareaDAO tareaDAO, UsuarioService usuarioService) {
        this.tareaDAO = tareaDAO;
        this.usuarioService = usuarioService;
    }

    public boolean agregarTarea(Tarea tarea) {
        return tareaDAO .guardar(tarea);
    }

    public Tarea buscarTarea(int id){
        return tareaDAO.buscarPorId(id).orElseThrow(() ->
                new TareaNoEncontradaException("No existe la tarea con ID " + id));
    }

    public List<Tarea> listarTareas() {
        return tareaDAO.listar();
    }

    public void asignarTarea(int idUsuario, int idTarea) {
        Usuario usuario = usuarioService.buscarUsuario(idUsuario);

        Tarea tarea = tareaDAO.buscarPorId(idTarea)
                .orElseThrow(() ->
                        new TareaNoEncontradaException(
                                "No existe la tarea con ID " + idTarea
                        ));

        tarea.setUsuario(usuario);

        tareaDAO.actualizar(tarea);
    }

    public void eliminarTarea(int idTarea) {
        tareaDAO.eliminar(idTarea);
    }

    public void modificarTarea(Tarea tarea) {
        tareaDAO.actualizar(tarea);
    }

    public List<Tarea> buscarPorPrioridad(Prioridad prioridad) {
        return tareaDAO.buscarPorPrioridad(prioridad);
    }

    public List<Tarea> buscarPorEstado(EstadoTarea estado){
        return tareaDAO.buscarPorEstado(estado);
    }

    public List<Tarea> obtenerTareasPendientes(){
        return tareaDAO.buscarPorEstado(EstadoTarea.PENDIENTE);
    }

    public List<Tarea> obtenerTareasCompletadas(){
        return tareaDAO.buscarPorEstado(EstadoTarea.COMPLETADA);
    }

    public List<Tarea> obtenerTareasProximas() {
        List<Tarea> proximas = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()== SituacionTemporal.PROXIMA_A_VENCER) {
                proximas.add(tarea);
            }
        }
        return proximas;
    }

    public List<Tarea> obtenerVenceHoy() {
        List<Tarea> venceHoy = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.VENCE_HOY) {
                venceHoy.add(tarea);
            }
        }

        return venceHoy;
    }

    public List<Tarea> obtenerEnPlazo() {
        List<Tarea> enPlazo = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.EN_PLAZO){
                enPlazo.add(tarea);
            }
        }
        return enPlazo;
    }

    public List<Tarea> obtenerTareasVencidas() {
        List<Tarea> vencidas = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.VENCIDA) {
                vencidas.add(tarea);
            }
        }
        return vencidas;
    }


    public List<Tarea> obtenerTareasFuturas() {
        List<Tarea> tareaFutura = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.esFutura()) {
                tareaFutura.add(tarea);
            }
        }

        tareaFutura.sort(Comparator.comparing(Tarea::getFechaLimite));
        return tareaFutura;
    }

    public List<Tarea> mostrarCategoria(int idCategoria) {
        return tareaDAO.buscarPorCategoria(idCategoria);
    }

    public int totalTareas() {
        return tareaDAO.contadorTareas();
    }

    public int totalPendientes() {
        return tareaDAO.contadorEstado(EstadoTarea.PENDIENTE);
    }

    public int totalProgreso() {
        return tareaDAO.contadorEstado(EstadoTarea.EN_PROGRESO);
    }

    public int totalCompletadas() {
        return tareaDAO.contadorEstado(EstadoTarea.COMPLETADA);
    }

    public int totalCanceladas() {
        return tareaDAO.contadorEstado(EstadoTarea.CANCELADA);
    }

    public int totalVencidas() {
        List<Tarea> vencidas = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.VENCIDA) {
                vencidas.add(tarea);
            }
        }
        return vencidas.size();
    }

    public int totalProximas() {
        List<Tarea> proximas = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.PROXIMA_A_VENCER) {
                proximas.add(tarea);
            }
        }
        return proximas.size();
    }

    public int totalHoy() {
        List<Tarea> hoy = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.VENCE_HOY){
                hoy.add(tarea);
            }
        }
        return hoy.size();
    }

    public int totalAlta() {
        return tareaDAO.contadorPrioridad(Prioridad.ALTA);
    }

    public int totalMedia() {
        return tareaDAO.contadorPrioridad(Prioridad.MEDIA);
    }

    public int totalBaja() {
        return tareaDAO.contadorPrioridad(Prioridad.BAJA);
    }

    public int contadorCategoria(Categoria categoria) {
        return tareaDAO.contadorCategoria(categoria.getId());
    }

    public int contadorUsuario(Usuario usuario) {
        return tareaDAO.contadorUsuario(usuario.getId());
    }

    public List<Tarea> buscarTareasPorUsuario(int idUsuario) {
        return tareaDAO.buscarPorUsuario(idUsuario);
    }

}