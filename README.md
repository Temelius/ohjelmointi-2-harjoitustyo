# Ohjelmointi 2 Harjoitustyö
Ohjelmointi 2 kurssin harjoitustyö

## Harjoitustyön aihe

Harjoitustyön aiheena on tietokantapohjainen verkkosivusto artistien ja albumien selaamiseksi ja hallinnoimiseksi.

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
