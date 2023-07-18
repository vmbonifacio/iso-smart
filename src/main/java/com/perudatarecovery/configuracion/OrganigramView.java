package com.perudatarecovery.configuracion;

import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.organigram.OrganigramNodeCollapseEvent;
import org.primefaces.event.organigram.OrganigramNodeDragDropEvent;
import org.primefaces.event.organigram.OrganigramNodeExpandEvent;
import org.primefaces.event.organigram.OrganigramNodeSelectEvent;
import org.primefaces.model.DefaultOrganigramNode;
import org.primefaces.model.OrganigramNode;

@Named
@ViewScoped
public class OrganigramView implements Serializable {

    private OrganigramNode rootNode;
    private OrganigramNode selection;

    private boolean zoom = false;
    private String style = "width: 100%";
    private int leafNodeConnectorHeight = 0;
    private boolean autoScrollToSelection = false;

    @PostConstruct
    public void init() {
        rootNode = new DefaultOrganigramNode("root", "CommerceBay GmbH", null);
        rootNode.setCollapsible(false);
        rootNode.setDroppable(true);

        OrganigramNode gerenciaGeneral = addNode(rootNode, "Gerencia General");

        addNode(gerenciaGeneral, "Administración");
    }

    protected OrganigramNode addNode(OrganigramNode parent, String name) {
        DefaultOrganigramNode node = new DefaultOrganigramNode("node", name, parent);
        node.setDroppable(true);
        node.setDraggable(true);
        node.setSelectable(true);
        return node;
    }

    public void nodeDragDropListener(OrganigramNodeDragDropEvent event) {
        OrganigramNode draggedNode = event.getOrganigramNode();
        OrganigramNode targetNode = event.getTargetOrganigramNode();

        makeConnection(draggedNode, targetNode);
    }

    private void makeConnection(OrganigramNode sourceNode, OrganigramNode targetNode) {
        sourceNode.getChildren().add(targetNode);
    }

    public void nodeSelectListener(OrganigramNodeSelectEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() + "' selected.");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void nodeCollapseListener(OrganigramNodeCollapseEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() + "' collapsed.");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void nodeExpandListener(OrganigramNodeExpandEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSummary("Node '" + event.getOrganigramNode().getData() + "' expanded.");
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public OrganigramNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(OrganigramNode rootNode) {
        this.rootNode = rootNode;
    }

    public OrganigramNode getSelection() {
        return selection;
    }

    public void setSelection(OrganigramNode selection) {
        this.selection = selection;
    }

    public boolean isZoom() {
        return zoom;
    }

    public void setZoom(boolean zoom) {
        this.zoom = zoom;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getLeafNodeConnectorHeight() {
        return leafNodeConnectorHeight;
    }

    public void setLeafNodeConnectorHeight(int leafNodeConnectorHeight) {
        this.leafNodeConnectorHeight = leafNodeConnectorHeight;
    }

    public boolean isAutoScrollToSelection() {
        return autoScrollToSelection;
    }

    public void setAutoScrollToSelection(boolean autoScrollToSelection) {
        this.autoScrollToSelection = autoScrollToSelection;
    }
}
