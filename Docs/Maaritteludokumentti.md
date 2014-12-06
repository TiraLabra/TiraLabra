# M��rittelydokumentti

**Aihe:**
Tietokonetta vastaan pelattava kivi-paperi-sakset peli, jossa voidaan valita lis�ksi pelattavaksi my�s laajennus kivi-paperi-sakset-spock-lisko. 

Peli� pelataan tekstik�ytt�liittym�n kautta. K�ytt�liittym� tulostaa jokaisen komennon j�lkeen valikon, josta n�kyy komennot. Pelin teko�ly yritt�� voittaa pelaajan muutaman yleisen k�ytt�ytymiss��nn�n ja heuristiikan turvin. 

Ohjelmointikielen� k�ytet��n Javaa. 

**K�ytt�j�t:** Pelaaja, Peli

**Pelaajan toiminnot:**

* Valitse pelimoodi
* Valitse olio (kivi/paperi/sakset/(spock/lisko))
* Pelaa kierros
* Kysy statistiikkaa

**Pelin toiminnot:**

* Arvo olio (kivi/paperi/sakset/(spock/lisko))

* * *

**Komentokehoitteen sy�te ja tuloste:**

* Ohjelman k�ynnistys
Ohjelma kysyy k�ynnistyess��n pelin moodia. Moodeina ovat perinteinen peli ja laajennettu peli, jossa lis�hahmoina ovat lisko ja spock.

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

**K�ytet�v�t tietorakenteet ja algoritmit**

* Ohjelma tallentaa pelatut k�det linkitettyyn listaan jonka koko on etuk�teen m��ritelty. Lista toimii pinon mukaisesti siten, ett� viimeisimm�n kierroksen k�siparit tallennetaan listan alkuun. Mik�li listan koko saavuttaa ennalta m��ritellyn koon, lis�yksen yhteydess� listan vanhin (=viimeinen) olio poistetaan listalta. Lista p��see k�siksi ensimm�iseen solmuun ja sis�lt�� tiedon listan nykyisest� koosta mik�li enimm�iskokoa ei ole saavutettu. Listalle lis��minen aiheuttaa vakiom��r�n operaatioita, joten lis�ys on vakioaikainen operaatio (aikavaatimus O(1), tilavaatimus O(1)). Mik�li listan lopussa oleva alkio tulee poistaa listalta (enimm�iskoko saavutettu) liikutaan listalla ensimm�isest� alkiosta viimeiseen alkioon ja hyppyj� tulee listan ennalta m��r�tyn koon verran. Koska lista koko on suhteellisen pieni (muutamia kymmeni�) on operaatio my�s aika- ja tilavaativuudeltaa vakio (O(1)).

* Selvitett�ess� em. listan avulla pelaajan todenn�k�isint� k�tt�, k�ytet��n apurakenteena viiden alkion kokonaislukutaulukkoa (int[5]). Peliss� pelattavia k�si� kuvataan koodissa numeroilla 0-4. Apurakenteen rakentaminen ja l�pik�yminen on suoraviivaista koska ennalta m��r�ttyjen alkioiden (5kpl) arvoa muutetaan tai luetaan. Aika- ja tilavaativuudet ovat pahimmassa tapauksessa O(5), eli siis vakioaikaisia ja -tilaisia (O(1))
