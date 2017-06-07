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
}


