package com.perudatarecovery.modelo;


public class PersonaTI {
    private int id_personai_t;
    private String tipo_inci;
    private String nombres_c;
    private String apellido_p;
    private String apellido_m;
    private int dni_ce;
    private int edad;
    private String genero;
    private String empresa_p;
    private String area_trabajo;
    private String puesto_trabajo;
    private String antiguedad_empleo;
    private String turno;
    private int h_trabajadas_antes_inci;

    public PersonaTI(int id_personai_t, String tipo_inci, String nombres_c, String apellido_p, String apellido_m, int dni_ce, int edad, String genero, String empresa_p, String area_trabajo, String puesto_trabajo, String antiguedad_empleo, String turno, int h_trabajadas_antes_inci) {
        this.id_personai_t = id_personai_t;
        this.tipo_inci = tipo_inci;
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
        this.h_trabajadas_antes_inci = h_trabajadas_antes_inci;
    }
    
    public PersonaTI(){
   }

    public int getId_personai_t() {
        return id_personai_t;
    }

    public void setId_personai_t(int id_personai_t) {
        this.id_personai_t = id_personai_t;
    }

    public String getTipo_inci() {
        return tipo_inci;
    }

    public void setTipo_inci(String tipo_inci) {
        this.tipo_inci = tipo_inci;
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

    public int getH_trabajadas_antes_inci() {
        return h_trabajadas_antes_inci;
    }

    public void setH_trabajadas_antes_inci(int h_trabajadas_antes_inci) {
        this.h_trabajadas_antes_inci = h_trabajadas_antes_inci;
    }
    
    
    
}
