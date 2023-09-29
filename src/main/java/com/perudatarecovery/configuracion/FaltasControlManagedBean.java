package com.perudatarecovery.configuracion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class FaltasControlManagedBean {

    private List<String> listaComponentes = new ArrayList<>();
    private List<String> listaComponentesPlanificacion = new ArrayList<>();
    private List<String> listaComponentesMedidas = new ArrayList<>();
    private List<String> listaComponentesPreparacion = new ArrayList<>();
    private List<String> listaComponentesVerificacion = new ArrayList<>();
    private List<String> listaComponentesControlOperacional = new ArrayList<>();

    private List<String> listaIndicadores = new ArrayList<>();
    private List<String> listaIndicadoresPlanificacion = new ArrayList<>();
    private List<String> listaIndicadoresMedidas = new ArrayList<>();
    private List<String> listaIndicadoresPreparacion = new ArrayList<>();
    private List<String> listaIndicadoresVerificacion = new ArrayList<>();
    private List<String> listaIndicadoresControlOperacional = new ArrayList<>();

    public FaltasControlManagedBean() {
        obtenerListaComponentes();
        obtenerListaComponentesPlanificacion();
        obtenerListaComponentesMedidas();
        obtenerListaComponentesPreparacion();
        obtenerListaComponentesVerificacion();
        obtenerListaComponentesControlOperacional();

        obtenerListaIndicadores();
        obtenerListaIndicadoresPlanificacion();
        obtenerListaIndicadoresMedidas();
        obtenerListaIndicadoresPreparacion();
        obtenerListaIndicadoresVerificacion();
        obtenerListaIndicadoresControlOperacional();
    }

    private void obtenerListaComponentes() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerLista_Id_Control(?)}");
            cs.setInt(1, obtenerIdLineamiento()); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Faltas_Control");
                listaComponentes.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Faltas_Control: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void obtenerListaIndicadores() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerLista_Det_Id_Control(?)}");
            cs.setInt(1, obtenerIdLineamiento()); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Faltas_Control_Detalle");
                listaIndicadores.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //***************************COMPROMISOS************************************//
    private void obtenerListaComponentesPlanificacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerLista_Id_Control(?)}");
            cs.setInt(1, 2); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Faltas_Control");
                listaComponentesPlanificacion.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Faltas_Control: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void obtenerListaIndicadoresPlanificacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerLista_Det_Id_Control(?)}");
            cs.setInt(1, 2); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Faltas_Control_Detalle");
                listaIndicadoresPlanificacion.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //****************************POLITICA DE SEGURIDAD *************************************//
    private void obtenerListaComponentesMedidas() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerLista_Id_Control(?)}");
            cs.setInt(1, 3); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Faltas_Control");
                listaComponentesMedidas.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Faltas_Control: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void obtenerListaIndicadoresMedidas() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerLista_Det_Id_Control(?)}");
            cs.setInt(1, 3); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Faltas_Control_Detalle");
                listaIndicadoresMedidas.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //***************************PLANEAMINETO Y APLICACION*******************************//
    private void obtenerListaComponentesPreparacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerLista_Id_Control(?)}");
            cs.setInt(1, 4); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Faltas_Control");
                listaComponentesPreparacion.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Faltas_Control: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void obtenerListaIndicadoresPreparacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerLista_Det_Id_Control(?)}");
            cs.setInt(1, 4); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Faltas_Control_Detalle");
                listaIndicadoresPreparacion.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //***************************IMPLEMENTACION  Y OPERACION *******************************//
    private void obtenerListaComponentesVerificacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerLista_Id_Control(?)}");
            cs.setInt(1, 5); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Faltas_Control");
                listaComponentesVerificacion.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Faltas_Control: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void obtenerListaIndicadoresVerificacion() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerLista_Det_Id_Control(?)}");
            cs.setInt(1, 5); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Faltas_Control_Detalle");
                listaIndicadoresVerificacion.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //*************************** CONTROL DE LA INFROMACION Y DOCUMENTOS*******************************//
    private void obtenerListaComponentesControlOperacional() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerLista_Id_Control(?)}");
            cs.setInt(1, 6); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreComponente = rs.getString("Nombre_Faltas_Control");
                listaComponentesControlOperacional.add(nombreComponente);

                // Agregar registro de depuración
                System.out.println("Nombre_Faltas_Control: " + nombreComponente);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void obtenerListaIndicadoresControlOperacional() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = Conexion.obtenerConexion();
            cs = conn.prepareCall("{call SP_ObtenerLista_Det_Id_Control(?)}");
            cs.setInt(1, 6); // Obtener el id_Lineamiento que corresponda

            rs = cs.executeQuery();

            while (rs.next()) {
                String nombreIndicador = rs.getString("Faltas_Control_Detalle");
                listaIndicadoresControlOperacional.add(nombreIndicador);
            }
        } catch (SQLException e) {
            // Manejo de errores, por ejemplo, imprimir el error
            e.printStackTrace();
        } finally {
            // Cerrar recursos (ResultSet, CallableStatement, Connection) en un bloque finally
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Método para obtener el id_Lineamiento
    private int obtenerIdLineamiento() {
        // Lógica para obtener el id_Lineamiento, por ejemplo, usando otro procedimiento almacenado
        return 1; // Debes adaptar esta lógica según tus necesidades
    }

    public List<String> getListaComponentes() {
        return listaComponentes;
    }

    public void setListaComponentes(List<String> listaComponentes) {
        this.listaComponentes = listaComponentes;
    }

    public List<String> getListaComponentesPlanificacion() {
        return listaComponentesPlanificacion;
    }

    public void setListaComponentesPlanificacion(List<String> listaComponentesPlanificacion) {
        this.listaComponentesPlanificacion = listaComponentesPlanificacion;
    }

    public List<String> getListaComponentesMedidas() {
        return listaComponentesMedidas;
    }

    public void setListaComponentesMedidas(List<String> listaComponentesMedidas) {
        this.listaComponentesMedidas = listaComponentesMedidas;
    }

    public List<String> getListaComponentesPreparacion() {
        return listaComponentesPreparacion;
    }

    public void setListaComponentesPreparacion(List<String> listaComponentesPreparacion) {
        this.listaComponentesPreparacion = listaComponentesPreparacion;
    }

    public List<String> getListaComponentesVerificacion() {
        return listaComponentesVerificacion;
    }

    public void setListaComponentesVerificacion(List<String> listaComponentesVerificacion) {
        this.listaComponentesVerificacion = listaComponentesVerificacion;
    }

    public List<String> getListaComponentesControlOperacional() {
        return listaComponentesControlOperacional;
    }

    public void setListaComponentesControlOperacional(List<String> listaComponentesControlOperacional) {
        this.listaComponentesControlOperacional = listaComponentesControlOperacional;
    }

    public List<String> getListaIndicadores() {
        return listaIndicadores;
    }

    public void setListaIndicadores(List<String> listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }

    public List<String> getListaIndicadoresPlanificacion() {
        return listaIndicadoresPlanificacion;
    }

    public void setListaIndicadoresPlanificacion(List<String> listaIndicadoresPlanificacion) {
        this.listaIndicadoresPlanificacion = listaIndicadoresPlanificacion;
    }

    public List<String> getListaIndicadoresMedidas() {
        return listaIndicadoresMedidas;
    }

    public void setListaIndicadoresMedidas(List<String> listaIndicadoresMedidas) {
        this.listaIndicadoresMedidas = listaIndicadoresMedidas;
    }

    public List<String> getListaIndicadoresPreparacion() {
        return listaIndicadoresPreparacion;
    }

    public void setListaIndicadoresPreparacion(List<String> listaIndicadoresPreparacion) {
        this.listaIndicadoresPreparacion = listaIndicadoresPreparacion;
    }

    public List<String> getListaIndicadoresVerificacion() {
        return listaIndicadoresVerificacion;
    }

    public void setListaIndicadoresVerificacion(List<String> listaIndicadoresVerificacion) {
        this.listaIndicadoresVerificacion = listaIndicadoresVerificacion;
    }

    public List<String> getListaIndicadoresControlOperacional() {
        return listaIndicadoresControlOperacional;
    }

    public void setListaIndicadoresControlOperacional(List<String> listaIndicadoresControlOperacional) {
        this.listaIndicadoresControlOperacional = listaIndicadoresControlOperacional;
    }
}
