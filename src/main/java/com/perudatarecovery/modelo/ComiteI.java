
package com.perudatarecovery.modelo;


public class ComiteI {
    private int id_comite_investigacion;
    private String nombre;
    private String apellidos;
    private String puesto_comite;
    private String estado_persona;

    public ComiteI() {
    }

    public ComiteI(int id_comite_investigacion, String nombre, String apellidos, String puesto_comite, String estado_persona) {
        this.id_comite_investigacion = id_comite_investigacion;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.puesto_comite = puesto_comite;
        this.estado_persona = estado_persona;
    }

    public int getId_comite_investigacion() {
        return id_comite_investigacion;
    }

    public void setId_comite_investigacion(int id_comite_investigacion) {
        this.id_comite_investigacion = id_comite_investigacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPuesto_comite() {
        return puesto_comite;
    }

    public void setPuesto_comite(String puesto_comite) {
        this.puesto_comite = puesto_comite;
    }

    public String getEstado_persona() {
        return estado_persona;
    }

    public void setEstado_persona(String estado_persona) {
        this.estado_persona = estado_persona;
    }
    
    
}
