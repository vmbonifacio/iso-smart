/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.perudatarecovery.configuracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author LOURDES
 */
@Named(value = "informacionAdicionalManagedBean")
@RequestScoped
public class InformacionAdicionalManagedBean {

    private int idOcurrencia;
    private String nombreOcurrencia;
    private String tipoOcurrencia;
    private int numPersonas;

    /**
     * Creates a new instance of InformacionAdicionalManagedBean
     */
    public InformacionAdicionalManagedBean() {
    }

    public void init() {
        // Cargar los datos del registro desde la base de datos utilizando el idOcurrencia
        // Actualiza las propiedades nombreOcurrencia y tipoOcurrencia con los valores obtenidos
    }

    public void guardarInformacionAdicional() {
        try (
                 Connection con = Conexion.obtenerConexion();  PreparedStatement pst = con.prepareStatement("UPDATE ocurrencia SET numPersonas = ? WHERE idOcurrencia = ?")) {

            pst.setInt(1, numPersonas);
            pst.setInt(2, idOcurrencia);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al guardar la información adicional: " + e.toString());
        }
    }

}
