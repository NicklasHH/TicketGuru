# TicketGuru

Ohjelmistoprojekti 1 kurssin tiimityö

99-Errors: Akseli Leskinen, Nadja Liljeström, Vilma Parikainen, Roope Salonen, Nicklas Åkerman

---

## Johdanto

Projektin tavoitteena on toteuttaa TicketGuru -niminen lipunmyyntijärjestelmä. Asiakkaana on lipputoimisto, joka on tilannut lipunmyyntijärjestelmän lippujen myymiseen myyntipisteessään. Myyntiin tullaan laittamaan eri lipputyyppejä.

Järjestelmässä tulee pystyä myymään ja tulostamaan lippuja sekä hallitsemaan (eli lisäämään, poistamaan, muokkaamaan) tapahtumia, joihin lippuja on myynnissä. Järjestelmästä tulee pystyä katsomaan myyntiraportit ja yksittäiset myyntitapahtumat kunkin tapahtuman osalta. Järjestelmästä tulee pystyä tarkistamaan yksittäisen lipun tunnistenumero.

Järjestelmän kehittämisessä käytetään Spring Bootia, ohjelmointikielenä lähtökohtaisesti Javaa, tietokantatyökaluna käytetään HeidiSQL:ää ja tietokantana MariaDB. Käyttöliittymä kehitetään desktop-käyttöön. Web-puoli kehitetään alustavasti Thymeleafia ja Bootstrapia hyödyntäen.

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

![Näyttömallinnukset](https://github.com/NicklasHH/TicketGuru/blob/master/Dokumentaatio/TicketGuruN%C3%A4ytt%C3%B6kuva.png)
Voit avata kuvan isommaksi klikkaamalla sitä

---

## Tietokanta

Järjstelmän tiedot on kuvattu alla olevassa kaaviossa. Kaavio pitää sisällään taulujen nimet, näiden väliset suhteet, pää- ja viiteavaimet, sekä taulujen tietoelementit. Alempana on esitetty myös taulujen tarkemmat selitykset ja perustelut.

![Tietokannan kaavio](https://github.com/NicklasHH/TicketGuru/blob/master/Dokumentaatio/Tietokantakaavio.V2.png)

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
| Kenttä       | Tyyppi       | Kuvaus                                                  |
|--------------|--------------|---------------------------------------------------------|
| event_id     | BIGINT PK    | Tapahtuman yksilöllinen tunniste, AUTO_INCREMENT        |
| event_name   | VARCHAR(100) | Tapahtuman nimi, not null                               |
| event_date   | DATE         | Tapahtuman päivämäärä, not null                         |
| event_time   | TIME         | Tapahtuman kellonaika, not null                         |
| ticket_count | INT          | Tapahtuman lippujen määrä, not null                     |
| venue_id     | BIGINT FK    | Tapahtuman paikan id, viittaus Venues-tauluun           |
| description  | TEXT         | Tapahtuman kuvaus                                       |

---

### Venues
##### Taulun nimi: venues

_Venues-taulu sisältää tapahtumapaikat. Yksi tapahtuma voi olla vain yhdessä tapahtumapaikassa._

| Kenttä         | Tyyppi        | Kuvaus                                                       |
|----------------|---------------|--------------------------------------------------------------|
| venue_id       | BIGINT PK     | Yksilöllinen tunniste, tapahtumapaikka, AUTO_INCREMENT       |
| place          | VARCHAR(150)  | Tapahtumapaikan nimi, not null                               |
| street_address | VARCHAR(150)  | Tapahtumapaikan katuosoite, not null                         |
| postalcode     | VARCHAR(5) FK | Viittaus postinroon, postalCode PostCodes-taulussa           |

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

<!--
## Tekninen kuvaus

Teknisessä kuvauksessa esitetään järjestelmän toteutuksen suunnittelussa tehdyt tekniset
ratkaisut, esim.

-   Missä mikäkin järjestelmän komponentti ajetaan (tietokone, palvelinohjelma)
    ja komponenttien väliset yhteydet (vaikkapa tähän tyyliin:
    https://security.ufl.edu/it-workers/risk-assessment/creating-an-information-systemdata-flow-diagram/)
-   Palvelintoteutuksen yleiskuvaus: teknologiat, deployment-ratkaisut yms.
-   Keskeisten rajapintojen kuvaukset, esimerkit REST-rajapinta. Tarvittaessa voidaan rajapinnan käyttöä täsmentää
    UML-sekvenssikaavioilla.
-   Toteutuksen yleisiä ratkaisuja, esim. turvallisuus.

Tämän lisäksi

-   ohjelmakoodin tulee olla kommentoitua
-   luokkien, metodien ja muuttujien tulee olla kuvaavasti nimettyjä ja noudattaa
    johdonmukaisia nimeämiskäytäntöjä
-   ohjelmiston pitää olla organisoitu komponentteihin niin, että turhalta toistolta
    vältytään

## Testaus

Tässä kohdin selvitetään, miten ohjelmiston oikea toiminta varmistetaan
testaamalla projektin aikana: millaisia testauksia tehdään ja missä vaiheessa.
Testauksen tarkemmat sisällöt ja testisuoritusten tulosten raportit kirjataan
erillisiin dokumentteihin.

Tänne kirjataan myös lopuksi järjestelmän tunnetut ongelmat, joita ei ole korjattu.

## Asennustiedot

Järjestelmän asennus on syytä dokumentoida kahdesta näkökulmasta:

-   järjestelmän kehitysympäristö: miten järjestelmän kehitysympäristön saisi
    rakennettua johonkin toiseen koneeseen

-   järjestelmän asentaminen tuotantoympäristöön: miten järjestelmän saisi
    asennettua johonkin uuteen ympäristöön.

Asennusohjeesta tulisi ainakin käydä ilmi, miten käytettävä tietokanta ja
käyttäjät tulee ohjelmistoa asentaessa määritellä (käytettävä tietokanta,
käyttäjätunnus, salasana, tietokannan luonti yms.).

## Käynnistys- ja käyttöohje

Tyypillisesti tässä riittää kertoa ohjelman käynnistykseen tarvittava URL sekä
mahdolliset kirjautumiseen tarvittavat tunnukset. Jos järjestelmän
käynnistämiseen tai käyttöön liittyy joitain muita toimenpiteitä tai toimintajärjestykseen liittyviä asioita, nekin kerrotaan tässä yhteydessä.

Usko tai älä, tulet tarvitsemaan tätä itsekin, kun tauon jälkeen palaat
järjestelmän pariin !

-->
