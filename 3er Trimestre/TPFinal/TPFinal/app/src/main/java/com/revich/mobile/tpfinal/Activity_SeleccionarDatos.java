package com.revich.mobile.tpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_SeleccionarDatos extends AppCompatActivity {
    Button btnCargarDatos, btnUsoDiario , btnEstadisticas;
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
        btnEstadisticas = (Button) findViewById(R.id.btnEstadisticas);
        btnEstadisticas.setOnClickListener(btnEstadisticas_click);
    }

    private View.OnClickListener btnCargarDatos_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
         IrAActivity_CargarDatos_UsoDiario_Estadisticas(0);
        }
    };

    private View.OnClickListener btnEstadisticas_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IrAActivity_CargarDatos_UsoDiario_Estadisticas(1);
        }
    };

    private View.OnClickListener btnUsoDiario_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IrAActivity_CargarDatos_UsoDiario_Estadisticas(2);
        }
    };

    private void IrAActivity_CargarDatos_UsoDiario_Estadisticas(int IdActivity)
    {
        Intent intent;
        switch (IdActivity)
        {
            case 0:
                intent = new Intent(Activity_SeleccionarDatos.this,Activity_CargarDatos.class);
                break;
            case 1:
                intent = new Intent(Activity_SeleccionarDatos.this,Activity_Estadisticas.class);
                break;
            case 2:
                intent = new Intent(Activity_SeleccionarDatos.this,Activity_Presentismo.class);
                break;
            default:
                intent = new Intent(Activity_SeleccionarDatos.this,Activity_CargarDatos.class);
                break;
        }
        startActivity(intent);
        //finish();
    }
}
