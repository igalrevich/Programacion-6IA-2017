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
        strSQL = JanijimYGruposManager.CreateTableScript();
        db.execSQL(strSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VersionAnterior, int VersionNueva) {
        Log.d("Igal", "onUpgrade");
        if(VersionAnterior!=VersionNueva)
        {
            db.execSQL("delete from DBTPFinal where type = 'table'");
            strSQL = JanijimYGruposManager.CreateTableScript();
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

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
