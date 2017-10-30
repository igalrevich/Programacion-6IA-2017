package com.revich.mobile.tpfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by 42426410 on 30/10/2017.
 */
public class JanijimYGruposManager {
        Context ElContexto;

        public static String CreateTableScript(){
            // Instrucciones DDL para la creación de la estructura de la Tabla.
            String strSQL = "";
            strSQL = "CREATE TABLE \"Ambitos\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"nombre\" TEXT);";
            strSQL += "	CREATE TABLE \"AmbitosxGrupo\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"idAmbito\" INTEGER REFERENCES \"Ambitos\" (\"_id\"), \"idGrupo\" INTEGER REFERENCES \"Cursos\" (\"idCurso\"));";
            strSQL += "	CREATE TABLE \"Fechas\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"Fecha\" TEXT, \"nombre\" TEXT); ";
            strSQL += "	CREATE TABLE \"Grupos\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"nombre\" TEXT, \"ano\" INTEGER); ";
            strSQL += "	CREATE TABLE \"Habilidades\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"nombre\" TEXT, \"esPositiva\" BOOLEAN); ";
            strSQL += " CREATE TABLE \"HabilidadesxJanij\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"idFecha\" TEXT REFERENCES \"Fechas\" (\"_id\"), \"idAmbito\" TEXT REFERENCES \"Ambitos\" (\"_id\"), \"idGrupo\" TEXT REFERENCES \"Grupos\" (\"_id\"), \"idJanij\" TEXT REFERENCES \"Janijim\" (\"_id\"), \"idHabilidad\" TEXT REFERENCES \"Habilidades\" (\"_id\"), \"Observaciones\" TEXT);";
            strSQL += " CREATE TABLE \"Janijim\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"nombre\" TEXT, \"apellido\" TEXT, \"dni\" INTEGER);";
            strSQL += " CREATE TABLE \"JanijimxGrupo\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"idJanij\" INTEGER REFERENCES \"Janijim\" (\"_id\"), \"idGrupo\" INTEGER REFERENCES \"Grupos\" (\"_id\"));";
            strSQL += " CREATE TABLE \"Presentismo\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"Alumno\" INTEGER REFERENCES \"Alumnos\" (\"idAlumno\"), \"Fecha\" INTEGER REFERENCES \"Fechas\" (\"idFecha\"), \"idGrupo\" INTEGER REFERENCES \"Grupos\" (\"_id\"), \"idAmbito\" INTEGER REFERENCES \"Ambitos\" (\"_id\"), \"asistio\" BOOLEAN, \"tarde\" BOOLEAN);";
            strSQL += " INSERT INTO \"Ambitos\" ( \"_id\",\"nombre\" ) VALUES ( '1','Sabado' );";
            strSQL += " INSERT INTO \"Ambitos\" ( \"_id\",\"nombre\" ) VALUES ( '2','Viernes' );";
            strSQL += " INSERT INTO \"Ambitos\" ( \"_id\",\"nombre\" ) VALUES ( '3','Curso de madrijim' );";
            strSQL += " INSERT INTO \"Fechas\" ( \"_id\",\"Fecha\",\"nombre\" ) VALUES ( '1','2017/10/25','' );";
            strSQL += " INSERT INTO \"Fechas\" ( \"_id\",\"Fecha\",\"nombre\" ) VALUES ( '2','2017/10/26','' );";
            strSQL += " INSERT INTO \"Fechas\" ( \"_id\",\"Fecha\",\"nombre\" ) VALUES ( '3','2017/10/27','' );";
            strSQL += " INSERT INTO \"Habilidades\" ( \"_id\",\"nombre\",\"esPositiva\" ) VALUES ( '1','Hizo la actividad','true' );";
            strSQL += " INSERT INTO \"Habilidades\" ( \"_id\",\"nombre\",\"esPositiva\" ) VALUES ( '2','Molesto a los companeros','false' );";
            strSQL += " INSERT INTO \"Habilidades\" ( \"_id\",\"nombre\",\"esPositiva\" ) VALUES ( '3','Respeto a los madrijim','true' );";
            strSQL += " INSERT INTO \"Habilidades\" ( \"_id\",\"nombre\",\"esPositiva\" ) VALUES ( '4','Rompio los materiales','false' );";
            return strSQL;
        }

        public JanijimYGruposManager(Context ctx){
            // Almacenar el Context en una variable privada de la clase, para futura utilización.
            this.ElContexto = ctx;
        }

        public int InsertarJanijOGrupo(Janij MiJanij, Grupo MiGrupo, boolean InsertaJanij)
        {
            TPFinalSQLiteHelper tpFinalSQLiteHelper= new TPFinalSQLiteHelper(ElContexto,"DBTPFinal",null,2);
            SQLiteDatabase db =tpFinalSQLiteHelper.open(true);
            ContentValues nuevoRegistro= new ContentValues();
            if(InsertaJanij)
            {
                nuevoRegistro.put("nombre",MiJanij.Nombre);
                nuevoRegistro.put("apellido",MiJanij.Apellido);
                nuevoRegistro.put("dni",MiJanij.DNI);
                db.insert("Janijim",null,nuevoRegistro);
            }
            else
            {
                nuevoRegistro.put("nombre",MiGrupo.Nombre);
                nuevoRegistro.put("ano",MiGrupo.Año);
                db.insert("Grupos",null,nuevoRegistro);
            }

            db.close();
            int IdIngresado= SelectConId(0,true);
            return  IdIngresado;

        }
        private SQLiteDatabase Abrir(boolean forWriting){
            TPFinalSQLiteHelper tpFinalSQLiteHelper= new TPFinalSQLiteHelper(ElContexto);
            SQLiteDatabase db =tpFinalSQLiteHelper.open(forWriting);
            return db;
        }
        public int ActualizarJanijOGrupo(Janij MiJanij, Grupo MiGrupo, boolean ActualizaJanij)
        {
            //AlumnosSQLiteHelper alumnosSQLiteHelper= new AlumnosSQLiteHelper(ElContexto);
            //SQLiteDatabase db =alumnosSQLiteHelper.open(true);
            SQLiteDatabase db = Abrir(true);
            if(ActualizaJanij)
            {
                db.execSQL("UPDATE Janijim SET nombre=\""+MiJanij.Nombre+"\" WHERE id="+MiAlumno.Id);
            }
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

    private int SelectId(Janij MiJanij , Grupo MiGrupo ,boolean BuscaIdJanij)
    {
        SQLiteDatabase db =Abrir(false);
        int IdIngresado=0;
        if(BuscaIdJanij)
        {
            Cursor c= db.rawQuery("SELECT _id FROM Janijim WHERE dni="+MiJanij.DNI,null);
            if(c.moveToFirst())
            {
                do
                {
                    IdIngresado=c.getInt(0);
                }while (c.moveToNext());
            }
            c.close();

        }
        else
        {
            Cursor c= db.rawQuery("SELECT _id FROM Grupos WHERE nombre="+MiGrupo.Nombre+" AND ano="+String.valueOf(MiGrupo.Año),null);
            if(c.moveToFirst())
            {
                do
                {
                    IdIngresado=c.getInt(0);
                }while (c.moveToNext());
            }
            c.close();
        }
        db.close();
        return IdIngresado;
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
