<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
	<nav class="navbar navbar-expand-md --navmenu">
		<a class="navbar-brand" href="IndexController">Home</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
			aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
			<i class="fas fa-bars --navbar-icon"></i>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link active" href="FlightsController?action=load&isDeparture=true">Departures<span class="sr-only">(current)</span></a>
				<a class="nav-item nav-link" href="FlightsController?action=load&isDeparture=false">Arrivals</a>
				<a class="nav-item nav-link" href="FlightReservationController?action=all">All reservations</a>
				<a class="nav-item nav-link" href="FlightReservationController?action=form">Flight reservation</a>
			</div>
		</div>
	</nav>