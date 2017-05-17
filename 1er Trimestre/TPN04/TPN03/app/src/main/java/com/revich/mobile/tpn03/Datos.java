package com.revich.mobile.tpn03;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Datos extends AppCompatActivity {
    Button btnIngresar;
    EditText edtNombre, edtCaptcha;
    String Nombre, TextoCaptcha;
    MathCaptcha mathCaptcha;
    ImageView ivCaptcha;
    int ResultadoCaptcha,TextoResultadoCaptcha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        ObtenerReferencias();
        mathCaptcha = new MathCaptcha(600, 150, MathCaptcha.MathOptions.PLUS_MINUS);
        ivCaptcha.setImageBitmap(mathCaptcha.getImage());
        btnIngresar.setOnClickListener(btnIngresar_click);
    }

    private void ObtenerReferencias()
    {
        btnIngresar= (Button) findViewById(R.id.btnIngresar);
        edtNombre=(EditText) findViewById(R.id.edtNombre);
        ivCaptcha=(ImageView) findViewById(R.id.ivCaptcha);
        edtCaptcha= (EditText) findViewById(R.id.edtCaptcha);

    }
    private void IniciarSegundaActivity()
    {
        Intent intent= new Intent(this,Juego.class);
        Bundle elBundle= new Bundle();
        elBundle.putString("Nombre",Nombre);
        intent.putExtras(elBundle);
        startActivity(intent);
    }
    private View.OnClickListener btnIngresar_click= new View.OnClickListener(){
        @Override
        public void onClick(View view) {
             Nombre= edtNombre.getText().toString();
             ResultadoCaptcha=mathCaptcha.ResultadoCaptha();
            Toast msg;
              if(Nombre.isEmpty())
                 {
                     try
                     {
                         msg= GenerarToastConInflador("Ingrese su nombre");
                         msg.show();
                     }
                     catch (Exception ex)
                     {
                         Log.e("LogsAndroid","ErrorToast",ex);
                     }
                 }
                 else
                 {
                     TextoCaptcha=edtCaptcha.getText().toString();
                     if(TextoCaptcha.isEmpty())
                     {
                         msg= GenerarToastConInflador("Ingrese el resultado de la suma de ambos numeros");
                         msg.show();
                     }
                     else
                     {
                         try
                         {
                             TextoResultadoCaptcha=Integer.parseInt(TextoCaptcha);
                             if(TextoResultadoCaptcha==ResultadoCaptcha)
                             {
                                 Dialog MiDialog=CrearDialogoConfirmacion();
                                 MiDialog.show();
                             }
                             else
                             {
                                 msg= GenerarToastConInflador("Resultado equivocado");
                                 msg.show();
                             }
                         }
                         catch (Exception e)
                         {
                             msg= GenerarToastConInflador("Resultado equivocado");
                             msg.show();
                         }

                     }

                 }

        }
    };

    private Toast GenerarToastConInflador(String Mensaje)
    {
        Toast msg= new Toast(getApplicationContext());
        LayoutInflater inflater= getLayoutInflater();
        View layout= inflater.inflate(R.layout.toast_layout,(ViewGroup)findViewById(R.id.lytLayout));
        TextView tvMensaje= (TextView) layout.findViewById(R.id.tvMensaje);
        tvMensaje.setText(Mensaje);
        msg.setDuration(Toast.LENGTH_SHORT);
        msg.setView(layout);
        return msg;
    }

    private Dialog CrearDialogoConfirmacion()
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Ir a Segunda actividad");
        builder.setMessage("Esta seguro de ir a la segunda actividad");
        builder.setPositiveButton("si", btnSi_click);
        builder.setNegativeButton("No",btnNo_click);
        return builder.create();
    }
    private DialogInterface.OnClickListener btnSi_click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            IniciarSegundaActivity();
            dialogInterface.cancel();
        }
    };
    private DialogInterface.OnClickListener btnNo_click = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.cancel();
        }
    };
}
