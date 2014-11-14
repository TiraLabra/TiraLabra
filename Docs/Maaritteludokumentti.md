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

* * *

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

* * *

**Algoritmit ja tietorakenteet**

* Ohjelma pit�� listaa pelaajan k�sist� viiden alkion kokoisen kokonaislukutaulukon avulla. K�det numeroidaan j�rjestyksess� ja jokaisen kierroksen j�lkeen pelaajan k�tt� vastaavan alkion arvoa korotetaan yhdell�. T�ll�in aika- ja tilavaativuus ovat O(1).

* Haluttaessa tiet�� pelaajan v�hiten pelaama k�si, k�yd��n edell� mainittu viiden alkion taulu l�pi ja sen alkion indeksi jolla on pienin arvo on v�hiten pelattu k�si. Indeksit noudattavat pelin nime� (KIVI[=0]-PAPERI[=1]-SAKSET[=2]-LISKO[=3]-SPOCK[=4]). V�hiten pelatun k�den etsiminen vie aikaa O(5) eli aikavaativuus on O(1).

* Teko�lyn t�ytyy selvitt�� v�hiten pelatun k�den prosenttiosuus kaikista k�sist�. Selvitt�m�ll� edell� mainitulla metodilla v�hiten pelattu k�si, saadaan samalla my�s tietoon k�den esiintymien m��r�. Statistiikka tarjoaa luokkamuuttujan joka kertoo pelattujen kierrosten m��r�n. Esiintymien m��r�n selvitys tapahtuu ajassa O(5) = O(1), luokkamuuttuja O(1) ja laskutoimitus O(1) -> vakioaikaista. 
