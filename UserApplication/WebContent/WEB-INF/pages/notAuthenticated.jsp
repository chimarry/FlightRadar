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
               <div class='container-fluid --not-authenticated-container'>
                <div class='row'>
                    <div class='col-md-3 col-0'></div>
                    <div class='col-md-3 col-6 d-flex justify-content-center --not-authenticated-center'>
                        You are not authenticated to access this page. Please, log in to continue.
                    </div>
                    <div class='col-md-3 col-6 d-flex justify-content-center --not-authenticated-center'>
                        <img src='images/lock.png' />
                    </div>
                    <div class='col-md-3 col-0'></div>
                </div>
               </div>
            </jsp:body>
        </l:layout>