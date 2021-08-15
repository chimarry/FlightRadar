<%@tag description="Overall Site Layout" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="alert" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<%@attribute name="navmenu" fragment="true"%>
<%@attribute name="customScript" fragment="true"%>
<%@attribute name="customLinks" fragment="true"%>

<html>
<head>
<title>ETFBL_IP_Aero</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet" href='css/global.css'>
<link rel="stylesheet" href='css/index.css'>
<link rel="stylesheet" href='css/scrollbar.css'>
<link rel="stylesheet" href='css/header.css'>
<link rel="stylesheet" href='css/footer.css'>
<link rel="stylesheet" href='css/navmenu.css'>
<link rel="stylesheet" href='css/featuredFligths.css'>
<link rel="stylesheet" href='css/registration.css'>
<link rel="stylesheet" href='css/flightReservation.css'>
<link rel="stylesheet" href='css/mainTable.css'>
<link rel="stylesheet" href='css/notAuthenticated.css'>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<script src="https://kit.fontawesome.com/ea07956efd.js"
	crossorigin="anonymous"></script>
<script
	src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
<jsp:invoke fragment="customScript" />
<jsp:invoke fragment="customLinks" />
<script>
	var map;
	function initialize() {
		var mapOptions = {
			zoom : 8,
			center : new google.maps.LatLng(-34.397, 150.644)
		};
		map = new google.maps.Map(document.getElementById('map-canvas'),
				mapOptions);
	}

	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<div id="header">
		<jsp:invoke fragment="header" />
	</div>
	<%
	if (request.getAttribute("errorMessage") != null) {
	%>
	<div class="alert alert-danger  alert-dismissible fade show" style="margin-bottom: 0px;"
		role="alert"><%=request.getAttribute("errorMessage")%>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<%
	}
	%>
	<%
	if (request.getAttribute("successMessage") != null) {
	%>
	<div class="alert alert-success  alert-dismissible fade show" style="margin-bottom: 0px;"
		role="alert"><%=request.getAttribute("successMessage")%>
		<button type="button" class="close" data-dismiss="alert"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<%
	}
	%>
	<div id="navmenu">
		<jsp:invoke fragment="navmenu" />
	</div>
	<div id="body">
		<jsp:doBody />
	</div>
	<div id="footer">
		<jsp:invoke fragment="footer" />
	</div>
</body>
</html>