package com.example._20180252_20196044_lab10.Beans;

public class Usuario {

    private int usuarioId;
    private String firstName;
    private String lastName;
    private String email;
    private int edad;
    private String especialidad;
    private Float gasto;

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Float getGasto() {
        return gasto;
    }

    public void setGasto(Float gasto) {
        this.gasto = gasto;
    }
}
