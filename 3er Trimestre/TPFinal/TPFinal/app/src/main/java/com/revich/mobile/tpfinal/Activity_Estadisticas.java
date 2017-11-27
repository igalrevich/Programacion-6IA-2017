package com.revich.mobile.tpfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Activity_Estadisticas extends AppCompatActivity {
    Spinner spnGruposEstadisticas,spnJanijimEstadisticas;
    ListView lstEstadisticas;
    ArrayList<Grupo> ListaGrupos;
    ArrayList<Janij> ListaJanijimEnGrupo;
    ArrayList<Presentismo> ListaPresentismo;
    ArrayList<HabilidadxJanij> ListaHabilidadesxJanij;
    Context context=this;
    JanijimYGruposManager janijimYGruposManager;
    Grupo MiGrupo;
    Janij MiJanij;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        ObtenerReferenciasYSetearListeners();
        janijimYGruposManager= new JanijimYGruposManager(this);
    }

    private void ObtenerReferenciasYSetearListeners()
    {
        lstEstadisticas=(ListView) findViewById(R.id.lstEstadisticas);
        lstEstadisticas.setOnItemClickListener(lstEstadisticas_click);
        spnGruposEstadisticas = (Spinner) findViewById(R.id.spnGruposEstadisticas);
        spnGruposEstadisticas.setOnItemSelectedListener(spnGruposEstadisticas_itemclicklistener);
        spnJanijimEstadisticas = (Spinner) findViewById(R.id.spnJanijimEstadisticas);
        spnJanijimEstadisticas.setOnItemSelectedListener(spnJanijimEstadisticas_itemclicklistener);
    }

    private Spinner.OnItemSelectedListener spnGruposEstadisticas_itemclicklistener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            MiGrupo= ListaGrupos.get(i);
            ListaJanijimEnGrupo= janijimYGruposManager.SelectJanijimDeUnGrupo(MiGrupo.Id);
            ArrayAdapter<Janij> adapterJanijim= new ArrayAdapter<Janij>(context,android.R.layout.simple_spinner_item,ListaJanijimEnGrupo);
            spnJanijimEstadisticas.setAdapter(adapterJanijim);

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private Spinner.OnItemSelectedListener spnJanijimEstadisticas_itemclicklistener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String TextoJanij=spnJanijimEstadisticas.getSelectedItem().toString();
            String[] VecDatosJanij= TextoJanij.split(" ");
            VecDatosJanij[0]= VecDatosJanij[0].replace(".","");
            MiJanij.Id= Integer.parseInt(VecDatosJanij[0]);
            ListaPresentismo= janijimYGruposManager.SelectPresentismoDeUnJanij(MiJanij.Id);
            ListaHabilidadesxJanij=janijimYGruposManager.SelectHabilidadesDeUnJanij(MiJanij.Id);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private ListView.OnItemClickListener lstEstadisticas_click= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


        }
    };
}
