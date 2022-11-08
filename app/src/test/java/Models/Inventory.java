package Models;

import java.util.Date;

public class Inventory {

    private int id;
    private String name;
    private final int cantidad;
    private Date fecha;

    public Inventory(int id, String name, int cantidad, String fecha) {
        this.id = id;
        this.name = name;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

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

    public int getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}