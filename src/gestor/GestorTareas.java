package gestor;
import modelo.*;
import util.*;
import excepciones.*;
import persistencia.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.List;

public class GestorTareas {
    private static GestorTareas instancia;
    private CategoriaDAO categoriaDAO;
    private TareaDAO tareaDAO;
    private UsuarioDAO usuarioDAO;

    private GestorTareas() {
        this.categoriaDAO = new CategoriaDAO();
        this.usuarioDAO = new UsuarioDAO();
        this.tareaDAO = new TareaDAO();
    }

    public static GestorTareas getInstancia(){
        if (instancia==null) {
            instancia = new GestorTareas();
        }
        return instancia;
    }

    public boolean agregarTarea(Tarea tarea) {
        return tareaDAO .guardar(tarea);
    }

    public boolean agregarUsuario(Usuario usuario) {
        return usuarioDAO.guardar(usuario);
    }

    public Usuario buscarUsuario(int id) {
        return usuarioDAO.buscarPorId(id).orElseThrow(() ->
                new UsuarioNoEncontradoException(Colores.ERROR + "No existe el usuario con ID " + id + Colores.RESET));
    }

    public boolean agregarCategoria(Categoria categoria) {
        return categoriaDAO.guardar(categoria);
    }

    public Tarea buscarTarea(int id){
        return tareaDAO.buscarPorId(id).orElseThrow(() ->
                new TareaNoEncontradaException(Colores.ERROR + "No existe la tarea con ID " + id + Colores.RESET));
    }
     
    public Categoria buscarCategoria(int id){
        return categoriaDAO.buscarPorId(id).orElseThrow(() ->
                new TareaNoEncontradaException(Colores.ERROR + "No existe la categoria con ID " + id + Colores.RESET));

    }

    public void asignarTarea(int idUsuario, int idTarea) {
        Usuario usuario = buscarUsuario(idUsuario);
        Tarea tarea = buscarTarea(idTarea);

        tarea.setUsuario(usuario);
        modificarTarea(tarea);
    }

    public List<Tarea> buscarPorPrioridad(Prioridad prioridad) {
        return tareaDAO.buscarPorPrioridad(prioridad);
    }

    public List<Tarea> buscarPorEstado(EstadoTarea estado){
        return tareaDAO.buscarPorEstado(estado);
    }

    public List<Categoria> listarCategorias() {
        return categoriaDAO.listar();
    }

    public List<Tarea> listarTareas() {
        return tareaDAO.listar();
    }

    public List<Tarea> obtenerTareasPendientes(){
        return tareaDAO.buscarPorEstado(EstadoTarea.PENDIENTE);
    }

    public List<Tarea> obtenerTareasCompletadas(){
        return tareaDAO.buscarPorEstado(EstadoTarea.COMPLETADA);
    }

    public void eliminarTarea(int idTarea) {
        tareaDAO.eliminar(idTarea);
    }

    public List<Tarea> mostrarCategoria(int idCategoria) {
        return tareaDAO.buscarPorCategoria(idCategoria);
    }

