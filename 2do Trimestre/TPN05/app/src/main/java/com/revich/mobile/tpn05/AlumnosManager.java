package com.revich.mobile.tpn05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 42426410 on 19/6/2017.
 */
public class AlumnosManager {
    Context ElContexto;

    public static String CreateTableScript(){
        // Instrucciones DDL para la creación de la estructura de la Tabla.
        String strSQL = "";
        strSQL = "CREATE TABLE Alumnos (";
        strSQL += "	id 	INTEGER PRIMARY KEY AUTOINCREMENT, ";
        strSQL += "	Nombre TEXT, ";
        strSQL += "	Casado TEXT, ";
        strSQL += "	FechaNacimiento TEXT NULL ";
        strSQL += ");";
        return strSQL;
    }

    public AlumnosManager(Context ctx){
        // Almacenar el Context en una variable privada de la clase, para futura utilización.
        this.ElContexto = ctx;
    }

    public int InsertarBaseDeDatos(Alumnos MiAlumno)
    {
        AlumnosSQLiteHelper alumnosSQLiteHelper= new AlumnosSQLiteHelper(ElContexto,"DBAlumnos",null,2);
        SQLiteDatabase db =alumnosSQLiteHelper.open(true);
        ContentValues nuevoRegistro= new ContentValues();
        nuevoRegistro.put("Nombre",MiAlumno.Nombre);
        nuevoRegistro.put("Casado",MiAlumno.Casado);
        /*Random rand= new Random();
        int TrueOFalse= rand.nextInt(2);
        if(TrueOFalse==0)
        {
            nuevoRegistro.put("Casado","false");
        }
        else
        {
            nuevoRegistro.put("Casado","true");
        }*/
        nuevoRegistro.put("FechaNacimiento",MiAlumno.FechaNacimiento);
        db.insert("Alumnos",null,nuevoRegistro);
        db.close();
        int IdIngresado= SelectConId(0,true);
        return  IdIngresado;

    }
    private SQLiteDatabase Abrir(boolean forWriting){
        AlumnosSQLiteHelper alumnosSQLiteHelper= new AlumnosSQLiteHelper(ElContexto);
        SQLiteDatabase db =alumnosSQLiteHelper.open(forWriting);
        return db;
    }
    public int ActualizarBaseDeDatos(Alumnos MiAlumno)
    {
        //AlumnosSQLiteHelper alumnosSQLiteHelper= new AlumnosSQLiteHelper(ElContexto);
        //SQLiteDatabase db =alumnosSQLiteHelper.open(true);
        SQLiteDatabase db = Abrir(true);
        db.execSQL("UPDATE Alumnos SET Nombre=\""+MiAlumno.Nombre+"\" WHERE id="+MiAlumno.Id);
        db.close();
        int RegistrosAfectados=SelectConId(MiAlumno.Id,false);
        return  RegistrosAfectados;
    }

    public int EliminarBaseDeDatos( int Id)
    {
        int RegistrosAfectados=SelectConId(Id,false);
        SQLiteDatabase db =Abrir(true);
        db.execSQL("DELETE FROM Alumnos WHERE id="+Id);
        db.close();
        return RegistrosAfectados;
    }

    private int SelectConId(int Id , boolean Insert)
    {
        SQLiteDatabase db =Abrir(false);
        if(Insert==false)
        {
            Cursor c= db.rawQuery("SELECT * FROM Alumnos WHERE id="+Id, null);
            int ContRegistrosAfectados=0;
            if(c.moveToFirst())
            {
                do
                {
                    ContRegistrosAfectados++;
                }while (c.moveToNext());
            }
            c.close();
            db.close();
            return  ContRegistrosAfectados;
        }
        else
        {
            Cursor c= db.rawQuery("SELECT MAX(id) FROM Alumnos",null);
            int IdIngresado=0;
            if(c.moveToFirst())
            {
                do
                {
                    IdIngresado=c.getInt(0);
                }while (c.moveToNext());
            }
            c.close();
            db.close();
            return IdIngresado;
        }
    }

    public ArrayList<Alumnos> SelectRegistros()
    {
        SQLiteDatabase db =Abrir(true);
        Cursor c= db.rawQuery("SELECT * FROM Alumnos", null);
        ArrayList<Alumnos> ListaAlumnos= new ArrayList<>();
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
        return ListaAlumnos;
    }
}
