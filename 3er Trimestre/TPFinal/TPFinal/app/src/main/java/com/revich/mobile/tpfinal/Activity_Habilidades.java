package com.revich.mobile.tpfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_Habilidades extends AppCompatActivity {
    Button btnConfirmarHabilidades;
    TextView tvNombreJanijHabilidades, tvFechaHabilidades;
    ListView lstHabilidades;
    ArrayList<Habilidades> ListaHabilidades;
    JanijimYGruposManager janijimYGruposManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__habilidades);
        ObtenerReferenciasYSetearListeners();
        janijimYGruposManager= new JanijimYGruposManager(this);
        ListaHabilidades= janijimYGruposManager.SelectHabilidades();
        lstHabilidades.setAdapter(new adapterLstHabilidades(this,ListaHabilidades));
    }

    private void ObtenerReferenciasYSetearListeners()
    {
        lstHabilidades=(ListView) findViewById(R.id.lstHabilidades);
        btnConfirmarHabilidades = (Button) findViewById(R.id.btnConfirmarHabilidades);
        btnConfirmarHabilidades.setOnClickListener( btnConfirmarHabilidades_click);
        //lstHabilidades.setOnItemClickListener(lstHabilidades_itemclick);
        tvNombreJanijHabilidades = (TextView) findViewById(R.id.tvNombreJanijHabilidades);
        tvFechaHabilidades = (TextView) findViewById(R.id.tvFechaHabilidades);
    }

    private View.OnClickListener btnConfirmarHabilidades_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };
}
