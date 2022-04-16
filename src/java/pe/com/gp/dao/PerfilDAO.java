/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaHana;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.DtoUsuario;
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.util.Constantes;
import pe.com.gp.util.Util;

/**
 *
 * @author GPPC588
 */
public class PerfilDAO {

    private static final Logger LOGGER = LogManager.getLogger();

   /* public void EliminarItemPerfil(String sistema, String perfil, String indice ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        String sql = " DELETE  FROM  \"GP_PERFIL_MENU\" WHERE \"U_SISTEMAS\"='" + sistema +"' AND \"U_INDICE\"='" + perfil + "' AND \"U_ITEM\"='" + indice + "'";
        String result = Util.nullCad(Util.sql_ejecutaHana(sql, null));
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
    }    */
    
    public void EliminarItemPerfil(String rucEmpresa, String sistema, String perfil, String indice ) throws Exception {
        LOGGER.info("<==== Inicio Metodo : ticketBajasINS ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        try {
            cs = cn.prepareCall("{call relaciones.dbo.perfil_eliminar_item_perfil(?,?,?,?)}");
            cs.setString(1, rucEmpresa);
            cs.setString(2, sistema);
            cs.setString(3, indice);
            cs.setString(4, perfil);
            cs.execute();

        } catch (Exception ex) {
            LOGGER.error("ERROR: {}", ex);
            System.out.println(ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        LOGGER.info("<==== Fin Metodo : ticketBajasINS ====>");
    }      
        

    public List<ListaGenerica> muestraAsignados(String sistama, String perfil) throws Exception {
        Connection cn = new ConectaHana().connection();
        Statement stmt = null;
        ResultSet rs = null;
        List<ListaGenerica> list = new ArrayList<>();
        try {
            /*
            String sql = "select \"U_ITEM\",  \n"
                    + "\"GP_MENU\".\"U_ORDEN\" U_INDICE ,\"GP_MENU\".\"U_NOMBRE\" U_DESCRIPCION,\"GP_MENU\".\"U_INDICENOMBRE\"  from \""+Constantes.HANNA_BASEDATOS+"\".\"GP_PERFIL_MENU\" , \""+Constantes.HANNA_BASEDATOS+"\".\"GP_MENU\"  where \n"
                    + " \"GP_MENU\".\"U_SISTEMA\"='" + sistama + "'\n"
                    + " AND \"GP_MENU\".\"U_SISTEMA\"=\"GP_PERFIL_MENU\".\"U_SISTEMAS\"\n"
                    + " AND \"U_INDICE\"='" + perfil + "'\n"
                    + " AND \"GP_PERFIL_MENU\".\"U_INDICENOMBRE\" = \"GP_MENU\".\"U_INDICENOMBRE\"\n"
                    + " ORDER BY \"U_ORDEN\" ";*/
            String sql = "";
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ListaGenerica obj = new ListaGenerica();
                obj.setIndice(rs.getString("U_INDICENOMBRE"));
                //obj.setItem(rs.getString("U_ITEM"));
                obj.setDescripcion(rs.getString("U_DESCRIPCION"));
                list.add(obj);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(stmt);
        }
        return list;
    }  
    

    public void AgregarItemPerfil(String rucEmpresa,String sistema, String indice, String perfil) throws Exception {
        LOGGER.info("<==== Inicio Metodo : ticketBajasINS ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        try {
            cs = cn.prepareCall("{call relaciones.dbo.perfil_agregar_item_perfil(?,?,?,?)}");
            cs.setString(1, rucEmpresa);
            cs.setString(2, sistema);
            cs.setString(3, indice);
            cs.setString(4, perfil);
            cs.execute();

        } catch (Exception ex) {
            LOGGER.error("ERROR: {}", ex);
            System.out.println(ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        LOGGER.info("<==== Fin Metodo : ticketBajasINS ====>");
    }

    public String AgregarPerfil(
            String rucEmpresa, String sistema, String perfil, String descripcion

    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: insertaParteEnTempo ====>");
        String sp = "{call  relaciones.dbo.perfil_actualizaPerfil(?,?,?,?)}";
        Object[] paramIN = { rucEmpresa,sistema, perfil,descripcion};
        String result = Util.nullCad(Util.sp_ejecuta(sp, null, paramIN));        
        return result;
    }      
    
    /*
    public void AgregarPerfil(String rucEmpresa, String sistema, String perfil, String descripcion) throws Exception {
        LOGGER.info("<==== Inicio Metodo : ticketBajasINS ====>");
        Connection conexion = new ConectaHana().connection();
        CallableStatement cs = null;
        try {
            cs.setString(1, sistema);
            cs.setString(2, "indice");
            cs.setString(3, perfil);
            cs.setString(4, "estado");
            cs.execute();

        } catch (Exception ex) {
            LOGGER.error("ERROR: {}", ex);
            System.out.println(ex.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        LOGGER.info("<==== Fin Metodo : ticketBajasINS ====>");
    }
*/
    
    public List<ListaGenerica> muestraLista(String rucEmpresa,String sistema) throws Exception {
        Connection cn = new ConectaSQL().connection();
        Statement stmt = null;
        ResultSet rs = null;
        List<ListaGenerica> list = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.perfil_ListaPerfiles(?,?)}");
            cs.setString(1, rucEmpresa);
            cs.setString(2, sistema);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                ListaGenerica obj = new ListaGenerica();
                obj.setIndice(rs.getString("U_INDICE"));
                obj.setDescripcion(rs.getString("U_DESCRIPCION"));

                list.add(obj);
            }            
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(stmt);
        }
        return list;
    }

    public List<DtoUsuario> muestraUsuarioPerfil(String rucempresa, String dni,String sistema) throws Exception {
        Connection cn = new ConectaSQL().connection();
        Statement stmt = null;
        ResultSet rs = null;
        List<DtoUsuario> list = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.LISTA_EMPRESAS_USUARIOS(?,?,?)}");
            cs.setString(1, rucempresa);
            cs.setString(2, dni);
            cs.setString(3, sistema);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                DtoUsuario obj = new DtoUsuario();
                obj.setCodigo(rs.getString("c_c_dni"));
                obj.setNombre(rs.getString("nombre"));
                obj.setPerfil(rs.getString("c_c_perfil"));
                obj.setNombrePerfil(rs.getString("c_t_nombre_perfil"));
                obj.setActivo(rs.getString("activo"));
                obj.setUsuario(rs.getString("c_c_usuario"));
                obj.setPassword(rs.getString("c_c_contrasenha"));
                list.add(obj);
            }            
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(stmt);
        }
        return list;
    }

    public List<ListaGenerica> muestraPedientes(String sistama, String perfil) throws Exception {
        Connection cn = new ConectaHana().connection();
        Statement stmt = null;
        ResultSet rs = null;
        List<ListaGenerica> list = new ArrayList<>();
        try {
            String sql = ""; /*SELECT \n"
                    + "\"GP_MENU\".\"U_NOMBRE\" NOMBRE,\n"
                    + "\"GP_MENU\".\"U_INDICENOMBRE\",\n"
                    + "\"GP_MENU\".\"U_AREA\",\n"
                    + "\"GP_MENU\".\"U_ORDEN\"\n"
                    + "FROM \""+Constantes.HANNA_BASEDATOS+"\".\"GP_MENU\" \n"
                    + "WHERE\n"
                    + "\"GP_MENU\".\"U_SISTEMA\"='" + sistama + "' \n"
                    + "AND \n"
                    + "\"GP_MENU\".\"U_INDICENOMBRE\" NOT IN (\n"
                    + "	 SELECT \n"
                    + "	\"GP_PERFIL_MENU\".\"U_INDICENOMBRE\"\n"
                    + "	 from \""+Constantes.HANNA_BASEDATOS+"\".\"GP_PERFIL_MENU\" , \""+Constantes.HANNA_BASEDATOS+"\".\"GP_MENU\" \n"
                    + "	 where \n"
                    + "	 \"GP_MENU\".\"U_SISTEMA\"='" + sistama + "'\n"
                    + "	 AND \"GP_MENU\".\"U_SISTEMA\"=\"GP_PERFIL_MENU\".\"U_SISTEMAS\"\n"
                    + "	 AND \"U_INDICE\"='" + perfil + "'\n"
                    + "	 AND \"GP_PERFIL_MENU\".\"U_INDICENOMBRE\" = \"GP_MENU\".\"U_INDICENOMBRE\"\n"
                    + "	 ORDER BY \"U_ORDEN\"\n"
                    + " )\n"
                    + " ORDER BY \"GP_MENU\".\"U_AREA\",\"GP_MENU\".\"U_ORDEN\"";*/
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ListaGenerica obj = new ListaGenerica();
                obj.setIndice(rs.getString("U_INDICENOMBRE"));
                obj.setDescripcion(rs.getString("NOMBRE"));
                list.add(obj);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
            Util.close(stmt);
        }
        return list;
    }

    
    public List<ListaGenerica> ListarItemsPerfil(String rucEmpresa,String sistema,String perfil) throws Exception {
        LOGGER.info("<==== Inicio Metodo : ticketBajasINS ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        List<ListaGenerica> list = new ArrayList<>();
        try {
            cs = cn.prepareCall("{call relaciones.dbo.perfil_ListaItemsPerfil(?,?,?)}");
            cs.setString(1, rucEmpresa);
            cs.setString(2, sistema);
            cs.setString(3, perfil);
            rs = cs.executeQuery();
            while (rs.next()) {
                ListaGenerica obj = new ListaGenerica();
                obj.setIndice(rs.getString("c_t_opcion"));
                obj.setDescripcion(rs.getString("c_t_nombre"));
                obj.setRegistro(rs.getInt("existe"));
               // obj.setAux(rs.getString("ASIGNADO"));
                list.add(obj);
            }
        } catch (Exception ex) {
            LOGGER.error("ERROR: {}", ex);
            System.out.println(ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        LOGGER.info("<==== Fin Metodo : ticketBajasINS ====>");
        return list;
    }
    
    public void AgregarContactoPerfil(String pIdPerfil, String pIdGrupoContacto) throws Exception {
        LOGGER.info("<==== Inicio Metodo : ticketBajasINS ====>");
        Connection conexion = new ConectaHana().connection();
        CallableStatement cs = null;
        try {
            cs = conexion.prepareCall("{call GP_AGREGAR_CONTACTO_PERFIL(?,?)}");
            cs.setString(1, pIdPerfil);
            cs.setString(2, pIdGrupoContacto);
            cs.execute();

        } catch (Exception ex) {
            LOGGER.error("ERROR: {}", ex);
            System.out.println(ex.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        LOGGER.info("<==== Fin Metodo : ticketBajasINS ====>");
    }
    
    public void EliminarContactoPerfil(String pIdPerfil, String pIdGrupoContacto) throws Exception {
        LOGGER.info("<==== Inicio Metodo : ticketBajasINS ====>");
        Connection conexion = new ConectaHana().connection();
        CallableStatement cs = null;
        try {
            cs = conexion.prepareCall("{call GP_PERFIL_ELIMINAR_CONTACTO(?,?)}");
            cs.setString(1, pIdPerfil);
            cs.setString(2, pIdGrupoContacto);
            cs.execute();

        } catch (Exception ex) {
            LOGGER.error("ERROR: {}", ex);
            System.out.println(ex.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        LOGGER.info("<==== Fin Metodo : ticketBajasINS ====>");
    }
    
    public void GrabarEditUsuarioPerfil(String rucEmpresa, String dni, String perfil, String nombre,String activo,String usuario,String contrasenha ) throws Exception {
        LOGGER.info("<==== Inicio Metodo : ticketBajasINS ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        try {
            cs = cn.prepareCall("{call relaciones.dbo.perfil_GrabarEditUsuarioPerfil(?,?,?,?,?,?,?)}");
            cs.setString(1, rucEmpresa);
            cs.setString(2, dni);
            cs.setString(3, perfil);
            cs.setString(4, nombre);
            cs.setString(5, activo);
            cs.setString(6, usuario);
            cs.setString(7, contrasenha);
            cs.execute();

        } catch (Exception ex) {
            LOGGER.error("ERROR: {}", ex);
            System.out.println(ex.getMessage());
        } finally {
            try {
                cn.close();
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        LOGGER.info("<==== Fin Metodo : ticketBajasINS ====>");
    }          
    
}
