package com.revich.mobile.tpn05;

/**
 * Created by 42426410 on 7/6/2017.
 */
public class Alumnos {
    public int Id;
    public String Nombre;
    public String Casado;
    public String FechaNacimiento;

    @Override
    public String toString() {
        return this.Id + ". " + this.Nombre + " " + this.Casado;
    }

    public Alumnos(String Nombre, String Casado, String FechaNacimiento) {
        this(0, Nombre, Casado, FechaNacimiento);
    }

    // Constructor
    public Alumnos(int id, String Nombre, String Casado, String FechaNacimiento) {
        this.Id 	= id;
        this.Nombre 	= Nombre;
        this.Casado 	= Casado;
        this.FechaNacimiento = FechaNacimiento;
    }

    // Constructor
    public Alumnos() {
        this(0, "", "", "");
    }

    //
    // Properties
    //

    // propiedad Id
    public long getId() {
        return Id;
    }
    public void setId(int id) {
        this.Id = id;
    }

    //Propiedad Nombre
    public String getNombre() {
        return this.Nombre;
    }
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    //Propiedad Casado
    public String getCasado() {
        return this.Casado;
    }
    public void setCasado(String Casado) {
        this.Casado = Casado;
    }

    // propiedad FechaNacimiento
    public String getFechaNacimiento() {
        return FechaNacimiento;
    }
    public void setFechaNacimiento(String FechaNacimiento)
    {
        this.FechaNacimiento = FechaNacimiento;
    }
}


