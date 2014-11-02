# M��rittelydokumentti

**Aihe:**
Tietokonetta vastaan pelattava kivi-paperi-sakset peli, jossa voidaan valita lis�ksi pelattavaksi my�s laajennus kivi-paperi-sakset-spock-lisko. 

Pelaaja voi pelaa peli� joko komentokehoitteesta tai graafisen k�ytt�liittym�n kautta. Peli on mahdollista tallentaa, jolloin sit� voi jatkaa my�hemmin. Tietokone pit�� yll� pelin statistiikkaa (voitot/tasapelit). 

Ohjelmointikielen� k�ytet��n Javaa. 

**K�ytt�j�t:** Pelaaja, Peli

**Pelaajan toiminnot:**

* Aloita uusi peli
* Lataa vanha peli
* Tallenna peli
* Valitse olio (kivi/paperi/sakset/(spock/lisko))
* Pelaa kierros

**Pelin toiminnot:**

* Arvo olio (kivi/paperi/sakset/(spock/lisko))

**Komentokehoitteen sy�te ja tuloste:**

* Ohjelman k�ynnistys
K�ynnistyksess� joko tyhj� argumentti tai "plus". Ilman argumenttia peli k�ynnistyy perinteiseen kivi-paperi-sakset-peliin ja argumentin kanssa lis�t��n mukaan spock ja lisko. 

* Ohjelma n�ytt�� jatkuvasti sy�tteiden lyhenteet:
K = Kivi
P = Paperi
S = Sakset
O = Spock
L = Lisko
T = Statistiikka
X = Lopeta

* K�ytt�j�n sy�tt�ess� muun kuin T tai X kone tulostaa oman valintansa

* Komennolla T kone tulostaa statistiikan

* Komento X lopettaa pelin

* Pelin lataaminen ja tallentaminen implementoidaan graafiseen k�ytt�liittym��n


