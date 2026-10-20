package com.example.Gestor_de_tareas.persistencia.dao;

import com.example.Gestor_de_tareas.excepciones.DuplicadoException;
import com.example.Gestor_de_tareas.modelo.*;
import com.example.Gestor_de_tareas.persistencia.ConexionSQLite;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDAO {
    public boolean guardar(Usuario u) {
        String sql = "INSERT INTO usuario(id, nombre) VALUES (?, ?)";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, u.getId());
            ps.setString(2, u.getNombre());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new DuplicadoException("Ya existe un usuario con ID " + u.getId());
        }
    }

    public Optional<Usuario> buscarPorId(int id) {

        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre")
                );
                return Optional.of(u);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Optional.empty();
    }

    public List<Usuario> listar() {

        List<Usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuario";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre")
                ));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lista;
    }

    public boolean eliminar(int id) {

        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Usuario u) {

        String sql = """
        UPDATE usuario
        SET nombre=?
        WHERE id=?
    """;

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setInt(2, u.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
