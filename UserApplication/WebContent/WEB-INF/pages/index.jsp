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
            <jsp:attribute name="customScript">
                <script>
                    function loadData() {
                        $.get("IndexController?action=refresh", function (responseJson) {
                            $("#departuresTable").find("td").remove();
                            $("#departuresTable").find("tr").remove();
                            $("#arrivalsTable").find("td").remove();
                            $("#arrivalsTable").find("tr").remove();
                            $.each(responseJson[0], function (index, flight) {
                                $('#departuresTable').append('<tr>');
                                $('#departuresTable').append('<td>' + flight.departureCityName + '</td>');
                                $('#departuresTable').append('<td>' + flight.arrivalCityName + '</td>');
                                $('#departuresTable').append('<td>' + flight.time + '</td>');
                                $('#departuresTable').append('<td>' + flight.status + '</td>');
                                $('#departuresTable').append('<td>' + flight.type + '</td></tr>');
                            });
                            $.each(responseJson[1], function (index, flight) {
                                $('#arrivalsTable').append('<tr>');
                                $('#arrivalsTable').append('<td>' + flight.departureCityName + '</td>');
                                $('#arrivalsTable').append('<td>' + flight.arrivalCityName + '</td>');
                                $('#arrivalsTable').append('<td>' + flight.time + '</td>');
                                $('#arrivalsTable').append('<td>' + flight.status + '</td>');
                                $('#arrivalsTable').append('<td>' + flight.type + '</td></tr>');
                            });
                        });
                    }
                    $(document).ready(function () {
                        loadData();
                        setInterval(function () {
                            loadData()
                        }, 60000); // refresh every 1min
                    });

                </script>
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
                                <jsp:attribute name="tableBody">
                                    <tbody id='arrivalsTable'>
                                    </tbody>
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
                                <jsp:attribute name="tableBody">
                                    <tbody id='departuresTable'>
                                    </tbody>
                                </jsp:attribute>
                            </l:featuredFligths>
                        </div>
                        <div class='col-lg-1 col-md-0'>
                        </div>
                    </div>
                </div>
            </jsp:body>
        </l:layout>