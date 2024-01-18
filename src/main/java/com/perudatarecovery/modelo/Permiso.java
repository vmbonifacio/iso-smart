package com.perudatarecovery.modelo;

public class Permiso {

    private int id_control;
    private String usuario;
    private String datosg;
    private String estruc_orga;
    private String ges_traba;
    private String ges_areapuesto;
    private String permiso;

    public Permiso(int id_control, String usuario, String datosg, String estruc_orga, String ges_traba, String ges_areapuesto, String permiso) {
        this.id_control = id_control;
        this.usuario = usuario;
        this.datosg = datosg;
        this.estruc_orga = estruc_orga;
        this.ges_traba = ges_traba;
        this.ges_areapuesto = ges_areapuesto;
        this.permiso = permiso;
    }


    public Permiso() {
    }

    public int getId_control() {
        return id_control;
    }

    public void setId_control(int id_control) {
        this.id_control = id_control;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDatosg() {
        return datosg;
    }

    public void setDatosg(String datosg) {
        this.datosg = datosg;
    }

    public String getEstruc_orga() {
        return estruc_orga;
    }

    public void setEstruc_orga(String estruc_orga) {
        this.estruc_orga = estruc_orga;
    }

    public String getGes_traba() {
        return ges_traba;
    }

    public void setGes_traba(String ges_traba) {
        this.ges_traba = ges_traba;
    }

    public String getGes_areapuesto() {
        return ges_areapuesto;
    }

    public void setGes_areapuesto(String ges_areapuesto) {
        this.ges_areapuesto = ges_areapuesto;
    }

    public String getPermiso() {
        return permiso;
    }

    public void setPermiso(String permiso) {
        this.permiso = permiso;
    }

    
}
