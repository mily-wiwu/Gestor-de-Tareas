package main;
import menus.*;
import gestor.*;
import util.*;
import java.util.Scanner;

//El programa asume la fecha actual

public class Main {
    public static void main (String[] args) {
        Scanner tcld = new Scanner(System.in);
        MenuUsuarios menuUsuarios = new MenuUsuarios(tcld);
        MenuCategoria menuCategoria = new MenuCategoria(tcld);
        MenuTarea menuTarea = new MenuTarea(tcld);
        MenuConsultas menuConsultas = new MenuConsultas(tcld);
        MenuReporte menuReporte = new MenuReporte(tcld);
        MenuExportacion menuExportacion = new MenuExportacion(tcld);

        int opcion;
        do {
            System.out.println(Colores.TITULO + "---MENU PRINCIPAL---" + Colores.RESET);
            System.out.println(Colores.MENU + "1. Gestion de usuarios" + Colores.RESET);
            System.out.println(Colores.MENU + "2. Gestion de categoria" + Colores.RESET);
            System.out.println(Colores.MENU + "3. Gestion de tareas" + Colores.RESET);
            System.out.println(Colores.MENU + "4. Consultas" + Colores.RESET);
            System.out.println(Colores.MENU + "5. Reportes" + Colores.RESET);
            System.out.println(Colores.MENU + "6. Exportacion" + Colores.RESET);
            System.out.println(Colores.MENU + "7. Salir" + Colores.RESET);

            System.out.print(Colores.INGRESO + "\nIngrese una opcion: " + Colores.RESET);
            opcion = tcld.nextInt();
            tcld.nextLine();

            switch (opcion) {
                case 1:
                    menuUsuarios.menuUsuarios();
                    break;
                case 2:
                    menuCategoria.menuCategoria();
                    break;
                case 3:
                    menuTarea.menuTarea();
                    break;
                case 4:
                    menuConsultas.menuConsultas();
                    break;
                case 5:
                    menuReporte.menuReporte();
                    break;
                case 6:
                    menuExportacion.menuExportacion();
                    break;
                case 7:
                    System.out.println(Colores.INFO + "SALIENDO..." + Colores.RESET);
                    break;
                default:
                    System.out.println(Colores.ERROR + "Opcion no valida" + Colores.RESET);
            }
        } while(opcion!=6);
        tcld.close();
    }
}