<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@taglib prefix="l" tagdir="/WEB-INF/tags" %>
        <l:layout>
            <jsp:attribute name="customScript">
                <script src='js/registration.js'></script>
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
                <div class="container-fluid --register-form">
                    <div class='--register-cite'>
                        <cite>
                            Become a member of our company. As an employee you get to organize important features of our
                            company. As an user,you can reserve flights, see history of your previous flights and many other awesome things.
                        </cite>
                    </div>
                    <form>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputName">Name</label>
                                <input type="text" class="form-control --register-control" id="inputName"
                                    placeholder="Name">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="lastName">Last name</label>
                                <input type="text" class="form-control  --register-control" id="lastName"
                                    placeholder="Last name">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputEmail">Email</label>
                                <input type="email" class="form-control  --register-control" id="inputEmail"
                                    placeholder="Email">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputUsername">Username</label>
                                <input type="text" class="form-control  --register-control" id="inputUsername"
                                    placeholder="Username">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="inputPassword">Password</label>
                                <input type="password" class="form-control  --register-control" id="inputPassword"
                                    placeholder="Password">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="inputConfirmPassword">Confirm password</label>
                                <input type="password" class="form-control  --register-control"
                                    id="inputConfirmPassword" placeholder="Password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputAddress">Address</label>
                            <input type="text" class="form-control  --register-control" id="inputAddress"
                                placeholder="1234 Main St">
                        </div>
                        <div class="form-group">
                            <label for="inputState">Country</label>
                            <select id="inputState" class="form-control  --register-control">
                                <option selected>Choose...</option>
                                <option>...</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="employee">
                                <label class="form-check-label" for="gridCheck">
                                    Employee
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" id="user">
                                <label class="form-check-label" for="gridCheck">
                                    User
                                </label>
                            </div>
                        </div>
                        <button type="submit" class="btn --register-button">Sign in</button>
                    </form>
                </div>
            </jsp:body>
        </l:layout>