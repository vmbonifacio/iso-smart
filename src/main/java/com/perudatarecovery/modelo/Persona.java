package com.perudatarecovery.modelo;

public class Persona {
    
    private Integer id_personaa;
    private String tipo_acci;
    private String nombres_c;
    private String apellido_p;
    private String apellido_m;
    private Integer dni_ce;
    private Integer edad;
    private String genero;
    private String area_trabajo;
    private String puesto_trabajo;
    private String antiguedad_empleo;
    private String turno;
    private String tipo_contrato;
    private String tiempo_experiencia;
    private Integer h_trabajadas_antes_acci;

    public Persona() {
    }

    public Persona(Integer id_personaa, String tipo_acci, String nombres_c, String apellido_p, String apellido_m, Integer dni_ce, Integer edad, String genero, String area_trabajo, String puesto_trabajo, String antiguedad_empleo, String turno, String tipo_contrato, String tiempo_experiencia, Integer h_trabajadas_antes_acci) {
        this.id_personaa = id_personaa;
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

    public Integer getId_personaa() {
        return id_personaa;
    }

    public void setId_personaa(Integer id_personaa) {
        this.id_personaa = id_personaa;
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

    public Integer getDni_ce() {
        return dni_ce;
    }

    public void setDni_ce(Integer dni_ce) {
        this.dni_ce = dni_ce;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
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

    public Integer getH_trabajadas_antes_acci() {
        return h_trabajadas_antes_acci;
    }

    public void setH_trabajadas_antes_acci(Integer h_trabajadas_antes_acci) {
        this.h_trabajadas_antes_acci = h_trabajadas_antes_acci;
    }
    
    
    
}
