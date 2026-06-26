package menus;
import java.util.*;
import gestor.*;
import modelo.*;
import util.*;

public class MenuReporte {
    private Scanner tcld;

    public MenuReporte (Scanner tcld) {
        this.tcld = tcld;
    }

    public void menuReporte() {
        int opcion;
        do {
            System.out.println(Colores.TITULO + "\n---MENU DE REPORTE---" + Colores.RESET);
            System.out.println(Colores.MENU + "1. Resumen general" + Colores.RESET);
            System.out.println(Colores.MENU + "2. Tareas por estado" + Colores.RESET);
            System.out.println(Colores.MENU + "3. Tareas por prioridad" + Colores.RESET);
            System.out.println(Colores.MENU + "4. Tareas por categoria" + Colores.RESET);
            System.out.println(Colores.MENU + "5. Tareas por usuario" + Colores.RESET);
            System.out.println(Colores.MENU + "6. Salir" + Colores.RESET);
            System.out.print(Colores.INGRESO + "\nIngrese una opción: " + Colores.RESET);
            opcion = tcld.nextInt();
            tcld.nextLine();

            switch (opcion) {
                case 1:
                    resumenGeneral();
                    return;
                case 2:
                    reporteEstados();
                    return;
                case 3:
                     reportePrioridades();
                    return;
                case 4:
                    reporteCategoria();
                    return;
                case 5:
                    reporteUsuario();
                    return;
                case 6:
                    System.out.println(Colores.INFO + "SALIENDO..." +Colores.RESET);
                    System.out.println(" ");
                    return;
                default:
                    System.out.println(Colores.ERROR + "Opcion no valida" + Colores.RESET);
                    System.out.println(" ");
                    return;
            }
        } while(opcion!=6);
    }

    public void resumenGeneral() {
        int total = GestorTareas.getInstancia().totalTareas();
        int pendientes = GestorTareas.getInstancia().totalPendientes();
        int progreso = GestorTareas.getInstancia().totalProgreso();
        int completadas = GestorTareas.getInstancia().totalCompletadas();
        int canceladas = GestorTareas.getInstancia().totalCanceladas();
        int vencidas = GestorTareas.getInstancia().totalVencidas();
        int proximas = GestorTareas.getInstancia().totalProximas();
        int hoy = GestorTareas.getInstancia().totalHoy();

        System.out.println(Colores.INFO + "Total de tareas: " + total + Colores.RESET);
        System.out.println(Colores.INFO + "\nPendientes: " + pendientes + Colores.RESET);
        System.out.println(Colores.INFO + "En progreso: " + progreso + Colores.RESET);
        System.out.println(Colores.INFO + "Completadas: " + completadas + Colores.RESET);
        System.out.println(Colores.INFO + "Canceladas: " + canceladas + Colores.RESET);
        System.out.println(Colores.INFO + "\nVencidas: " + vencidas + Colores.RESET);
        System.out.println(Colores.INFO + "Proximas a vencer: " + proximas + Colores.RESET);
        System.out.println(Colores.INFO + "Vence hoy: " + hoy + Colores.RESET);
        System.out.println(" ");
    }

    public void reporteEstados() {
        float total = GestorTareas.getInstancia().totalTareas();
        float pendientes = GestorTareas.getInstancia().totalPendientes();
        float progreso = GestorTareas.getInstancia().totalProgreso();
        float completadas = GestorTareas.getInstancia().totalCompletadas();
        float canceladas = GestorTareas.getInstancia().totalCanceladas();

        if (total > 0) {
            System.out.printf(Colores.INFO + "Pendientes: %.2f%% (%d)%s%n", (pendientes * 100 / total), (int) pendientes, Colores.RESET);
            System.out.printf(Colores.INFO + "En progreso: %.2f%% (%d)%s%n", (progreso * 100 / total), (int) progreso, Colores.RESET);
            System.out.printf(Colores.INFO + "Completadas: %.2f%% (%d)%s%n", (completadas * 100 / total), (int) completadas, Colores.RESET);
            System.out.printf(Colores.INFO + "Canceladas: %.2f%% (%d)%s%n", (canceladas * 100 / total), (int) canceladas, Colores.RESET);
            System.out.println(" ");
        } else {
            System.out.println(Colores.INFO + "Pendientes: 0% (0)" + Colores.RESET);
            System.out.println(Colores.INFO + "En progreso: 0% (0)" + Colores.RESET);
            System.out.println(Colores.INFO + "Completadas: 0% (0)" + Colores.RESET);
            System.out.println(Colores.INFO + "Canceladas: 0% (0)" + Colores.RESET);
            System.out.println(" ");
        }
    }

    public void reportePrioridades() {
        float total = GestorTareas.getInstancia().totalTareas();
        float altas = GestorTareas.getInstancia().totalAlta();
        float medias = GestorTareas.getInstancia().totalMedia();
        float bajas = GestorTareas.getInstancia().totalBaja();

        if (total > 0) {
            System.out.printf(Colores.INFO + "Alta: %.2f%% (%d)%s%n", (altas * 100/total), (int) altas, Colores.RESET);
            System.out.printf(Colores.INFO + "Media: %.2f%% (%d)%s%n", (medias * 100/total), (int) medias, Colores.RESET);
            System.out.printf(Colores.INFO + "Baja: %.2f%% (%d)%s%n", (bajas * 100/total), (int) bajas, Colores.RESET);
            System.out.println(" ");
        } else {
            System.out.println(Colores.INFO + "Alta: 0% (0)" + Colores.RESET);
            System.out.println(Colores.INFO + "Media: 0% (0)" + Colores.RESET);
            System.out.println(Colores.INFO + "Baja: 0% (0" + Colores.RESET);
            System.out.println(" ");
        }
    }

    public void reporteCategoria() {
        int total = GestorTareas.getInstancia().totalTareas();
        if (total != 0) {
            for (Categoria categoria : GestorTareas.getInstancia().getCategorias()) {
                int cantidad = GestorTareas.getInstancia().contadorCategoria(categoria);
                System.out.println(Colores.INFO + categoria.getNombre() + ": " + cantidad + Colores.RESET);
            }
            System.out.println(" ");
        } else {
            System.out.println(Colores.ERROR + "No existen datos" + Colores.RESET);
            System.out.println(" ");
        }
    }

    public void reporteUsuario(){
        int total = GestorTareas.getInstancia().totalTareas();
        if (total != 0) {
            for (Usuario usuario : GestorTareas.getInstancia().getUsuarios()) {
                int cantidad = GestorTareas.getInstancia().contadorUsuario(usuario);
                System.out.println(Colores.INFO + usuario.getNombre() + ": " + cantidad + Colores.RESET);
            }
        } else {
            System.out.println(Colores.ERROR + "No existen datos" + Colores.RESET);
            System.out.println(" ");
        }
    }
}