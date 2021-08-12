<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@taglib prefix="l" tagdir="/WEB-INF/tags" %>
        <l:layout>
            <jsp:attribute name="customLinks">
                <script type="text/javascript"
                    src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
                <link rel="stylesheet"
                    href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
            </jsp:attribute>
            <jsp:attribute name="customScript">
                <script>
                    $(document).ready(function () {
                        var arrivalDateInput = $('input[name="arrivalDate"]');
                        var departureDateInput = $('input[name="departureDate"]');
                        var container = $('#flightReservationForm').length > 0 ? $('#flightReservationForm').parent() : "body";
                        var options = {
                            format: 'mm/dd/yyyy',
                            container: container,
                            todayHighlight: true,
                            autoclose: true,
                        };
                        arrivalDateInput.datepicker(options);
                        departureDateInput.datepicker(options);
                        $("#specificationFile").on("change", function () {
                            var fileName = $(this).val().split("\\").pop();
                            $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
                        });
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
                    <form id="flightReservationForm">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <!-- Date input -->
                                <label class="control-label" for="departureDate">Departure date</label>
                                <input class="form-control" id="departureDate" name="departureDate"
                                    placeholder="MM/DD/YYY" type="text" />
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputDepartureTime">Departure time:</label>
                                <input type="text" class="form-control  --flight-reservation-control"
                                    id="inputDepartureTime" placeholder="22:00">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <!-- Date input -->
                                <label class="control-label" for="arrivalDate">Arrival date</label>
                                <input class="form-control" id="arrivalDate" name="arrivalDate" placeholder="MM/DD/YYY"
                                    type="text" />
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputArrivalTime">Arrival time:</label>
                                <input type="text" class="form-control  --flight-reservation-control"
                                    id="inputArrivalTime" placeholder="23:10">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputState">Country</label>
                                <select id="inputState" class="form-control  --flight-reservation-control">
                                    <option selected>Choose...</option>
                                    <option>...</option>
                                </select>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputCity">City</label>
                                <select id="inputCity" class="form-control  --flight-reservation-control">
                                    <option selected>Choose...</option>
                                    <option>...</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputSeatNumber">Seat number</label>
                                <input id="inputSeatNumber" type="number"
                                    class="form-control  --flight-reservation-control" />
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputCargoDescription">Description of cargo</label>
                                <textarea class="form-control --flight-reservation-control" id="inputConfirmPassword"
                                    name="inputCargoDescription" placeholder="Please enter your description here..."
                                    rows="3"></textarea>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="customFileLabel">Specification file</label>
                                <div class="custom-file">
                                    <label class="custom-file-label " for="specificationFile"
                                        id="customFileLabel">Example: specification.docs</label>
                                    <input type="file" class="custom-file-input --flight-reservation-control"
                                        id="specificationFile" name="specificationFile">
                                </div>
                            </div>
                        </div>
                        <button type="submit" class="btn --flight-reservation-button">Sign in</button>
                    </form>
                </div>
            </jsp:body>
        </l:layout>