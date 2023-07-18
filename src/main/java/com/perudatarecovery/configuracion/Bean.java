package com.perudatarecovery.configuracion;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

@ManagedBean
@SessionScoped
public class Bean {
    private String selectedOption;

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public void abrirDialogo() {
    if (selectedOption.equals("1")) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Accidente", null));
        PrimeFaces.current().executeScript("PF('dialogoAccidente').show();");
    } else if (selectedOption.equals("2")) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Incidente", null));
        PrimeFaces.current().executeScript("PF('dialogoIncidente').show();");
    }
}


}