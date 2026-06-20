package menus;

import gestor.GestorTareas;
import modelo.Usuario;

import java.util.Scanner;

public class MenuUsuarios {
    private GestorTareas gestor;
    private Scanner tcld;

    public MenuUsuarios (GestorTareas gestor, Scanner tcld) {
        this.gestor = gestor;
        this.tcld = tcld;
    }

    public void menuUsuarios() {
        int opcion;
        do {
            System.out.println("---MENU DE USUARIO---");
            System.out.println("1. Crear usuario");
            System.out.println("2. Salir");

            System.out.print("\nIngrese una opcion: ");
            opcion = tcld.nextInt();
            tcld.nextLine();
            switch (opcion) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    System.out.println("SALIENDO...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while (opcion!=2);

    }

    public void crearUsuario(){
        System.out.print("Ingrese el ID del usuario (RUT sin puntos ni guion): ");
        int idUsuario = tcld.nextInt();
        tcld.nextLine();
        System.out.print("Ingrese el nombre del usuario: ");
        String nombreUsuario = tcld.nextLine();
        Usuario usuario = new Usuario(idUsuario, nombreUsuario);
        gestor.agregarUsuario(usuario);
        System.out.println("modelo.Usuario creado correctamente");
    }
}
