## Tietokantaskeema

![tietokantaskeema](http://yuml.me/0ab356e6.jpg "Tietokantaskeema")



#### Tietokantataulujen luontilauseet:

```sql
CREATE TABLE Book (
	book_id INTEGER PRIMARY KEY AUTOINCREMENT,
	title VARCHAR(255) CHECK(LENGTH(title) >= 3),
	author VARCHAR(127),
    description VARCHAR(1023) DEFAULT '',
    comment VARCHAR(1023) DEFAULT '',
	url VARCHAR(255) DEFAULT '',
	status INTEGER CHECK(status IN (0,1)) DEFAULT '0',
	read DATE,
    isbn VARCHAR(20) CHECK(LENGTH(isbn) <= 20) DEFAULT '',
	lukuvinkki_id INTEGER,
	FOREIGN KEY (lukuvinkki_id) REFERENCES Lukuvinkki(id)
	    ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Podcast (
	podcast_id INTEGER PRIMARY KEY AUTOINCREMENT,
    episode_title VARCHAR(255) CHECK(LENGTH(title) >= 3),
    creator VARCHAR(127) DEFAULT '',
    series VARCHAR(255) DEFAULT '',
    description VARCHAR(1023) DEFAULT '',
    comment VARCHAR(1023) DEFAULT '',
    url VARCHAR(255) DEFAULT '',
    status INTEGER CHECK(status IN (0,1)) DEFAULT '0',
    read DATE,
    lukuvinkki_id INTEGER,
    FOREIGN KEY (lukuvinkki_id) REFERENCES Lukuvinkki(id)
    	ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Lukuvinkki (
  id	  INTEGER       PRIMARY KEY AUTOINCREMENT,
  Name    VARCHAR(255)  NOT NULL,
  Type    VARCHAR(127) CHECK( Type IN ('book', 'podcast', 'video')) NOT NULL DEFAULT ('Kirja') REFERENCES LukuvinkkiType(Type)
);
```

