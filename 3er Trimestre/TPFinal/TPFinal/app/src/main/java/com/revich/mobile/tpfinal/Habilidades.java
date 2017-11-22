package com.revich.mobile.tpfinal;

/**
 * Created by Igal on 11/22/2017.
 */

public class Habilidades {
    public int Id;
    public String Nombre;
    public boolean esPositiva;

    public int getId() {
        return Id;
    }

    public void setEsPositiva(boolean EsPositiva) {
        esPositiva = EsPositiva;
    }

    public boolean getEsPositiva() {
        return esPositiva;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    @Override
    public String toString() {
        return this.Id + ". " + this.Nombre ;
    }

    public Habilidades(String Nombre, boolean EsPositiva) {
        this(0, Nombre, EsPositiva);
    }

    // Constructor
    public Habilidades(int id, String Nombre, boolean EsPositiva) {
        this.Id 	= id;
        this.Nombre 	= Nombre;
        this.esPositiva 	= EsPositiva;
    }

    // Constructor
    public Habilidades() {
        this(0, "", false);
    }
}
