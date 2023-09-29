package com.perudatarecovery.configuracion;

import com.perudatarecovery.modelo.GestorM;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named(value = "gestionMatrizManagedBean")
@RequestScoped
public class GestionMatrizManagedBean implements Serializable {

    // Otros atribu,tos y métodos de la clase
    private GestorM selectedGestorM;
    private List<GestorM> listaGestorM;
    private String opcionPregunta1;
    private String opcionPregunta2;
    private String opcionPregunta3;
    private String opcionPregunta4;
    private int sumaValores;
    private String opcionPregunta5;
    private String cambiosTabla;

    private GestorM gestorM = new GestorM();

    public GestionMatrizManagedBean() {
        selectedGestorM = new GestorM();
    }

    @PostConstruct
    public void init() {
        // Inicializa la lista de datos en el método @PostConstruct
        listaGestorM = obtenerRegistrosGestorM();
    }

    public void inicializar() {
        listaGestorM = obtenerRegistrosGestorM();
    }

    public GestorM getGestorM() {
        return gestorM;
    }

    public void setGestorM(GestorM gestorM) {
        this.gestorM = gestorM;
    }

    public int getSumaValores() {
        return sumaValores;
    }

    public void setSumaValores(int sumaValores) {
        this.sumaValores = sumaValores;
    }

    public GestorM getSelectedGestorM() {
        return selectedGestorM;
    }

    public void setSelectedGestorM(GestorM selectedGestorM) {
        this.selectedGestorM = selectedGestorM;
    }

    public List<GestorM> getListaGestorM() {
        return listaGestorM;
    }

    public void setListaGestorM(List<GestorM> listaTercerosInc) {
        this.listaGestorM = listaTercerosInc;
    }

    public String getOpcionPregunta1() {
        return opcionPregunta1;
    }

    public void setOpcionPregunta1(String opcionPregunta1) {
        this.opcionPregunta1 = opcionPregunta1;
    }

    public String getOpcionPregunta2() {
        return opcionPregunta2;
    }

    public void setOpcionPregunta2(String opcionPregunta2) {
        this.opcionPregunta2 = opcionPregunta2;
    }

    public String getOpcionPregunta3() {
        return opcionPregunta3;
    }

    public void setOpcionPregunta3(String opcionPregunta3) {
        this.opcionPregunta3 = opcionPregunta3;
    }

    public String getOpcionPregunta4() {
        return opcionPregunta4;
    }

    public void setOpcionPregunta4(String opcionPregunta4) {
        this.opcionPregunta4 = opcionPregunta4;
    }

    public String getOpcionPregunta5() {
        return opcionPregunta1;
    }

    public void setOpcionPregunta5(String opcionPregunta5) {
        this.opcionPregunta5 = opcionPregunta5;
    }

    public String getCambiosTabla() {
        return cambiosTabla;
    }

    public void setCambiosTabla(String cambiosTabla) {
        this.cambiosTabla = cambiosTabla;
    }

