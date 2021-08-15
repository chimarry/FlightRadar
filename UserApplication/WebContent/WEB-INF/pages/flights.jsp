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
					function get(name) {
						if (name = (new RegExp('[?&]' + encodeURIComponent(name) + '=([^&]*)')).exec(location.search))
							return decodeURIComponent(name[1]);
					}
					function loadData() {
						$.get("FlightsController?action=refresh&isDeparture=" + get('isDeparture'), function (responseJson) {
							$("#tableBody").find("td").remove();
							$("#tableBody").find("tr").remove();
							$.each(responseJson, function (index, flight) {
								$('#tableBody').append('<tr>');
								$('#tableBody').append('<td>' +flight.departureCityName + '</td>');
								$('#tableBody').append('<td>' + flight.arrivalCityName  + '</td>');
								$('#tableBody').append('<td>' + flight.time + '</td>');
								$('#tableBody').append('<td>' + flight.status + '</td>');
								$('#tableBody').append('<td>' + flight.type + '</td></tr>');
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
				<div class='container-fluid --main-table-container'>
					<table id="#flightsTable" class="table table-sm --main-table">
						<thead>
							<tr>
								<th scope="col">Start location</th>
								<th scope="col">Destination</th>
								<th scope="col">Time of departure</th>
								<th scope="col">Status</th>
								<th scope="col">Flight type</th>
							</tr>
						</thead>
						<tbody id="tableBody">
						</tbody>
					</table>
				</div>
			</jsp:body>
		</l:layout>