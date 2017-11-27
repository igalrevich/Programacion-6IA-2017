package com.revich.mobile.tpfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_Presentismo extends AppCompatActivity {
    Spinner spnGrupos,spnAmbitos,spnFechas;
    ListView lstJanijimPresentismo;
    Button btnConfirmarPresentismo;
    Context context=this;
    ArrayList<Ambito> ListaAmbitos = new ArrayList<>();
    ArrayList<Grupo> ListaGrupos;
    Grupo MiGrupo;
    Ambito MiAmbito;
    ArrayList<Fecha> ListaFechas;
    ArrayList<Janij> ListaJanijimEnGrupo;
    JanijimYGruposManager janijimYGruposManager;
    CheckBox chbVinoJanij,chbLlegoTardeJanij;
    adapterLstJanijimEnGrupos adapterLstJanijimEnGrupos;

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
        lstJanijimPresentismo.setOnItemClickListener(lstJanijimPresentismo_click1);
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
                adapterLstJanijimEnGrupos= new adapterLstJanijimEnGrupos(getApplicationContext(),ListaJanijimEnGrupo);
                lstJanijimPresentismo.setAdapter(adapterLstJanijimEnGrupos);
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
                adapterLstJanijimEnGrupos= new adapterLstJanijimEnGrupos(getApplicationContext(),ListaJanijimEnGrupo);
                lstJanijimPresentismo.setAdapter(adapterLstJanijimEnGrupos);
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
             Presentismo MiPresentismo;
                 try
                 {
                     v = lstJanijimPresentismo.getAdapter().getView(i, null, null);
                     TextView tvNombreJanijPresentismo= (TextView) v.findViewById(R.id.tvNombreJanijPresentismo);
                     TextView tvApellidoJanijPresentismo= (TextView) v.findViewById(R.id.tvApellidoJanijPresentismo);
                     TextView tvDNIJanijPresentismo= (TextView) v.findViewById(R.id.tvDNIJanijPresentismo);
                     chbVinoJanij= (CheckBox) v.findViewById(R.id.chbVinoJanij);
                     chbLlegoTardeJanij= (CheckBox) v.findViewById(R.id.chbLlegoTardeJanij);
                     MiPresentismo=new Presentismo();
                     int DniJanij= Integer.parseInt(tvDNIJanijPresentismo.getText().toString());
                     Janij MiJanij= new Janij();
                     Grupo MiGrupo= new Grupo();
                     MiJanij.DNI= DniJanij;
                     MiPresentismo.Janij= janijimYGruposManager.SelectId(MiJanij,MiGrupo,true);
                     String [] TextoGrupos= spnGrupos.getSelectedItem().toString().split(" ");
                     MiGrupo.Nombre=TextoGrupos[1];
                     MiGrupo.Año= Integer.parseInt(TextoGrupos[2]);
                     MiPresentismo.idGrupo= janijimYGruposManager.SelectId(MiJanij,MiGrupo,false);
                     String NombreAmbito=spnAmbitos.getSelectedItem().toString();
                     String NombreFecha= spnFechas.getSelectedItem().toString();
                     MiPresentismo.idAmbito= janijimYGruposManager.SelectIdAmbitoOFecha(NombreAmbito,NombreFecha,true);
                     MiPresentismo.Fecha= janijimYGruposManager.SelectIdAmbitoOFecha(NombreAmbito,NombreFecha,false);
                     if(adapterLstJanijimEnGrupos.checkedVino[i])
                     {
                         MiPresentismo.asistio=true;
                     }
                     else
                     {
                         MiPresentismo.asistio=false;
                     }
                     if(adapterLstJanijimEnGrupos.checkedTarde[i])
                     {
                         MiPresentismo.tarde=true;
                     }
                     else
                     {
                         MiPresentismo.tarde=false;
                     }
                     janijimYGruposManager.InsertarPresentismo(MiPresentismo);
                 }
                 catch (Exception ex)
                 {
                     Log.e("Exception", ex.getMessage() );
                 }
             }
             Toast msg= Toast.makeText(getApplicationContext(),"Se insertaron los datos de presentismo con exito",Toast.LENGTH_SHORT);
             msg.show();
         }
        }
    };


    private ListView.OnItemClickListener lstJanijimPresentismo_click1= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Janij MiJanij = ListaJanijimEnGrupo.get(i);
            String Fecha = spnFechas.getSelectedItem().toString();
            MiGrupo = ListaGrupos.get(i);
            MiAmbito = ListaAmbitos.get(i);
            IrAActivityHabilidades(MiJanij.Nombre, MiJanij.Apellido, Fecha, MiGrupo.Id, MiAmbito.Id, MiJanij.Id);
        }
    };

    private void IrAActivityHabilidades(String NombreJanij, String ApellidoJanij, String Fecha, int idGrupo, int idAmbito,int idJanij)
    {
        Intent intent= new Intent(Activity_Presentismo.this,Activity_Habilidades.class);
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
