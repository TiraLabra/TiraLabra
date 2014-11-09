# Määrittelydokumentti

**Aihe:**
Tietokonetta vastaan pelattava kivi-paperi-sakset peli, jossa voidaan valita lisäksi pelattavaksi myös laajennus kivi-paperi-sakset-spock-lisko. 

Pelaaja voi pelaa peliä joko komentokehoitteesta tai graafisen käyttöliittymän kautta. Peli on mahdollista tallentaa, jolloin sitä voi jatkaa myöhemmin. Tietokone pitää yllä pelin statistiikkaa (voitot/tasapelit). 

Ohjelmointikielenä käytetään Javaa. 

**Käyttäjät:** Pelaaja, Peli

**Pelaajan toiminnot:**

* Aloita uusi peli
* Lataa vanha peli
* Tallenna peli
* Valitse olio (kivi/paperi/sakset/(spock/lisko))
* Pelaa kierros

**Pelin toiminnot:**

* Arvo olio (kivi/paperi/sakset/(spock/lisko))

**Komentokehoitteen syöte ja tuloste:**

* Ohjelman käynnistys
Käynnistyksessä joko tyhjä argumentti tai "plus". Ilman argumenttia peli käynnistyy perinteiseen kivi-paperi-sakset-peliin ja argumentin kanssa lisätään mukaan spock ja lisko. 

* Ohjelma näyttää jatkuvasti syötteiden lyhenteet:
K = Kivi
P = Paperi
S = Sakset
O = Spock
L = Lisko
T = Statistiikka
X = Lopeta

* Käyttäjän syöttäessä muun kuin T tai X kone tulostaa oman valintansa

* Komennolla T kone tulostaa statistiikan

* Komento X lopettaa pelin

* Pelin lataaminen ja tallentaminen implementoidaan graafiseen käyttöliittymään

**Algoritmit ja tietorakenteet**

* Ohjelma pitää yllä pelaajan pelaamia käsiä ja ne talletetaan linkitettyyn listaan. Listaan lisääminen tapahtuu vakioajassa ja listan läpikäyminen vaatii O(n) ajan. Luonnollisesti tilavaatimuksena on O(n). 

* Luokat sisältävät yksittäisiä muuttujia mm. kierrosten, tasapelien ja pelaajan voittojen määrästä. Muuttujat päivitetään kierroksittain, joten niiden tila- ja aikavaatimus on vakio.

* Tekoäly tulee ylläpitämään tilastoa pelaajan vähiten pelaamasta kädestä ja kyseisen käden esiintymistiheydestä. Päivitettäessä em. tietoja tulee käydä läpi pelaajan pelaamat kädet, joten kummankin tehtävän aikavaativuus tulee olemaan O(n). Tilavaativuus on kummallakin vakio, sillä pelaajan pelaamat kädet pidetään yhdessä linkitetyssä listassa, jota käydään läpi. Listan kokoon ei tehdä muutoksia em. tilanteissa ja lista saadaan argumenttina.