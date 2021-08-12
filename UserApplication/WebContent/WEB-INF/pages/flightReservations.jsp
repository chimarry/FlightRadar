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
            <jsp:body>
                <div class="container-fluid --main-table-container">
                    <div class="row">
                        <div class='col-12'>
                            <table class="table table-sm --main-table">
                                <thead>
                                    <tr>
                                        <th scope="col">Departure country</th>
                                        <th scope="col">Departure city</th>
                                        <th scope="col">Arrival country</th>
                                        <th scope="col">Arrival city</th>
                                        <th scope="col">Seat number</th>
                                        <th scope="col">Cargo description</th>
                                        <th scope="col">Specification file</th>
                                        <th scope="col">Departure datetime</th>
                                        <th scope="col">Arrival datetime</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Change status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>California</td>
                                        <td>Boston</td>
                                        <td>Serbia</td>
                                        <td>Belgrade</td>
                                        <td>12</td>
                                        <td></td>
                                        <td></td>
                                        <td>12/12/2021 8:00</td>
                                        <td>12/12/2021 20:00</td>
                                        <td>Approved</td>
                                        <td><button class='btn btn-danger' disabled>Cancel</button></td>
                                    </tr>
                                    <tr>
                                        <td>Russia</td>
                                        <td>Moscow</td>
                                        <td>Bosnia and Herzegovina</td>
                                        <td>Banja Luka</td>
                                        <td></td>
                                        <td>This is a huge description of an imaginary cargo being shipped from Russia to Bosnia, in late evening time.</td>
                                        <td><button class='btn btn-secondary'>Download</button></td>
                                        <td>13/12/2021 21:00</td>
                                        <td>13/12/2021 02:00</td>
                                        <td>New</td>
                                        <td><button class='btn btn-danger'>Cancel</button></td>
                                    </tr>
                                    <tr>
                                        <td>Netherlands</td>
                                        <td>Amsterdam</td>
                                        <td>Bosnia and Herzegovina</td>
                                        <td>Sarajevo</td>
                                        <td>11</td>
                                        <td></td>
                                        <td></td>
                                        <td>13/11/2021 21:00</td>
                                        <td>13/11/2021 03:00</td>
                                        <td>Canceled</td>
                                        <td><button class='btn btn-danger' disabled>Cancel</button></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </jsp:body>
        </l:layout>