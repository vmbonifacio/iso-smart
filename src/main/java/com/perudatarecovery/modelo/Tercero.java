package com.perudatarecovery.modelo;

public class Tercero {

    private Integer id_empresa_tp;
    private String razon_social;
    private Integer ruc;
    private String departamento;
    private String provincia;
    private String distrito;
    private String direccion;
    private String a_economica;
    private Integer n_trabajadores;
    private Integer n_trabajadores_sctr;
    private Integer n_trabajadores_nosctr;
    private String n_aseguradora;

    public Tercero() {
    }

    public Tercero(Integer id_empresa_tp, String razon_social, Integer ruc, String departamento, String provincia, String distrito, String direccion, String a_economica, Integer n_trabajadores, Integer n_trabajadores_sctr, Integer n_trabajadores_nosctr, String n_aseguradora) {
        this.id_empresa_tp = id_empresa_tp;
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

    public Integer getId_empresa_tp() {
        return id_empresa_tp;
    }

    public void setId_empresa_tp(Integer id_empresa_tp) {
        this.id_empresa_tp = id_empresa_tp;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public Integer getRuc() {
        return ruc;
    }

    public void setRuc(Integer ruc) {
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

    public Integer getN_trabajadores() {
        return n_trabajadores;
    }

    public void setN_trabajadores(Integer n_trabajadores) {
        this.n_trabajadores = n_trabajadores;
    }

    public Integer getN_trabajadores_sctr() {
        return n_trabajadores_sctr;
    }

    public void setN_trabajadores_sctr(Integer n_trabajadores_sctr) {
        this.n_trabajadores_sctr = n_trabajadores_sctr;
    }

    public Integer getN_trabajadores_nosctr() {
        return n_trabajadores_nosctr;
    }

    public void setN_trabajadores_nosctr(Integer n_trabajadores_nosctr) {
        this.n_trabajadores_nosctr = n_trabajadores_nosctr;
    }

    public String getN_aseguradora() {
        return n_aseguradora;
    }

    public void setN_aseguradora(String n_aseguradora) {
        this.n_aseguradora = n_aseguradora;
    }

    
}
