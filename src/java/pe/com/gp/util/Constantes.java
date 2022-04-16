package pe.com.gp.util;

/**
 *
 * @author acabello
 */
public final class Constantes {

    public Constantes() {
    }

    // =====
    // Login
    // =====
    
    public static final String MNU_PERFILES = "MNU_PERFILES";
    public static final String MNU_CREAR_PERFILES = "MNU_CREAR_PERFILES";
    public static final String MNU_ASIGNAR_PERFIL = "MNU_ASIGNAR_PERFIL";
    public static final String MNU_ASIGNAR_ASIGNA_EXRANET = "MNU_ASIGNAR_ASIGNA_EXRANET";
    public static final String MNU_DIA_DE_CREDITO = "MNU_DIA_DE_CREDITO";
    public static final String MNU_CANCELACIONES = "MNU_CANCELACIONES";
    public static final String MNU_MIGRAR_VENTAS = "MNU_MIGRAR_VENTAS";
    public static final String MNU_MIGRAR_COBRANZAS = "MNU_MIGRAR_COBRANZAS";
    public static final String MNU_COMPRAS = "MNU_COMPRAS";
    public static final String MNU_PROVISIONAR_DOCUMENTOS = "MNU_PROVISIONAR_DOCUMENTOS";
    public static final String MNU_PROGRAMACION_PAGOS = "MNU_PROGRAMACION_PAGOS";
    public static final String MNU_PROGRAMACION_POLIZA = "MNU_PROGRAMACION_POLIZA";
    public static final String MNU_REGISTRO_DE_VENTAS = "MNU_REGISTRO_DE_VENTAS";   
    public static final String MNU_MAIL = "MNU_MAIL"; 
    public static final String MNU_CUENTAS_BANCARIAS_POR_PROVEEDOR = "MNU_CUENTAS_BANCARIOAS_POR_PROVEEDOR";
    public static final String MNU_BANCARIAS_EMPRESA = "MNU_BANCARIAS_EMPRESA";
    
    
    public static final String MSG_USER_NO_REGISTRADO_SAP = "Usuario no registrado en el SAP.";
    public static final String MSG_USER_NO_REGISTRADO = "Usuario no registrado en la WEB";
    public static final String MSG_USER_INHABILITADO = "Usuario inhabilitado.";
    public static final String MSG_TIPO_CAMBIO = "Tipo de Cambio no cargado.";
    public static final String MSG_USER_REQUERIDO = "Debes ingresar tu usuario.";
    public static final String MSG_PASS_REQUERIDO = "Debes ingresar tu clave.";
    public static final String MSG_USER_PASS_ERROR = "Usuario o clave incorrectos.";
    public static final String COD_SISGP = "21";   // SISGP
    public static final String COD_SISGP_WEB = "10"; // SISGP WEB
    public static final String NOMBRE_SISTEMA = "SISGP WEB";
    // ======
    // Varios
    // ======
    public static final String ACTIVA = "activo";
    public static final String DESACTIVA = "inactivo";
    public static final String URL_SERVIDOR = "http://192.168.253.107:8083";
    public static final String URL_SISTEMA = URL_SERVIDOR + "/sisgpweb";
    public static final String DIRECTORIO_TMP_PDF = "tmp_pdf";
    public static final String DIRECTORIO_TMP_TXT = "tmp_txt";
    public static final String DIRECTORIO_TMP_IMG = "tmp_img";
    public static final String DIRECTORIO_IREPORT = "iReport";
    public static final String DIRECTORIO_IMAGENES = "images";
    public static final String DIRECTORIO_PRODUCTOS = "images/productos";
    public static final String MENSAJE_EXITO = "Operacion exitosa";
    public static final String MENSAJE_ERROR = "Ha ocurrido un error";
    public static final String MENSAJE_INFO = "No se encontraron resultados";
    public static final String MENSAJE_ALERTA = "Hay algo que merece tu atencion";

    // ===========================
    // Constantes para usar en SAP
    // ===========================
    public static final String SAP_MONEDA_SOLES = "S/";
    public static final String SAP_MONEDA_DOLARES = "US$";
    public static final String SAP_MONEDA_EUROS = "EUR";
    public static final String SAP_SERVICIOS = "SER";
    public static final String SAP_REPUESTOS = "REP";

    public static final String SISTEMA = "21";
    
