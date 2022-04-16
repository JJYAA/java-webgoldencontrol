package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaDb;
import pe.com.gp.entity.Persona;
import pe.com.gp.util.Util;

public class PersonalDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    public Persona obtienePersonalPorCodigo(String codigo) {
        LOGGER.info("<==== Inicio Metodo : obtienePersonalPorCodigo ====>");
        LOGGER.trace("codigo: {}", codigo);

        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Persona persona = new Persona();

        if (cn != null) {
            try {
                cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.sp_devuelveDatosPersonal(?,?)}");
                cs.setString(1, codigo);
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(2);

                while (rs.next()) {
                    persona.setCodigo(rs.getString("c_c_codigo"));
                    persona.setNombre(rs.getString("c_t_nombre"));
                    persona.setPriNombre(rs.getString("c_t_primer_nombre"));
                    persona.setSegNombre(rs.getString("c_t_segundo_nombre"));
                    persona.setApePaterno(rs.getString("c_t_apellido_paterno"));
                    persona.setApeMaterno(rs.getString("c_t_apellido_materno"));
                    persona.setExiste(true);
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

        LOGGER.info("<==== Inicio Fin : obtienePersonalPorCodigo ====>");
        return persona;
    }

    public List<Persona> listaPersonas(String habilitado, String situacion) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Persona> lista = new ArrayList<>();

        if (cn != null) {
            try {
                cs = cn.prepareCall("{call PANAAUTOS.PKG_UTIL.SP_LISTA_PERSONAS(?,?,?)}");
                cs.setString(1, habilitado);
                cs.setString(2, situacion);
                cs.registerOutParameter(3, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(3);

                while (rs.next()) {
                    Persona list = new Persona();
                    list.setCodigo(rs.getString("C_C_CODIGO"));
                    list.setNombre(rs.getString("C_T_NOMBRE"));
                    list.setLibretaElectoral(rs.getString("C_C_LIB_ELECTORAL"));
                    list.setDirecion(rs.getString("C_T_DIRECCION"));
                    list.setDistrito(rs.getString("C_T_DISTRITO"));
                    list.setCiudad(rs.getString("C_T_CIUDAD"));
                    list.setTelefono(rs.getString("C_C_TELF"));
                    list.setCarnetSeguro(rs.getString("C_C_CARNET_SEGURO"));
                    list.setAfp(rs.getString("C_C_AFP"));
                    list.setMontoBasico(rs.getBigDecimal("N_I_MONTO_BASICO"));
                    list.setIngreso(rs.getString("D_INGRESO"));
                    list.setGrupo(rs.getString("C_C_GRUPO"));
                    list.setNacimiento(rs.getString("C_FECHA_NAC"));
                    list.setSituacion(rs.getString("C_ST_SITUACION"));
                    list.setSexo(rs.getString("C_T_SEXO"));
                    list.setCargo(rs.getString("C_T_CARGO"));
                    list.setArea(rs.getString("C_C_AREA"));
                    list.setDepartamento(rs.getString("C_C_DPTO"));
                    list.setBrevete(rs.getString("C_C_BREVETE"));
                    list.setCorreo(rs.getString("C_T_MAIL"));
                    list.setUserCorreo(rs.getString("C_T_NOM_MAIL"));
                    list.setApePaterno(rs.getString("C_T_APELLIDO_PATERNO"));
                    list.setApeMaterno(rs.getString("C_T_APELLIDO_MATERNO"));
                    list.setPriNombre(rs.getString("C_T_PRIMER_NOMBRE"));
                    list.setSegNombre(rs.getString("C_T_SEGUNDO_NOMBRE"));
                    list.setActividad(rs.getString("C_C_ACTIVIDAD"));
                    list.setCodEmpresa(rs.getString("C_C_COD_EMP"));
                    list.setPlaca(rs.getString("C_C_PLACA"));
                    list.setMarca(rs.getString("C_T_MARCA"));
                    list.setHabilitado(rs.getString("C_ST_HABILITADO"));
                    list.setCese(rs.getString("D_CESE"));
                    list.setPuesto(rs.getString("C_C_PUESTO"));
                    list.setCargo(rs.getString("C_C_CARGO"));
                    list.setFlgWeb(rs.getString("C_FL_WEB"));
                    lista.add(list);
                }

            } catch (Exception e) {
                throw new Exception("GP.ERROR: " + e);
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
        return lista;
    }

    public String getNombrePersona(String codigo) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        String nombre = "";

        if (cn != null) {
            try {
                cs = cn.prepareCall("{?=call panaautos.pkg_generales.fn_nom_person(?)}");
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.setString(2, codigo);
                cs.execute();
                nombre = cs.getString(1);

            } catch (Exception e) {
                throw new Exception("GP.ERROR: " + e);
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
        return nombre;
    }

    public String getCorreoPersona(String codigo) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        String correo = "";

        if (cn != null) {
            try {
                cs = cn.prepareCall("{?=call panaautos.pkg_generales.FN_MAIL_EMPLEADO(?)}");
                cs.registerOutParameter(1, OracleTypes.VARCHAR);
                cs.setString(2, codigo);
                cs.execute();
                correo = cs.getString(1);

            } catch (Exception e) {
                throw new Exception("GP.ERROR: " + e);
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
        return correo;
    }

    public Persona obtenerDatosPersonal(String codigo) throws Exception {
        /*
        String sql = "SELECT "
                + "TRIM(p.c_c_codigo) AS c_c_codigo,"
                + "TRIM(p.c_t_nombre) AS c_t_nombre,"
                + "TRIM(p.c_c_lib_electoral) AS c_c_lib_electoral,"
                + "p.c_t_direccion,"
                + "p.c_t_distrito,"
                + "p.c_t_ciudad,"
                + "p.c_c_telf,"
                + "p.c_c_carnet_seguro,"
                + "p.c_c_afp,"
                + "p.n_i_monto_basico,"
                + "p.d_ingreso,"
                + "p.c_c_grupo,"
                + "p.c_fecha_nac,"
                + "p.c_st_situacion,"
                + "p.c_t_sexo,"
                + "p.c_t_cargo,"
                + "initcap(c_t_cargo) AS cargoInitCap,"
                + "p.c_c_area,"
                + "p.c_c_dpto,"
                + "p.c_c_brevete,"
                + "p.c_t_mail,"
                + "p.c_t_nom_mail,"
                + "p.c_t_apellido_paterno,"
                + "p.c_t_apellido_materno,"
                + "p.c_t_primer_nombre,"
                + "p.c_t_segundo_nombre,"
                + "p.c_c_actividad,"
                + "p.c_c_cod_emp,"
                + "p.c_c_placa,"
                + "p.c_t_marca,"
                + "p.c_st_habilitado,"
                + "p.d_cese,"
                + "p.c_c_puesto,"
                + "p.c_c_cargo,"
                + "p.c_fl_web "
                + "FROM panaautos.personal p "
                + "WHERE TRIM(p.c_c_codigo) = TRIM('" + codigo + "')";

        ResultSet rs = Util.sql_consulta(sql, null);
        Persona persona = new Persona();
        while (rs.next()) {
            persona.setCodigo(rs.getString("c_c_codigo"));
            persona.setNombre(rs.getString("c_t_nombre"));
            persona.setLibretaElectoral(rs.getString("c_c_lib_electoral"));
            persona.setDirecion(rs.getString("c_t_direccion"));
            persona.setDistrito(rs.getString("c_t_distrito"));
            persona.setCiudad(rs.getString("c_t_ciudad"));
            persona.setTelefono(rs.getString("c_c_telf"));
            persona.setCarnetSeguro(rs.getString("c_c_carnet_seguro"));
            persona.setAfp(rs.getString("c_c_afp"));
            persona.setMontoBasico(rs.getBigDecimal("n_i_monto_basico"));
            persona.setIngreso(rs.getString("d_ingreso"));
            persona.setGrupo(rs.getString("c_c_grupo"));
            persona.setNacimiento(rs.getString("c_fecha_nac"));
            persona.setSituacion(rs.getString("c_st_situacion"));
            persona.setSexo(rs.getString("c_t_sexo"));
            persona.setCargo(rs.getString("c_t_cargo"));
            persona.setCargoInitCap(rs.getString("cargoInitCap"));
            persona.setArea(rs.getString("c_c_area"));
            persona.setDepartamento(rs.getString("c_c_dpto"));
            persona.setBrevete(rs.getString("c_c_brevete"));
            persona.setCorreo(rs.getString("c_t_mail"));
            persona.setUserCorreo(rs.getString("c_t_nom_mail"));
            persona.setApePaterno(rs.getString("c_t_apellido_paterno"));
            persona.setApeMaterno(rs.getString("c_t_apellido_materno"));
            persona.setPriNombre(rs.getString("c_t_primer_nombre"));
            persona.setSegNombre(rs.getString("c_t_segundo_nombre"));
            persona.setActividad(rs.getString("c_c_actividad"));
            persona.setCodEmpresa(rs.getString("c_c_cod_emp"));
            persona.setPlaca(rs.getString("c_c_placa"));
            persona.setMarca(rs.getString("c_t_marca"));
            persona.setHabilitado(rs.getString("c_st_habilitado"));
            persona.setCese(rs.getString("d_cese"));
            persona.setPuesto(rs.getString("c_c_puesto"));
            persona.setCodCargo(rs.getString("c_c_cargo"));
            persona.setFlgWeb(rs.getString("c_fl_web"));
            persona.setExiste(true);
        }
        return persona;
         */
        Connection cn = new ConectaDb().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Persona persona = new Persona();
        try {
            String sql = "SELECT "
                    + "TRIM(p.c_c_codigo) AS c_c_codigo,"
                    + "TRIM(p.c_t_nombre) AS c_t_nombre,"
                    + "TRIM(p.c_c_lib_electoral) AS c_c_lib_electoral,"
                    + "p.c_t_direccion,"
                    + "p.c_t_distrito,"
                    + "p.c_t_ciudad,"
                    + "p.c_c_telf,"
                    + "p.c_c_carnet_seguro,"
                    + "p.c_c_afp,"
                    + "p.n_i_monto_basico,"
                    + "p.d_ingreso,"
                    + "p.c_c_grupo,"
                    + "p.c_fecha_nac,"
                    + "p.c_st_situacion,"
                    + "p.c_t_sexo,"
                    + "p.c_t_cargo,"
                    + "initcap(c_t_cargo) AS cargoInitCap,"
                    + "p.c_c_area,"
                    + "p.c_c_dpto,"
                    + "p.c_c_brevete,"
                    + "p.c_t_mail,"
                    + "p.c_t_nom_mail,"
                    + "p.c_t_apellido_paterno,"
                    + "p.c_t_apellido_materno,"
                    + "p.c_t_primer_nombre,"
                    + "p.c_t_segundo_nombre,"
                    + "p.c_c_actividad,"
                    + "p.c_c_cod_emp,"
                    + "p.c_c_placa,"
                    + "p.c_t_marca,"
                    + "p.c_st_habilitado,"
                    + "p.d_cese,"
                    + "p.c_c_puesto,"
                    + "p.c_c_cargo,"
                    + "p.c_fl_web "
                    + "FROM panaautos.personal p "
                    + "WHERE TRIM(p.c_c_codigo) = TRIM('" + codigo + "')";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                persona.setCodigo(rs.getString("c_c_codigo"));
                persona.setNombre(rs.getString("c_t_nombre"));
                persona.setLibretaElectoral(rs.getString("c_c_lib_electoral"));
                persona.setDirecion(rs.getString("c_t_direccion"));
                persona.setDistrito(rs.getString("c_t_distrito"));
                persona.setCiudad(rs.getString("c_t_ciudad"));
                persona.setTelefono(rs.getString("c_c_telf"));
                persona.setCarnetSeguro(rs.getString("c_c_carnet_seguro"));
                persona.setAfp(rs.getString("c_c_afp"));
                persona.setMontoBasico(rs.getBigDecimal("n_i_monto_basico"));
                persona.setIngreso(rs.getString("d_ingreso"));
                persona.setGrupo(rs.getString("c_c_grupo"));
                persona.setNacimiento(rs.getString("c_fecha_nac"));
                persona.setSituacion(rs.getString("c_st_situacion"));
                persona.setSexo(rs.getString("c_t_sexo"));
                persona.setCargo(rs.getString("c_t_cargo"));
                persona.setCargoInitCap(rs.getString("cargoInitCap"));
                persona.setArea(rs.getString("c_c_area"));
                persona.setDepartamento(rs.getString("c_c_dpto"));
                persona.setBrevete(rs.getString("c_c_brevete"));
                persona.setCorreo(rs.getString("c_t_mail"));
                persona.setUserCorreo(rs.getString("c_t_nom_mail"));
                persona.setApePaterno(rs.getString("c_t_apellido_paterno"));
                persona.setApeMaterno(rs.getString("c_t_apellido_materno"));
                persona.setPriNombre(rs.getString("c_t_primer_nombre"));
                persona.setSegNombre(rs.getString("c_t_segundo_nombre"));
                persona.setActividad(rs.getString("c_c_actividad"));
                persona.setCodEmpresa(rs.getString("c_c_cod_emp"));
                persona.setPlaca(rs.getString("c_c_placa"));
                persona.setMarca(rs.getString("c_t_marca"));
                persona.setHabilitado(rs.getString("c_st_habilitado"));
                persona.setCese(rs.getString("d_cese"));
                persona.setPuesto(rs.getString("c_c_puesto"));
                persona.setCodCargo(rs.getString("c_c_cargo"));
                persona.setFlgWeb(rs.getString("c_fl_web"));
                persona.setExiste(true);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(ps);
        }
        return persona;
    }

    // NO USAR ESTE METODO
    // NO USAR ESTE METODO
    // NO USAR ESTE METODO
    // NO USAR ESTE METODO
    // NO USAR ESTE METODO
    // NO USAR ESTE METODO
    // NO USAR ESTE METODO
    // NO USAR ESTE METODO
    // NO USAR ESTE METODO
    // NO USAR ESTE METODO
    /**
     * Listar los correos de un personal
     *
     * @param documento
     * @return
     * @throws Exception
     */
    public List<String> listaMailsPersonal(String documento) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        List<String> lista = new ArrayList<>();
        if (cn != null) {
            try {
                cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.sp_lista_mails_personal(?,?)}");
                cs.setString(1, documento);
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(2);
                while (rs.next()) {
                    lista.add(rs.getString("mail"));
                }
            } catch (Exception e) {
                LOGGER.error("GP.ERROR: {}", e);
                throw e;
            } finally {
                Util.close(cn);
                Util.close(cs);
                Util.close(rs);
            }
        }
        return lista;
    }

    /**
     * Funcion para validar si el correo de un personal guarda relacion, esta
     * funcion se esta utilizando en la opcion de recuperar password en el
     * login.
     *
     * @param documento
     * @param correo
     * @return
     * @throws Exception
     */
    public int validaEmailPersonal(String documento, String correo) throws Exception {
        String fn = "{?=call panaautos.PKG_WEB_TALLER_MOVIL.fn_valida_mail_personal(?,?)}";
        Object[] paramIN = {documento, correo};
        Object result = Util.fn_ejecuta(fn, OracleTypes.INTEGER, paramIN);
        int ctos = Util.nullNum(result);
        return ctos;
    }

    /**
     * Obtiene los datos de un personal de un determinado sistema, util para la
     * recuperacion de password
     *
     * @param codEmp
     * @param documento
     * @param idSistema
     * @return
     * @throws Exception
     */
    public Persona getDatosPersonalSistema(String codEmp, String documento, String idSistema) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Persona obj = new Persona();
        if (cn != null) {
            try {
                cs = cn.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.sp_datos_personal_sistema(?,?,?,?)}");
                cs.setString(1, codEmp);
                cs.setString(2, documento);
                cs.setString(3, idSistema);
                cs.registerOutParameter(4, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(4);
                while (rs.next()) {
                    obj.setNombre(rs.getString("nom_personal"));
                    obj.setNomTienda(rs.getString("nom_tienda"));
                    obj.setPassword(rs.getString("password"));
                    obj.setExiste(true);
                }
            } catch (Exception e) {
                LOGGER.error("GP.ERROR: {}", e);
                throw e;
            } finally {
                Util.close(cn);
                Util.close(cs);
                Util.close(rs);
            }
        }
        return obj;
    }

    public List<Persona> getPersonalNombre(String tipo, String nombre) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Persona> list = new ArrayList<>();
        try {
            cs = cn.prepareCall("{call panaautos.pkg_util.sp_get_personal_nombre(?,?,?)}");
            cs.setString(1, tipo);
            cs.setString(2, nombre);
            cs.registerOutParameter(3, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(3);
            while (rs.next()) {
                Persona obj = new Persona();
                obj.setCodigo(rs.getString("codigo"));
                obj.setNombre(Util.quitarApostrofos(Util.nullCad(rs.getString("nombre"))));
                list.add(obj);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(cs);
        }
        return list;
    }

}
