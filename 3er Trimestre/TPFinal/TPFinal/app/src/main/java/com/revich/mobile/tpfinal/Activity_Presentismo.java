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
    Boolean Vino,LlegoTarde;
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
                /*ListaJanijimEnGrupo= janijimYGruposManager.SelectJanijimDeUnGrupo(MiGrupo.Id);
                adapterLstJanijimEnGrupos= new adapterLstJanijimEnGrupos(getApplicationContext(),ListaJanijimEnGrupo);
                lstJanijimPresentismo.setAdapter(adapterLstJanijimEnGrupos);*/
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
                adapterLstJanijimEnGrupos= new adapterLstJanijimEnGrupos(context,ListaJanijimEnGrupo);
                Log.d("CheckedVino1", String.valueOf(adapterLstJanijimEnGrupos.checkedVino[0]));
                lstJanijimPresentismo.setAdapter(adapterLstJanijimEnGrupos);
                Log.d("CheckedVino2", String.valueOf(adapterLstJanijimEnGrupos.checkedVino[0]));
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
         {   Log.d("CheckedVino-2[0] ", String.valueOf(adapterLstJanijimEnGrupos.checkedVino[0]));
             Log.d("CheckedTarde-2[0] ", String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[0]));
             Log.d("CheckedVino-2[1] ", String.valueOf(adapterLstJanijimEnGrupos.checkedVino[1]));
             Log.d("CheckedTarde-2[1] ", String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[1]));
             for(int i=0;i<lstJanijimPresentismo.getCount();i++)
             {
                 Presentismo MiPresentismo;
                 try
                 {   Vino= adapterLstJanijimEnGrupos.checkedVino[i];
                     LlegoTarde=adapterLstJanijimEnGrupos.checkedTarde[i];
                     Log.d("CheckedTarde-1 "+String.valueOf(i), String.valueOf(LlegoTarde));
                     v = lstJanijimPresentismo.getAdapter().getView(i, null, null);
                     Log.d("CheckedVino1 "+String.valueOf(i), String.valueOf(Vino));
                     Log.d("CheckedTarde1 "+String.valueOf(i), String.valueOf(LlegoTarde));
                     TextView tvNombreJanijPresentismo= (TextView) v.findViewById(R.id.tvNombreJanijPresentismo);
                     Log.d("CheckedVino2 "+String.valueOf(i), String.valueOf(Vino));
                     Log.d("CheckedTarde2 "+String.valueOf(i), String.valueOf(LlegoTarde));
                     TextView tvApellidoJanijPresentismo= (TextView) v.findViewById(R.id.tvApellidoJanijPresentismo);
                     Log.d("CheckedVino3 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde3 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     TextView tvDNIJanijPresentismo= (TextView) v.findViewById(R.id.tvDNIJanijPresentismo);
                     Log.d("CheckedVino4 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde4 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     chbVinoJanij= (CheckBox) v.findViewById(R.id.chbVinoJanij);
                     Log.d("CheckedVino5 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde5 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     chbLlegoTardeJanij= (CheckBox) v.findViewById(R.id.chbLlegoTardeJanij);
                     Log.d("CheckedVino6 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde6 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     MiPresentismo=new Presentismo();
                     Log.d("CheckedVino7 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde7 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     int DniJanij= Integer.parseInt(tvDNIJanijPresentismo.getText().toString());
                     Log.d("CheckedVino8 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde8 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     Janij MiJanij= new Janij();
                     Log.d("CheckedVino9 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde9 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     Grupo MiGrupo= new Grupo();
                     Log.d("CheckedVino10 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde10 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     MiJanij.DNI= DniJanij;
                     Log.d("CheckedVino11 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde11 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     MiPresentismo.Janij= janijimYGruposManager.SelectId(MiJanij,MiGrupo,true);
                     Log.d("CheckedVino12 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde12 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     String [] TextoGrupos= spnGrupos.getSelectedItem().toString().split(" ");
                     Log.d("CheckedVino13 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde13 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     MiGrupo.Nombre=TextoGrupos[1];
                     Log.d("CheckedVino14 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde14 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     MiGrupo.Año= Integer.parseInt(TextoGrupos[2]);
                     Log.d("CheckedVino15 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde15 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     MiPresentismo.idGrupo= janijimYGruposManager.SelectId(MiJanij,MiGrupo,false);
                     Log.d("CheckedVino16 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde16 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     String NombreAmbito=spnAmbitos.getSelectedItem().toString();
                     Log.d("CheckedVino17 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde17 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     String NombreFecha= spnFechas.getSelectedItem().toString();
                     Log.d("CheckedVino18 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde18 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     MiPresentismo.idAmbito= janijimYGruposManager.SelectIdAmbitoOFecha(NombreAmbito,NombreFecha,true);
                     Log.d("CheckedVino19 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde19 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     MiPresentismo.Fecha= janijimYGruposManager.SelectIdAmbitoOFecha(NombreAmbito,NombreFecha,false);
                     Log.d("CheckedVino20 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedVino[i]));
                     Log.d("CheckedTarde20 "+String.valueOf(i), String.valueOf(adapterLstJanijimEnGrupos.checkedTarde[i]));
                     if(Vino)
                     {
                         MiPresentismo.asistio=true;
                     }
                     else
                     {
                         MiPresentismo.asistio=false;
                     }
                     if(LlegoTarde)
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
