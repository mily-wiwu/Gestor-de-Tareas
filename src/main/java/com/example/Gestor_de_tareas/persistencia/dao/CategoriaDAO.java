package com.example.Gestor_de_tareas.persistencia.dao;

import com.example.Gestor_de_tareas.excepciones.DuplicadoException;
import com.example.Gestor_de_tareas.modelo.*;
import com.example.Gestor_de_tareas.persistencia.ConexionSQLite;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaDAO {

    public boolean guardar(Categoria c) {
        String sql = "INSERT INTO categoria(id, nombre, descripcion) VALUES (?, ?, ?)";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getId());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getDescripcion());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            throw new DuplicadoException("Ya existe una categoria con ID " + c.getId());
        }
    }

    public Optional<Categoria> buscarPorId(int id) {

        String sql = "SELECT * FROM categoria WHERE id = ?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Categoria c = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                );
                return Optional.of(c);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    public List<Categoria> listar() {

        List<Categoria> lista = new ArrayList<>();

        String sql = "SELECT * FROM categoria";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Categoria(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion")
                ));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM categoria WHERE id = ?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al eliminar categoria: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Categoria c) {

        String sql = """
        UPDATE categoria
        SET nombre = ?, descripcion = ?
        WHERE id=?
    """;

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getDescripcion());
            ps.setInt(3, c.getId());


            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Categoria mapear(ResultSet rs) throws Exception {
        return new Categoria(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("descripcion")
        );
    }

}