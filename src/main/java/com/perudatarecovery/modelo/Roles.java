package com.perudatarecovery.modelo;

import java.io.Serializable;

public class Roles implements Serializable{

    private int id_asig;
    private String usuario;
    private String rol;

    public Roles() {
    }

    public Roles(int id_asig, String usuario, String rol) {
        this.id_asig = id_asig;
        this.usuario = usuario;
        this.rol = rol;
    }

    public int getId_asig() {
        return id_asig;
    }

    public void setId_asig(int id_asig) {
        this.id_asig = id_asig;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }  
}
