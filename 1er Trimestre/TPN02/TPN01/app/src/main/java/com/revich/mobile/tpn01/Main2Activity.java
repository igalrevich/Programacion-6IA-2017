package com.revich.mobile.tpn01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {
    TextView tvNombre;
    TextView tvEmail;
    TextView tvGenero;
    TextView tvSatisfaccion;
    TextView tvPracticaDeportes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("TPN02");
        Intent ElIntentQueVino= getIntent();
        Bundle ElBundle= ElIntentQueVino.getExtras();
        ObtenerReferencias();
        tvNombre.setText(ElBundle.getString("Nombre"));
        tvEmail.setText(ElBundle.getString("Email"));
        tvGenero.setText(ElBundle.getString("Genero"));
        tvSatisfaccion.setText(ElBundle.getString("PorcentajeSatisfaccion"));
        tvPracticaDeportes.setText(ElBundle.getString("PracticaDeportes"));


    }
    private void ObtenerReferencias()
    {

        tvNombre  = (TextView)findViewById(R.id.tvNombre);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvGenero  = (TextView)findViewById(R.id.tvGenero);
        tvSatisfaccion  = (TextView)findViewById(R.id.tvSatisfaccion);
        tvPracticaDeportes  = (TextView)findViewById(R.id.tvPracticaDeportes);

    }

}
