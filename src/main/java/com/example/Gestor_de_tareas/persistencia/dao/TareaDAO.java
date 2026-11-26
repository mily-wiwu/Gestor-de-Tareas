package com.example.Gestor_de_tareas.persistencia.dao;

import com.example.Gestor_de_tareas.excepciones.TareaNoEncontradaException;
import com.example.Gestor_de_tareas.modelo.*;
import com.example.Gestor_de_tareas.persistencia.ConexionSQLite;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TareaDAO {
    public boolean guardar(Tarea t) {

        if (t == null ||
                t.getCategoria() == null ||
                t.getFechaCreacion() == null ||
                t.getTipoAsignacionFecha() == null ||
                t.getEstado() == null ||
                t.getPrioridad() == null ||
                t.getTitulo() == null ||
                t.getTitulo().isBlank()) {

            throw new IllegalArgumentException("Tarea inválida");
        }

        if (t.getTipoAsignacionFecha() == TipoAsignacionFecha.FECHA_LIMITE) {

            if (t.getFechaLimite() == null || t.getFechaProgramada() != null) {
                throw new IllegalArgumentException("Fecha límite inválida");
            }

        } else if (t.getTipoAsignacionFecha() == TipoAsignacionFecha.DIA_ESPECIFICO) {

            if (t.getFechaProgramada() == null || t.getFechaLimite() != null) {
                throw new IllegalArgumentException("Fecha programada inválida");
            }
        }

        String sql = """
        INSERT INTO tarea(
            titulo, descripcion, fechaCreacion, fechaLimite,
            tipoAsignacionFecha, fechaProgramada,
            estado, prioridad, categoria_id, usuario_id
        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getTitulo());
            ps.setString(2, t.getDescripcion());
            ps.setString(3, t.getFechaCreacion().toString());

            if (t.getFechaLimite() == null) {
                ps.setNull(4, Types.VARCHAR);
            } else {
                ps.setString(4, t.getFechaLimite().toString());
            }

            ps.setString(5, t.getTipoAsignacionFecha().name());

            if (t.getFechaProgramada() == null) {
                ps.setNull(6, Types.VARCHAR);
            } else {
                ps.setString(6, t.getFechaProgramada().toString());
            }

            ps.setString(7, t.getEstado().name());
            ps.setString(8, t.getPrioridad().name());
            ps.setInt(9, t.getCategoria().getId());

            if (t.getUsuario() == null) {
                ps.setNull(10, Types.VARCHAR);
            } else {
                ps.setString(10, t.getUsuario().getId());
            }

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new RuntimeException("No se pudo guardar la tarea", e);
        }
    }

    public Optional<Tarea> buscarPorId(int id) {
        String sql = consultaConJoin() + " WHERE t.id = ?";

        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(mapear(rs));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tarea> listar() {
        List<Tarea> lista = new ArrayList<>();
        String sql = consultaConJoin();

        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void eliminar(int id) {
        String sql = "DELETE FROM tarea WHERE id = ?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            if (ps.executeUpdate() == 0) {
                throw new TareaNoEncontradaException("No existe una tarea con ID " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la tarea.", e);
        }
    }

    private Tarea mapear(ResultSet rs) throws SQLException {
        Categoria categoria = null;
        Usuario usuario = null;

        if (rs.getObject("categoria_id") != null) {
            categoria = new Categoria(rs.getInt("categoria_id"), rs.getString("categoria_nombre"), rs.getString("categoria_descripcion"));
        }

        if (rs.getObject("usuario_id") != null) {
            usuario = new Usuario(rs.getString("usuario_id"), rs.getString("usuario_nombre"));
        }

        String fechaLimite = rs.getString("fechaLimite");
        String fechaProgramada = rs.getString("fechaProgramada");

        return new Tarea(rs.getInt("id"), rs.getString("titulo"), rs.getString("descripcion"), LocalDate.parse(rs.getString("fechaCreacion")), fechaLimite != null ? LocalDate.parse(fechaLimite) : null, TipoAsignacionFecha.valueOf(rs.getString("tipoAsignacionFecha")), fechaProgramada != null ? LocalDate.parse(fechaProgramada) : null, EstadoTarea.valueOf(rs.getString("estado")), Prioridad.valueOf(rs.getString("prioridad")), categoria, usuario);
    }

    public void actualizar(Tarea t) {

        if (t.getTipoAsignacionFecha() == TipoAsignacionFecha.FECHA_LIMITE) {

            if (t.getFechaLimite() == null || t.getFechaProgramada() != null) {
                throw new IllegalArgumentException("Fecha límite inválida");
            }

        } else if (t.getTipoAsignacionFecha() == TipoAsignacionFecha.DIA_ESPECIFICO) {

            if (t.getFechaProgramada() == null || t.getFechaLimite() != null) {
                throw new IllegalArgumentException("Fecha programada inválida");
            }
        }

        String sql = """
        UPDATE tarea
        SET titulo = ?,
            descripcion = ?,
            fechaLimite = ?,
            tipoAsignacionFecha = ?,
            fechaProgramada = ?,
            prioridad = ?,
            categoria_id = ?,
            usuario_id = ?
        WHERE id = ?
        """;

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, t.getTitulo());
            ps.setString(2, t.getDescripcion());

            if (t.getFechaLimite() == null) {
                ps.setNull(3, Types.VARCHAR);
            } else {
                ps.setString(3, t.getFechaLimite().toString());
            }

            ps.setString(4, t.getTipoAsignacionFecha().name());

            if (t.getFechaProgramada() == null) {
                ps.setNull(5, Types.VARCHAR);
            } else {
                ps.setString(5, t.getFechaProgramada().toString());
            }

            ps.setString(6, t.getPrioridad().name());
            ps.setInt(7, t.getCategoria().getId());

            if (t.getUsuario() == null) {
                ps.setNull(8, Types.VARCHAR);
            } else {
                ps.setString(8, t.getUsuario().getId());
            }

            ps.setInt(9, t.getId());

            if (ps.executeUpdate() == 0) {
                throw new TareaNoEncontradaException("No existe una tarea con ID " + t.getId());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la tarea.", e);
        }
    }
    public List<Tarea> buscarPorUsuario(int idUsuario) {
        List<Tarea> tareas = new ArrayList<>();

        String sql = consultaConJoin() + " WHERE t.usuario_id = ?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                tareas.add(mapear(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return tareas;
    }

    private String consultaConJoin() {
        return """
        SELECT 
            t.id,
            t.titulo,
            t.descripcion,
            t.fechaCreacion,
            t.fechaLimite,
            t.tipoAsignacionFecha,
            t.fechaProgramada,
            t.estado,
            t.prioridad,
    
            c.id AS categoria_id,
            c.nombre AS categoria_nombre,
            c.descripcion AS categoria_descripcion,
    
            u.id AS usuario_id,
            u.nombre AS usuario_nombre
    
        FROM tarea t
        LEFT JOIN categoria c ON t.categoria_id = c.id
        LEFT JOIN usuario u ON t.usuario_id = u.id
        
        """;
    }

    public void actualizarEstado(int id, EstadoTarea estado) {
        String sql = """
        UPDATE tarea
        SET estado = ?
        WHERE id = ?
        """;

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, estado.name());
                ps.setInt(2, id);

                if (ps.executeUpdate() == 0) {
                    throw new TareaNoEncontradaException("No existe una tarea con ID " + id);
                }

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el estado.", e);
        }
    }

    public List<Tarea> buscarConFiltros(String titulo, String criterioFiltro, String valorFiltro, String criterioOrden, String direccionOrden) {        StringBuilder sql = new StringBuilder(consultaConJoin());
        sql = new StringBuilder(consultaConJoin());
        sql.append(" WHERE 1=1 ");

        List<Object> parametros = new ArrayList<>();

        if (titulo != null && !titulo.isBlank()) {
            sql.append(" AND t.titulo LIKE ? ");
            parametros.add("%" + titulo.trim() + "%");
        }

        if (criterioFiltro != null && valorFiltro != null && !valorFiltro.isBlank()) {
            switch (criterioFiltro) {
                case "categoria" -> {
                    sql.append(" AND t.categoria_id = ? ");
                    parametros.add(Integer.parseInt(valorFiltro));
                }

                case "prioridad" -> {
                    sql.append(" AND t.prioridad = ? ");
                    parametros.add(valorFiltro);
                }

                case "estado" -> {
                    sql.append(" AND t.estado = ? ");
                    parametros.add(valorFiltro);
                }
            }
        }

        String columnaOrden = switch (criterioOrden) {
            case "id" -> "t.id";
            case "titulo" -> "t.titulo COLLATE NOCASE";
            case "fecha" -> "t.fechaLimite";
            case "prioridad" -> """
                CASE t.prioridad
                    WHEN 'ALTA' THEN 1
                    WHEN 'MEDIA' THEN 2
                    WHEN 'BAJA' THEN 3
                END
                """;
            case "categoria" -> "c.nombre COLLATE NOCASE";
            case "estado" -> """
                CASE t.estado
                    WHEN 'PENDIENTE' THEN 1
                    WHEN 'EN_PROGRESO' THEN 2
                    WHEN 'COMPLETADA' THEN 3
                    WHEN 'CANCELADA' THEN 4
                END
                """;
            default -> "t.id";
        };

        String direccion = "desc".equalsIgnoreCase(direccionOrden)
                ? "DESC" : "ASC";

        sql.append(" ORDER BY ")
                .append(columnaOrden)
                .append(" ")
                .append(direccion)
                .append(", t.id ASC ");

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                ps.setObject(i + 1, parametros.get(i));
            }

            List<Tarea> tareas = new ArrayList<>();

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tareas.add(mapear(rs));
                }
            }

            return tareas;

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar tareas.", e);
        }
    }

    public List<Tarea> listarActivas() {
        List<Tarea> lista = new ArrayList<>();

        String sql = consultaConJoin() + " WHERE t.estado IN ('PENDIENTE', 'EN_PROGRESO')";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return lista;
    }
}