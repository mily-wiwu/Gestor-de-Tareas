package menus;

import gestor.*;
import modelo.*;
import util.*;
import java.util.*;

public class MenuCategoria {
    private Scanner tcld;

    public MenuCategoria(Scanner tcld){
        this.tcld = tcld;
    }

    public void menuCategoria() {
        int opcion;
        do {
            System.out.println(Colores.TITULO + "\n---MENU DE CATEGORIA---" + Colores.RESET);
            System.out.println(Colores.MENU + "1. Crear categoria" + Colores.RESET);
            System.out.println(Colores.MENU + "2. Salir" + Colores.RESET);

            System.out.print(Colores.INGRESO + "\nIngrese una opcion: " + Colores.RESET);
            opcion = tcld.nextInt();
            tcld.nextLine();

            switch (opcion) {
                case 1:
                    crearCategoria();
                    return;

                case 2:
                    System.out.println(Colores.INFO + "SALIENDO..." + Colores.RESET);
                    System.out.println(" ");
                    return;

                default:
                    System.out.println(Colores.ERROR + "Opcion no valida" + Colores.RESET);
                    System.out.println(" ");
                    break;
            }
        }while (opcion!=2);
    }

    public void crearCategoria(){
        System.out.print(Colores.INGRESO + "Ingrese el ID de la categoria: " + Colores.RESET);
        int idCategoria = tcld.nextInt();
        tcld.nextLine();
        System.out.print(Colores.INGRESO + "Ingrese el nombre de la categoria: " + Colores.RESET);
        String nombre = tcld.nextLine();
        System.out.print(Colores.INGRESO + "Ingrese una descripcion breve de la categoria: " + Colores.RESET);
        String descripcion = tcld.nextLine();

        Categoria categoria = new Categoria(idCategoria, nombre, descripcion);
        boolean creado = GestorTareas.getInstancia().agregarCategoria(categoria);
        if (creado) {
            System.out.println(Colores.EXITO + "Categoria creada correctamente" + Colores.RESET);
            System.out.println(" ");
        } else {
            System.out.println(Colores.ERROR + "La categoria ya existe (ID)" + Colores.RESET);
            System.out.println(" ");
        }
    }
}
