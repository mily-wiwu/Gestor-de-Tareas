package com.example.Gestor_de_tareas.servicio;

import com.example.Gestor_de_tareas.excepciones.TareaNoEncontradaException;
import com.example.Gestor_de_tareas.modelo.*;
import com.example.Gestor_de_tareas.persistencia.dao.TareaDAO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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

    public void asignarUsuario(int id, Usuario usuario) {
        Tarea tarea = buscarTarea(id);
        tarea.setUsuario(usuario);
        tareaDAO.actualizar(tarea);
    }

    public void eliminarTarea(int idTarea) {
        tareaDAO.eliminar(idTarea);
    }

    public void modificarTarea(Tarea tarea) {
        tareaDAO.actualizar(tarea);
    }

    public void cambiarEstado(int id, EstadoTarea estado) {
        tareaDAO.actualizarEstado(id, estado);
    }

    public List<Tarea> buscarTareas(String titulo, Integer idCategoria, Prioridad prioridad, EstadoTarea estado, String criterioOrden, String direccionOrden) {
        return tareaDAO.buscarConFiltros(titulo, idCategoria, prioridad, estado, criterioOrden, direccionOrden);
    }

    public SituacionTemporal diasRestantes(Tarea tarea, EstadoTarea estadoTarea) {
        if (estadoTarea == EstadoTarea.COMPLETADA) {
            return null;
        }

        LocalDate hoy = LocalDate.now();
        long diasRestantes = ChronoUnit.DAYS.between(hoy, tarea.getFechaLimite());

        if (diasRestantes < 0) {
            return SituacionTemporal.VENCIDA;
        } else if (diasRestantes == 0) {
            return SituacionTemporal.VENCE_HOY;
        } else if (diasRestantes >= 1 && diasRestantes <= 3) {
            return SituacionTemporal.PROXIMA_A_VENCER;
        } else {
            return SituacionTemporal.EN_PLAZO;
        }
    }

    public EstadisticasTareas calcularSituacionTareas() {
        List<Tarea> tareas = tareaDAO.listar();
        int tareasVencidas = 0;
        int tareasVenceHoy = 0;
        int tareasVenceProxima = 0;
        int tareasEnPlazo = 0;
        int tareasPendientes = 0;
        int tareasEnProgreso = 0;
        int tareasCompletadas = 0;
        int tareasCanceladas = 0;

        for (Tarea tarea : tareas) {
            SituacionTemporal situacion = diasRestantes(tarea, tarea.getEstado());
            EstadoTarea estado = tarea.getEstado();

            if (situacion == SituacionTemporal.VENCIDA) {
                tareasVencidas ++;
            } else if (situacion == SituacionTemporal.VENCE_HOY) {
                tareasVenceHoy++;
            } else if (situacion == SituacionTemporal.PROXIMA_A_VENCER) {
                tareasVenceProxima++;
            } else  if (situacion == SituacionTemporal.EN_PLAZO){
                tareasEnPlazo++;
            }

            if (estado == EstadoTarea.PENDIENTE){
                tareasPendientes++;
            } else if (estado == EstadoTarea.EN_PROGRESO) {
                tareasEnProgreso++;
            } else if (estado == EstadoTarea.COMPLETADA) {
                tareasCompletadas++;
            } else if (estado == EstadoTarea.CANCELADA){
                tareasCanceladas++;
            }
        }

        return new EstadisticasTareas(tareasVencidas, tareasVenceHoy, tareasVenceProxima, tareasEnPlazo, tareasPendientes, tareasEnProgreso, tareasCompletadas, tareasCanceladas);
    }
}