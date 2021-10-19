# Flight radar
--------------

System is meant to show how JSP M2, JSF, Spring Boot and Angular work. It is implemented in educational purposes. System is diveded into five projects:
1) DatabaseLayer - provides API for interaction with MySQL database in Java,
2) UserApplication - JSP M2 application that uses Bootstrap to make design responsive,
3) AdministratorApplication - JSF application that uses Bootstrap to make design responsive,
4) EmployeeApplication - REST implemented with Spring Boot that uses [MapStruct](https://mapstruct.org/) for easier mapping between classes, [JPA Query methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods) for database access, Spring security with JWT authorization. 
5) employee-application - Angular application that calls remote RESTful services.

## Features
---------------
### User application
Home page has header with login form, footer with Google map and option to send message to employees, menu with options to see departures, arrivals, and if user is logged in option to see all reservations and option to reserve a flight. Design must be responsive. Home page shows 5 newest arrivals and departures with status (Arrived/Departured, Waiting), and it is necessary to use Ajax call to refresh those flights each minute. User is logged in using username and password. There is also an option to create new account. User can have passenger or transport account. When user with passenger account makes reservation, it chooses seat number. When passenger with trasnport account makes reservation, it has option to upload specification file, which can later be downloaded. Reservation that is not confirmed by employee can be cancelled. Images bellow show application UI.
![1](/img/1.PNG)
------------------
![1,1](/img/1.1.PNG)
-----------------
![2](/img/2.PNG)
-------------
![4](/img/4.PNG)
----------------
![5](/img/5.PNG)
----------------
![6](/img/6.PNG)
-------------------

### Administrator application

### Employee application
