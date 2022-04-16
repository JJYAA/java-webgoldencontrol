package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaDb;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.util.Util;

public class UbigeoDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    public List<ListaGenerica> obtenerDepartamento(String codigoDepartamento) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaSQL().connection();
        Statement stmt = null;
        List<ListaGenerica> list = new ArrayList<>();
        ListaGenerica obj = null;

        String sql = "select c_c_depa indice, nombre descripcion from ubigeo where c_c_prov='00' and c_c_dist='00'";
        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    obj = new ListaGenerica();
                    obj.setIndice(rs.getString("indice"));
                    obj.setDescripcion(rs.getString("descripcion"));
                    list.add(obj);
                }

            } catch (SQLException e) {
                LOGGER.error("GP.ERROR: {}", e);
            } finally {
                try {
                    conexion.close(); // libera conexion
                } catch (Exception e) {
                }

                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception e) {
                }
            }
        }

        return list;
    }

    public List<ListaGenerica> obtenerProvincia(String codigoDepartamento, String codigoProvincia) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaSQL().connection();
        Statement stmt = null;
        List<ListaGenerica> list = new ArrayList<>();
        ListaGenerica obj = null;

        String sql = "select c_c_depa indice, nombre descripcion from ubigeo where c_c_depa='" + codigoDepartamento + "' and c_c_dist='00' and c_c_prov<>'00'";
        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    obj = new ListaGenerica();
                    obj.setIndice(rs.getString("indice"));
                    obj.setDescripcion(rs.getString("descripcion"));
                    list.add(obj);
                }

            } catch (SQLException e) {
                LOGGER.error("GP.ERROR: {}", e);
            } finally {
                try {
                    conexion.close(); // libera conexion
                } catch (Exception e) {
                }

                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception e) {
                }
            }
        }

        return list;
    }

    public List<ListaGenerica> obtenerDistrito(String codigoDepartamento, String codigoProvincia, String codigoDistrito) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaSQL().connection();
        Statement stmt = null;
        List<ListaGenerica> list = new ArrayList<>();
        ListaGenerica obj = null;

        String sql = "select c_c_depa indice, nombre descripcion from ubigeo where c_c_depa='" + codigoDepartamento + "' and c_c_prov='" + codigoProvincia + "'  and c_c_dist<>'00' ";
        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    obj = new ListaGenerica();
                    obj.setIndice(rs.getString("indice"));
                    obj.setDescripcion(rs.getString("descripcion"));
                    list.add(obj);
                }

            } catch (SQLException e) {
                LOGGER.error("GP.ERROR: {}", e);
            } finally {
                try {
                    conexion.close(); // libera conexion
                } catch (Exception e) {
                }

                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (Exception e) {
                }
            }
        }

        return list;
    }

    public String obtenerCodigoPostal(String ubigeo) throws Exception {
        ResultSet rs = null;
        Connection cn = new ConectaDb().connection();
        Statement stmt = null;
        String codigoPostal = "P99";
        String sql = "SELECT "
                + "ccp_cod_pos "
                + "FROM panaautos.cab_cod_pos "
                + "WHERE ccp_ubi_geo = '" + ubigeo + "'";
        if (cn != null) {
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    codigoPostal = rs.getString("ccp_cod_pos");
                }
            } catch (Exception e) {
                throw e;
            } finally {
                Util.close(cn);
                Util.close(stmt);
                Util.close(rs);
            }
        }

        return codigoPostal;
    }

    public String obtenerNombreUbigeo(String ubigeo, String tipo) throws Exception {
        String fn = "{?=call panaautos.pkg_generales.fn_ubigeo(?,?)}";
        Object[] paramIN = {tipo, ubigeo};
        String result = Util.nullCad(Util.fn_ejecuta(fn, OracleTypes.VARCHAR, paramIN));
        return result;
    }

    public List<ListaGenerica> listaDepartamentos() throws Exception {
        ArrayList<ListaGenerica> listaDepartamentos = new ArrayList<>();
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call PANAAUTOS.PKG_SERVICIOS_WEB.GetDpto(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                ListaGenerica obj = new ListaGenerica();
                obj.setIndice(rs.getString("indice"));
                obj.setDescripcion(rs.getString("descripcion"));
                listaDepartamentos.add(obj);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(cs);
            Util.close(rs);
        }
        return listaDepartamentos;
    }

    public List<ListaGenerica> listaProvincias(String Departamento) throws Exception {
        ArrayList<ListaGenerica> listaProvincias = new ArrayList<>();
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call web_GetProv(?)}");
            cs.setString(1, Departamento);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                ListaGenerica obj = new ListaGenerica();
                obj.setIndice(rs.getString("indice"));
                obj.setDescripcion(rs.getString("descripcion"));
                listaProvincias.add(obj);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(cs);
            Util.close(rs);
        }
        return listaProvincias;
    }

    public List<ListaGenerica> listaDistritos(String Departamento, String Provincia) throws Exception {
        ArrayList<ListaGenerica> listaDistritos = new ArrayList<>();
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call web_GetDistrito(?,?)}");
            cs.setString(1, Departamento);
            cs.setString(2, Provincia);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                ListaGenerica obj = new ListaGenerica();
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setIndice(rs.getString("indice"));
                listaDistritos.add(obj);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(cs);
            Util.close(rs);
        }
        return listaDistritos;
    }

    public List listaDistritosPostal() throws Exception {
        ArrayList<ListaGenerica> lista = new ArrayList<>();
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call panaautos.pkg_servicios_web.sp_Lista_CodPostal(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);
            while (rs.next()) {
                ListaGenerica list = new ListaGenerica();
                list.setIndice(rs.getString("codigo"));
                list.setDescripcion(rs.getString("descripcion"));
                lista.add(list);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(cs);
            Util.close(rs);
        }
        return lista;
    }
}
