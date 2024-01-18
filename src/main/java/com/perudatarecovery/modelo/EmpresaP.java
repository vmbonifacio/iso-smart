package com.perudatarecovery.modelo;
import java.util.Date;

public class EmpresaP {
    private int id_empresa_p;
    private Integer ruc;
    private String razon_s;
    private String nombre_c;
    private String actividad;
    private String departamento;
    private String provincia;
    private String distrito;
    private String direccion;
    private Integer telefono;
    private String correo;
    private Integer num_licencia;
    private Date fecha_fundacion;
    private Date fecha_operacion;
    private Integer numero_s;
        
    public EmpresaP(){
    }

    public EmpresaP(int id_empresa_p, Integer ruc, String razon_s, String nombre_c, String actividad, String departamento, String provincia, String distrito, String direccion, Integer telefono, String correo, Integer num_licencia, Date fecha_fundacion, Date fecha_operacion, Integer numero_s) {
        this.id_empresa_p = id_empresa_p;
        this.ruc = ruc;
        this.razon_s = razon_s;
        this.nombre_c = nombre_c;
        this.actividad = actividad;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.num_licencia = num_licencia;
        this.fecha_fundacion = fecha_fundacion;
        this.fecha_operacion = fecha_operacion;
        this.numero_s = numero_s;
    }

    public int getId_empresa_p() {
        return id_empresa_p;
    }

    public void setId_empresa_p(int id_empresa_p) {
        this.id_empresa_p = id_empresa_p;
    }

    public Integer getRuc() {
        return ruc;
    }

    public void setRuc(Integer ruc) {
        this.ruc = ruc;
    }

    public String getRazon_s() {
        return razon_s;
    }

    public void setRazon_s(String razon_s) {
        this.razon_s = razon_s;
    }

    public String getNombre_c() {
        return nombre_c;
    }

    public void setNombre_c(String nombre_c) {
        this.nombre_c = nombre_c;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getNum_licencia() {
        return num_licencia;
    }

    public void setNum_licencia(Integer num_licencia) {
        this.num_licencia = num_licencia;
    }

    public Date getFecha_fundacion() {
        return fecha_fundacion;
    }

    public void setFecha_fundacion(Date fecha_fundacion) {
        this.fecha_fundacion = fecha_fundacion;
    }

    public Date getFecha_operacion() {
        return fecha_operacion;
    }

    public void setFecha_operacion(Date fecha_operacion) {
        this.fecha_operacion = fecha_operacion;
    }

    public Integer getNumero_s() {
        return numero_s;
    }

    public void setNumero_s(Integer numero_s) {
        this.numero_s = numero_s;
    }

    
}
