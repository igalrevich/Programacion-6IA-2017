package com.revich.mobile.tpn03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Resultados extends AppCompatActivity {
    TextView tvJugadas;
    String Jugadores="";
    Spinner cmbJugadores;
    ListView lstJugadas;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        Intent ElIntentQueVino=getIntent();
        Bundle ElBundle= ElIntentQueVino.getExtras();
        boolean JugadasHechas= ElBundle.getBoolean("Jugadas");
        tvJugadas= (TextView) findViewById(R.id.tvJugadas);
        GenerarAdapterSpinner(JugadasHechas);
        if(JugadasHechas)
        {
            lstJugadas=(ListView) findViewById(R.id.lstJugadas);
            lstJugadas.setAdapter(adapter);
            //tvJugadas.setText(ElBundle.getString("JugadasHechas"));
        }
        else
        {
            cmbJugadores=(Spinner)findViewById(R.id.cmbJugadores);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cmbJugadores.setAdapter(adapter);

        }
    }
    private void GenerarAdapterSpinner(boolean JugadasHechas)
    {
        ArrayList<String> ListaJugadores=AdministracionDeUsuarios.ObtenerLista();
        ArrayList<String> ListaNumJugadas=AdministracionDeUsuarios.ObtenerListaNumJuagdas();
        if(JugadasHechas)
        {
            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ListaNumJugadas);
        }
        else
        {
            adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ListaJugadores);
        }
    }


   }
