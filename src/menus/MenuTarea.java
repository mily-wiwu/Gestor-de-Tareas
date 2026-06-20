package menus;

import gestor.GestorTareas;
import modelo.*;

import java.time.LocalDate;
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
            System.out.println("---MENU DE TAREA---");
            System.out.println("1. Crear tarea");
            System.out.println("2. Modificar tarea");
            System.out.println("3. Eliminar tarea");
            System.out.println("4. Asignar tarea");
            System.out.println("5. Eliminar asignacion");
            System.out.println("6. Empezar tarea");
            System.out.println("7. Completar tarea");
            System.out.println("8. Cancelar tarea");
            System.out.println("9. Salir");
            System.out.print("\nIngrese una opcion: ");
            opcion = tcld.nextInt();
            tcld.nextLine();

            switch (opcion) {
                case 1:
                    crearTarea();
                    break;
                case 2:
                    modificarTarea();
                    break;
                case 3:
                    eliminacionTarea();
                    break;
                case 4:
                    asignacionTarea();
                    break;
                case 5:
                    eliminarAsignacion();
                    break;
                case 6:
                    empezarTarea();
                    break;
                case 7:
                    completarTarea();
                    break;
                case 8:
                    cancelarTarea();
                    break;
                case 9:
                    System.out.println("SALIENDO...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while(opcion!=9);
    }

    public void crearTarea() {
        System.out.print("Ingrese el ID de la tarea: ");
        int idTarea = tcld.nextInt();
        tcld.nextLine();
        System.out.print("Ingrese el titulo de la tarea: ");
        String titulo = tcld.nextLine();
        System.out.print("Ingrese una descripcion de la tarea: ");
        String descripcion = tcld.nextLine();
        LocalDate fechaCreacion = LocalDate.now();
        System.out.print("Ingrese el dia limite: ");
        int diaLimite = tcld.nextInt();
        System.out.print("Ingrese el mes limite: ");
        int mesLimite = tcld.nextInt();
        System.out.print("Ingrese el anio limite: ");
        int anioLimite = tcld.nextInt();
        tcld.nextLine();
        LocalDate fechaLimite = LocalDate.of(anioLimite, mesLimite, diaLimite);
        EstadoTarea estadoInicial = EstadoTarea.PENDIENTE;
        System.out.print("Ingrese la prioridad (Baja, Media, Alta): ");
        String prioridad = tcld.nextLine().toUpperCase();
        while (!prioridad.equals("ALTA") && !prioridad.equals("MEDIA") && !prioridad.equals("BAJA")){
            System.out.println("ERROR! La prioridad debe ser baja, media o alta");
            System.out.print("Ingrese la prioridad (Baja, Media, Alta): ");
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
        System.out.println("CATEGORIAS");
        gestor.listarCategorias();
        Categoria categoriaSeleccionada = null;
        while (categoriaSeleccionada==null) {
            System.out.print("Ingrese el ID una categoria: ");
            int idCategoria = tcld.nextInt();
            tcld.nextLine();
            Categoria categoriaEncontrada = gestor.buscarCategoria(idCategoria);
            if (categoriaEncontrada!= null) {
                categoriaSeleccionada = categoriaEncontrada;
            } else {
                System.out.println("La categoria no existe");
            }
        }

        Tarea tarea = new Tarea(idTarea, titulo, descripcion, fechaCreacion, fechaLimite, estadoInicial, prioridadEnum, categoriaSeleccionada);
        gestor.agregarTarea(tarea);
        System.out.println("modelo.Tarea creada exitosamente");
    }

    public void modificarTarea() {
        System.out.println("MODIFICACIONES");
        System.out.println("1. Titulo");
        System.out.println("2. Descripcion");
        System.out.println("3. Fecha Limite");
        System.out.println("4. modelo.Prioridad");
        System.out.println("5. modelo.Categoria");
        System.out.print("Que desea modificar?: ");
        int opModificar = tcld.nextInt();
        tcld.nextLine();
        System.out.print("Ingrese el ID de la tarea a modificar: ");
        int idTarea = tcld.nextInt();
        tcld.nextLine();
        Tarea tareaEncontrada = gestor.buscarTarea(idTarea);
        if (tareaEncontrada!=null) {
            switch (opModificar) {
                case 1:
                    System.out.print("Ingrese el nuevo titulo: ");
                    String titulo = tcld.nextLine();
                    tareaEncontrada.setTitulo(titulo);
                    break;
                case 2:
                    System.out.print("Ingrese la nueva descripcion: ");
                    String descripcion = tcld.nextLine();
                    tareaEncontrada.setDescripcion(descripcion);
                    break;
                case 3:
                    System.out.print("Ingrese el nuevo dia limite: ");
                    int diaLimite = tcld.nextInt();
                    System.out.print("Ingrese el nuevo mes limite: ");
                    int mesLimite = tcld.nextInt();
                    System.out.print("Ingrese el nuevo anio limite: ");
                    int anioLimite = tcld.nextInt();
                    tcld.nextLine();
                    LocalDate fechaLimite = LocalDate.of(anioLimite,mesLimite, diaLimite);
                    tareaEncontrada.setFechaLimite(fechaLimite);
                    break;
                case 4:
                    System.out.print("Ingrese la nueva prioridad (Alta, media baja): ");
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

                    tareaEncontrada.setPrioridad(prioridadEnum);
                    break;
                case 5:
                    System.out.println("CATEGORIAS");
                    gestor.listarCategorias();
                    System.out.print("Ingrese el ID de la categoria deseada: ");
                    int idCategoria = tcld.nextInt();
                    tcld.nextLine();
                    Categoria categoriaSeleccionada = gestor.buscarCategoria(idCategoria);
                    if (categoriaSeleccionada!=null) {
                        tareaEncontrada.setCategoria(categoriaSeleccionada);
                    } else {
                        System.out.println("No se encontro la categoria");
                    }
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }

    public void eliminacionTarea() {
        System.out.print("Ingrese el ID de la tarea a eliminar: ");
        int idTarea = tcld.nextInt();
        tcld.nextLine();
        Tarea tareaEncontrada = gestor.buscarTarea(idTarea);
        if (tareaEncontrada!=null) {
            gestor.eliminarTarea(idTarea);
        } else {
            System.out.println("modelo.Tarea no encontrada");
        }
    }

    public void asignacionTarea() {
        System.out.print("Ingrese el ID del usuario: ");
        int idUsuario = tcld.nextInt();
        tcld.nextLine();
        Usuario usuarioEncontrado = gestor.buscarUsuario(idUsuario);
        if (usuarioEncontrado!=null) {
            System.out.print("Ingrese el ID de la tarea a asignar: ");
            int idTarea = tcld.nextInt();
            tcld.nextLine();
            Tarea tarea = gestor.buscarTarea(idTarea);
            if (tarea!=null) {
                gestor.asignarTarea(idUsuario, tarea);
                System.out.println("modelo.Tarea asignada");
            } else {
                System.out.println("La tarea no existe");
            }
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void eliminarAsignacion() {
        System.out.print("Ingrese el id del usuario: ");
        int idUsuario = tcld.nextInt();
        Usuario usuarioEncontrado = gestor.buscarUsuario(idUsuario);
        if (usuarioEncontrado!=null) {
            System.out.print("Ingrese el ID de la tarea a eliminar: ");
            int idTarea = tcld.nextInt();
            Tarea tareaEncontrada = gestor.buscarTarea(idTarea);

            if (tareaEncontrada!=null) {
                usuarioEncontrado.eliminarTarea(idTarea);
                System.out.println("modelo.Tarea eliminada para el usuario");
            } else {
                System.out.println("La tarea no existe");
            }
        } else {
            System.out.println("El usuario no existe");
        }
    }

    public void empezarTarea() {
        System.out.print("Ingrese el ID de la tarea: ");
        int idTarea = tcld.nextInt();
        tcld.nextLine();

        Tarea tarea = gestor.buscarTarea(idTarea);
        if (tarea!=null) {
            tarea.empezar();
            System.out.println("modelo.Tarea empezada");
        } else {
            System.out.println("La tarea no existe");
        }
    }

    public void completarTarea() {
        System.out.print("Ingrese el ID de la tarea: ");
        int idTarea = tcld.nextInt();
        tcld.nextLine();

        Tarea tarea = gestor.buscarTarea(idTarea);
        if (tarea!=null) {
            tarea.completar();
            System.out.println("modelo.Tarea completada");
        } else {
            System.out.println("La tarea no existe");
        }
    }

    public void cancelarTarea() {
        System.out.print("Ingrese el ID de la tarea: ");
        int idTarea = tcld.nextInt();
        tcld.nextLine();

        Tarea tarea = gestor.buscarTarea(idTarea);
        if (tarea!=null) {
            tarea.cancelar();
            System.out.println("modelo.Tarea cancelada");
        } else {
            System.out.println("La tarea no existe");
        }
    }
}
