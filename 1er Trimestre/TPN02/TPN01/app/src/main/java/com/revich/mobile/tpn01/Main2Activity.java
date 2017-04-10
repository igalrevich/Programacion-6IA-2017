package com.revich.mobile.tpn01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class Main2Activity extends AppCompatActivity {
    TextView tvNombre;
    TextView tvEmail;
    TextView tvGenero;
    TextView tvSatisfaccion;
    TextView tvPracticaDeportes;
    Button btnExtras;

    String Nombre;
    int CantidadLetrasNombre;
    String Primeras3LetrasNombre;
    String Ultimas3LetrasNombre;
    String Primeras3LetrasDespuesDeLaTerceraNombre;
    String NombreEnMinuscula;
    String NombreEnMayuscula;
    String Operacion3PrimerasLetras= "3PrimerasLetras";
    String OperacionUltimas3Letras="3UltimasLetras";
    String Operacion3PrimerasLetrasDespuesDeLaTercera= "3PrimerasLetrasDespuesDeLaTercera";

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
        Nombre=tvNombre.toString();
        btnExtras.setOnClickListener(btnExtras_Click_);
    }

    private View.OnClickListener btnExtras_Click_ = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            CantidadLetrasNombre=DevolverCantidadLetrasTieneNombre(Nombre);
            Primeras3LetrasNombre=Devolver3LetrasNombre(Nombre,Operacion3PrimerasLetras);
            Ultimas3LetrasNombre= Devolver3LetrasNombre(Nombre,OperacionUltimas3Letras);
            Primeras3LetrasDespuesDeLaTerceraNombre=Devolver3LetrasNombre(Nombre,Operacion3PrimerasLetrasDespuesDeLaTercera);
            NombreEnMinuscula=Nombre.toLowerCase();
            NombreEnMayuscula=Nombre.toUpperCase();
            Toast msg= Toast.makeText(getApplicationContext(),"Se puso algo en el EditTextNombre"+"\n"+"Cantidad de letras de su nombre: "+CantidadLetrasNombre+"\n"+ "Primeras 3 letras de su nombre: " + Primeras3LetrasNombre+ "\n" + "Ultimas 3 letras de su nombre: "+ Ultimas3LetrasNombre+ "\n"+ "Primeras 3 letras de su nombre despues de la tercera: "+ Primeras3LetrasDespuesDeLaTerceraNombre+ "\n"+ "Su nombre en mayuscula: "+NombreEnMayuscula+"\n"+ "Su nombre en minuscula: "+NombreEnMinuscula,Toast.LENGTH_SHORT);
            msg.show();
        }
    };




    private void ObtenerReferencias()
    {

        tvNombre  = (TextView)findViewById(R.id.tvNombre);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvGenero  = (TextView)findViewById(R.id.tvGenero);
        tvSatisfaccion  = (TextView)findViewById(R.id.tvSatisfaccion);
        tvPracticaDeportes  = (TextView)findViewById(R.id.tvPracticaDeportes);
        btnExtras = (Button) findViewById(R.id.btnExtras);

    }
    private View.OnClickListener btnExtras_Click = new View.OnClickListener()
    {
        @Override
        public void onClick(View view) {

        }
    };
    private int DevolverCantidadLetrasTieneNombre(String Nombre)
    {   int CantLetrasNombre=0;
        if(Nombre.contains(" "))
        {
            String[] NombreSeparado = Nombre.split(" ");
            for(int i=0 ; i<NombreSeparado.length;i++)
            {
                CantLetrasNombre=CantLetrasNombre+NombreSeparado[i].length();
            }
        }
        else
        {
            CantLetrasNombre=Nombre.length();
        }
        return CantLetrasNombre;
    }

    private String Devolver3LetrasNombre(String Nombre, String Operacion)
    {   String TresLetrasNombre="";

        if(Operacion=="3PrimerasLetras")
        {
            TresLetrasNombre="";
            try
            {
                TresLetrasNombre=Nombre.substring(0,2);
            }
            catch(Exception MiExcepcion)
            {
                Toast msg= Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                msg.show();
            }
        }
        if(Operacion=="3UltimasLetras")
        {
            TresLetrasNombre="";
            try
            {
              TresLetrasNombre=Nombre.substring(Nombre.length()-1,Nombre.length()-3);
            }
            catch(Exception MiExcepcion)
            {
                Toast msg= Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                msg.show();
            }

        }
        if(Operacion=="3PrimerasLetrasDespuesDeLaTercera")
        {
            TresLetrasNombre="";
            try
            {
                TresLetrasNombre=Nombre.substring(3,5);
            }
            catch(Exception MiExcepcion)
            {
                Toast msg= Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT);
                msg.show();
            }
        }
        return TresLetrasNombre;
    }

}
