package com.revich.mobile.tpfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Activity_Presentismo extends AppCompatActivity {
    Spinner spnGrupos,spnAmbitos,spnFechas;
    ListView lstJanijimPresentismo;
    ArrayList<Ambito> ListaAmbitos = new ArrayList<>();
    ArrayList<Grupo> ListaGrupos;
    Grupo MiGrupo;
    ArrayList<Fecha> ListaFechas;
    JanijimYGruposManager janijimYGruposManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__presentismo);
        ObtenerReferenciasYSetearListeners();
        janijimYGruposManager= new JanijimYGruposManager(this);
        ListaGrupos= janijimYGruposManager.SelectGrupos();
        ArrayAdapter<Grupo> adapterGrupos= new ArrayAdapter<Grupo>(this,android.R.layout.simple_spinner_item,ListaGrupos);
        spnGrupos.setAdapter(adapterGrupos);
        ListaFechas= janijimYGruposManager.SelectFechas();
        ArrayAdapter<Fecha> adapterFechas= new ArrayAdapter<Fecha>(this,android.R.layout.simple_spinner_item,ListaFechas);
        spnFechas.setAdapter(adapterFechas);


    }

    private void ObtenerReferenciasYSetearListeners()
    {
        lstJanijimPresentismo=(ListView) findViewById(R.id.lstJanijimPresentismo);
        spnAmbitos = (Spinner) findViewById(R.id.spnAmbitos);
        spnAmbitos.setOnItemSelectedListener(spnAmbitos_itemclicklistener);
        spnGrupos = (Spinner) findViewById(R.id.spnGrupos);
        spnGrupos.setOnItemSelectedListener(spnGrupos_itemclicklistener);
        spnFechas = (Spinner) findViewById(R.id.spnFechas);
    }

    private Spinner.OnItemSelectedListener spnGrupos_itemclicklistener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            MiGrupo= ListaGrupos.get(i);
            ListaAmbitos= janijimYGruposManager.SelectAmbitos(MiGrupo.Id);
            ArrayAdapter<Ambito> adapterAmbitos= new ArrayAdapter<Ambito>(getApplicationContext(),android.R.layout.simple_spinner_item,ListaAmbitos);
            spnAmbitos.setAdapter(adapterAmbitos);
            if(spnFechas.getSelectedItem()!=null)
            {
                ArrayList<Janij> ListaJanijimEnGrupo= janijimYGruposManager.SelectJanijimDeUnGrupo(MiGrupo.Id);
                lstJanijimPresentismo.setAdapter(new adapterLstJanijimEnGrupos(getApplicationContext(), ListaJanijimEnGrupo));
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private Spinner.OnItemSelectedListener spnAmbitos_itemclicklistener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            MiGrupo= ListaGrupos.get(i);
            if(spnFechas.getSelectedItem()!=null)
            {
                ArrayList<Janij> ListaJanijimEnGrupo= janijimYGruposManager.SelectJanijimDeUnGrupo(MiGrupo.Id);
                lstJanijimPresentismo.setAdapter(new adapterLstJanijimEnGrupos(getApplicationContext(), ListaJanijimEnGrupo));
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };


}
