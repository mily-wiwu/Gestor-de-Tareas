package com.example.Gestor_de_tareas.persistencia;

import java.sql.Connection;
import java.sql.Statement;

public class InitDB {
    public static void crearTablas(Connection conn) throws Exception {
        Statement st = conn.createStatement();

        st.execute("""
            CREATE TABLE IF NOT EXISTS usuario (
                id INTEGER PRIMARY KEY,
                nombre TEXT
            );
        """);


        st.execute("""
            CREATE TABLE IF NOT EXISTS categoria (
                id INTEGER PRIMARY KEY,
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
                estado TEXT,
                prioridad TEXT,
                categoria_id INTEGER NOT NULL,
                usuario_id INTEGER,
                FOREIGN KEY (categoria_id) REFERENCES categoria(id),
                FOREIGN KEY (usuario_id) REFERENCES usuario(id)
            );
        """);

        st.close();
    }
}
