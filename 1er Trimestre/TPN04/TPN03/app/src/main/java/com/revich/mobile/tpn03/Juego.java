package com.revich.mobile.tpn03;

import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Juego extends AppCompatActivity {
    String[] Jugadas= new String[9];
    int[] EstadosBotones= new int[9];
    String Jugadores="";
    Button btn01;
    Button btn02;
    Button btn03;
    Button btn04;
    Button btn05;
    Button btn06;
    Button btn07;
    Button btn08;
    Button btn09;
    Button btnResultados,btnAutomatico,btnJugadores,btnMezclarTablero;
    Button [] VecBotones= new Button[9];
    int NumRandom,IndiceVec, NumJugadas=0;
    boolean Gano,ApretoMezclarTablero;
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
        btnAutomatico.setEnabled(true);
        Intent ElIntentQueVino= getIntent();
        Bundle ElBundle= ElIntentQueVino.getExtras();
        String Jugador= ElBundle.getString("Nombre");
        AdministracionDeUsuarios.ObtenerListaJugadas().clear();
        AdministracionDeUsuarios.AgregarJugadorALista(Jugador,AdministracionDeUsuarios.ObtenerLista());
        AdministracionDeUsuarios.NuevoJuego(AdministracionDeUsuarios.ObtenerCantidadVecesJugadoJuego());
        if(AdministracionDeUsuarios.ObtenerLista().size()==1 && AdministracionDeUsuarios.ObtenerCantidadVecesJugadoJuego()==1)
        {
          AdministracionDeUsuarios.ObtenerListaNumJuagdas().clear();
        }
        MezclarTablero();
        SetearListeners();



    }

    private void MezclarTablero()
    {
        btnAutomatico.setEnabled(true);
        NumJugadas=0;
        JugadasHechas="";
        setTitle("TPN03");
        LLenarVectorBotones();
        LlenarEstados();
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
        btnJugadores= (Button) findViewById(R.id.btnJugadores);
        btnMezclarTablero= (Button) findViewById(R.id.btnMezclarTablero);

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
        btnJugadores.setOnClickListener(btnJugadores_click);
        btnMezclarTablero.setOnClickListener(btnMezclarTablero_click);


    }

    private View.OnClickListener btn_click= new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            IndiceVec=EncontrarIndiceAModificar(view);
            ModificarBotones(IndiceVec);
            JugadasHechas=String.valueOf(IndiceVec+1)+":"+Jugadas[IndiceVec]+"\n";
            AdministracionDeUsuarios.AgregarJugadaALista(JugadasHechas,AdministracionDeUsuarios.ObtenerListaJugadas());
            NumJugadas=NumJugadas+1;
            setTitle(String.valueOf(NumJugadas));
            boolean Gano= CheckearSiGano();
            if(Gano)
            {

                AdministracionDeUsuarios.AgregarNumJugadasALista(NumJugadas,AdministracionDeUsuarios.ObtenerListaNumJuagdas());
                FijarseSiGanoConMinimoJugadas();
            }

        }

    };
    private void FijarseSiGanoConMinimoJugadas()
    {   int IndiceLista=0;
        while(NumJugadas>Integer.parseInt(AdministracionDeUsuarios.ObtenerListaNumJuagdas().get(IndiceLista)) && IndiceLista<AdministracionDeUsuarios.ObtenerListaNumJuagdas().size()-1)
        {
            IndiceLista++;
        }
        if(IndiceLista==AdministracionDeUsuarios.ObtenerListaNumJuagdas().size()-1)
        {

            if(AdministracionDeUsuarios.ObtenerListaNumJuagdas().size()==1)
            {
                Toast msg= Toast.makeText(getApplicationContext(),"GANASTE, CON EL MINIMO DE JUGADAS",Toast.LENGTH_SHORT);
                msg.show();
            }
            else
            {
                Toast msg= Toast.makeText(getApplicationContext(),"Ganaste, pero no con el minimo de jugadas",Toast.LENGTH_SHORT);
                msg.show();
            }

        }
        else
        {
            Toast msg= Toast.makeText(getApplicationContext(),"GANASTE, CON EL MINIMO DE JUGADAS",Toast.LENGTH_SHORT);
            msg.show();
        }
    }
    private View.OnClickListener btnMezclarTablero_click= new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {
            MezclarTablero();

        }
    };
    private View.OnClickListener btnResultados_click= new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            IniciarTerceraActivityJugadasHechas();

        }

    };
    private View.OnClickListener btnJugadores_click= new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            IniciarTerceraActivityJugadores();

        }

    };
    private View.OnClickListener btnAutomatico_click= new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            btnMezclarTablero.setEnabled(false);
            Gano=false;
            final Timer timer= new Timer();
            TimerTask timerTask= new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            NumRandom=rand.nextInt(9);
                            ModificarBotones(NumRandom);
                            JugadasHechas=String.valueOf(IndiceVec+1)+":"+Jugadas[IndiceVec]+"\n";
                            AdministracionDeUsuarios.AgregarJugadaALista(JugadasHechas,AdministracionDeUsuarios.ObtenerListaJugadas());
                            NumJugadas=NumJugadas+1;
                            setTitle(String.valueOf(NumJugadas));
                            Gano=CheckearSiGano();
                            if(Gano )
                            {
                                AdministracionDeUsuarios.AgregarNumJugadasALista(NumJugadas,AdministracionDeUsuarios.ObtenerListaNumJuagdas());
                                FijarseSiGanoConMinimoJugadas();
                                timer.cancel();
                                btnMezclarTablero.setEnabled(true);
                                btnAutomatico.setEnabled(false);
                            }
                        }
                    });
                }
            };
            timer.schedule(timerTask,0,100);



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
    {

        int color;
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
    private void IniciarTerceraActivityJugadasHechas()
    {
        Intent MiIntent= new Intent(this,Resultados.class);
        Bundle ElBundle= new Bundle();
        ElBundle.putString("JugadasHechas",JugadasHechas);
        ElBundle.putBoolean("Jugadas",true);
        MiIntent.putExtras(ElBundle);
        startActivity(MiIntent);
    }
    private void IniciarTerceraActivityJugadores()
    {
        Intent MiIntent= new Intent(this,Resultados.class);
        Bundle ElBundle= new Bundle();
        //ElBundle.putStringArrayList("Jugadores",MiAdminUsuarios.ListaJugadores);
        ElBundle.putBoolean("Jugadas",false);
        MiIntent.putExtras(ElBundle);
        startActivity(MiIntent);
    }
    private boolean CheckearSiGano()
    {
        int ContCasillerosVectorEstados=0;
        boolean EstadosDistintos=false;
        boolean Gano=false;
        while (EstadosDistintos==false && ContCasillerosVectorEstados<8)
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