    // ===================
    // URLS (WEB SERVICES)
    // ===================
    // Servicios
    public static final String URL_ELIMINA_VOUCHER_POLIZA = "http://192.168.178.221:8081/wsGolden/rest/poliza/elimina_Poliza_VoucherCabecera";
    public static final String URL_ACTUALIZA_VOUCHER_POLIZA = "http://192.168.178.221:8081/wsGolden/rest/poliza/actualiza_Poliza_VoucherCabecera";
    public static final String URL_ACTUALIZA_POLIZA = "http://192.168.178.221:8081/wsGolden/rest/poliza/actualizaPoliza";
    public static final String URL_LISTA_POLIZA = "http://192.168.178.221:8081/wsGolden/rest/poliza/ListaPoliza";
    public static final String URL_LISTA_POLIZA_DETALLE = "http://192.168.178.221:8081/wsGolden/rest/poliza/ListaPoliza_Detalle";
    public static final String URL_LISTA_POLIZA_DETALLE_ITEM = "http://192.168.178.221:8081/wsGolden/rest/poliza/ListaPoliza_Detalle_Item";
    public static final String URL_LISTA_POLIZA_VALIDA_ITEM = "http://192.168.178.221:8081/wsGolden/rest/poliza/ValidaPolizaItem";
    public static final String URL_ACTUALIZA_POLIZA_CAB = "http://192.168.178.221:8081/wsGolden/rest/poliza/actualizaPolizaCab";
    
    public static final String URL_LISTA_TIPO_GASTOS = "http://192.168.178.221:8081/wsGolden/rest/poliza/ListaTiposGastoPoliza";
    
    public static final String URL_STOCK_ADMINISTRACION = "http://192.168.178.221:8081/wsGolden/rest/poliza/StockAdministracion";
    
    
    
    public static final String URL_LLAMADA_SERVICIO_INS_OFE_VTA = "http://192.168.140.16:8084/api/LlamadaServicio/creaOfertaVenta";
    public static final String URL_LLAMADA_SERVICIO_UPD_OFE_VTA = "http://192.168.140.16:8084/api/LlamadaServicio/modificaOfertaVenta";
    public static final String URL_LLAMADA_SERVICIO_INS_ORD_VTA = "http://192.168.140.16:8084/api/SypSoft/SYP_CreaOrdenVenta";
    public static final String URL_LLAMADA_SERVICIO_UPD_ORD_VTA = "http://192.168.140.16:8084/api/SypSoft/SYP_ModificaOrdenVenta";
    public static final String URL_LLAMADA_SERVICIO_SOLICITUD_TRASLADO_REP = "http://192.168.140.16:8084/api/SypSoft/SYP_TrasladosRepuestoAlmacen";
    public static final String URL_LLAMADA_SERVICIO_INS_ORDEN_COMPRA = "http://192.168.140.16:8084/api/LlamadaServicio/creaOrdenCompra";
    public static final String URL_LLAMADA_SERVICIO_CREACION = "http://192.168.140.16:8084/api/SypSoft/SYP_CrearLlamadaServicio";
    public static final String URL_TARJETA_EQUIPO_CREACION = "http://192.168.140.16:8084/api/SypSoft/SYP_CrearTarjetaEquipo";
    public static final String URL_PROYECTO_CREACION = "http://192.168.140.16:8084/api/SypSoft/SYP_RegistrarProyecto";
    // Repuestos
    public static final String URL_REPUESTOS_INS_OFE_VTA = "http://192.168.140.16:8084/api/DocumentosMarketing/crearOfertaVenta";
    public static final String URL_REPUESTOS_UPD_OFE_VTA = "http://192.168.140.16:8084/api/DocumentosMarketing/modificarOfertaVenta";
    public static final String URL_REPUESTOS_CRE_FAC_VTA = "http://192.168.140.16:8084/api/DocumentosMarketing/creaFacturaVenta";
    
    public static final String URL_REPUESTOS_INS_ORD_VTA = "http://192.168.140.16:8084/api/?????";
    public static final String URL_REPUESTOS_UPD_ORD_VTA = "http://192.168.140.16:8084/api/?????";
    
    // Generales
    public static final String URL_SOCIO_NEGOCIO_CLIENTE_CREACION = "http://192.168.140.16:8084/api/SypSoft/SYP_RegistrarCliente";

