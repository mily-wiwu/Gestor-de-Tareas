
package com.example.Gestor_de_tareas.persistencia;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class InitDB {

    public static void crearTablas(Connection conn) throws Exception {

        Statement st = conn.createStatement();

        st.execute("""
            CREATE TABLE IF NOT EXISTS usuario (
                id TEXT PRIMARY KEY,
                nombre TEXT
            );
        """);

        st.execute("""
            CREATE TABLE IF NOT EXISTS categoria (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT,
                descripcion TEXT
            );
        """);

        st.execute("""
            CREATE TABLE IF NOT EXISTS tarea (
                id INTEGER PRIMARY KEY,
                titulo TEXT,
                descripcion TEXT,
                fechaCreacion TEXT,
                fechaLimite TEXT,
                tipoAsignacionFecha TEXT NOT NULL DEFAULT 'FECHA_LIMITE',
                fechaProgramada TEXT,
                estado TEXT,
                prioridad TEXT,
                categoria_id INTEGER NOT NULL,
                usuario_id INTEGER,
                FOREIGN KEY (categoria_id) REFERENCES categoria(id),
                FOREIGN KEY (usuario_id) REFERENCES usuario(id)
            );
        """);

        agregarColumnaSiNoExiste(
                conn, "tipoAsignacionFecha", "TEXT NOT NULL DEFAULT 'FECHA_LIMITE'"
        );

        agregarColumnaSiNoExiste(
                conn, "fechaProgramada", "TEXT"
        );

        st.close();
    }

    private static void agregarColumnaSiNoExiste(Connection conn, String nombreColumna, String definicion) throws Exception {
        boolean existe = false;

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("PRAGMA table_info(tarea)")) {

            while (rs.next()) {
                if (rs.getString("name").equalsIgnoreCase(nombreColumna)) {
                    existe = true;
                    break;
                }
            }
        }

        if (!existe) {
            try (Statement st = conn.createStatement()) {
                st.execute("ALTER TABLE tarea ADD COLUMN " + nombreColumna + " " + definicion);
            }
        }
    }
}
