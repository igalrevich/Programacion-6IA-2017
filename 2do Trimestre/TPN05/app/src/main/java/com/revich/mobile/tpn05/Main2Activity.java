package com.revich.mobile.tpn05;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {
    ListView lstRegistros;
    AlumnosSQLiteHelper alumnosSQLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lstRegistros= (ListView) findViewById(R.id.lstRegistros);
        alumnosSQLiteHelper= new AlumnosSQLiteHelper(this,"DBAlumnos",null,2);
    }

    private void SelectRegistros()
    {
        SQLiteDatabase db =alumnosSQLiteHelper.getReadableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM Almunos");
    }
}
