<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="accountBean" type="pro.artse.user.beans.AccountBean"
	scope="session" />

<div class="container-fluid --header-container">
	<div class="row align-items-center">
		<div class="col-lg-8">
			<div class='--header-logo-container align-items-center'>
				<a href="IndexController"><img src='images/logo.png' alt='logo' /></a>
				<p>ETFBL_IP_Aero</p>
			</div>
		</div>
		<%
		if (accountBean.isLoggedIn()) {
		%>
		<div class="col-lg-4">
			<div class='--account-info'>
				<p>
					<i class="fas fa-user-alt --account-icon"> </i><%=accountBean.toString()%>
				</p>
				<a class="btn --login-button align-items-bottom --login-control"
					id="logoutbutton" href="IndexController?action=logout">Log out</a>
			</div>
		</div>
		<%
		} else {
		%>
		<div class="col-lg-4">
			<form class='--login-form' action="IndexController" method="POST">
				<div class="form-row">
					<div class="form-group col-md-4 --login-form-col">
						<label for="username">&nbsp; Username</label> <input type="text"
							class="form-control --login-control" id="username"
							placeholder="Username" name="username" required>
					</div>
					<div class="form-group col-md-4 --login-form-col">
						<label for="password">&nbsp; Password</label> <input
							type="password" class="form-control --login-control"
							id="password" name="password" placeholder="Password" required>
					</div>
					<div class="form-group col-md-2 --login-form-col">
						<button type="submit"
							class="btn --login-button align-items-bottom --login-control"
							id="loginbutton">Log in</button>
					</div>
					<div class="form-group col-md-2 --login-form-col">
						<a class="btn --login-button align-items-bottom --login-control"
							id="registerbutton" href="RegistrationController">Sign in</a>
					</div>
				</div>
			</form>
		</div>
		<%
		}
		%>
	</div>
</div>