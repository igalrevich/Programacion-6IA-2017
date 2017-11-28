package com.revich.mobile.tpfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
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
            db.execSQL(" CREATE TABLE \"AmbitosxGrupo\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"idAmbito\" INTEGER REFERENCES \"Ambitos\" (\"_id\"), \"idGrupo\" INTEGER REFERENCES \"Grupos\" (\"_id\"));");
            db.execSQL(" CREATE TABLE \"Fechas\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"Fecha\" TEXT, \"nombre\" TEXT); ");
            db.execSQL(" CREATE TABLE \"Grupos\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"nombre\" TEXT, \"ano\" INTEGER); ");
            db.execSQL(" CREATE TABLE \"Habilidades\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"nombre\" TEXT, \"esPositiva\" BOOLEAN); ");
            db.execSQL(" CREATE TABLE \"HabilidadesxJanij\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"idFecha\" TEXT REFERENCES \"Fechas\" (\"_id\"), \"idAmbito\" TEXT REFERENCES \"Ambitos\" (\"_id\"), \"idGrupo\" TEXT REFERENCES \"Grupos\" (\"_id\"), \"idJanij\" TEXT REFERENCES \"Janijim\" (\"_id\"), \"idHabilidad\" TEXT REFERENCES \"Habilidades\" (\"_id\"), \"Observaciones\" TEXT);");
            db.execSQL(" CREATE TABLE \"JanijimxGrupo\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"idJanij\" INTEGER REFERENCES \"Janijim\" (\"_id\"), \"idGrupo\" INTEGER REFERENCES \"Grupos\" (\"_id\"));");
            db.execSQL(" CREATE TABLE \"Presentismo\" (\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT, \"Janij\" INTEGER REFERENCES \"Janijim\" (\"_id\"), \"Fecha\" INTEGER REFERENCES \"Fechas\" (\"idFecha\"), \"idGrupo\" INTEGER REFERENCES \"Grupos\" (\"_id\"), \"idAmbito\" INTEGER REFERENCES \"Ambitos\" (\"_id\"), \"asistio\" BOOLEAN, \"tarde\" BOOLEAN);");
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
            SQLiteDatabase db =Abrir(false);
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

    public ArrayList<Fecha> SelectFechas()
    {
        SQLiteDatabase db =Abrir(false);
        Cursor c= db.rawQuery("SELECT * FROM Fechas", null);
        ArrayList<Fecha> ListaFechas= new ArrayList<>();
        if(c.moveToFirst())
        {
            do
            {
                Fecha MiFecha= new Fecha();
                MiFecha.Id= c.getInt(0);
                MiFecha.Fecha= c.getString(1);
                MiFecha.Nombre = c.getString(2);
                ListaFechas.add(MiFecha);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return ListaFechas;
    }

    public ArrayList<Ambito> SelectAmbitos(int IdGrupo)
    {
        SQLiteDatabase db =Abrir(false);
        Cursor c= db.rawQuery("SELECT idAmbito FROM AmbitosxGrupo WHERE idGrupo="+String.valueOf(IdGrupo), null);
        ArrayList<Ambito> ListaAmbitos= new ArrayList<>();
        if(c.moveToFirst())
        {
            do
            {   Ambito MiAmbito= new Ambito();
                int idAmbito= c.getInt(0);
                SQLiteDatabase db2 =Abrir(false);
                Cursor c2= db.rawQuery("SELECT * FROM Ambitos WHERE _id="+String.valueOf(idAmbito), null);
                if(c2.moveToFirst())
                {
                    do
                    {
                        MiAmbito.Id= c2.getInt(0);
                        MiAmbito.Nombre= c2.getString(1);
                    } while (c2.moveToNext());
                }
                c2.close();
                db2.close();
                ListaAmbitos.add(MiAmbito);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return ListaAmbitos;
    }

    public ArrayList<Janij> SelectJanijimDeUnGrupo(int IdGrupo)
    {
        SQLiteDatabase db =Abrir(false);
        Cursor c= db.rawQuery("SELECT idJanij FROM JanijimxGrupo WHERE idGrupo="+String.valueOf(IdGrupo), null);
        ArrayList<Janij> ListaJanijim= new ArrayList<>();
        if(c.moveToFirst())
        {
            do
            {   Janij MiJanij= new Janij();
                int idJanij= c.getInt(0);
                SQLiteDatabase db2 =Abrir(false);
                Cursor c2= db.rawQuery("SELECT * FROM Janijim WHERE _id="+String.valueOf(idJanij), null);
                if(c2.moveToFirst())
                {
                    do
                    {
                        MiJanij.Id= c2.getInt(0);
                        MiJanij.Nombre = c2.getString(1);
                        MiJanij.Apellido = c2.getString(2);
                        MiJanij.DNI= c2.getInt(3);
                    } while (c2.moveToNext());
                }
                c2.close();
                db2.close();
                ListaJanijim.add(MiJanij);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return ListaJanijim;
    }

    public void InsertarPresentismo(Presentismo MiPresentismo)
    {
        SQLiteDatabase db=Abrir(false);
        Cursor c= db.rawQuery("SELECT _id FROM Presentismo WHERE idGrupo="+String.valueOf(MiPresentismo.idGrupo)+" AND idAmbito="+String.valueOf(MiPresentismo.idAmbito)+" AND Fecha=\""+MiPresentismo.Fecha+"\" AND Janij="+String.valueOf(MiPresentismo.Janij), null);
        int IdPresentismo=0;
        if(c.moveToFirst())
        {
            do
            {
                IdPresentismo=c.getInt(0);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        db=Abrir(true);
        if(IdPresentismo==0)
        {
            ContentValues nuevoRegistro= new ContentValues();
            nuevoRegistro.put("Janij",MiPresentismo.Janij);
            nuevoRegistro.put("idGrupo",MiPresentismo.idGrupo);
            nuevoRegistro.put("idAmbito",MiPresentismo.idAmbito);
            nuevoRegistro.put("Fecha",MiPresentismo.Fecha);
            nuevoRegistro.put("asistio",MiPresentismo.asistio);
            nuevoRegistro.put("tarde",MiPresentismo.tarde);
            db.insert("Presentismo",null,nuevoRegistro);
        }
        else
        {
            ContentValues nuevoRegistro= new ContentValues();
            nuevoRegistro.put("asistio",MiPresentismo.asistio);
            nuevoRegistro.put("tarde",MiPresentismo.tarde);
            String where = "_id=?";
            String[] whereArgs = new String[] {String.valueOf(IdPresentismo)};
            db.update("Presentismo",nuevoRegistro,where,whereArgs);
        }

        db.close();
    }

    public int SelectIdAmbitoOFecha(String NombreAmbito, String Fecha, boolean BuscaIdAmbito)
    {
        SQLiteDatabase db =Abrir(false);
        Cursor c;
        if(BuscaIdAmbito)
        {
            c= db.rawQuery("SELECT _id FROM Ambitos WHERE nombre=\""+NombreAmbito+"\"", null);
        }
        else
        {
            c= db.rawQuery("SELECT _id FROM Fechas WHERE Fecha=\""+Fecha+"\"", null);
        }
        int IdARetornar=0;
        if(c.moveToFirst())
        {
            do
            {
               IdARetornar=c.getInt(0) ;
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return IdARetornar;
    }

    public ArrayList<Habilidades> SelectHabilidades()
    {
        SQLiteDatabase db =Abrir(false);
        Cursor c= db.rawQuery("SELECT * FROM Habilidades", null);
        ArrayList<Habilidades> ListaHabilidades= new ArrayList<>();
        if(c.moveToFirst())
        {
            do
            {   Habilidades MiHabilidad= new Habilidades();
                MiHabilidad.Id= c.getInt(0);
                MiHabilidad.Nombre = c.getString(1);
                MiHabilidad.esPositiva = Boolean.parseBoolean(c.getString(2));
                ListaHabilidades.add(MiHabilidad);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return ListaHabilidades;
    }

    public int SelectIdHabilidadxJanij(HabilidadxJanij MiHabilidadxJanij)
    {
        SQLiteDatabase db =Abrir(false);
        Cursor c= db.rawQuery("SELECT _id FROM HabilidadesxJanij WHERE idJanij="+String.valueOf(MiHabilidadxJanij.idJanij)+" AND idGrupo="+String.valueOf(MiHabilidadxJanij.idGrupo)+" AND idAmbito="+String.valueOf(MiHabilidadxJanij.idAmbito)+" AND idFecha="+String.valueOf(MiHabilidadxJanij.idFecha)+" AND idHabilidad="+String.valueOf(MiHabilidadxJanij.idHabilidad), null);
        int IdDevuelto=0;
        if(c.moveToFirst())
        {
            do
            {   IdDevuelto=c.getInt(0);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return IdDevuelto;
    }

    public void InsertarOModificarHabilidadxJanij(HabilidadxJanij MiHabilidadxJanij)
    {   SQLiteDatabase db =Abrir(true);
        int IdDevuelto= SelectIdHabilidadxJanij(MiHabilidadxJanij);
        if(IdDevuelto==0)
        {
            ContentValues nuevoRegistro= new ContentValues();
            nuevoRegistro.put("idJanij",MiHabilidadxJanij.idJanij);
            nuevoRegistro.put("idGrupo",MiHabilidadxJanij.idGrupo);
            nuevoRegistro.put("idAmbito",MiHabilidadxJanij.idAmbito);
            nuevoRegistro.put("idFecha",MiHabilidadxJanij.idFecha);
            nuevoRegistro.put("idHabilidad",MiHabilidadxJanij.idHabilidad);
            nuevoRegistro.put("Observaciones",MiHabilidadxJanij.Observaciones);
            db.insert("HabilidadesxJanij",null,nuevoRegistro);
        }
        else
        {
            db.execSQL("UPDATE HabilidadesxJanij SET idHabilidad="+String.valueOf(MiHabilidadxJanij.idHabilidad)+", Observaciones=\""+MiHabilidadxJanij.Observaciones+"\" WHERE _id="+String.valueOf(IdDevuelto));
        }
        db.close();
    }

    public int SelectIdHabilidad(String NombreHabilidad)
    {
        SQLiteDatabase db=Abrir(false);
        Cursor c= db.rawQuery("SELECT _id FROM Habilidades WHERE nombre=\""+NombreHabilidad+"\"", null);
        int IdHabilidad=0;
        if(c.moveToFirst())
        {
            do
            {
                IdHabilidad=c.getInt(0);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return IdHabilidad;
    }

    public ArrayList<Presentismo> SelectPresentismoDeUnJanij(int IdJanij)
    {
        SQLiteDatabase db =Abrir(false);
        Cursor c= db.rawQuery("SELECT * FROM Presentismo WHERE Janij="+String.valueOf(IdJanij), null);
        ArrayList<Presentismo> ListaPresentismo= new ArrayList<>();
        if(c.moveToFirst())
        {
            do
            {   Presentismo MiPresentismo= new Presentismo();
                MiPresentismo.Id= c.getInt(0);
                MiPresentismo.Janij = c.getInt(1);
                MiPresentismo.Fecha= c.getInt(2);
                MiPresentismo.idGrupo = c.getInt(3);
                MiPresentismo.idAmbito= c.getInt(4);
                if(c.getInt(5)==1)
                {
                    MiPresentismo.asistio=true;
                }
                else
                {
                    MiPresentismo.asistio=false;
                }
                if(c.getInt(6)==1)
                {
                    MiPresentismo.tarde=true;
                }
                else
                {
                    MiPresentismo.tarde=false;
                }
                ListaPresentismo.add(MiPresentismo);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return ListaPresentismo;
    }

    public ArrayList<HabilidadxJanij> SelectHabilidadesDeUnJanij(int IdJanij)
    {
        SQLiteDatabase db =Abrir(false);
        Cursor c= db.rawQuery("SELECT * FROM HabilidadesxJanij WHERE idJanij="+String.valueOf(IdJanij), null);
        ArrayList<HabilidadxJanij> ListaHabilidadesxJanij= new ArrayList<>();
        if(c.moveToFirst())
        {
            do
            {   HabilidadxJanij MiHabilidadxJanij= new HabilidadxJanij();
                MiHabilidadxJanij.Id= c.getInt(0);
                MiHabilidadxJanij.idFecha = c.getInt(1);
                MiHabilidadxJanij.idAmbito= c.getInt(2);
                MiHabilidadxJanij.idGrupo = c.getInt(3);
                MiHabilidadxJanij.idJanij= c.getInt(4);
                MiHabilidadxJanij.idHabilidad= c.getInt(5);
                MiHabilidadxJanij.Observaciones = c.getString(6);
                ListaHabilidadesxJanij.add(MiHabilidadxJanij);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return ListaHabilidadesxJanij;
    }

    public String SelectNombreFechaOAmbitoById(int id,boolean BuscaNombreFecha)
    {
        SQLiteDatabase db =Abrir(false);
        Cursor c;
        if(BuscaNombreFecha)
        {
            c= db.rawQuery("SELECT Fecha FROM Fechas WHERE _id="+String.valueOf(id), null);
        }
        else
        {
            c= db.rawQuery("SELECT nombre FROM Ambitos WHERE _id="+String.valueOf(id), null);
        }
        String NombreARetornar="";
        if(c.moveToFirst())
        {
            do
            {   NombreARetornar=c.getString(0);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return NombreARetornar;
    }
    public Habilidades SelectHabilidadById(int idHabilidad)
    {
        SQLiteDatabase db =Abrir(false);
        Cursor c= db.rawQuery("SELECT * FROM Habilidades WHERE _id="+String.valueOf(idHabilidad), null);
        Habilidades MiHabilidad=new Habilidades();
        if(c.moveToFirst())
        {
            do
            {
                MiHabilidad.Id=c.getInt(0);
                MiHabilidad.Nombre=c.getString(1);
                MiHabilidad.esPositiva=Boolean.parseBoolean(c.getString(2));
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return MiHabilidad;
    }

    public void EliminarHabilidadxJanij( int id)
    {
        SQLiteDatabase db =Abrir(true);
        db.execSQL("DELETE FROM HabilidadesxJanij WHERE _id="+String.valueOf(id));
        db.close();
    }

    public ArrayList<HabilidadxJanij> SelectHabilidadesDeUnJanijCompleta(HabilidadxJanij MiHabilidadxJanij)
    {
        SQLiteDatabase db =Abrir(false);
        Cursor c= db.rawQuery("SELECT * FROM HabilidadesxJanij WHERE idJanij="+String.valueOf(MiHabilidadxJanij.idJanij)+ " AND idGrupo="+String.valueOf(MiHabilidadxJanij.idGrupo)+" AND idAmbito="+String.valueOf(MiHabilidadxJanij.idAmbito)+" AND idFecha="+String.valueOf(MiHabilidadxJanij.idFecha), null);
        ArrayList<HabilidadxJanij> ListaHabilidadesxJanij= new ArrayList<>();
        if(c.moveToFirst())
        {
            do
            {   MiHabilidadxJanij= new HabilidadxJanij();
                MiHabilidadxJanij.Id= c.getInt(0);
                MiHabilidadxJanij.idFecha = c.getInt(1);
                MiHabilidadxJanij.idAmbito= c.getInt(2);
                MiHabilidadxJanij.idGrupo = c.getInt(3);
                MiHabilidadxJanij.idJanij= c.getInt(4);
                MiHabilidadxJanij.idHabilidad= c.getInt(5);
                MiHabilidadxJanij.Observaciones = c.getString(6);
                ListaHabilidadesxJanij.add(MiHabilidadxJanij);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return ListaHabilidadesxJanij;
    }













}
