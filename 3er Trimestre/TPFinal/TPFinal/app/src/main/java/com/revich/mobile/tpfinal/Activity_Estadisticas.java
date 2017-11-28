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
import android.widget.TextView;

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
        ListaGrupos= janijimYGruposManager.SelectGrupos();
        ArrayAdapter<Grupo> adapterGrupos= new ArrayAdapter<Grupo>(this,android.R.layout.simple_spinner_item,ListaGrupos);
        spnGruposEstadisticas.setAdapter(adapterGrupos);
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
            MiJanij= new Janij();
            MiJanij.Id= Integer.parseInt(VecDatosJanij[0]);
            MiJanij.Nombre=VecDatosJanij[1];
            MiJanij.Apellido=VecDatosJanij[2];
            ListaPresentismo= janijimYGruposManager.SelectPresentismoDeUnJanij(MiJanij.Id);
            ListaHabilidadesxJanij=janijimYGruposManager.SelectHabilidadesDeUnJanij(MiJanij.Id);
            lstEstadisticas.setAdapter(new adapterLstEstadisticas(getApplicationContext(),ListaPresentismo,ListaHabilidadesxJanij));
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private ListView.OnItemClickListener lstEstadisticas_click= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            View v = lstEstadisticas.getAdapter().getView(i, null, null);
            TextView tvFechaEstadisticas= (TextView) v.findViewById(R.id.tvFechaEstadisticas);
            TextView tvAmbitoJanij= (TextView) v.findViewById(R.id.tvAmbitoJanij);
            String NombreAmbito= tvAmbitoJanij.getText().toString();
            int IdAmbito= janijimYGruposManager.SelectIdAmbitoOFecha(NombreAmbito,"",true);
            String Fecha= tvFechaEstadisticas.getText().toString();
            IrAActivityHabilidades(MiJanij.Nombre, MiJanij.Apellido, Fecha, MiGrupo.Id, IdAmbito, MiJanij.Id);

        }
    };

    private void IrAActivityHabilidades(String NombreJanij, String ApellidoJanij, String Fecha, int idGrupo, int idAmbito,int idJanij)
    {
        Intent intent= new Intent(Activity_Estadisticas.this,Activity_Habilidades.class);
        Bundle ElBundle= new Bundle();
        ElBundle.putString("NombreJanij",NombreJanij);
        ElBundle.putString("ApellidoJanij",ApellidoJanij);
        ElBundle.putString("Fecha",Fecha);
        ElBundle.putInt("idGrupo",idGrupo);
        ElBundle.putInt("idAmbito",idAmbito);
        ElBundle.putInt("idJanij",idJanij);
        intent.putExtras(ElBundle);
        startActivity(intent);
    }
}
