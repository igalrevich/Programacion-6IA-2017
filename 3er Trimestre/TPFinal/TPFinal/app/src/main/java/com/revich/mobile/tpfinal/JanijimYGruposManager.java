package com.revich.mobile.tpfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 42426410 on 30/10/2017.
 */
public class JanijimYGruposManager {
        Context ElContexto;

        public static void CreateTableScript(SQLiteDatabase db){
            // Instrucciones DDL para la creación de la estructura de la Tabla.
            String strSQL = "";
            db.execSQL( "CREATE TABLE \"Janijim\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"nombre\" TEXT, \"apellido\" TEXT, \"dni\" INTEGER);");
            db.execSQL(" CREATE TABLE \"Ambitos\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"nombre\" TEXT);");
            db.execSQL(" CREATE TABLE \"AmbitosxGrupo\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"idAmbito\" INTEGER REFERENCES \"Ambitos\" (\"_id\"), \"idGrupo\" INTEGER REFERENCES \"Cursos\" (\"idCurso\"));");
            db.execSQL(" CREATE TABLE \"Fechas\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"Fecha\" TEXT, \"nombre\" TEXT); ");
            db.execSQL(" CREATE TABLE \"Grupos\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"nombre\" TEXT, \"ano\" INTEGER); ");
            db.execSQL(" CREATE TABLE \"Habilidades\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"nombre\" TEXT, \"esPositiva\" BOOLEAN); ");
            db.execSQL(" CREATE TABLE \"HabilidadesxJanij\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"idFecha\" TEXT REFERENCES \"Fechas\" (\"_id\"), \"idAmbito\" TEXT REFERENCES \"Ambitos\" (\"_id\"), \"idGrupo\" TEXT REFERENCES \"Grupos\" (\"_id\"), \"idJanij\" TEXT REFERENCES \"Janijim\" (\"_id\"), \"idHabilidad\" TEXT REFERENCES \"Habilidades\" (\"_id\"), \"Observaciones\" TEXT);");
            db.execSQL(" CREATE TABLE \"JanijimxGrupo\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"idJanij\" INTEGER REFERENCES \"Janijim\" (\"_id\"), \"idGrupo\" INTEGER REFERENCES \"Grupos\" (\"_id\"));");
            db.execSQL(" CREATE TABLE \"Presentismo\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"Alumno\" INTEGER REFERENCES \"Alumnos\" (\"idAlumno\"), \"Fecha\" INTEGER REFERENCES \"Fechas\" (\"idFecha\"), \"idGrupo\" INTEGER REFERENCES \"Grupos\" (\"_id\"), \"idAmbito\" INTEGER REFERENCES \"Ambitos\" (\"_id\"), \"asistio\" BOOLEAN, \"tarde\" BOOLEAN);");
            db.execSQL(" INSERT INTO \"Ambitos\" ( \"_id\",\"nombre\" ) VALUES ( '1','Sabado' );");
            db.execSQL(" INSERT INTO \"Ambitos\" ( \"_id\",\"nombre\" ) VALUES ( '2','Viernes' );");
            db.execSQL(" INSERT INTO \"Ambitos\" ( \"_id\",\"nombre\" ) VALUES ( '3','Curso de madrijim' );");
            db.execSQL(" INSERT INTO \"Fechas\" ( \"_id\",\"Fecha\",\"nombre\" ) VALUES ( '1','2017/10/25','' );");
            db.execSQL(" INSERT INTO \"Fechas\" ( \"_id\",\"Fecha\",\"nombre\" ) VALUES ( '2','2017/10/26','' );");
            db.execSQL(" INSERT INTO \"Fechas\" ( \"_id\",\"Fecha\",\"nombre\" ) VALUES ( '3','2017/10/27','' );");
            db.execSQL(" INSERT INTO \"Habilidades\" ( \"_id\",\"nombre\",\"esPositiva\" ) VALUES ( '1','Hizo la actividad','true' );");
            db.execSQL(" INSERT INTO \"Habilidades\" ( \"_id\",\"nombre\",\"esPositiva\" ) VALUES ( '2','Molesto a los companeros','false' );");
            db.execSQL(" INSERT INTO \"Habilidades\" ( \"_id\",\"nombre\",\"esPositiva\" ) VALUES ( '3','Respeto a los madrijim','true' );");
            db.execSQL(" INSERT INTO \"Habilidades\" ( \"_id\",\"nombre\",\"esPositiva\" ) VALUES ( '4','Rompio los materiales','false' );");
            db.execSQL(" INSERT INTO \"Grupos\" ( \"_id\",\"nombre\",\"ano\" ) VALUES ( '1','Hamordim','2017' );");
            db.execSQL(" INSERT INTO \"Grupos\" ( \"_id\",\"nombre\",\"ano\" ) VALUES ( '2','Shovavim','2017' );");
            db.execSQL(" INSERT INTO \"Grupos\" ( \"_id\",\"nombre\",\"ano\" ) VALUES ( '3','Shelanu','2017' );");
            db.execSQL(" INSERT INTO \"Janijim\" ( \"_id\",\"nombre\",\"apellido\",\"dni\" ) VALUES ( '1','Igal','Revich','42426410' );");
            db.execSQL(" INSERT INTO \"Janijim\" ( \"_id\",\"nombre\",\"apellido\",\"dni\" ) VALUES ( '2','Flor','Aizen','42420000' );");
            db.execSQL(" INSERT INTO \"Janijim\" ( \"_id\",\"nombre\",\"apellido\",\"dni\" ) VALUES ( '3','Samy','Aizen','44426410' );");
            db.execSQL(" INSERT INTO \"AmbitosxGrupo\" ( \"_id\",\"idAmbito\",\"idGrupo\" ) VALUES ( '1','1','1' );");
            db.execSQL(" INSERT INTO \"AmbitosxGrupo\" ( \"_id\",\"idAmbito\",\"idGrupo\" ) VALUES ( '2','1','2' );");
            db.execSQL(" INSERT INTO \"AmbitosxGrupo\" ( \"_id\",\"idAmbito\",\"idGrupo\" ) VALUES ( '3','1','3' );");
            db.execSQL(" INSERT INTO \"AmbitosxGrupo\" ( \"_id\",\"idAmbito\",\"idGrupo\" ) VALUES ( '4','2','1' );");
            db.execSQL(" INSERT INTO \"AmbitosxGrupo\" ( \"_id\",\"idAmbito\",\"idGrupo\" ) VALUES ( '5','2','2' );");
            db.execSQL(" INSERT INTO \"AmbitosxGrupo\" ( \"_id\",\"idAmbito\",\"idGrupo\" ) VALUES ( '6','3','1' );");
        }

