package pe.com.gp.dao;

import pe.com.gp.connection.ConectaDb;
import pe.com.gp.entity.Usuario;
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
import pe.com.gp.connection.ConectaHana;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.BeanPreProvision;
import pe.com.gp.util.Util;

public class AutenticaDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    public String codigoEmpleadoVenta(String usuario) throws Exception {
        String result = "-1";
        Connection cn = new ConectaHana().connection();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT "
                + "OHEM.\"salesPrson\","
                + "OHEM.\"U_SYP_NRODOCIDE\" "
                + "FROM \"OUSR\" OUSR "
                + "INNER JOIN \"OHEM\" OHEM "
                + "ON OUSR.\"USERID\" = OHEM.\"userId\" "
                + "WHERE UCASE(OUSR.\"USER_CODE\")=UCASE('" + usuario + "')";
        if (cn != null) {
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    result = rs.getString("salesPrson");
                }
            } catch (Exception e) {
                throw e;
            } finally {
                Util.close(cn);
                Util.close(rs);
                Util.close(stmt);
            }
        }
        return result;
    }

    public Usuario autenticaSAP(String usuario, String clave) throws Exception {
        Usuario user = new Usuario();
        Connection cn = new ConectaSQL().connection();
        Statement stmt = null;
        ResultSet rs = null;
        String sql  = "SELECT '09850505' CODIGO,'JOSE LUIS' NOMBRE";
        if (cn != null) {
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    user = new Usuario();
                    user.setDocidentidad(rs.getString("CODIGO"));
                    user.setHabilitado("1");
                    user.setExiste(true);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                Util.close(cn);
                Util.close(rs);
                Util.close(stmt);
            }
        }

        return user;
    }
    
    public Usuario  autenticaWEB(String empresa, String usuario, String clave) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Usuario user = new Usuario();         
        try { 
            cs = cn.prepareCall("{call relaciones.dbo.web_autentica(?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, usuario); 
            cs.setString(3, clave); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                    user.setDocidentidad(rs.getString("USER_CODE"));
                    user.setCodigo(rs.getString("USER_CODE"));
                    user.setFullname(rs.getString("U_NAME"));
                    user.setNombre(rs.getString("U_NAME"));
                    user.setRucEmpresa(rs.getString("ruc"));
                    user.setCodTieLog(empresa);
                    user.setCodTienda(empresa);
                    user.setExiste(true);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return user;
    }
    

   /* public Usuario autenticaWEB(String tienda, String usuario, String clave) throws Exception {
        Usuario user = new Usuario();
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        Statement stmt = null;
        String sql = "SELECT  '09850505' USER_CODE, 'JOSE LUIS' U_NAME";                
        if (cn != null) {
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    user.setDocidentidad(rs.getString("USER_CODE"));
                    user.setCodigo(rs.getString("USER_CODE"));
                    user.setFullname(rs.getString("U_NAME"));
                    user.setNombre(rs.getString("U_NAME"));
                    user.setExiste(true);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                Util.close(cn);
                Util.close(rs);
                Util.close(stmt);
            }
        }
        return user;
    }*/

    public Usuario autentica(String tienda, String clave, String sistema) {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Usuario user = null;

        if (cn != null) {
            try {
                cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.sp_autentica(?,?,?,?)}");
                //cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.sp_autenticacion(?,?,?,?,?)}");
                cs.setString(1, tienda);
                cs.setString(2, clave);
                cs.setString(3, sistema);
                cs.registerOutParameter(4, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(4);

                while (rs.next()) {
                    user = new Usuario();
                    user.setDocidentidad(rs.getString("docidentidad")); // c_c_codigo
                    user.setCodigo(rs.getString("docidentidad")); // c_c_codigo
                    user.setPassword(rs.getString("password"));
                    user.setFullname(rs.getString("fullname"));
                    user.setNombre(rs.getString("fullname"));
                    user.setEmail(rs.getString("email"));
                    user.setSexo(rs.getString("sexo"));
                    user.setCargo(rs.getString("cargo"));
                    user.setApaterno(rs.getString("apaterno"));
                    user.setAmaterno(rs.getString("amaterno"));
                    user.setPnombre(rs.getString("pnombre"));
                    user.setPrimerNombre(rs.getString("pnombre"));
                    user.setSnombre(rs.getString("snombre"));
                    user.setCodTienda(rs.getString("codigotienda"));
                    user.setNomTienda(rs.getString("nombretienda"));
                    user.setCodTieLog(rs.getString("codigotiendalogin"));
                    user.setNomTieLog(rs.getString("nombretiendalogin"));
                    user.setEstado(rs.getString("estado"));
                    user.setCentrocostos(rs.getString("centrocostos"));
                    user.setPrivilegio1(rs.getString("c_c_privilegio1"));
                    user.setPrivilegio2(rs.getString("c_c_privilegio2"));
                    user.setPrivilegio3(rs.getString("c_c_privilegio3"));
                    user.setGerencia(rs.getString("gerencia"));
                    user.setHabilitado(rs.getString("habilitado"));
                    user.setTienda(rs.getString("tienda"));
                    user.setDsctoTrabajador(rs.getDouble("dsctoUsr"));
                    user.setCod_emp_gen(rs.getString("cod_emp_gen"));
                    user.setRuc_emp(rs.getString("ceg_doc_emp"));
                    user.setCodigoPerfil(rs.getInt("codigoPerfil"));
                    user.setNombrePerfil(rs.getString("nombrePerfil"));
                    user.setCodigoCargo(rs.getString("codCargo"));
                    user.setCanalVenta(rs.getString("canal"));
                    user.setTiendaSAP(rs.getString("tienda_sap"));
                }

            } catch (Exception e) {
                LOGGER.error("GP.ERROR: {}", e);
            } finally {
                try {
                    cn.close(); // libera cn
                } catch (Exception e) {
                }

                try {
                    if (cs != null) {
                        cs.close();
                    }
                } catch (Exception e) {
                }

                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                }
            }
        }

        return user;
    }

