package com.revich.mobile.tpfinal;

/**
 * Created by 42426410 on 14/11/2017.
 */
public class Fecha {
    public int Id;
    public String Fecha;
    public String Nombre;



    public String getFecha() {
        return Fecha;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getId() {
        return Id;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return this.Fecha ;
    }

    public Fecha(String fecha,String Nombre) {
        this(0, fecha, Nombre);
    }

    // Constructor
    public Fecha(int id, String fecha, String nombre) {
        this.Id 	= id;
        this.Nombre 	= nombre;
        this.Fecha= fecha;
    }

    // Constructor
    public Fecha() {
        this(0,"","");
    }
}
