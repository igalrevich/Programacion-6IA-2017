package com.revich.mobile.tpn03;

import java.util.ArrayList;

/**
 * Created by 42426410 on 9/5/2017.
 */
public class AdministracionDeUsuarios {
    private static ArrayList<String> ListaJugadores = new ArrayList<String>();
    private static  int CantVecesJugadoJuego=0;
    private static ArrayList<String> ListaJugadas=new ArrayList<String>();
    public static ArrayList<String> ObtenerListaJugadas(){
        return ListaJugadas;
    }
    public static void AgregarJugadaALista(String Jugada, ArrayList<String> ListaJugadas)
    {
        ListaJugadas.add(Jugada);
    }
    public static void AgregarJugadorALista(String Jugador, ArrayList<String> ListaJugadores)
    {
      ListaJugadores.add(Jugador);
    }
    public static int ObtenerCantidadVecesJugadoJuego()
    {
        return CantVecesJugadoJuego;
    }
    public static void NuevoJuego(int CantVecesJuego)
    {
        CantVecesJuego++;
    }

    public static ArrayList<String> ObtenerLista(){
        return ListaJugadores;
    }

        private static ArrayList<String> ListaNumJugadas = new ArrayList<String>();

    public static ArrayList<String> ObtenerListaNumJuagdas(){
        return ListaNumJugadas;
    }

    public static void AgregarNumJugadasALista(int Jugadas, ArrayList<String> ListaNumJugadas)
    {
        ListaNumJugadas.add(String.valueOf(Jugadas));
    }
}