    public List<Tarea> obtenerTareasVencidas() {
        List<Tarea> vencidas = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.VENCIDA) {
                vencidas.add(tarea);
            }
        }
        return vencidas;
    }

    public List<Tarea> obtenerTareasProximas() {
        List<Tarea> proximas = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.PROXIMA_A_VENCER) {
                proximas.add(tarea);
            }
        }
        return proximas;
    }

    public List<Tarea> obtenerVenceHoy() {
        List<Tarea> venceHoy = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.VENCE_HOY) {
                venceHoy.add(tarea);
            }
        }

        return venceHoy;
    }

    public List<Tarea> obtenerEnPlazo() {
        List<Tarea> enPlazo = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.EN_PLAZO){
                enPlazo.add(tarea);
            }
        }
        return enPlazo;
    }

    public List<Tarea> obtenerTareasFuturas() {
        List<Tarea> tareaFutura = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.esFutura()) {
                tareaFutura.add(tarea);
            }
        }

        tareaFutura.sort(Comparator.comparing(Tarea::getFechaLimite));
        return tareaFutura;
    }

    public int totalTareas() {
        return tareaDAO.contadorTareas();
    }

    public int totalPendientes() {
        return tareaDAO.contadorEstado(EstadoTarea.PENDIENTE);
    }

    public int totalProgreso() {
        return tareaDAO.contadorEstado(EstadoTarea.EN_PROGRESO);
    }

    public int totalCompletadas() {
        return tareaDAO.contadorEstado(EstadoTarea.COMPLETADA);
    }

    public int totalCanceladas() {
        return tareaDAO.contadorEstado(EstadoTarea.CANCELADA);
    }

    public int totalVencidas() {
        List<Tarea> vencidas = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.VENCIDA) {
                vencidas.add(tarea);
            }
        }
        return vencidas.size();
    }

    public int totalProximas() {
        List<Tarea> proximas = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.PROXIMA_A_VENCER) {
                proximas.add(tarea);
            }
        }
        return proximas.size();
    }

    public int totalHoy() {
        List<Tarea> hoy = new ArrayList<>();
        for (Tarea tarea : tareaDAO.listar()) {
            if (tarea.getSituacionTemporal()==SituacionTemporal.VENCE_HOY){
                hoy.add(tarea);
            }
        }
        return hoy.size();
    }

    public int totalAlta() {
       return tareaDAO.contadorPrioridad(Prioridad.ALTA);
    }

    public int totalMedia() {
        return tareaDAO.contadorPrioridad(Prioridad.MEDIA);
    }

    public int totalBaja() {
        return tareaDAO.contadorPrioridad(Prioridad.BAJA);
    }

    public int contadorCategoria(Categoria categoria) {
        return tareaDAO.contadorCategoria(categoria.getId());
    }

    public ArrayList<Categoria> getCategorias() {
        return new ArrayList<>(categoriaDAO.listar());
    }

    public int contadorUsuario(Usuario usuario) {
        return tareaDAO.contadorUsuario(usuario.getId());
    }

    public ArrayList<Usuario> getUsuarios() {
        return new ArrayList<>(usuarioDAO.listar()  );
    }

    public void exportarUsuario() {
        try(FileWriter writer = new FileWriter("usuarios.csv")) {
            writer.write("id,nombre\n");

            for(Usuario usuario : usuarioDAO.listar()) {
                writer.write(usuario.getId() + "," + usuario.getNombre() + "\n");
            }
            System.out.println(Colores.EXITO + "Usuarios exportados" + Colores.RESET);

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void exportarCategoria() {
        try(FileWriter writer = new FileWriter("categorias.csv")) {
            writer.write("id,nombre,descripcion\n");

            for(Categoria categoria : categoriaDAO.listar()) {
                writer.write(categoria.getId() + "," + categoria.getNombre() + "," + categoria.getDescripcion() + "\n");
            }

            System.out.println(Colores.EXITO + "Categorias exportadas exitosamente" + Colores.RESET);

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void exportarTareas() {
        try (FileWriter writer = new FileWriter("tareas.csv")) {
            writer.write("id,titulo,descripcion,fechaCreacion,fechaLimite,estado,prioridad,categoria,usuario\n");

            for (Tarea tarea : tareaDAO.listar()) {
                writer.write(tarea.getId() + "," + tarea.getTitulo() + "," + tarea.getDescripcion() + "," + tarea.getFechaCreacion() + "," + tarea.getFechaLimite() + "," + tarea.getEstado() + "," + tarea.getPrioridad() + "," + tarea.getCategoria().getNombre() + "," + (tarea.getUsuario() != null ? tarea.getUsuario().getNombre() : "Sin asignar") + "\n");
            }

            System.out.println(Colores.EXITO + "Tareas exportadas correctamente" + Colores.RESET);

        } catch (IOException e) {
            System.out.println(Colores.ERROR + "Error exportando tareas: " + e.getMessage() + Colores.RESET
            );
        }
    }

    public void exportarTodo(){
        exportarTareas();
        exportarUsuario();
        exportarCategoria();
    }

    public void modificarTarea(Tarea tarea) {
        tareaDAO.actualizar(tarea);
    }

    public boolean crearCopiaBD() {
        Path origen = Paths.get("gestor.db");
        Path destino = Paths.get("gestor_backup.db");

        try {
            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            return true;

        } catch (IOException e) {
            System.out.println(Colores.ERROR + "Error al crear copia de seguridad: " + e.getMessage() + Colores.RESET);
            return false;
        }
    }

    public List<Tarea> buscarTareasPorUsuario(int idUsuario) {
        return tareaDAO.buscarPorUsuario(idUsuario);
    }
}