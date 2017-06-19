package com.revich.mobile.tpn05;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    Button btnInsertar, btnEliminar, btnActualizar, btnRegistrosOtraActivity;
    EditText edtInsertarNombre, edtEliminarNombre, edtActualizarNombre, edtActualizarId;
    AlumnosSQLiteHelper alumnosSQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObtenerReferencias();
        alumnosSQLiteHelper= new AlumnosSQLiteHelper(this,"DBAlumnos",null,2);
        SetearListeners();
        Log.d("Constructor", "Paso por el constructor");

    }
    private void ObtenerReferencias()
    {
        btnInsertar= (Button) findViewById(R.id.btnInsertar);
        btnEliminar= (Button) findViewById(R.id.btnEliminar);
        btnActualizar= (Button) findViewById(R.id.btnActualizar);
        btnRegistrosOtraActivity= (Button) findViewById(R.id.btnRegistrosOtraActivity);
        edtActualizarNombre=(EditText) findViewById(R.id.edtActualizarNombre);
        edtActualizarId=(EditText) findViewById(R.id.edtActualizarId);
        edtInsertarNombre=(EditText) findViewById(R.id.edtInsertarNombre);
        edtEliminarNombre=(EditText) findViewById(R.id.edtEliminarNombre);
    }
    private void InsertarBaseDeDatos()
    {
        String Nombre= edtInsertarNombre.getText().toString();
        Alumnos MiAlumno= new Alumnos();
        MiAlumno.setNombre(Nombre);
         Random rand= new Random();
        int TrueOFalse= rand.nextInt(2);
        if(TrueOFalse==0)
        {
            MiAlumno.setCasado("false");
        }
        else
        {
            MiAlumno.setCasado("true");
        }
        MiAlumno.setFechaNacimiento("2000/02/03");
        AlumnosManager alumnosManager= new AlumnosManager(this);
        int IdIngresado= alumnosManager.InsertarBaseDeDatos(MiAlumno);
        Toast msg= Toast.makeText(getApplicationContext(), "Se hizo un INSERT con el id "+String.valueOf(IdIngresado), Toast.LENGTH_SHORT);
        msg.show();
    }
    private void ActualizarBaseDeDatos(int Id, String Nombre)
    {
        Alumnos MiAlumno= new Alumnos();
        AlumnosManager alumnosManager= new AlumnosManager(this);
        MiAlumno.setId(Id);
        MiAlumno.setNombre(Nombre);
        int RegistrosAfectados=alumnosManager.ActualizarBaseDeDatos(MiAlumno);
        Toast msg= Toast.makeText(getApplicationContext(), "Se hizo un UPDATE a "+String.valueOf(RegistrosAfectados)+ " registros", Toast.LENGTH_SHORT);
        msg.show();
    }
    private void EliminarBaseDeDatos( int Id)
    {
        AlumnosManager alumnosManager= new AlumnosManager(this);
        int RegistrosAfectados= alumnosManager.EliminarBaseDeDatos(Id);
        Toast msg= Toast.makeText(getApplicationContext(), "Se hizo un DELETE a "+String.valueOf(RegistrosAfectados)+ " registros", Toast.LENGTH_SHORT);
        msg.show();
    }
    private void SetearListeners()
    {
        btnInsertar.setOnClickListener(btnInsertar_click);
        btnEliminar.setOnClickListener(btnEliminar_click);
        btnActualizar.setOnClickListener(btnActualizar_click);
        btnRegistrosOtraActivity.setOnClickListener(btnRegistrosOtraActivity_click);
    }

    private void IniciarSegundaActivity()
    {
        Intent ElIntent= new Intent(this,Main2Activity.class);
        startActivity(ElIntent);
    }

    private View.OnClickListener btnInsertar_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
         InsertarBaseDeDatos();
        }
    };

    private View.OnClickListener btnRegistrosOtraActivity_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IniciarSegundaActivity();
        }
    };

    private View.OnClickListener btnEliminar_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int Id=ValidarId("Eliminar");
            if(Id!=0)
            {
              EliminarBaseDeDatos(Id);
            }
        }
    };

    private View.OnClickListener btnActualizar_click= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int Id=ValidarId("Actualizar");
            String Nombre= edtActualizarNombre.getText().toString();
            if(Id!=0)
            {
                ActualizarBaseDeDatos(Id,Nombre);
            }
        }
    };

    private int ValidarId(String Operacion)
    {
        String TextoId="";
        if(Operacion.equals("Actualizar"))
        {
            TextoId= edtActualizarId.getText().toString();
            if(TextoId.isEmpty())
            {
                Toast msg= Toast.makeText(getApplicationContext(),"Ingrese el id del registro que quiere actualizar",Toast.LENGTH_SHORT);
                msg.show();
                return 0;
            }
            else
            {
                try
                {
                    int Id= Integer.parseInt(TextoId);
                    if(Id<=0)
                    {
                        Toast msg= Toast.makeText(getApplicationContext(),"Ingrese un id mayor a 0",Toast.LENGTH_SHORT);
                        msg.show();
                        return 0;
                    }
                    else
                    {
                        return Id;
                    }
                }
                catch(Exception e)
                {
                    Toast msg= Toast.makeText(getApplicationContext(),"Ingrese un numero",Toast.LENGTH_SHORT);
                    msg.show();
                    return 0;
                }
            }
        }
        else
        {
            TextoId= edtEliminarNombre.getText().toString();
            if(TextoId.isEmpty())
            {
                Toast msg= Toast.makeText(getApplicationContext(),"Ingrese el id del registro que quiere eliminar",Toast.LENGTH_SHORT);
                msg.show();
                return 0;
            }
            else
            {
                try
                {
                    int Id= Integer.parseInt(TextoId);
                    if(Id<=0)
                    {
                        Toast msg= Toast.makeText(getApplicationContext(),"Ingrese un id mayor a 0",Toast.LENGTH_SHORT);
                        msg.show();
                        return 0;
                    }
                    else
                    {
                        return Id;
                    }
                }
                catch(Exception e)
                {
                    Toast msg= Toast.makeText(getApplicationContext(),"Ingrese un numero",Toast.LENGTH_SHORT);
                    msg.show();
                    return 0;
                }
            }
        }
      }
    }





