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
        edtNombreGrupo= (EditText) findViewById(R.id.edtNombreGrupo);
        edtAñoGrupo= (EditText) findViewById(R.id.edtAñoGrupo);
        btnAgregarGrupo.setOnClickListener(btnAgregarGrupo_click);
    }

    private View.OnClickListener btnAgregarGrupo_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //IrAABMJanijim(true);
            MiGrupo= new Grupo();
            boolean DatosValidos= janijimYGruposManager.ValidarJanijimYGrupos(MiJanij,MiGrupo,false);
            if(DatosValidos)
            {
                if (ValidarCamposDeTextoLlenos())
                {
                    if(AgregarGrupo)
                    {
                        MiGrupo.setNombre(edtNombreGrupo.getText().toString());
                        MiGrupo.setAño(Integer.parseInt(edtAñoGrupo.getText().toString()));
                        janijimYGruposManager.InsertarJanijOGrupo(MiJanij,MiGrupo,false);
                    }
                    else
                    {
                        int IdObtenido=janijimYGruposManager.SelectId(MiJanij,MiGrupo,false);
                        if(IdObtenido!=0)
                        {
                            MiGrupo.Id=IdObtenido;
                            janijimYGruposManager.ActualizarJanijOGrupo(MiJanij,MiGrupo,false);
                        }
                        else
                        {
                            msg=Toast.makeText(getApplicationContext(),"No existe dicho grupo",Toast.LENGTH_SHORT);
                            msg.show();
                        }
                    }
                }
                else
                {
                    msg=Toast.makeText(getApplicationContext(),"Hay datos vacios, por favor completelos",Toast.LENGTH_SHORT);
                    msg.show();
                }
            }
            else
            {
                msg=Toast.makeText(getApplicationContext(),"Ya existe un grupo con dichos datos",Toast.LENGTH_SHORT);
                msg.show();
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
