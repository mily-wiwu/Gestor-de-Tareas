package com.example.Gestor_de_tareas.modelo;
    public class Usuario {
        private int id;
        private String nombre;

        public Usuario(int id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public int getId() {
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
            return Integer.hashCode(id);
        }
    }
