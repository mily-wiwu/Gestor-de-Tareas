import java.util.ArrayList;

public class Usuario {
    private int id;
    private ArrayList<Tarea> tareas;

    public Usuario (int id) {
        this.id = id;
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

}
