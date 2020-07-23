create table book (
	id 	int auto_increment primary key,
	isbn bigint,
	title varchar(255),
	author varchar(255),
	book_type int,
	publisher varchar(255),
	publication_year int,
	price double
);
create table client (
	id int,
	pesel varchar(11) PRIMARY KEY,
	name varchar(255),
	second_name varchar(255)
);
create table contact (
	id int auto_increment PRIMARY KEY,
	pesel varchar(11),
	address varchar(255),
	mail varchar(255),
	telephone varchar(15),
	constraint fk_pesel foreign key (pesel) references client(pesel)
);
create table book_order (
	id int auto_increment PRIMARY KEY,
	book_id int,
	pesel varchar(11),
	status int,
	constraint fk_peselek foreign key (pesel) references client(pesel), 
	constraint fk_book foreign key (book_id) references book(id) 
);
create table sl_book_type as 
(
	select 1 AS id, 'SENSATION' AS name from DUAL
	UNION ALL
	select 2 AS id, 'CRIMINAL' AS name from DUAL
	UNION ALL
	select 3 AS id, 'FANTASY' AS name from DUAL
	UNION ALL
	select 4 AS id, 'THRILLER' AS name from DUAL
	UNION ALL
	select 5 AS id, 'HORROR' AS name from DUAL
	UNION ALL
	select 6 AS id, 'NOVEL_OF_MANNERS' AS name from DUAL
	UNION ALL
	select 7 AS id, 'PORADNIKGUIDE' AS name from DUAL
	UNION ALL
	select 8 AS id, 'BIOGRAPHY' AS name from DUAL
	UNION ALL
	select 9 AS id, 'HISTORICAL' AS name from DUAL
	UNION ALL
	select 10 AS id, 'TRAVEL' AS name from DUAL
	UNION ALL
	select 11 AS id, 'ROMANS' AS name from DUAL
	UNION ALL
	select 12 AS id, 'POPULAR_SCIENCE' AS name from DUAL
	UNION ALL
	select 13 AS id, 'YOUTH' AS name from DUAL
	UNION ALL
	select 14 AS id, 'DZIECIĘCA' AS name from DUAL
	UNION ALL
	select 15 AS id, 'REPORTAGE' AS name from DUAL
	UNION ALL
	select 16 AS id, 'HANDBOOK' AS name from DUAL
);

create table sl_order_status as 
(
	select 1 AS id, 'WAITING' AS name from DUAL
	UNION ALL
	select 2 AS id, 'PAID' AS name from DUAL
	UNION ALL
	select 3 AS id, 'SHIPPED' AS name from DUAL
	UNION ALL
	select 4 AS id, 'DELIVERED' AS name from DUAL
);

