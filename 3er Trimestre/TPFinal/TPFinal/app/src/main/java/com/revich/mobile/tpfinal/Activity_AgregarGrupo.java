package com.revich.mobile.tpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_AgregarGrupo extends AppCompatActivity {
    Button btnAgregarGrupo;
    EditText edtNombreGrupo, edtAñoGrupo;
    Grupo MiGrupo;
    Janij MiJanij;
    Toast msg;
    boolean AgregarGrupo;
    int IdGrupo;
    JanijimYGruposManager janijimYGruposManager = new JanijimYGruposManager(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__agregar_grupo);
        ObtenerReferenciasYSetearListeners();
        Intent intent = getIntent();
        Bundle ElBundleQueVino= intent.getExtras();
        AgregarGrupo= ElBundleQueVino.getBoolean("AgregarGrupo");
        if(AgregarGrupo==false)
        {
            MiGrupo=new Grupo();
            MiGrupo.setNombre(ElBundleQueVino.getString("NombreGrupo"));
            edtNombreGrupo.setText(MiGrupo.getNombre());
            MiGrupo.setAño(ElBundleQueVino.getInt("AñoGrupo"));
            edtAñoGrupo.setText(MiGrupo.getAño());
            IdGrupo = ElBundleQueVino.getInt("IdGrupo");
        }
    }

    private void ObtenerReferenciasYSetearListeners()
    {
        btnAgregarGrupo = (Button) findViewById(R.id.btnAgregarGrupo);
        edtNombreGrupo= (EditText) findViewById(R.id.edtNombreGrupoABM);
        edtAñoGrupo= (EditText) findViewById(R.id.edtAñoGrupo);
        btnAgregarGrupo.setOnClickListener(btnAgregarGrupo_click);
        edtNombreGrupo.setClickable(false);
    }

    private View.OnClickListener btnAgregarGrupo_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //IrAABMJanijim(true);
            if(ValidarCamposDeTextoLlenos())
            {

            }
        }
    };

    private boolean ValidarCamposDeTextoLlenos()
    {
        if (!(edtNombreGrupo.getText().toString().isEmpty() || edtAñoGrupo.getText().toString().isEmpty()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
