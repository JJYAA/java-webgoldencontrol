/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.entity;

import java.io.Serializable;
import oracle.sql.DATE;

/**
 *
 * @author acabello
 */
public class EntregaVenta implements Serializable{
    /*Número de documento*/
    private int DocNum;
    /*
    Fecha de contabilización
    */
    private String DocDate;
    /*
    Código de cliente/proveedor	
    */
    private String CardCode;
    /*
    Nombre de cliente/proveedor	
    */
    private String CardName;
    /*
    Destinatario de factura
    */
    private String Address ;
    /*
    Moneda del documento
    */
    private String DocCur ;
    
  /*  Nombre	Descripción	
DocNum	Número de documento	
DocType	Clase de documento	
CANCELED	Cancelado	
Handwrtten	Numeración manual	
Printed	Impreso	
DocStatus	Status de documento	
InvntSttus	Status del almacén	
Transfered	Traspaso de ejercicio	
ObjType	Tipo de objeto	
DocDate	Fecha de contabilización	
DocDueDate	Fecha de vencimiento	
CardCode	Código de cliente/proveedor	
CardName	Nombre de cliente/proveedor	
Address	Destinatario de factura	
NumAtCard	Número de referencia de deudor/acreedor	
VatPercent	Tipo impositivo	
VatSum	Impuesto total	
VatSumFC	Importe del impuesto (ME)	
DiscPrcnt	% de descuento para documento	
DiscSum	Descuento total	
DiscSumFC	Descuento total en ME	
DocCur	Moneda del documento	
DocRate	Tipo de cambio de documento	
DocTotal	Total del documento	
DocTotalFC	Total del documento en moneda extranjera	
PaidToDate	Pagado hasta la fecha	
PaidFC	Pagado en ME	
GrosProfit	Ganancia bruta	
GrosProfFC	Ganancia bruta en ME	
Ref1	Referencia 1	
Ref2	Referencia 2	
Comments	Comentarios	
JrnlMemo	Comentarios	
TransId	Número de operación	
ReceiptNum	Número de recibo	
GroupNum	Código de condiciones de pago	
DocTime	Tiempo de generación	
SlpCode	Empleado del departamento de ventas	
TrnspCode	Forma de envío	
PartSupply	Entrega parcial	
Confirmed	Confirmado	
GrossBase	Lista de precios para ganancia bruta	
ImportEnt	Número de pedido	
SummryType	Método resumen	
UpdInvnt	Actualización del almacén	
UpdCardBal	Actualizar saldos	
CntctCode	Persona de contacto	
FatherCard	Consolidación SN	
SysRate	Precio de sistema	
CurSource	Moneda inicial	
VatSumSy	Importe del impuesto (MEST)	
DiscSumSy	Descuento total en MS	
DocTotalSy	Total de documento en MS	
PaidSys	Pagado en MS	
FatherType	Clase de resumen superior	
GrosProfSy	Ganancia bruta en MS	
UpdateDate	Fecha de actualización	
IsICT	Factura de deudor + Pago	
CreateDate	Fecha de creación	
Volume	Volumen	
VolUnit	Unidad de medida de volumen	
Weight	Peso	
WeightUnit	Unidad de medida del peso	
Series	Serie	
TaxDate	Fecha de documento	
Filler	Filtro	
StampNum	Número de sello	
isCrin	Factura corregida	
FinncPriod	Período contable	
UserSign	Firma del usuario	
selfInv	Factura automática	
VatPaid	Impuesto pagado hasta la fecha	
VatPaidFC	Definición del impuesto	
VatPaidSys	Impuesto pagado en MS	
WddStatus	Status de autorización	
draftKey	ID interna doc.preliminar	
TotalExpns	Costos totales	
TotalExpFC	Costos totales (ME)	
TotalExpSC	Costos totales (MS)	
Address2	Destino	
Exported	Exportado	
StationID	ID de estación de trabajo	
Indicator	Indicador	
NetProc	Procedimiento neto	
AqcsTax	IVA de adquisición de la CE	
AqcsTaxFC	IVA de adquisición de la CE (ME)	
AqcsTaxSC	IVA de adquisición de la CE (MEST)	
CashDiscPr	Porcentaje de descuento por pronto pago	
CashDiscnt	Descuento por pronto pago	
CashDiscFC	Descuento por pronto pago (ME)	
CashDiscSC	Descuento por pronto pago (MEST)	
ShipToCode	Destinatario de mercancías: Código	
LicTradNum	Número de vendedor autorizado	
PaymentRef	Número referencia de pago	
WTSum	Importe de retención	
WTSumFC	Impte.retención (ME)	
WTSumSC	Impte.retención (MS)	
RoundDif	Importe de diferencias de redondeo	
RoundDifFC	Importe de diferencias de redondeo (ME)	
RoundDifSy	Importe de diferencias de redondeo (MEST)	
CheckDigit	Dígito de control	
Form1099	Formulario 1099	
Box1099	Casilla 1099	
submitted	Solicitado	
PoPrss	Proceso de pedido	
Rounding	Redondeo	
RevisionPo	Partir pedido	
Segment	Segment	
ReqDate	Fecha necesaria	
CancelDate	Fecha de cancelación	
PickStatus	Status de picking	
Pick	Efectuar picking	
BlockDunn	Bloquear reclamación	
PeyMethod	Forma de pago	
PayBlock	Bloqueo de pago	
PayBlckRef	Payment Block Abs Entry	
MaxDscn	Descuento máximo	
Reserve	Reservar	
Max1099	Importe 1099 máximo	
CntrlBnk	Indicador de banco central	
PickRmrk	Comentarios sobre picking	
ISRCodLine	Línea de codificación ISR	
ExpAppl	Exp applied	
ExpApplFC	Exp applied FC	
ExpApplSC	Exp applied DC	
Project	Código de proyecto	
DeferrTax	Impuesto diferido	
LetterNum	Número de carta de exención de impuestos	
FromDate	Desde fecha de validez de exención	
ToDate	Hasta fecha de validez de exención	
WTApplied	Retención de impuestos aplicada	
WTAppliedF	Retención impto.aplicada (ME)	
BoeReserev	Efecto reservado	
AgentCode	Código de agente	
WTAppliedS	Retención impto.aplicada (MS)	
EquVatSum	Total recargo de equivalencia	
EquVatSumF	Total recargo de equivalencia (ME)	
EquVatSumS	Total recargo de equivalencia (MEST)	
Installmnt	Cantidad de plazos	
VATFirst	Aplicar impuesto en el primer plazo	
NnSbAmnt	Impte.no sujeto a retención impto.	
NnSbAmntSC	Impte.no sujeto a retención impto.(MS)	
NbSbAmntFC	Impte.no sujeto a retención impto.(ME)	
ExepAmnt	Importe libre de retención de impuestos	
ExepAmntSC	Importe libre de impuestos de retención (ME)	
ExepAmntFC	Importe libre de impuestos de retención (ME)	
VatDate	Fecha IVA	
CorrExt	Número de documento corregido externo	
CorrInv	Número de documento corregido interno	
NCorrInv	Próximo documento de corrección	
CEECFlag	Bloquear creación de factura de corrección del objetivo	
BaseAmnt	Importe base	
BaseAmntSC	Importe base (MEST)	
BaseAmntFC	Importe base (ME)	
CtlAccount	Cuenta asociada	
BPLId	Sucursal	
BPLName	Nombre de sucursal	
VATRegNum	Número de registro de IVA	
TxInvRptNo	Número de recepción de factura de impuestos	
TxInvRptDt	Fecha de recepción de factura de impuestos	
KVVATCode	Código de IVA para recepción de factura de impuestos	
WTDetails	Detalles retención impuestos	
SumAbsId	ID de extracto de IVA por totales	
SumRptDate	Fecha de informe de IVA por totales	
PIndicator	Indicador de período	
ManualNum	Número manual	
UseShpdGd	Utilizar cuenta de mercancías enviadas	
BaseVtAt	ID BPL asignado a factura	
BaseVtAtSC	Nombre BPL	
BaseVtAtFC	Número de registro impuesto	
NnSbVAt	Número de recepción de factura de impuestos	
NnSbVAtSC	Fecha de recepción de factura de impuestos	
NbSbVAtFC	Impte.IVA no sujeto a retención impto.(ME)	
ExptVAt	Impte.IVA exento retención impto.	
ExptVAtSC	Impte.IVA exento retención impto.(MS)	
ExptVAtFC	Detalles retención impuestos	
LYPmtAt	Pagos del ejercicio anterior	
LYPmtAtSC	Pagos últimos años (MS)	
LYPmtAtFC	Fecha de declaración de impuestos por totales	
ExpAnSum	Indicador de período	
ExpAnSys	Número manual	
ExpAnFrgn	Utilizar cuenta de mercancías enviadas	
DocSubType	Código de IVA para recepción de factura de impuestos	
DpmStatus	ID de extracto de IVA por totales	
DpmAmnt	Importe de anticipo ML	
DpmAmntSC	Importe de anticipo MS	
DpmAmntFC	Importe de anticipo ME	
DpmDrawn	Sacado a anticipo	
DpmPrcnt	% anticipo	
PaidSum	Total pagado	
PaidSumFc	Total pagado (ME)	
PaidSumSc	Total pagado (MS)	
FolioPref	String prefijo folio	
FolioNum	Número de folio	
DpmAppl	Anticipo aplicado ML	
DpmApplFc	Anticipo aplicado ME	
DpmApplSc	Anticipo aplicado MS	
LPgFolioN	Nº folio p.última página en doc.	
Header	Cabecera	
Footer	Pie de página	
Posted	Anticipo contabilizado	
OwnerCode	Titular documento	
BPChCode	Código canal SN	
BPChCntc	Persona de contacto de canal SN	
PayToCode	Pagar a	
IsPaytoBnk	Es pago a banco	
BnkCntry	Pago a país del banco	
BankCode	Pago a código bancario	
BnkAccount	Pago a núm.cta.bancaria	
BnkBranch	Pago a sucursal bancaria	
isIns	Factura de reserva	
TrackNo	Número de seguimiento	
VersionNum	Número de versión	
LangCode	Código de idioma	
BPNameOW	BP_NAME_OVERWRITTEN	
BillToOW	BILL_TO_OVERWRITTEN	
ShipToOW	SHIP_TO_OVERWRITTEN	
RetInvoice	Nota de crédito	
ClsDate	Fecha de cierre de documento	
MInvNum	Número de factura mensual	
MInvDate	Fecha de factura mensual	
SeqCode	Código de secuencia	
Serial	Número de serie	
SeriesStr	String de series	
SubStr	String subseries	
Model	Modelo fiscal nota	
TaxOnExp	Impuesto del total de costos	
TaxOnExpFc	Impuesto del total de costos (ME)	
TaxOnExpSc	Impuesto del total de costos (MS)	
TaxOnExAp	Impuesto aplicado en costos	
TaxOnExApF	Impuesto aplicado en porte (ME)	
TaxOnExApS	Impuesto aplicado en porte (MS)	
LastPmnTyp	Último tipo de pago	
LndCstNum	Número de precio de entrega	
UseCorrVat	Utilizar grupo IVA corrección	
BlkCredMmo	Bloquear creación nota de crédito destino	
OpenForLaC	Abierto para costos al desembarque	
Excised	Eliminado	
ExcRefDate	Eliminar fecha de referencia	
ExcRmvTime	Eliminar tiempo de amortización	
SrvGpPrcnt	Porcentaje de servicio de ganancia bruta	
DepositNum	Número de presentación de un cheque al cobro	
CertNum	Número de certificado	
DutyStatus	Status derechos de aduana	
AutoCrtFlw	Crear autom.documento subsiguiente	
FlwRefDate	Fecha referencia documento subsig.	
FlwRefNum	Nº referencia documento subsig.	
VatJENum	Nº de asiento de IVA	
DpmVat	Impuesto anticipo ML	
DpmVatFc	Impuesto anticipo ME	
DpmVatSc	Impuesto anticipo MS	
DpmAppVat	Impuesto aplicado anticipo ML	
DpmAppVatF	Impuesto aplicado anticipo ME	
DpmAppVatS	Impuesto aplicado anticipo MS	
InsurOp347	347 Operación de aseguradora	
IgnRelDoc	Ignorar documento relevante en archivo	
BuildDesc	Descriptor de reestructuración	
ResidenNum	Número de residencia	
Checker	Controlador	
Payee	Receptor del pago	
CopyNumber	Número de copia	
SSIExmpt	Exención SSI	
PQTGrpSer	Serie de grupo de ofertas de compra	
PQTGrpNum	Número de grupo de ofertas de compra	
PQTGrpHW	Manual de grupo de ofertas de compra	
ReopOriDoc	Reabrir pedido original por devolución	
ReopManCls	Reabrir pedidos cerrados/cancelados manualmente	
DocManClsd	Documento cerrado manualmente	
ClosingOpt	Opción de cierre	
SpecDate	Fecha de contabilización especificada por usuario	
Ordered	Pago solicitado	
NTSApprov	NTS aprobado	
NTSWebSite	Sitio web de impuestos en línea	
NTSeTaxNo	Número de impuestos en línea	
NTSApprNo	Número de aprobación NTS	
PayDuMonth	Inicio desde	
ExtraMonth	Cantidad de meses adicionales	
ExtraDays	Cantidad de días adicionales	
CdcOffset	Intervalo de descuento por pronto pago	
SignMsg	Mensaje entrante de firma	
SignDigest	Codificación de firma	
CertifNum	Número de certificado	
KeyVersion	Versión de clave privada	
EDocGenTyp	Tipo de creación de documento electrónico	
ESeries	Serie electrónica	
EDocNum	Número de documento electrónico	
EDocExpFrm	Formato de exportación de documento electrónico	
OnlineQuo	Crear oferta en línea	
POSEqNum	Número de equipo de TPV	
POSManufSN	Número de serie del fabricante de TPV	
POSCashN	Número de cajero de TPV	
EDocStatus	Status de documento electrónico	
EDocCntnt	Contenido de documento electrónico	
EDocProces	Proceso de documento electrónico	
EDocErrCod	Código de error de documento electrónico	
EDocErrMsg	Mensaje de error de documento electrónico	
EDocCancel	Documento electrónico - Cancelado	
EDocTest	Documento electrónico - Prueba	
EDocPrefix	Documento electrónico - Prefijo	
CUP	Código único de proyecto	
CIG	Identificación de código de contrato	
DpmAsDscnt	Documento de descuento con anticipo	
Attachment	Anexo	
AtcEntry	Entrada de anexo	
SupplCode	Código adicional	
GTSRlvnt	Relevante para GTS	
BaseDisc	Descuento base ML	
BaseDiscSc	Descuento base MS	
BaseDiscFc	Descuento base ME	
BaseDiscPr	Porcentaje de descuento base	
CreateTS	Hora de creación - Incl. segundos	
UpdateTS	Actualizar tiempo completo	
SrvTaxRule	Aplicar la regla de impuesto sobre servicio	
AnnInvDecR	Referencia de declaración de factura anual	
Supplier	Proveedor	
Releaser	Encargado de aprobar la distribución de mercancías	
Receiver	Encargado de aprobar la liberación de mercancías	
ToWhsCode	A código de almacén	
AssetDate	Fecha de valor de activo fijo	
Requester	Usuario solicitante de mercancías	
ReqName	Nombre de usuario	
Branch	Sucursal	
Department	Departamento	
Email	Correo electrónico	
Notify	Necesario enviar notificación	
ReqType	Tipo de solicitante usuario/empleado	
OriginType	Origen del documento	
IsReuseNum	Está reutilizando el número de documento	
IsReuseNFN	Está reutilizando el número de la nota fiscal	
DocDlvry	Entrega de documento	
PaidDpm	Pago con anticipo	
PaidDpmF	Pago con anticipo (ME)	
PaidDpmS	Pago con anticipo (MS)	
EnvTypeNFe	Tipo de entorno NF-e	
AgrNo	Número de acuerdo	
IsAlt	Es un cambio	
AltBaseTyp	Tipo de cambio base	
AltBaseEnt	Entrada de cambio base	
AuthCode	Código de autorización	
StDlvDate	Fecha de entrega inicial	
StDlvTime	Hora de entrega inicial	
EndDlvDate	Fecha de entrega final	
EndDlvTime	Hora de entrega final	
VclPlate	Matrícula de vehículo	
ElCoStatus	Estado de com.electrónica	
AtDocType	Tipo de documento de clase de cuenta	
ElCoMsg	Mensaje de com.elec.	
PrintSEPA	Imprimir notificación previa de cobro de cargo SEPA	
FreeChrg	Libre de gastos IC	
FreeChrgFC	Libre de gastos IC ML	
FreeChrgSC	Libre de gastos IC MS	
NfeValue	Valor NF-e	
FiscDocNum	Número de documento fiscal	
RelatedTyp	Tipo relacionado	
RelatedEnt	Entrada relacionada	
CCDEntry	CCD Abs. Entry	
NfePrntFo	Formato de impresión NF-e	
ZrdAbs	Número de resumen diario de TPV	
POSRcptNo	Número de recibo de TPV	
FoCTax	Libre de gastos IC Impuesto	
FoCTaxFC	Libre de gastos IC Impuesto ML	
FoCTaxSC	Libre de gastos IC Impuesto MS	
TpCusPres	Tipo de presencia de usuario final	
ExcDocDate	Fecha de documento de consumo	
FoCFrght	Libre de gastos Porte	
FoCFrghtFC	Libre de gastos Porte ME	
FoCFrghtSC	Libre de gastos Porte MS	
InterimTyp	Tipo de provisional	
PTICode	Código POI	
Letter	Carta	
FolNumFrom	Nº de folio desde	
FolNumTo	Número de folio a	
FolSeries	Serie de folios	
SplitTax	Impuesto de pago dividido	
SplitTaxFC	Impuesto de pago dividido ME	
SplitTaxSC	Impuesto de pago dividido MS	
ToBinCode	To Bin Location	
PriceMode	Modo de precio	
PoDropPrss	Proceso de envío directo de pedido	
PermitNo	Permit Number	
MYFtype	Tipo MYF	
DocTaxID	Identificación fiscal de documento	
DateReport	Fecha de informes	
RepSection	Sección de informes	
ExclTaxRep	Excluir de declaración de control	
PosCashReg	POS/Registro de caja	
DmpTransID	ID de transacción para anticipo	
ECommerBP	Operador de comercio electrónico	
EComerGSTN	Número de región de GST para comercio electrónico	
Revision	Revisión	
RevRefNo	Número de referencia original	
RevRefDate	Fecha de referencia original	
RevCreRefN	Número de referencia de crédito/débito original	
RevCreRefD	Fecha de referencia de débito/crédito original	
TaxInvNo	Número de factura de impuestos	
FrmBpDate	Fecha de inicio de proveedor	
GSTTranTyp	Tipo de transacción de GST	
BaseType	Clase de documento base	
BaseEntry	Clave interna de documento base	
ComTrade	Comercio de comisiones	
UseBilAddr	Determinar impuestos sobre bienes y servicios utilizando Destinatario de la fact	
IssReason	Motivo de emisión de notas	
ComTradeRt	Devolución de comercio de comisiones	
SplitPmnt	División de pagos de proveedores	
SOIWizId	SOI Wizard ID	
SelfPosted	Factura propia creada [Sí/No]	
EnBnkAcct	Encryption of Pay to Bank Acct	
EncryptIV	Encrypt IV	
DPPStatus	Estado de protección de datos	
EWBGenType	Tipo de generación de E-Way Bill	
SAPPassprt	SAP Passport ampliado	
CtActTax	Impuesto de contabilidad de cliente	
CtActTaxFC	Impuesto de contabilidad de cliente (ME)	
CtActTaxSC	Impuesto de contabilidad de cliente (MS)	
U_SYP_MDMT	Motivo de Traslado	
U_SYP_MDTD	Tipo de Documento	
U_SYP_MDSD	Serie del Documento	
U_SYP_MDCD	Correlativo del Documento	
U_SYP_STATUS	Estado del Documento	
U_SYP_MDTO	Tipo doc. Vinculado(SUNAT)	
U_SYP_MDSO	Serie doc.Vinculado(SUNAT)	
U_SYP_MDCO	Correlativo doc.Vinc.(SUNAT)	
U_SYP_FECHAREF	Fecha Doc. Origen	
U_SYP_MOTNCND	Motivo de Emision N.C./N.D.	
U_SYP_DETPAGADO	Detraccion Pagada?	
U_SYP_AUTODET	Autodetracción	
U_SYP_DETPAY	Pago Detracción	
U_SYP_NGUIA	Nro. Guias de Entrega	
U_SYP_NOCCLIENTE	Nro. Orden Compra Cliente	
U_SYP_NANTICIPO	Nro. Anticipo	
U_SYP_TCOMPRA	Tipo de Compra	
U_SYP_ANTICIPO	Anticipo	
U_SYP_PANTICIPO	% Anticipo	
U_SYP_COTPROV	Nro. Cotizacion Proveedor	
U_SYP_CARPIMP	Nro.Importacion	
U_SYP_DPFC	Fecha Deposito - Detraccion	
U_SYP_DPNM	Nro. Deposito - Detraccion	
U_SYP_MDFE	Fecha de embarque	
U_SYP_MDND	Numero de DUA	
U_SYP_CDAD	Codigo Aduana	
U_SYP_MDFD	Fecha de DUA	
U_SYP_CODERCC	Numero CC/ER	
U_SYP_TREQUE	Tipo de Requerimiento	
U_SYP_TIPOBOLETO	Tipo de Boleto	
U_SYP_TEMBAR	Tipo de Embarque	
U_SYP_NUMCRET	Nro. Comprobante retencion	
U_SYP_MDCT	Código de transportista	
U_SYP_MDRT	RUC del Transportista	
U_SYP_MDNT	Nombre Transportista	
U_SYP_MDDT	Direccion de transportista	
U_SYP_MDFN	Nombre del conductor	
U_SYP_MDFC	Licencia del conductor	
U_SYP_MDVN	Marca del vehiculo	
U_SYP_MDVC	Placa del vehiculo	
U_SYP_MDVT	Placa de la tolva	
U_SYP_MDCI	Certificado de Inscripcion	
U_SYP_MDTS	Tipo Salida	
U_SYP_TPOENTME	Tipo Entrada Mercancias	
U_SYP_ALMACENERO	Almacenero	
U_SYP_SOLICITANTE	Solicitante	
U_SYP_TPOSALME	Tipo Salida Mercancias	
U_SYP_RQORIGEN	Dcto.Origen Requerimiento	
U_SYP_NROREQINT	Nro. Req. Interno	
U_SYP_NROSUNAT	Nro. Documento SUNAT	
U_SYP_VALFOBEXP	Valor FOB Expo	
U_SYP_MDCF	Correlativo Final Dcto	
U_SYP_PROVJRNL	Asiento de provision	
U_SYP_CONSIGNADOR	Documento Consignador	
U_SYP_EXE4TA	Exonerado renta de cuarta	
U_SYP_GRFT	Guia-Factura	
U_SYP_TIPO_TRANSF	Tipo de Transferencia	
U_SYP_TRANS_ENV	Transferencia de Envío	
U_SYP_SERRET	Serie Comp. Retención	
U_SYP_CODSNERCC	Código SN ER/CCH	
U_SYP_DOCDT	Doc. Origen DT	
U_SYP_DOCDTT	Doc. Origen DT - Tipo	
U_SYP_DLOTE	N° Lote - DET	
U_SYP_TPO_OP	Tipo Operación - DET	
U_SYP_FEESTADO	FE Estado CDP SUNAT	
U_SYP_FERPTA	FE Respuesta CDP SUNAT	
U_SYP_FEMOT	FE Motivo NC/ND	
U_SYP_FECAT07	Tipo de Afectación al IGV	
U_SYP_FECAT09	Tipo nota crédito electronica	
U_SYP_FECAT10	Tipo nota débito según motivo	
U_SYP_FECAT17	Tipo de Operación	
U_SYP_FECAT12	Cód tipo doc tributarios	
U_SYP_FECAT15	Cód adic factura o boleta	
U_SYP_ESTDOC_LE	Estado Documento LE	
U_SYP_FECHA_LE	Periodo Libro Electronico	
U_SYP_DOCEXPORT	Documento de exportación	
U_SYP_TIPDOCCF	Tipo CP Sustento Credit Fiscal	
U_SYP_EXONDOM	Exoneración No Domiciliados	
U_SYP_TIPRTAND	Tipo de Renta	
U_SYP_MODSRVND	Modalidad Servicio No Domicili	
U_SYP_PNPART76	Aplica penultimo parrafo Art76	
U_SYP_BIESRVADQ	Bienes y Servicios Adquiridos	
U_SYP_CONTRPRY	Identificación del Contrato	
U_SYP_NCDSTO	Nota de Crédito de Descuento	
U_SYP_NCPESND	N° Compr de Pago Emitido SND	
U_SYP_NUMFORMIMP	Número de Formulario	
U_SYP_TPODISG	Tipo de decuento global	
U_SYP_FECAT51	Tipo de Operación-CAT 51	
U_SYP_APLIFACT	Aplica Factoring	
U_SYP_APLIFACTN	Aplica Fact.Neg	
U_SYP_CDRECCLA	Reclasificar Cobranza dudosa	
U_SYP_CDTDOCORI	Tipo documento origen CD	
U_SYP_CDDOCORI	Documento origen CD	
U_SYP_CDDOCPR	Documento de compensación CD	
U_SYP_CDJRNLEST	Asiento de estimacion CD	
U_SYP_CARDCODE	Codigo Proveedor	
U_SYP_CONSIGNATARIO	Documento Consignatario	
U_SYP_MOTTRA	Motivo Traslado Consignatario	
U_SYP_CGT_GRPVENT	Grupo Documentos para venta	
U_SYP_CCUN	CC Unidad de Negocio	
U_SYP_CCTIENDA	CC Tienda	
U_SYP_ORCOTI	Origen Cotizacion	
U_SYP_FORMAPAGO	Forma de Pago	
U_SYP_MODVENTA	Modalidad de Venta	
U_SYP_MODFINANCIA	Modalidad de Financiamiento	
U_SYP_ENTBANCARIA	Entidad bancaria	
U_SYP_MODELO	Modelo Unidad	
U_SYP_NROFILE	Nro. File	
U_SYP_COLOR	Color	
U_SYP_CAMPANA	Codigo Campaña	
U_SYP_PTOCONTACTO	Punto de Contacto	
U_SYP_INCENTIVOT	Incentivo Toyota	
U_SYP_PLACAUSADO	Placa Usado	
U_SYP_LLAMADASER	Nro. Llamada Servicio	
U_SYP_NROSERIE	Nro. Serie / Proyecto	
U_SYP_NROPLACA	Nro. Placa Servicio	
U_SYP_FRANQUICIA	Es franquicia Y/N	
U_SYP_COCLIFRANQ	Cod. Cliente Franquicia	
U_SYP_NOCLIFRANQ	Nombre Cliente Franquicia	
U_SYP_VALORFRANQ	Valor Franquicia	
U_SYP_GLOSAFACT	Glosa de la Factura	
U_SYP_KIMING	Kilometraje de Ingreso	
U_SYP_TIPOOTP	Tipo de OT	
U_SYP_CANALVP	Canal de venta PANA	
U_SYP_CANALVT	Canal de venta TOYOTA	
U_SYP_TIPOVT	Tipo Venta por Cliente TOYOTA	
U_SYP_CLASEVT	Clase de facturacion TOYOTA	
U_SYP_TIPONCRED	Tipo de Nota de Credito	
U_SYP_TIPOTRAS	Tipo Traslado	
U_SYP_AREASOL	Area Solicitante	
U_SYP_NOARSOL	Nombre Area Solicitante	
U_SYP_FEREVPRO	Fecha Revaluacion Proveedor	
U_SYP_TIPOCOMT	Tipo de Compra TOYOTA	
U_SYP_INCOTERM	INCOTERM	
U_SYP_NROSINIESTRO	Nro. Siniestro	
U_SYP_ALMAENT	Almacén Entrega	
U_SYP_FECPROGENTR	Fecha Programada de Entrega	
U_SYP_HORPROGENTR	Hora Programada de Entrega	
U_SYP_MDSDG	Serie Doc (Guía)	
U_SYP_MDCDG	Correlativo Doc (Guía)	
U_SYP_VERSION	Versión	
U_SYP_ANO	Año	
U_SYP_PRECIO	Precio Unidad	
U_SYP_DESCUENTO	Descuento Unidad	
U_SYP_MOINICIAL	Monto Inicial Unidad	
U_SYP_MOFINANC	Monto a Financiar Unidad	
U_SYP_MOINTERES	Intereses Unidad	
U_SYP_DALEASING	Datos Leasing Unidad	
U_SYP_MOTOTAL	Total Operación Unidad	
U_SYP_DAPROPROP	Datos Aprobacion Propuesta	
U_SYP_DAPROCRED	Datos Aprobacion Credito	
U_SYP_FEPROXMANT	Fecha Proximo Mantenimiento	
U_SYP_CONTNOMBRE	Contacto Nombre Unidad	
U_SYP_CONTCELULAR	Contacto Celular Unidad	
U_SYP_CAMPANANO	Nombre Campaña	
U_SYP_TASACIONUSADO	Monto Tasacion Usado	
U_SYP_COARRENDA	Codigo arrendatario	
U_SYP_NOARRENDA	Nombre arrendatario	
*/
   

}
