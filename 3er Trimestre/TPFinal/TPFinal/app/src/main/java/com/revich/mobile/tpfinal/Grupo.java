package com.revich.mobile.tpfinal;

/**
 * Created by 42426410 on 30/10/2017.
 */
public class Grupo {

    public int Id;
    public String Nombre;
    public int Año;

    public int getId() {
        return Id;
    }

    public void setAño(int año) {
        Año = año;
    }

    public int getAño() {
        return Año;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return this.Id + ". " + this.Nombre + " " + this.Año;
    }

    public Grupo(String Nombre, int Año) {
        this(0, Nombre, Año);
    }

    // Constructor
    public Grupo(int id, String Nombre, int Año) {
        this.Id 	= id;
        this.Nombre 	= Nombre;
        this.Año 	= Año;
    }

    // Constructor
    public Grupo() {
        this(0, "", 0);
    }
}
