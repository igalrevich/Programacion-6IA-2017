package com.revich.mobile.tpn06;

import java.util.ArrayList;

/**
 * Created by 42426410 on 26/6/2017.
 */
public class

DatosJuego {

    public static String NombreUsuario;

    public static ArrayList<Pais> Paises;

    public static ArrayList<Geonames> Geonames;

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
