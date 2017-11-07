package com.revich.mobile.tpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_ABMGrupos extends AppCompatActivity {
    EditText edtNombreGrupoABM, edtJanijParaGrupo;
    Button btnAgregarJanijAlGrupo;
    ListView lstJanijimEnGrupo;
    ArrayList<Janij> Janijim;
    Toast msg;
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
          if(ValidarCamposDeTextoLlenos())
          {
              try
              {
                  int DniJanij= Integer.parseInt(edtJanijParaGrupo.getText().toString());
                  JanijimYGruposManager janijimYGruposManager= new JanijimYGruposManager(getApplicationContext());
                  Janij MiJanij= janijimYGruposManager.SelectJanijConDni(DniJanij);
                  if(MiJanij.Id!=-1)
                  {
                      Janijim= janijimYGruposManager.SelectRegistros();
                      lstJanijimEnGrupo.setAdapter(new adapterLstJanijimEnGrupos(getApplicationContext(),Janijim));
                  }
                  else
                  {
                      msg= Toast.makeText(getApplicationContext(),"Ingrese un DNI numerico",Toast.LENGTH_SHORT);
                      msg.show();
                  }
              }
              catch (Exception ex)
              {
                  msg= Toast.makeText(getApplicationContext(),"No existe un janij con ese DNI",Toast.LENGTH_SHORT);
                  msg.show();
              }
          }
        }
    };

    private boolean ValidarCamposDeTextoLlenos()
    {
        if (!(edtNombreGrupoABM.getText().toString().isEmpty() || edtJanijParaGrupo.getText().toString().isEmpty()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
