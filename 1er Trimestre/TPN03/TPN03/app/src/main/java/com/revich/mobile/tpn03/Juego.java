package com.revich.mobile.tpn03;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
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
    Button btnResultados,btnAutomatico;
    Button [] VecBotones= new Button[9];
    int NumRandom,IndiceVec;
    int resId;
    String btn;
    String JugadasHechas="";
    Random rand= new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        LlenarVectorJugadas();
        ObtenerReferencias();
        LLenarVectorBotones();
        LlenarEstados();
        AsignarEstadoBotones();
        SetearListeners();



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
    private  void LLenarVectorBotones()
    {
        for(int i=0;i<9;i++)
        {
            switch (i)
            {
                case 0:
                VecBotones[i]=btn01;
                break;
                case 1:
                    VecBotones[i]=btn02;
                    break;
                case 2:
                    VecBotones[i]=btn03;
                    break;
                case 3:
                    VecBotones[i]=btn04;
                    break;
                case 4:
                    VecBotones[i]=btn05;
                    break;
                case 5:
                    VecBotones[i]=btn06;
                    break;
                case 6:
                    VecBotones[i]=btn07;
                    break;
                case 7:
                    VecBotones[i]=btn08;
                    break;
                case 8:
                    VecBotones[i]=btn09;
                    break;
            }
        }
    }

    private void LlenarEstados()
    {   boolean Checkear1= true;
        boolean Checkear2= true;
        for(int i=0;i<9;i++)
        {
          NumRandom=rand.nextInt(2) ;
          EstadosBotones[i]=NumRandom;
        }
        for(int i=0;i<9;i++)
        {
            if(EstadosBotones[i]==0)
            {
              Checkear1=false;
            }
            else
            {
                Checkear2=false;
            }
        }
        if(!(Checkear1==false && Checkear2==false))
        {
            LlenarEstados();
        }

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
        btnResultados=(Button) findViewById(R.id.btnResultados);
        btnAutomatico=(Button) findViewById(R.id.btnAutomatico);
    }
    private void AsignarEstadoBotones()
    {   int color;
        for(int i=0;i<9;i++)
        {
            if(EstadosBotones[i]==0)
            {
                color= Color.parseColor("#FFD0C8C8");
                VecBotones[i].setBackgroundColor(color);
            }
            else
            {
                color= Color.parseColor("#FFF31010");
                VecBotones[i].setBackgroundColor(color);
            }
        }


    }
    private void SetearListeners()
    {
        btn01.setOnClickListener(btn_click);
        btn02.setOnClickListener(btn_click);
        btn03.setOnClickListener(btn_click);
        btn04.setOnClickListener(btn_click);
        btn05.setOnClickListener(btn_click);
        btn06.setOnClickListener(btn_click);
        btn07.setOnClickListener(btn_click);
        btn08.setOnClickListener(btn_click);
        btn09.setOnClickListener(btn_click);
        btnResultados.setOnClickListener(btnResultados_click);
        btnAutomatico.setOnClickListener(btnAutomatico_click);
    }
    private View.OnClickListener btn_click= new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            IndiceVec=EncontrarIndiceAModificar(view);
            ModificarBotones(IndiceVec);
            JugadasHechas=JugadasHechas+String.valueOf(IndiceVec+1)+":"+Jugadas[IndiceVec]+"\n";
            boolean Gano= CheckearSiGano();
            if(Gano)
            {
                JugadasHechas=JugadasHechas+"Ganaste" ;
            }

        }

    };
    private View.OnClickListener btnResultados_click= new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            IniciarTerceraActivity();

        }

    };
    private View.OnClickListener btnAutomatico_click= new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {  boolean Gano=false;
            while(Gano==false)
            {
                NumRandom=rand.nextInt(9);
                ModificarBotones(NumRandom);
                Gano=CheckearSiGano();

            }

        }

    };


    private int EncontrarIndiceAModificar(View view)
    {
        int id= view.getId();
        int IndiceVec=0;
        boolean IdVecIgualIdBoton=false;
        while(IdVecIgualIdBoton==false)
        {
            int IdVec=VecBotones[IndiceVec].getId();
            if(id==IdVec)
            {
                IdVecIgualIdBoton=true;
            }
            else
            {
             IndiceVec++;
            }
        }
        return IndiceVec;
    }
    private void ModificarBotones(int IndiceVec)
    {   int color;
        String[]BotonesAModificar=Jugadas[IndiceVec].split(",");
        for(int i=0;i<BotonesAModificar.length;i++)
        {

            int IndiceVecAModificar= Integer.parseInt(BotonesAModificar[i])-1;
            if(EstadosBotones[IndiceVecAModificar]==0)
            {
                color= Color.parseColor("#FFF31010");
                VecBotones[IndiceVecAModificar].setBackgroundColor(color);
                EstadosBotones[IndiceVecAModificar]=1;
            }
            else
            {
                color= Color.parseColor("#FFD0C8C8");
                VecBotones[IndiceVecAModificar].setBackgroundColor(color);
                EstadosBotones[IndiceVecAModificar]=0;
            }
        }
    }
    private void IniciarTerceraActivity()
    {
        Intent MiIntent= new Intent(this,Resultados.class);
        Bundle ElBundle= new Bundle();
        ElBundle.putString("JugadasHechas",JugadasHechas);
        MiIntent.putExtras(ElBundle);
        startActivity(MiIntent);
    }
    private boolean CheckearSiGano()
    {
        int ContCasillerosVectorEstados=0;
        boolean EstadosDistintos=false;
        boolean Gano=false;
        while (EstadosDistintos==false || ContCasillerosVectorEstados<8)
        {
            if(EstadosBotones[ContCasillerosVectorEstados]!=EstadosBotones[ContCasillerosVectorEstados+1])
            {
              EstadosDistintos=true;
            }
            ContCasillerosVectorEstados++;
        }
        if(EstadosDistintos==false)
        {
            Gano=true;
        }
        return Gano;
    }



}
