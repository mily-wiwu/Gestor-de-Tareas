package com.example.Gestor_de_tareas.modelo;

public class EstadisticasTareas {
    private int tareasVencidas;
    private int tareasVenceHoy;
    private int tareasVenceProxima;
    private int tareasEnPlazo;
    private int tareasPendientes;
    private int tareasEnProgreso;
    private int tareasCompletadas;
    private int tareasCanceladas;

    public EstadisticasTareas(int tareasVencidas, int tareasVenceHoy, int tareasVenceProxima, int tareasEnPlazo, int tareasPendientes, int tareasEnProgreso, int tareasCompletadas, int tareasCanceladas) {
        this.tareasVencidas = tareasVencidas;
        this.tareasVenceHoy = tareasVenceHoy;
        this.tareasVenceProxima = tareasVenceProxima;
        this.tareasEnPlazo = tareasEnPlazo;
        this.tareasPendientes = tareasPendientes;
        this.tareasEnProgreso = tareasEnProgreso;
        this.tareasCompletadas = tareasCompletadas;
        this.tareasCanceladas = tareasCanceladas;
    }

    public int getTareasVencidas() {
        return tareasVencidas;
    }

    public int getTareasVenceHoy() {
        return tareasVenceHoy;
    }

    public int getTareasVenceProxima() {
        return tareasVenceProxima;
    }

    public int getTareasEnPlazo() {
        return tareasEnPlazo;
    }

    public int getTareasPendientes() {
        return tareasPendientes;
    }

    public int getTareasEnProgreso() {
        return tareasEnProgreso;
    }

    public int getTareasCompletadas() {
        return tareasCompletadas;
    }

    public int getTareasCanceladas() {
        return tareasCanceladas;
    }
}
