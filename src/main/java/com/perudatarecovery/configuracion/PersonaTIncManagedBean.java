package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.PersonaTI;
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

@Named(value = "personaTIncManagedBean")
@RequestScoped
public class PersonaTIncManagedBean {

    private PersonaTI selectedPersonaTI;
    private List<PersonaTI> listaPersonaTI;
    private List<PersonaTI> registrosNuevos; // Nueva lista para los registros recién insertados
    private List<String> listaRazonSocialI;
    private boolean mostrarTabla;

    @PostConstruct
    public void init() {
        listaPersonaTI = new ArrayList<>();
        registrosNuevos = new ArrayList<>(); // Inicializar la lista de registros nuevos
        mostrarTabla = true;
        cargarListaRazonSocialI();
    }

    public PersonaTIncManagedBean() {
        selectedPersonaTI = new PersonaTI();
    }

    public void inicializar() {
        listaPersonaTI = obtenerRegistrosPersonaTI();

    }

    public PersonaTI getSelectedPersonaTI() {
        return selectedPersonaTI;
    }

    public void setSelectedPersonaTI(PersonaTI selectedPersonaTI) {
        this.selectedPersonaTI = selectedPersonaTI;
    }

    public List<PersonaTI> getListaPersonaTI() {
        return listaPersonaTI;
    }

    public void setListaPersonaTI(List<PersonaTI> listaPersonaTI) {
        this.listaPersonaTI = listaPersonaTI;
    }

    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    public List<String> getListaRazonSocialI() {
        return listaRazonSocialI;
    }


    //    METODO OBTENER REGISTRO PERSONA
    public List<PersonaTI> obtenerRegistrosPersonaTI() {
        List<PersonaTI> data = new ArrayList<>(listaPersonaTI);

        data.addAll(registrosNuevos); // Agregar registros nuevos a la lista

        Collections.sort(data, Comparator.comparingInt(PersonaTI::getId_personai_t));

        return data;
    }

    public void agregarRegistroTI() {
        try ( Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("INSERT INTO persona_incitpp (tipo_inci, nombres_c, apellido_p, apellido_m, dni_ce, edad, genero, empresa_p, area_trabajo, puesto_trabajo, antiguedad_empleo, turno, h_trabajadas_antes_inci) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            pst.setString(1, selectedPersonaTI.getTipo_inci());
            pst.setString(2, selectedPersonaTI.getNombres_c());
            pst.setString(3, selectedPersonaTI.getApellido_p());
            pst.setString(4, selectedPersonaTI.getApellido_m());
            pst.setInt(5, selectedPersonaTI.getDni_ce());
            pst.setInt(6, selectedPersonaTI.getEdad());
            pst.setString(7, selectedPersonaTI.getGenero());
            pst.setString(8, selectedPersonaTI.getEmpresa_p());
            pst.setString(9, selectedPersonaTI.getArea_trabajo());
            pst.setString(10, selectedPersonaTI.getPuesto_trabajo());
            pst.setString(11, selectedPersonaTI.getAntiguedad_empleo());
            pst.setString(12, selectedPersonaTI.getTurno());
            pst.setInt(13, selectedPersonaTI.getH_trabajadas_antes_inci());

            pst.executeUpdate();

            try ( ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int nuevoID = generatedKeys.getInt(1);

                    PersonaTI nuevoRegistro = new PersonaTI();
                    nuevoRegistro.setId_personai_t(nuevoID);
                    nuevoRegistro.setTipo_inci(selectedPersonaTI.getTipo_inci());
                    nuevoRegistro.setNombres_c(selectedPersonaTI.getNombres_c());
                    nuevoRegistro.setApellido_p(selectedPersonaTI.getApellido_p());
                    nuevoRegistro.setApellido_m(selectedPersonaTI.getApellido_m());
                    nuevoRegistro.setDni_ce(selectedPersonaTI.getDni_ce());
                    nuevoRegistro.setEdad(selectedPersonaTI.getEdad());
                    nuevoRegistro.setGenero(selectedPersonaTI.getGenero());
                    nuevoRegistro.setEmpresa_p(selectedPersonaTI.getEmpresa_p());
                    nuevoRegistro.setArea_trabajo(selectedPersonaTI.getArea_trabajo());
                    nuevoRegistro.setPuesto_trabajo(selectedPersonaTI.getPuesto_trabajo());
                    nuevoRegistro.setAntiguedad_empleo(selectedPersonaTI.getAntiguedad_empleo());
                    nuevoRegistro.setTurno(selectedPersonaTI.getTurno());
                    nuevoRegistro.setH_trabajadas_antes_inci(selectedPersonaTI.getH_trabajadas_antes_inci());

                    // Agregar el registro a la lista de registros nuevos
                    registrosNuevos.add(nuevoRegistro);

                    // Refrescar la lista de registros en la tabla
                    mostrarTabla = true;
                    PrimeFaces.current().ajax().update("tablaPersonasTI");

                    // Limpiar la instancia para el siguiente registro
                    selectedPersonaTI = new PersonaTI();

                    PrimeFaces.current().executeScript("agregarFilaATabla(" + nuevoRegistro.getId_personai_t() + ");");

                }
            }

            PrimeFaces.current().executeScript("window.scrollTo(0,document.body.scrollHeight);");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al agregar el registro: " + e.getMessage());
        }
    }

    //    METODO BOTON ELIMINAR REGISTRO
    public void eliminarRegistroTI(int id_personai_t) {
        try ( Connection conn = Conexion.obtenerConexion();  PreparedStatement pst = conn.prepareStatement("DELETE FROM persona_incitpp WHERE id_personai_t = ?")) {

            pst.setInt(1, id_personai_t);
            pst.executeUpdate();

            listaPersonaTI = obtenerRegistrosPersonaTI();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al eliminar el registro: " + e.getMessage());
        }
    }

    private PersonaTI personaTI = new PersonaTI();

    public PersonaTI getPersonaTI() {
        return personaTI;
    }

    public void setPersonaTI(PersonaTI personaT) {
        this.personaTI = personaT;
    }

    public void cargarListaRazonSocialI() {
        listaRazonSocialI = new ArrayList<>();

        try ( Connection conn = Conexion.obtenerConexion();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery("EXEC ObtenerRazonSocialI")) {

            while (rs.next()) {
                String razonSocial = rs.getString("razon_social");
                listaRazonSocialI.add(razonSocial);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
