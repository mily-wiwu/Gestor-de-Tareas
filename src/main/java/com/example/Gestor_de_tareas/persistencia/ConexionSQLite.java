package com.example.Gestor_de_tareas.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionSQLite {
    private static final String URL = "jdbc:sqlite:gestor.db";
    public static Connection conectar() throws SQLException {
        Connection conn = DriverManager.getConnection(URL);

        try (Statement st = conn.createStatement()) {
            st.execute("PRAGMA foreign_keys = ON;");
        }
        return conn;
    }
}