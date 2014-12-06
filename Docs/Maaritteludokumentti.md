# Määrittelydokumentti

**Aihe:**
Tietokonetta vastaan pelattava kivi-paperi-sakset peli, jossa voidaan valita lisäksi pelattavaksi myös laajennus kivi-paperi-sakset-spock-lisko. 

Peliä pelataan tekstikäyttöliittymän kautta. Käyttöliittymä tulostaa jokaisen komennon jälkeen valikon, josta näkyy komennot. Pelin tekoäly yrittää voittaa pelaajan muutaman yleisen käyttäytymissäännön ja heuristiikan turvin. 

Ohjelmointikielenä käytetään Javaa. 

**Käyttäjät:** Pelaaja, Peli

**Pelaajan toiminnot:**

* Valitse pelimoodi
* Valitse olio (kivi/paperi/sakset/(spock/lisko))
* Pelaa kierros
* Kysy statistiikkaa

**Pelin toiminnot:**

* Arvo olio (kivi/paperi/sakset/(spock/lisko))

* * *

**Komentokehoitteen syöte ja tuloste:**

* Ohjelman käynnistys
Ohjelma kysyy käynnistyessään pelin moodia. Moodeina ovat perinteinen peli ja laajennettu peli, jossa lisähahmoina ovat lisko ja spock.

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

* * *

**Käytetävät tietorakenteet ja algoritmit**

* Ohjelma tallentaa pelatut kädet linkitettyyn listaan jonka koko on etukäteen määritelty. Lista toimii pinon mukaisesti siten, että viimeisimmän kierroksen käsiparit tallennetaan listan alkuun. Mikäli listan koko saavuttaa ennalta määritellyn koon, lisäyksen yhteydessä listan vanhin (=viimeinen) olio poistetaan listalta. Lista pääsee käsiksi ensimmäiseen solmuun ja sisältää tiedon listan nykyisestä koosta mikäli enimmäiskokoa ei ole saavutettu. Listalle lisääminen aiheuttaa vakiomäärän operaatioita, joten lisäys on vakioaikainen operaatio (aikavaatimus O(1), tilavaatimus O(1)). Mikäli listan lopussa oleva alkio tulee poistaa listalta (enimmäiskoko saavutettu) liikutaan listalla ensimmäisestä alkiosta viimeiseen alkioon ja hyppyjä tulee listan ennalta määrätyn koon verran. Koska lista koko on suhteellisen pieni (muutamia kymmeniä) on operaatio myös aika- ja tilavaativuudeltaa vakio (O(1)).

* Selvitettäessä em. listan avulla pelaajan todennäköisintä kättä, käytetään apurakenteena viiden alkion kokonaislukutaulukkoa (int[5]). Pelissä pelattavia käsiä kuvataan koodissa numeroilla 0-4. Apurakenteen rakentaminen ja läpikäyminen on suoraviivaista koska ennalta määrättyjen alkioiden (5kpl) arvoa muutetaan tai luetaan. Aika- ja tilavaativuudet ovat pahimmassa tapauksessa O(5), eli siis vakioaikaisia ja -tilaisia (O(1))
