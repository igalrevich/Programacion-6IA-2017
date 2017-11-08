package com.revich.mobile.tpfinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 42426410 on 25/10/2017.
 */
public class TPFinalSQLiteHelper extends SQLiteOpenHelper {

    String strSQL="";
    public TPFinalSQLiteHelper(Context contexto, String Nombre, SQLiteDatabase.CursorFactory cursorFactory, int Version)
    {
        super(contexto,Nombre,cursorFactory,Version);
    }


    public TPFinalSQLiteHelper (Context contexto)
    {
        super(contexto,"DBTPFinal",null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Igal", "onCreate");
        JanijimYGruposManager.CreateTableScript(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VersionAnterior, int VersionNueva) {
        Log.d("Igal", "onUpgrade");
        if(VersionAnterior!=VersionNueva)
        {
            db.execSQL("DROP TABLE IF EXISTS Ambitos");
            db.execSQL("DROP TABLE IF EXISTS AmbitosxGrupo");
            db.execSQL("DROP TABLE IF EXISTS Fechas");
            db.execSQL("DROP TABLE IF EXISTS Grupos");
            db.execSQL("DROP TABLE IF EXISTS Habilidades");
            db.execSQL("DROP TABLE IF EXISTS HabilidadesxJanij");
            db.execSQL("DROP TABLE IF EXISTS Janijim");
            db.execSQL("DROP TABLE IF EXISTS JanijimxGrupo");
            db.execSQL("DROP TABLE IF EXISTS Presentismo");
            JanijimYGruposManager.CreateTableScript(db);
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
