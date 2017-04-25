package com.revich.mobile.tpn03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.Random;

public class Juego extends AppCompatActivity {
    String[] Jugadas= new String[10];
    Button btn01;
    Button btn02;
    Button btn03;
    Button btn04;
    Button btn05;
    Button btn06;
    Button btn07;
    Button btn08;
    Button btn09;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        LlenarVectorJugadas();
        ObtenerReferencias();


    }
    private void LlenarVectorJugadas()
    {
        Jugadas[0]="";
        Jugadas[1]="1,2,4";
        Jugadas[2]="2,3,5,1";
        Jugadas[3]="3,6,2";
        Jugadas[4]="4,5,7,1";
        Jugadas[5]="5,6,8,4,2";
        Jugadas[6]="6,9,5,3";
        Jugadas[7]="7,8,4";
        Jugadas[8]="8,9,5,7";
        Jugadas[9]="9,6,8";
    }
    private void ObtenerReferencias()
    {
        btn01=(Button) findViewById(R.id.btn01);
        btn02=(Button) findViewById(R.id.btn02);
        btn03=(Button) findViewById(R.id.btn03);
        btn04=(Button) findViewById(R.id.btn04);
        btn05=(Button) findViewById(R.id.btn05);
        btn06=(Button) findViewById(R.id.btn06);
        btn07=(Button) findViewById(R.id.btn07);
        btn08=(Button) findViewById(R.id.btn08);
        btn09=(Button) findViewById(R.id.btn09);
    }
    private void AsignarEstadoBotones()
    {
        for(int i=1;i<=9;i++)
        {
            if(String.valueOf(i)==btn01.getText().toString())
            {
                Random rand= new Random();
                int NumeroEstado= rand.nextInt(2);
                if(NumeroEstado==0)
                {
                  btn01.setBackgroundColor();
                }
            }
        }
    }


}
