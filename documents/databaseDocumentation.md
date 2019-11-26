#### Tietokantataulujen luontilauseet:

create table Lukuvinkki( 
id integer primary key autoincrement,
type varchar(32)
);


create table Book(
lukuvinkki_id integer, 
name varchar(100), 
writer varchar(50), 
description varchar(255), 
comment varchar(255), 
url varchar(200), 
status integer, read datetime, 
foreign key (lukuvinkki_id) 
	references Lukuvinkki(id)
); 

#### Esimerkkejä tietokannan käytöstä

insert into Lukuvinkki (type) values ('book');

insert into Book (lukuvinkki_id, name,writer) values (1, 'Tietokantojen ihanuudesta', 'D. B. Ace');
