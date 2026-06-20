package menus;

import gestor.*;
import modelo.*;

import java.util.Scanner;

public class MenuCategoria {
    private GestorTareas gestor;
    private Scanner tcld;

    public MenuCategoria(GestorTareas gestor, Scanner tcld){
        this.gestor = gestor;
        this.tcld = tcld;
    }

    public void menuCategoria() {
        int opcion;
        do {
            System.out.println("---MENU DE CATEGORIA---");
            System.out.println("1. Crear categoria");
            System.out.println("2. Salir");

            System.out.print("\nIngrese una opcion: ");
            opcion = tcld.nextInt();
            tcld.nextLine();

            switch (opcion) {
                case 1:
                    crearCategoria();
                    break;

                case 2:
                    System.out.println("SALIENDO...");
                    break;

                default:
                    System.out.println("Opcion no valida");
            }
        }while (opcion!=2);
    }

    public void crearCategoria(){
        System.out.print("Ingrese el ID de la categoria: ");
        int idCategoria = tcld.nextInt();
        tcld.nextLine();
        System.out.print("Ingrese el nombre de la categoria: ");
        String nombre = tcld.nextLine();
        System.out.print("Ingrese una descripcion breve de la categoria: ");
        String descripcion = tcld.nextLine();

        Categoria categoria = new Categoria(idCategoria, nombre, descripcion);
        gestor.agregarCategoria(categoria);
        System.out.println("modelo.Categoria creada correctamente");
    }
}
