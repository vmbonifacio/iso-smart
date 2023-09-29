package com.perudatarecovery.modelo;


public class Gravedad {
    private int id_gravedad;
    private String nombres;
    private String ap_p;
    private String ap_m;
    private int edad;
    private String area;
    private String puesto;
    private String gravedad;
    private String gravedad_I;
    private int d_descanzo; 
    private String c_afectado;

    public Gravedad(int id_gravedad, String nombres, String ap_p, String ap_m, int edad, String area, String puesto, String gravedad, String gravedad_I, int d_descanzo, String c_afectado) {
        this.id_gravedad = id_gravedad;
        this.nombres = nombres;
        this.ap_p = ap_p;
        this.ap_m = ap_m;
        this.edad = edad;
        this.area = area;
        this.puesto = puesto;
        this.gravedad = gravedad;
        this.gravedad_I = gravedad_I;
        this.d_descanzo = d_descanzo;
        this.c_afectado = c_afectado;
    }

    public int getId_gravedad() {
        return id_gravedad;
    }

    public void setId_gravedad(int id_gravedad) {
        this.id_gravedad = id_gravedad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getAp_p() {
        return ap_p;
    }

    public void setAp_p(String ap_p) {
        this.ap_p = ap_p;
    }

    public String getAp_m() {
        return ap_m;
    }

    public void setAp_m(String ap_m) {
        this.ap_m = ap_m;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getGravedad() {
        return gravedad;
    }

    public void setGravedad(String gravedad) {
        this.gravedad = gravedad;
    }

    public String getGravedad_I() {
        return gravedad_I;
    }

    public void setGravedad_I(String gravedad_I) {
        this.gravedad_I = gravedad_I;
    }

    public int getD_descanzo() {
        return d_descanzo;
    }

    public void setD_descanzo(int d_descanzo) {
        this.d_descanzo = d_descanzo;
    }

    public String getC_afectado() {
        return c_afectado;
    }

    public void setC_afectado(String c_afectado) {
        this.c_afectado = c_afectado;
    }
    
    
}
