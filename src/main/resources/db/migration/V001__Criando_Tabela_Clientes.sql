create table clients (
	id int generated always as identity primary key,
	first_name varchar(250) not null,
	last_name varchar(250),
	email varchar(250) not null
)