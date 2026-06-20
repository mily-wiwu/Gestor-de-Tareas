package modelo;

import java.util.ArrayList;

public class Usuario {
    private int id;
    private String nombre;
    private ArrayList<Tarea> tareas;

    public Usuario (int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.tareas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void agregarTarea(Tarea tarea){
        tareas.add(tarea);
    }
    public void eliminarTarea(int idTarea) {
        for (int i=0; i<tareas.size();i++) {
            if (tareas.get(i).getId() == idTarea) {
                tareas.remove(i);
                break;
            }
        }
    }

    public ArrayList<Tarea> tareasUsuario (){
        return tareas;
    }
}
