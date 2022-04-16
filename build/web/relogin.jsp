<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.sendRedirect("LoginAuxiliarAction.do?operacion=logout");
    return;
%>