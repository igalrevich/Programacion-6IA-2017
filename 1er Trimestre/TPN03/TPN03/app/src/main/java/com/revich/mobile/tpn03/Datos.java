package com.revich.mobile.tpn03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
              if(Nombre.isEmpty())
                 {
                     Toast msg= Toast.makeText(getApplicationContext(),"Ingrese su nombre",Toast.LENGTH_SHORT);
                     msg.show();
                 }
                 else
                 {
                     TextoCaptcha=edtCaptcha.getText().toString();
                     if(TextoCaptcha.isEmpty())
                     {
                         Toast msg= Toast.makeText(getApplicationContext(),"Ingrese el resultado de la suma de ambos numeros",Toast.LENGTH_SHORT);
                         msg.show();
                     }
                     else
                     {
                         TextoResultadoCaptcha=Integer.parseInt(TextoCaptcha);
                         if(TextoResultadoCaptcha==ResultadoCaptcha)
                         {
                             IniciarSegundaActivity();
                         }
                         else
                         {
                             Toast msg= Toast.makeText(getApplicationContext(),"Resultado equivocado",Toast.LENGTH_SHORT);
                             msg.show();
                         }
                     }

                 }

        }
    };
}
