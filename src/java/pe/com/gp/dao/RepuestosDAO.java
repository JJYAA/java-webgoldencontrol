package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.com.gp.connection.ConectaDb;
import pe.com.gp.entity.DtoMovimientoAlmacen;
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.entity.Parte;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.util.Util;

public class RepuestosDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    public long numero_boleta(long seq) throws Exception {
        LOGGER.info("<==== Inicio Metodo: numero_boleta ====>");
        LOGGER.trace("seq: {}", seq);
        long num_bol = 0;
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = conexion.prepareCall("{?=call PANAAUTOS.PKG_REPUESTOS_WEB.Numero_Boleta(?)}");
            cs.registerOutParameter(1, OracleTypes.NUMERIC);
            cs.setLong(2, seq);
            cs.execute();
            num_bol = cs.getLong(1);

        } catch (SQLException e) {
            LOGGER.error("GP.ERROR: {}", e);
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
        LOGGER.info("<==== Fin Metodo: numero_boleta ====>");
        return num_bol;
    }

    public void Genera_boleta(long seq, String tienda, long ord_tra, String codigo) throws Exception {
        LOGGER.info("<==== Inicio Metodo: Genera_boleta ====>");
        LOGGER.trace("seq: {}", seq);
        LOGGER.trace("tienda: {}", tienda);
        LOGGER.trace("ord_tra: {}", ord_tra);
        LOGGER.trace("codigo: {}", codigo);

        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        try {

            cs = conexion.prepareCall("{call PANAAUTOS.SP_INSERT_BOL_ALM_WEB(?,?,?,?)}");
            cs.setLong(1, seq);
            cs.setString(2, tienda);
            cs.setLong(3, ord_tra);
            cs.setString(4, codigo);
            cs.execute();
        } catch (SQLException e) {
            LOGGER.error("GP.ERROR: {}", e);
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
        }
        LOGGER.info("<==== Fin Metodo: Genera_boleta ====>");
    }

    public void Agrega_Item_tmp(String tienda, String codigo, String cantidad, String descuento, long seq) throws Exception {
        LOGGER.info("<==== Inicio Metodo: Agrega_Item_tmp ====>");
        LOGGER.trace("tienda: {}", tienda);
        LOGGER.trace("codigo: {}", codigo);
        LOGGER.trace("cantidad: {}", cantidad);
        LOGGER.trace("descuento: {}", descuento);
        LOGGER.trace("seq: {}", seq);

        String wwn_desc = "";
        wwn_desc = descuento;
        if ((wwn_desc == null) || (wwn_desc.length() == 0)) {
            wwn_desc = "0";
        }
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        try {

            cs = conexion.prepareCall("{call panaautos.SP_AGREGATMP_ITEM_WEB(?,?,?,?,?)}");
            cs.setString(1, tienda);
            cs.setString(2, codigo);
            cs.setString(3, cantidad);
            cs.setString(4, wwn_desc);
            cs.setLong(5, seq);
            cs.execute();
        } catch (SQLException e) {
            LOGGER.error("GP.ERROR: {}", e);
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
        }
        LOGGER.info("<==== Fin Metodo: Agrega_Item_tmp ====>");
    }

    public int obtieneCantidad(String codigoTienda) throws Exception {
        LOGGER.info("<==== Inicio Metodo: obtieneCantidad ====>");
        LOGGER.trace("codigoTienda: {}", codigoTienda);

        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        int cantidad = 0;

        String sql = "SELECT nvl(SUM(cantidad), 0) AS cant "
                + "FROM tempo "
                + "WHERE cod_Empresa = '" + codigoTienda + "' "
                + "AND ubicacion = 'SEGURIDA'";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    cantidad = rs.getInt("cant");
                    break;
                }

            } catch (SQLException e) {
                LOGGER.error("GP.ERROR: {}", e);
            } finally {
                try {
                    conexion.close(); // libera cn
                } catch (Exception e) {
                }

                try {
                    if (stmt != null) {
                        stmt.close();
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
        LOGGER.info("<==== Fin Metodo: obtieneCantidad ====>");
        return cantidad;
    }

    public int obtieneStockTemporal(String codigoTienda, String codigoParte) throws Exception {
        LOGGER.info("<==== Inicio Metodo: obtieneStockTemporal ====>");
        LOGGER.trace("codigoTienda: {}", codigoTienda);
        LOGGER.trace("codigoParte: {}", codigoParte);

        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        int cantidad = 0;

        String sql = "SELECT nvl(SUM(cantidad), 0) AS stock_tempo "
                + "FROM tempo "
                + "WHERE cod_empresa = '" + codigoTienda + "' "
                + "AND TRIM(cod_parte) = TRIM('" + codigoParte + "') "
                + "AND tip_documento IN ('FR', 'BR', 'MS', 'ME', 'TE', 'RS')";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    cantidad = rs.getInt("stock_tempo");
                    break;
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

        LOGGER.info("<==== Fin Metodo: obtieneStockTemporal ====>");

        return cantidad;
    }

    /**
     * Busca una parte(repuesto) en la tabla LIS_PRE_REP
     *
     * @param codigoParte
     * @return Parte
     * @throws Exception
     */
    public Parte buscarParteLPR(String codigoParte) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        Parte parte = null;

        String sql = "SELECT * FROM "
                + "lis_pre_rep "
                + "WHERE "
                + "lpr_cod_pro = TRIM('" + codigoParte + "')";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    parte = new Parte();
                    parte.setIgv(rs.getDouble("lpr_igv_pro"));
                    parte.setCodigo(rs.getString("lpr_cod_pro"));
                    parte.setDsctoMaximo(rs.getDouble("lpr_des_max"));
                    parte.setDsctoManoObra(rs.getDouble("lpr_des_mo"));
                    parte.setDsctoTaller(rs.getDouble("lpr_des_ot"));
                    parte.setEsObsequio(rs.getString("lpr_obs_sn"));
                    parte.setAnulado(rs.getString("lpr_anu_sn"));
                    parte.setTipoProducto(rs.getString("LPR_TIP_PRO"));
                    parte.setDescripcion(rs.getString("lpr_des_pro"));
                    parte.setUnidadMedida(rs.getString("lpr_uni_med"));
                    parte.setCodProcedencia(rs.getString("lpr_pro_pro"));
                    parte.setVVPSol(rs.getDouble("lpr_VVP_Sol"));
                    parte.setVVDSol(rs.getDouble("lpr_VVD_Sol"));
                    parte.setVVPDol(rs.getDouble("lpr_VVP_Dol"));
                    parte.setVVDDol(rs.getDouble("lpr_VVD_Dol"));
                    parte.setDsctoMostrador(rs.getDouble("lpr_des_mo"));
                    parte.setDsctoOT(rs.getDouble("lpr_des_ot"));
                    parte.setDsctoCiaSeguro(rs.getDouble("lpr_des_seg"));
                    parte.setDsctoOtros(rs.getDouble("lpr_des_otr"));
                    parte.setDsctoMaximo(rs.getDouble("lpr_des_max"));
                    parte.setMarca(rs.getString("lpr_mar_pro"));
                    parte.setTipoDescuento(rs.getString("lpr_tip_des"));
                    parte.setTipoFactor(rs.getString("lpr_tip_fac"));
                    parte.setCodigoMarca(rs.getString("lpr_cod_mar"));
                    parte.setClaseProducto(rs.getString("lpr_cla_rep"));
                    break;
                }

            } catch (SQLException e) {
            } finally {
                try {
                    conexion.close(); // libera cn
                } catch (Exception e) {
                }

                try {
                    if (stmt != null) {
                        stmt.close();
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

        return parte;
    }

    //Busca una parte(repuesto) en la tabla STK_REP_GEN   
    /*public Parte buscarParteSRG(String codigoTienda, String codigoParte) throws Exception {
     ResultSet rs = null;
     Connection conexion = new ConectaDb().connection();
     Statement stmt = null;
     Parte parte = null;

     String sql = "SELECT * FROM "
     + "stk_rep_gen "
     + "WHERE "
     + "srg_cod_emp = '" + codigoTienda + "' "
     + "AND srg_cod_alm = '00' "
     + "AND srg_cod_pro = TRIM('" + codigoParte + "')";

     if (conexion != null) {
     try {
     stmt = conexion.createStatement();
     rs = stmt.executeQuery(sql);

     while (rs.next()) {
     parte = new Parte();
     parte.setStkSeg(rs.getInt("srg_stk_seg"));
     parte.setStkDis(rs.getInt("srg_stk_dis"));
     parte.setStkTotal(rs.getInt("srg_stk_tot"));
     parte.setStkExt(rs.getInt("srg_stk_ext"));
     parte.setStkTaller(rs.getInt("srg_stk_tal"));
     parte.setStkAlm(rs.getInt("srg_stk_alm"));
     parte.setStkComprometido(rs.getInt("srg_stk_com"));
     parte.setUltCosto(rs.getDouble("srg_ult_cos"));
     parte.setCostoProm(rs.getDouble("srg_ucos_pro"));
     parte.setSinMoviVenta(rs.getInt("srg_smov_vta"));
     parte.setSinMoviCompra(rs.getInt("srg_smov_com"));
     parte.setUltFecSal(rs.getString("srg_ult_sal"));
     parte.setUltFecIng(rs.getString("srg_ult_ing"));
     parte.setFecApe(rs.getString("srg_fec_ape"));
     parte.setUbicacion(rs.getString("srg_ubi_pro"));
     parte.setInventariado(rs.getString("srg_inv_sn"));
     parte.setStkTmp(rs.getInt("srg_stk_tmp"));
     break;
     }

     } catch (SQLException e) {
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

     return parte;
     }*/
    // Busca un repuesto en STK_REP_GEN
    public Parte buscarParteSRG(String codEmp, String codParte, String codAlm) throws Exception {
        /*String sp = "panaautos.pkg_web_taller_movil.busca_parte_srg(?,?,?,?)";
        Object[] paramIN = {codEmp, codParte, codAlm};
        ResultSet rs = Util.sp_consulta(sp, paramIN);
        Parte obj = new Parte();
        while (rs.next()) {
            obj.setStkAlm(rs.getInt("srg_stk_alm"));
            obj.setUbicacion(rs.getString("srg_ubi_pro"));
            obj.setUltFecIng(rs.getString("srg_ult_ing"));
            obj.setUltCosto(rs.getDouble("srg_ult_cos"));
            obj.setStkSeg(rs.getInt("srg_stk_seg"));
            obj.setStkDis(rs.getInt("srg_stk_dis"));
            obj.setStkTotal(rs.getInt("srg_stk_tot"));
            obj.setStkExt(rs.getInt("srg_stk_ext"));
            obj.setStkTaller(rs.getInt("srg_stk_tal"));
            obj.setStkComprometido(rs.getInt("srg_stk_com"));
            obj.setCostoProm(rs.getDouble("srg_ucos_pro"));
            obj.setSinMoviVenta(rs.getInt("srg_smov_vta"));
            obj.setSinMoviCompra(rs.getInt("srg_smov_com"));
            obj.setUltFecSal(rs.getString("srg_ult_sal"));
            obj.setFecApe(rs.getString("srg_fec_ape"));
            obj.setInventariado(rs.getString("srg_inv_sn"));
            obj.setStkTmp(rs.getInt("srg_stk_tmp"));
            obj.setCodigoAlmacen(rs.getString("srg_cod_alm"));
            obj.setStkMin(rs.getInt("srg_stk_min"));
            obj.setStkMax(rs.getInt("srg_stk_max"));
            obj.setMesesSinMvtoVta(rs.getInt("srg_smov_vta"));
            obj.setMesesSinMvtoComp(rs.getInt("srg_smov_com"));
            obj.setClaseCompra(rs.getString("srg_cla_com"));
            obj.setClaseVenta(rs.getString("srg_cla_ven"));
            obj.setPedPendiente(rs.getString("ord_com_pen"));
            obj.setSepPendiente(rs.getString("sep_pen"));
            obj.setExiste(true);
        }
        return obj;*/
//        LOGGER.info("<==== Inicio Metodo: buscarParteSRG ====>");
//        Connection cn = new ConectaDb().connection();
//        CallableStatement cs = null;
//        ResultSet rs = null;
//        Parte obj = new Parte();
//        try {
//            cs = cn.prepareCall("{call panaautos.pkg_web_taller_movil.busca_parte_srg(?,?,?,?)}");
//            cs.setString(1, codEmp);
//            cs.setString(2, codParte);
//            cs.setString(3, codAlm);
//            cs.registerOutParameter(4, OracleTypes.CURSOR);
//            cs.execute();
//            rs = (ResultSet) cs.getObject(4);
//            while (rs.next()) {
//                obj.setStkAlm(rs.getInt("srg_stk_alm"));
//                obj.setUbicacion(rs.getString("srg_ubi_pro"));
//                obj.setUltFecIng(rs.getString("srg_ult_ing"));
//                obj.setUltCosto(rs.getDouble("srg_ult_cos"));
//                obj.setStkSeg(rs.getInt("srg_stk_seg"));
//                obj.setStkDis(rs.getInt("srg_stk_dis"));
//                obj.setStkTotal(rs.getInt("srg_stk_tot"));
//                obj.setStkExt(rs.getInt("srg_stk_ext"));
//                obj.setStkTaller(rs.getInt("srg_stk_tal"));
//                obj.setStkComprometido(rs.getInt("srg_stk_com"));
//                obj.setCostoProm(rs.getDouble("srg_ucos_pro"));
//                //obj.setSinMoviVenta(rs.getInt("srg_smov_vta"));
//                obj.setSinMoviCompra(rs.getInt("srg_smov_com"));
//                obj.setUltFecSal(rs.getString("srg_ult_sal"));
//                obj.setFecApe(rs.getString("srg_fec_ape"));
//                obj.setInventariado(rs.getString("srg_inv_sn"));
//                obj.setStkTmp(rs.getInt("srg_stk_tmp"));
//                obj.setCodigoAlmacen(rs.getString("srg_cod_alm"));
//                obj.setStkMin(rs.getInt("srg_stk_min"));
//                obj.setStkMax(rs.getInt("srg_stk_max"));
//                obj.setMesesSinMvtoVta(rs.getInt("srg_smov_vta"));
//                obj.setMesesSinMvtoComp(rs.getInt("srg_smov_com"));
//                obj.setClaseCompra(rs.getString("srg_cla_com"));
//                obj.setClaseVenta(rs.getString("srg_cla_ven"));
//                obj.setPedPendiente(rs.getString("ord_com_pen"));
//                obj.setSepPendiente(rs.getString("sep_pen"));
//                obj.setDiasSinMvtoVta(rs.getInt("dias_sin_vta"));
//                obj.setExiste(true);
//            }
//        } catch (Exception e) {
//            LOGGER.error("GP.ERROR: {}", e);
//            throw e;
//        } finally {
//            Util.close(cn);
//            Util.close(rs);
//            Util.close(cs);
//        }
        Parte obj = new Parte();
        obj.setExiste(true);
        LOGGER.info("<==== Fin Metodo: buscarParteSRG ====>");
        return obj;
    }

    /**
     * Lista las partes en almacenes secundarios.
     *
     * @param codigoTienda
     * @param codigoAlmacen
     * @param codigoParte
     * @return
     * @throws Exception
     */
    public List<Parte> listaParteEnAlmSec(String codigoTienda, String codigoAlmacen, String codigoParte) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        List<Parte> list = new ArrayList<>();

        String sql = "SELECT * FROM "
                + "stk_rep_gen "
                + "WHERE "
                + "srg_cod_emp = '" + codigoTienda + "' "
                + "AND srg_cod_alm <> '" + codigoAlmacen + "' "
                + "AND srg_cod_pro = TRIM('" + codigoParte + "')";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    Parte parte = new Parte();
                    parte.setCodigoEmpresa(rs.getString("srg_cod_emp"));
                    parte.setCodigoProducto(rs.getString("srg_cod_pro"));
                    parte.setCodigoAlmacen(rs.getString("srg_cod_alm"));
                    parte.setCodigoProducto(rs.getString("srg_ubi_pro"));
                    parte.setUbicacion(rs.getString("srg_ubi_pro"));
                    list.add(parte);
                }

            } catch (SQLException e) {
            } finally {
                try {
                    conexion.close(); // libera cn
                } catch (Exception e) {
                }

                try {
                    if (stmt != null) {
                        stmt.close();
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

    /**
     * Lista el movimiento mensual de una parte.
     *
     * @param codigoTienda
     * @param codigoAlmacen
     * @param codigoParte
     * @return
     * @throws Exception
     */
    public List<Parte> listaParteMovMensual(String codigoTienda, String codigoAlmacen, String codigoParte) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        List<Parte> list = new ArrayList<>();

        String sql = "SELECT "
                + "srm_num_mes,"
                + "srm_num_ano,"
                + "srm_vta_mes,"
                + "srm_atg_com,"
                + "srm_atg_ven,"
                + "srm_cos_pro,"
                + "srm_cod_alm "
                + "FROM stk_rep_men "
                + "WHERE srm_cod_emp = '" + codigoTienda + "' "
                + "AND srm_cod_pro =  TRIM('" + codigoParte + "') "
                + "AND srm_cod_alm = '" + codigoAlmacen + "' "
                + "ORDER BY srm_num_ano, srm_num_mes";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    Parte parte = new Parte();
                    parte.setMes(rs.getInt("srm_num_mes"));
                    parte.setAnio(rs.getInt("srm_num_ano"));
                    parte.setVentas(rs.getInt("srm_vta_mes"));
                    parte.setAntMovCompra(rs.getInt("srm_atg_com"));
                    parte.setAntMovVenta(rs.getInt("srm_atg_ven"));
                    parte.setCostoPromedio(rs.getDouble("srm_cos_pro"));
                    parte.setCodigoAlmacen(rs.getString("srm_cod_alm"));
                    list.add(parte);
                }

            } catch (SQLException e) {
            } finally {
                try {
                    conexion.close(); // libera cn
                } catch (Exception e) {
                }

                try {
                    if (stmt != null) {
                        stmt.close();
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

    /**
     * Movimientos mensuales de repuestos
     *
     * @param codigoTienda
     * @param codigoParte
     * @return Parte
     * @throws Exception
     */
    public Parte movimientosMensRptos(String codigoTienda, String codigoParte) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        Parte parte = null;

        String sql = "SELECT "
                + "srm_stk_fm,"
                + "srm_vta_mes "
                + "FROM stk_rep_men "
                + "WHERE srm_cod_emp = '" + codigoTienda + "' "
                + "AND srm_cod_pro = TRIM('" + codigoParte + "') "
                + "AND srm_cod_alm = '00' order by rowid desc";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                int intNum = 1;
                int dbStkMad = 0;
                int stkIni = 0;

                while (rs.next()) {
                    int stkFM = rs.getInt("srm_stk_fm");
                    int stkMES = rs.getInt("srm_vta_mes");
                    if (intNum == 1) {
                        stkIni = stkFM;
                    }
                    dbStkMad = dbStkMad + stkMES;

                    if (intNum == 6) {
                        break;
                    }
                    intNum++;
                }

                parte = new Parte();

                // stock INI
                parte.setStkIni(stkIni);

                // stock MAD
                double stkMad = Util.redondear(dbStkMad / intNum, 0);
                int intStkMad = (int) stkMad;
                parte.setStkMad(intStkMad);

                // stock MAX
                double stkMax = Util.redondear((dbStkMad / intNum) * 0.25, 0);
                int intStkMax = (int) stkMax;
                parte.setStkMax(intStkMax);

            } catch (SQLException e) {
            } finally {
                try {
                    conexion.close(); // libera cn
                } catch (Exception e) {
                }

                try {
                    if (stmt != null) {
                        stmt.close();
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

        return parte;
    }

    public int getStockMesSRM(String codigoTienda, int mes, int ano, String codigoAlmacen, String codigoParte) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        int stockMes = 0;

        String sql = "SELECT "
                + "(nvl(srm_stk_dis, 0) + nvl(srm_stk_seg, 0)) AS Stock_Mes "
                + "FROM stk_rep_men "
                + "WHERE srm_cod_emp = '" + codigoTienda + "' "
                + "AND srm_num_mes = " + mes + " "
                + "AND srm_num_ano = " + ano + " "
                + "AND srm_cod_pro = '" + codigoParte + "' "
                + "AND srm_cod_alm = '" + codigoAlmacen + "'";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    stockMes = rs.getInt("Stock_Mes");
                }

            } catch (SQLException e) {
            } finally {
                try {
                    conexion.close(); // libera cn
                } catch (Exception e) {
                }

                try {
                    if (stmt != null) {
                        stmt.close();
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

        return stockMes;
    }

    public List<Parte> listaParteEnAlmacenes(String codigoTienda, String codigoParte, int IntAlmSec) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        List<Parte> list = new ArrayList<>();

        String sql = "SELECT "
                + "a.cag_des_alm AS Almacen,"
                + "b.srg_ubi_pro AS Ubicacion,"
                + "decode(b.srg_cod_alm, '00', b.srg_stk_tot - " + IntAlmSec + ", b.srg_stk_tot) AS Stock,"
                + "b.srg_cod_alm "
                + "FROM cab_alm_gen a, stk_rep_gen b "
                + "WHERE a.cag_cod_emp = '" + codigoTienda + "' "
                + "AND b.srg_cod_pro = TRIM('" + codigoParte + "') "
                + "AND a.cag_cod_emp = b.srg_cod_emp "
                + "AND a.cag_cod_alm = b.srg_cod_alm "
                + "AND a.cag_anu_sn <> 'S'";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    Parte parte = new Parte();
                    parte.setAlmacen(rs.getString("Almacen"));
                    parte.setUbicaParteEnAlmacen(rs.getString("Ubicacion"));
                    parte.setStockParteEnAlmacen(rs.getInt("Stock"));
                    parte.setCodigoAlmacen(rs.getString("srg_cod_alm"));
                    list.add(parte);
                }

            } catch (SQLException e) {
            } finally {
                try {
                    conexion.close(); // libera cn
                } catch (Exception e) {
                }

                try {
                    if (stmt != null) {
                        stmt.close();
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

    public String parteCambioUbicacion(String codigoTienda, String codigoParte, String ubicacion, String codigoAlmacen) throws Exception {
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        String result = null;
        try {
            cs = conexion.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.PARTE_CAMBIO_UBICACION(?,?,?,?)}");
            cs.setString(1, codigoTienda);
            cs.setString(2, codigoParte);
            cs.setString(3, ubicacion);
            cs.setString(4, codigoAlmacen);
            cs.executeUpdate();
        } catch (Exception e) {
            result = e.getMessage();
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
        }
        return result;
    }

    public String parteCambioDescripcion(String codigoParte, String descripcion) throws Exception {
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        String result = null;
        try {
            cs = conexion.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.PARTE_CAMBIO_DESCRIPCION(?,?)}");
            cs.setString(1, codigoParte);
            cs.setString(2, descripcion);
            cs.executeUpdate();
        } catch (Exception e) {
            result = e.getMessage();
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
        }
        return result;
    }

//    /**
//     * Obtiene el stock disponible de una parte.
//     *
//     * @param codigoTienda
//     * @param codigoParte
//     * @return
//     * @throws Exception
//     */
//    public int getStockDisponibleSRG(String codigoTienda, String codigoParte) throws Exception {
//        Connection conexion = new ConectaDb().connection();
//        CallableStatement cs = null;
//        int cantidad = 0;
//        if (conexion != null) {
//            try {
//                cs = conexion.prepareCall("{?=call panaautos.fn_verifica_stock(?,?,?,?)}");
//                cs.registerOutParameter(1, OracleTypes.VARCHAR);
//                cs.setString(2, codigoTienda);
//                cs.setString(3, codigoParte);
//                cs.setInt(4, 0);
//                cs.setInt(5, 0);
//                cs.execute();
//                cantidad = cs.getInt(1);
//            } catch (Exception e) {
//            } finally {
//                try {
//                    conexion.close(); // libera cn
//                } catch (Exception e) {
//                }
//
//                try {
//                    if (cs != null) {
//                        cs.close();
//                    }
//                } catch (Exception e) {
//                }
//            }
//        }
//        return cantidad;
//    }
    /**
     * Actualiza el Stock Disponible y Stock de Seguridad, luego inserta en la
     * tabla de movimientos.
     *
     * @param codigoTienda Tienda del usuario logueado
     * @param codigoParte Codigo de la parte (repuesto)
     * @param dbStkDis Stock Disponible
     * @param dbStkSeg Stock de Seguridad
     * @param codigoUsuario Codigo del usuario logueado
     * @param cantidad Cantidad de Stock a trasladar
     * @param parte Objeto Parte
     * @return
     */
    public String insUpdStkDisSegMov(
            String codigoTienda,
            String codigoParte,
            int dbStkDis,
            int dbStkSeg,
            String codigoUsuario,
            int cantidad,
            Parte parte) {

        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        String result = null;

        if (conexion == null) {
            result = "No hay acceso a base de datos!";
        } else {
            try {
                conexion.setAutoCommit(false);
                // actualiza el stock disponible y de seguridad
                String s1 = "UPDATE stk_rep_gen SET "
                        + "srg_stk_dis = " + dbStkDis + " ,"
                        + "srg_stk_seg = " + dbStkSeg + " "
                        + "WHERE srg_cod_alm = '00' "
                        + "AND srg_cod_emp = '" + codigoTienda + "' "
                        + "AND srg_cod_pro = TRIM('" + codigoParte + "')";

                stmt = conexion.createStatement();
                stmt.executeUpdate(s1);
                stmt.close();
                // ---

                // inserta el movimiento
                String s2 = "INSERT INTO panaautos.det_mov_otr("
                        + "DMO_COD_EMP,"
                        + "DMO_COD_PRO,"
                        + "DMO_FEC_GEN,"
                        + "DMO_CAN_PRO,"
                        + "DMO_COS_PRO,"
                        + "DMO_COS_ANT,"
                        + "DMO_STK_TOTA,"
                        + "DMO_STK_DISA,"
                        + "DMO_STK_TALA,"
                        + "DMO_STK_EXTA,"
                        + "DMO_STK_TMPA,"
                        + "DMO_STK_ALMA,"
                        + "DMO_STK_SEGA,"
                        + "DMO_STK_COMA,"
                        + "DMO_COS_POS,"
                        + "DMO_STK_TOTP,"
                        + "DMO_STK_DISP,"
                        + "DMO_STK_TALP,"
                        + "DMO_STK_EXTP,"
                        + "DMO_STK_TMPP,"
                        + "DMO_STK_ALMP,"
                        + "DMO_STK_SEGP,"
                        + "DMO_STK_COMP,"
                        + "DMO_COD_ALM,"
                        + "DMO_COD_USR,"
                        + "DMO_CLA_DOC"
                        + ")VALUES("
                        + "'" + codigoTienda + "',"
                        + "TRIM('" + codigoParte + "'),"
                        + "SYSDATE,"
                        + "" + cantidad + ","
                        + "" + parte.getCostoProm() + ","
                        + "" + parte.getCostoProm() + ","
                        + "" + parte.getStkTotal() + ","
                        + "" + parte.getStkDis() + ","
                        + "" + parte.getStkTaller() + ","
                        + "" + parte.getStkExt() + ","
                        + "" + parte.getStkTmp() + ","
                        + "" + parte.getStkAlm() + ","
                        + "" + parte.getStkSeg() + ","
                        + "" + parte.getStkComprometido() + ","
                        + "" + parte.getCostoProm() + ","
                        + "" + parte.getStkTotal() + ","
                        + "" + dbStkDis + ","
                        + "" + parte.getStkTaller() + ","
                        + "" + parte.getStkExt() + ","
                        + "" + parte.getStkTmp() + ","
                        + "" + parte.getStkAlm() + ","
                        + "" + dbStkSeg + ","
                        + "" + parte.getStkComprometido() + ","
                        + "'00',"
                        + "TRIM('" + codigoUsuario + "'),"
                        + "'DS'"
                        + ")";

                stmt = conexion.createStatement();
                stmt.executeUpdate(s2);
                conexion.commit();
            } catch (SQLException e1) {
                result = e1.getMessage();
                try {
                    conexion.rollback();
                } catch (SQLException e2) {
                    result = e2.getMessage();
                }
            } finally {
                try {
                    conexion.setAutoCommit(true);
                    conexion.close();
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

        return result;
    }

    /**
     * Actualiza el Stock de Seguridad y el Stock Disponible, luego inserta en
     * la tabla de movimientos.
     *
     * @param codigoTienda Tienda del usuario logueado
     * @param codigoParte Codigo de la parte (repuesto)
     * @param dbStkDis Stock Disponible
     * @param dbStkSeg Stock de Seguridad
     * @param codigoUsuario Codigo del usuario logueado
     * @param cantidad Cantidad de Stock a trasladar
     * @param parte Objeto Parte
     * @return
     */
    public String insUpdStkSegDisMov(
            String codigoTienda,
            String codigoParte,
            int dbStkDis,
            int dbStkSeg,
            String codigoUsuario,
            int cantidad,
            Parte parte) {

        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        String result = null;

        if (conexion == null) {
            result = "No hay acceso a base de datos!";
        } else {
            try {
                conexion.setAutoCommit(false);

                // actualiza el stock de seguridad y disponible
                String s1 = "UPDATE stk_rep_gen SET "
                        + "srg_stk_dis = " + dbStkDis + " ,"
                        + "srg_stk_seg = " + dbStkSeg + " "
                        + "WHERE srg_cod_alm = '00' "
                        + "AND srg_cod_emp = '" + codigoTienda + "' "
                        + "AND srg_cod_pro = TRIM('" + codigoParte + "')";

                stmt = conexion.createStatement();
                stmt.executeUpdate(s1);
                stmt.close();
                // ---

                // inserta el movimiento
                String s2 = "INSERT INTO panaautos.det_mov_otr("
                        + "DMO_COD_EMP,"
                        + "DMO_COD_PRO,"
                        + "DMO_FEC_GEN,"
                        + "DMO_CAN_PRO,"
                        + "DMO_COS_PRO,"
                        + "DMO_COS_ANT,"
                        + "DMO_STK_TOTA,"
                        + "DMO_STK_DISA,"
                        + "DMO_STK_TALA,"
                        + "DMO_STK_EXTA,"
                        + "DMO_STK_TMPA,"
                        + "DMO_STK_ALMA,"
                        + "DMO_STK_SEGA,"
                        + "DMO_STK_COMA,"
                        + "DMO_COS_POS,"
                        + "DMO_STK_TOTP,"
                        + "DMO_STK_DISP,"
                        + "DMO_STK_TALP,"
                        + "DMO_STK_EXTP,"
                        + "DMO_STK_TMPP,"
                        + "DMO_STK_ALMP,"
                        + "DMO_STK_SEGP,"
                        + "DMO_STK_COMP,"
                        + "DMO_COD_ALM,"
                        + "DMO_COD_USR,"
                        + "DMO_CLA_DOC"
                        + ")VALUES("
                        + "'" + codigoTienda + "',"
                        + "TRIM('" + codigoParte + "'),"
                        + "SYSDATE,"
                        + "" + cantidad + ","
                        + "" + parte.getCostoProm() + ","
                        + "" + parte.getCostoProm() + ","
                        + "" + parte.getStkTotal() + ","
                        + "" + parte.getStkDis() + ","
                        + "" + parte.getStkTaller() + ","
                        + "" + parte.getStkExt() + ","
                        + "" + parte.getStkTmp() + ","
                        + "" + parte.getStkAlm() + ","
                        + "" + parte.getStkSeg() + ","
                        + "" + parte.getStkComprometido() + ","
                        + "" + parte.getCostoProm() + ","
                        + "" + parte.getStkTotal() + ","
                        + "" + dbStkDis + ","
                        + "" + parte.getStkTaller() + ","
                        + "" + parte.getStkExt() + ","
                        + "" + parte.getStkTmp() + ","
                        + "" + parte.getStkAlm() + ","
                        + "" + dbStkSeg + ","
                        + "" + parte.getStkComprometido() + ","
                        + "'00',"
                        + "TRIM('" + codigoUsuario + "'),"
                        + "'SD'"
                        + ")";

                stmt = conexion.createStatement();
                stmt.executeUpdate(s2);
                // ---             

                conexion.commit();
            } catch (SQLException e1) {
                result = e1.getMessage();

                try {
                    conexion.rollback();
                } catch (SQLException e2) {
                    result = e2.getMessage();
                }
            } finally {
                try {
                    conexion.setAutoCommit(true);
                    conexion.close(); // libera cn
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

        return result;
    }

    public List<DtoMovimientoAlmacen> listaDetalleTaller(String codigoTienda, String codigoParte) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        List<DtoMovimientoAlmacen> list = new ArrayList<>();

        String sql = "SELECT "
                + "dma_cla_doc,"
                + "dma_num_bol,"
                + "to_char(cma_fec_gen, 'dd/mm/yyyy') cma_fec_gen,"
                + "(nvl(dma_can_pro, 0) - nvl(dma_can_dev, 0)) cantidad,"
                + "c_t_nombre "
                + "FROM det_mov_alm, cab_mov_alm, Personal "
                + "WHERE dma_cod_pro = TRIM('" + codigoParte + "') "
                + "AND dma_cod_emp = '" + codigoTienda + "' "
                + "AND (dma_can_pro - dma_can_dev) <> 0 "
                + "AND dma_cla_doc = 'MS' "
                + "AND dma_tip_bol = 'S' "
                + "AND dma_cod_emp = cma_cod_emp "
                + "AND dma_cla_doc = cma_cla_doc "
                + "AND dma_tip_bol = cma_tip_bol "
                + "AND dma_num_bol = cma_num_bol "
                + "AND nvl(cma_num_ot, 0) <> 0 "
                + "AND cma_fec_ace IS NULL "
                + "AND TRIM(c_c_codigo(+)) = TRIM(cma_cod_usr)";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    DtoMovimientoAlmacen obj = new DtoMovimientoAlmacen();
                    obj.setClaseDocumento(rs.getString("dma_cla_doc"));
                    obj.setNumeroBoleta(rs.getInt("dma_num_bol"));
                    obj.setFecha(rs.getString("cma_fec_gen"));
                    obj.setCantidad(rs.getInt("cantidad"));
                    obj.setNombre(rs.getString("c_t_nombre"));
                    list.add(obj);
                }

            } catch (SQLException e) {
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

    public DtoMovimientoAlmacen datosBoletaSalida(String codigoTienda, long numeroBoleta, String tipoBoleta, String claseDocumento) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        DtoMovimientoAlmacen boletaSalida = null;

        String sql = "SELECT "
                + "a.cma_cod_usr,"
                + "a.cma_num_bol,"
                + "a.cma_cla_doc,"
                + "a.cma_doc_Ref,"
                + "a.cma_nom_cli AS nombre,"
                + "a.cma_num_ot AS ot,"
                + "a.cma_ord_com AS oc,"
                + "a.cma_tip_ref AS tipref,"
                + "a.cma_doc_ref AS docref,"
                + "a.cma_ser_prv AS serpro,"
                + "a.cma_fac_prv AS docpro,"
                + "b.c_t_nombre AS usuario,"
                + "to_char(a.cma_fec_gen, 'dd/mm/yyyy') AS fecgen,"
                + "to_char(a.cma_fec_ace, 'dd/mm/yyyy') AS fecace,"
                + "a.cma_alm_pro AS AlmOri,"
                + "a.cma_cod_cli "
                + "FROM cab_mov_alm a, personal b "
                + "WHERE a.cma_cod_emp = '" + codigoTienda + "' "
                + "AND a.cma_num_bol = " + numeroBoleta + " "
                + "AND a.cma_tip_bol = '" + tipoBoleta + "' "
                + "AND a.cma_cla_doc = '" + claseDocumento + "' "
                + "AND a.cma_cod_usr = ltrim(rtrim(b.c_c_codigo))";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    boletaSalida = new DtoMovimientoAlmacen();
                    boletaSalida.setNumeroBoleta(rs.getLong("cma_num_bol"));
                    boletaSalida.setNroOT(rs.getLong("ot"));
                    boletaSalida.setNroOC(rs.getLong("oc"));
                    boletaSalida.setCodigoCliente(rs.getString("cma_cod_cli"));
                    boletaSalida.setNombreCliente(rs.getString("nombre"));
                    boletaSalida.setSerieProveedor(rs.getInt("serpro"));
                    boletaSalida.setFacturaProveedor(rs.getLong("docpro"));
                    boletaSalida.setFechaGeneracion(rs.getString("fecgen"));
                    boletaSalida.setFechaAceptacion(rs.getString("fecace"));
                    // boletaSalida.setAlmacenOrigen(rs.getString("AlmOri"));
                    boletaSalida.setCodigoUsuario(rs.getString("cma_cod_usr"));
                    boletaSalida.setNombreUsuario(rs.getString("usuario"));
                    boletaSalida.setTipoDevolucion(rs.getString("tipref"));
                    boletaSalida.setNumeroDevolucion(rs.getLong("docref"));
                    boletaSalida.setClaseDocumento(rs.getString("cma_cla_doc"));
                }

            } catch (SQLException e) {
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

        return boletaSalida;
    }

    public String getAlmacenMovAlm(String codigoTienda, long numeroBoleta, String tipoBoleta, String claseDocumento) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        String almacen = null;

        String sql = "SELECT "
                + "cma_alm_pro "
                + "FROM cab_mov_alm "
                + "WHERE cma_cod_emp = '" + codigoTienda + "' "
                + "AND cma_num_Bol = " + numeroBoleta + " "
                + "AND cma_tip_Bol = '" + tipoBoleta + "' "
                + "AND cma_cla_doc = '" + claseDocumento + "'";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    almacen = rs.getString("cma_alm_pro");
                }

            } catch (SQLException e) {
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

        return almacen;
    }

    public long getNumeroGuia(String codigoTienda, long numeroBoleta, String documentoRef, String claseBoleta) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        long numeroGuia = 0L;

        String sql = "SELECT "
                + "cgg_num_gui "
                + "FROM cab_gui_gen "
                + "WHERE CGG_cod_Emp = '" + codigoTienda + "' "
                + "AND CGG_NUM_rEF = " + numeroBoleta + " "
                + "AND CGG_PRO_GUI = 'R' "
                + "AND CGG_DOC_REF = '" + documentoRef + "' "
                + "AND CGG_CLA_BOL = '" + claseBoleta + "'";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    numeroGuia = rs.getLong("cgg_num_gui");
                }

            } catch (SQLException e) {
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

        return numeroGuia;
    }

    public String getDescripcionAlmacen(String codigoTienda, String codigoAlmacen) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        String descripcionAlmacen = null;

        String sql = "SELECT "
                + "nvl(cag_des_alm, ' ') cag_des_alm "
                + "FROM CAB_ALM_GEN "
                + "WHERE cag_cod_emp = '" + codigoTienda + "' "
                + "AND cag_cod_alm = '" + codigoAlmacen + "'";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    descripcionAlmacen = rs.getString("cag_des_alm");
                }

            } catch (SQLException e) {
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

        return descripcionAlmacen;
    }

    public List<DtoMovimientoAlmacen> listaDetalleBoletaSalida(String codigoTienda, long nroBoleta, String tipoBoleta, String claseDocumento) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        List<DtoMovimientoAlmacen> list = new ArrayList<>();

        String sql = "SELECT "
                + "dma_cod_pro,"
                + "lpr_des_pro,"
                + "dma_can_pro,"
                + "dma_cos_pro,"
                + "round(dma_cos_pro * dma_can_pro, 2) AS wtot "
                + "FROM det_mov_alm, lis_pre_rep "
                + "WHERE dma_cod_emp = '" + codigoTienda + "' "
                + "AND dma_num_bol = " + nroBoleta + " "
                + "AND dma_tip_bol = '" + tipoBoleta + "' "
                + "AND dma_cla_doc = '" + claseDocumento + "' "
                + "AND lpr_cod_pro = dma_cod_pro "
                + "ORDER BY dma_cod_pro";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    DtoMovimientoAlmacen obj = new DtoMovimientoAlmacen();
                    obj.setCodigoProducto(rs.getString("dma_cod_pro"));
                    obj.setDescripcionProducto(rs.getString("lpr_des_pro"));
                    obj.setCantidad(rs.getInt("dma_can_pro"));
                    obj.setCosto(rs.getDouble("dma_cos_pro"));
                    obj.setTotal(rs.getDouble("wtot"));
                    list.add(obj);
                }

            } catch (SQLException e) {
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

    public String delTempoCons(long secuencia) throws Exception {
        LOGGER.info("<==== Inicio Metodo: delTempoCons ====>");
        String sql = "DELETE FROM tempo_cons WHERE seq_temporal=" + secuencia;
        String result = Util.sql_ejecuta(sql, null);
        LOGGER.trace("secuencia: {}", secuencia);
        LOGGER.trace("result: {}", result);
        LOGGER.info("<==== Fin Metodo: delTempoCons ====>");

        return result;
    }

    public List<DtoMovimientoAlmacen> consultaTempoCons(long secuencia) throws Exception {
        LOGGER.info("<==== Inicio Metodo: consultaTempoCons ====>");
        LOGGER.trace("secuencia: {}", secuencia);

        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        List<DtoMovimientoAlmacen> list = new ArrayList<>();

        String sql = "SELECT Num_Doc AS Bol_Salida,"
                + "ubicacion AS Clase,"
                + "COD_MOBRA AS Fecha_Gen,"
                + "Descripcion1 AS Empleado,"
                + "to_char(VVP_SOL_UNI, '999999') AS Stock_Ant,"
                + "CANTIDAD AS Cantidad,"
                + "to_char(VVP_DOL_UNI, '999999') AS Stock_Pos,"
                + "to_char(VVD_SOL_UNI, '999999') AS Stock_Taller,"
                + "Descripcion2 AS OT,"
                + "OTRO1 AS Tipo,"
                + "decode(OTRO2, 0, OTRO2, cod_mec || '-' || OTRO2) AS Docu,"
                + "COD_EMPRESA AS CoCr,"
                + "to_char(fecha, 'DD/MM/YYYY') AS Fecha_Fac,"
                + "aceptado "
                + "FROM tempo_cons "
                + "WHERE SEQ_TEMPORAL = " + secuencia + " "
                + "ORDER BY num_item";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    DtoMovimientoAlmacen obj = new DtoMovimientoAlmacen();
                    obj.setNumeroBoleta(rs.getLong("Bol_Salida"));
                    obj.setClaseDocumento(rs.getString("Clase"));
                    obj.setFecha(rs.getString("Fecha_Gen"));
                    obj.setEmpleado(rs.getString("Empleado"));
                    obj.setStockAnterior(rs.getInt("Stock_Ant"));
                    obj.setCantidad(rs.getInt("Cantidad"));
                    obj.setStockPosterior(rs.getInt("Stock_Pos"));
                    obj.setStockTaller(rs.getInt("Stock_Taller"));
                    obj.setNroOT(rs.getLong("OT"));
                    obj.setTipo(rs.getString("Tipo"));
                    obj.setDocumento(rs.getString("Docu"));
                    obj.setCocr(rs.getString("CoCr"));
                    obj.setFechaFacturacion(rs.getString("Fecha_Fac"));
                    obj.setAceptado(rs.getString("aceptado"));
                    list.add(obj);
                }

            } catch (Exception e) {
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

        LOGGER.info("<==== Fin Metodo: consultaTempoCons ====>");

        return list;
    }

    public List<DtoMovimientoAlmacen> consultaTempoCons2(long secuencia) throws Exception {
        LOGGER.info("<==== Inicio Metodo: consultaTempoCons2 ====>");
        LOGGER.trace("secuencia {}: ", secuencia);

        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        List<DtoMovimientoAlmacen> list = new ArrayList<>();

        String sql = "SELECT ubicacion AS Clase,"
                + "SUM(Cantidad) AS Cantidad,"
                + "DECODE(OTRO2, 0, 0, 1) AS OT,"
                + "TRIM(COD_EMPRESA) AS Co_Cr "
                + "FROM tempo_cons "
                + "WHERE SEQ_TEMPORAL = " + secuencia + " "
                + "GROUP BY UBICACION, DECODE(OTRO2, 0, 0, 1), TRIM(COD_EMPRESA) "
                + "ORDER BY UBICACION";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    DtoMovimientoAlmacen obj = new DtoMovimientoAlmacen();
                    obj.setClaseDocumento(rs.getString("Clase"));
                    obj.setCantidad(rs.getInt("Cantidad"));
                    obj.setNroOT(rs.getLong("OT"));
                    obj.setCocr(rs.getString("Co_Cr"));
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

        LOGGER.info("<==== Fin Metodo: consultaTempoCons2 ====>");

        return list;
    }

    public int getStockAnuSRA(String codigoTienda, int mes, int ano, String codigoAlmacen, String codigoParte) throws Exception {
        LOGGER.info("<==== Inicio Metodo: getStockAnuSRA ====>");

        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        int stockAnu = 0;

        String sql = "SELECT "
                + "(nvl(sra_stk_dis, 0) + nvl(sra_stk_seg, 0)) AS Stk_Anu "
                + "FROM stk_rep_anu "
                + "WHERE sra_cod_emp = '" + codigoTienda + "' "
                + "AND sra_num_ano = " + mes + " "
                + "AND sra_cod_pro = '" + codigoParte + "' "
                + "AND sra_cod_alm = '" + codigoAlmacen + "'";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    stockAnu = rs.getInt("Stk_Anu");
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

        LOGGER.info("<==== Fin Metodo: getStockAnuSRA ====>");

        return stockAnu;
    }

    public String rastreoCodigo(long secuencia, String codigoTienda, String fecha, String vtaRs, String numeroParte, int stockInicioMes, String ubicacion) throws Exception {
        LOGGER.info("<==== Inicio Metodo: rastreoCodigo ====>");

        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        String result = null;

        if (conexion != null) {
            try {
                cs = conexion.prepareCall("{call PANAAUTOS.SP_RASTREO_CODIGO(?,?,?,?,?,?,?)}");
                cs.setLong(1, secuencia);
                cs.setString(2, codigoTienda);
                cs.setString(3, fecha);
                cs.setString(4, vtaRs);
                cs.setString(5, numeroParte);
                cs.setInt(6, stockInicioMes);
                cs.setString(7, ubicacion);
                cs.executeUpdate();

            } catch (Exception e) {
                LOGGER.error("GP.ERROR: {}", e);
                throw e;
            } finally {
                try {
                    conexion.close(); // libera conexion
                } catch (Exception e) {
                }
                try {
                    if (cs != null) {
                        cs.close();
                    }
                } catch (Exception e) {
                }
            }
        }

        LOGGER.info("<==== Fin Metodo: rastreoCodigo ====>");

        return result;
    }

    public int getStockInicial(long secuencia) throws Exception {
        LOGGER.info("<==== Inicio Metodo: getStockInicial ====>");
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        int stockInicial = 0;

        String sql = "SELECT DISTINCT (tot_sol) AS Stock_Inicial "
                + "FROM tempo_cons "
                + "WHERE SEQ_TEMPORAL = " + secuencia + "";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    stockInicial = rs.getInt("Stock_Inicial");
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

        LOGGER.info("<==== Fin Metodo: getStockInicial ====>");

        return stockInicial;
    }

    public List<ListaGenerica> listaMarcasTDP() throws Exception {
        /*String sql = "SELECT "
                + "cmv_cod_mar, "
                + "cmv_des_mar "
                + "FROM cab_mar_veh "
                + "WHERE cmv_cod_tdp = '1'";
        ResultSet rs = Util.sql_consulta(sql, null);
        List<ListaGenerica> list = new ArrayList<>();
        while (rs.next()) {
            ListaGenerica obj = new ListaGenerica();
            obj.setIndice(rs.getString("cmv_cod_mar"));
            obj.setDescripcion(rs.getString("cmv_des_mar"));
            list.add(obj);
        }
        return list;*/
        LOGGER.info("<==== Inicio Metodo: listaMarcasTDP ====>");
        Connection cn = new ConectaDb().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ListaGenerica> list = new ArrayList<>();
        try {
            String sql = "SELECT "
                    + "cmv_cod_mar, "
                    + "cmv_des_mar "
                    + "FROM cab_mar_veh "
                    + "WHERE cmv_cod_tdp = '1'";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ListaGenerica obj = new ListaGenerica();
                obj.setIndice(rs.getString("cmv_cod_mar"));
                obj.setDescripcion(rs.getString("cmv_des_mar"));
                list.add(obj);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(ps);
        }
        LOGGER.info("<==== Fin Metodo: listaMarcasTDP ====>");
        return list;
    }

    public List<ListaGenerica> listaClasesProductos() throws Exception {
        /*String sql = "SELECT "
                + "par_cod_id, "
                + "UPPER(par_desc) par_desc "
                + "FROM parametro "
                + "WHERE par_tipo = '000621'";
        ResultSet rs = Util.sql_consulta(sql, null);
        List<ListaGenerica> list = new ArrayList<>();
        while (rs.next()) {
            ListaGenerica obj = new ListaGenerica();
            obj.setIndice(rs.getString("par_cod_id"));
            obj.setDescripcion(rs.getString("par_desc"));
            list.add(obj);
        }
        return list;*/
        LOGGER.info("<==== Inicio Metodo: listaClasesProductos ====>");
        Connection cn = new ConectaDb().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ListaGenerica> list = new ArrayList<>();
        try {
            String sql = "SELECT "
                    + "par_cod_id, "
                    + "UPPER(par_desc) par_desc "
                    + "FROM parametro "
                    + "WHERE par_tipo = '000621'";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ListaGenerica obj = new ListaGenerica();
                obj.setIndice(rs.getString("par_cod_id"));
                obj.setDescripcion(rs.getString("par_desc"));
                list.add(obj);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(ps);
        }
        LOGGER.info("<==== Fin Metodo: listaClasesProductos ====>");
        return list;
    }

    public List<ListaGenerica> listaTiposProductos() throws Exception {
//        String sql = "SELECT "
//                + "cmt_cod_mer, "
//                + "cmt_des_mer "
//                + "FROM PANAAUTOS.cod_mer_tdp ORDER BY 1";
//        ResultSet rs = Util.sql_consulta(sql, null);
//        List<ListaGenerica> list = new ArrayList<>();
//        while (rs.next()) {
//            ListaGenerica obj = new ListaGenerica();
//            obj.setIndice(rs.getString("cmt_cod_mer"));
//            obj.setDescripcion(rs.getString("cmt_des_mer"));
//            list.add(obj);
//        }
//        return list;
        LOGGER.info("<==== Inicio Metodo: listaTiposProductos ====>");
        Connection cn = new ConectaDb().connection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ListaGenerica> list = new ArrayList<>();
        try {
            String sql = "SELECT "
                    + "cmt_cod_mer, "
                    + "cmt_des_mer "
                    + "FROM PANAAUTOS.cod_mer_tdp ORDER BY 1";
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ListaGenerica obj = new ListaGenerica();
                obj.setIndice(rs.getString("cmt_cod_mer"));
                obj.setDescripcion(rs.getString("cmt_des_mer"));
                list.add(obj);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(ps);
        }
        LOGGER.info("<==== Fin Metodo: listaTiposProductos ====>");
        return list;
    }

    public int getStockTotal(String numeroParte) throws Exception {
        ResultSet rs = null;
        Connection conexion = new ConectaDb().connection();
        Statement stmt = null;
        int cantidad = 0;

        String sql = "SELECT "
                + "SUM(nvl(srg_stk_tot, 0)) AS stock "
                + "FROM stk_rep_gen "
                + "WHERE TRIM(srg_cod_pro) = TRIM('" + numeroParte + "')";

        if (conexion != null) {
            try {
                stmt = conexion.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    cantidad = rs.getInt("stock");
                    break;
                }

            } catch (SQLException e) {
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

        return cantidad;
    }

    /**
     * Elimina una parte de lis_pre_rep
     *
     * @param codigoParte codigo de la parte (repuesto).
     * @return null si todo ok, sino devuelve mensaje de error.
     * @throws Exception
     */
    public String eliminaParte(String codigoParte) throws Exception {
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        String result = null;
        try {
            cs = conexion.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.sp_eliminar_parte(?)}");
            cs.setString(1, codigoParte);
            cs.executeUpdate();
        } catch (Exception e) {
            result = e.getMessage();
        } finally {
            try {
                conexion.close(); // libera conexion
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

    public String insertarParte(Parte parte) throws Exception {
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        String result = null;
        try {
            cs = conexion.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.SP_INSERTA_PARTE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, parte.getCodigo());
            cs.setString(2, parte.getDescripcion());
            cs.setString(3, parte.getUnidadMedida());
            cs.setString(4, parte.getTipo());
            cs.setString(5, parte.getProcedencia());
            cs.setDouble(6, parte.getVVPSol());
            cs.setDouble(7, parte.getVVDSol());
            cs.setDouble(8, parte.getVVPDol());
            cs.setDouble(9, parte.getVVDDol());
            cs.setDouble(10, parte.getDsctoMostrador());
            cs.setDouble(11, parte.getDsctoOT());
            cs.setDouble(12, parte.getDsctoCiaSeguro());
            cs.setDouble(13, parte.getDsctoOtros());
            cs.setString(14, parte.getTipoDescuento());
            cs.setString(15, parte.getMarca());
            cs.setString(16, parte.getTipoFactor());
            cs.setDouble(17, parte.getDsctoMaximo());
            cs.setString(18, parte.getEsObsequio());
            cs.setString(19, parte.getMarcaTDP());
            cs.setString(20, parte.getClaseProducto());
            cs.executeUpdate();

        } catch (Exception e) {
            result = e.getMessage();
        } finally {
            try {
                conexion.close(); // libera conexion
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

    public String actualizarParte(Parte parte, String codigoUsuario) throws Exception {
        Connection conexion = new ConectaDb().connection();
        CallableStatement cs = null;
        String result = null;
        try {
            cs = conexion.prepareCall("{call PANAAUTOS.PKG_WEB_TALLER_MOVIL.SP_ACTUALIZA_PARTE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            cs.setString(1, parte.getCodigo());
            cs.setString(2, parte.getDescripcion());
            cs.setString(3, parte.getUnidadMedida());
            cs.setString(4, parte.getTipo());
            cs.setString(5, parte.getProcedencia());
            cs.setDouble(6, parte.getVVPSol());
            cs.setDouble(7, parte.getVVDSol());
            cs.setDouble(8, parte.getVVPDol());
            cs.setDouble(9, parte.getVVDDol());
            cs.setDouble(10, parte.getDsctoMostrador());
            cs.setDouble(11, parte.getDsctoOT());
            cs.setDouble(12, parte.getDsctoCiaSeguro());
            cs.setDouble(13, parte.getDsctoOtros());
            cs.setString(14, parte.getTipoDescuento());
            cs.setString(15, parte.getMarca());
            cs.setString(16, parte.getTipoFactor());
            cs.setDouble(17, parte.getDsctoMaximo());
            cs.setString(18, parte.getEsObsequio());
            cs.setString(19, codigoUsuario);
            cs.setString(20, parte.getMarcaTDP());
            cs.setString(21, parte.getClaseProducto());
            cs.executeUpdate();

        } catch (Exception e) {
            result = e.getMessage();
        } finally {
            try {
                conexion.close(); // libera conexion
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

    public List<Parte> consultaRepVvpMargen(String numeroParte, int porcentaje, String codTienda) throws Exception {
        LOGGER.info("<==== Inicio Metodo: consultaRepVvpMargen ====>");
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Parte> list = new ArrayList<>();
        try {
            cs = cn.prepareCall("{call panaautos.pkg_web_taller_movil.sp_repuestos_vvp_margen(?,?,?,?)}");
            cs.setString(1, numeroParte);
            cs.setInt(2, porcentaje);
            cs.setString(3, codTienda);
            cs.registerOutParameter(4, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(4);
            while (rs.next()) {
                Parte obj = new Parte();
                obj.setCodigo(rs.getString("codigo"));
                obj.setDescripcion(rs.getString("lpr_des_pro"));
                obj.setStkDis(rs.getInt("srg_stk_dis"));
                obj.setVVPSol(rs.getDouble("lpr_vvp_sol"));
                obj.setCostoPromedio(rs.getDouble("srg_ucos_pro"));
                obj.setDsctoMaximo(rs.getDouble("dscto_max"));
                obj.setMargen(rs.getDouble("porc"));
                obj.setVvp20(rs.getDouble("ochenta"));
                obj.setVvp30(rs.getDouble("setenta"));
                obj.setVvp40(rs.getDouble("sesenta"));
                obj.setVvp50(rs.getDouble("cincuenta"));
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
        LOGGER.info("<==== Fin Metodo: consultaRepVvpMargen ====>");
        return list;
    }

    public List<Map<String, Object>> consultaFacRepCliFecha(String codEmp, String codCli, String tipoReporte, String soloReporte, String traGratuita, String fechaIni, String fechaFin) throws Exception {
        LOGGER.info("<==== Inicio Metodo: consultaFacRepCliFecha ====>");
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            cs = cn.prepareCall("{call panaautos.pkg_web_taller_movil.sp_repuestos_fac_cli_fecha(?,?,?,?,?,?,?,?)}");
            cs.setString(1, codEmp);
            cs.setString(2, codCli);
            cs.setString(3, tipoReporte);
            cs.setString(4, soloReporte);
            cs.setString(5, traGratuita);
            cs.setString(6, fechaIni);
            cs.setString(7, fechaFin);
            cs.registerOutParameter(8, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(8);
            while (rs.next()) {
                Map<String, Object> map = new HashMap<>();
                switch (tipoReporte) {
                    case "DET":
                        map.put("tipDoc", rs.getString("TipDoc"));
                        map.put("numDoc", rs.getLong("NumDoc"));
                        map.put("fecDoc", rs.getString("FecDoc"));
                        map.put("codigo", rs.getString("codigo"));
                        map.put("descripcion", rs.getString("Descripcion"));
                        map.put("tipDes", rs.getString("tipdes"));
                        map.put("cantidad", rs.getInt("Canti"));
                        map.put("vvpUnit", rs.getDouble("VVP_Unit"));
                        map.put("descuento", rs.getDouble("Dcto"));
                        map.put("totalVVP", rs.getDouble("TotPVP"));
                        break;
                    default:
                        map.put("tipDoc", rs.getString("TipDoc"));
                        map.put("numDoc", rs.getLong("NumDoc"));
                        map.put("fecDoc", rs.getString("FecDoc"));
                        map.put("vvp", rs.getDouble("vvp"));
                        map.put("descuento", rs.getDouble("descu"));
                        map.put("totalPVP", rs.getDouble("TotPVP"));
                        map.put("bolAlm", rs.getLong("cdg_bol_alm"));
                        map.put("ot", rs.getLong("ot"));
                        map.put("guiaRemision", rs.getLong("guia"));
                        map.put("tipCam", rs.getDouble("Cambio"));
                        break;
                }
                list.add(map);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(cs);
        }
        LOGGER.info("<==== Fin Metodo: obtieneDatosTmpNumDoc ====>");
        return list;
    }

    public List<Parte> detalleFacturaRepuestos(String codTienda, String procedeDoc, String tipoDoc, long serieDoc, long numeroDoc) throws Exception {
        Connection cn = new ConectaDb().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Parte> list = new ArrayList<>();
        try {
            cs = cn.prepareCall("{call panaautos.PKG_REPUESTOS_WEB.sp_detalle_factura_rep(?,?,?,?,?,?)}");
            cs.setString(1, codTienda);
            cs.setString(2, procedeDoc);
            cs.setString(3, tipoDoc);
            cs.setLong(4, serieDoc);
            cs.setLong(5, numeroDoc);
            cs.registerOutParameter(6, OracleTypes.CURSOR);
            cs.execute();
            rs = (ResultSet) cs.getObject(6);
            while (rs.next()) {
                Parte obj = new Parte();
                obj.setCodigoProducto(rs.getString("ddr_cod_pro"));
                obj.setDescripcion(rs.getString("lpr_des_pro"));
                obj.setCantidad(rs.getInt("ddr_can_pro"));
                obj.setDescuento(rs.getDouble("ddr_por_des"));
                obj.setIgv(rs.getDouble("ddr_por_igv"));
                obj.setVvp(rs.getDouble("precio_uni"));
                obj.setVvpDsc(rs.getDouble("vvp_dsc"));
                obj.setUbicacion(rs.getString("ubicacion"));
                list.add(obj);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(cs);
            Util.close(rs);
        }
        return list;
    }
}
