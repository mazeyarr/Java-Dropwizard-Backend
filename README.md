dropwizard-mysql-crud-example
=============================

Taken from this [link](https://github.com/HoldInArms/dropwizard-mssql-crud-example) and ported to mysql

### Pre-requisites

MYSQL database: Update the main.yml if your configuration differs. In this example, we use

	database: database_name
	user: database_user
	pass: database_password

### Build:

	mvn clean package
	

### Database creation:

	java -jar target/iipsen2-1.0.0.jar db migrate main.yml
	
	
### Run:

	java -jar target/iipsen2-1.0.0.jar server main.yml
	
### Watch changes
    
    mvn fizzed-watcher:run
	
	
### Open browser pointing at

	http://localhost:9000

