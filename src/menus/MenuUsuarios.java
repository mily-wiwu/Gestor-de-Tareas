package menus;

import gestor.*;
import modelo.Usuario;
import util.*;
import java.util.Scanner;

public class MenuUsuarios {
    private Scanner tcld;

    public MenuUsuarios (Scanner tcld) {
        this.tcld = tcld;
    }

    public void menuUsuarios() {
        int opcion;
        do {
            System.out.println(Colores.TITULO + "\n---MENU DE USUARIO---" + Colores.RESET);
            System.out.println(Colores.MENU + "1. Crear usuario" + Colores.RESET);
            System.out.println(Colores.MENU + "2. Salir" + Colores.RESET);

            System.out.print(Colores.INGRESO + "\nIngrese una opcion: " + Colores.RESET);
            opcion = tcld.nextInt();
            tcld.nextLine();
            switch (opcion) {
                case 1:
                    crearUsuario();
                    return;
                case 2:
                    System.out.println(Colores.INFO + "SALIENDO..." + Colores.RESET);
                    return;
                default:
                    System.out.println("Opcion no valida" + Colores.RESET);
                    break;
            }
        }while (opcion!=2);

    }

    public void crearUsuario(){
        System.out.print(Colores.INGRESO + "Ingrese el ID del usuario (RUT sin puntos ni guion): " + Colores.RESET);
        int idUsuario = tcld.nextInt();
        tcld.nextLine();
        System.out.print(Colores.INGRESO + "Ingrese el nombre del usuario: " + Colores.RESET);
        String nombreUsuario = tcld.nextLine();
        Usuario usuario = new Usuario(idUsuario, nombreUsuario);
        boolean creado = GestorTareas.getInstancia().agregarUsuario(usuario);
        if (creado) {
            System.out.println(Colores.EXITO + "Usuario creado correctamente" + Colores.RESET);
            System.out.println(" ");
        } else {
            System.out.println(Colores.ERROR + "El usuario ya existe (ID)" + Colores.RESET);
            System.out.println(" ");
        }
    }
}
