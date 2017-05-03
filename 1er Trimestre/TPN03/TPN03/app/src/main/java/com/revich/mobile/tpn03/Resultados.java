package com.revich.mobile.tpn03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Resultados extends AppCompatActivity {
    TextView tvJugadas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        Intent ElIntentQueVino=getIntent();
        Bundle ElBundle= ElIntentQueVino.getExtras();
        tvJugadas= (TextView) findViewById(R.id.tvJugadas);
        tvJugadas.setText("");
        tvJugadas.setText(ElBundle.getString("JugadasHechas"));
    }
}
