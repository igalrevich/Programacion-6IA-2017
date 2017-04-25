package com.revich.mobile.tpn03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Datos extends AppCompatActivity {
    Button btnIngresar;
    EditText edtNombre;
    String Nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        ObtenerReferencias();
        btnIngresar.setOnClickListener(btnIngresar_click);
    }

    private void ObtenerReferencias()
    {
        btnIngresar= (Button) findViewById(R.id.btnIngresar);
        edtNombre=(EditText) findViewById(R.id.edtNombre);
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
             if(Nombre!="")
             {
                 IniciarSegundaActivity();
             }
            else
             {
                 Toast msg= Toast.makeText(getApplicationContext(),"Ingrese su nombre",Toast.LENGTH_SHORT);
                 msg.show();
             }
        }
    };
}
