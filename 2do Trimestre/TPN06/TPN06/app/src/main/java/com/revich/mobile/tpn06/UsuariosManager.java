package com.revich.mobile.tpn06;

import android.content.ContentValues;
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

    public void InsertarBaseDeDatos(Usuario MiUsuario)
    {
        UsuariosSQLiteHelper usuariosSQLiteHelper= new UsuariosSQLiteHelper(ElContexto,"DBAlumnos",null,2);
        SQLiteDatabase db =Abrir(true);
        ContentValues nuevoRegistro= new ContentValues();
        nuevoRegistro.put("nombre",MiUsuario.Nombre);
        nuevoRegistro.put("ciudadesacertadas",MiUsuario.CiudadesAcertadas);
        nuevoRegistro.put("tiempodejuego",MiUsuario.TiempoDeJuego);
        db.insert("Usuarios",null,nuevoRegistro);
        db.close();
    }
}
