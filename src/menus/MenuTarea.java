package menus;

import gestor.GestorTareas;
import modelo.*;
import util.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

public class MenuTarea {
    private GestorTareas gestor;
    private Scanner tcld;

    public MenuTarea(GestorTareas gestor, Scanner tcld) {
        this.gestor = gestor;
        this.tcld = tcld;
    }

    public void menuTarea() {
        int opcion;
        do {
            System.out.println(Colores.TITULO + "\n---MENU DE TAREA---" + Colores.RESET);
            System.out.println(Colores.MENU + "1. Crear tarea" + Colores.RESET);
            System.out.println(Colores.MENU + "2. Modificar tarea" + Colores.RESET);
            System.out.println(Colores.MENU + "3. Eliminar tarea" + Colores.RESET);
            System.out.println(Colores.MENU + "4. Asignar tarea" + Colores.RESET);
            System.out.println(Colores.MENU + "5. Eliminar asignacion" + Colores.RESET);
            System.out.println(Colores.MENU + "6. Empezar tarea" + Colores.RESET);
            System.out.println(Colores.MENU + "7. Completar tarea" + Colores.RESET);
            System.out.println(Colores.MENU + "8. Cancelar tarea" + Colores.RESET);
            System.out.println(Colores.MENU + "9. Salir" + Colores.RESET);
            System.out.print(Colores.INGRESO + "\nIngrese una opcion: " + Colores.RESET);
            opcion = tcld.nextInt();
            tcld.nextLine();

            switch (opcion) {
                case 1:
                    crearTarea();
                    return;
                case 2:
                    modificarTarea();
                    return;
                case 3:
                    eliminacionTarea();
                    return;
                case 4:
                    asignacionTarea();
                    return;
                case 5:
                    eliminarAsignacion();
                    return;
                case 6:
                    empezarTarea();
                    return;
                case 7:
                    completarTarea();
                    return;
                case 8:
                    cancelarTarea();
                    return;
                case 9:
                    System.out.println(Colores.INFO + "SALIENDO..." + Colores.RESET);
                    System.out.println(" ");
                    return;
                default:
                    System.out.println(Colores.ERROR + "Opcion no valida" + Colores.RESET);
                    System.out.println(" ");
                    break;
            }
        }while(opcion!=9);
    }

    public void crearTarea() {
        System.out.print(Colores.INGRESO + "Ingrese el ID de la tarea: " + Colores.RESET);
        int idTarea = tcld.nextInt();
        tcld.nextLine();
        System.out.print(Colores.INGRESO + "Ingrese el titulo de la tarea: " + Colores.RESET);
        String titulo = tcld.nextLine();
        System.out.print(Colores.INGRESO + "Ingrese una descripcion de la tarea: " + Colores.RESET);
        String descripcion = tcld.nextLine();
        LocalDate fechaCreacion = LocalDate.now();
        System.out.print(Colores.INGRESO + "Ingrese el dia limite: " + Colores.RESET);
        int diaLimite = tcld.nextInt();
        System.out.print(Colores.INGRESO + "Ingrese el mes limite: " + Colores.RESET);
        int mesLimite = tcld.nextInt();
        System.out.print(Colores.INGRESO + "Ingrese el anio limite: " + Colores.RESET);
        int anioLimite = tcld.nextInt();
        tcld.nextLine();
        LocalDate fechaLimite = LocalDate.of(anioLimite, mesLimite, diaLimite);
        EstadoTarea estadoInicial = EstadoTarea.PENDIENTE;
        System.out.print(Colores.INGRESO + "Ingrese la prioridad (Baja, Media, Alta): " + Colores.RESET);
        String prioridad = tcld.nextLine().toUpperCase();
        while (!prioridad.equals("ALTA") && !prioridad.equals("MEDIA") && !prioridad.equals("BAJA")){
            System.out.println(Colores.ERROR + "ERROR! La prioridad debe ser baja, media o alta" + Colores.RESET);
            System.out.print(Colores.INGRESO + "Ingrese la prioridad (Baja, Media, Alta): " + Colores.RESET);
            prioridad = tcld.nextLine();
        }
        Prioridad prioridadEnum;
        if (prioridad.equals("ALTA")) {
            prioridadEnum = Prioridad.ALTA;
        } else if (prioridad.equals("MEDIA")) {
            prioridadEnum = Prioridad.MEDIA;
        } else {
            prioridadEnum = Prioridad.BAJA;
        }
        System.out.println(Colores.TITULO + "CATEGORIAS" + Colores.RESET);
        gestor.listarCategorias();
        Categoria categoriaSeleccionada = null;
        while (categoriaSeleccionada == null) {
            System.out.print(Colores.INGRESO + "Ingrese el ID una categoria: " + Colores.RESET);
            int idCategoria = tcld.nextInt();
            tcld.nextLine();
            Optional<Categoria> categoriaEncontrada = gestor.buscarCategoria(idCategoria);
            if (categoriaEncontrada.isPresent()) {
                categoriaSeleccionada = categoriaEncontrada.get();
            } else {
                System.out.println(Colores.ERROR + "La categoria no existe" + Colores.RESET);
                System.out.println(" ");
            }
        }

        Tarea tarea = new Tarea(idTarea, titulo, descripcion, fechaCreacion, fechaLimite, estadoInicial, prioridadEnum, categoriaSeleccionada);
        boolean creado = gestor.agregarTarea(tarea);
        if (creado) {
            System.out.println(Colores.EXITO + "Tarea creada correctamente" + Colores.RESET);
            System.out.println(" ");
        } else {
            System.out.println(Colores.ERROR + "La tarea ya existe (ID)" + Colores.RESET);
            System.out.println(" ");
        }
    }

