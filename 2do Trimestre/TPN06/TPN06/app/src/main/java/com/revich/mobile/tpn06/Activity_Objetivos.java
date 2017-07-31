package com.revich.mobile.tpn06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Objetivos extends AppCompatActivity {
    Button btnComenzar,btnRanking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__objetivos);
        ObtenerReferencias();
        SetearListeners();
    }

    private void ObtenerReferencias()
    {
        btnComenzar= (Button) findViewById(R.id.btnComenzar);
        btnRanking= (Button) findViewById(R.id.btnRanking);
    }

    private void SetearListeners()
    {
        btnComenzar.setOnClickListener(btnComenzar_click);
        btnRanking.setOnClickListener(btnRanking_click);
    }

    private View.OnClickListener btnComenzar_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
         IrAActivityJuego();
        }
    };

    private View.OnClickListener btnRanking_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
         IrAActivityRanking();
        }
    };

    private void IrAActivityJuego()
    {
        Intent MiIntent= new Intent(Activity_Objetivos.this,Activity_Juego2.class);
        startActivity(MiIntent);
    }

    private void IrAActivityRanking()
    {
        //UsuariosManager MiUsuarioManager=new UsuariosManager(getApplicationContext());
        //MiUsuarioManager.EliminarBaseDeDatos();
        Intent MiIntent= new Intent(Activity_Objetivos.this,Activity_Ranking.class);
        startActivity(MiIntent);
    }
}
