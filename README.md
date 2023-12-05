# TicketGuru

Ohjelmistoprojekti 1 kurssin tiimityö

99-Errors: Akseli Leskinen, Nadja Liljeström, Vilma Parikainen, Roope Salonen, Nicklas Åkerman

---

## Johdanto

Projektin tavoitteena on toteuttaa TicketGuru -niminen lipunmyyntijärjestelmä. Asiakkaana on lipputoimisto, joka on tilannut lipunmyyntijärjestelmän lippujen myymiseen myyntipisteessään. Myyntiin tullaan laittamaan eri lipputyyppejä.

Järjestelmässä tulee pystyä myymään ja tulostamaan lippuja sekä hallitsemaan (eli lisäämään, poistamaan, muokkaamaan) tapahtumia, joihin lippuja on myynnissä. Järjestelmästä tulee pystyä katsomaan myyntiraportit ja yksittäiset myyntitapahtumat kunkin tapahtuman osalta. Järjestelmästä tulee pystyä tarkistamaan yksittäisen lipun tunnistenumero.

Järjestelmän kehittämisessä käytetään Spring Bootia, ohjelmointikielenä lähtökohtaisesti Javaa ja tietokantajärjestelmänä toimii MariaDB. Käyttöliittymä kehitetään desktop-käyttöön. Web-puoli kehitetään alustavasti Thymeleafia ja Bootstrapia hyödyntäen.

---

## Järjestelmän määrittely

### Käyttäjätarinat

1. **Admin-käyttäjänä haluan...**

   - Pystyä hallitsemaan käyttäjätilejä
   - Pystyä hallitsemaan käyttäjätilien oikeuksia (permissions)
   - Pystyä tarkastelemaan ja hallitsemaan tapahtumien tietoja ja kategorioita

2. **Lipunmyyjänä haluan...**

   - Että jokaisella lipulla on oma uniikki tunnuksensa
   - Tarjota lippuja myyntiin eri ikäryhmille
   - Asettaa lippujen ennakkomyynnille aloitus- ja lopetuspäivämäärän
   - Asettaa kullekin tapahtumalle myytävien lippujen määrän
   - Määrittää lippujen hinnoittelua ja alennuksia
   - Pystyä lisäämään myytäviä lippuja järjestelmään
   - Pystyä poistamaan myynnissä olevia lippuja
   - Pystyä muokkaamaan myynnissä olevia lippuja

   - Pystyä luomaan uuden tapahtuman
   - Pystyä asettamaan tapahtumalle päivämäärän
   - Pystyä asettamaan tapahtumalle paikan ja osoitetiedot

   - Pystyä luomaan myyntiraportteja
   - Saada (tulostettua) järjestelmästä myyntiraportin
   - Pystyä tulostamaan kerralla myymättömät liput, kun ennakkomyynti on päättynyt
   - Pystyä tulostamaan myyntihetkellä yksittäisiä lippuja
   - Pystyä tarkastelemaan myyntitapahtumia
   - Että jokainen myyty lippu merkitään (järjestelmässä) myydyksi
   - Mahdollisuuden tarkastaa lippujen myynti- ja varastotilanteen

3. **Lipuntarkastajana haluan...**

   - Että jokaisella lipulla on oma uniikki tunnuksensa
   - Pystyä tarkastamaan lipun yksilöllisen tunnistenumeron (ovella)
   - Pystyä merkitsemään järjestelmään tarkastamani lipun käytetyksi

### Käyttäjäroolit

1. Käyttöjärjestelmän ylläpitäjä (admin, tekee järjestelmään muutokset/päivitykset)
2. Lipunmyyjä (käyttää järjestelmää)
3. Lipuntarkastaja (käyttää järjestelmää lippujen tarkastamiseen)

---

## Käyttöliittymä

Käyttöliittymään kirjautuessa on olennaista kirjautuuko järjestelmään lipunmyyjänä vai lipuntarkastajana. Admin-oikeuksia käytetään järjestelmän kehittämiseen ja muutosten tekoon.

