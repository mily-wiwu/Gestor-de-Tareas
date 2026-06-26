package menus;

import gestor.GestorTareas;
import modelo.*;
import util.*;
import java.util.*;

public class MenuConsultas {
    private Scanner tcld;

    public MenuConsultas(Scanner tcld){
        this.tcld = tcld;
    }

    public void menuConsultas(){
        int opcion;
        do {
            System.out.println(Colores.TITULO + "\n---MENU DE CONSULTAS---" + Colores.RESET);
            System.out.println(Colores.MENU + "1. Ver todas las tareas" + Colores.RESET);
            System.out.println(Colores.MENU + "2. Buscar tarea" + Colores.RESET);
            System.out.println(Colores.MENU + "3. Buscar por prioridad" + Colores.RESET);
            System.out.println(Colores.MENU + "4. Buscar por estado" + Colores.RESET);
            System.out.println(Colores.MENU + "5. Mostrar tareas por usuario" + Colores.RESET);
            System.out.println(Colores.MENU + "6. Mostrar tareas por categoria" + Colores.RESET);
            System.out.println(Colores.MENU + "7. Ver tareas pendientes" + Colores.RESET);
            System.out.println(Colores.MENU + "8. Ver tareas completadas" + Colores.RESET);
            System.out.println(Colores.MENU + "9. Ver tareas en plazo" + Colores.RESET);
            System.out.println(Colores.MENU + "10. Ver tareas que vencen hoy" + Colores.RESET);
            System.out.println(Colores.MENU + "11. Ver tareas proximas a vencer" + Colores.RESET);
            System.out.println(Colores.MENU + "12. Ver tareas vencidas" + Colores.RESET);
            System.out.println(Colores.MENU + "13. Ver tareas futuras" + Colores.RESET);
            System.out.println(Colores.MENU + "14. Salir" + Colores.RESET);
            System.out.print(Colores.INGRESO + "\nIngrese una opcion: " + Colores.RESET);
            opcion = tcld.nextInt();
            tcld.nextLine();

            switch (opcion){
                case 1:
                    mostrarTareas();
                    return;
                case 2:
                    buscadorTareas();
                    return;
                case 3:
                    tareasPrioridad();
                    return;
                case 4:
                    tareasEstado();
                    return;
                case 5:
                    tareasUsuario();
                    return;
                case 6:
                    tareasCategoria();
                    return;
                case 7:
                    tareasPendientes();
                    return;
                case 8:
                    tareasCompletas();
                    return;
                case 9:
                    tareasEnPlazo();
                    return;
                case 10:
                    tareasHoy();
                    return;
                case 11:
                    tareasProximas();
                    return;
                case 12:
                    tareasVencidas();
                    return;
                case 13:
                    tareasFuturas();
                    return;
                case 14:
                    System.out.println(Colores.INFO + "SALIENDO..." + Colores.RESET);
                    System.out.println(" ");
                    return;
                default:
                    System.out.println(Colores.ERROR + "Opcion no valida" + Colores.RESET);
                    System.out.println(" ");
                    break;
            }
        }while (opcion != 14);
    }

    public void mostrarTareas() {
        GestorTareas.getInstancia().listarTareas();
        System.out.println(" ");
    }

    public void buscadorTareas(){
        System.out.println(Colores.INGRESO  + "Ingrese el ID de la tarea: " + Colores.RESET);
        int idTarea = tcld.nextInt();
        tcld.nextLine();
        Optional<Tarea> tareaEncontrada = GestorTareas.getInstancia().buscarTarea(idTarea);

        if (tareaEncontrada.isPresent()) {
            System.out.println(tareaEncontrada);
            System.out.println(" ");
        } else {
            System.out.println(Colores.ERROR + "Tarea no encontrada" + Colores.RESET);
            System.out.println(" ");
        }
    }

