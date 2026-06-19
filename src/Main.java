//El programa asume la fecha actual

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        GestorTareas gestor = new GestorTareas();
        Scanner tcld = new Scanner(System.in);
        System.out.println("---MENU PRINCIPAL---");
        System.out.println("1. Crear usuario");
        System.out.println("2. Crear categoria");
        System.out.println("3. Crear tarea");
        System.out.println("4. Asignar tarea");
        System.out.println("5. Eliminar asignacion de tarea");
        System.out.println("6. Empezar Tarea");
        System.out.println("7. Completar tarea");
        System.out.println("8. Cancelar");
        System.out.println("9. Ver tareas");
        System.out.println("10. Buscar tareas");
        System.out.println("11. Buscar tareas por prioridad");
        System.out.println("12. Buscar tareas por estado");
        System.out.println("13. Obtener tareas pendientes");
        System.out.println("14. Obtener tareas completadas");
        System.out.println("15. Salir");
        System.out.print("\nIngrese una opcion: ");
        int opcion = tcld.nextInt();
        tcld.nextLine();

        do {
           switch (opcion) {
               case 1:
                   System.out.print("Ingrese el ID del usuario (RUT sin puntos ni guion): ");
                   int id = tcld.nextInt();
                   tcld.nextLine();
                   Usuario usuario = new Usuario(id);
                   gestor.agregarUsuario(usuario);
                   break;
               case 2:
                   System.out.print("Ingrese el ID de la categoria: ");
                   id = tcld.nextInt();
                   tcld.nextLine();
                   System.out.print("Ingrese el nombre de la categoria: ");
                   String nombre = tcld.nextLine();
                   System.out.print("Ingrese una descripcion breve de la categoria: ");
                   String descripcion = tcld.nextLine();

                   Categoria categoria = new Categoria(id, nombre, descripcion);
                   gestor.agregarCategoria(categoria);
                   break;
               case 3:
                   System.out.print("Ingrese el ID de la tarea: ");
                   id = tcld.nextInt();
                   tcld.nextLine();
                   System.out.print("Ingrese el titulo de la tarea: ");
                   String titulo = tcld.nextLine();
                   System.out.print("Ingrese una descripcion de la tarea: ");
                   descripcion = tcld.nextLine();
                   LocalDate fechaCreacion = LocalDate.now();
                   System.out.print("Ingrese el dia limite: ");
                   int diaLimite = tcld.nextInt();
                   System.out.print("Ingrese el mes limite: ");
                   int mesLimite = tcld.nextInt();
                   System.out.print("Ingrese el anio limite: ");
                   int anioLimite = tcld.nextInt();
                   tcld.nextLine();
                   LocalDate fechaLimite = LocalDate.of(anioLimite, mesLimite, diaLimite);
                   EstadoTarea estado = EstadoTarea.PENDIENTE;
                   System.out.print("Ingrese la prioridad (Baja, Media, Alta): ");
                   String prioridad = tcld.nextLine().toUpperCase();
                   while (!prioridad.equals("ALTA") && !prioridad.equals("MEDIA") && !prioridad.equals("BAJA")){
                       System.out.println("ERROR! La prioridad debe ser baja, media o alta");
                       System.out.print("Ingrese la prioridad (Baja, Media, Alta): ");
                       prioridad = tcld.nextLine();
                   }
                   Prioridad prioridad1;
                   if (prioridad.equals("ALTA")) {
                       prioridad1 = Prioridad.ALTA;
                   } else if (prioridad.equals("MEDIA")) {
                       prioridad1 = Prioridad.MEDIA;
                   } else {
                       prioridad1 = Prioridad.BAJA;
                   }
                   System.out.println("CATEGORIAS");
                   gestor.listarCategorias();
                   categoria = null;
                   while (categoria==null) {
                       System.out.print("Ingrese el ID una categoria: ");
                       int idCategoria = tcld.nextInt();
                       tcld.nextLine();
                       Categoria encontrada = gestor.buscarCategoria(idCategoria);
                       if (encontrada!= null) {
                           categoria = encontrada;
                       } else {
                           System.out.println("La categoria no existe");
                       }
                   }

                   Tarea tarea = new Tarea(id, titulo, descripcion, fechaCreacion, fechaLimite, estado, prioridad1, categoria);
                   gestor.agregarTarea(tarea);
                   break;
               case 4:
                   System.out.print("Ingrese el ID del usuario: ");
                   int idUsuario = tcld.nextInt();
                   tcld.nextLine();
                   Usuario encontrado = gestor.buscarUsuario(idUsuario);
                   if (encontrado!=null) {
                       System.out.print("Ingrese el ID de la tarea a asignar: ");
                       int idTarea = tcld.nextInt();
                       tcld.nextLine();
                       tarea = gestor.buscarTarea(idTarea);
                       if (tarea!=null) {
                           gestor.asignarTarea(idUsuario, tarea);
                           System.out.println("Tarea asignada");
                       } else {
                           System.out.println("La tarea no existe");
                       }
                   } else {
                       System.out.println("El usuario no existe");
                   }
                   break;
               case 5:
                   System.out.print("Ingrese el id del usuario: ");
                   idUsuario = tcld.nextInt();
                   encontrado = gestor.buscarUsuario(idUsuario);
                   if (encontrado!=null) {
                       System.out.print("Ingrese el ID de la tarea a eliminar: ");
                       id = tcld.nextInt();
                       Tarea tareaEncontrada = gestor.buscarTarea(id);

                       if (tareaEncontrada!=null) {
                           encontrado.eliminarTarea(id);
                           System.out.println("Tarea eliminada para el usuario");
                       } else {
                           System.out.println("La tarea no existe");
                       }
                   } else {
                       System.out.println("El usuario no existe");
                   }
                   break;
               case 6:
                   System.out.print("Ingrese el ID de la tarea: ");
                   id = tcld.nextInt();
                   tcld.nextLine();

                   tarea = gestor.buscarTarea(id);
                   if (tarea!=null) {
                       tarea.empezar();
                       System.out.println("Tarea empezada");
                   } else {
                       System.out.println("La tarea no existe");
                   }
                   break;
               case 7:
                    System.out.print("Ingrese el ID de la tarea: ");
                    id = tcld.nextInt();
                    tcld.nextLine();

                    tarea = gestor.buscarTarea(id);
                    if (tarea!=null) {
                        tarea.completar();
                        System.out.println("Tarea completada");
                    } else {
                        System.out.println("La tarea no existe");
                    }
                    break;
               case 8:
                   System.out.print("Ingrese el ID de la tarea: ");
                   id = tcld.nextInt();
                   tcld.nextLine();

                   tarea = gestor.buscarTarea(id);
                   if (tarea!=null) {
                       tarea.cancelar();
                       System.out.println("Tarea cancelada");
                   } else {
                       System.out.println("La tarea no existe");
                   }
                   break;
               case 9:
                   gestor.listarTareas();
                   break;
               case 10:
                   System.out.println("Ingrese el ID de la tarea: ");
                   id = tcld.nextInt();
                   tcld.nextLine();
                   Tarea tareaEncontrada = gestor.buscarTarea(id);

                   if (tareaEncontrada!= null) {
                       System.out.println("Tarea encontrada");
                   } else {
                       System.out.println("Tarea no encontrada");
                   }
                   break;
               case 11:
                   System.out.print("Ingrese la prioridad (Baja, Media, Alta): ");
                   prioridad = tcld.nextLine().toUpperCase();
                   while (!prioridad.equals("ALTA") && !prioridad.equals("MEDIA") && !prioridad.equals("BAJA")){
                       System.out.println("ERROR! La prioridad debe ser baja, media o alta");
                       System.out.print("Ingrese la prioridad (Baja, Media, Alta): ");
                       prioridad = tcld.nextLine().toUpperCase();
                   }

                   if (prioridad.equals("ALTA")) {
                       prioridad1 = Prioridad.ALTA;
                   } else if (prioridad.equals("MEDIA")) {
                       prioridad1 = Prioridad.MEDIA;
                   } else {
                       prioridad1 = Prioridad.BAJA;
                   }

                   ArrayList<Tarea> prioridades = gestor.buscarPorPrioridad(prioridad1);
                   if (prioridades.isEmpty()) {
                       System.out.println("No se encontraron tareas");
                   } else {
                       for (int i=0;i<prioridades.size();i++) {
                           System.out.println(prioridades.get(i));
                       }
                   }
                   break;
               case 12:
                   System.out.print("Ingrese el estado (Pendiente, en progreso, completada, cancelada): ");
                   String estadoString = tcld.nextLine().toUpperCase().replace(" ", "_");
                   while (!estadoString.equals("PENDIENTE") && !estadoString.equals("EN_PROGRESO") && !estadoString.equals("COMPLETADA") && !estadoString.equals("CANCELADA")){
                       System.out.println("ERROR! El estado debe ser pendiente, en progreso, completada, cancelada");
                       System.out.print("Ingrese el estado (Pendiente, en progreso, completada, cancelada): ");
                       estadoString = tcld.nextLine().toUpperCase().replace(" ", "_");
                   }

                   EstadoTarea estadoTarea;
                   if (estadoString.equals("PENDIENTE")) {
                       estadoTarea = EstadoTarea.PENDIENTE;
                   } else if (estadoString.equals("EN_PROGRESO")) {
                       estadoTarea = EstadoTarea.EN_PROGRESO;
                   } else if (estadoString.equals("COMPLETADA")) {
                       estadoTarea = EstadoTarea.COMPLETADA;
                   } else {
                       estadoTarea = EstadoTarea.CANCELADA;
                   }

                   ArrayList<Tarea> estados = gestor.buscarPorEstado(estadoTarea);
                   if (estados.isEmpty()) {
                       System.out.println("No se encontraron tareas");
                   } else {
                       for (int i=0;i<estados.size();i++) {
                           System.out.println(estados.get(i));
                       }
                   }
                   break;
               case 13:
                   ArrayList<Tarea> pendientes = gestor.obtenerTareasPendientes();
                   if (pendientes.isEmpty()) {
                       System.out.println("No se encontraron tareas");
                   } else {
                       for (int i=0;i<pendientes.size();i++) {
                           System.out.println(pendientes.get(i));
                       }
                   }
                   break;
               case 14:
                   ArrayList<Tarea> completadas = gestor.obtenerTareasCompletadas();
                   if (completadas.isEmpty()) {
                       System.out.println("No se encontraron tareas");
                   } else {
                       for (int i=0;i<completadas.size();i++) {
                           System.out.println(completadas.get(i));
                       }
                   }
                   break;
               case 15:
                   System.out.println("SALIENDO...");
                   break;
               default:
                   System.out.println("La opcion ingresada no es valida");
           }
           System.out.print("\nIngrese una opcion: ");
           opcion = tcld.nextInt();
           tcld.nextLine();
        } while (opcion!=15);


    }
}