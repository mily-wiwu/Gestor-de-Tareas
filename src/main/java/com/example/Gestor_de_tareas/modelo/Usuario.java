package com.example.Gestor_de_tareas.modelo;

import java.util.Objects;

public class Usuario {
        private String id;
        private String nombre;

        public Usuario(String id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public String getId() {
            return id;
        }

        public String getNombre() {
            return nombre;
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
            return Objects.hash(id);
        }
    }
