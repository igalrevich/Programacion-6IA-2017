package com.revich.mobile.tpfinal;

/**
 * Created by 42426410 on 24/11/2017.
 */
public class HabilidadxJanij {
    public int Id;
    public int idFecha;
    public int idAmbito;
    public int idGrupo;
    public int idJanij;
    public int idHabilidad;
    public String Observaciones;

    public int getId() {
        return Id;
    }

    public void setIdFecha(int IdFecha) {
        idFecha = IdFecha;
    }

    public int getIdFecha() {
        return idFecha;
    }

    public void setIdGrupo(int IdGrupo) {
        idGrupo = IdGrupo;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdAmbito(int IdAmbito) {
        idAmbito = IdAmbito;
    }

    public int getIdJanij() {
        return idJanij;
    }

    public void setIdJanij(int IdJanij) {
        idJanij = IdJanij;
    }

    public int getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(int IdHabilidad) {
        idHabilidad = IdHabilidad;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }

    @Override
    public String toString() {
        return this.Id + ". " + this.idFecha + " "+ this.idJanij + " "+ this.idGrupo + " "+ this.idAmbito + " "+ this.idHabilidad+ " "+ this.Observaciones;
    }

    public HabilidadxJanij(int IdFecha, int IdGrupo, int IdJanij, int IdAmbito, int IdHabilidad, String Observaciones) {
        this(0,IdFecha,IdGrupo,IdJanij,IdAmbito,IdHabilidad,Observaciones);
    }

    // Constructor
    public HabilidadxJanij(int id, int IdFecha, int IdGrupo, int IdJanij, int IdAmbito, int IdHabilidad, String observaciones) {
        this.Id 	= id;
        this.idFecha 	= IdFecha;
        this.idGrupo 	= IdGrupo;
        this.idJanij 	= IdJanij;
        this.idAmbito 	= IdAmbito;
        this.idHabilidad 	= IdHabilidad;
        this.Observaciones	= observaciones;
    }

    // Constructor
    public HabilidadxJanij() {
        this(0,0,0,0,0,"");
    }
}
