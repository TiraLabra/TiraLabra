# Harjoitustyö: tietorakenteet ja algoritmit

Projektin tarkoituksena on tehdä toimiva go-botti.

## Go
Go on kiinalainen kaksin pelattava lautapeli. Peli tunnetaan myös nimillä wéiqí kiinaksi, igo japaniksi ja baduk koreaksi. Pelin juuret sijoittuvat kiinalaisen mytologian mukaan yli 4 000 vuoden taakse, historiallisesti vähintään 500 eaa. edelle. Säännöt gossa ovat hyvin yksinkertaiset, mutta pelaaminen tarjoaa runsaasti haasteita ja vaatii kaikessa monimuotoisuudessaan runsaasti strategista silmää kuten esimerkiksi länsimaissa tunnetumpi shakki. (Wikipedia)
Go on äärellinen täyden informaation peli, eikä siinä ole lainkaan satunnaisuutta. Hyvin pelaavan go-botin ohjelmoinnista tekee haastavaa go:n korkea haarautumisaste, joka on yli 300. Esimerkiksi shakin haarautumisaste on keskimäärin n. 35. Go:ssa ei myöskään voi helposti arvioida yksittäisen siirron arvoa, koska laudan yleistilanne voi vaikuttaa paikallisesti samanlaisen siirron arvoon.

## Projektin tavoitteet
Projektin aikana toteutetaan botti, joka käyttää Go Text Protocol -protokollaa kommunikointiin serverin tai graafisen käyttöliittymän kanssa.
Koska harjoitustyössä on tarkoitus keskittyä algorimeihin ja tietorakenteisiin, tulee tärkein tavoite olemaan toimiva Monte Carlo -algoritmi, joka käy läpi satunnaisia pelisimulaatioita, ja valitsee niiden perusteella lupaavimman siirron. Tämän lisäksi toteutetaan ainakin Zobrist-hash, jolla laudan tilanteesta saadaan 64-bittinen avain, millä nopeutetaan esimerkiksi jo toistuneen tilanteen tarkastamista.
Koska pääpaino ei ole ohjelman kokonaisvaltaisessa suunnittelussa, käytetään pohjana tohtori Peter Draken Orego -go-engineä, jonka lähdekoodi on julkaistu lisenssillä Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.