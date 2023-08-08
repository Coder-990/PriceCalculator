# PriceCalculator

# Overview
Goal of this REST service is to create and store 'Product' model 
where price in USD has to be computed by HNB buying currency exchange rate.

# Guidelines
For starting the project, requirement is to have PostgreSQL database (native or as Docker image).
Project contains simple script for creating Docker image of PostgreSQL database. If you do nat have installed PostgreSQL,
look here for instructions: https://www.postgresql.org/download/ for windows or other platform.

For those who still don't know what is Docker here are instructions for docker installation on windows OS : https://docs.docker.com/desktop/install/windows-install/ <br>
for Linux OS:  https://docs.docker.com/desktop/install/linux-install/ <br>
for Arch Linux OS: https://docs.docker.com/desktop/install/archlinux/ <br>
for Mac OS: https://docs.docker.com/desktop/install/mac-install/ <br>

If you have docker installed then you have to clone project and follow these steps:
1. Open project using IntelliJ IDEA, and wait for project to load, after completing go to File-> Project Structure -> under Project Setting pick 'Project' and set SDK Java 17 LTS.    
2. Point yourself in project directory /src/man/resources in terminal and hit command: 'docker-compose -f application-docker.yaml up' or open the file and simply pres play button (triangle shape) for downloading image of latest Postgres database.
3. Check if there are some containers running by hitting command: 'docker ps', if you see a postgres container of name 'ingemark' you are ready to go further, or you can check in Docker desktop app if container is running.
4. In the upper right corner in IntelliJ open 'Database' -> 'Plus' icon to add a new data source -> and pick PostgreSQL. Information about fields 'User', 'Password', and 'Database' are write in file 'application.properties' in directory: src/main/resources. Click on the name of db 'postgresdb' and put checkmark only on 'postgresdb' and pick only 'public' schema. 
5. After filing information for connecting to db, click on icon 'Test Connection' to be sure that db is connected to application, you will receive a successfully message of connection.
6. Start the service 'PriceCalculatorApplication' and for the first time because there are no any tables in db you will receive  exceptions while app is trying to start up, just ignore them, after next start up, there will no be any exceptions.
7. Start the 'Postman' app and import 'postman_collection.json' file that is directory in project.
8. Pick from any requests and enjoy the app.
# Support
Please enter an issue in the repo for any questions or problems.
Alternatively, please contact me at borovcakdomagoj@gmail.com


