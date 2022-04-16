/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import pe.com.gp.dao.PerfilDAO;
import pe.com.gp.dao.TiendaDAO;
import pe.com.gp.entity.DtoUsuario;
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.PerfilForm;
import pe.com.gp.form.RegistroVentasForm;
import pe.com.gp.util.Constantes;
import pe.com.gp.util.Util;

/**
 *
 * @author GPPC588
 */
public class PerfilAction extends DispatchAction {

	private static final Logger LOGGER = LogManager.getLogger();
        

	public ActionForward inicializaAsignar(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");              
		String mappingFindForward;
                PerfilForm uform = (PerfilForm) form;
		if (Util.sesionEstaActiva(request)) {
			HttpSession session = request.getSession();
                        Usuario usuario = (Usuario) session.getAttribute("Usuario");  
                        uform.setRucEmpresa(usuario.getRucEmpresa());
                        uform.setSistema(Constantes.SISTEMA);                        
			muestaLista(request,uform);
			mappingFindForward = "inicializaAsignar";
		} else {
			mappingFindForward = "logout";
		}
                   
		LOGGER.info("<==== Fin Metodo: inicializa ====>");
		return mapping.findForward(mappingFindForward);
	}
        
	public ActionForward inicializa(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		String mappingFindForward;
		if (Util.sesionEstaActiva(request)) {
                    
			HttpSession session = request.getSession();
                        Usuario usuario = (Usuario) session.getAttribute("Usuario");  
			PerfilForm uform = (PerfilForm) form;
                        uform.setRucEmpresa(usuario.getRucEmpresa());
                        uform.setSistema(Constantes.SISTEMA);
			mappingFindForward = "inicializa";
                        muestaLista(request,uform);  
		} else {
			mappingFindForward = "logout";
		}
                
		LOGGER.info("<==== Fin Metodo: inicializa ====>");
		return mapping.findForward(mappingFindForward);
	}

