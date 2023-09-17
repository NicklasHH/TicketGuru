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

   - Että jokaisella lipulla on oma uniikki tunnuksensa
   - Että jokainen myyty lippu merkitään (järjestelmässä) myydyksi
   - Pystyä hallitsemaan käyttäjätilejä
   - Pystyä hallitsemaan käyttäjätilien oikeuksia (permissions)
   - Mahdollisuuden tarkastaa lippujen myynti- ja varastotilanteen
   - Pystyä tarkastelemaan ja hallitsemaan tapahtumien tietoja ja kategorioita

2. **Lipunmyyjänä haluan...**

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

3. **Lipuntarkastajana haluan...**

   - Pystyä tarkastamaan lipun yksilöllisen tunnistenumeron (ovella)
   - Pystyä merkitsemään järjestelmään tarkastamani lipun käytetyksi

4. **Tapahtumanjärjestäjänä haluan...**

   - Asettaa lippuja myytäväksi järjestämiini tapahtumiin
   - Ilmoittaa lipunmyyjälle tapahtumapaikan ja -ajan
   - Ilmoittaa lipunmyyjälle tapahtumaan myytävien lippujen määrän
   - Seurata myyntiä

5. **Asiakkaana haluan...**
   - Ostaa lippuja itseäni kiinnostaviin tapahtumiin
     -Saatavilla olevat maksuvaihtoehdot, kuten luottokortin, mobiilimaksun tai laskun
   - Tietoa itseäni kiinnostavista tapahtumista (missä ja milloin)
   - Pitää lipunmyyntipisteen tietoisena, että verkkokaupalle olisi kysyntää

### Käyttäjäroolit

1. Käyttöjärjestelmän ylläpitäjä (admin, tekee järjestelmään muutokset/päivitykset)
2. Lipunmyyjä (käyttää järjestelmää)
3. Lipuntarkastaja (käyttää järjestelmää lippujen tarkastamiseen)
4. Tapahtumanjärjestäjä (antaa lipunmyyjälle tiedot uusista tapahtumista, saa myyntiraportit tarvittaessa lipunmyyjältä)
5. Asiakas (ostaa lippuja lipunmyyjältä)

---

## Käyttöliittymä

Käyttöliittymään kirjautuessa on olennaista kirjautuuko järjestelmään lipunmyyjänä vai lipuntarkastajana. Admin-oikeuksia käytetään järjestelmän kehittämiseen ja muutosten tekoon.

![](https://github.com/NicklasHH/TicketGuru/blob/master/TicketGuruN%C3%A4ytt%C3%B6kuva.png)
Voit avata kuvan isommaksi klikkaamalla sitä

---

## Tietokanta

Järjestelmään säilöttävä ja siinä käsiteltävät tiedot ja niiden väliset suhteet
kuvataan käsitekaaviolla. Käsitemalliin sisältyy myös taulujen välisten viiteyhteyksien ja avainten
määritykset. Tietokanta kuvataan käyttäen jotain kuvausmenetelmää, joko ER-kaaviota ja UML-luokkakaaviota.
![image](https://github.com/NicklasHH/TicketGuru/assets/117033936/3bc18d8d-1f3a-4d97-a931-61a100e671a6)

---

## Taulut, taulujen attribuutit ja näiden selitys

### AppUsers

_Kuvaus_
| Kenttä    | Tyyppi      | Kuvaus           |
|-----------|-------------|------------------|
| appUserid | Long PK     | Käyttäjän id     |
| username  | varchar(30) | Tilin nimimerkki |
| password  | String      | Tilin salasana   |
| roleId    | int FK      | Käyttäjän rooli  |

---

### Roles

_Kuvaus_
| Kenttä   | Tyyppi  | Kuvaus                                 |
|----------|---------|----------------------------------------|
| roleId   | Long PK | @Id, @GeneratedValue, not null, unique |
| roleName | String  | not null                               |

---

### TicketTypes

_TicketTypes-taulu sisältää eri lipputyypit. Yksi lippu sisältää vain yhdenlaisen lipputyypin._
| Kenttä       | Tyyppi      | Kuvaus                                      |
|--------------|-------------|---------------------------------------------|
| ticketTypeId | Long PK     | Lipputyypin yksilöllinen tunniste, not null |
| ticketType   | varchar(50) | Lipun tyyppi, not null                      |
| price        | varchar(10) | Lipputyypin hinta, not null                 |

---

### Tickets

_Tickets-taulu sisältää lipun tiedot. Yksi lippu sisältää yhden lipputyypin sekä yhden tapahtuman._
| Kenttä       | Tyyppi  | Kuvaus                                 |
|--------------|---------|----------------------------------------|
| ticketId     | Long PK | Lipun yksilöllinen tunniste, not null  |
| eventId      | int FK  | Viittaus Events-tauluun, not null      |
| ticketTypeId | int FK  | Viittaus TicketTypes-tauluun, not null |

---

### Events

_Events-taulu sisältää tapahtuman tiedot._
| Kenttä      | Tyyppi       | Kuvaus                                        |
|-------------|--------------|-----------------------------------------------|
| eventId     | Long PK      | Tapahtuman id                                 |
| eventName   | varchar(100) | Tapahtuman nimi                               |
| date        | String       | Tapahtuman päivämäärä                         |
| ticketCount | int          | Tapahtuman lippujen määrä                     |
| venueId     | int FK       | Tapahtuman paikan id, viittaus Venues-tauluun |
| description | varchar(500) | Tapahtuman kuvaus                             |

---

### Venues

| Kenttä        | Tyyppi       | Kuvaus                                                       |
|---------------|--------------|--------------------------------------------------------------|
| venueId       | long PK      | Yksilöllinen tunniste, tapahtumapaikka, not null             |
| place         | varchar(150) | Tapahtumapaikan nimi, not null                               |
| streetAddress | varchar(150) | Tapahtumapaikan katuosoite, not null                         |
| postalCode    | int FK       | Viittaus postinroon, postalCode PostCodes-taulussa, not null |
| cityId        | int FK       | Viittaus kaupunkiin, cityID Cities-taulussa, not null        |

---

### Postcodes

_Kuvaus_
| Kenttä | Tyyppi | Kuvaus |
|--------|--------|--------|
| A      | B      | C      |
| A      | B      | C      |

---

### Cities

_Kuvaus_
| Kenttä | Tyyppi | Kuvaus |
|--------|--------|--------|
| A      | B      | C      |
| A      | B      | C      |

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
