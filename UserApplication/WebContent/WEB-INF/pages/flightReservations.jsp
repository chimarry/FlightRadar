<%@ page import="pro.artse.user.beans.FlightReservationBean" %>
    <%@ page import="pro.artse.dal.dto.FlightReservationStatus" %>
        <%@ page import="pro.artse.dal.dto.AccountRole" %>
            <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
                <jsp:useBean id="flightReservationsBean" type="pro.artse.user.beans.FlightReservationsBean"
                    scope="request" />
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <%@taglib prefix="l" tagdir="/WEB-INF/tags" %>
                        <c:set var="flightReservations">
                            <thead>
                                <tr>
                                    <th scope="col">Departure country</th>
                                    <th scope="col">Departure city</th>
                                    <th scope="col">Arrival country</th>
                                    <th scope="col">Arrival city</th>
                                    <% if((AccountRole)request.getAttribute("accountRole")==AccountRole.Passenger) {%>
                                        <th scope="col">Seat number</th>
                                        <%} else { %>
                                            <th scope="col">Cargo description</th>
                                            <th scope="col">Specification file</th>
                                            <% } %>
                                                <th scope="col">Departure on</th>
                                                <th scope="col">Arrival on</th>
                                                <th scope="col">Created on</th>
                                                <th scope="col">Status</th>
                                                <th scope="col">Change status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${flightReservationsBean.getFlightReservations()}" var="item">
                                    <tr>
                                        <td>${item.getDepartureCountryName()}</td>
                                        <td>${item.getDepartureCityName()}</td>
                                        <td>${item.getArrivalCountryName()}</td>
                                        <td>${item.getArrivalCityName()}</td>
                                        <% if((AccountRole)request.getAttribute("accountRole")==AccountRole.Passenger)
                                            {%>
                                            <td>${item.getSeatNumber()}</td>
                                            <%} else { %>
                                                <td>${item.getCargoDescription()}</td>
                                                 <c:url var="downloadFile"
                                                                value="FlightReservationController">
                                                                <c:param name="file" value="${item.getFileSpecificationName()}" />
                                                                <c:param name="action" value="download" />
                                                            </c:url>
                                                <td><button class='btn btn-secondary'><a href="${downloadFile}">Download</a></button></td>
                                                <% } %>
                                                    <td>${item.getDepartureOn()}</td>
                                                    <td>${item.getArrivalOn()}</td>
                                                    <td>${item.getCreatedOn()}</td>
                                                    <td>${item.getStatus()}</td>
                                                    <c:choose>
                                                        <c:when test="${item.getStatus()==FlightReservationStatus.New}">
                                                            <c:url var="changeStatus"
                                                                value="FlightReservationController">
                                                                <c:param name="status" value="Canceled" />
                                                                <c:param name="action" value="change" />
                                                                <c:param name="flightReservationId"
                                                                    value="${item.getFlightReservationId()}" />
                                                            </c:url>
                                                            <td><button class='btn btn-danger'>
                                                                    <a href="${changeStatus}">
                                                                        Cancel
                                                                    </a>
                                                                </button></td>
                                                        </c:when>
                                                        <c:when test="${item.getStatus()!=FlightReservationStatus.New}">
                                                            <td><button class='btn btn-danger' disabled>Cancel</button>
                                                            </td>
                                                        </c:when>
                                                    </c:choose>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </c:set>
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
                                <div class="container-fluid --main-table-container">
                                    <div class="row">
                                        <div class='col-12'>
                                            <table class="table table-sm --main-table">
                                                ${flightReservations}
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </jsp:body>
                        </l:layout>