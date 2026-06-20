package main;
import menus.*;
import gestor.*;
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
            System.out.println("---MENU PRINCIPAL---");
            System.out.println("1. Gestion de usuarios");
            System.out.println("2. Gestion de categoria");
            System.out.println("3. Gestion de tareas");
            System.out.println("4. Consultas");
            System.out.println("5. Salir");

            System.out.print("\nIngrese una opcion: ");
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
                    System.out.println("SALIENDO...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while(opcion!=5);
        tcld.close();
    }
}