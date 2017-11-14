package com.revich.mobile.tpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_SeleccionarDatos extends AppCompatActivity {
    Button btnCargarDatos, btnUsoDiario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__seleccionar_datos);
        ObtenerReferenciasYSetearListeners();
        TPFinalSQLiteHelper tpFinalSQLiteHelper= new TPFinalSQLiteHelper(this,"DBTPFinal",null,4);
    }

    private void ObtenerReferenciasYSetearListeners()
    {
       btnCargarDatos = (Button) findViewById(R.id.btnCargarDatos);
       btnUsoDiario= (Button) findViewById(R.id.btnUsoDiario);
       btnCargarDatos.setOnClickListener(btnCargarDatos_click);
       btnUsoDiario.setOnClickListener(btnUsoDiario_click);
    }

    private View.OnClickListener btnCargarDatos_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
         IrAActivity_CargarDatosOUsoDiario(true);
        }
    };

    private View.OnClickListener btnUsoDiario_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IrAActivity_CargarDatosOUsoDiario(false);
        }
    };

    private void IrAActivity_CargarDatosOUsoDiario(boolean CargarDatos)
    {
        Intent intent;
        if(CargarDatos)
        {
         intent = new Intent(Activity_SeleccionarDatos.this,Activity_CargarDatos.class);
        }
        else
        {
            intent = new Intent(Activity_SeleccionarDatos.this,Activity_Presentismo.class);
        }
        startActivity(intent);
        //finish();
    }
}
