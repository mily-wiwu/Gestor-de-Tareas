package menus;
import gestor.GestorTareas;
import modelo.*;
import util.*;
import java.util.*;

public class MenuExportacion {
    private Scanner tcld;

    public MenuExportacion(Scanner tcld) {
        this.tcld = tcld;
    }

    public void menuExportacion() {
        int opcion;
        do {
            System.out.println(Colores.TITULO + "\n---MENU DE EXPORTACION---" + Colores.RESET);
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
                    expTodo();
                    return;
                case 2:
                    expTareas();
                    return;
                case 3:
                    expUsuarios();
                    return;
                case  4:
                    expCategorias();
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

    public void expTodo() {
        GestorTareas.getInstancia().exportarTodo();
    }

    public void expTareas() {
        GestorTareas.getInstancia().exportarTareas();
    }

    public void expUsuarios() {
        GestorTareas.getInstancia().exportarUsuario();
    }

    public void expCategorias() {
        GestorTareas.getInstancia().exportarCategoria();
    }
}