//    public Usuario autentica(String usuario, String clave, String codigoTienda, String idSistema) throws Exception {
//        Connection cn = new ConectaDb().connection();
//        CallableStatement cs = null;
//        ResultSet rs = null;
//        Usuario user = null;
//
//        if (cn != null) {
//            try {
//                cs = cn.prepareCall("{call PANAAUTOS.PKG_UTIL.SP_AUTENTICA(?,?,?,?,?)}");
//                cs.setString(1, usuario);
//                cs.setString(2, clave);
//                cs.setString(3, codigoTienda);
//                cs.setString(4, idSistema);
//                cs.registerOutParameter(5, OracleTypes.CURSOR);
//                cs.execute();
//                rs = (ResultSet) cs.getObject(5);
//
//                while (rs.next()) {
//                    user = new Usuario();
//                    user.setCodigo(rs.getString("codigo"));
//                    user.setDocidentidad(rs.getString("docidentidad"));
//                    user.setPassword(rs.getString("password"));
//                    user.setFullname(rs.getString("nombre"));
//                    user.setNombre(rs.getString("nombre"));
//                    user.setEmail(rs.getString("correo"));
//                    user.setSexo(rs.getString("sexo"));
//                    user.setCargo(rs.getString("cargo"));
//                    user.setApaterno(rs.getString("apePaterno"));
//                    user.setAmaterno(rs.getString("apeMaterno"));
//                    user.setPnombre(rs.getString("priNombre"));
//                    user.setPrimerNombre(rs.getString("priNombre"));
//                    user.setSnombre(rs.getString("segNombre"));
//                    user.setCodTienda(rs.getString("codigoTienda"));
//                    user.setNomTienda(rs.getString("nombreTienda"));
//                    user.setCodTieLog(rs.getString("codigoTiendaLogin"));
//                    user.setNomTieLog(rs.getString("nombreTiendaLogin"));
//                    user.setEstado(rs.getString("habilitado"));
//                    user.setCentrocostos(rs.getString("centroCostos"));
//                    user.setPrivilegio1(rs.getString("privilegio1"));
//                    user.setPrivilegio2(rs.getString("privilegio2"));
//                    user.setPrivilegio3(rs.getString("privilegio3"));
//                    user.setGerencia(rs.getString("tipoUsuario"));
//                    user.setHabilitado(rs.getString("habilitado"));
//                    user.setTienda(rs.getString("codigoTienda"));
//                    user.setDsctoTrabajador(rs.getDouble("descuento"));
//                    user.setCodigoPerfil(rs.getLong("codigoPerfil"));
//                    user.setNombrePerfil(rs.getString("nombrePerfil"));
//                    user.setCod_emp_gen(rs.getString("cod_emp_gen"));
//                    user.setRuc_emp(rs.getString("ceg_doc_emp"));
//                }
//
//            } catch (Exception e) {
//                throw new Exception("GP.ERROR: " + e);
//            } finally {
//                try {
//                    cn.close(); // libera cn
//                } catch (Exception e) {
//                }
//
//                try {
//                    if (cs != null) {
//                        cs.close();
//                    }
//                } catch (Exception e) {
//                }
//
//                try {
//                    if (rs != null) {
//                        rs.close();
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }
//        return user;
//    }   
    public List<String> opcMenusConAcceso(String rucEmpresa, String codUsuLog, String idSis) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<String> list = new ArrayList<>();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.perfil_ListaAccesosOpciones(?,?,?)}");
            cs.setString(1, rucEmpresa);
            cs.setString(2, codUsuLog);  
            cs.setString(3, idSis);  
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                String item = Util.nullCad(rs.getString("c_t_opcion"));
                list.add(item);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return list;        
        
        
        
    }

    /*public int validaAcceso(String tienda, String usuario, String menu) {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        int tieneAcceso = 0;

        String sql = "SELECT dag_nom_prg "
                + "FROM det_acc_gen2 "
                + "WHERE dag_cod_emp = TRIM(UPPER('" + Util.nullCad(tienda) + "')) "
                + "AND dag_cod_usr = TRIM('" + Util.nullCad(usuario) + "') "
                + "AND dag_nom_prg = TRIM(UPPER('" + Util.nullCad(menu) + "')) "
                + "AND dag_sistema = '06'";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    tieneAcceso = 1;
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

        return tieneAcceso;
    }*/
    public String insertarLogAccesos(String codTieLog, String ip, String codUsrLog, String opcion, String sistema, String form) {
        LOGGER.info("<==== Inicio Metodo : insertarLogAccesos ====>");
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        String result = null;
        if (cn != null) {
            try {
                cs = cn.prepareCall("{call panaautos.sp_log_usuarios(?,?,?,?,?,?)}");
                cs.setString(1, codTieLog);
                cs.setString(2, ip);
                cs.setString(3, codUsrLog);
                cs.setString(4, opcion);
                cs.setString(5, sistema);
                cs.setString(6, form);
                cs.execute();
            } catch (Exception e) {
                LOGGER.error("GP.ERROR: {}", e);
                result = "" + e;
            } finally {
                try {
                    cn.close(); // libera cn
                } catch (Exception e) {
                }
                try {
                    if (cs != null) {
                        cs.close();
                    }
                } catch (Exception e) {
                }
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception e) {
                }
            }
        }
        LOGGER.info("<==== Fin Metodo : insertarLogAccesos ====>");
        return result;
    }

    public Usuario usuarioTercero(String codigo) throws Exception {
        ResultSet rs = null;
        Connection cn = new ConectaDb().connection();
        Statement stmt = null;
        Usuario obj = new Usuario();
        String sql = "SELECT "
                + "par_desc, "
                + "par_val1 "
                + "FROM parametro "
                + "WHERE par_id = 'PERS' "
                + "AND par_tipo = 'TERCE' "
                + "AND par_desc = '" + codigo + "' "
                + "AND par_ser_sn = 'N'";
        if (cn != null) {
            try {
                stmt = cn.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    obj.setCodigo(rs.getString("par_desc"));
                    obj.setNomTieLog(rs.getString("par_val1"));
                    obj.setExiste(true);
                }
            } catch (Exception e) {
                throw e;
            } finally {
                Util.close(cn);
                Util.close(rs);
                Util.close(stmt);
            }
        }
        return obj;
    }
}
