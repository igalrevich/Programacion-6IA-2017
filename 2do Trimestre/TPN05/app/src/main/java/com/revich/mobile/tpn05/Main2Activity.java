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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lstRegistros= (ListView) findViewById(R.id.lstRegistros);
        AlumnosManager alumnosManager= new AlumnosManager(this);
        ArrayList<Alumnos> ListaAlumnos= alumnosManager.SelectRegistros();
        ArrayAdapter<Alumnos> adapterAlumnos= new ArrayAdapter<Alumnos>(this,android.R.layout.simple_list_item_1,ListaAlumnos);
        lstRegistros.setAdapter(adapterAlumnos);

    }


}
