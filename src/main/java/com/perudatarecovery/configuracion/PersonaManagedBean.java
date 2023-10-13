package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.Persona;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.PrimeFaces;

@Named(value = "personaManagedBean")
@RequestScoped
public class PersonaManagedBean {

    private Persona selectedPersona;
    private List<Persona> listaPersona;
    private List<Persona> registrosNuevos; // Nueva lista para los registros recién insertados
    private boolean mostrarTabla;

    @PostConstruct
    public void init() {
        listaPersona = new ArrayList<>();
        registrosNuevos = new ArrayList<>(); // Inicializar la lista de registros nuevos
        mostrarTabla = true;
        // Establecer un valor predeterminado
        persona.setTipo_acci("1");
        persona.setGenero("M");
    }

    public PersonaManagedBean() {
        selectedPersona = new Persona();
    }

    public void inicializar() {
        listaPersona = obtenerRegistrosPersona();
    }

    public Persona getSelectedPersona() {
        return selectedPersona;
    }

    public void setSelectedPersona(Persona selectedPersona) {
        this.selectedPersona = selectedPersona;
    }

    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public List<Persona> getRegistrosNuevos() {
        return registrosNuevos;
    }

    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    private boolean mostrarSelectOneMenu = false;

    public boolean isMostrarSelectOneMenu() {
        return mostrarSelectOneMenu;
    }

    public void setMostrarSelectOneMenu(boolean mostrarSelectOneMenu) {
        this.mostrarSelectOneMenu = mostrarSelectOneMenu;
    }

    public void handleRadioChange() {
        if ("2".equals(persona.getTipo_acci())) {
            mostrarSelectOneMenu = true;
        } else {
            mostrarSelectOneMenu = false;
        }
    }

    public void eliminarRegistro(int idPersonaa) {
        try (
                 Connection conn = Conexion.obtenerConexion();  Statement sql = conn.createStatement()) {

            String query = "DELETE FROM persona_accipp WHERE id_personaa = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, idPersonaa);
            pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Persona> obtenerRegistrosPersona() {
        List<Persona> data = new ArrayList<>(listaPersona);

        data.addAll(registrosNuevos); // Agregar registros nuevos a la lista

        Collections.sort(data, Comparator.comparingInt(Persona::getId_personaa));

        return data;
    }

    public void agregarRegistro() {
        try (
                 Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("INSERT INTO persona_accipp (tipo_acci, nombres_c, apellido_p, apellido_m, dni_ce, edad, genero, area_trabajo, puesto_trabajo, antiguedad_empleo, turno, tipo_contrato, tiempo_experiencia, h_trabajadas_antes_acci) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, selectedPersona.getTipo_acci());
            pst.setString(2, selectedPersona.getNombres_c());
            pst.setString(3, selectedPersona.getApellido_p());
            pst.setString(4, selectedPersona.getApellido_m());
            pst.setInt(5, selectedPersona.getDni_ce());
            pst.setInt(6, selectedPersona.getEdad());
            pst.setString(7, selectedPersona.getGenero());
            pst.setString(8, selectedPersona.getArea_trabajo());
            pst.setString(9, selectedPersona.getPuesto_trabajo());
            pst.setString(10, selectedPersona.getAntiguedad_empleo());
            pst.setString(11, selectedPersona.getTurno());
            pst.setString(12, selectedPersona.getTipo_contrato());
            pst.setString(13, selectedPersona.getTiempo_experiencia());
            pst.setInt(14, selectedPersona.getH_trabajadas_antes_acci());

            pst.executeUpdate();

            try ( ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int nuevoID = generatedKeys.getInt(1);

                    // Crear una nueva instancia de Persona para el nuevo registro
                    Persona nuevoRegistro = new Persona();
                    nuevoRegistro.setId_personaa(nuevoID);
                    nuevoRegistro.setTipo_acci(selectedPersona.getTipo_acci());
                    nuevoRegistro.setNombres_c(selectedPersona.getNombres_c());
                    nuevoRegistro.setApellido_p(selectedPersona.getApellido_p());
                    nuevoRegistro.setApellido_m(selectedPersona.getApellido_m());
                    nuevoRegistro.setDni_ce(selectedPersona.getDni_ce());
                    nuevoRegistro.setEdad(selectedPersona.getEdad());
                    nuevoRegistro.setGenero(selectedPersona.getGenero());
                    nuevoRegistro.setArea_trabajo(selectedPersona.getArea_trabajo());
                    nuevoRegistro.setPuesto_trabajo(selectedPersona.getPuesto_trabajo());
                    nuevoRegistro.setAntiguedad_empleo(selectedPersona.getAntiguedad_empleo());
                    nuevoRegistro.setTurno(selectedPersona.getTurno());
                    nuevoRegistro.setTipo_contrato(selectedPersona.getTipo_contrato());
                    nuevoRegistro.setTiempo_experiencia(selectedPersona.getTiempo_experiencia());
                    nuevoRegistro.setH_trabajadas_antes_acci(selectedPersona.getH_trabajadas_antes_acci());

                    // Agregar el registro a la lista de registros nuevos
                    registrosNuevos.add(nuevoRegistro);

                    // Refrescar la lista de registros en la tabla
                    mostrarTabla = true;
                    PrimeFaces.current().ajax().update("tablaPersonas"); // Asegúrate de que "tablaPersonas" sea el ID correcto de la tabla en tu página

                    // Crear una nueva instancia de Persona para el siguiente registro
                    selectedPersona = new Persona();

                    // Ejecutar un script de JavaScript para agregar la fila a la tabla
                    PrimeFaces.current().executeScript("agregarFilaATabla(" + nuevoRegistro.getId_personaa() + ");");
                }
            }

            PrimeFaces.current().executeScript("window.scrollTo(0,document.body.scrollHeight);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String obtenerRegistrosNuevosJS() {
        StringBuilder script = new StringBuilder();

        for (Persona registro : registrosNuevos) {
            script.append("var newRow = document.createElement('tr');");

            // Agregar más código para crear las celdas y establecer los valores de las columnas en newRow
            script.append("table.appendChild(newRow);");
        }

        return script.toString();
    }

    public void prepararEdicion(Persona persona) {
        selectedPersona = persona;
    }

    public void prepararGravedad(Persona persona) {
        selectedPersona = persona;
    }

    private Persona persona = new Persona();

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    // Método para limpiar la lista de registros nuevos y ocultar la tabla
    public void showTable() {
        registrosNuevos.clear();
        mostrarTabla = false;
    }
}
