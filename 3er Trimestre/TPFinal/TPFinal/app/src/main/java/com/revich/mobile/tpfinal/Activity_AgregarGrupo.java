package com.revich.mobile.tpfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_AgregarGrupo extends AppCompatActivity {
    Button btnAgregarGrupo, btnEliminarGrupo;
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
        btnEliminarGrupo = (Button) findViewById(R.id.btnEliminarGrupo);
        edtNombreGrupo= (EditText) findViewById(R.id.edtNombreGrupoABM);
        edtAñoGrupo= (EditText) findViewById(R.id.edtAñoGrupo);
        btnAgregarGrupo.setOnClickListener(btnAgregarGrupo_click);
        btnEliminarGrupo.setOnClickListener(btnEliminarGrupo_click);
        edtNombreGrupo.setClickable(false);
    }

    private View.OnClickListener btnAgregarGrupo_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //IrAABMJanijim(true);
            if(ValidarCamposDeTextoLlenos())
            {
                if (ValidarCamposDeTextoLlenos())
                {
                    if(AgregarGrupo)
                    {
                        MiGrupo.setNombre(edtNombreGrupo.getText().toString());
                        try
                        {
                            MiGrupo.setAño(Integer.parseInt(edtAñoGrupo.getText().toString()));
                            janijimYGruposManager.InsertarJanijOGrupo(MiJanij,MiGrupo,false);
                        }
                        catch (Exception ex)
                        {
                            msg=Toast.makeText(getApplicationContext(),"Ingresar un dato numerico en el año",Toast.LENGTH_SHORT);
                            msg.show();
                        }

                    }
                    else
                    {
                        int IdObtenido=janijimYGruposManager.SelectId(MiJanij,MiGrupo,false);
                        if(IdObtenido!=0)
                        {
                            MiJanij.Id=IdObtenido;
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
                msg=Toast.makeText(getApplicationContext(),"Ya existe un janij con ese DNI",Toast.LENGTH_SHORT);
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

    private View.OnClickListener btnEliminarGrupo_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MiGrupo= new Grupo();
            if (ValidarCamposDeTextoLlenos())
            {
                int IdObtenido=janijimYGruposManager.SelectId(MiJanij,MiGrupo,false);
                if(IdObtenido!=0)
                {
                    MiGrupo.Id=IdObtenido;
                    MiGrupo.setNombre(edtNombreGrupo.getText().toString());
                    try
                    {
                        MiGrupo.setAño(Integer.parseInt(edtAñoGrupo.getText().toString()));
                        janijimYGruposManager.InsertarJanijOGrupo(MiJanij,MiGrupo,false);
                    }
                    catch (Exception ex)
                    {
                        msg=Toast.makeText(getApplicationContext(),"Ingresar un dato numerico en el año",Toast.LENGTH_SHORT);
                        msg.show();
                    }
                    janijimYGruposManager.EliminarJanijOGrupo(MiJanij,MiGrupo,false);
                }
                else
                {
                    msg=Toast.makeText(getApplicationContext(),"No existe dicho janij",Toast.LENGTH_SHORT);
                    msg.show();
                }
            }
            else
            {
                msg=Toast.makeText(getApplicationContext(),"Hay datos vacios, por favor completelos",Toast.LENGTH_SHORT);
                msg.show();
            }
        }

    };
}
