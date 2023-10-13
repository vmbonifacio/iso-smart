package com.perudatarecovery.modelo;

import java.util.Date;

public class Trabajador {
    private int id_trabajador;
    private String nombre;
    private String ap_p;
    private String ap_m;
    private int dni_ce;
    private Date fecha_nacimiento;
    private String genero;
    private String estado_civil;
    private String departamento;
    private String distrito;
    private String provincia;
    private String direccion;
    private String tipo_contrato;
    private Date fecha_ingreso;
    private String turno;
    private String area;
    private String puesto;

    public Trabajador(){

    }

    public Trabajador(int id_trabajador, String nombre, String ap_p, String ap_m, int dni_ce, Date fecha_nacimiento, String genero, String estado_civil, String departamento, String distrito, String provincia, String direccion, String tipo_contrato, Date fecha_ingreso, String turno, String area, String puesto) {
        this.id_trabajador = id_trabajador;
        this.nombre = nombre;
        this.ap_p = ap_p;
        this.ap_m = ap_m;
        this.dni_ce = dni_ce;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.estado_civil = estado_civil;
        this.departamento = departamento;
        this.distrito = distrito;
        this.provincia = provincia;
        this.direccion = direccion;
        this.tipo_contrato = tipo_contrato;
        this.fecha_ingreso = fecha_ingreso;
        this.turno = turno;
        this.area = area;
        this.puesto = puesto;
    }

    public int getId_trabajador() {
        return id_trabajador;
    }

    public void setId_trabajador(int id_trabajador) {
        this.id_trabajador = id_trabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getDni_ce() {
        return dni_ce;
    }

    public void setDni_ce(int dni_ce) {
        this.dni_ce = dni_ce;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo_contrato() {
        return tipo_contrato;
    }

    public void setTipo_contrato(String tipo_contrato) {
        this.tipo_contrato = tipo_contrato;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
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

    
}