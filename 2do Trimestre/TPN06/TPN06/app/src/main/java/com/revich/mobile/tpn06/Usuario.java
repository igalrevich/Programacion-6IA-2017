package com.revich.mobile.tpn06;

/**
 * Created by 42426410 on 11/7/2017.
 */
public class Usuario {

    public int Id;
    public String Nombre;
    public String CiudadesAcertadas;
    public String TiempoDeJuego;

    @Override
    public String toString() {
        return this.Id + ". " + this.Nombre + " " + this.CiudadesAcertadas + " " + this.TiempoDeJuego;
    }

    public Usuario(String Nombre, String CiudadesAcertadas, String TiempoDeJuego) {
        this(0, Nombre, CiudadesAcertadas, TiempoDeJuego);
    }

    // Constructor
    public Usuario(int id, String Nombre, String CiudadesAcertadas, String TiempoDeJuego) {
        this.Id 	= id;
        this.Nombre 	= Nombre;
        this.CiudadesAcertadas 	= CiudadesAcertadas;
        this.TiempoDeJuego = TiempoDeJuego;
    }

    // Constructor
    public Usuario() {
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

    //Propiedad CiudadesAcertadas
    public String getCiudadesAcertadas() {
        return this.CiudadesAcertadas;
    }
    public void setCiudadesAcertadas(String CiudadesAcertadas) {
        this.CiudadesAcertadas = CiudadesAcertadas;
    }

    // propiedad TiempoDeJuego
    public String getTiempoDeJuego() {
        return this.TiempoDeJuego;
    }
    public void setTiempoDeJuego(String TiempoDeJuego)
    {
        this.TiempoDeJuego = TiempoDeJuego;
    }
}
