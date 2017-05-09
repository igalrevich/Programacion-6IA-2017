package com.revich.mobile.tpn03;

import java.util.ArrayList;

/**
 * Created by 42426410 on 9/5/2017.
 */
public class AdministracionDeUsuarios {
    private static ArrayList<String> ListaJugadores = new ArrayList<String>();

    public static void AgregarJugadorALista(String Jugador, ArrayList<String> ListaJugadores)
    {
      ListaJugadores.add(Jugador);
    }

    public static ArrayList<String> ObtenerLista(){
        return ListaJugadores;
    }
}
