# Ohjelmointi 2 Harjoitustyö
Ohjelmointi 2 kurssin harjoitustyö

## Harjoitustyön aihe

Harjoitustyön aiheena on tietokantapohjainen verkkosivusto artistien ja albumien selaamiseksi ja hallinnoimiseksi.

## Harjoitustyön dokumentointi
`db/` hakemistoon voi tallentaa harjoitustyössä käytettyä `Chinook_Sqlite.sqlite` tietokantaa!
(kts. linkki alhaalla.)

### Tietokanta ajuri
Tietokanta ajuri löytyy `database` paketista ja sitä vaaditaan kaikissa JDBC luokissa tietokantayhteyden muodostamiseksi.
Luokka pitää sisällään myös metodin jolla suljetaan kaikki yhteydet.
```
database.Database.java
```

### Artists sivu ja sen käyttämät luokat
`/artists`
Artists sivu näyttää kaikki tietokannassa olevat artistit ja heidän albumien määrän.
Response lähetetään `WEB-INF` hakemistossa sijaitsevalle `index.jsp` tiedostolle.
Sivulta onnistuu myös uuden Artistin lisääminen tietokantaan.
Sivulta on myös mahdollista poistaa Artisti ja poistaessa metodi poistaa myös Artistin albumit.
(`ArtistServlet.java` servletissä)
Artistin haku onnistuu myös täältä ja se lähettää pyynnön `/artists/search` sivulle
Artistia klikkaamalla pääsee `/albums` sivulle
```
model.Artist.java <- model olio luokka
database.ArtistDao.java <- Rajapinta joka määrittelee mitä JDBC luokan on toteutettava
database.JDBCArtistDao.java <- konkreettinen SQL logiikka
servlet.ArtistServlet.java <- hoitaa pyynnöt
WEB-INF/index.jsp <- jsp sivu tietojen näyttämiseksi
scripts/app.js <- hoitaa poistopyynnön lähettämisen, sekä poistaa selaimesta elementin.
```

### Albums sivu ja sen käyttämät luokat
`/albums?ArtistId=[long id]`
Albums sivu näyttää kaikki annetun artistin albumit.
Response lähetetään `WEB-INF` hakemistossa sijaitsevalle `album.jsp` tiedostolle.
Sivulta voi lisätä artistille uuden albumin tietokantaan ja tämä onnistuu `POST` metodilla.
Yksittäisen albumin voi poistaa Remove napilla.

Olisin kovasti halunnut tehdä myös albumin haku ominaisuuden, mutta valitettavasti aika loppui kesken. Saatan hyvinkin lisätä tämän ominaisuuden myöhemmin.
Dao luokasta löytyy luurankometodi albumien etsimiseen. Sitä ei ole kuitenkaan testattu ja saattaa olla virheellinen.
```
model.Album.java <- model olio luokka
database.AlbumDao.java <- Rajapinta joka määrittelee mitä JDBC luokan on toteutettava
database.JDBCAlbumDao.java <- konkreettinen SQL logiikka
servlet.AlbumServlet.java <- pyyntöjen hoitaminen
WEB-INF/index.jsp <- jsp sivu tietojen näyttämiseksi
scripts/app.js <- hoitaa poistopyynnön lähettämisen, sekä poistaa selaimesta elementin.
```
Albums sivu käyttää myös Artist luokkia esim. Artistin nimen näyttämiseksi jsp sivulla ja artistin tietojen hakemiseen pyyntöjä varten.

### Search ominaisuus 
`/artists/search?q=[String keyword]`
Sivu näyttää hakusanan tulokset. Artistin ID:n ja artistin nimen. Linkkiä painamalla pääsee tarkastelemaan artistin albumeja.
Servletti käyttää Artists dao luokan metodia `searchArtist()` tietojen hakemiseen.
```
servlet.SearchServlet.java <- pyyntöjen hoitaminen
WEB-INF/searchResults.jsp <- jsp sivu tietojen näyttämiseksi
scripts/app.js <- käyttää samaa remove metodia kuin Artistin index.jsp
```

### Tulevaisuuden suunnitelmat sovellukselle
* Albumien ja niiden artistien hakeminen
* Albumin kappaleiden näyttäminen
* Olemassa olevien tietojen muokkausmahdollisuus
* JUnit testien tekeminen
* Logiikan parantaminen ja bugikorjaukset
* Virheiden kestäminen ja niiden ennaltaehkäisy

## Harjoitustyön opppimistavoitteet

Harjoitustyön tarkoituksena on oppia ja syventää osaamista seuraavissa aiheissa:

1. HTTP-pyyntöjen käsittely Javalla (Servletit)
    * *käyttäjältä saadun datan käsittely (HTTP parametrit, lomakkeet)*
    * *datan välittäminen servletiltä käyttöliittymälle*
1. Tietokantojen käyttö Javassa (JDBC)
    * *tietokantakerroksen eriyttäminen muusta logiikasta (DAO, data access object)*
    * *kyselyiden kokoaminen turvallisesti (prepared statements)*
1. Dynaamisten HTML-sivujen toteutus JSP:llä (JavaServer Pages)
    * *sivujen muodostaminen dynaamisesti JSP-teknologialla*
    * *datan käsittely JSP-sivuilla JSTL-kirjaston avulla (JavaServer Pages Standard Tag Library)*

### Harjoitustyön teknisen toteutuksen tavoitteet ovat seuraavat:

* palvelu hyödyntää SQL-tietokantaa
* tietokantaoperaatiot toteutetaan Javalla hyödyntäen JDBC-teknologiaa
* palvelun HTTP-pyyntöjen käsittely toteutetaan servletteillä
* palvelun käyttöliittymäkerros toteutetaan JSP-sivuina JSTL-kirjaston avulla.

## Harjoitustyön vaatimukset

Kaikki vaaditut riippuvuudet löytyvät `pom.xml` tiedostosta.

### Valmis musiikkitietokanta
Harjoitustyössä käytetään valmista [Chinook](https://github.com/lerocha/chinook-database) musiikkitietokantaa.

En pidä tietokantaa repositoriossa mutta sen voi ladata itselle `db/` hakemistoon [SQLite-muodossa tästä](https://github.com/lerocha/chinook-database/raw/master/ChinookDatabase/DataSources/Chinook_Sqlite.sqlite)

#### Tietokannan muut aineistot

* UML-kaavio [(Chinook-tietokannan Wiki)](https://github.com/lerocha/chinook-database/wiki/Chinook-Schema)
* Valmis tietokanta: [Chinook_Sqlite.sqlite](https://github.com/lerocha/chinook-database/raw/master/ChinookDatabase/DataSources/Chinook_Sqlite.sqlite)
* Dokumentaatio: https://github.com/lerocha/chinook-database
* SQL-luontikäskyt: [Chinook_Sqlite.sql](https://raw.githubusercontent.com/lerocha/chinook-database/master/ChinookDatabase/DataSources/Chinook_Sqlite.sql)
* [Tietokannan lisenssi (MIT)](https://github.com/lerocha/chinook-database/blob/master/LICENSE.md)
