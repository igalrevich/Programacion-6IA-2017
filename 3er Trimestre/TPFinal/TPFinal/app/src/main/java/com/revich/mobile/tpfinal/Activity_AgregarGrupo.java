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
    Grupo MiGrupo= new Grupo();
    Janij MiJanij = new Janij();
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
            MiGrupo.setNombre(ElBundleQueVino.getString("NombreGrupo"));
            edtNombreGrupo.setText(MiGrupo.getNombre());
            MiGrupo.setAño(ElBundleQueVino.getInt("AñoGrupo"));
            edtAñoGrupo.setText(String.valueOf(MiGrupo.getAño()));
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

                if(AgregarGrupo)
                {
                        MiGrupo.setNombre(edtNombreGrupo.getText().toString());
                        try
                        {
                            MiGrupo.setAño(Integer.parseInt(edtAñoGrupo.getText().toString()));
                            if(MiGrupo.Año>0)
                            {
                                boolean DatosValidos= janijimYGruposManager.ValidarJanijimYGrupos(MiJanij,MiGrupo,false);
                                if (DatosValidos)
                                {
                                    janijimYGruposManager.InsertarJanijOGrupo(MiJanij,MiGrupo,false);
                                    msg=Toast.makeText(getApplicationContext(),"Se inserto el grupo con exito",Toast.LENGTH_SHORT);
                                    msg.show();
                                }
                                else
                                {
                                    msg=Toast.makeText(getApplicationContext(),"Ya existe un grupo con esos datos",Toast.LENGTH_SHORT);
                                    msg.show();
                                }
                            }
                            else
                            {
                                msg=Toast.makeText(getApplicationContext(),"El año debe ser positivo",Toast.LENGTH_SHORT);
                                msg.show();
                            }

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
                        MiGrupo.Id=IdObtenido;
                        try
                        {
                            MiGrupo.setAño(Integer.parseInt(edtAñoGrupo.getText().toString()));
                            if(MiGrupo.Año>0)
                            {
                                boolean DatosValidos= janijimYGruposManager.ValidarJanijimYGrupos(MiJanij,MiGrupo,false);
                                if (DatosValidos)
                                {
                                    janijimYGruposManager.ActualizarJanijOGrupo(MiJanij,MiGrupo,false);
                                    msg=Toast.makeText(getApplicationContext(),"Se actualizo el grupo con exito",Toast.LENGTH_SHORT);
                                    msg.show();
                                }
                                else
                                {
                                    msg=Toast.makeText(getApplicationContext(),"Ya existe un grupo con esos datos",Toast.LENGTH_SHORT);
                                    msg.show();
                                }
                            }
                            else
                            {
                                msg=Toast.makeText(getApplicationContext(),"El año debe ser positivo",Toast.LENGTH_SHORT);
                                msg.show();
                            }

                        }
                        catch (Exception ex)
                        {
                            msg=Toast.makeText(getApplicationContext(),"Ingresar un dato numerico en el año",Toast.LENGTH_SHORT);
                            msg.show();
                        }

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
                MiGrupo.setNombre(edtNombreGrupo.getText().toString());
                try
                {
                    MiGrupo.setAño(Integer.parseInt(edtAñoGrupo.getText().toString()));
                    int IdObtenido=janijimYGruposManager.SelectId(MiJanij,MiGrupo,false);
                    if(IdObtenido!=0)
                    {
                        MiGrupo.Id=IdObtenido;
                        janijimYGruposManager.EliminarJanijOGrupo(MiJanij,MiGrupo,false);
                    }
                    else
                    {
                        msg=Toast.makeText(getApplicationContext(),"No existe dicho grupo",Toast.LENGTH_SHORT);
                        msg.show();
                    }
                }
                catch (Exception ex)
                {
                    msg=Toast.makeText(getApplicationContext(),"Ingresar un dato numerico en el año",Toast.LENGTH_SHORT);
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
