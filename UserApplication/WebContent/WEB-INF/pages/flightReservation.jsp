    <%@page import="pro.artse.dal.dto.AccountRole" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <%@taglib prefix="l" tagdir="/WEB-INF/tags" %>
                <c:set var="roleBasedContent">
                    <% if((AccountRole)request.getAttribute("accountRole") == AccountRole.Passenger) {%>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="seatNumber">Seat number</label>
                                <input id="seatNumber" type="number" class="form-control  --flight-reservation-control"
                                    name="seatNumber" />
                            </div>
                        </div>
                        <% } else {%>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="cargoDescription">Description of cargo</label>
                                    <textarea class="form-control --flight-reservation-control" id="cargoDescription"
                                        name="cargoDescription" placeholder="Please enter your description here..."
                                        rows="3"></textarea>
                                </div>
                            </div>
                            <div class="form-row">
                                <div class="form-group col-md-6">
                                    <label for="customFileLabel">Specification file</label>
                                    <div class="custom-file">
                                        <label class="custom-file-label " for="specificationFile"
                                            id="customFileLabel">Example:
                                            specification.docs</label>
                                        <input type="file" class="custom-file-input --flight-reservation-control"
                                            id="specificationFile" name="specificationFile">
                                    </div>
                                </div>
                            </div>
                            <% } %>
                </c:set>
                <l:layout>
                    <jsp:attribute name="customLinks">
                        <script type="text/javascript"
                            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
                        <link rel="stylesheet"
                            href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
                    </jsp:attribute>
                    <jsp:attribute name="customScript">
                        <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
                        <script>
                            function setDatePicker() {
                                var departureDateInput = $('input[name="departureDate"]');
                                var container = $('#flightReservationForm').length > 0 ? $('#flightReservationForm').parent() : "body";
                                var options = {
                                    format: 'mm/dd/yyyy',
                                    container: container,
                                    todayHighlight: true,
                                    autoclose: true,
                                };
                                departureDateInput.datepicker(options);
                            }
                            function setFilePicker() {
                                $("#specificationFile").on("change", function () {
                                    var fileName = $(this).val().split("\\").pop();
                                    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
                                });
                            }
                            function getCountries() {
                                $.get("LocationController", function (responseJson) {
                                    $.each(responseJson, function (index, country) {
                                        $('#departureCountry').append('<option value=' + country.countryId + '>' + country.name + '</option>');
                                        $('#arrivalCountry').append('<option value=' + country.countryId + '>' + country.name + '</option>');
                                    });
                                });
                            }
                            function getCities(selectedCountryId, selectId) {
                                var x = document.getElementById(selectedCountryId).value;
                                $.get("LocationController?action=cities&countryId=" + x, function (responseJson) {
                                    $(selectId).empty();
                                    $.each(responseJson, function (index, city) {
                                        $(selectId).append('<option value=' + city.cityId + '>' + city.name + '</option>');
                                    });
                                });
                            }
                            $(document).ready(function () {
                                setDatePicker();
                                setFilePicker();
                                getCountries();
                            })
                        </script>
                    </jsp:attribute>
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
                        <div class="container-fluid --flight-reservation">
                            <form id="flightReservationForm" method="POST" action="FlightReservationController"
                                enctype="multipart/form-data">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="departureCountry">Departure country</label>
                                        <select id="departureCountry" class="form-control  --flight-reservation-control"
                                            required onchange="getCities('departureCountry','#departureCityName')">
                                            <option selected>Choose...</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="departureCityName">City</label>
                                        <select id="departureCityName"
                                            class="form-control  --flight-reservation-control" name="departureCityName"
                                            required>
                                            <option selected>Choose...</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label for="arrivalCountry">Country</label>
                                        <select id="arrivalCountry" class="form-control  --flight-reservation-control"
                                            required onchange="getCities('arrivalCountry','#arrivalCityName')">
                                            <option selected>Choose...</option>
                                        </select>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="arrivalCityName">City</label>
                                        <select id="arrivalCityName" class="form-control  --flight-reservation-control"
                                            name="arrivalCityName" required>
                                            <option selected>Choose...</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <!-- Date input -->
                                        <label class="control-label" for="departureDate">Departure date</label>
                                        <input class="form-control" id="departureDate" name="departureDate" required
                                            placeholder="MM/DD/YYY" type="text" />
                                    </div>
                                </div>
                                ${roleBasedContent}
                                <button type="submit" class="btn --flight-reservation-button">Submit</button>
                            </form>
                        </div>
                    </jsp:body>
                </l:layout>