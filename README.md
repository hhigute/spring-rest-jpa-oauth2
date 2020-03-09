# Spring + JPA + OAuth2 (Password Flow)

This example used JPA and Security/OAuth2 (OAuth  __password flow__ )

I used [Spring Boot](https://start.spring.io) to create the base project.

_Dependencies:_

* Spring Web
* Spring Data JPA

Bellow others Maven dependencies that I configured manually.

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-oauth2</artifactId>
		</dependency>
		...
		
		<dependencyManagement>
			<dependencies>
				<dependency>
					<groupId>org.springframework.cloud</groupId>
					<artifactId>spring-cloud-dependencies</artifactId>
					<version>Hoxton.SR1</version>
					<type>pom</type>
					<scope>import</scope>
				</dependency>
			</dependencies>
		</dependencyManagement>


### _Setup / Pre-Requirements_

We need to create the tables bellow to authenticate the user and store access tokens. 


![Alt text](./doc/DatabaseDiagram.png?raw=true "Database diagram")




* __users__
     - _This table store the user informations that request "authorization"._

   
* __role__
     - _This table store the roles that users can have._
   
   
* __usersRole__
     - _This table associates users and roles._
   
   
* __oauth_access_token__
     - _This table store the access token used during the "authentication" flow._   
   
   
* __oauth_refresh_token__
     - _This table store the refresh token._   
   
    
To create the tables/structure previous described, you have to follow the steps bellow.


* MS SQL Server 2017 Express Edition

>Run the files/scripts .sql found in the folder "src/main/resources/setup"


``` 
1) DDL.sql
2) DML.sql
```

* If you don't have or don't want to install SQLServer, you can follow the steps bellow to create your database in `Docker` container.



1. **Start Docker** 
   
   In my case I used Docker Desktop for windows
   
2. **Create MS SQL Server container**

   Run the command bellow in Prompt Ms-DOS </br>
   PS: Password must be strong </br></br> 
   `docker run -d -p 1433:1433 -e sa_password=<<SET_YOUR_PASSWORD>> -e ACCEPT_EULA=Y --name SqlServerContainer microsoft/mssql-server-windows-express`
   
   ![Alt text](./doc/dockerrun.png?raw=true "docker SqlServerContainer")	

3. **Check Installation**
	
   `docker ps -a`
   
   ![Alt text](./doc/dockerps-a.png?raw=true "docker ps -a")	
	
4. **Connect to SqlServerContainer**	
	
   `sqlcmd -S localhost,1433 -U SA -P <<SET_YOUR_PASSWORD>>`
   
   ![Alt text](./doc/sqlcmd.png?raw=true "sqlcmd connect SqlServerContainer")

5. **Check Connection**
		
   `select @@version`
   	
   ![Alt text](./doc/sqlcmd_check.png?raw=true "check SqlServerContainer")
	
6. **Create tables to run our project**

   `Run the files/scripts .sql found in the folder "src/main/resources/setup"`
	
   `sqlcmd -S localhost,1433 -U SA -P <<SET_YOUR_PASSWORD>> -i <PATH>\DDL.sql -o <PATH>\output_DDL.txt`
	
   `sqlcmd -S localhost,1433 -U SA -P <<SET_YOUR_PASSWORD>> -i <PATH>\DML.sql -o <PATH>\output_DML.txt`
   
   ![Alt text](./doc/sqlcmd_tables.png?raw=true "check SqlServerContainer")


### _Update file application.properties_


		###### Server ######
		server.port=8082
		
		###### Datasource ######
		spring.datasource.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
		spring.datasource.url=jdbc:sqlserver://localhost;databaseName=Investment
		spring.datasource.username=<<SET_YOUR_USERNAME>>
		spring.datasource.password=<<SET_YOUR_PASSWORD>>
		################### JPA Configuration ##########################
		spring.jpa.show-sql=true
		spring.jpa.properties.hibernate.format_sql = true
		spring.jpa.hibernate.dialect=org.hibernate.dialect.SQLServer2012Dialect
		spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
		################### Security Configuration OAuth2 ##########################
		oauth.authorization.server.username=higute
		oauth.authorization.server.password=higutepwd


### _Install maven dependencies_ 

	mvn clean install
   
   ![Alt text](./doc/mvn_cleaninstall.png?raw=true "mvn clean install")


### _Build and start the application_

   I used **Postman** to invoke the services.  
	
- **Set Authorization infos to get Access Token (POST)**   
   
	URL: http://localhost:8082/oauth/token
   
> Username and Password configured at application.properties 
   
  ![Alt text](./doc/postman_authorization1.png?raw=true "Authorization") 
  
> Username and Password inserted into table "users" (DML.sql)
  
  ![Alt text](./doc/postman_authorization2.png?raw=true "Authorization") 
  
- **Execute secured service/API using Access Token (GET) **
   
   
> URL: http://localhost:8082/api/v1/secured/user
   
  ![Alt text](./doc/postman_customapi_with_access_token.png?raw=true "Secured API") 


