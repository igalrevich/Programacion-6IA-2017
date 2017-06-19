package com.revich.mobile.tpn05;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

/**
 * Created by 42426410 on 5/6/2017.
 */
public class AlumnosSQLiteHelper extends SQLiteOpenHelper {

    String strSQL="";
    public AlumnosSQLiteHelper(Context contexto, String Nombre, CursorFactory cursorFactory,int Version)
    {
        super(contexto,Nombre,cursorFactory,Version);
    }

    public AlumnosSQLiteHelper(Context contexto)
    {
        super(contexto,"DBAlumnos",null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Igal", "onCreate");
        strSQL = AlumnosManager.CreateTableScript();
        db.execSQL(strSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VersionAnterior, int VersionNueva) {
        Log.d("Igal", "onUpgrade");
        if(VersionAnterior!=VersionNueva)
        {
            db.execSQL("DROP TABLE IF EXISTS Alumnos");
            strSQL = AlumnosManager.CreateTableScript();
            db.execSQL(strSQL);
        }
    }

    public SQLiteDatabase open (boolean forWriting){
        return forWriting ? this.getWritableDatabase() : this.getReadableDatabase();
    }

    //
    // Cierra una SQLiteDatabase
    //
    public void close(SQLiteDatabase db) {
        try {
            if (db.inTransaction()) {
                db.endTransaction();
            }
            db.close();
        } catch (RuntimeException e) {
            //Log.d(LOG_TAG, "Error", e);

        }
    }
}
