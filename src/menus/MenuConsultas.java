package menus;

import gestor.GestorTareas;
import modelo.*;

import java.util.Scanner;
import java.util.ArrayList;

public class MenuConsultas {
    private GestorTareas gestor;
    private Scanner tcld;

    public MenuConsultas(GestorTareas gestor, Scanner tcld){
        this.gestor = gestor;
        this.tcld = tcld;
    }

    public void menuConsultas(){
        int opcion;
        do {
            System.out.println("---MENU DE CONSULTAS---");
            System.out.println("1. Ver todas las tareas");
            System.out.println("2. Buscar tarea");
            System.out.println("3. Buscar por prioridad");
            System.out.println("4. Buscar por estado");
            System.out.println("5. Mostrar tareas por usuario");
            System.out.println("6. Mostrar tareas por categoria");
            System.out.println("7. Ver tareas pendientes");
            System.out.println("8. Ver tareas completadas");
            System.out.println("9. Salir");
            System.out.print("\nIngrese una opcion: ");
            opcion = tcld.nextInt();
            tcld.nextLine();

            switch (opcion){
                case 1:
                    mostrarTareas();
                    break;
                case 2:
                    buscadorTareas();
                    break;
                case 3:
                    tareasPrioridad();
                    break;
                case 4:
                    tareasEstado();
                    break;
                case 5:
                    tareasUsuario();
                    break;
                case 6:
                    tareasCategoria();
                    break;
                case 7:
                    tareasPendientes();
                    break;
                case 8:
                    ArrayList<Tarea> completadas = gestor.obtenerTareasCompletadas();
                    if (completadas.isEmpty()) {
                        System.out.println("No se encontraron tareas");
                    } else {
                        for (int i=0;i<completadas.size();i++) {
                            System.out.println(completadas.get(i));
                        }
                    }
                    break;
                case 9:
                    System.out.println("SALIENDO...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while (opcion != 9);
    }

    public void mostrarTareas() {
        gestor.listarTareas();
    }

    public void buscadorTareas(){
        System.out.println("Ingrese el ID de la tarea: ");
        int idTarea = tcld.nextInt();
        tcld.nextLine();
        Tarea tareaEncontrada = gestor.buscarTarea(idTarea);

        if (tareaEncontrada!= null) {
            System.out.println(tareaEncontrada);
        } else {
            System.out.println("modelo.Tarea no encontrada");
        }
    }

    public void tareasPrioridad(){
        System.out.print("Ingrese la prioridad (Baja, Media, Alta): ");
        String prioridad = tcld.nextLine().toUpperCase();
        while (!prioridad.equals("ALTA") && !prioridad.equals("MEDIA") && !prioridad.equals("BAJA")){
            System.out.println("ERROR! La prioridad debe ser baja, media o alta");
            System.out.print("Ingrese la prioridad (Baja, Media, Alta): ");
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

        ArrayList<Tarea> prioridades = gestor.buscarPorPrioridad(prioridadEnum);
        if (prioridades.isEmpty()) {
            System.out.println("No se encontraron tareas");
        } else {
            for (int i=0;i<prioridades.size();i++) {
                System.out.println(prioridades.get(i));
            }
        }
    }

    private void tareasEstado(){
        System.out.print("Ingrese el estado (Pendiente, en progreso, completada, cancelada): ");
        String estadoString = tcld.nextLine().toUpperCase().replace(" ", "_");
        while (!estadoString.equals("PENDIENTE") && !estadoString.equals("EN_PROGRESO") && !estadoString.equals("COMPLETADA") && !estadoString.equals("CANCELADA")){
            System.out.println("ERROR! El estado debe ser pendiente, en progreso, completada, cancelada");
            System.out.print("Ingrese el estado (Pendiente, en progreso, completada, cancelada): ");
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

        ArrayList<Tarea> estados = gestor.buscarPorEstado(estado);
        if (estados.isEmpty()) {
            System.out.println("No se encontraron tareas");
        } else {
            for (int i=0;i<estados.size();i++) {
                System.out.println(estados.get(i));
            }
        }
    }

    private void tareasUsuario(){
        System.out.print("Ingrese el id del usuario: ");
        int idUsuario = tcld.nextInt();
        tcld.nextLine();
        Usuario usuarioEncontrado = gestor.buscarUsuario(idUsuario);
        if (usuarioEncontrado!=null) {
            ArrayList<Tarea> tareasUsuario = usuarioEncontrado.tareasUsuario();

            if (tareasUsuario.isEmpty()) {
                System.out.println("No se encontraron tareas");
            } else {
                for (Tarea t : tareasUsuario ){
                    System.out.println(t);
                }
            }
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void tareasCategoria() {
        System.out.println("CATEGORIAS");
        gestor.listarCategorias();
        System.out.print("Ingrese el ID de la categoria a mostrar: ");
        int idCategoria = tcld.nextInt();
        tcld.nextLine();
        Categoria categoriaSeleccionada = gestor.buscarCategoria(idCategoria);
        if (categoriaSeleccionada!=null) {
            ArrayList<Tarea> tareasCategoria = gestor.mostrarCategoria(idCategoria);
            if (tareasCategoria.isEmpty()) {
                System.out.println("No se encontraron tareas");
            } else {
                for (Tarea t : tareasCategoria) {
                    System.out.println(t);
                }
            }
        }
    }

    public void tareasPendientes(){
        ArrayList<Tarea> pendientes = gestor.obtenerTareasPendientes();
        if (pendientes.isEmpty()) {
            System.out.println("No se encontraron tareas");
        } else {
            for (int i=0;i<pendientes.size();i++) {
                System.out.println(pendientes.get(i));
            }
        }
    }

    public void tareasCompletas(){
        ArrayList<Tarea> completadas = gestor.obtenerTareasCompletadas();
        if (completadas.isEmpty()) {
            System.out.println("No se encontraron tareas");
        } else {
            for (int i=0;i<completadas.size();i++) {
                System.out.println(completadas.get(i));
            }
        }
    }
}