    public List<GestorM> obtenerRegistrosGestorM() {
        List<GestorM> data = new ArrayList<>();

        try ( Connection con = Conexion.obtenerConexion();  Statement sql = con.createStatement();  ResultSet rs = sql.executeQuery("select * from gestor_matrices_iperc")) {

            while (rs.next()) {
                GestorM gestor = new GestorM(
                        rs.getInt("id_gestor_iperc"),
                        rs.getString("tipo"),
                        rs.getString("nombre_proceso"),
                        rs.getInt("id_padre"),
                        rs.getString("nombre_subproceso"),
                        rs.getString("actividad"),
                        rs.getString("rutinaria"),
                        rs.getString("peligros"),
                        rs.getString("riesgos"),
                        rs.getString("tipo_riesgo"),
                        rs.getString("consecuencia"),
                        rs.getString("controles"),
                        rs.getString("legislacion"),
                        rs.getInt("esti_riesgo")
                );
                data.add(gestor);
            }

            Collections.sort(data, Comparator.comparingInt(GestorM::getId_gestor_iperc));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void agregarProceso() {

        selectedGestorM.setNombre_proceso(selectedGestorM.getNombre_proceso().toUpperCase()); // Convertir a mayúsculas

        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement pst = con.prepareStatement("INSERT INTO gestor_matrices_iperc(tipo, nombre_proceso) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, "P");
            pst.setString(2, selectedGestorM.getNombre_proceso());
            pst.executeUpdate();
            selectedGestorM.setNombre_proceso(selectedGestorM.getNombre_proceso().toUpperCase()); // Convertir a mayúsculas
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idProcesoPadre = generatedKeys.getInt(1); // Obtener el ID del proceso padre recién registrado
                selectedGestorM.setIdProcesoPadre(idProcesoPadre); // Almacenar el ID del proceso padre en el bean
            }

            listaGestorM = obtenerRegistrosGestorM();

            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al agregar el proceso: " + e.toString());
        }
    }

    public void agregarSubproceso() {
        calcularResultado();
        selectedGestorM.setNombre_subproceso(selectedGestorM.getNombre_subproceso().toUpperCase()); // Convertir a mayúscula

        try {
            Connection con = Conexion.obtenerConexion();
            PreparedStatement pst = con.prepareStatement("INSERT INTO gestor_matrices_iperc(tipo, nombre_subproceso, id_padre, actividad, rutinaria, peligros, riesgos, tipo_riesgo, consecuencia, controles, legislacion, esti_riesgo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pst.setString(1, "S");
            pst.setString(2, selectedGestorM.getNombre_subproceso());
            pst.setInt(3, selectedGestorM.getIdProcesoPadre()); // Usar el ID del proceso padre almacenado en el bean
            pst.setString(4, selectedGestorM.getActividad());
            pst.setString(5, selectedGestorM.getRutinaria());
            pst.setString(6, selectedGestorM.getPeligros());
            pst.setString(7, selectedGestorM.getRiesgos());
            pst.setString(8, selectedGestorM.getTipo_riesgo());
            pst.setString(9, selectedGestorM.getConsecuencia());
            pst.setString(10, selectedGestorM.getControles());
            pst.setString(11, selectedGestorM.getLegislacion());
            pst.setInt(12, selectedGestorM.getEsti_riesgo());
            pst.executeUpdate();

            selectedGestorM.setCantidadSubprocesos(selectedGestorM.getCantidadSubprocesos() + 1);

            listaGestorM = obtenerRegistrosGestorM();

            pst.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al agregar el subproceso: " + e.toString());
        }
    }

    public void eliminarRegistro() {
        if (selectedGestorM != null) {
            try ( Connection conn = Conexion.obtenerConexion();  Statement sql = conn.createStatement()) {
                String query = "DELETE FROM gestor_matrices_iperc WHERE id_gestor_iperc = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, selectedGestorM.getId_gestor_iperc());
                int rowCount = pst.executeUpdate(); // Agregar esta línea para obtener el número de filas afectadas
                System.out.println("Número de filas afectadas por la eliminación: " + rowCount);

                // Actualizar la lista de registros después de eliminar uno
                listaGestorM = obtenerRegistrosGestorM();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void calcularResultado() {
        // Obtener los valores numéricos correspondientes a las opciones seleccionadas en las 4 primeras preguntas
        int valorPregunta1 = Integer.parseInt(opcionPregunta1); // Debes mapear las opciones a valores numéricos
        int valorPregunta2 = Integer.parseInt(opcionPregunta2);
        int valorPregunta3 = Integer.parseInt(opcionPregunta3);
        int valorPregunta4 = Integer.parseInt(opcionPregunta4);

        // Obtener el valor numérico correspondiente a la opción seleccionada en la quinta pregunta
        int valorPregunta5 = Integer.parseInt(opcionPregunta5); // Debes mapear las opciones a valores numéricos

        // Calcular la suma de los valores de las 4 primeras preguntas
        sumaValores = valorPregunta1 + valorPregunta2 + valorPregunta3 + valorPregunta4;

        // Calcular el resultado final multiplicando la suma de los valores con la opción de la quinta pregunta
        int esti_riesgo = sumaValores * valorPregunta5;

        // Asignar el valor calculado a selectedGestorM
        selectedGestorM.setEsti_riesgo(esti_riesgo);
    }
}

