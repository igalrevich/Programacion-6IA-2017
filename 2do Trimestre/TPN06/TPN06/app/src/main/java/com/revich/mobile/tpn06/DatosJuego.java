package com.revich.mobile.tpn06;

import java.util.ArrayList;

/**
 * Created by 42426410 on 26/6/2017.
 */
public class

DatosJuego {

    public static String NombreUsuario;

    public static ArrayList<Pais> Paises= new ArrayList<>();

    public static ArrayList<Geonames> Geonames = new ArrayList<>();

    public static String NombreCiudadCorrecta;

    public static String GetNombreCiudadCorrecta()
    {
      return  NombreCiudadCorrecta;
    }

    public static void SetNombreCiudadCorrecta(String CiudadCorrecta)
    {
        NombreCiudadCorrecta=CiudadCorrecta;
    }

    public static int SegundosJuego;

    public static int GetSegundosJuego()
    {
        return  SegundosJuego;
    }

    public static void SetSegundosJuego(int SegundosJuegoActual)
    {
        SegundosJuego=SegundosJuegoActual+1;
    }

    public static boolean Perdio;

    public static boolean GetPerdio()
    {
        return  Perdio;
    }

    public static void SetPerdio(boolean PerdioToF)
    {
        Perdio=PerdioToF;
    }

    public  DatosJuego()
    {
        //Paises= new ArrayList<>();
        //Gnonames= new ArrayList<>();
    }

    public static void SetPaises(Pais MiPais)
    {
        Paises.add(MiPais);
    }

    public static ArrayList<Pais> GetPaises()
    {
        return  Paises;
    }

    public static void SetGeonames(Geonames MiGeonames)
    {
        Geonames.add(MiGeonames);
    }

    public static ArrayList<Geonames> GetGeonames()
    {
        return  Geonames;
    }

    public static void SetNombre(String Nombre)
    {
        NombreUsuario=Nombre;
    }

    public static String GetNombre()
    {
        return  NombreUsuario;
    }
}