    // ===========================
    // Iconos
    // https://material.io/icons/
    // ===========================    
    public static final String ICON_TIMER = "<i class=\"far fa-clock\"></i>";
    public static final String ICON_BUILDING = "<i class=\"far fa-building\"></i>";
    public static final String ICON_WRENCH = "<i class=\"fas fa-wrench\"></i>";
    public static final String ICON_CHECK = "<i class=\"fas fa-check\"></i>";
    public static final String ICON_CLEAR = "<i class=\"fas fa-times\"></i>";
    public static final String ICON_SEARCH = "<i class=\"fas fa-search\"></i>";
    public static final String ICON_REPLY = "<i class=\"fas fa-reply\"></i>";
    public static final String ICON_REFRESH = "<i class=\"fas fa-sync\"></i>";
    public static final String ICON_ATTACH = "<i class=\"fas fa-paperclip\"></i>";
    public static final String ICON_EMAIL = "<i class=\"far fa-envelope\"></i>";
    public static final String ICON_DOWNLOAD = "<i class=\"fas fa-download\"></i>";
    public static final String ICON_UPLOAD = "<i class=\"fas fa-upload\"></i>";
    public static final String ICON_PLAY = "<i class=\"fas fa-play\"></i>";
    public static final String ICON_PAUSE = "<i class=\"fas fa-pause\"></i>";
    public static final String ICON_STOP = "<i class=\"fas fa-stop\"></i>";
    public static final String ICON_CHAT = "<i class=\"far fa-comment\"></i>";
    public static final String ICON_FOLDER_OPEN = "<i class=\"far fa-comment\"></i>";
    public static final String ICON_ADD = "<i class=\"fas fa-plus\"></i>";
    public static final String ICON_WARNING = "<i class=\"fas fa-exclamation-triangle\"></i>";
    public static final String ICON_INFO = "<i class=\"fas fa-info-circle\"></i>";
    public static final String ICON_PRINT = "<i class=\"fas fa-print\"></i>";
    public static final String ICON_MENU = "<i class=\"fas fa-bars\"></i>";
    public static final String ICON_LOCK = "<i class=\"fas fa-lock\"></i>";
    public static final String ICON_USER = "<i class=\"fas fa-user-circle\"></i>";
    public static final String ICON_COG = "<i class=\"fas fa-cog\"></i>";
    public static final String ICON_EXIT = "<i class=\"fas fa-sign-out-alt\"></i>";
    public static final String ICON_THUMBS_UP = "<i class=\"far fa-thumbs-up\"></i>";
    public static final String ICON_EDIT = "<i class=\"fas fa-pencil-alt\"></i>";
    public static final String ICON_ARROW_UP = "<i class=\"fas fa-arrow-up\"></i>";
    public static final String ICON_ARROW_DOWN = "<i class=\"fas fa-arrow-down\"></i>";
    public static final String ICON_ARROW_RIGHT = "<i class=\"fas fa-arrow-right\"></i>";
    public static final String ICON_ARROW_LEFT = "<i class=\"fas fa-arrow-left\"></i>";
    public static final String ICON_ANGLE_RIGHT = "<i class=\"fas fa-angle-right\"></i>";
    public static final String ICON_TRASH = "<i class=\"far fa-trash-alt\"></i>";
    public static final String ICON_ENTER = "<i class=\"fas fa-reply\"></i>";
    public static final String ICON_BOOK = "<i class=\"fas fa-book\"></i>";
    public static final String ICON_MAP_MARKER = "<i class=\"fas fa-map-marker-alt\"></i>";
    public static final String ICON_DATABASE = "<i class=\"fas fa-database\"></i>";
    public static final String ICON_TABLE = "<i class=\"fas fa-table\"></i>";
    public static final String ICON_PHONE = "<i class=\"fas fa-phone\"></i>";
    public static final String ICON_DOLLAR_SIGN = "<i class=\"fas fa-dollar-sign\"></i>";
    public static final String ICON_UNLOCK = "<i class=\"fas fa-unlock\"></i>";
    public static final String ICON_LOCK_OPEN = "<i class=\"fas fa-lock-open\"></i>";
    public static final String ICON_CART_PLUS = "<i class=\"fas fa-cart-plus\"></i>";
    public static final String ICON_HAMBURGER = "<i class=\"glyphicon glyphicon-menu-hamburger\"></i>";
    public static final String ICON_PDF = "<i class=\"fas fa-file-pdf\"></i>";
    
  
    
}
