package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.PersonaT;
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

@Named(value = "personaTManagedBean")
@RequestScoped

public class PersonaTManagedBean {

    private PersonaT selectedPersonaT;
    private List<PersonaT> listaPersonaT;
    private List<PersonaT> registrosNuevos; // Nueva lista para los registros recién insertados
    private List<String> listaRazonSocial;
    private boolean mostrarTabla;

    @PostConstruct
    public void init() {
        listaPersonaT = new ArrayList<>();
        registrosNuevos = new ArrayList<>(); // Inicializar la lista de registros nuevos
        mostrarTabla = true;
        cargarListaRazonSocial();
    }

    public PersonaTManagedBean() {
        selectedPersonaT = new PersonaT();
    }

    public void inicializar() {
        listaPersonaT = obtenerRegistrosPersonaT();
    }

    public PersonaT getSelectedPersonaT() {
        return selectedPersonaT;
    }

    public void setSelectedPersonaT(PersonaT selectedPersonaT) {
        this.selectedPersonaT = selectedPersonaT;
    }

    public List<PersonaT> getListaPersonaT() {
        return listaPersonaT;
    }

    public void setListaPersonaT(List<PersonaT> listaPersonaT) {
        this.listaPersonaT = listaPersonaT;
    }

    public List<PersonaT> getRegistrosNuevos() {
        return registrosNuevos;
    }

    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    public List<String> getListaRazonSocial() {
        return listaRazonSocial;
    }

//  METODO BOTON ELIMINAR REGISTRO
    public void eliminarRegistroT(int id_personaa_t) {
        try ( Connection conn = Conexion.obtenerConexion();  PreparedStatement pst = conn.prepareStatement("DELETE FROM persona_accitpp WHERE id_personaa_t = ?")) {

            pst.setInt(1, id_personaa_t);
            pst.executeUpdate();

            listaPersonaT = obtenerRegistrosPersonaT();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el registro: " + e.getMessage());
        }
    }

//    METODO OBTENER REGISTRO PERSONA
    public List<PersonaT> obtenerRegistrosPersonaT() {
        List<PersonaT> data = new ArrayList<>(listaPersonaT);

        data.addAll(registrosNuevos); // Agregar registros nuevos a la lista

        Collections.sort(data, Comparator.comparingInt(PersonaT::getId_personaa_t));

        return data;
    }

    public void agregarRegistroT() {
        try ( Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("INSERT INTO persona_accitpp (tipo_acci, nombres_c, apellido_p, apellido_m, dni_ce, edad, genero, empresa_p, area_trabajo, puesto_trabajo, antiguedad_empleo, turno, h_trabajadas_antes_acci) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            pst.setString(1, selectedPersonaT.getTipo_acci());
            pst.setString(2, selectedPersonaT.getNombres_c());
            pst.setString(3, selectedPersonaT.getApellido_p());
            pst.setString(4, selectedPersonaT.getApellido_m());
            pst.setInt(5, selectedPersonaT.getDni_ce());
            pst.setInt(6, selectedPersonaT.getEdad());
            pst.setString(7, selectedPersonaT.getGenero());
            pst.setString(8, selectedPersonaT.getEmpresa_p());
            pst.setString(9, selectedPersonaT.getArea_trabajo());
            pst.setString(10, selectedPersonaT.getPuesto_trabajo());
            pst.setString(11, selectedPersonaT.getAntiguedad_empleo());
            pst.setString(12, selectedPersonaT.getTurno());
            pst.setInt(13, selectedPersonaT.getH_trabajadas_antes_acci());

            pst.executeUpdate();

            try ( ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int nuevoID = generatedKeys.getInt(1);

                    PersonaT nuevoRegistro = new PersonaT();
                    nuevoRegistro.setId_personaa_t(nuevoID);
                    nuevoRegistro.setTipo_acci(selectedPersonaT.getTipo_acci());
                    nuevoRegistro.setNombres_c(selectedPersonaT.getNombres_c());
                    nuevoRegistro.setApellido_p(selectedPersonaT.getApellido_p());
                    nuevoRegistro.setApellido_m(selectedPersonaT.getApellido_m());
                    nuevoRegistro.setDni_ce(selectedPersonaT.getDni_ce());
                    nuevoRegistro.setEdad(selectedPersonaT.getEdad());
                    nuevoRegistro.setGenero(selectedPersonaT.getGenero());
                    nuevoRegistro.setEmpresa_p(selectedPersonaT.getEmpresa_p());
                    nuevoRegistro.setArea_trabajo(selectedPersonaT.getArea_trabajo());
                    nuevoRegistro.setPuesto_trabajo(selectedPersonaT.getPuesto_trabajo());
                    nuevoRegistro.setAntiguedad_empleo(selectedPersonaT.getAntiguedad_empleo());
                    nuevoRegistro.setTurno(selectedPersonaT.getTurno());
                    nuevoRegistro.setH_trabajadas_antes_acci(selectedPersonaT.getH_trabajadas_antes_acci());

                    // Agregar el registro a la lista de registros nuevos
                    registrosNuevos.add(nuevoRegistro);

                    // Refrescar la lista de registros en la tabla
                    mostrarTabla = true;
                    PrimeFaces.current().ajax().update("tablaPersonasT"); // Asegúrate de que "tablaPersonasT" sea el ID correcto de la tabla en tu página

                    // Limpiar la instancia para el siguiente registro
                    selectedPersonaT = new PersonaT();

                    // Ejecutar un script de JavaScript para agregar la fila a la tabla
                    PrimeFaces.current().executeScript("agregarFilaATabla(" + nuevoRegistro.getId_personaa_t() + ");");
                }
            }

            PrimeFaces.current().executeScript("window.scrollTo(0,document.body.scrollHeight);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarListaRazonSocial() {
        listaRazonSocial = new ArrayList<>();

        try ( Connection conn = Conexion.obtenerConexion();  
                Statement stmt = conn.createStatement(); 
                ResultSet rs = stmt.executeQuery("EXEC ObtenerRazonSocial")) {

            while (rs.next()) {
                String razonSocial = rs.getString("razon_social");
                listaRazonSocial.add(razonSocial);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String obtenerRegistrosNuevosJS() {
        StringBuilder script = new StringBuilder();

        for (PersonaT registro : registrosNuevos) {
            script.append("var newRow = document.createElement('tr');");

            // Agregar más código para crear las celdas y establecer los valores de las columnas en newRow
            script.append("table.appendChild(newRow);");
        }

        return script.toString();
    }

    public void prepararEdicion(PersonaT personaT) {
        selectedPersonaT = personaT;
    }

    private PersonaT personaT = new PersonaT();

    public PersonaT getPersonaT() {
        return personaT;
    }

    public void setPersonaT(PersonaT personaT) {
        this.personaT = personaT;
    }

    // Método para limpiar la lista de registros nuevos y ocultar la tabla
    public void showTable() {
        registrosNuevos.clear();
        mostrarTabla = false;
    }
}
