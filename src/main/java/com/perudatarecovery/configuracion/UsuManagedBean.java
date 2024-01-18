package com.perudatarecovery.configuracion;

public class UsuManagedBean {

    private boolean datosGeneralesEnabled = true;

    public boolean isDatosGeneralesEnabled() {
        return datosGeneralesEnabled;
    }

    public void setDatosGeneralesEnabled(boolean datosGeneralesEnabled) {
        this.datosGeneralesEnabled = datosGeneralesEnabled;
    }
}
