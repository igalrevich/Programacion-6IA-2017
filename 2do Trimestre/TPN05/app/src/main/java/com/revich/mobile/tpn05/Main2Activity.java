package com.revich.mobile.tpn05;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {
    ListView lstRegistros;
    AlumnosSQLiteHelper alumnosSQLiteHelper;
    ArrayList<Alumnos> ListaAlumnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lstRegistros= (ListView) findViewById(R.id.lstRegistros);
        alumnosSQLiteHelper= new AlumnosSQLiteHelper(this,"DBAlumnos",null,2);
        SelectRegistros();
        ArrayAdapter<Alumnos> adapterAlumnos= new ArrayAdapter<Alumnos>(this,android.R.layout.simple_list_item_1,ListaAlumnos);
        lstRegistros.setAdapter(adapterAlumnos);

    }

    private void SelectRegistros()
    {
        SQLiteDatabase db =alumnosSQLiteHelper.getReadableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM Alumnos", null);
        ListaAlumnos= new ArrayList<>();
        if(c.moveToFirst())
        {
            do
            {
               Alumnos MiAlumno= new Alumnos();
               MiAlumno.Id= c.getInt(0);
               MiAlumno.Nombre = c.getString(1);
               MiAlumno.Casado = c.getString(2);
               MiAlumno.FechaNacimiento= c.getString(3);
               ListaAlumnos.add(MiAlumno);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
    }

}
