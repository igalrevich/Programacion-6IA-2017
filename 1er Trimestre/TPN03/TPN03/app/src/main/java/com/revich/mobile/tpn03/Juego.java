package com.revich.mobile.tpn03;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.Random;

public class Juego extends AppCompatActivity {
    String[] Jugadas= new String[9];
    int[] EstadosBotones= new int[9];
    Button btn01;
    Button btn02;
    Button btn03;
    Button btn04;
    Button btn05;
    Button btn06;
    Button btn07;
    Button btn08;
    Button btn09;
    int NumRandom;
    int resId;
    String btn;
    Random rand= new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        LlenarVectorJugadas();
        ObtenerReferencias();
        AsignarEstadoBotones();



    }
    private void LlenarVectorJugadas()
    {

        Jugadas[0]="1,2,4";
        Jugadas[1]="2,3,5,1";
        Jugadas[2]="3,6,2";
        Jugadas[3]="4,5,7,1";
        Jugadas[4]="5,6,8,4,2";
        Jugadas[5]="6,9,5,3";
        Jugadas[6]="7,8,4";
        Jugadas[7]="8,9,5,7";
        Jugadas[8]="9,6,8";
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
        for(int i=0;i<9;i++)
        {
            btn = "btn0" + String.valueOf(i + 1);
            CambiarColoresConNumerosAlAzar(btn);
            EstadosBotones[i]=NumRandom;
        }
            int ContSectoresVectorEstados=1;
            int Estado= EstadosBotones[0];
            ContSectoresVectorEstados++;
            while(EstadosBotones[ContSectoresVectorEstados-1]==Estado || ContSectoresVectorEstados!=10 )
            {
                ContSectoresVectorEstados++;
            }
            if(ContSectoresVectorEstados==10)
            {
                NumRandom=rand.nextInt(1-10);
                btn= "btn0"+String.valueOf(NumRandom );
              CambiarColoresConNumerosAlAzar(btn);
            }
            else
            {
                ContSectoresVectorEstados=1;
                ContSectoresVectorEstados++;
                while(EstadosBotones[ContSectoresVectorEstados-1]!=Estado || ContSectoresVectorEstados!=10 )
                {
                    ContSectoresVectorEstados++;
                }
                if(ContSectoresVectorEstados==10)
                {
                    NumRandom=rand.nextInt(1-10);
                    btn= "btn0"+String.valueOf(NumRandom );
                    CambiarColoresConNumerosAlAzar(btn);
                }
            }




    }
    private void CambiarColoresConNumerosAlAzar(String btn)
    {


        NumRandom=rand.nextInt(0-2);
        resId=getResources().getIdentifier(btn, "id", getPackageName());
        switch (resId)
        {
            case R.id.btn01:

                if(NumRandom==0)
                {
                    btn01.setBackgroundColor(Color.parseColor("#d0c8c8"));
                }
                else
                {
                    btn01.setBackgroundColor(Color.parseColor("#f51010"));

                }

                break;
            case R.id.btn02:

                if(NumRandom==0)
                {
                    btn02.setBackgroundColor(Color.parseColor("#d0c8c8"));
                }
                else
                {
                    btn02.setBackgroundColor(Color.parseColor("#f51010"));

                }
                break;
            case R.id.btn03:

                if(NumRandom==0)
                {
                    btn03.setBackgroundColor(Color.parseColor("#d0c8c8"));
                }
                else
                {
                    btn03.setBackgroundColor(Color.parseColor("#f51010"));

                }
                break;
            case R.id.btn04:

                if(NumRandom==0)
                {
                    btn04.setBackgroundColor(Color.parseColor("#d0c8c8"));
                }
                else
                {
                    btn04.setBackgroundColor(Color.parseColor("#f51010"));

                }
                break;
            case R.id.btn05:

                if(NumRandom==0)
                {
                    btn05.setBackgroundColor(Color.parseColor("#d0c8c8"));
                }
                else
                {
                    btn05.setBackgroundColor(Color.parseColor("#f51010"));

                }
                break;
            case R.id.btn06:

                if(NumRandom==0)
                {
                    btn06.setBackgroundColor(Color.parseColor("#d0c8c8"));
                }
                else
                {
                    btn06.setBackgroundColor(Color.parseColor("#f51010"));

                }
                break;
            case R.id.btn07:

                if(NumRandom==0)
                {
                    btn07.setBackgroundColor(Color.parseColor("#d0c8c8"));
                }
                else
                {
                    btn07.setBackgroundColor(Color.parseColor("#f51010"));

                }
                break;
            case R.id.btn08:

                if(NumRandom==0)
                {
                    btn08.setBackgroundColor(Color.parseColor("#d0c8c8"));
                }
                else
                {
                    btn08.setBackgroundColor(Color.parseColor("#f51010"));

                }
                break;
            case R.id.btn09:

                if(NumRandom==0)
                {
                    btn09.setBackgroundColor(Color.parseColor("#d0c8c8"));
                }
                else
                {
                    btn09.setBackgroundColor(Color.parseColor("#f51010"));

                }

                break;

        }
    }


}
