package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.PersonaC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

@Named(value = "personaCManagedBean")
@RequestScoped
public class PersonaCManagedBean {

    private PersonaC selectedPersonaC;
    private List<PersonaC> listaPersonaC;
    private List<PersonaC> registrosNuevos; // Nueva lista para los registros recién insertados
    private boolean mostrarTabla;

    @PostConstruct
    public void init() {
        listaPersonaC = new ArrayList<>();
        registrosNuevos = new ArrayList<>(); // Inicializar la lista de registros nuevos
        mostrarTabla = true;
    }

    public PersonaCManagedBean() {
        selectedPersonaC = new PersonaC();
    }

    public void inicializar() {
        listaPersonaC = obtenerRegistrosPersonaC();
    }

    public PersonaC getSelectedPersonaC() {
        return selectedPersonaC;
    }

    public void setSelectedPersonaC(PersonaC selectedPersonaC) {
        this.selectedPersonaC = selectedPersonaC;
    }

    public List<PersonaC> getListaPersonaC() {
        return listaPersonaC;
    }

    public void setListaPersonaC(List<PersonaC> listaPersonaC) {
        this.listaPersonaC = listaPersonaC;
    }

    public List<PersonaC> getRegistrosNuevos() {
        return registrosNuevos;
    }

    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    //METODO PARA BOTON ELIMINAR

    public void eliminarRegistroC(int id_personaa_c) {
        try (
                 Connection conn = Conexion.obtenerConexion();  PreparedStatement pst = conn.prepareStatement("DELETE FROM persona_acci_cp WHERE id_personaa_c = ?")) {
            pst.setInt(1, id_personaa_c);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    //    METODO OBTENER REGISTRO PERSONA CIVIL
    public List<PersonaC> obtenerRegistrosPersonaC() {
        List<PersonaC> data = new ArrayList<>(listaPersonaC);

        data.addAll(registrosNuevos); // Agregar registros nuevos a la lista

        Collections.sort(data, Comparator.comparingInt(PersonaC::getId_personaa_c));

        return data;
    }

    public void agregarRegistroC() {
        try (
                 Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("INSERT INTO persona_acci_cp (tipo_acci, nombres_c, apellido_p, apellido_m, dni_ce, edad, genero, area_trabajo, puesto_trabajo, antiguedad_empleo, turno, tipo_contrato, tiempo_experiencia, h_trabajadas_antes_acci) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, selectedPersonaC.getTipo_acci());
            pst.setString(2, selectedPersonaC.getNombres_c());
            pst.setString(3, selectedPersonaC.getApellido_p());
            pst.setString(4, selectedPersonaC.getApellido_m());
            pst.setInt(5, selectedPersonaC.getDni_ce());
            pst.setInt(6, selectedPersonaC.getEdad());
            pst.setString(7, selectedPersonaC.getGenero());
            pst.setString(8, selectedPersonaC.getArea_trabajo());
            pst.setString(9, selectedPersonaC.getPuesto_trabajo());
            pst.setString(10, selectedPersonaC.getAntiguedad_empleo());
            pst.setString(11, selectedPersonaC.getTurno());
            pst.setString(12, selectedPersonaC.getTipo_contrato());
            pst.setString(13, selectedPersonaC.getTiempo_experiencia());
            pst.setInt(14, selectedPersonaC.getH_trabajadas_antes_acci());

            pst.executeUpdate();

            try ( ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int nuevoID = generatedKeys.getInt(1);

                    PersonaC nuevoRegistro = new PersonaC();
                    nuevoRegistro.setId_personaa_c(nuevoID);
                    nuevoRegistro.setTipo_acci(selectedPersonaC.getTipo_acci());
                    nuevoRegistro.setNombres_c(selectedPersonaC.getNombres_c());
                    nuevoRegistro.setApellido_p(selectedPersonaC.getApellido_p());
                    nuevoRegistro.setApellido_m(selectedPersonaC.getApellido_m());
                    nuevoRegistro.setDni_ce(selectedPersonaC.getDni_ce());
                    nuevoRegistro.setEdad(selectedPersonaC.getEdad());
                    nuevoRegistro.setGenero(selectedPersonaC.getGenero());
                    nuevoRegistro.setArea_trabajo(selectedPersonaC.getArea_trabajo());
                    nuevoRegistro.setPuesto_trabajo(selectedPersonaC.getPuesto_trabajo());
                    nuevoRegistro.setAntiguedad_empleo(selectedPersonaC.getAntiguedad_empleo());
                    nuevoRegistro.setTurno(selectedPersonaC.getTurno());
                    nuevoRegistro.setTipo_contrato(selectedPersonaC.getTipo_contrato());
                    nuevoRegistro.setTiempo_experiencia(selectedPersonaC.getTiempo_experiencia());
                    nuevoRegistro.setTipo_contrato(selectedPersonaC.getTipo_contrato());
                    nuevoRegistro.setH_trabajadas_antes_acci(selectedPersonaC.getH_trabajadas_antes_acci());

                    // Agregar el registro a la lista de registros nuevos
                    registrosNuevos.add(nuevoRegistro);

                    // Refrescar la lista de registros en la tabla
                    mostrarTabla = true;
                    PrimeFaces.current().ajax().update("tablaPersonasC"); // Asegúrate de que "tablaPersonasT" sea el ID correcto de la tabla en tu página

                    // Limpiar la instancia para el siguiente registro
                    selectedPersonaC = new PersonaC();

                    // Ejecutar un script de JavaScript para agregar la fila a la tabla
                    PrimeFaces.current().executeScript("agregarFilaATabla(" + nuevoRegistro.getId_personaa_c() + ");");
                }
            }

            PrimeFaces.current().executeScript("window.scrollTo(0,document.body.scrollHeight);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String obtenerRegistrosNuevosJS() {
        StringBuilder script = new StringBuilder();

        for (PersonaC registro : registrosNuevos) {
            script.append("var newRow = document.createElement('tr');");

            // Agregar más código para crear las celdas y establecer los valores de las columnas en newRow
            script.append("table.appendChild(newRow);");
        }

        return script.toString();
    }

    private PersonaC personac = new PersonaC();

    public PersonaC getPersonaC() {
        return personac;
    }

    public void setPersonaC(PersonaC personac) {
        this.personac = personac;
    }

    public void showTable() {
        registrosNuevos.clear();
        mostrarTabla = false;
    }
}
