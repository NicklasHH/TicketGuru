# Testausdokumentaatio TickerGuru:lle

## 1. Johdanto
Tämä dokumentti antaa yleiskatsauksen TicketGurun lipunmyyntijärjestelmän testausprosesseista ja tuloksista.

## 2. Testiympäristö
- **Teknologiat ja työkalut**: Spring Boot, JUnit
- **Konfiguraatio**:
  - Spring Boot versio: (3.0.1)
  - Tietokannan konfiguraatio:
```spring.datasource.url=jdbc:mariadb://localhost:3306/ticketguru
spring.datasource.username=root
spring.datasource.password=root```

  - Turvallisuusasetukset: Käytä MockMvc:tä API-testeihin
    
## 3. Testausstrategia
### Yksikkötestit
- Testaa yksittäisiä metodeja ja luokkien toiminnallisuuksia käyttäen JUnitia.
- Esimerkki: Testaa CRUD-toiminnot Roles-luokalle.
### Integraatiotestit
- Testaa API-kutsuja ja vuorovaikutusta tietokannan ja varausjärjestelmän välillä.
- Esimerkki: Testaa lipun hakua ID:n perusteella sekä tunnistamis- ja valtuutustoimintoja.

### Päästä päähän Testit (End-To-End, E2E)
- Simuloi loppukäyttäjän suorittamia skenaarioita, testaten kokonaisia käyttäjäpolkuja.
- Esimerkki: Testaa lippujen tarkistusprosessia, mukaan lukien virheenkäsittely ja onnistunut lipun varmennus.
  
## 4. Testitapausten yksityiskohdat
- **Testitapaus: Onnistunut lippujen osto**
  - **Askeleet**: 
    1. Käyttäjä painaa "Lipun osto" -painiketta.
    2. Käyttäjä valitsee tapahtuman ja valitsee lipputyypin.
    3. Käyttäjä suorittaa maksun.
    4. Järjestelmä generoi lipun.
  - **Odottettu tulos**: Lippu ostetaan onnistuneesti ja toimitetaan käyttäjälle.


## 5. Esimerkkikoodit
```
    @Test
    public void testFindKorvatunturi() throws Exception {
        mockMvc.perform(get("/api/postalcodes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.postalcode == '99999')].postOffice").value("Korvatunturi"));
    }
```

## 6. Testitulokset ja analyysi
- **Tulokset**: Testit osoittivat onnistuneesti, että postinumeron API palauttaa oikein "Korvatunturi" postinumerolle 99999, ja lipun osto toimii odotetusti.
- **Analyysi**: Jos kaikki testit läpäisevät, se tarkoittaa, että postinumero-API toimii oikein ja käyttäjä voi ostaa lipun.
  
## 7. Johtopäätökset ja seuraavat vaiheet
- **Johtopäätökset**: Nykyinen testausvaihe on varmistanut TicketGurun järjestelmän olennaiset toiminnallisuudet, varmistaen, että keskeiset ominaisuudet kuten roolien hallinta ja lipun osto toimivat odotetusti.
- **Suositukset**: Testaa kaikki API-pisteet ja CRUD-luokat.
  
## 8. Liitteet ja Viitteet
**GitHub-repositorio ja lähdekoodi**: 
https://github.com/NicklasHH/TicketGuru
---
