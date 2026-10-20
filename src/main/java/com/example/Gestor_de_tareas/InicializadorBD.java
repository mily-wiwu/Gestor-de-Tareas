package com.example.Gestor_de_tareas;

import com.example.Gestor_de_tareas.persistencia.ConexionSQLite;
import com.example.Gestor_de_tareas.persistencia.InitDB;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InicializadorBD implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        try (var conn = ConexionSQLite.conectar()) {
            InitDB.crearTablas(conn);
            System.out.println("Base de datos inicializada correctamente.");
        }
    }
}