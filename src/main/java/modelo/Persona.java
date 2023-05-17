package modelo;

public class Persona {
    private String RazonSocialD;
    private char ruc;
    private int domicilio;
    private String direccion;
    private int actividad;
    private int numTrabajadores;

    public Persona(String RazonSocialD, char ruc, int domicilio, String direccion, int actividad, int numTrabajadores) {
        this.RazonSocialD = RazonSocialD;
        this.ruc = ruc;
        this.domicilio = domicilio;
        this.direccion = direccion;
        this.actividad = actividad;
        this.numTrabajadores = numTrabajadores;
    }

    public String getRazonSocialD() {
        return RazonSocialD;
    }

    public void setRazonSocialD(String RazonSocialD) {
        this.RazonSocialD = RazonSocialD;
    }

    public char getRuc() {
        return ruc;
    }

    public void setRuc(char ruc) {
        this.ruc = ruc;
    }

    public int getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(int domicilio) {
        this.domicilio = domicilio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getActividad() {
        return actividad;
    }

    public void setActividad(int actividad) {
        this.actividad = actividad;
    }

    public int getNumTrabajadores() {
        return numTrabajadores;
    }

    public void setNumTrabajadores(int numTrabajadores) {
        this.numTrabajadores = numTrabajadores;
    }
    
    
    
}

