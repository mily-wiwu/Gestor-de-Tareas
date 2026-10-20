package menus;
import gestor.GestorTareas;
import modelo.*;
import util.*;
import java.util.*;

public class MenuExportacion {
    private Scanner tcld;
    MenuDatos menuDatos;

    public MenuExportacion(Scanner tcld) {
        this.tcld = tcld;
        this.menuDatos = new MenuDatos(tcld);
    }

    public void menuExportacion() {
        int opcion;
        do {
            System.out.println(Colores.TITULO + "\n---MENU DE EXPORTACION---" + Colores.RESET);
            System.out.println(Colores.MENU + "1. Exportar datos CSV" + Colores.RESET);
            System.out.println(Colores.MENU + "2. Crear copia de seguridad de la base de datos" + Colores.RESET);
            System.out.println(Colores.MENU + "3. Salir" + Colores.RESET);
            System.out.print(Colores.INGRESO + "\nIngrese una opcion: " + Colores.RESET);
            opcion = tcld.nextInt();
            tcld.nextLine();

            switch (opcion) {
                case 1:
                    menuDatos.menuDatos();
                    return;
                case 2:
                    copiaDB();
                    return;
                case 3:
                    System.out.println("SALIENDO...");
                    System.out.println(" ");
                    return;
                default:
                    System.out.println(Colores.ERROR + "Opcion no valida" + Colores.RESET);
                    System.out.println(" ");
                    break;
            }
        }while(opcion!=3);
    }

    public void copiaDB() {
        boolean creada = GestorTareas.getInstancia().crearCopiaBD();

        if(creada){
            System.out.println(Colores.EXITO + "Copia creada correctamente" + Colores.RESET);
        }
    }
}
