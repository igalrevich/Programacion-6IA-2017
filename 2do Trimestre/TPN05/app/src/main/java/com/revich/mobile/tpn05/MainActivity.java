package com.revich.mobile.tpn05;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button btnInsertar, btnEliminar, btnActualizar;
    EditText edtInsertarNombre, edtEliminarNombre, edtActualizarNombre, edtActualizarId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ObtenerReferencias();
        AlumnosSQLiteHelper alumnosSQLiteHelper= new AlumnosSQLiteHelper(this,"DBAlumnos",null,1);

    }
    private void ObtenerReferencias()
    {
        btnInsertar= (Button) findViewById(R.id.btnInsertar);
        btnEliminar= (Button) findViewById(R.id.btnEliminar);
        btnActualizar= (Button) findViewById(R.id.btnActualizar);
        edtActualizarNombre=(EditText) findViewById(R.id.edtActualizarNombre);
        edtActualizarId=(EditText) findViewById(R.id.edtActualizarId);
        edtInsertarNombre=(EditText) findViewById(R.id.edtInsertarNombre);
        edtEliminarNombre=(EditText) findViewById(R.id.edtEliminarNombre);
    }

}

