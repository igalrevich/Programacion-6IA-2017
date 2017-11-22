package com.revich.mobile.tpfinal;

import android.content.Context;
<<<<<<< HEAD
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
>>>>>>> cbfe8222f5b8da56efb772a4bd593c14b6e588e9
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
<<<<<<< HEAD
import android.widget.Toast;
=======
>>>>>>> cbfe8222f5b8da56efb772a4bd593c14b6e588e9

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
<<<<<<< HEAD
        lstJanijimPresentismo.setOnItemClickListener(lstJanijimPresentismo_click);
=======
>>>>>>> cbfe8222f5b8da56efb772a4bd593c14b6e588e9
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
<<<<<<< HEAD
             Presentismo MiPresentismo;
                 try
                 {
                     v = lstJanijimPresentismo.getAdapter().getView(i, null, null);
                     TextView tvNombreJanijPresentismo= (TextView) v.findViewById(R.id.tvNombreJanijPresentismo);
                     TextView tvApellidoJanijPresentismo= (TextView) v.findViewById(R.id.tvApellidoJanijPresentismo);
                     TextView tvDNIJanijPresentismo= (TextView) v.findViewById(R.id.tvDNIJanijPresentismo);
                     CheckBox chbVinoJanij= (CheckBox) v.findViewById(R.id.chbVinoJanij);
                     CheckBox chbLlegoTardeJanij= (CheckBox) v.findViewById(R.id.chbLlegoTardeJanij);
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
                     if(chbVinoJanij.isChecked())
                     {
                         MiPresentismo.asistio=true;
                     }
                     else
                     {
                         MiPresentismo.asistio=false;
                     }
                     if(chbLlegoTardeJanij.isChecked())
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
=======
                 v = lstJanijimPresentismo.getAdapter().getView(i, null, null);
                 TextView tvNombreJanijPresentismo= (TextView) v.findViewById(R.id.tvNombreJanijPresentismo);
                 TextView tvApellidoJanijPresentismo= (TextView) v.findViewById(R.id.tvApellidoJanijPresentismo);
                 TextView tvDNIJanijPresentismo= (TextView) v.findViewById(R.id.tvDNIJanijPresentismo);
                 CheckBox chbVinoJanij= (CheckBox) v.findViewById(R.id.chbVinoJanij);

             }
>>>>>>> cbfe8222f5b8da56efb772a4bd593c14b6e588e9
         }
        }
    };

<<<<<<< HEAD
    private ListView.OnItemClickListener lstJanijimPresentismo_click= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        }
    };

=======
>>>>>>> cbfe8222f5b8da56efb772a4bd593c14b6e588e9

}