    public void tareasPrioridad(){
        System.out.print(Colores.INGRESO + "Ingrese la prioridad (Baja, Media, Alta): " + Colores.RESET);
        String prioridad = tcld.nextLine().toUpperCase();
        while (!prioridad.equals("ALTA") && !prioridad.equals("MEDIA") && !prioridad.equals("BAJA")){
            System.out.println(Colores.ERROR + "ERROR! La prioridad debe ser baja, media o alta" + Colores.RESET);
            System.out.print(Colores.INGRESO + "Ingrese la prioridad (Baja, Media, Alta): " + Colores.RESET);
            prioridad = tcld.nextLine().toUpperCase();
        }

        Prioridad prioridadEnum;
        if (prioridad.equals("ALTA")) {
            prioridadEnum = Prioridad.ALTA;
        } else if (prioridad.equals("MEDIA")) {
            prioridadEnum = Prioridad.MEDIA;
        } else {
            prioridadEnum = Prioridad.BAJA;
        }

        List<Tarea> prioridades = GestorTareas.getInstancia().buscarPorPrioridad(prioridadEnum);
        if (prioridades.isEmpty()) {
            System.out.println(Colores.ERROR + "No se encontraron tareas" + Colores.RESET);
            System.out.println(" ");
        } else {
            for (int i=0;i<prioridades.size();i++) {
                System.out.println(prioridades.get(i));
            }
            System.out.println(" ");
        }
    }

    private void tareasEstado(){
        System.out.print(Colores.INGRESO + "Ingrese el estado (Pendiente, en progreso, completada, cancelada): " + Colores.RESET);
        String estadoString = tcld.nextLine().toUpperCase().replace(" ", "_");
        while (!estadoString.equals("PENDIENTE") && !estadoString.equals("EN_PROGRESO") && !estadoString.equals("COMPLETADA") && !estadoString.equals("CANCELADA")){
            System.out.println(Colores.ERROR + "ERROR! El estado debe ser pendiente, en progreso, completada, cancelada" + Colores.RESET);
            System.out.print(Colores.INGRESO + "Ingrese el estado (Pendiente, en progreso, completada, cancelada): " + Colores.RESET);
            estadoString = tcld.nextLine().toUpperCase().replace(" ", "_");
        }

        EstadoTarea estado;
        if (estadoString.equals("PENDIENTE")) {
            estado = EstadoTarea.PENDIENTE;
        } else if (estadoString.equals("EN_PROGRESO")) {
            estado = EstadoTarea.EN_PROGRESO;
        } else if (estadoString.equals("COMPLETADA")) {
            estado = EstadoTarea.COMPLETADA;
        } else {
            estado = EstadoTarea.CANCELADA;
        }

        List<Tarea> estados = GestorTareas.getInstancia().buscarPorEstado(estado);
        if (estados.isEmpty()) {
            System.out.println(Colores.ERROR + "No se encontraron tareas" + Colores.RESET);
            System.out.println(" ");
        } else {
            for (int i=0;i<estados.size();i++) {
                System.out.println(estados.get(i));
            }
            System.out.println(" ");
        }
    }

    private void tareasUsuario(){
        System.out.print(Colores.INGRESO + "Ingrese el id del usuario: " + Colores.RESET);
        int idUsuario = tcld.nextInt();
        tcld.nextLine();
        Optional<Usuario> usuarioEncontrado = GestorTareas.getInstancia().buscarUsuario(idUsuario);
        if (usuarioEncontrado.isPresent()) {
            Collection<Tarea> tareasUsuario = usuarioEncontrado.get().tareasUsuario();

            if (tareasUsuario.isEmpty()) {
                System.out.println(Colores.ERROR + "No se encontraron tareas" + Colores.RESET);
                System.out.println(" ");
            } else {
                for (Tarea t : tareasUsuario ){
                    System.out.println(t);
                }
                System.out.println(" ");
            }
        } else {
            System.out.println(Colores.ERROR + "El usuario no existe" + Colores.RESET);
            System.out.println(" ");

        }
    }

