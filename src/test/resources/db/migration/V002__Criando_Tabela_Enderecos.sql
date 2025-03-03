create table addresses (
	id int generated always as identity primary key,
	id_client int not null,
	address varchar(250) not null,
	number varchar(20) not null,
	complement varchar(250),
	postal_code varchar(10),
	city varchar(250),
	state varchar(250),
	country varchar(250),
	foreign key (id_client) references clients(id)
)