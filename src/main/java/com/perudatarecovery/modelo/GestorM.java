package com.perudatarecovery.modelo;

public class GestorM {

    private int id_gestor_iperc;
    private String tipo;
    private String nombre_proceso;
    private int id_padre;
    private String actividad;
    private String rutinaria;
    private String peligros;
    private String riesgos;
    private String tipo_riesgo;
    private String consecuencia;
    private String controles;
    private String legislacion;
    private String nombre_subproceso;
    private int esti_riesgo;
    private int cantidadSubprocesos;

    private int idProcesoPadre; // Nueva propiedad para almacenar el ID del proceso padre

    public GestorM() {
    }

    public GestorM(int id_gestor_iperc, String tipo, String nombre_proceso, int id_padre, String actividad, String rutinaria, String peligros, String riesgos, String tipo_riesgo, String consecuencia, String controles, String legislacion, String nombre_subproceso, int esti_riesgo) {
        this.id_gestor_iperc = id_gestor_iperc;
        this.tipo = tipo;
        this.nombre_proceso = nombre_proceso;
        this.id_padre = id_padre;
        this.actividad = actividad;
        this.rutinaria = rutinaria;
        this.peligros = peligros;
        this.riesgos = riesgos;
        this.tipo_riesgo = tipo_riesgo;
        this.consecuencia = consecuencia;
        this.controles = controles;
        this.legislacion = legislacion;
        this.nombre_subproceso = nombre_subproceso;
        this.esti_riesgo = esti_riesgo;
    }

    public int getId_gestor_iperc() {
        return id_gestor_iperc;
    }

    public void setId_gestor_iperc(int id_gestor_iperc) {
        this.id_gestor_iperc = id_gestor_iperc;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre_proceso() {
        return nombre_proceso;
    }

    public void setNombre_proceso(String nombre_proceso) {
        this.nombre_proceso = nombre_proceso;
    }

    public String getNombre_subproceso() {
        return nombre_subproceso;
    }

    public void setNombre_subproceso(String nombre_subproceso) {
        this.nombre_subproceso = nombre_subproceso;
    }

    public int getId_padre() {
        return id_padre;
    }

    public void setId_padre(int id_padre) {
        this.id_padre = id_padre;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getRutinaria() {
        return rutinaria;
    }

    public void setRutinaria(String rutinaria) {
        this.rutinaria = rutinaria;
    }

    public String getPeligros() {
        return peligros;
    }

    public void setPeligros(String peligros) {
        this.peligros = peligros;
    }

    public String getRiesgos() {
        return riesgos;
    }

    public void setRiesgos(String riesgos) {
        this.riesgos = riesgos;
    }

    public String getTipo_riesgo() {
        return tipo_riesgo;
    }

    public void setTipo_riesgo(String tipo_riesgo) {
        this.tipo_riesgo = tipo_riesgo;
    }

    public String getConsecuencia() {
        return consecuencia;
    }

    public void setConsecuencia(String consecuencia) {
        this.consecuencia = consecuencia;
    }

    public String getControles() {
        return controles;
    }

    public void setControles(String controles) {
        this.controles = controles;
    }

    public String getLegislacion() {
        return legislacion;
    }

    public void setLegislacion(String legislacion) {
        this.legislacion = legislacion;
    }

    public int getIdProcesoPadre() {
        return idProcesoPadre;
    }

    public void setIdProcesoPadre(int idProcesoPadre) {
        this.idProcesoPadre = idProcesoPadre;
    }
    
     public int getEsti_riesgo() {
        return esti_riesgo;
    }

    public void setEsti_riesgo(int esti_riesgo) {
        this.esti_riesgo = esti_riesgo;
    }

    public Object getSubprocesos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setEstimacionRiesgo(int estimacionRiesgo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int getCantidadSubprocesos() {
        return cantidadSubprocesos;
    }

    public void setCantidadSubprocesos(int cantidadSubprocesos) {
        this.cantidadSubprocesos = cantidadSubprocesos;
    }

}