    public void modificarTarea() {
        System.out.println(Colores.TITULO + "MODIFICACIONES" + Colores.RESET);
        System.out.println(Colores.MENU + "1. Titulo" + Colores.RESET);
        System.out.println(Colores.MENU + "2. Descripcion" + Colores.RESET);
        System.out.println(Colores.MENU + "3. Fecha Limite" + Colores.RESET);
        System.out.println(Colores.MENU + "4. Prioridad" + Colores.RESET);
        System.out.println(Colores.MENU + "5. Categoria" + Colores.RESET);
        System.out.print(Colores.INGRESO + "\nQue desea modificar?: " + Colores.RESET);
        int opModificar = tcld.nextInt();
        tcld.nextLine();
        System.out.print(Colores.INGRESO + "Ingrese el ID de la tarea a modificar: " + Colores.RESET);
        int idTarea = tcld.nextInt();
        tcld.nextLine();
        Optional<Tarea> tareaEncontrada = gestor.buscarTarea(idTarea);
        if (tareaEncontrada.isPresent()) {
            Tarea tarea = tareaEncontrada.get();
            switch (opModificar) {
                case 1:
                    System.out.print(Colores.INGRESO + "Ingrese el nuevo titulo: " + Colores.RESET);
                    String titulo = tcld.nextLine();
                    tarea.setTitulo(titulo);
                    break;
                case 2:
                    System.out.print(Colores.INGRESO + "Ingrese la nueva descripcion: " + Colores.RESET);
                    String descripcion = tcld.nextLine();
                    tarea.setDescripcion(descripcion);
                    break;
                case 3:
                    System.out.print(Colores.INGRESO + "Ingrese el nuevo dia limite: " + Colores.RESET);
                    int diaLimite = tcld.nextInt();
                    System.out.print(Colores.INGRESO + "Ingrese el nuevo mes limite: " + Colores.RESET);
                    int mesLimite = tcld.nextInt();
                    System.out.print(Colores.INGRESO + "Ingrese el nuevo anio limite: " + Colores.RESET);
                    int anioLimite = tcld.nextInt();
                    tcld.nextLine();
                    LocalDate fechaLimite = LocalDate.of(anioLimite,mesLimite, diaLimite);
                    tarea.setFechaLimite(fechaLimite);
                    break;
                case 4:
                    System.out.print(Colores.INGRESO + "Ingrese la nueva prioridad (Alta, media baja): " + Colores.RESET);
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

                    tarea.setPrioridad(prioridadEnum);
                    break;
                case 5:
                    System.out.println(Colores.TITULO + "CATEGORIAS" + Colores.RESET);
                    gestor.listarCategorias();
                    System.out.print(Colores.INGRESO + "Ingrese el ID de la categoria deseada: " + Colores.RESET);
                    int idCategoria = tcld.nextInt();
                    tcld.nextLine();
                    Optional<Categoria> categoriaEncontrada = gestor.buscarCategoria(idCategoria);
                    if (categoriaEncontrada.isPresent()) {
                        tarea.setCategoria(categoriaEncontrada.get());
                    } else {
                        System.out.println(Colores.ERROR + "No se encontro la categoria" + Colores.RESET);
                        System.out.println(" ");
                    }
                    break;
                default:
                    System.out.println(Colores.ERROR + "Opcion no valida" + Colores.RESET);
                    System.out.println(" ");
                    break;
            }
        } else {
            System.out.println(Colores.ERROR + "Tarea no encontrada" + Colores.RESET);
            System.out.println(" ");
        }
    }

