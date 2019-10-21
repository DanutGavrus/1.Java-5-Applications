# Database and Reflexion Techniques
This project is made for processing customer orders for a warehouse by connecting to a database and implements the following: -User Interface; -Database Connection; -Relational Databases; -Reflection Techniques; -Layered Architecture.

## User's Interface
![alt text](https://github.com/DanutGavrus/Java-Applications/blob/master/0.%20User%20Interfaces/3.%20Working%20with%20Threads.png)

## Getting Started
You can find BOTH .exe and .jar versions of the project for EASY TESTING. Also, this project's code can be found in the layer folders.

### Installing
NO NEED FOR INSTALLATION ! First, add "ordermanagement.sql" database to your Database Server having name = 'root' and password = 'root' in order for this program to connect to it. Otherwise buttons would not interact with any table so no functionability will be remarked. Then:<br/>
Just open .exe file:
```
Double click on 4. Database and Reflexion Techniques.exe
```
If that does not work, open .jar file:
```
Double click on 4. Database and Reflexion Techniques.jar
```
If both do not work, a new project must be created containing the layer folders and the connector "mysql-connector-java-8.0.15.jar" included as a library. A database containing 3 tables: "client" "product" "orders" each one with its columns as seen in their representative classes, then run.<br/>

### Prerequisites
You need to have these installed:
```
-java jre: https://www.java.com/en/download/
-java jdk 11.0.3 or higher: https://www.oracle.com/technetwork/java/javase/downloads/index.html
-mariaDb or any other Database Server: https://mariadb.org/download/
```

### Built With
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - The Java IDE used

### Warning !
You might get a warning because the .exe file was not certificated, but it is a trusted file. For the .jar file there should be no warning !