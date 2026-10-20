package menus;
import gestor.GestorTareas;
import modelo.*;
import util.*;
import java.util.*;

public class MenuDatos {
    private Scanner tcld;

    public MenuDatos(Scanner tcld) {
        this.tcld = tcld;
    }

    public void menuDatos() {
        int opcion;
        do {
            System.out.println(Colores.TITULO + "\n---MENU DE DATOS---" + Colores.RESET);
            System.out.println(Colores.MENU + "1. Exportar todo" + Colores.RESET);
            System.out.println(Colores.MENU + "2. Exportar tareas" + Colores.RESET);
            System.out.println(Colores.MENU + "3. Exportar usuarios" + Colores.RESET);
            System.out.println(Colores.MENU + "4. Exportar categorias" + Colores.RESET);
            System.out.println(Colores.MENU + "5. Salir" + Colores.RESET);
            System.out.print(Colores.INGRESO + "\nIngrese una opcion: " + Colores.RESET);
            opcion = tcld.nextInt();
            tcld.nextLine();

            switch (opcion) {
                case 1:
                    exportarTodo();
                    return;
                case 2:
                    exportarTareas();
                    return;
                case 3:
                    exportarUsuarios();
                    return;
                case  4:
                    exportarCategorias();
                    return;
                case 5:
                    System.out.println("SALIENDO...");
                    System.out.println(" ");
                    return;
                default:
                    System.out.println(Colores.ERROR + "Opcion no valida" + Colores.RESET);
                    System.out.println(" ");
                    break;
            }
        }while(opcion!=5);
    }

    public void exportarTodo() {
        GestorTareas.getInstancia().exportarTodo();
    }

    public void exportarTareas() {
        GestorTareas.getInstancia().exportarTareas();
    }

    public void exportarUsuarios() {
        GestorTareas.getInstancia().exportarUsuario();
    }

    public void exportarCategorias() {
        GestorTareas.getInstancia().exportarCategoria();
    }
}
