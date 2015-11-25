use db;

create table Employee{
	employeeId int AUTO INCREMENT,
	name varchar(50),
	phone varchar(50),
	address varchar(500),
    PRIMARY KEY (employeeId)
}