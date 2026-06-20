package main;
import menus.*;
import gestor.*;
import util.*;
import java.util.Scanner;

//El programa asume la fecha actual

public class Main {
    public static void main (String[] args) {
        Scanner tcld = new Scanner(System.in);
        GestorTareas gestor = new GestorTareas();
        MenuUsuarios menuUsuarios = new MenuUsuarios(gestor,tcld);
        MenuCategoria menuCategoria = new MenuCategoria(gestor, tcld);
        MenuTarea menuTarea = new MenuTarea(gestor, tcld);
        MenuConsultas menuConsultas = new MenuConsultas(gestor, tcld);
        int opcion;

        do {
            System.out.println(Colores.TITULO + "---MENU PRINCIPAL---" + Colores.RESET);
            System.out.println(Colores.MENU + "1. Gestion de usuarios" + Colores.RESET);
            System.out.println(Colores.MENU + "2. Gestion de categoria" + Colores.RESET);
            System.out.println(Colores.MENU + "3. Gestion de tareas" + Colores.RESET);
            System.out.println(Colores.MENU + "4. Consultas" + Colores.RESET);
            System.out.println(Colores.MENU + "5. Salir" + Colores.RESET);

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
                    System.out.println(Colores.INFO + "SALIENDO..." + Colores.RESET);
                    break;
                default:
                    System.out.println(Colores.ERROR + "Opcion no valida" + Colores.RESET);
            }
        } while(opcion!=5);
        tcld.close();
    }
}