package modelo;

public class Persona {
    String RazonSocialD;
    char Ruc;
    int Domicilio;
    String Direccion;
    int Actividad;
    int NumTrabajadores;

    public Persona(String RazonSocialD, char Ruc, int Domicilio, String Direccion, int Actividad, int NumTrabajadores) {
        this.RazonSocialD = RazonSocialD;
        this.Ruc = Ruc;
        this.Domicilio = Domicilio;
        this.Direccion = Direccion;
        this.Actividad = Actividad;
        this.NumTrabajadores = NumTrabajadores;
    }

    public String getRazonSocialD() {
        return RazonSocialD;
    }

    public void setRazonSocialD(String RazonSocialD) {
        this.RazonSocialD = RazonSocialD;
    }

    public char getRuc() {
        return Ruc;
    }

    public void setRuc(char Ruc) {
        this.Ruc = Ruc;
    }

    public int getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(int Domicilio) {
        this.Domicilio = Domicilio;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getActividad() {
        return Actividad;
    }

    public void setActividad(int Actividad) {
        this.Actividad = Actividad;
    }

    public int getNumTrabajadores() {
        return NumTrabajadores;
    }

    public void setNumTrabajadores(int NumTrabajadores) {
        this.NumTrabajadores = NumTrabajadores;
    }

    
}

