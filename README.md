# TicketGuru
Ohjelmistoprojekti 1 kurssin tiimityö

99-Errors: Akseli Leskinen, Nadja Liljeström, Vilma Parikainen, Roope Salonen, Nicklas Åkerman

## Johdanto

Projektin tavoitteena on toteuttaa TicketGuru -niminen lipunmyyntijärjestelmä. Asiakkaana on lipputoimisto, joka on tilannut lipunmyyntijärjestelmän lippujen myymiseen myyntipisteessään. Myyntiin tullaan laittamaan eri lipputyyppejä.  

Järjestelmässä tulee pystyä myymään ja tulostamaan lippuja sekä hallitsemaan (eli lisäämään, poistamaan, muokkaamaan) tapahtumia, joihin lippuja on myynnissä. Järjestelmästä tulee pystyä katsomaan myyntiraportit ja yksittäiset myyntitapahtumat kunkin tapahtuman osalta. Järjestelmästä tulee pystyä tarkistamaan yksittäisen lipun tunnistenumero.  

Järjestelmän kehittämisessä käytetään Spring Bootia, ohjelmointikielenä lähtökohtaisesti Javaa ja tietokantajärjestelmänä käytetään HeidiSQL:ää. Käyttöliittymä kehitetään desktop-käyttöön. Web-puoli kehitetään alustavasti Thymeleafia ja Bootstrapia hyödyntäen. 

## Järjestelmän määrittely

### Käyttäjätarinat

1. __Viimehetken mieliteko__  
Viljami haluaa kavereidensa mukaan stand-up keikalle, mutta hänellä ei ole lippua ja ennakkomyynti on päättynyt. Viljamin kaverit tietävät, että niitä on saatavilla vielä ovelta, koska tapahtuma ei ollut loppuunmyyty. Viljami lähtee etkoilta vähän ennen muita, jotta ehtii ostaa lipun ovelta ilman kiirettä ja pääsee näin kavereidensa kanssa mukaan keikalle.
<br>

2. __Perheen yhteinen reissu__  
Perhe koostuu neljästä henkilöstä, jotka haluavat päästä messukeskuksessa järjestettävään tapahtumaan. He ostavat kaksi aikuisten ja kaksi lasten lippua myyntipisteen kautta ennakkoon, jolloin he voivat vain kävellä suoraan tapahtumaan sisään, näyttäen ovella lipuista löytyvää koodia.
<br>

3. __Lasse Lipunmyyjä__  
Lipunmyyjänä Lasse lisää TicketGuru-järjestelmään tapahtumia ja niihin myytäviä lippuja. Kun Lasse myy lippuja, järjestelmän lippusaldo päivittyy ja myydyt liput merkitään myydyiksi. Ennakkomyynnin loputtua myymättä jääneet liput tulostetaan ovella myytäväksi, joten Lasse tarvitsee tarkan luvun, monta lippua on myymättä.
<br>

4. __Tanja Tapahtumanjärjestäjä__  
Tapahtumanjärjestäjänä Tanja haluaa myydä tapahtumalippuja TicketGuru-sivustolla, jotta tapahtumat saavuttaisivat mahdollisimman laajan yleisön. Tanja haluaa myyntiraportin myydyistä lipuista, jotta hän voi laskea onko tapahtuman järjestäminen kannattavaa.
<br>

5. __Ennakkomyynti loppuu ja lippuja on jäljellä__  
Ennakkomyynnistä saatiin myytyä noin 50% lipuista, joten lipunmyyjä painaa ennakkomyynnin päätyttyä nappia, mistä tulostuu automaattisesti loput liput, jotka laitetaan ovelle myyntiin.
<br>

### Käyttäjäroolit
1. Lipunmyyjä (käyttää järjestelmää)
2. Asiakas (ostaa lippuja lipunmyyjältä)
3. Käyttöjärjestelmän ylläpitäjä (admin, tekee järjestelmään muutokset/päivitykset)
4. Lipuntarkastaja (käyttää järjestelmää lippujen tarkastamiseen)
5. Tapahtumanjärjestäjä (antaa lipunmyyjälle tiedot uusista tapahtumista, saa myyntiraportit tarvittaessa lipunmyyjältä)

## Käyttöliittymä

Käyttöliittymään kirjautuessa on olennaista kirjautuuko järjestelmään lipunmyyjänä vai lipuntarkastajana. Admin-oikeuksia käytetään järjestelmän kehittämiseen ja muutosten tekoon. 

![Katastrofi ajalta 2023 09 07 13 47 45](https://github.com/NicklasHH/TicketGuru/assets/117033936/b0fca7bb-b075-429f-973a-4c63afdf7641)


## Tietokanta

Järjestelmään säilöttävä ja siinä käsiteltävät tiedot ja niiden väliset suhteet
kuvataan käsitekaaviolla. Käsitemalliin sisältyy myös taulujen välisten viiteyhteyksien ja avainten
määritykset. Tietokanta kuvataan käyttäen jotain kuvausmenetelmää, joko ER-kaaviota ja UML-luokkakaaviota.

Lisäksi kukin järjestelmän tietoelementti ja sen attribuutit kuvataan
tietohakemistossa. Tietohakemisto tarkoittaa yksinkertaisesti vain jokaisen elementin (taulun) ja niiden
attribuuttien (kentät/sarakkeet) listausta ja lyhyttä kuvausta esim. tähän tyyliin:

> ### _Tilit_
> _Tilit-taulu sisältää käyttäjätilit. Käyttäjällä voi olla monta tiliä. Tili kuuluu aina vain yhdelle käyttäjälle._
>
> Kenttä | Tyyppi | Kuvaus
> ------ | ------ | ------
> id | int PK | Tilin id
> nimimerkki | varchar(30) |  Tilin nimimerkki
> avatar | int FK | Tilin avatar, viittaus [avatar](#Avatar)-tauluun
> kayttaja | int FK | Viittaus käyttäjään [käyttäjä](#Kayttaja)-taulussa

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
