package com.revich.mobile.tpfinal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Activity_Presentismo extends AppCompatActivity {
    Spinner spnGrupos,spnAmbitos,spnFechas;
    ListView lstJanijimPresentismo;
    Button btnConfirmarPresentismo;
    Context context=this;
    ArrayList<Ambito> ListaAmbitos = new ArrayList<>();
    ArrayList<Grupo> ListaGrupos;
    Grupo MiGrupo;
    ArrayList<Fecha> ListaFechas;
    ArrayList<Janij> ListaJanijimEnGrupo;
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
        btnConfirmarPresentismo = (Button) findViewById(R.id.btnConfirmarPresentismo);
        btnConfirmarPresentismo.setOnClickListener(btnConfirmarPresentismo_click);
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
            ArrayAdapter<Ambito> adapterAmbitos= new ArrayAdapter<Ambito>(context,android.R.layout.simple_spinner_item,ListaAmbitos);
            spnAmbitos.setAdapter(adapterAmbitos);
            if(spnFechas.getSelectedItem()!=null)
            {
                ListaJanijimEnGrupo= janijimYGruposManager.SelectJanijimDeUnGrupo(MiGrupo.Id);
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
            String TextoGrupo=spnGrupos.getSelectedItem().toString();
            String[] VecDatosGrupo= TextoGrupo.split(" ");
            VecDatosGrupo[0]= VecDatosGrupo[0].replace(".","");
            MiGrupo.Id= Integer.parseInt(VecDatosGrupo[0]);
            if(spnFechas.getSelectedItem()!=null)
            {
                ListaJanijimEnGrupo= janijimYGruposManager.SelectJanijimDeUnGrupo(MiGrupo.Id);
                lstJanijimPresentismo.setAdapter(new adapterLstJanijimEnGrupos(getApplicationContext(), ListaJanijimEnGrupo));
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private View.OnClickListener btnConfirmarPresentismo_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
         View v;
         if(lstJanijimPresentismo.getCount()>0)
         {
             for(int i=0;i<lstJanijimPresentismo.getCount();i++)
             {
                 v = lstJanijimPresentismo.getAdapter().getView(i, null, null);
                 TextView tvNombreJanijPresentismo= (TextView) v.findViewById(R.id.tvNombreJanijPresentismo);
                 TextView tvApellidoJanijPresentismo= (TextView) v.findViewById(R.id.tvApellidoJanijPresentismo);
                 TextView tvDNIJanijPresentismo= (TextView) v.findViewById(R.id.tvDNIJanijPresentismo);
                 CheckBox chbVinoJanij= (CheckBox) v.findViewById(R.id.chbVinoJanij);

             }
         }
        }
    };


}
