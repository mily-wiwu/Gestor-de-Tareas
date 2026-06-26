    package modelo;

    import java.util.*;

    public class Usuario {
        private int id;
        private String nombre;
        private Set<Tarea> tareas;

        public Usuario (int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
            this.tareas = new HashSet<>();
        }

        public int getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
        }

        public void agregarTarea(Tarea tarea){
            tareas.add(tarea);
        }
        public void eliminarTarea(int idTarea) {
            tareas.removeIf(t -> t.getId() == idTarea);
        }

        public Collection<Tarea> tareasUsuario (){
            return tareas;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Usuario)) return false;
            Usuario usuario = (Usuario) o;
            return id == usuario.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }
    }
