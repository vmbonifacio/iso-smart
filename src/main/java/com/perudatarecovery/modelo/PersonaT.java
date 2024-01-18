package com.perudatarecovery.modelo;

public class PersonaT {
    private int id_personaa_t;
    private String tipo_acci;
    private String nombres_c;
    private String apellido_p;
    private String apellido_m;
    private Integer dni_ce;
    private Integer edad;
    private String genero;
    private String empresa_p;
    private String area_trabajo;
    private String puesto_trabajo;
    private String antiguedad_empleo;
    private String turno;
    private int h_trabajadas_antes_acci;

    public PersonaT(int id_personaa_t, String tipo_acci, String nombres_c, String apellido_p, String apellido_m, Integer dni_ce, Integer edad, String genero, String empresa_p, String area_trabajo, String puesto_trabajo, String antiguedad_empleo, String turno, int h_trabajadas_antes_acci) {
        this.id_personaa_t = id_personaa_t;
        this.tipo_acci = tipo_acci;
        this.nombres_c = nombres_c;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.dni_ce = dni_ce;
        this.edad = edad;
        this.genero = genero;
        this.empresa_p = empresa_p;
        this.area_trabajo = area_trabajo;
        this.puesto_trabajo = puesto_trabajo;
        this.antiguedad_empleo = antiguedad_empleo;
        this.turno = turno;
        this.h_trabajadas_antes_acci = h_trabajadas_antes_acci;
    }
    
    public PersonaT(){
        
    }

    public PersonaT(int id, String tipoAcci, String nombresC) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId_personaa_t() {
        return id_personaa_t;
    }

    public void setId_personaa_t(int id_personaa_t) {
        this.id_personaa_t = id_personaa_t;
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

    public String getEmpresa_p() {
        return empresa_p;
    }

    public void setEmpresa_p(String empresa_p) {
        this.empresa_p = empresa_p;
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

    public int getH_trabajadas_antes_acci() {
        return h_trabajadas_antes_acci;
    }

    public void setH_trabajadas_antes_acci(int h_trabajadas_antes_acci) {
        this.h_trabajadas_antes_acci = h_trabajadas_antes_acci;
    }
    
    
    
}