        public void muestaLista(HttpServletRequest request,PerfilForm form)  throws Exception 
        {                
                request.setAttribute("listaPerfiles", new PerfilDAO().muestraLista(form.getRucEmpresa(),form.getSistema())); 
        }         
	public void muestraTabla(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
                HttpSession session = request.getSession();
                Usuario usuario = (Usuario) session.getAttribute("Usuario");
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		PerfilForm uform = (PerfilForm) form;
		JSONObject jsonObjTabla = new JSONObject();
		try {

			StringBuilder sbLista = new StringBuilder();
			List<ListaGenerica> listaImagenes = new PerfilDAO().muestraLista(usuario.getRucEmpresa(),Constantes.SISTEMA);
			if (listaImagenes != null && !listaImagenes.isEmpty()) {

				sbLista.append("<table id=\"tablaPerfil\">");
				sbLista.append("<thead>");
				sbLista.append("<tr>");
				sbLista.append("<th>Tienda</th>");
				sbLista.append("<th>Nombre</th>");
				sbLista.append("<th></th>");
				sbLista.append("<th></th>");
				sbLista.append("</tr>");
				sbLista.append("</thead>");
				sbLista.append("<tbody>");
				for (ListaGenerica img : listaImagenes) {
					sbLista.append("<tr>");
					sbLista.append("<td>").append(img.getIndice()).append("</td>");
					sbLista.append("<td>").append(img.getDescripcion()).append("</td>");
					sbLista.append(
							"<td class=\"text-center\"><a class=\"btn btn-xs btn-default\" onclick=\"EditarPerfil('"
									+ img.getIndice() + "','" + img.getDescripcion() + "')\"")
							.append(" title=\"Editar\">").append(" Editar </a></td>");
					sbLista.append(
							"<td class=\"text-center\"><a class=\"btn btn-xs btn-default\" onclick=\"AgregarOpciones('"
									+ img.getIndice() + "','" + img.getDescripcion() + "')\"")
							.append(" title=\"Editar\">").append(" Agregar Opciones perfil </a></td>");

					sbLista.append("</tr>");
				}
				sbLista.append("</tbody>");
				sbLista.append("</table>");
				jsonObjTabla.put("tabla", sbLista.toString());
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("objTabla", jsonObjTabla);
			sb.append(jsonObject.toString());
			out = response.getWriter();
			out.write(sb.toString());
		} catch (Exception e) {
			LOGGER.error("GP.ERROR: {}", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

public void agregarPerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
                HttpSession session = request.getSession();
                Usuario usuario = (Usuario) session.getAttribute("Usuario");                
		StringBuilder sb = new StringBuilder();                
		PrintWriter out = null;
		PerfilForm uform = (PerfilForm) form;
		JSONObject jsonObjTabla = new JSONObject();
		String perfil = (String) request.getParameter("perfil");
		String descripcion = (String) request.getParameter("descripcion");
		try {
			
			new PerfilDAO().AgregarPerfil(usuario.getRucEmpresa(),Constantes.SISTEMA,perfil,descripcion);
		} catch (Exception e) {
			LOGGER.error("GP.ERROR: {}", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		out = response.getWriter();
		out.write("exito");
	}

    public void muestraItePerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
                HttpSession session = request.getSession();
                Usuario usuario = (Usuario) session.getAttribute("Usuario");                   
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		PerfilForm uform = (PerfilForm) form;
		JSONObject jsonObjTabla = new JSONObject();
		String perfil = (String) request.getParameter("perfil");
		try {

			StringBuilder sbLista = new StringBuilder();
			List<ListaGenerica> listaContactosAsignados = new PerfilDAO().ListarItemsPerfil(usuario.getRucEmpresa(),Constantes.SISTEMA, perfil);

			sbLista.append("<table id=\"tablaItemsPendientes\">");
			sbLista.append("<thead>");
			sbLista.append("<tr>");
			sbLista.append("<th>Opcion</th>");
			sbLista.append("<th></th>");
			sbLista.append("</tr>");
			sbLista.append("</thead>");
			sbLista.append("<tbody>");
			for (ListaGenerica lista : listaContactosAsignados) {
				if (lista.getRegistro()==0) {
					sbLista.append("<tr>");
					sbLista.append("<td>").append(lista.getDescripcion()).append("</td>");
					sbLista.append(
							"<td class=\"text-center\"><a class=\"btn btn-xs btn-default\" onclick=AgregarItemPerfil('")
							.append(lista.getIndice()).append("') title=\"Asignar\">").append(" Asignar </a></td>");
					sbLista.append("</tr>");
				}

			}
			sbLista.append("</tbody>");
			sbLista.append("</table>");
			jsonObjTabla.put("tablaPendientes", sbLista.toString());

			sbLista = new StringBuilder();

			sbLista.append("<table id=\"tablaItemsAsignados\">");
			sbLista.append("<thead>");
			sbLista.append("<tr>");
			sbLista.append("<th>Opcion</th>");
			sbLista.append("<th></th>");
			sbLista.append("</tr>");
			sbLista.append("</thead>");
			sbLista.append("<tbody>");
			for (ListaGenerica lista : listaContactosAsignados) {
				if (lista.getRegistro()==1) {
					sbLista.append("<tr>");
					sbLista.append("<td>").append(lista.getDescripcion()).append("</td>");
					sbLista.append(
							"<td class=\"text-center\"><a class=\"btn btn-xs btn-default\" onclick=EliminarItemPerfil('")
							.append(lista.getIndice()).append("') title=\"Eliminar\">").append(" Eliminar </a></td>");
					sbLista.append("</tr>");
				}

			}
			sbLista.append("</tbody>");
			sbLista.append("</table>");
			jsonObjTabla.put("tablaAsignados", sbLista.toString());

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("objTabla", jsonObjTabla);
			sb.append(jsonObject.toString());
			out = response.getWriter();
			out.write(sb.toString());
		} catch (Exception e) {
			LOGGER.error("GP.ERROR: {}", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}


        public void GrabarEditUsuarioPerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
                HttpSession session = request.getSession();
                Usuario usuario = (Usuario) session.getAttribute("Usuario");                
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		PerfilForm uform = (PerfilForm) form;
		JSONObject jsonObjTabla = new JSONObject();
		String perfil = (String) request.getParameter("perfil");
		String nombre = (String) request.getParameter("nombre");
                String dni = (String) request.getParameter("dni");
                String activo = (String) request.getParameter("activo");
                String aaaaa = (String) request.getParameter("usuario");
                String bbbbb = (String) request.getParameter("contrasenha");
		try {

			StringBuilder sbLista = new StringBuilder();
			new PerfilDAO().GrabarEditUsuarioPerfil(usuario.getRucEmpresa() , dni, perfil, nombre,activo,aaaaa,bbbbb);
		} catch (Exception e) {
			LOGGER.error("GP.ERROR: {}", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		out = response.getWriter();
		out.write("exito");
	}


       
	public void AgregarItemPerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
                HttpSession session = request.getSession();
                Usuario usuario = (Usuario) session.getAttribute("Usuario");                
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		PerfilForm uform = (PerfilForm) form;
		JSONObject jsonObjTabla = new JSONObject();
		String perfil = (String) request.getParameter("perfil");
		String nombreOpcion = (String) request.getParameter("nombreOpcion");
		try {

			StringBuilder sbLista = new StringBuilder();
			new PerfilDAO().AgregarItemPerfil(usuario.getRucEmpresa() , Constantes.SISTEMA, perfil, nombreOpcion);
		} catch (Exception e) {
			LOGGER.error("GP.ERROR: {}", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		out = response.getWriter();
		out.write("exito");
	}


        
        public void EliminarItemPerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
                HttpSession session = request.getSession();
                Usuario usuario = (Usuario) session.getAttribute("Usuario");                   
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		PerfilForm uform = (PerfilForm) form;
		JSONObject jsonObjTabla = new JSONObject();
		String perfil = (String) request.getParameter("perfil");
		String indice = (String) request.getParameter("indice");
		String indicenombre = (String) request.getParameter("indicenombre");
		try {

			StringBuilder sbLista = new StringBuilder();
			new PerfilDAO().EliminarItemPerfil(usuario.getRucEmpresa(), Constantes.SISTEMA, perfil, indice);
		} catch (Exception e) {
			LOGGER.error("GP.ERROR: {}", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		out = response.getWriter();
		out.write("exito");
	}
        
        public void muestraUsuarioPerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
                HttpSession session = request.getSession();
                Usuario usuario = (Usuario) session.getAttribute("Usuario");                 
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		PerfilForm uform = (PerfilForm) form;
		JSONObject jsonObjTabla = new JSONObject();
		String dni = (String) request.getParameter("dni");
		try {

			StringBuilder sbLista = new StringBuilder();
			List<DtoUsuario> listaItemPerfil = new PerfilDAO().muestraUsuarioPerfil(usuario.getRucEmpresa(),dni,Constantes.SISTEMA );
			if (listaItemPerfil != null && !listaItemPerfil.isEmpty()) {

				sbLista.append("<table id=\"tablaUsuarioPerfil\">");
				sbLista.append("<thead>");
				sbLista.append("<tr>");
				sbLista.append("<th>DNI</th>");
				sbLista.append("<th>Nombre</th>");
                                sbLista.append("<th>Perfil</th>");
                                sbLista.append("<th></th>");
				sbLista.append("</tr>");
				sbLista.append("</thead>");
				sbLista.append("<tbody>");
                                String strCadena="";
				for (DtoUsuario lista : listaItemPerfil) {
                                    strCadena=lista.getCodigo();
					sbLista.append("<tr>");
					sbLista.append("<td>").append(lista.getCodigo()).append("</td>");
					sbLista.append("<td>").append(lista.getNombre()).append("</td>");
                                        sbLista.append("<td>").append(lista.getNombrePerfil()).append("</td>");
                                        sbLista.append(
							"<td class=\"text-center\"><a class=\"btn btn-xs btn-default\" onclick=AgregarUsuario('" + strCadena + "') title=\"Asignar\">").append(Constantes.ICON_CHECK + " </a></td>");
					sbLista.append("</tr>");
				}
				sbLista.append("</tbody>");
				sbLista.append("</table>");
				jsonObjTabla.put("tabla", sbLista.toString());
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("objTabla", jsonObjTabla);
			sb.append(jsonObject.toString());
			out = response.getWriter();
			out.write(sb.toString());
		} catch (Exception e) {
			LOGGER.error("GP.ERROR: {}", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

         public void muestraUsuarioDatos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
                HttpSession session = request.getSession();
                Usuario usuario = (Usuario) session.getAttribute("Usuario");                 
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		PerfilForm uform = (PerfilForm) form;
		JSONObject jsonObjTabla = new JSONObject();
		String dni = (String) request.getParameter("dni");
		try {

			StringBuilder sbLista = new StringBuilder();
			List<DtoUsuario> listaItemPerfil = new PerfilDAO().muestraUsuarioPerfil(usuario.getRucEmpresa(),dni,Constantes.SISTEMA );
                        jsonObjTabla.put("dni", listaItemPerfil.get(0).getCodigo());
                        jsonObjTabla.put("nombre", listaItemPerfil.get(0).getNombre());
                        jsonObjTabla.put("activo", listaItemPerfil.get(0).getActivo());
                        jsonObjTabla.put("usuario", listaItemPerfil.get(0).getUsuario());
                        jsonObjTabla.put("contrasenha", listaItemPerfil.get(0).getPassword());
                        jsonObjTabla.put("perfil", listaItemPerfil.get(0).getPerfil());
                        
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("datos", jsonObjTabla);
			sb.append(jsonObject.toString());
			out = response.getWriter();
			out.write(sb.toString());
		} catch (Exception e) {
			LOGGER.error("GP.ERROR: {}", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}       
        
/*
	
*/
        /*
	public void VisualizarPendientes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		PerfilForm uform = (PerfilForm) form;
		JSONObject jsonObjTabla = new JSONObject();
		String perfil = (String) request.getParameter("perfil");
		try {

			StringBuilder sbLista = new StringBuilder();
			List<ListaGenerica> listaItemPerfil = new PerfilDAO().muestraPedientes("001", perfil);
			if (listaItemPerfil != null && !listaItemPerfil.isEmpty()) {

				sbLista.append("<table id=\"tablaItemPendientes\">");
				sbLista.append("<thead>");
				sbLista.append("<tr>");
				sbLista.append("<th>Opcion</th>");
				sbLista.append("<th></th>");
				sbLista.append("</tr>");
				sbLista.append("</thead>");
				sbLista.append("<tbody>");
				for (ListaGenerica img : listaItemPerfil) {
					sbLista.append("<tr>");
					sbLista.append("<td>").append(img.getDescripcion()).append("</td>");
					sbLista.append(
							"<td class=\"text-center\"><a class=\"btn btn-xs btn-default\" onclick=AgregarItemPerfil('")
							.append(img.getIndice()).append("','").append("a").append("') title=\"Asignar\">")
							.append(" Asignar </a></td>");
					sbLista.append("</tr>");
				}
				sbLista.append("</tbody>");
				sbLista.append("</table>");
				jsonObjTabla.put("tabla", sbLista.toString());
			}
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("objTabla", jsonObjTabla);
			sb.append(jsonObject.toString());
			out = response.getWriter();
			out.write(sb.toString());
		} catch (Exception e) {
			LOGGER.error("GP.ERROR: {}", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
*/
        /*
	public void tablaAsignados(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		PerfilForm uform = (PerfilForm) form;
		JSONObject jsonObjTabla = new JSONObject();
		String perfil = (String) request.getParameter("perfil");
		try {

			StringBuilder sbLista = new StringBuilder();
			List<ListaGenerica> listaItemPerfil = new PerfilDAO().muestraAsignados("001", perfil);
			sbLista.append("<table id=\"tablaItemPerfil\">");
			sbLista.append("<thead>");
			sbLista.append("<tr>");
			sbLista.append("<th>Nombre</th>");
			sbLista.append("<th></th>");
			sbLista.append("</tr>");
			sbLista.append("</thead>");
			sbLista.append("<tbody>");
			if (listaItemPerfil != null && !listaItemPerfil.isEmpty()) {
				for (ListaGenerica img : listaItemPerfil) {
					sbLista.append("<tr>");
					sbLista.append("<td>").append(img.getDescripcion()).append("</td>");
					sbLista.append(
							"<td class=\"text-center\"><a class=\"btn btn-xs btn-default\" onclick=EliminarItemPerfil('")
							.append("img.getItem()").append("','").append(img.getIndice()).append("') title=\"Asignar\">")
							.append(" Eliminar </a></td>");
					// sbLista.append("<td class=\"text-center\"><a class=\"btn btn-xs btn-default\"
					// onclick=EliminarItemPerfil('").append(img.getItem()).append("','").append("a").append("')
					// title=\"Asignar\">").append(" Eliminar </a></td>");
					sbLista.append("</tr>");
				}

			}
			sbLista.append("</tbody>");
			sbLista.append("</table>");
			jsonObjTabla.put("tabla", sbLista.toString());
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("objTabla", jsonObjTabla);
			sb.append(jsonObject.toString());
			out = response.getWriter();
			out.write(sb.toString());
		} catch (Exception e) {
			LOGGER.error("GP.ERROR: {}", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
*/
	/*
*/
        
	/*
*/
        
	/*public void AgregarContactoPerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		PerfilForm uform = (PerfilForm) form;
		JSONObject jsonObjTabla = new JSONObject();
		String perfil = (String) request.getParameter("idPerfil");
		String vIdGrupoContacto = (String) request.getParameter("idGrupoContacto");
		try {

			StringBuilder sbLista = new StringBuilder();
			new PerfilDAO().AgregarContactoPerfil(perfil, vIdGrupoContacto);
		} catch (Exception e) {
			LOGGER.error("GP.ERROR: {}", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		out = response.getWriter();
		out.write("exito");
	}*/
/*
	public void EliminarContactoPerfil(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOGGER.info("<==== Inicio Metodo: inicializa ====>");
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		PerfilForm uform = (PerfilForm) form;
		JSONObject jsonObjTabla = new JSONObject();
		String perfil = (String) request.getParameter("idPerfil");
		String vIdGrupoContacto = (String) request.getParameter("idGrupoContacto");
		try {

			StringBuilder sbLista = new StringBuilder();
			new PerfilDAO().EliminarContactoPerfil(perfil, vIdGrupoContacto);
		} catch (Exception e) {
			LOGGER.error("GP.ERROR: {}", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
		out = response.getWriter();
		out.write("exito");
	}*/
        
}
