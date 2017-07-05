package com.revich.mobile.tpn06;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 42426410 on 5/7/2017.
 */
public class UsuariosManager {

    Context ElContexto;

    public static String CreateTableScript(){
        // Instrucciones DDL para la creación de la estructura de la Tabla.
        String strSQL = "";
        strSQL = "CREATE TABLE Usuarios (";
        strSQL += "	_id 	INTEGER PRIMARY KEY AUTOINCREMENT, ";
        strSQL += "	nombre TEXT, ";
        strSQL += "	ciudadesacertadas INTEGER, ";
        strSQL += "	tiempodejuego TEXT";
        strSQL += ");";
        return strSQL;
    }

    public UsuariosManager(Context ctx){
        // Almacenar el Context en una variable privada de la clase, para futura utilización.
        this.ElContexto = ctx;
    }

    private SQLiteDatabase Abrir(boolean forWriting){
        UsuariosSQLiteHelper usuariosSQLiteHelper= new UsuariosSQLiteHelper(ElContexto);
        SQLiteDatabase db =usuariosSQLiteHelper.open(forWriting);
        return db;
    }

    private boolean ExisteUsuario(String NombreUsuario)
    {
        SQLiteDatabase db= Abrir(false);
        Cursor c= db.rawQuery("SELECT * FROM Usuarios WHERE nombre="+DatosJuego.GetNombre(), null);
        int ContRegistrosAfectados=0;
        if(c.moveToFirst())
        {
            do
            {
                ContRegistrosAfectados++;
            }while (c.moveToNext());
        }
        if(ContRegistrosAfectados>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
