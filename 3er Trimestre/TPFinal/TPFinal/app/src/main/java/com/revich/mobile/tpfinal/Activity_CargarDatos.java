package com.revich.mobile.tpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_CargarDatos extends AppCompatActivity {
    Button btnJanijim, btnGrupos , btnAsignarJanijimAGrupos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__cargar_datos);
        ObtenerReferenciasYSetearListeners();
    }

    private void ObtenerReferenciasYSetearListeners()
    {
        btnJanijim = (Button) findViewById(R.id.btnJanijim);
        btnGrupos= (Button) findViewById(R.id.btnGrupos);
        btnAsignarJanijimAGrupos = (Button) findViewById(R.id.btnAsignarJaniimAGrupos);
        btnJanijim.setOnClickListener(btnJanijim_click);
        btnGrupos.setOnClickListener(btnGrupos_click);
        btnAsignarJanijimAGrupos.setOnClickListener(btnAsignarJanijimAGrupos_click);
    }

    private View.OnClickListener btnJanijim_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IrAListadoJanijimOGrupos(true);
        }
    };

    private View.OnClickListener btnGrupos_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IrAListadoJanijimOGrupos(false);
        }
    };

    private View.OnClickListener btnAsignarJanijimAGrupos_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IrAListadoJanijimOGrupos(false);
        }
    };

    private void IrAListadoJanijimOGrupos(boolean ListadoJanijim)
    {
        Intent intent;
        if(ListadoJanijim)
        {
            intent = new Intent(Activity_CargarDatos.this,Activity_ListadoJanijim.class);
        }
        else
        {
            intent = new Intent(Activity_CargarDatos.this,Activity_ListadoGrupos.class);
        }
        startActivity(intent);
        finish();
    }
}
