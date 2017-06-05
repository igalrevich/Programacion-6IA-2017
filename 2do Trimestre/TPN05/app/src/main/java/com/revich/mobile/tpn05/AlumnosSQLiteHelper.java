package com.revich.mobile.tpn05;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by 42426410 on 5/6/2017.
 */
public class AlumnosSQLiteHelper extends SQLiteOpenHelper {

    String sqlCreate= "CREATE TABLE Alumnos (id INTEGER PRIMARY KEY, Nombre TEXT, Casado TEXT, FechaNacimiento TEXT NULL)";
    public AlumnosSQLiteHelper(Context contexto, String Nombre, CursorFactory cursorFactory,int Version)
    {
      super(contexto,Nombre,cursorFactory,Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VersionAnterior, int VersionNueva) {
     if(VersionAnterior!=VersionNueva)
     {
       db.execSQL("DROP TABLE IF EXISTS Alumnos");
       db.execSQL(sqlCreate);
     }
    }
}
