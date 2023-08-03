package com.perudatarecovery.modelo;

import java.util.Date;

public class Ocurrencia {
    private int codigo;
    private String nombre;
    private String tipoOcurrencia;
    private Date fechaOcurrencia;
    private int numPersonasAfectadas;

    public Ocurrencia() {
    }

    public Ocurrencia(int codigo, String nombre, String tipoOcurrencia, Date fechaOcurrencia, int numPersonasAfectadas) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipoOcurrencia = tipoOcurrencia;
        this.fechaOcurrencia = fechaOcurrencia;
        this.numPersonasAfectadas = numPersonasAfectadas;
    }

    public int getNumPersonasAfectadas() {
        return numPersonasAfectadas;
    }

    public void setNumPersonasAfectadas(int numPersonasAfectadas) {
        this.numPersonasAfectadas = numPersonasAfectadas;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoOcurrencia() {
        return tipoOcurrencia;
    }

    public void setTipoOcurrencia(String tipoOcurrencia) {
        this.tipoOcurrencia = tipoOcurrencia;
    }

    public Date getFechaOcurrencia() {
        return fechaOcurrencia;
    }

    public void setFechaOcurrencia(Date fechaOcurrencia) {
        this.fechaOcurrencia = fechaOcurrencia;
    }
    
    
}
