<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>

	<div class="container-fluid --header-container">
		<div class="row align-items-center">
			<div class="col-lg-8">
				<div class='--header-logo-container align-items-center'>
					<a href="IndexController"><img src='images/logo.png' alt='logo' /></a>
					<p>ETFBL_IP_Aero</p>
				</div>
			</div>
			<div class="col-lg-4">
				<form class='--login-form'>
					<div class="form-row">
						<div class="form-group col-md-4 --login-form-col">
							<label for="inputEmail4">&nbsp; Email</label>
							<input type="email" class="form-control --login-control" id="inputEmail4"
								placeholder="Email">
						</div>
						<div class="form-group col-md-4 --login-form-col">
							<label for="inputPassword4">&nbsp; Password</label>
							<input type="password" class="form-control --login-control" id="inputPassword4"
								placeholder="Password">
						</div>
						<div class="form-group col-md-2 --login-form-col">
							<button type="submit" class="btn --login-button align-items-bottom --login-control"
								id="loginbutton"><a href="RegistrationController">Log in</a></button>
						</div>
						<div class="form-group col-md-2 --login-form-col">
							<button class="btn --login-button align-items-bottom --login-control" id="registerbutton">
								<a href="RegistrationController">Sign in</a>
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>