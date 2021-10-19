# Flight radar
--------------

The system is meant to show how JSP M2, JSF, Spring Boot and Angular work. It is implemented for educational purposes. The system is divided into five projects:
1) DatabaseLayer - provides API for interaction with MySQL database in Java,
2) UserApplication - JSP M2 application that uses Bootstrap to make a design responsive,
3) AdministratorApplication - JSF application that uses Bootstrap to make a design responsive,
4) EmployeeApplication - REST implemented with Spring Boot that uses [MapStruct](https://mapstruct.org/) for easier mapping between classes, [JPA Query methods](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods) for database access, Spring Security with JWT authorization. 
5) employee-application - Angular application that calls remote RESTful services.

## Features
---------------
### User application
The home page has a header with a login form, a footer with a Google map and option to send message to employees, a menu with options to see departures, arrivals, and if the user is logged in option to see all reservations and option to reserve a flight. Design must be responsive. The home page shows 5 newest arrivals and departures with status (Arrived/Departured, Waiting), and it is necessary to use Ajax call to refresh those flights each minute.
 The user is logged in using a username and password. There is also an option to create a new account.
 Users can have passenger or transport account.
 When a user with a passenger account makes the reservation, it chooses a seat number. 
 When a passenger with a transport account makes the reservation, it has the option to upload a specification file, which can later be downloaded. 
 Reservation that is not confirmed by the employee can be cancelled. Images below show application UI. There is also an RSS service for flights.  
Home page:  
![1](/img/1.PNG)  

Home page (mobile screen):  
![1,1](/img/1.1.PNG)  

Restricted access:  
![2](/img/2.PNG)

Reserve flight:  
![4](/img/4.PNG)  

Reserve flight (mobile view):  
![5](/img/5.PNG)  

See all reservations:  
![6](/img/6.PNG)  


### Administrator application
An administrator is defined on database level and has options to:
- Manage locations (countries and cities)
- Manage users (standard users and employees)
- To see the usage of the application in last 30 days

Images are shown below.  

Login form:  
![a1](/img/a1.PNG)  

Home page with diagram:  
![a2](/img/a2.PNG)  

Locations:  
![a3](/img/a3.PNG)  

Employees (with register tab opened):  
![a4](/img/a4.PNG)  


### Employee application
Employee application has backend implemented in Spring Boot, and frontend implemented in Angular with Material UI. REST services are secured so that only authorized and authenticated employees can access them. An employee has the option to create a flight (with multiple dates), to see messages, search by text and reply to a specific message. The employee can also manage reservations: cancel (it has to write the reason for cancellation) or confirm. Images are shown below.  

Login form:  
![e1](/img/e1.PNG)  

Messages:  
![e2](/img/e2.PNG)  

Reply to message (modal):  
![e3](/img/e3.PNG)  

Reservations:  
![e4](/img/e4.PNG)  


Reservations with addedd filter (Confirmed):  
![e5](/img/e5.PNG)  

Add flight:  
![e6](/img/e6.PNG)  
