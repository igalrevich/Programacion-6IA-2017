package com.revich.mobile.tpfinal;

/**
 * Created by 42426410 on 14/11/2017.
 */
public class Ambito {


    public int Id;
    public String Nombre;

    public String getNombre() {
        return Nombre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return this.Nombre;
    }

    public Ambito(String Nombre) {
        this(0,Nombre);
    }

    // Constructor
    public Ambito(int id, String Nombre) {
        this.Id 	= id;
        this.Nombre 	= Nombre;
    }

    // Constructor
    public Ambito() {
        this(0,"");
    }
}
