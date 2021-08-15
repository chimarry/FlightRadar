<%@tag import="pro.artse.user.beans.FlightBean"%>
<%@tag description="Flights" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="title" fragment="true"%>
<jsp:useBean id="flightsBean" type="pro.artse.user.beans.FlightsBean"
	scope="request" />

<div class='container-fluid --main-table-container'>
	<table class="table table-sm --main-table">
		<thead>
			<tr>
				<th scope="col">Start location</th>
				<th scope="col">Destination</th>
				<th scope="col">Time of departure</th>
				<th scope="col">Status</th>
				<th scope="col">Flight type</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
</div>