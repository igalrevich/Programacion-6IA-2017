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
            for(int i=0;i<AdministracionDeUsuarios.ObtenerLista().size();i++)
            {
                Jugadores=Jugadores+AdministracionDeUsuarios.ObtenerLista().get(i)+"\n";
            }
            tvJugadas.setText(Jugadores);
        }
    }


}
