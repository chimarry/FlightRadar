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
					function formatDate(date) {
						var d = new Date(date),
							month = '' + (d.getMonth() + 1),
							day = '' + d.getDate(),
							year = d.getFullYear();

						if (month.length < 2)
							month = '0' + month;
						if (day.length < 2)
							day = '0' + day;

						return [year, month, day].join('-');
					}

					function prevDate(date) {
						var currentDate = Date.parse(document.getElementById('date').innerHTML);
						const yesterday = new Date(currentDate);
						yesterday.setDate(yesterday.getDate() - 1);
						return formatDate(yesterday);
					}
					function nextDate(date) {
						var currentDate = Date.parse(document.getElementById('date').innerHTML);
						const next = new Date(currentDate);
						next.setDate(next.getDate() + 1);
						return formatDate(next);
					}
					function loadData(date) {
						document.getElementById('date').innerHTML = date;
						$.get("FlightsController?action=refresh&isDeparture=" + get('isDeparture') + "&date=" + date, function (responseJson) {
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
						loadData(formatDate(new Date()));
						setInterval(function () {
							loadData(document.getElementById('date').innerHTML)
						}, 60000); // refresh every 1min
					});
					window.addEventListener('resize', changeOnResize);
				</script>
			</jsp:attribute>
			<jsp:body>
				<div class='container-fluid --main-table-container'>
					<div class='row'>
						<div class='col-12 --flights-filter'>
							<button id='prevBtn' onclick='loadData(prevDate())'>&lt;&lt; &nbsp;Previous</button>
							<p id='date'></p>
							<button id='nextBtn' onclick='loadData(nextDate())'>Next &nbsp;&gt;&gt;</button>
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