    public void tareasCategoria() {
        System.out.println(Colores.TITULO + "CATEGORIAS" + Colores.RESET);
        GestorTareas.getInstancia().listarCategorias();
        System.out.print(Colores.INGRESO + "Ingrese el ID de la categoria a mostrar: " + Colores.RESET);
        int idCategoria = tcld.nextInt();
        tcld.nextLine();
        Optional<Categoria> categoriaSeleccionada = GestorTareas.getInstancia().buscarCategoria(idCategoria);
        if (categoriaSeleccionada.isPresent()) {
            List<Tarea> tareasCategoria = GestorTareas.getInstancia().mostrarCategoria(idCategoria);
            if (tareasCategoria.isEmpty()) {
                System.out.println(Colores.ERROR + "No se encontraron tareas" + Colores.RESET);
                System.out.println(" ");
            } else {
                for (Tarea t : tareasCategoria) {
                    System.out.println(t);
                }
                System.out.println(" ");
            }
        }
    }

    public void tareasPendientes(){
        List<Tarea> pendientes = GestorTareas.getInstancia().obtenerTareasPendientes();
        if (pendientes.isEmpty()) {
            System.out.println(Colores.ERROR + "No se encontraron tareas" + Colores.RESET);
            System.out.println(" ");
        } else {
            for (int i=0;i<pendientes.size();i++) {
                System.out.println(pendientes.get(i));
            }
            System.out.println(" ");
        }
    }

    public void tareasCompletas(){
        List<Tarea> completadas = GestorTareas.getInstancia().obtenerTareasCompletadas();
        if (completadas.isEmpty()) {
            System.out.println(Colores.ERROR + "No se encontraron tareas" + Colores.RESET);
            System.out.println(" ");
        } else {
            for (int i=0;i<completadas.size();i++) {
                System.out.println(completadas.get(i));
            }
            System.out.println(" ");
        }
    }

    public void tareasVencidas() {
        List<Tarea> vencidas = GestorTareas.getInstancia().obtenerTareasVencidas();
        if (vencidas.isEmpty()) {
            System.out.println(Colores.ERROR + "No se encontraron tareas" + Colores.RESET);
            System.out.println(" ");
        } else {
            for (int i = 0; i < vencidas.size(); i++) {
                System.out.println(Colores.ERROR + vencidas.get(i) + Colores.RESET);
            }
            System.out.println(" ");
        }
    }

    public void tareasHoy() {
        List<Tarea> venceHoy = GestorTareas.getInstancia().obtenerVenceHoy();
        if (venceHoy.isEmpty()) {
            System.out.println(Colores.ERROR + "No se encontraron tareas" + Colores.RESET);
            System.out.println(" ");
        } else {
            for (int i=0;i<venceHoy.size();i++) {
                System.out.println(Colores.ADVERTENCIA + venceHoy.get(i) + Colores.RESET);
            }
            System.out.println(" ");
        }
    }

    public void tareasProximas() {
        List<Tarea> proximas = GestorTareas.getInstancia().obtenerTareasProximas();
        if (proximas.isEmpty()) {
            System.out.println(Colores.ERROR + "No se encontraron tareas" + Colores.RESET);
            System.out.println(" ");
        } else {
            for (int i=0;i<proximas.size();i++) {
                System.out.println(Colores.ADVERTENCIA + proximas.get(i) + Colores.RESET);
            }
            System.out.println(" ");
        }
    }

    public void tareasEnPlazo() {
        List<Tarea> enPlazo = GestorTareas.getInstancia().obtenerEnPlazo();
        if (enPlazo.isEmpty()) {
            System.out.println(Colores.ERROR + "No se encontraron tareas" + Colores.RESET);
            System.out.println(" ");
        } else {
            for (int i=0;i<enPlazo.size();i++) {
                System.out.println(Colores.EXITO + enPlazo.get(i) + Colores.RESET);
            }
            System.out.println(" ");
        }
    }

    public void tareasFuturas() {
        List<Tarea> futuras = GestorTareas.getInstancia().obtenerTareasFuturas();
        if (futuras.isEmpty()) {
            System.out.println(Colores.ERROR + "No se encontraron tareas" + Colores.RESET);
            System.out.println(" ");
        } else {
            for (int i=0;i<futuras.size();i++) {
                System.out.println(Colores.ADVERTENCIA + futuras.get(i) + Colores.RESET);System.out.println(" ");
            }
            System.out.println(" ");

        }
    }
}
