package com.revich.mobile.tpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activity_ABMGrupos extends AppCompatActivity {
    EditText edtNombreGrupoABM, edtJanijParaGrupo;
    Button btnAgregarJanijAlGrupo , btnEliminarJanijDelGrupo;
    ListView lstJanijimEnGrupo;
    ArrayList<Janij> Janijim;
    ArrayAdapter<Janij> adapterJanijim;
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
        adapterJanijim= new ArrayAdapter<Janij>(this,android.R.layout.simple_list_item_1,Janijim);
        lstJanijimEnGrupo.setAdapter(adapterJanijim);

    }

    private void ObtenerReferenciasYSetearListeners()
    {
        btnAgregarJanijAlGrupo = (Button) findViewById(R.id.btnAgregarJanijAlGrupo);
        btnEliminarJanijDelGrupo = (Button) findViewById(R.id.btnEliminarJanijEnGrupo);
        edtNombreGrupoABM = (EditText) findViewById(R.id.edtNombreGrupoABM);
        edtJanijParaGrupo = (EditText) findViewById(R.id.edtJanijParaGrupo);
        btnAgregarJanijAlGrupo.setOnClickListener(btnAgregarJanijAlGrupo_click);
        lstJanijimEnGrupo = (ListView) findViewById(R.id.lstJanijimEnGrupo);
        btnEliminarJanijDelGrupo.setOnClickListener(btnEliminarJanijDelGrupo_click);
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
                      String[] NombreYAñoGrupo= edtNombreGrupoABM.getText().toString().split("-");
                      Grupo MiGrupo= new Grupo();
                      MiGrupo.Nombre= NombreYAñoGrupo[0];
                      MiGrupo.Año = Integer.parseInt(NombreYAñoGrupo[1]);
                      int IdGrupo= janijimYGruposManager.SelectId(MiJanij,MiGrupo,false);
                      boolean SeInsertoConExito=janijimYGruposManager.InsertarJanijEnGrupo(MiJanij.Id,IdGrupo);
                      if(SeInsertoConExito)
                      {
                          msg= Toast.makeText(getApplicationContext(),"El janij se inserto al grupo con exito",Toast.LENGTH_SHORT);
                      }
                      else
                      {
                          msg= Toast.makeText(getApplicationContext(),"Dicho janij ya se encuentra en ese grupo",Toast.LENGTH_SHORT);
                      }
                      msg.show();
                      adapterJanijim= new ArrayAdapter<Janij>(getApplicationContext(),android.R.layout.simple_list_item_1,Janijim);
                      lstJanijimEnGrupo.setAdapter(adapterJanijim);
                  }
                  else
                  {
                      msg= Toast.makeText(getApplicationContext(),"No existe un janij con ese DNI",Toast.LENGTH_SHORT);
                      msg.show();
                  }
              }
              catch (Exception ex)
              {
                  msg= Toast.makeText(getApplicationContext(),"Ingrese un DNI numerico",Toast.LENGTH_SHORT);
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

    private View.OnClickListener btnEliminarJanijDelGrupo_click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try
            {
                int DniJanij= Integer.parseInt(edtJanijParaGrupo.getText().toString());
                JanijimYGruposManager janijimYGruposManager= new JanijimYGruposManager(getApplicationContext());
                Janij MiJanij= janijimYGruposManager.SelectJanijConDni(DniJanij);
                if(MiJanij.Id!=-1)
                {
                    String[] NombreYAñoGrupo= edtNombreGrupoABM.getText().toString().split("-");
                    Grupo MiGrupo= new Grupo();
                    MiGrupo.Nombre= NombreYAñoGrupo[0];
                    MiGrupo.Año = Integer.parseInt(NombreYAñoGrupo[1]);
                    int IdGrupo= janijimYGruposManager.SelectId(MiJanij,MiGrupo,false);
                    janijimYGruposManager.EliminarJanijEnGrupo(MiJanij.getId(),IdGrupo);
                    adapterJanijim= new ArrayAdapter<Janij>(getApplicationContext(),android.R.layout.simple_list_item_1,Janijim);
                    lstJanijimEnGrupo.setAdapter(adapterJanijim);
                }
                else
                {
                    msg= Toast.makeText(getApplicationContext(),"No existe un janij con ese DNI",Toast.LENGTH_SHORT);
                    msg.show();
                }
            }
            catch (Exception ex)
            {
            msg= Toast.makeText(getApplicationContext(),"Ingrese un DNI numerico",Toast.LENGTH_SHORT);
            msg.show();
            }
        }
    };
}
