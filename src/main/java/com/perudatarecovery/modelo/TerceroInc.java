package com.perudatarecovery.modelo;

public class TerceroInc {
    
    private int id_empresa_ti;
    private String razon_social;
    private int ruc;
    private String departamento;
    private String provincia;
    private String distrito;
    private String direccion;
    private String a_economica;
    private int n_trabajadores;
    private int n_trabajadores_sctr;
    private int n_trabajadores_nosctr;
    private String n_aseguradora;

    public TerceroInc() {  
    }

    public TerceroInc(int id_empresa_ti, String razon_social, int ruc, String departamento, String provincia, String distrito, String direccion, String a_economica, int n_trabajadores, int n_trabajadores_sctr, int n_trabajadores_nosctr, String n_aseguradora) {
        this.id_empresa_ti = id_empresa_ti;
        this.razon_social = razon_social;
        this.ruc = ruc;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.direccion = direccion;
        this.a_economica = a_economica;
        this.n_trabajadores = n_trabajadores;
        this.n_trabajadores_sctr = n_trabajadores_sctr;
        this.n_trabajadores_nosctr = n_trabajadores_nosctr;
        this.n_aseguradora = n_aseguradora;
    }

    public int getId_empresa_ti() {
        return id_empresa_ti;
    }

    public void setId_empresa_ti(int id_empresa_ti) {
        this.id_empresa_ti = id_empresa_ti;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        this.ruc = ruc;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getA_economica() {
        return a_economica;
    }

    public void setA_economica(String a_economica) {
        this.a_economica = a_economica;
    }

    public int getN_trabajadores() {
        return n_trabajadores;
    }

    public void setN_trabajadores(int n_trabajadores) {
        this.n_trabajadores = n_trabajadores;
    }

    public int getN_trabajadores_sctr() {
        return n_trabajadores_sctr;
    }

    public void setN_trabajadores_sctr(int n_trabajadores_sctr) {
        this.n_trabajadores_sctr = n_trabajadores_sctr;
    }

    public int getN_trabajadores_nosctr() {
        return n_trabajadores_nosctr;
    }

    public void setN_trabajadores_nosctr(int n_trabajadores_nosctr) {
        this.n_trabajadores_nosctr = n_trabajadores_nosctr;
    }

    public String getN_aseguradora() {
        return n_aseguradora;
    }

    public void setN_aseguradora(String n_aseguradora) {
        this.n_aseguradora = n_aseguradora;
    }

    
    
}