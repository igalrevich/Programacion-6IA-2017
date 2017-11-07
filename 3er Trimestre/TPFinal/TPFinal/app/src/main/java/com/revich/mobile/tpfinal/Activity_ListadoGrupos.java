package com.revich.mobile.tpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Activity_ListadoGrupos extends AppCompatActivity {
    ListView lstGrupos;
    Button btnAgregarGrupo;
    ArrayList<Grupo> ListaGrupos;
    Grupo MiGrupo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__listado_grupos);
        JanijimYGruposManager janijimYGruposManager= new JanijimYGruposManager(this);
        ListaGrupos= janijimYGruposManager.SelectGrupos();
        ArrayAdapter<Grupo> adapterGrupos= new ArrayAdapter<Grupo>(this,android.R.layout.simple_list_item_1,ListaGrupos);
        lstGrupos.setAdapter(adapterGrupos);
        ObtenerReferenciasYSetearListeners();
    }

    private void ObtenerReferenciasYSetearListeners()
    {
        lstGrupos= (ListView) findViewById(R.id.lstGrupos);
        btnAgregarGrupo = (Button) findViewById(R.id.btnAgregarJanij);
        btnAgregarGrupo.setOnClickListener(btnAgregarGrupo_click);
        lstGrupos.setOnItemClickListener(lstGrupos_itemclick);
    }

    private View.OnClickListener btnAgregarGrupo_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IrAABMGrupos(true);
        }
    };

    private ListView.OnItemClickListener lstGrupos_itemclick= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent= getIntent();
            Bundle ElBundle= intent.getExtras();
            boolean AsignarJanijim= ElBundle.getBoolean("AsignarJanijim");
            MiGrupo= ListaGrupos.get(i);
            if(AsignarJanijim==false)
            {
                IrAABMGrupos(false);
            }
            else
            {
                IrAAsignarJanijimAGrupos();
            }

        }
    };

    private void  IrAABMGrupos( boolean AgregarGrupo)
    {
        Intent intent= new Intent(Activity_ListadoGrupos.this,Activity_AgregarGrupo.class);
        Bundle ElBundle= new Bundle();
        ElBundle.putBoolean("AgregarGrupo",AgregarGrupo);
        if(AgregarGrupo==false)
        {

            ElBundle.putInt("IdGrupo",MiGrupo.Id);
            ElBundle.putString("NombreGrupo",MiGrupo.Nombre);
            ElBundle.putInt("AñoGrupo",MiGrupo.Año);
        }
        intent.putExtras(ElBundle);
        startActivity(intent);
        finish();
    }

    private void  IrAAsignarJanijimAGrupos()
    {
        Intent intent= new Intent(Activity_ListadoGrupos.this,Activity_ABMGrupos.class);
        Bundle ElBundle= new Bundle();
        String NombreYAñoGrupo= MiGrupo.Nombre+"-"+ String.valueOf(MiGrupo.Año);
        ElBundle.putString("NombreAñoGrupo",NombreYAñoGrupo);
        intent.putExtras(ElBundle);
        startActivity(intent);
        finish();
    }


}
