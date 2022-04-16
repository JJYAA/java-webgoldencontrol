package pe.com.gp.listas;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.com.gp.connection.ConectaDb;
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.entity.Tienda;
import java.sql.Statement;
import oracle.jdbc.OracleTypes;
import pe.com.gp.connection.ConectaHana;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.DtoTienda;

public class ListaTiendas {

    //Lista Tienda de Boleta de Ingreso de Traslado
    public List Lista_Tiendas_BolIng() throws Exception {
        ArrayList<ListaGenerica> lista = new ArrayList<ListaGenerica>();
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            cs = conexion.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.SP_GET_TIENDAS_BOLINGRESO_TRAS(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);//   
            while (rs.next()) {
                ListaGenerica LST = new ListaGenerica();
                LST.setIndice(rs.getString("ceg_cod_emp"));
                LST.setDescripcion(rs.getString("ceg_nom_emp"));
                lista.add(LST);
            }
        } catch (Exception e) {
        } finally {
            try {
                conexion.close(); // libera cn
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
        return lista;
    }

    // Lista Tienda de Taller Movil
    public List Lista_Tiendas() throws Exception {
        ArrayList<ListaGenerica> lista = new ArrayList<ListaGenerica>();
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Statement stm = null;
        try {
            cs = conexion.prepareCall("{call PANAAUTOS.PKG_WEB_CONSULTAS.GetTiendas_Movil(?)}");
            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(1);//   
            while (rs.next()) {
                ListaGenerica LST = new ListaGenerica();
                LST.setDescripcion(rs.getString("ceg_nom_int"));
                LST.setIndice(rs.getString("ceg_cod_emp"));
                lista.add(LST);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conexion.close(); // libera cn
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

            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (Exception e) {
            }

        }
        return lista;
    }

    // Lista tienda Grupo Pana por ruc o codigo de cliente
    public Tienda listaTiendasGP(String ruc_empresa) {
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Tienda tienda = null;

        if (conexion != null) {
            try {
                cs = conexion.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.SP_BUSCA_TIENDA_RUC(?,?)}");
                cs.setString(1, ruc_empresa);
                cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(2);
                while (rs.next()) {
                    tienda = new Tienda();
                    tienda.setCodigo(rs.getString("CEG_COD_EMP"));
                    tienda.setNombre(rs.getString("CEG_NOM_EMP"));
                }

            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                try {
                    conexion.close(); // libera cn
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

        return tienda;
    }

    public List<ListaGenerica> listaTiendasSAP() {
        ArrayList<ListaGenerica> list = new ArrayList<>();
        Connection conexion = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        if (conexion != null) {
            try {                 
                String sql = "select c_c_empresa codigo,c_t_empresa descripcion from empresa where c_fl_estado='1'";
                ps = conexion.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ListaGenerica listGenerica = new ListaGenerica();
                    listGenerica.setIndice(rs.getString("codigo"));
                    listGenerica.setDescripcion(rs.getString("descripcion"));
                    list.add(listGenerica);
                }
            } catch (SQLException e) {

            } finally {
                try {
                    conexion.close(); // libera cn
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

        return list;
    }
    
    
    
    // Lista las tiendas de ventas de Grupo Pana    
    public List<ListaGenerica> listaTiendasGP() {
        ArrayList<ListaGenerica> list = new ArrayList<>();
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        if (conexion != null) {
            try {
                cs = conexion.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.GetTiendas(?)}");
                cs.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(1);
                while (rs.next()) {
                    ListaGenerica listGenerica = new ListaGenerica();
                    listGenerica.setIndice(rs.getString("codigo"));
                    listGenerica.setDescripcion(rs.getString("descripcion"));
                    list.add(listGenerica);
                }
            } catch (SQLException e) {

            } finally {
                try {
                    conexion.close(); // libera cn
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

        return list;
    }

    // NO SEGUIR UTILIZANDO ESTE METODO
    // NO SEGUIR UTILIZANDO ESTE METODO
    // NO SEGUIR UTILIZANDO ESTE METODO
    // NO SEGUIR UTILIZANDO ESTE METODO
    // NO SEGUIR UTILIZANDO ESTE METODO
    // NO SEGUIR UTILIZANDO ESTE METODO
    // NO SEGUIR UTILIZANDO ESTE METODO
    // NO SEGUIR UTILIZANDO ESTE METODO
    // NO SEGUIR UTILIZANDO ESTE METODO
    // 2017
    public DtoTienda getTienda(String codigoTienda) {
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        DtoTienda tienda = null;

        if (conexion != null) {
            try {
                cs = conexion.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.sp_busca_tienda(?,?)}");
                cs.setString(1, codigoTienda);
                cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(2);
                while (rs.next()) {
                    tienda = new DtoTienda();
                    tienda.setVtaRs(rs.getString("CEG_VTA_RS"));// NO USAR
                    tienda.setCodigo(rs.getString("CEG_COD_EMP"));//NO USAR
                    tienda.setDocumento(rs.getString("CEG_COD_CLI"));//NO USAR
                    tienda.setNombre(rs.getString("CEG_NOM_EMP"));//NO USAR
                    tienda.setRucTdp(rs.getString("CEG_RUC_TDP"));//NO USAR
                    tienda.setEmpEmp(rs.getString("CEG_COD_GEN"));//NO USAR
                    tienda.setDireccion(rs.getString("CEG_DIR_EMP"));//NO USAR
                    tienda.setCorreoSer(rs.getString("CEG_EM_SER"));//NO USAR
                    tienda.setCorreoRep(rs.getString("CEG_EM_REP"));//NO USAR
                    tienda.setCodTdp(rs.getString("CEG_COD_TDP"));   //  NO USAR        
                }

            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                try {
                    conexion.close(); // libera cn
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

        return tienda;
    }

    /**
     * Lista las tienda por tipo de Neogocio
     *
     * @param tipoNegocio Especifica el tipo de negocio.
     * @return
     */
    public List<ListaGenerica> listaTiendasXtipoNegocio(String tipoNegocio) {
        ArrayList<ListaGenerica> list = new ArrayList<>();
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;

        if (conexion != null) {
            try {
                cs = conexion.prepareCall("{call panaautos.pkg_generales.Get_Emp_Negocio(?,?)}");
                cs.setString(1, tipoNegocio);
                cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(2);
                while (rs.next()) {
                    ListaGenerica listGenerica = new ListaGenerica();
                    listGenerica.setIndice(rs.getString("ceg_cod_emp"));
                    listGenerica.setDescripcion(rs.getString("ceg_nom_emp"));
                    list.add(listGenerica);
                }

            } catch (SQLException e) {
                System.out.println(e);
            } finally {
                try {
                    conexion.close(); // libera cn
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

        return list;
    }

    public List<DtoTienda> listaTiendas(String tipoNegocio) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        List<DtoTienda> lista = new ArrayList<>();

        if (cn != null) {
            try {
                cs = cn.prepareCall("{call PANAAUTOS.PKG_UTIL.SP_LISTA_TIENDAS(?,?)}");
                cs.setString(1, tipoNegocio);
                cs.registerOutParameter(2, OracleTypes.CURSOR);
                cs.execute();
                rs = (ResultSet) cs.getObject(2);

                while (rs.next()) {
                    DtoTienda list = new DtoTienda();
                    list.setCodigo(rs.getString("CEG_COD_EMP"));
                    list.setNombre(rs.getString("CEG_NOM_EMP"));
                    list.setDocumento(rs.getString("CEG_DOC_EMP"));
                    list.setCodTdp(rs.getString("CEG_COD_TDP"));
                    list.setDireccion(rs.getString("CEG_DIR_EMP"));
                    list.setDistrito(rs.getString("CEG_DIS_EMP"));
                    list.setTelefono1(rs.getString("CEG_TEL_001"));
                    list.setTelefono2(rs.getString("CEG_TEL_002"));
                    list.setNumeroFax(rs.getString("CEG_NUM_FAX"));
                    list.setCorreo(rs.getString("CEG_EM_COR"));
                    list.setCorreoRep(rs.getString("CEG_EM_REP"));
                    list.setCorreoSer(rs.getString("CEG_EM_SER"));
                    list.setCorreoVeh(rs.getString("CEG_EM_VEH"));
                    list.setAnulado(rs.getString("CEG_ANU_SN"));
                    list.setNotaFac1(rs.getString("CEG_NOT_FA1"));
                    list.setNotaFac2(rs.getString("CEG_NOT_FA2"));
                    list.setNotaFac3(rs.getString("CEG_NOT_FA3"));
                    list.setNotaFac4(rs.getString("CEG_NOT_FA4"));
                    list.setTipoNegocio(rs.getString("cen_tip_neg"));
                    list.setDescNegocio(rs.getString("cen_des_neg"));
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

    public List<ListaGenerica> listaTiendasEmpresa(String empresa) {
        ArrayList<ListaGenerica> list = new ArrayList<>();
        Connection conexion = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        if (conexion != null) {
            try {                 
                cs = conexion.prepareCall("{call relaciones.dbo.LISTA_EMPRESAS(?)}");
                cs.setString(1, empresa);
                cs.execute();
                 rs = cs.getResultSet();
                while (rs.next()) {
                    ListaGenerica listGenerica = new ListaGenerica();
                    listGenerica.setIndice(rs.getString("codigo"));
                    listGenerica.setDescripcion(rs.getString("descripcion"));
                    list.add(listGenerica);
                }
            } catch (SQLException e) {
                
            } finally {
                try {
                    conexion.close(); // libera cn
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

        return list;
    }
}
