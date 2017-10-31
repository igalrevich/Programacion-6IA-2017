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

public class Activity_ListadoJanijim extends AppCompatActivity {
     ListView lstJanijim;
     Button btnAgregarJanij;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__listado_janijim);
        JanijimYGruposManager janijimYGruposManager= new JanijimYGruposManager(this);
        ArrayList<Janij> ListaJanijim= janijimYGruposManager.SelectRegistros();
        ArrayAdapter<Janij> adapterJanijim= new ArrayAdapter<Janij>(this,android.R.layout.simple_list_item_1,ListaJanijim);
        lstJanijim.setAdapter(adapterJanijim);
        ObtenerReferenciasYSetearListeners();
    }

    private void ObtenerReferenciasYSetearListeners()
    {
        lstJanijim= (ListView) findViewById(R.id.lstJanijim);
        btnAgregarJanij = (Button) findViewById(R.id.btnAgregarJanij);
        btnAgregarJanij.setOnClickListener(btnAgregarJanij_click);
        lstJanijim.setOnItemClickListener(lstJanijim_itemclick);
    }

    private View.OnClickListener btnAgregarJanij_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IrAABMJanijim(true);
        }
    };

    private ListView.OnItemClickListener lstJanijim_itemclick= new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
          
        }
    };

    private void IrAABMJanijim(boolean AgregarJanij)
    {
        Intent intent;
        if(AgregarJanij)
        {
            intent = new Intent(Activity_ListadoJanijim.this,Activity_ABMJanijim.class);
        }
        else
        {
            intent = new Intent(Activity_ListadoJanijim.this,Activity_ABMJanijim.class);
            Bundle ElBundle= new Bundle();
        }
        startActivity(intent);
        finish();
    }

}
