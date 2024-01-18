package com.perudatarecovery.configuracion;

import org.primefaces.component.dnd.Draggable;

public class Node extends Draggable {

    private String type;
    private String styleClass;
    private String icon;
    private String iconPos;
    private String data;

    public Node(String type, String styleClass, String icon, String iconPos, String data) {
        this.type = type;
        this.styleClass = styleClass;
        this.icon = icon;
        this.iconPos = iconPos;
        this.data = data;
    }

    // Implementa los métodos de la interfaz Draggable
    public String getDragId() {
        return type; // Puedes usar cualquier identificador único aquí
    }

    public Object getData() {
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStyleClass() {
        return styleClass;
    }

    public void setStyleClass(String styleClass) {
        this.styleClass = styleClass;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconPos() {
        return iconPos;
    }

    public void setIconPos(String iconPos) {
        this.iconPos = iconPos;
    }


}
