/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pe.com.gp.connection.ConectaDb;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.HelpGenerico;
import pe.com.gp.util.Util;

/**
 *
 * @author Administrador
 */
public class HelpDAO {
    
   public List<HelpGenerico> buscarCliente(String empresa,String filtro,String tipoBusqueda) throws Exception {
        Connection cn = new ConectaSQL().connection();
        Statement stmt = null;
        ResultSet rs = null;
        List<HelpGenerico> list = new ArrayList<>();
        HelpGenerico obj ;
        if (filtro.equals(""))
                filtro="'%'";
        else
            filtro = "'%"  + filtro + "%'";
        try {
            String sql = "select top 30 c_c_codigo,c_t_razon_social from cliente where c_c_empresa='" + empresa + "'" ;                   
            if (tipoBusqueda.equals("COD"))
                sql = sql + " and c_c_codigo like "  + filtro  ;
            else
                sql = sql + " and c_t_razon_social like "  + filtro  ;
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                obj = new HelpGenerico();
                obj.setCodigo(rs.getString("c_c_codigo"));
                obj.setDescripcion(rs.getString("c_t_razon_social"));                   
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

   public List<HelpGenerico> buscarProductos(String empresa,String filtro,String tipoBusqueda) throws Exception {
        Connection cn = new ConectaSQL().connection();
        Statement stmt = null;
        ResultSet rs = null;
        List<HelpGenerico> list = new ArrayList<>();
        HelpGenerico obj ;
        if (filtro.equals(""))
                filtro="'%'";
        else
            filtro = "'%"  + filtro + "%'";
        try {
            String sql = "select top 30 c_c_codigo_prod c_c_codigo ,c_t_codigo_prod c_t_codigo_prod from productos where productos.c_c_empresa='" + empresa + "'" ;                   
            if (tipoBusqueda.equals("COD"))
                sql = sql + " and c_c_codigo_prod like "  + filtro  ;
            else
                sql = sql + " and c_t_codigo_prod like "  + filtro  ;
            stmt = cn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                obj = new HelpGenerico();
                obj.setCodigo(rs.getString("c_c_codigo"));
                obj.setDescripcion(rs.getString("c_t_codigo_prod"));                   
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
    
}
