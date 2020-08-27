# Sports-Hub-Buy-Sports-Tickets

Sport Hub is a web application that will allow customer to buy tickets from the application through online.

There would be two main roles, Store Manager and Customer

This application will allow Customer to perform basic actions like creating an account, update information, check the availability of tickets in different cities and add reviews. The Store Manager can add,update or delete a Customer tickets, events and customer.

# Technology Used:
HTML
CSS
Bootstrap
JavaScript
Ajax
Java
JSP
Servlets
Java Beans
MySQL
MongoDB

# SPORTS HUB User Document:

Instructions on show how to deploy and run the bank project:
1. Start tomcat server
2. Copy "SportsHub" folder to the tomcat webapps folder.
3. Start the MySQL database with database name as sportshub. The username is root and password is admin to connect to the MySQL DB.
4. Start the MongoDB server with mongo.exe and mongod.exe with database name as complaint and collection name as complaintStored.
5. In order to start the application open the browser and type http://localhost/EWA_Project or http://localhost/EWA_Project/Home.

Note:
To compile Java files, use this command:
javac *.java

If in case it doesn't work then, use the following cmd:

javac -cp "C:/tommy/tomcat-7.0.34-preconfigured/apache-tomcat-7.0.34/lib/mongo-java-driver-3.2.2.jar;C:\tommy\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\servlet-api.jar;C:\tommy\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\mysql-connector-java-5.1.23-bin.jar;C:\tommy\tomcat-7.0.34-preconfigured\apache-tomcat-7.0.34\lib\gson-2.6.2.jar;" *.java

# Role Information:

There are two Roles: a. Customer b. Store Manager

**Customer**
For testing purposes you can give as:
User Name: user
Password:  user

Customer can,

1. Buy tickets 
2. View and Canacel order
3. Write and View Review
4. Search for different events 
5. Buy tickets for different cities
6. Log out

**Store Manager**
User Name: admin
Password:  admin


Manager can,

1. Create New customer 
2. Create New Event
3. Update a Event 
4. Delete a Event 
5. Can access sales report
6. Can view data analytics reports
7. Can modify customer orders
8. Log out

This project was made in collbration with **Rakshith Churchagundi Amarnath** as the submission for the subject Enterprise Web Application Here in folder attached code for the system and a test sql file for testing purposes.
