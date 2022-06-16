package com.example.adopciondeanimales.Models;

public class Animal {
    private int tokenAnimal;
    private String nombre;
    private String tipo;
    private String raza;
    private String sexo;
    private String descripcion;

    public Animal(){}

    public Animal(int tokenAnimal, String nombre, String tipo, String raza, String sexo, String descripcion) {
        this.tokenAnimal = tokenAnimal;
        this.nombre = nombre;
        this.tipo = tipo;
        this.raza = raza;
        this.sexo = sexo;
        this.descripcion = descripcion;
    }

    public int getTokenAnimal() {
        return tokenAnimal;
    }

    public void setTokenAnimal(int tokenAnimal) {
        this.tokenAnimal = tokenAnimal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
