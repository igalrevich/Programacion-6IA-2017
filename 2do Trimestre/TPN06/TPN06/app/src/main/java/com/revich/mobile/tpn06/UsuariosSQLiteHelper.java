package com.revich.mobile.tpn06;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
/**
 * Created by 42426410 on 5/7/2017.
 */
public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    String strSQL="";
    public UsuariosSQLiteHelper(Context contexto, String Nombre, CursorFactory cursorFactory,int Version)
    {
        super(contexto,Nombre,cursorFactory,Version);
    }

    public UsuariosSQLiteHelper(Context contexto)
    {
        super(contexto,"DBUsuariosTPN06",null,2);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Igal", "onCreate");
        strSQL = UsuariosManager.CreateTableScript();
        db.execSQL(strSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int VersionAnterior, int VersionNueva) {
        Log.d("Igal", "onUpgrade");
        if(VersionAnterior!=VersionNueva)
        {
            db.execSQL("DROP TABLE IF EXISTS Alumnos");
            strSQL = UsuariosManager.CreateTableScript();
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
