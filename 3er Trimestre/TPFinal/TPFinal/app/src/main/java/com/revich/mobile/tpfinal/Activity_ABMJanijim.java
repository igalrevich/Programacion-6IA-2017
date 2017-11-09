package com.revich.mobile.tpfinal;

import android.content.Intent;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_ABMJanijim extends AppCompatActivity {
    Button btnOk, btnEliminarJanij;
    EditText edtDNIJanij, edtApellidoJanij, edtNombreJanij;
    boolean Agregarjanij;
    int IdJanij;
    Janij MiJanij=new Janij();
    Grupo MiGrupo;
    Toast msg;
    JanijimYGruposManager janijimYGruposManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__abmjanijim);
        ObtenerReferenciasYSetearListeners();
        Intent intent = getIntent();
        Bundle ElBundleQueVino= intent.getExtras();
        janijimYGruposManager= new JanijimYGruposManager(this);
        Agregarjanij= ElBundleQueVino.getBoolean("AgregarJanij");
        if(Agregarjanij==false)
        {
            MiJanij.setNombre(ElBundleQueVino.getString("NombreJanij"));
            edtNombreJanij.setText(MiJanij.getNombre());
            MiJanij.setApellido(ElBundleQueVino.getString("ApellidoJanij"));
            edtApellidoJanij.setText(MiJanij.getApellido());
            MiJanij.setDNI(ElBundleQueVino.getInt("DNIJanij"));
            edtDNIJanij.setText(String.valueOf(MiJanij.getDNI()));
            IdJanij = ElBundleQueVino.getInt("IdJanij");
        }

    }

    private void ObtenerReferenciasYSetearListeners()
    {
        btnEliminarJanij = (Button) findViewById(R.id.btnEliminarJanij);
        btnOk = (Button) findViewById(R.id.btnOK);
        edtApellidoJanij= (EditText) findViewById(R.id.edtApellidoJanij);
        edtNombreJanij= (EditText) findViewById(R.id.edtNombreJanij);
        edtDNIJanij= (EditText) findViewById(R.id.edtDNIJanij);
        btnOk.setOnClickListener(btnOk_click);
        btnEliminarJanij.setOnClickListener(btnEliminarJanij_click);
    }

    private View.OnClickListener btnOk_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //IrAABMJanijim(true);
             MiGrupo= new Grupo();
             if(ValidarCamposDeTextoLlenos())
             {
                 if(Agregarjanij)
                 {
                     MiJanij.setNombre(edtNombreJanij.getText().toString());
                     MiJanij.setApellido(edtApellidoJanij.getText().toString());
                     try
                     {
                         MiJanij.setDNI(Integer.parseInt(edtDNIJanij.getText().toString()));
                         boolean DatosValidos= janijimYGruposManager.ValidarJanijimYGrupos(MiJanij,MiGrupo,true);
                         if (DatosValidos)
                         {
                             janijimYGruposManager.InsertarJanijOGrupo(MiJanij,MiGrupo,true);
                             msg=Toast.makeText(getApplicationContext(),"Se inserto el janij con exito",Toast.LENGTH_SHORT);
                             msg.show();
                         }
                         else
                         {
                             msg=Toast.makeText(getApplicationContext(),"Ya existe un janij con ese DNI",Toast.LENGTH_SHORT);
                             msg.show();
                         }

                     }
                     catch (Exception ex)
                     {
                         msg=Toast.makeText(getApplicationContext(),"Ingrese un dato numerico en el DNI",Toast.LENGTH_SHORT);
                         msg.show();
                     }

                 }
                 else
                 {
                     int IdObtenido=janijimYGruposManager.SelectId(MiJanij,MiGrupo,true);
                     if(IdObtenido!=0)
                     {
                         MiJanij.Id=IdObtenido;
                         janijimYGruposManager.ActualizarJanijOGrupo(MiJanij,MiGrupo,true);
                         msg=Toast.makeText(getApplicationContext(),"Se actualizo el janij con exito",Toast.LENGTH_SHORT);
                         msg.show();
                     }
                     else
                     {
                         msg=Toast.makeText(getApplicationContext(),"No existe dicho janij",Toast.LENGTH_SHORT);
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

    private View.OnClickListener btnEliminarJanij_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            MiGrupo= new Grupo();
            if (ValidarCamposDeTextoLlenos())
            {
                int IdObtenido=janijimYGruposManager.SelectId(MiJanij,MiGrupo,true);
                if(IdObtenido!=0)
                {
                    MiJanij.Id=IdObtenido;
                    MiJanij.setNombre(edtNombreJanij.getText().toString());
                    MiJanij.setApellido(edtApellidoJanij.getText().toString());
                    MiJanij.setDNI(Integer.parseInt(edtDNIJanij.getText().toString()));
                    janijimYGruposManager.EliminarJanijOGrupo(MiJanij,MiGrupo,true);
                    msg=Toast.makeText(getApplicationContext(),"Se elimino el janij con exito",Toast.LENGTH_SHORT);
                    msg.show();
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


    private boolean ValidarCamposDeTextoLlenos()
    {
        if (!(edtNombreJanij.getText().toString().isEmpty() || edtApellidoJanij.getText().toString().isEmpty() || edtDNIJanij.getText().toString().isEmpty()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
