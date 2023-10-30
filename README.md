# TicketGuru

Ohjelmistoprojekti 1 kurssin tiimityö

99-Errors: Akseli Leskinen, Nadja Liljeström, Vilma Parikainen, Roope Salonen, Nicklas Åkerman

---

## Johdanto

Projektin tavoitteena on toteuttaa TicketGuru -niminen lipunmyyntijärjestelmä. Asiakkaana on lipputoimisto, joka on tilannut lipunmyyntijärjestelmän lippujen myymiseen myyntipisteessään. Myyntiin tullaan laittamaan eri lipputyyppejä.

Järjestelmässä tulee pystyä myymään ja tulostamaan lippuja sekä hallitsemaan (eli lisäämään, poistamaan, muokkaamaan) tapahtumia, joihin lippuja on myynnissä. Järjestelmästä tulee pystyä katsomaan myyntiraportit ja yksittäiset myyntitapahtumat kunkin tapahtuman osalta. Järjestelmästä tulee pystyä tarkistamaan yksittäisen lipun tunnistenumero.

Järjestelmän kehittämisessä käytetään Spring Bootia, ohjelmointikielenä lähtökohtaisesti Javaa ja tietokantajärjestelmänä käytetään HeidiSQL:ää. Käyttöliittymä kehitetään desktop-käyttöön. Web-puoli kehitetään alustavasti Thymeleafia ja Bootstrapia hyödyntäen.

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

![Näyttömallinnukset](https://github.com/NicklasHH/TicketGuru/blob/master/TicketGuruN%C3%A4ytt%C3%B6kuva.png)
Voit avata kuvan isommaksi klikkaamalla sitä

---

## Tietokanta

Järjstelmän tiedot on kuvattu alla olevassa kaaviossa. Kaavio pitää sisällään taulujen nimet, näiden väliset suhteet, pää- ja viiteavaimet, sekä taulujen tietoelementit. Alempana on esitetty myös taulujen tarkemmat selitykset ja perustelut.

![Tietokannan kaavio](https://github.com/NicklasHH/TicketGuru/blob/master/TiecketGuruTietokantakaavio.png)

---

## Taulut, taulujen attribuutit ja näiden selitys

### AppUsers

_AppUsers-taulu sisältää käyttäjätunnukset, joita vaaditaan järjestelmän käyttämiseen._
| Kenttä    | Tyyppi      | Kuvaus                                   |
|-----------|-------------|------------------------------------------|
| appUserid | Long PK     | Käyttäjän yksilöllinen tunnite, not null |
| username  | varchar(25) | Tilin nimimerkki, not null               |
| password  | varchar(50) | Tilin salasana, not null                 |
| roleId    | int FK      | Viittaus Roles-tauluun, not null         |

---

### Roles

_Roles-taulu sisältää käyttäjätunnuksien roolit. Yhdellä käyttäjätunnuksella on yksi rooli._
| Kenttä   | Tyyppi      | Kuvaus                                 |
|----------|-------------|----------------------------------------|
| roleId   | Long PK     | Roolin yksilöllinen tunniste, not null |
| roleName | varchar(50) | Roolin nimi, not null                  |

---

### TicketTypes

_TicketTypes-taulu sisältää lipputyypit. Yhdellä lipulla on yksi lipputyyppi._
| Kenttä       | Tyyppi      | Kuvaus                                      |
|--------------|-------------|---------------------------------------------|
| ticketTypeId | Long PK     | Lipputyypin yksilöllinen tunniste, not null |
| ticketType   | varchar(50) | Lipun tyyppi, not null                      |
| eventId      | int FK      | Viittaus Events-tauluun, not null           |
| price        | double      | Lipputyypin hinta, not null                 |

---

### Tickets

_Tickets-taulu sisältää lipun tiedot. Yksi lippu sisältää yhden tapahtuman ja yhden lipputyypin tiedot._
| Kenttä        | Tyyppi  | Kuvaus                                              |
|---------------|---------|-----------------------------------------------------|
| ticketId      | Long PK | Lipun yksilöllinen tunniste, not null               |
| ticketTypeId  | int FK  | Viittaus TicketTypes-tauluun, not null              |
| eventId       | int FK  | Viittaus Events-tauluun, not null                   |
| transactionId | int FK  | Viittaus transactions-tauluun, not null             |
| isChecked     | Boolean | Onko lippu tarkistettu, true = tarkistettu not null |
---

### Events

_Events-taulu sisältää tapahtuman tiedot._
| Kenttä      | Tyyppi       | Kuvaus                                                  |
|-------------|--------------|---------------------------------------------------------|
| eventId     | Long PK      | Tapahtuman yksilöllinen tunniste, not null              |
| eventName   | varchar(100) | Tapahtuman nimi, not null                               |
| eventDate   | String       | Tapahtuman päivämäärä, not null                         |
| eventTime   | String       | Tapahtuman kellonaika, not null                         |
| ticketCount | int          | Tapahtuman lippujen määrä, not null                     |
| venueId     | int FK       | Tapahtuman paikan id, viittaus Venues-tauluun, not null |
| description | varchar(500) | Tapahtuman kuvaus                                       |

---

### Venues

_Venues-taulu sisältää tapahtumapaikat. Yksi tapahtuma voi olla vain yhdessä tapahtumapaikassa._

| Kenttä        | Tyyppi       | Kuvaus                                                       |
|---------------|--------------|--------------------------------------------------------------|
| venueId       | long PK      | Yksilöllinen tunniste, tapahtumapaikka, not null             |
| place         | varchar(150) | Tapahtumapaikan nimi, not null                               |
| streetAddress | varchar(150) | Tapahtumapaikan katuosoite, not null                         |
| postalcode    | int FK       | Viittaus postinroon, postalCode PostCodes-taulussa, not null |

---

### Postcodes

_Postcodes-taulu sisältää postinumerot, ja niihin linkittyvät postitoimipaikat. Yksi tapahtumapaikka sisältää vain yhden postinumeron._
| Kenttä     | Tyyppi       | Kuvaus                                                 |
|------------|--------------|--------------------------------------------------------|
| postalcode | varchar(5)   | Postinumero toimii yksilöllisenä tunnisteena, not null |
| postOffice | varchar(150) | Postitoimipaikka, not null                             |

---

### Transactions

_Transactions-taulu sisältää maksutapahtumat._
| Kenttä          | Tyyppi  | Kuvaus                                                   |
|-----------------|---------|----------------------------------------------------------|
| transactionId   | long PK | transactionid toimii yksilöllisenä tunnisteena, not null |
| amount          | Double  | maksutapahtuman summa, not null                          |
| transactionDate | String  | maksutapahtuman päivämäärä, not null, yyyy-mm-dd         |
| transactionTime | String  | maksutapahtuman kellonaika, not null, hh:mm:ss           |

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
