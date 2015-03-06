create table account (
	id int not null auto_increment,
	name varchar(200),
	type varchar(100),
	balance decimal(12,3),
	primary key (id)
);