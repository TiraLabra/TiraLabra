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

* * *

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

* * *

**Algoritmit ja tietorakenteet**

* Ohjelma pitää listaa pelaajan käsistä viiden alkion kokoisen kokonaislukutaulukon avulla. Kädet numeroidaan järjestyksessä ja jokaisen kierroksen jälkeen pelaajan kättä vastaavan alkion arvoa korotetaan yhdellä. Tällöin aika- ja tilavaativuus ovat O(1).

* Haluttaessa tietää pelaajan vähiten pelaama käsi, käydään edellä mainittu viiden alkion taulu läpi ja sen alkion indeksi jolla on pienin arvo on vähiten pelattu käsi. Indeksit noudattavat pelin nimeä (KIVI[=0]-PAPERI[=1]-SAKSET[=2]-LISKO[=3]-SPOCK[=4]). Vähiten pelatun käden etsiminen vie aikaa O(5) eli aikavaativuus on O(1).

* Tekoälyn täytyy selvittää vähiten pelatun käden prosenttiosuus kaikista käsistä. Selvittämällä edellä mainitulla metodilla vähiten pelattu käsi, saadaan samalla myös tietoon käden esiintymien määrä. Statistiikka tarjoaa luokkamuuttujan joka kertoo pelattujen kierrosten määrän. Esiintymien määrän selvitys tapahtuu ajassa O(5) = O(1), luokkamuuttuja O(1) ja laskutoimitus O(1) -> vakioaikaista. 
