package com.perudatarecovery.configuracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "ubigeo")
@ViewScoped
public class Ubigeo {
    private List<String> listaDepartamentos;
    private List<String> listaProvincias;
    private List<String> listaDistritos;
    private String departamentoSeleccionado;
    private String provinciaSeleccionada;
    private String distritoSeleccionado;

    @PostConstruct
    public void init() {
        obtenerListaDepartamentos();
    }

    public void obtenerListaDepartamentos() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Conexion.obtenerConexion();
            statement = connection.prepareStatement("EXEC SP_ObtenerListaDepartamentos");
            resultSet = statement.executeQuery();

            listaDepartamentos = new ArrayList<>();

            while (resultSet.next()) {
                String departamento = resultSet.getString("Nombre_Departamento");
                listaDepartamentos.add(departamento);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de departamentos: " + e.getMessage());
        } finally {
            closeResources(resultSet, statement, connection);
        }
    }

    public void obtenerListaProvincias(String departamento) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Conexion.obtenerConexion();
            int codigoDepartamento = obtenerCodigoDepartamento(departamento);

            statement = connection.prepareStatement("EXEC SP_ObtenerListaProvincias ?");
            statement.setInt(1, codigoDepartamento);
            resultSet = statement.executeQuery();

            listaProvincias = new ArrayList<>();

            while (resultSet.next()) {
                String provincia = resultSet.getString("Nombre_Provincia");
                listaProvincias.add(provincia);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de provincias: " + e.getMessage());
        } finally {
            closeResources(resultSet, statement, connection);
        }
    }

    public void obtenerListaDistritos(String provincia) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Conexion.obtenerConexion();
            int codigoProvincia = obtenerCodigoProvincia(provincia);

            statement = connection.prepareStatement("EXEC SP_ObtenerListaDistritos ?");
            statement.setInt(1, codigoProvincia);
            resultSet = statement.executeQuery();

            listaDistritos = new ArrayList<>();

            while (resultSet.next()) {
                String distrito = resultSet.getString("Nombre_Distrito");
                listaDistritos.add(distrito);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la lista de distritos: " + e.getMessage());
        } finally {
            closeResources(resultSet, statement, connection);
        }
    }

    public int obtenerCodigoDepartamento(String nombreDepartamento) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int codigoDepartamento = 0;

        try {
            connection = Conexion.obtenerConexion();
            statement = connection.prepareStatement("EXEC SP_ObtenerCodigoDepartamento ?");
            statement.setString(1, nombreDepartamento);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                codigoDepartamento = resultSet.getInt("id_Departamento");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el código del departamento: " + e.getMessage());
        } finally {
            closeResources(resultSet, statement, connection);
        }

        return codigoDepartamento;
    }

    public int obtenerCodigoProvincia(String nombreProvincia) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int codigoProvincia = 0;

        try {
            connection = Conexion.obtenerConexion();
            statement = connection.prepareStatement("EXEC SP_ObtenerCodigoProvincia ?");
            statement.setString(1, nombreProvincia);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                codigoProvincia = resultSet.getInt("id_Provincia");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el código de la provincia: " + e.getMessage());
        } finally {
            closeResources(resultSet, statement, connection);
        }

        return codigoProvincia;
    }

    private void closeResources(ResultSet resultSet, PreparedStatement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar los recursos: " + e.getMessage());
        }
    }

    public List<String> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public List<String> getListaProvincias() {
        return listaProvincias;
    }

    public List<String> getListaDistritos() {
        return listaDistritos;
    }

    public String getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(String departamento) {
        this.departamentoSeleccionado = departamento;
        obtenerListaProvincias(departamentoSeleccionado);
        listaDistritos = new ArrayList<>();
    }

    public String getProvinciaSeleccionada() {
        return provinciaSeleccionada;
    }

    public void setProvinciaSeleccionada(String provincia) {
        this.provinciaSeleccionada = provincia;
        if (provinciaSeleccionada != null && !provinciaSeleccionada.isEmpty()) {
            obtenerListaDistritos(provinciaSeleccionada);
        }
    }

    public String getDistritoSeleccionado() {
        return distritoSeleccionado;
    }

    public void setDistritoSeleccionado(String distrito) {
        this.distritoSeleccionado = distrito;
    }
}