    public void eliminacionTarea() {
        System.out.print(Colores.INGRESO + "Ingrese el ID de la tarea a eliminar: " + Colores.RESET);
        int idTarea = tcld.nextInt();
        tcld.nextLine();
        Optional<Tarea> tareaEncontrada = gestor.buscarTarea(idTarea);
        if (tareaEncontrada.isPresent()) {
            gestor.eliminarTarea(idTarea);
        } else {
            System.out.println(Colores.ERROR + "Tarea no encontrada" + Colores.RESET);
            System.out.println(" ");
        }
    }

    public void asignacionTarea() {
        System.out.print(Colores.INGRESO + "Ingrese el ID del usuario: " + Colores.RESET);
        int idUsuario = tcld.nextInt();
        tcld.nextLine();
        Optional<Usuario> usuarioEncontrado = gestor.buscarUsuario(idUsuario);
        if (usuarioEncontrado.isPresent()) {
            System.out.print(Colores.INGRESO + "Ingrese el ID de la tarea a asignar: " + Colores.RESET);
            int idTarea = tcld.nextInt();
            tcld.nextLine();
            Optional<Tarea> tarea = gestor.buscarTarea(idTarea);
            if (tarea.isPresent()) {
                gestor.asignarTarea(idUsuario, tarea.get());
                System.out.println(Colores.EXITO + "Tarea asignada" + Colores.RESET);
                System.out.println(" ");
            } else {
                System.out.println(Colores.ERROR + "La tarea no existe" + Colores.RESET);
                System.out.println(" ");
            }
        } else {
            System.out.println(Colores.ERROR + "El usuario no existe" + Colores.RESET);
            System.out.println(" ");
        }
    }

    public void eliminarAsignacion() {
        System.out.print(Colores.INGRESO + "Ingrese el id del usuario: " + Colores.RESET);
        int idUsuario = tcld.nextInt();
        Optional<Usuario> usuarioEncontrado = gestor.buscarUsuario(idUsuario);
        if (usuarioEncontrado.isPresent()) {
            System.out.print(Colores.INGRESO + "Ingrese el ID de la tarea a eliminar: " + Colores.RESET);
            int idTarea = tcld.nextInt();
            Optional<Tarea> tareaEncontrada = gestor.buscarTarea(idTarea);

            if (tareaEncontrada.isPresent()) {
                usuarioEncontrado.get().eliminarTarea(idTarea);
                System.out.println(Colores.EXITO + "Tarea eliminada para el usuario" + Colores.RESET);
                System.out.println(" ");
            } else {
                System.out.println(Colores.ERROR + "La tarea no existe" + Colores.RESET);
                System.out.println(" ");
            }
        } else {
            System.out.println(Colores.ERROR + "El usuario no existe" + Colores.RESET);
            System.out.println(" ");
        }
    }

    public void empezarTarea() {
        System.out.print(Colores.INGRESO + "Ingrese el ID de la tarea: " + Colores.RESET);
        int idTarea = tcld.nextInt();
        tcld.nextLine();

        Optional<Tarea> tarea = gestor.buscarTarea(idTarea);
        if (tarea.isPresent()) {
            tarea.get().empezar();
            System.out.println(Colores.EXITO + "Tarea empezada" + Colores.RESET);
            System.out.println(" ");
        } else {
            System.out.println(Colores.ERROR + "La tarea no existe" + Colores.RESET);
            System.out.println(" ");
        }
    }

    public void completarTarea() {
        System.out.print(Colores.INGRESO + "Ingrese el ID de la tarea: " + Colores.RESET);
        int idTarea = tcld.nextInt();
        tcld.nextLine();

        Optional<Tarea> tarea = gestor.buscarTarea(idTarea);
        if (tarea.isPresent()) {
            tarea.get().completar();
            System.out.println(Colores.EXITO + "Tarea completada" + Colores.RESET);
            System.out.println(" ");
        } else {
            System.out.println(Colores.ERROR + "La tarea no existe" + Colores.RESET);
            System.out.println(" ");
        }
    }

    public void cancelarTarea() {
        System.out.print(Colores.INGRESO + "Ingrese el ID de la tarea: " + Colores.RESET);
        int idTarea = tcld.nextInt();
        tcld.nextLine();

        Optional<Tarea> tarea = gestor.buscarTarea(idTarea);
        if (tarea.isPresent()) {
            tarea.get().cancelar();
            System.out.println(Colores.EXITO + "Tarea cancelada" + Colores.RESET);
            System.out.println(" ");
        } else {
            System.out.println(Colores.ERROR + "La tarea no existe" + Colores.RESET);
            System.out.println(" ");
        }
    }
}
