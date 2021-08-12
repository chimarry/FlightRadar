<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@taglib prefix="l" tagdir="/WEB-INF/tags" %>
        <l:layout>
            <jsp:attribute name="header">
                <%@include file='header.jsp' %>
            </jsp:attribute>
            <jsp:attribute name="navmenu">
                <%@include file='navmenu.jsp' %>
            </jsp:attribute>
            <jsp:attribute name="footer">
                <%@include file='footer.jsp' %>
            </jsp:attribute>
            <jsp:body>
                <l:flights></l:flights>
            </jsp:body>
        </l:layout>