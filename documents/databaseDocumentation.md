#### Tietokantataulujen luontilauseet:

```sql
CREATE TABLE Book (
	book_id INTEGER PRIMARY KEY AUTOINCREMENT,
	title VARCHAR(255) CHECK(LENGTH(title) >= 3),
	author VARCHAR(127),
	description VARCHAR(255),
	comment VARCHAR(255),
	url VARCHAR(255),
	status INTEGER CHECK(status IN (0,1)) DEFAULT '0',
	read DATE,
	lukuvinkki_id INTEGER,
	FOREIGN KEY (lukuvinkki_id) REFERENCES Lukuvinkki(id)
	    ON UPDATE CASCADE ON DELETE CASCADE);

CREATE TABLE Lukuvinkki (
  id	  INTEGER       PRIMARY KEY AUTOINCREMENT,
  Name    VARCHAR(255)  NOT NULL,
  Type    VARCHAR(127) CHECK( Type IN ('Kirja', 'Podcast', 'Video')) NOT NULL DEFAULT ('Kirja') REFERENCES LukuvinkkiType(Type)
);
```

#### Esimerkkejä tietokannan käytöstä

Löytyypi BookDao luokan syövereistä
