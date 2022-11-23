package com.example.rukaffe.Models;

public class Inventory {

    private int id;
    private String name;
    private String cantidad;
    private String fecha;

    public Inventory(int id, String name, String cantidad, String fecha) {
        this.id = id;
        this.name = name;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }
    public Inventory() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}