![Näyttömallinnukset](https://github.com/NicklasHH/TicketGuru/blob/master/TicketGuruDocumentation/TicketGuruN%C3%A4ytt%C3%B6kuva.png)
Voit avata kuvan isommaksi klikkaamalla sitä

---

## Tietokanta

Järjstelmän tiedot on kuvattu alla olevassa kaaviossa. Kaavio pitää sisällään taulujen nimet, näiden väliset suhteet, pää- ja viiteavaimet, sekä taulujen tietoelementit. Alempana on esitetty myös taulujen tarkemmat selitykset ja perustelut.

![Tietokannan kaavio](https://github.com/NicklasHH/TicketGuru/blob/master/TicketGuruDocumentation/Tietokantakaavio.V2.png)

---

## Taulut, taulujen attribuutit ja näiden selitys

### AppUsers

##### Taulun nimi: app_users

_AppUsers-taulu sisältää käyttäjätunnukset, joita vaaditaan järjestelmän käyttämiseen._

| Kenttä   | Tyyppi      | Kuvaus                                         |
|----------|-------------|------------------------------------------------|
| user_id  | BIGINT PK   | Käyttäjän yksilöllinen tunnite, AUTO_INCREMENT |
| username | VARCHAR(25) | Tilin nimimerkki, not null                     |
| password | VARCHAR(50) | Tilin salasana, not null                       |
| role_id  | BIGINT FK   | Viittaus Roles-tauluun                         |

---

### Roles

##### Taulun nimi: roles

_Roles-taulu sisältää käyttäjätunnuksien roolit. Yhdellä käyttäjätunnuksella on yksi rooli._
| Kenttä    | Tyyppi      | Kuvaus                                       |
|-----------|-------------|----------------------------------------------|
| role_id   | BIGINT PK   | Roolin yksilöllinen tunniste, AUTO_INCREMENT |
| role_name | varchar(50) | Roolin nimi, not null                        |

---

### TicketTypes

##### Taulun nimi: ticket_types

_TicketTypes-taulu sisältää lipputyypit. Yhdellä lipulla on yksi lipputyyppi._
| Kenttä         | Tyyppi        | Kuvaus                                           |
|----------------|---------------|--------------------------------------------------|
| ticket_type_id | VARCHAR PK    | Lipputyypin yksilöllinen tunniste,AUTO_INCREMENT |
| ticket_type    | VARCHAR(50)   | Lipun tyyppi, not null                           |
| event_id       | BIGINT FK     | Viittaus Events-tauluun                          |
| price          | DECIMAL(10,2) | Lipputyypin hinta, not null                      |

---

### Tickets

##### Taulun nimi: tickets

_Tickets-taulu sisältää lipun tiedot. Yksi lippu sisältää yhden tapahtuman ja yhden lipputyypin tiedot._
| Kenttä         | Tyyppi    | Kuvaus                                      |
|----------------|-----------|---------------------------------------------|
| ticket_id      | BIGINT PK | Lipun yksilöllinen tunniste, AUTO_INCREMENT |
| ticket_type_id | BIGINT FK | Viittaus TicketTypes-tauluun                |
| event_id       | BIGINT FK | Viittaus Events-tauluun                     |
| transaction_id | BIGINT FK | Viittaus transactions-tauluun               |
| is_checked     | BOOLEAN   | Onko lippu tarkistettu, true = tarkistettu  |

---

### Events

##### Taulun nimi: events

_Events-taulu sisältää tapahtuman tiedot._
| Kenttä       | Tyyppi       | Kuvaus                                           |
|--------------|--------------|--------------------------------------------------|
| event_id     | BIGINT PK    | Tapahtuman yksilöllinen tunniste, AUTO_INCREMENT |
| event_name   | VARCHAR(100) | Tapahtuman nimi, not null                        |
| event_date   | DATE         | Tapahtuman päivämäärä, not null                  |
| event_time   | TIME         | Tapahtuman kellonaika, not null                  |
| ticket_count | INT          | Tapahtuman lippujen määrä, not null              |
| venue_id     | BIGINT FK    | Tapahtuman paikan id, viittaus Venues-tauluun    |
| description  | TEXT         | Tapahtuman kuvaus                                |

---

### Venues

##### Taulun nimi: venues

_Venues-taulu sisältää tapahtumapaikat. Yksi tapahtuma voi olla vain yhdessä tapahtumapaikassa._

| Kenttä         | Tyyppi        | Kuvaus                                                 |
|----------------|---------------|--------------------------------------------------------|
| venue_id       | BIGINT PK     | Yksilöllinen tunniste, tapahtumapaikka, AUTO_INCREMENT |
| place          | VARCHAR(150)  | Tapahtumapaikan nimi, not null                         |
| street_address | VARCHAR(150)  | Tapahtumapaikan katuosoite, not null                   |
| postalcode     | VARCHAR(5) FK | Viittaus postinroon, postalCode PostCodes-taulussa     |

---

### Postcodes

##### Taulun nimi: postalcodes

_Postcodes-taulu sisältää postinumerot, ja niihin linkittyvät postitoimipaikat. Yksi tapahtumapaikka sisältää vain yhden postinumeron._
| Kenttä      | Tyyppi        | Kuvaus                                                 |
|-------------|---------------|--------------------------------------------------------|
| postalcode  | VARCHAR(5) PK | Postinumero toimii yksilöllisenä tunnisteena, not null |
| post_office | VARCHAR(150)  | Postitoimipaikka, not null                             |

---

### Transactions

##### Taulun nimi: transactions

_Transactions-taulu sisältää maksutapahtumat._
| Kenttä           | Tyyppi      | Kuvaus                                                          |
|------------------|-------------|-----------------------------------------------------------------|
| transaction_id   | BIGINT PK   | transaction id toimii yksilöllisenä tunnisteena, AUTO_INCREMENT |
| amount           | DECIMAL     | maksutapahtuman summa, not null                                 |
| transaction_ok   | BOOLEAN     | maksutapahtuma on mennyt läpi                                   |
| transaction_date | VARCHAR(10) | maksutapahtuman kellonaika, not null, yyyy-mm-dd                |
| transaction_time | VARCHAR(8)  | maksutapahtuman kellonaika, not null, hh:mm:ss                  |

---

## Tekninen kuvaus  
**Palvelintoteutus**  
Julkaisussa on käytetty hyödyksi Mavenin Jkube lisäosaa, jonka avulla projekti on julkaistu Rahdin palvelimelle.  

Myös MariaDB tietokanta on julkaistu Rahdin palvelimelle ja sitä varten on luotu tietokannan luontiscripti SQL kielellä. [Tietokannan luontilauseet sisältävä kansio](https://github.com/NicklasHH/TicketGuru/tree/master/db)

**Järjestelmän komponentit**  
Järjestelmän komponentit pitävät sisällään kaikkien luokkien GET ja SET metodit,CRUD toiminnot REST API:lle, QR koodin tekemisen lipun tiedoista, yksittäisen lipun tarkistuksen ID perusteella, sekä jäljellä olevien lippujen määrän laskemisen tapahtuman ID perusteella.


**Arkkitehtuuri**  
![Arkkitehtuurin kuvaus](https://github.com/NicklasHH/TicketGuru/blob/master/TicketGuruDocumentation/ArkkitehtuuriKuvaus.png)

**Keskeiset rajapinnat**  
Rajapintoja varten on tarkempi dokumentaatio, mikä sisältää jokaisen käytettävissä olevien endpointtien kuvauksen, esimerkkisisällön sekä muun olennaisen tiedon. [Rajapintojen dokumentaatioon pääset tästä](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/README.md#ticketguru-rest-api)

Endpointeista saatavilla olevat tiedot mukailevat tietokantakaavion sisältöä: [Tietokantakaavioon pääset tästä](https://github.com/NicklasHH/TicketGuru#tietokanta)

**Yleistä tietoa teknisestä osuudesta**
- Ohjelmassa on käytössä endpointtien autentikointi sekä auktorisointi, joka mahdollistaa käyttäjätunnuksen ja salasanan perusteella tietojen näyttämisen.
- Ohjelmakoodi on käyty läpi niin, että nimeämiset ja koodaus sekä kommentointi olisi yhdenmukaista.
- Pääsääntöisesti esimerkiksi REST APIn syötteiden tarkistus tehdään kunkin osion entiteetti sekä service luokassa ja jälkimmäiseen onkin helppo lisätä uusia tarkistuksia.


---

## Testaus

Testauksesta on oma dokumentaatio: https://github.com/NicklasHH/TicketGuru/blob/master/TicketGuruDocumentation/TestiDokumentaatio.md#testausdokumentaatio-tickergurulle

Testausta toteutetaan yksikkötesteillä, integraatiotesteillä sekä päästä päähän(E2E) testeillä. Näitä testejä suoritetaan, kun kyseiseen testattavaan osioon tehdään muutoksia. [Testauksen koodeihin pääset tästä](https://github.com/NicklasHH/TicketGuru/tree/master/src/test/java/Ohjelmistoprojekti/TicketGuru)

**Järjestelmän tunnetut ongelmat:**

- Jkuben skiptests ei toimi, joten testit on kommentoitava ulos Rahtiin julkaistaessa.
- Rahdin julkaisun yhteydessä useimmiten katoaa routen määrittelyt.

---

## Asennustiedot

**Kehitysympäristö:** `Eclipse`  
**Tuotantoympäristö:** `Rahti`  
**Tietokanta:** `MariaDB`  
**Tietokannan luonti:** [https://github.com/NicklasHH/TicketGuru/tree/master/db](https://github.com/NicklasHH/TicketGuru/tree/master/db)  
**Käynnistysasetukset:** [https://github.com/NicklasHH/TicketGuru/tree/master/src/main/resources](https://github.com/NicklasHH/TicketGuru/tree/master/src/main/resources)
<br>
**Lisää Tietokannasta:**  

- Tietokantaa asennettaessa tulee huomioida, että tietokannan käyttäjällä on vaadittavat oikeudet.
- Tietokannan luontia varten on useita eri scriptejä, riippuen mitä halutaan tehdä.(Tyhjät taulut, pelkät tiedot, taulut ja tiedot)
  <br>

**Tuotantoympäristön luonti**  

1. Luo projekti
2. Luo tietokantapalvelu
3. Julkaise Spring Boot projekti
4. Tietokannan konfigurointi

Yksityiskohtaisemmat ohjeet julkaisuun löytyy opettajan kasaamasta materiaalista: https://mruonavaara.github.io/hh-csc-docs/ohje_spring_rahti/#rahti-projektin-luonti  
<br>

**Paikallisen kehitysympäristön käyttöönotto-ohje:**  

1. Asenna eclipse  
2. Lataa tämä projekti GitHubista: `git clone` https://github.com/NicklasHH/TicketGuru  
3. File -> Import -> Excisting maven projects -> etsi ja valitse äsken kloonattu kansio  
4. Varmista oikea profiili käytettävän tietokannan mukaan application.propertiesistä (H2, mariadb)   
5. Käynnistä projekti klikkaamalla hiiren oikealla painikkeella: `TicketGuruApplication.java` ja paina `Run as java application`
6. Avaa selaimen osoiterivillä: http://localhost:8080/

_Sisäänkirjautumistiedot_
| Käyttäjätunnus  | Salasana        |
|-----------------|-----------------|
| admin           | admin           |
| lipunmyyjä      | lipunmyyjä      |
| lipuntarkastaja | lipuntarkastaja |

<br>

**MariaDB tietokannan ohje**  
Mariadb-tietokantaa käytettäessä sinun tarvitsee määritellä `application.properties` tiedostossa `spring.profiles.default=MariaDB`

1. Lataa HeidiSQL ja asenna se
2. Lataa MariaDB ja asenna se: Käyttäjätunnus ja salasana on suotavaa asettaa `root`, Jos et käytä `root` niin määrittele käyttäjätunnus ja salasana tiedostoon `application-MariaDB.properties`
3. Avaa HeidiSQL -> Uusi istunto juurikansiossa -> Määrittele MariaDB käyttäjätunnus ja salasana -> Avaa -> Klikkaa hiiren oikealla äsken luotua istuntoa -> Luo uusi -> Tietokanta -> Avautuneeseen tietokantaan: Luo uusi kysely -> Kopioi tähän projektin juuressa olevan db kansion sisältä `data-mariadb.sql` tiedoston sisältö. -> Suorita SQL kysely.

<br>

**H2 tietokannan ohje**  
H2-tietokantaa käytettäessä sinun tarvitsee määritellä `application.properties` tiedostossa `spring.profiles.default=h2` jonka jälkeen sinulla on H2-tietokanta käytössä. Sovelluksessa on käytössä ominaisuus, jonka avulla tietokantaan rakennetaan automaattisesti tietokanta seuraavalla komennolla: `spring.sql.init.schema-locations=file:db/data-h2.sql`
H2-tietokantaan pääset käsiksi osoitteessa: http://localhost:8080/h2-console/
<br>


---

## Käynnistys- ja käyttöohje  

[**Etusivulle:**](https://ticketguru-ohjelmistoprojekti.rahtiapp.fi/) https://ticketguru-ohjelmistoprojekti.rahtiapp.fi/

[**Endpointtien listaukseen:**](https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/README.md#ticketguru-rest-api) https://github.com/NicklasHH/TicketGuru/blob/master/RestDocumentation/README.md#ticketguru-rest-api
<br>

**Sisäänkirjautumistiedot**
| Käyttäjätunnus  | Salasana        |
|-----------------|-----------------|
| admin           | admin           |
| lipunmyyjä      | lipunmyyjä      |
| lipuntarkastaja | lipuntarkastaja |

---
