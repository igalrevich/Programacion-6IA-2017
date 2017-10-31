package com.revich.mobile.tpfinal;

/**
 * Created by 42426410 on 30/10/2017.
 */
public class Janij {

    public int Id;
    public String Nombre;
    public String Apellido;
    public int DNI;

    public int getDNI() {
        return DNI;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getId() {
        return Id;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return this.Id + ". " + this.Nombre + " " + this.Apellido + " " + this.DNI;
    }

    public Janij(String Nombre, String Apellido, int dni) {
        this(0, Nombre, Apellido, dni);
    }

    // Constructor
    public Janij(int id, String Nombre, String Apellido, int dni) {
        this.Id 	= id;
        this.Nombre 	= Nombre;
        this.Apellido 	= Apellido;
        this.DNI= dni;
    }

    // Constructor
    public Janij() {
        this("", "", 0);
    }
}
