package com.revich.mobile.tpn05;

import android.content.ContentValues;
import android.content.Intent;
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
        SQLiteDatabase db =alumnosSQLiteHelper.getWritableDatabase();
        ContentValues nuevoRegistro= new ContentValues();
        String Nombre= edtInsertarNombre.getText().toString();
        nuevoRegistro.put("Nombre",Nombre);
        Random rand= new Random();
        int TrueOFalse= rand.nextInt(2);
        if(TrueOFalse==0)
        {
            nuevoRegistro.put("Casado","false");
        }
        else
        {
            nuevoRegistro.put("Casado","true");
        }
        nuevoRegistro.put("FechaNacimiento","2000/02/03");
        db.insert("Alumnos",null,nuevoRegistro);
        db.close();
    }
    private void ActualizarBaseDeDatos(int Id, String Nombre)
    {
        SQLiteDatabase db =alumnosSQLiteHelper.getWritableDatabase();
        db.execSQL("UPDATE Alumnos SET Nombre="+Nombre);
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
            InsertarBaseDeDatos();
        }
    };





}