        public JanijimYGruposManager(Context ctx){
            // Almacenar el Context en una variable privada de la clase, para futura utilización.
            this.ElContexto = ctx;
        }

        public void InsertarJanijOGrupo(Janij MiJanij, Grupo MiGrupo, boolean InsertaJanij)
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
        }
        private SQLiteDatabase Abrir(boolean forWriting){
            TPFinalSQLiteHelper tpFinalSQLiteHelper= new TPFinalSQLiteHelper(ElContexto);
            SQLiteDatabase db =tpFinalSQLiteHelper.open(forWriting);
            return db;
        }
        public void ActualizarJanijOGrupo(Janij MiJanij, Grupo MiGrupo, boolean ActualizaJanij)
        {
            //AlumnosSQLiteHelper alumnosSQLiteHelper= new AlumnosSQLiteHelper(ElContexto);
            //SQLiteDatabase db =alumnosSQLiteHelper.open(true);
            SQLiteDatabase db = Abrir(true);
            if(ActualizaJanij)
            {
                db.execSQL("UPDATE Janijim SET nombre=\""+MiJanij.Nombre+"\" , apellido=\""+MiJanij.Apellido +"\" , dni="+String.valueOf(MiJanij.DNI)+"  WHERE _id="+String.valueOf(SelectId(MiJanij,MiGrupo,true)));
            }
            else
            {
                db.execSQL("UPDATE Grupos SET nombre=\""+MiGrupo.Nombre+"\" , ano=" + String.valueOf(MiGrupo.Año)+ " WHERE _id="+String.valueOf(MiGrupo.Id));
            }
            db.close();
        }

        public void EliminarJanijOGrupo( Janij MiJanij, Grupo MiGrupo, boolean EliminaJanij)
        {
            SQLiteDatabase db =Abrir(true);
            if(EliminaJanij)
            {
                db.execSQL("DELETE FROM Janijim WHERE _id="+String.valueOf(MiJanij.Id));
            }
            else
            {
                db.execSQL("DELETE FROM Grupos WHERE _id="+String.valueOf(MiGrupo.Id));
            }
            db.close();
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

    public int SelectId(Janij MiJanij , Grupo MiGrupo ,boolean BuscaIdJanij)
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
            Cursor c= db.rawQuery("SELECT _id FROM Grupos WHERE nombre=\""+MiGrupo.Nombre+"\" AND ano="+String.valueOf(MiGrupo.Año),null);
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

        public ArrayList<Janij> SelectRegistros()
        {
            SQLiteDatabase db =Abrir(true);
            Cursor c= db.rawQuery("SELECT * FROM Janijim", null);
            ArrayList<Janij> ListaJanijim= new ArrayList<>();
            if(c.moveToFirst())
            {
                do
                {
                    Janij MiJanij= new Janij();
                    MiJanij.Id= c.getInt(0);
                    MiJanij.Nombre = c.getString(1);
                    MiJanij.Apellido = c.getString(2);
                    MiJanij.DNI= c.getInt(3);
                    ListaJanijim.add(MiJanij);
                } while (c.moveToNext());
            }
            c.close();
            db.close();
            return ListaJanijim;
        }

        public ArrayList<Grupo> SelectGrupos()
       {
        SQLiteDatabase db =Abrir(true);
        Cursor c= db.rawQuery("SELECT * FROM Grupos", null);
        ArrayList<Grupo> ListaGrupos= new ArrayList<>();
        if(c.moveToFirst())
        {
            do
            {
                Grupo MiGrupo= new Grupo();
                MiGrupo.Id= c.getInt(0);
                MiGrupo.Nombre= c.getString(1);
                MiGrupo.Año= c.getInt(2);
                ListaGrupos.add(MiGrupo);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return ListaGrupos;
       }

       public boolean ValidarJanijimYGrupos(Janij MiJanij, Grupo MiGrupo, boolean ValidarJanij)
       {
           SQLiteDatabase db =Abrir(false);
           int RegistrosObtenidos=0;
           if(ValidarJanij)
           {
               Cursor c= db.rawQuery("SELECT * FROM Janijim WHERE dni="+ String.valueOf(MiJanij.DNI) ,null);
               if(c.moveToFirst())
               {
                   do
                   {
                       RegistrosObtenidos++;
                   }while (c.moveToNext());
               }
               c.close();
           }
           else
           {
               Cursor c= db.rawQuery("SELECT * FROM Grupos WHERE nombre=\""+ MiGrupo.Nombre+  "\" AND ano="+ String.valueOf(MiGrupo.Año) ,null);
               if(c.moveToFirst())
               {
                   do
                   {
                       RegistrosObtenidos++;
                   }while (c.moveToNext());
               }
               c.close();
           }
           db.close();
           if(RegistrosObtenidos==0)
           {
               return true;
           }
           else
           {
               return false;
           }
       }

    public Janij SelectJanijConDni(int dni)
    {
        SQLiteDatabase db =Abrir(true);
        Cursor c= db.rawQuery("SELECT * FROM Janijim WHERE dni="+String.valueOf(dni), null);
        Janij MiJanij= new Janij();
        MiJanij.Id=-1;
        if(c.moveToFirst())
        {
            do
            {
                MiJanij.Id= c.getInt(0);
                MiJanij.Nombre= c.getString(1);
                MiJanij.Apellido= c.getString(2);
                MiJanij.DNI= c.getInt(3);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return MiJanij;
    }

    public boolean InsertarJanijEnGrupo(int IdJanij, int IdGrupo)
    {
        TPFinalSQLiteHelper tpFinalSQLiteHelper= new TPFinalSQLiteHelper(ElContexto,"DBTPFinal",null,2);
        SQLiteDatabase db =Abrir(false);
        Cursor c= db.rawQuery("SELECT * FROM JanijimxGrupo WHERE idJanij="+String.valueOf(IdJanij)+" AND idGrupo="+String.valueOf(IdGrupo), null);
        boolean EstaElJanijEnEseGrupo=false;
        Janij MiJanij= new Janij();
        MiJanij.Id=-1;
        if(c.moveToFirst())
        {
            do
            {
                EstaElJanijEnEseGrupo=true;
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        if(EstaElJanijEnEseGrupo==false)
        {
            db=Abrir(true);
            ContentValues nuevoRegistro= new ContentValues();
            nuevoRegistro.put("idJanij",IdJanij);
            nuevoRegistro.put("idGrupo",IdGrupo);
            db.insert("JanijimxGrupo",null,nuevoRegistro);
            db.close();
            return true;
        }
        else
        {
            return false;
        }
    }

    public void EliminarJanijEnGrupo(int IdJanij, int IdGrupo)
    {
        SQLiteDatabase db =Abrir(false);
        int IdJanijimxGrupo=0;
        Cursor c= db.rawQuery("SELECT _id FROM JanijimxGrupo WHERE idJanij="+String.valueOf(IdJanij)+" AND idGrupo="+String.valueOf(IdGrupo),null);
        if(c.moveToFirst())
        {
            do
            {
                IdJanijimxGrupo=c.getInt(0);
            }while (c.moveToNext());
        }
        c.close();
        db.close();
        db=Abrir(true);
        db.execSQL("DELETE FROM JanijimxGrupo WHERE _id="+String.valueOf(IdJanijimxGrupo));
        db.close();
    }







}
