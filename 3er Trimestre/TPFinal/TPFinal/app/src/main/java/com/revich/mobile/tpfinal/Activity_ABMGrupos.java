package com.revich.mobile.tpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Activity_ABMGrupos extends AppCompatActivity {
    EditText edtNombreGrupoABM, edtJanijParaGrupo;
    Button btnAgregarJanijAlGrupo;
    ListView lstJanijimEnGrupo;
    ArrayList<Janij> Janijim;
    JanijimYGruposManager janijimYGruposManager= new JanijimYGruposManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__abmgrupos);
        ObtenerReferenciasYSetearListeners();
        Intent intent= getIntent();
        Bundle ElBundle = intent.getExtras();
        edtNombreGrupoABM.setText(ElBundle.getString("NombreAñoGrupo"));
        Janijim= janijimYGruposManager.SelectRegistros();
        lstJanijimEnGrupo.setAdapter(new adapterLstJanijimEnGrupos(this,Janijim ));
    }

    private void ObtenerReferenciasYSetearListeners()
    {
        btnAgregarJanijAlGrupo = (Button) findViewById(R.id.btnAgregarJanijAlGrupo);
        edtNombreGrupoABM = (EditText) findViewById(R.id.edtNombreGrupoABM);
        edtJanijParaGrupo = (EditText) findViewById(R.id.edtJanijParaGrupo);
        btnAgregarJanijAlGrupo.setOnClickListener(btnAgregarJanijAlGrupo_click);
        lstJanijimEnGrupo = (ListView) findViewById(R.id.lstJanijimEnGrupo);
    }

    private View.OnClickListener btnAgregarJanijAlGrupo_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //IrAListadoJanijimOGrupos(true,false);
        }
    };
}
