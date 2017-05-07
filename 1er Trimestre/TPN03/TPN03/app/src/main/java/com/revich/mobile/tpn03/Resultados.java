package com.revich.mobile.tpn03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Resultados extends AppCompatActivity {
    TextView tvJugadas;
    String Jugadores="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        Intent ElIntentQueVino=getIntent();
        Bundle ElBundle= ElIntentQueVino.getExtras();
        boolean JugadasHechas= ElBundle.getBoolean("Jugadas");
        tvJugadas= (TextView) findViewById(R.id.tvJugadas);
        if(JugadasHechas)
        {
            tvJugadas.setText(ElBundle.getString("JugadasHechas"));
        }
        else
        {
            Jugadores=ElBundle.getString("Jugadores");
            tvJugadas.setText(Jugadores);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("Jugadores",Jugadores);

    }
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Jugadores=savedInstanceState.getString("Jugadores");
        tvJugadas.setText(Jugadores);
    }
}
