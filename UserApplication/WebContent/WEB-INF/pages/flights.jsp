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
					function changeOnResize() {
						if (window.innerWidth < 576) {
							document.querySelector('#prevBtn').textContent = '<<';
							document.querySelector('#nextBtn').textContent = '>>';
						}
					}
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
								$('#tableBody').append('<td>' + flight.departureCityName + '</td>');
								$('#tableBody').append('<td>' + flight.arrivalCityName + '</td>');
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
					window.addEventListener('resize', changeOnResize);
				</script>
			</jsp:attribute>
			<jsp:body>
				<div class='container-fluid --main-table-container'>
					<div class='row'>
						<div class='col-12 --flights-filter'>
							<button id='prevBtn'>&lt;&lt; &nbsp;Previous</button>
							<p>15-08-2021</p>
							<button id='nextBtn'>Next &nbsp;&gt;&gt;</button>
						</div>
					</div>
					<div class='row'>
						<div class='col-12'>
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
					</div>
				</div>
			</jsp:body>
		</l:layout>