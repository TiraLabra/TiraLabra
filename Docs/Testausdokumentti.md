# Testaus

Testaukseen k�ytet��n JUnit-testej� sek� PIT-mutaatiotestausta ongelma-
kohtien l�yt�miseen.

Suorituskykymitauksia.xlsx -tiedostossa on testituloksia eri osa-alueiden
suorituskyvyst� 200 - 24320 solmun tapauksissa. K�ytetyt karttatiedostot
l�ytyv�t Tiralabra_maven-hakemistosta nimill� suorituskykykartta[1-5].txt.

## Astar-algoritmin testaus

Algoritmia testataan antamalla sy�tteeksi ennalta m��ritetty verkko, jonka
lyhimm�n polun voi m��ritt�� kommenttiin kirjoitetusta 2D-esityksest�.

Ensin testataan konstruktori ja solmujen alustuminen muutamalla 
yksinkertaisella testill�.

Kun perusteet on testattu, tehd��n polunetsint� kolmesta verkosta:
1. yksinkertainen verkko, jossa aloitus- ja kohdesolmun v�liss� on sein��
2. aloitus- ja kohdesolmun v�liss� on vaikeakulkuista maastoa, lyhin polku
   ei ole linnuntiet� lyhin, vaan kevein
3. aloitus- ja kohdesolmun v�liss� on sein�� siten, ett� algoritmi haluaa
   menn� suoraan kohti, mutta joutuu huomaamaan, ett� sein� t�ytyy kiert��
   alakautta

## Tietorakenteiden testaus

### Verkko
Testeiss� k�yd��n l�pi solmun lis�ys ja haku. Vieruslistojen luonti 
testataan 2D-esimerkkiverkolla.

### Solmu
Solmusta testataan konstruktori, getterit, setterit ja vierussolmun lis�ys. 
Vierussolmun lis�yksen yhteydess� testataan kaaripainojen haku my�s siin� 
erikoistapauksessa, ett� annettu solmu ei ole t�m�n solmun vieruslistalla.

### Linkitetty lista
Tietorakenteelle ei ole erikseen omia testej�, vaan testaus sis�ltyy muiden
luokkien testeihin. Luokat ja niiden testit tehtiin ensin ArrayList:eill�. 
ArrayList:it korvattiin linkitetyll� listalla ja PIT kertoo, ett� jokainen 
rivi on katettu ja kaikki mutaatiot tapettu.

### Minimikeko
Kekoa testataan ensin luomalla pari kekoa, joista on piirretty puu testin 
kommentteihin. Keosta poistetaan solmuja, joiden pit�isi poistua oikeassa 
j�rjestyksess�.

Erikoistapauksiakin testataan, kuten tapaus, jossa heapify k�sittelee vain 
vasenta lasta, sek� eritt�in todenn�k�inen tilanne, jossa samoja arvoja on 
puussa useampia. 

Yksitt�iset toiminnot testataan erikseen, kuten arvon pienennys ja keon 
alkioiden luku taulukon indeksien avulla. Solmun indeksin palautus testataan
keon yhteydess�.

## Kartanlukija-apuohjelman testaus

Kartanlukijaa testataan samoilla kolmella testill� kuin Astar-algoritmiakin,
mutta verkot on talletettu kartanlukijaTesti(1 - 4).txt -tiedostoihin. N�iden
lis�ksi on yksi testi, jossa tiedostosta luetaan kaikilla numeroarvoilla
varustettu kartta.