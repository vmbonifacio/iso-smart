package com.perudatarecovery.modelo;

public class PersonaC {
    private int id_personaa_c;
    private String tipo_acci;
    private String nombres_c;
    private String apellido_p;
    private String apellido_m;
    private int dni_ce;
    private int edad;
    private String genero;
    private String area_trabajo;
    private String puesto_trabajo;
    private String antiguedad_empleo;
    private String turno;
    private String tipo_contrato;
    private String tiempo_experiencia;
    private int h_trabajadas_antes_acci;

    public PersonaC(int id_personaa_c, String tipo_acci, String nombres_c, String apellido_p, String apellido_m, int dni_ce, int edad, String genero, String area_trabajo, String puesto_trabajo, String antiguedad_empleo, String turno, String tipo_contrato, String tiempo_experiencia, int h_trabajadas_antes_acci) {
        this.id_personaa_c = id_personaa_c;
        this.tipo_acci = tipo_acci;
        this.nombres_c = nombres_c;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.dni_ce = dni_ce;
        this.edad = edad;
        this.genero = genero;
        this.area_trabajo = area_trabajo;
        this.puesto_trabajo = puesto_trabajo;
        this.antiguedad_empleo = antiguedad_empleo;
        this.turno = turno;
        this.tipo_contrato = tipo_contrato;
        this.tiempo_experiencia = tiempo_experiencia;
        this.h_trabajadas_antes_acci = h_trabajadas_antes_acci;
    }

    public PersonaC() {
    }

    public int getId_personaa_c() {
        return id_personaa_c;
    }

    public void setId_personaa_c(int id_personaa_c) {
        this.id_personaa_c = id_personaa_c;
    }

    public String getTipo_acci() {
        return tipo_acci;
    }

    public void setTipo_acci(String tipo_acci) {
        this.tipo_acci = tipo_acci;
    }

    public String getNombres_c() {
        return nombres_c;
    }

    public void setNombres_c(String nombres_c) {
        this.nombres_c = nombres_c;
    }

    public String getApellido_p() {
        return apellido_p;
    }

    public void setApellido_p(String apellido_p) {
        this.apellido_p = apellido_p;
    }

    public String getApellido_m() {
        return apellido_m;
    }

    public void setApellido_m(String apellido_m) {
        this.apellido_m = apellido_m;
    }

    public int getDni_ce() {
        return dni_ce;
    }

    public void setDni_ce(int dni_ce) {
        this.dni_ce = dni_ce;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getArea_trabajo() {
        return area_trabajo;
    }

    public void setArea_trabajo(String area_trabajo) {
        this.area_trabajo = area_trabajo;
    }

    public String getPuesto_trabajo() {
        return puesto_trabajo;
    }

    public void setPuesto_trabajo(String puesto_trabajo) {
        this.puesto_trabajo = puesto_trabajo;
    }

    public String getAntiguedad_empleo() {
        return antiguedad_empleo;
    }

    public void setAntiguedad_empleo(String antiguedad_empleo) {
        this.antiguedad_empleo = antiguedad_empleo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(String tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

    public String getTiempo_experiencia() {
        return tiempo_experiencia;
    }

    public void setTiempo_experiencia(String tiempo_experiencia) {
        this.tiempo_experiencia = tiempo_experiencia;
    }

    public int getH_trabajadas_antes_acci() {
        return h_trabajadas_antes_acci;
    }

    public void setH_trabajadas_antes_acci(int h_trabajadas_antes_acci) {
        this.h_trabajadas_antes_acci = h_trabajadas_antes_acci;
    }

}
