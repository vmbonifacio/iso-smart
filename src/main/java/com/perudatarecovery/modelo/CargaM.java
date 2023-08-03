
package com.perudatarecovery.modelo;

import java.util.Date;


public class CargaM {
	private int id_cargam;
	private String nombre;
	private String ap_m;
	private String ap_p;
	private String tipoDoc;
	private Date fechaN;
	private String estadoC;
	private String genero;
	private String departamento;
	private String provincia;
	private String distrito;
	private String direccion;
	private String tipoC;
	private Date fechaI;
	private String turno;
	private String area;
	private String puesto;
	private String nivelE;
	private String tiempoE;
	private String salario;
	private String aseguradora;
	private String sucursales;
	private String tipoS;
    
    public CargaM(int id_cargam, String nombre, String ap_m, String ap_p, String tipoDoc, Date fechaN, String estadoC, String genero, String departamento, String provincia, String distrito, String direccion, String tipoC, Date fechaI, String turno, String area, String puesto, String nivelE, String tiempoE, String salario, String aseguradora, String sucursales, String tipoS) {
        this.id_cargam = id_cargam;
        this.nombre = nombre;
        this.ap_m = ap_m;
        this.ap_p = ap_p;
        this.tipoDoc = tipoDoc;
        this.fechaN = fechaN;
        this.estadoC = estadoC;
        this.genero = genero;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.direccion = direccion;
        this.tipoC = tipoC;
        this.fechaI = fechaI;
        this.turno = turno;
        this.area = area;
        this.puesto = puesto;
        this.nivelE = nivelE;
        this.tiempoE = tiempoE;
        this.salario = salario;
        this.aseguradora = aseguradora;
        this.sucursales = sucursales;
        this.tipoS = tipoS;
    }
    
     public CargaM(){
     }

    public CargaM(int id_cargam, String nombre, String ap_p, String ap_m, String tipoDoc, String genero) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getId_cargam() {
        return id_cargam;
    }

    public void setId_cargam(int id_cargam) {
        this.id_cargam = id_cargam;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp_m() {
        return ap_m;
    }

    public void setAp_m(String ap_m) {
        this.ap_m = ap_m;
    }

    public String getAp_p() {
        return ap_p;
    }

    public void setAp_p(String ap_p) {
        this.ap_p = ap_p;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public Date getFechaN() {
        return fechaN;
    }

    public void setFechaN(Date fechaN) {
        this.fechaN = fechaN;
    }

    public String getEstadoC() {
        return estadoC;
    }

    public void setEstadoC(String estadoC) {
        this.estadoC = estadoC;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public String getTipoC() {
        return tipoC;
    }

    public void setTipoC(String tipoC) {
        this.tipoC = tipoC;
    }

    public Date getFechaI() {
        return fechaI;
    }

    public void setFechaI(Date fechaI) {
        this.fechaI = fechaI;
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

    public String getNivelE() {
        return nivelE;
    }

    public void setNivelE(String nivelE) {
        this.nivelE = nivelE;
    }

    public String getTiempoE() {
        return tiempoE;
    }

    public void setTiempoE(String tiempoE) {
        this.tiempoE = tiempoE;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    public String getSucursales() {
        return sucursales;
    }

    public void setSucursales(String sucursales) {
        this.sucursales = sucursales;
    }

    public String getTipoS() {
        return tipoS;
    }

    public void setTipoS(String tipoS) {
        this.tipoS = tipoS;
    }

        
}
