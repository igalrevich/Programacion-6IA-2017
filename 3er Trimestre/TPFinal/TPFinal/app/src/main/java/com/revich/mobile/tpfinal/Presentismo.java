package com.revich.mobile.tpfinal;

/**
 * Created by 42426410 on 15/11/2017.
 */

public class Presentismo {

    public int Id;
    public int Janij;
    public int idGrupo;
    public int idAmbito;
    public int Fecha;
    public boolean asistio;
    public boolean tarde;

    public int getJanij() {
        return Janij;
    }

    public void setJanij(int janij) {
        Janij = janij;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idgrupo) {
        idGrupo = idgrupo;
    }

    public int getFecha() {
        return Fecha;
    }

    public void setFecha(int fecha) {
        Fecha = fecha;
    }

    public int getIdAmbito() {
        return idAmbito;
    }

    public void setIdAmbito(int IdAmbito) {
        idAmbito= IdAmbito;
    }

    public boolean getAsistio() {
        return asistio;
    }

    public void setAsistio(boolean Asistio) {
        asistio = Asistio;
    }

    public boolean getTarde() {
        return tarde;
    }

    public void setTarde(boolean Tarde) {
        tarde = Tarde;
    }
    @Override
    public String toString() {
        return this.Id+ " " +this.Janij + " " +this.idGrupo+ " " +this.idAmbito+ " " +this.Fecha+ " " +this.asistio+ " " +this.tarde;
    }

    public Presentismo(int Janij, int idGrupo,int idAmbito, int Fecha, boolean asistio,boolean tarde) {
        this(0,Janij,idGrupo,idAmbito,Fecha,asistio,tarde);
    }

    // Constructor
    public Presentismo(int id, int Janij, int idGrupo,int idAmbito, int Fecha, boolean asistio,boolean tarde) {
        this.Id 	= id;
        this.Janij 	= Janij;
        this.Id 	= id;
        this.Janij 	= Janij;
        this.Id 	= id;
        this.Janij 	= Janij;
        this.Id 	= id;
        this.Janij 	= Janij;

    }

    // Constructor
<<<<<<< HEAD
    public Presentismo() {
        this(0,0,0,0,0,false,false);
=======
    public Ambito() {
        this(0,"");
>>>>>>> cbfe8222f5b8da56efb772a4bd593c14b6e588e9
    }


}
