<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@taglib prefix="l" tagdir="/WEB-INF/tags" %>
        <l:layout>
            <jsp:attribute name="header">
                <%@include file='loggedInHeader.jsp' %>
            </jsp:attribute>
            <jsp:attribute name="navmenu">
                <%@include file='navmenu.jsp' %>
            </jsp:attribute>
            <jsp:attribute name="footer">
                <%@include file='footer.jsp' %>
            </jsp:attribute>
            <jsp:body>
                <div class="container-fluid --index-container">
                    <div class="row">
                        <div class='col-lg-1 col-md-0'>
                        </div>
                        <div class="col-lg-5 col-md-6">
                            <l:featuredFligths>
                                <jsp:attribute name="title">
                                    <h4 class="--flight-title text-center">Arrivals
                                        <img src="images/arrival.svg">
                                    </h4>
                                </jsp:attribute>
                            </l:featuredFligths>
                        </div>
                        <div class="col-lg-5 col-md-6">
                            <l:featuredFligths>
                                <jsp:attribute name="title">
                                    <h4 class="--flight-title text-center">Departures
                                        <img src="images/departure.svg">
                                    </h4>
                                </jsp:attribute>
                            </l:featuredFligths>
                        </div>
                        <div class='col-lg-1 col-md-0'>
                        </div>
                    </div>
                </div>
            </jsp:body>
        </l:layout>