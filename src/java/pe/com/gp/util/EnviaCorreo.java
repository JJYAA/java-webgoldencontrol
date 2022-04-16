package pe.com.gp.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import pe.com.gp.entity.ListaGenerica;

/**
 * Clase para el envio de Correos Electronicos.
 *
 * @author: Alex D. Cabello.
 * @version: 01/2017
 */
public final class EnviaCorreo {

    private final String HOST = "smtp.gmail.com" ; //"smtp.office365.com"; // outlook.office365.com
    private final int PORT = 587; //587; // 25
    private final String AUTH = "true";
    private final String TLS = "true";
    //private final String TRANSPORT = "smtp";

    /**
     * Envia correo(s) a el(los) destinatario(s) especificado(s); tambien, si se
     * lo desea, envia un archivo adjunto.
     *
     * @param nombreFrom Nombre del usuario remitente.
     * @param correoFrom Correo del remitente.
     * @param claveFrom Clave del correo del remitente.
     * @param asunto Asunto del correo.
     * @param destinatarios Correos destino.
     * @param cc Correos a enviar con copia.
     * @param mensaje Mensaje del correo.
     * @param adjuntos Ejm. indice="D:\\mis_archivos\\archivo.png"; descripcion=
     * "archivo.png"
     * @param tema Tema (estilo) del correo.
     *
     * @return Null si todo salio bien, sino captura el error.
     */
    public String prepara(
            String nombreFrom,
            final String correoFrom,
            final String claveFrom,
            String asunto,
            String destinatarios,
            String cc,
            String mensaje,
            List<ListaGenerica> adjuntos,
            //String adjuntos, // Ejm. D:\\foto.png
            String tema) {

        String result = null;
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", AUTH);
            props.put("mail.smtp.starttls.enable", TLS);
            props.put("mail.smtp.host", HOST);
            props.put("mail.smtp.port", PORT);

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(correoFrom, claveFrom);
                }
            });
            //session.setDebug(true);

            // texto cuerpo
            BodyPart texto = new MimeBodyPart();
            if ("T01".equals(tema)) {
                texto.setContent(tema01(mensaje), "text/html; charset=UTF-8");
            } else {
                texto.setContent(mensaje, "text/html; charset=UTF-8");
            }

            /*
             // Adjunto
             BodyPart adjunto = new MimeBodyPart();
             if (pathArchivoAdjunto != null) {
             adjunto.setDataHandler(
             new DataHandler(new FileDataSource(pathArchivoAdjunto)));
             adjunto.setFileName(nombreArchivoAdjunto);
             }

             // Agrupar texto y adjunto.
             MimeMultipart multiParte = new MimeMultipart();
             multiParte.addBodyPart(texto);
             if (pathArchivoAdjunto != null) {
             multiParte.addBodyPart(adjunto);
             }
             */
            // Agrupar mensaje y adjunto.
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            if (adjuntos != null && !adjuntos.isEmpty()) {
                for (ListaGenerica adjunto : adjuntos) {
                    File file = new File(adjunto.getIndice());
                    if (file.exists()) {
                        DataSource source = new FileDataSource(adjunto.getIndice());
                        BodyPart messageBodyPart = new MimeBodyPart();
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(adjunto.getDescripcion());
                        multiParte.addBodyPart(messageBodyPart);
                    }
                }
            }

            /*if (adjuntos != null && adjuntos.trim().length() > 0) {
                String arrAdjuntos[] = adjuntos.split(",");
                for (String adj : arrAdjuntos) {
                    DataSource source = new FileDataSource(adj);
                    BodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.setDataHandler(new DataHandler(source));
                    messageBodyPart.setFileName(adj);
                    multiParte.addBodyPart(messageBodyPart);
                }
            }*/
            // Composicion
            MimeMessage message = new MimeMessage(session);
            message.setHeader("Content-Type", "text/html; charset=UTF-8");

            message.setFrom(new InternetAddress(correoFrom, nombreFrom));

            if (destinatarios != null && destinatarios.trim().length() > 0) {
                String mails[] = destinatarios.split("\\s*,\\s*|\\s*;\\s*");
                for (String mail : mails) {
                    if (mail.trim().length() > 0) {
                        message.addRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(mail)});
                    }
                }
            } else {
                throw new MessagingException("Exception thrown");
            }

            if (cc != null && cc.trim().length() > 0) {
                String mailsCC[] = cc.split("\\s*,\\s*|\\s*;\\s*");
                for (String mail : mailsCC) {
                    if (mail.trim().length() > 0) {
                        message.addRecipients(Message.RecipientType.CC, new InternetAddress[]{new InternetAddress(mail)});
                    }
                }
            }

            message.setSubject(asunto, "UTF-8"); // comentado el 28/06/2018           
            message.setContent(multiParte, "text/html; charset=UTF-8"); // message.setContent(multiParte, "text/html");
            //Transport transport = session.getTransport(TRANSPORT);            // Comentado el 20-06-2019 por acabello
            //transport.connect(HOST, correoFrom, nombreFrom);                  // Comentado el 20-06-2019 por acabello
            //transport.sendMessage(message, message.getAllRecipients());       // Comentado el 20-06-2019 por acabello
            //transport.close();                                                // Comentado el 20-06-2019 por acabello
            Transport.send(message, message.getAllRecipients());
        } catch (MessagingException | UnsupportedEncodingException e) {
            result = "" + e;
        }

        return result;
    }

    // Armador del cuerpo del correo 
    private String tema01(String mensaje) {
        StringBuilder sb = new StringBuilder();
        sb.append("<table border=\"0\" cellpadding=\"13\" cellspacing=\"0\" style=\"border: 0px;border-collapse: collapse;font-family: Tahoma,Arial,sans-serif;font-size: 12px;margin: 0px 0px 30px 0px;color: #333333;\">");
        sb.append("<tr>");
        sb.append("<td style=\"padding: 0px\">");
        sb.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
        sb.append("<tr>");
        sb.append("<td>").append(mensaje).append("</td>");
        sb.append("</tr>");
        sb.append("<tr>");
        sb.append("<td style=\"vertical-align: middle;padding-top: 15px;color: #777777;font-size: 11px;\">");
        sb.append("<p style=\"display: block;padding: 0;margin: 0;\">-</p>");
        sb.append("<br/>");
        sb.append("<p style=\"display: block;padding: 0;margin: 0;\">Importante</p>");
        sb.append("<p style=\"display: block;padding: 0;margin: 0;\">");
        sb.append("Este correo fue generado y enviado autom&aacute;ticamente por nuestros sistemas. ");
        sb.append("<strong>Por favor no responda a este remitente.</strong>");
        sb.append("</p>");
        sb.append("<br/>");
        sb.append("<p style=\"display: block;padding: 0;margin: 0;\">Aviso Legal</p>");
        sb.append("<p style=\"display: block;padding: 0;margin: 0;\">");
        sb.append("Este mensaje es solamente para la(s) persona(s) a la(s) que va dirigido. Puede ");
        sb.append("contener informaci&oacute;n confidencial o legalmente protegida. ");
        sb.append("Si usted ha recibido este mensaje por error, le rogamos que borre de su ");
        sb.append("sistema inmediatamente el mensaje as&iacute; como todas sus copias y notifique al remitente.");
        sb.append("</p>");
        sb.append("</td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</td>");
        sb.append("</tr>");
        sb.append("</table>");
        return sb.toString();
    }
}
