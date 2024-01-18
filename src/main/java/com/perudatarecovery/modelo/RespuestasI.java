package com.perudatarecovery.modelo;

public class RespuestasI {
    private int id_respuesta_indicador;
    private String fuente;
    private String si;
    private String no;
    private String observaciones;

    public RespuestasI() {
    }

    public RespuestasI(int id_respuesta_indicador, String fuente, String si, String no, String observaciones) {
        this.id_respuesta_indicador = id_respuesta_indicador;
        this.fuente = fuente;
        this.si = si;
        this.no = no;
        this.observaciones = observaciones;
    }

    public int getId_respuesta_indicador() {
        return id_respuesta_indicador;
    }

    public void setId_respuesta_indicador(int id_respuesta_indicador) {
        this.id_respuesta_indicador = id_respuesta_indicador;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

 

      
}