<%@page import="pro.artse.user.beans.CountryBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="countriesBean"
	type="pro.artse.user.beans.CountriesBean" scope="request" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="l" tagdir="/WEB-INF/tags"%>
<c:set var="scripletContent">
	<%
	for (CountryBean countryBean : countriesBean.getCountries()) {
	%>
	<option><%=countryBean.getName()%></option>
	<%
	}
	%>
</c:set>
<l:layout>
	<jsp:attribute name="customScript">
                <script src='js/registration.js'></script>
            </jsp:attribute>
	<jsp:attribute name="header">
                <%@include file='header.jsp'%>
            </jsp:attribute>
	<jsp:attribute name="navmenu">
                <%@include file='navmenu.jsp'%>
            </jsp:attribute>
	<jsp:attribute name="footer">
                <%@include file='footer.jsp'%>
            </jsp:attribute>
	<jsp:body>
                <div class="container-fluid --register-form">
                    <div class='--register-cite'>
                        <cite>
                            Become a member of our company. As an employee you get to organize important features of our
                            company. As an user,you can reserve flights, see history of your previous flights and many other awesome things.
                        </cite>
                    </div>
                    <form method="POST" action="RegistrationController">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="name">Name</label>
                                <input type="text"
							class="form-control --register-control" id="name" name="name"
							required placeholder="Name">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="lastName">Last name</label>
                                <input type="text"
							class="form-control  --register-control" id="lastName"
							name="lastName" required placeholder="Last name">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="email">Email</label>
                                <input type="email"
							class="form-control  --register-control" id="email" name="email"
							required placeholder="Email">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="username">Username</label>
                                <input type="text"
							class="form-control  --register-control" id="username"
							name="username" required placeholder="Username">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="password">Password</label>
                                <input type="password"
							class="form-control  --register-control" id="password"
							name="password" required placeholder="Password">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="confirmPassword">Confirm password</label>
                                <input type="password"
							class="form-control  --register-control" id="confirmPassword"
							name="confirmPassword" placeholder="Password" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="adddress">Address</label>
                            <input type="text"
						class="form-control  --register-control" id="address"
						name="address" placeholder="1234 Main St" required>
                        </div>
                        <div class="form-group">
                            <label for="country">Country</label>
                            <select id="country"
						class="form-control  --register-control" name="country" required>
                            <option selected>Choose...</option>
                            		  ${scripletContent}
                            </select>
                        </div>
                        <div class="form-group">
                            <div class="form-check">
                                <input class="form-check-input"
							type="checkbox" id="transport" name="transport">
                                <label class="form-check-label"
							for="gridCheck">
                                    Transport
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input"
							type="checkbox" id="passenger" name="passenger">
                                <label class="form-check-label"
							for="gridCheck">
                                    Passenger
                                </label>
                            </div>
                        </div>
                        <button type="submit"
					class="btn --register-button">Sign in</button>
                    </form>
                </div>
            </jsp:body>
</l:layout>