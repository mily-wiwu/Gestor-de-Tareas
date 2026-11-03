package com.example.Gestor_de_tareas.servicio;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class RespaldoService {
    public boolean crearCopiaBD() {
        Path origen = Paths.get("gestor.db");
        Path destino = Paths.get("gestor_backup.db");

        try {
            Files.copy(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            return true;

        } catch (IOException e) {
            System.out.println("Error al crear copia de seguridad: " + e.getMessage());
            return false;
        }
    }
}
