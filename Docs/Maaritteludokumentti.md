Aihe: Reitinetsint�

Kieli: Java

K�ytett�v� algoritmi: A*

Tarkoituksena olisi ratkaista reitinetsint� ongelma, jossa tavoitteena l�yt�� mahdollisimman nopeasti mahdollisimman lyhyt reitti l�ht�pisteest� maaliin. T�h�n tarkoitukseen valitsin A*-algoritmin koska se on hyvin tehokas ja tunnettu reitinetsint� algoritmi.

Tietorakenteet: Keko; taulukko  tai verkko, joka sy�tteen�;

Sy�te:	Sy�tteen� n ulotteinen taulukko alueesta jonka l�pi reitti tulee etsi� sek� l�ht� ja maali alkiot.
	Reitti etsit��n k�ytt�en taulukkoa. Reitti kulkee l�ht� alkiosta maali alikoon.

tai
	l�ht� ja maali alkiot alkoioiden verkosta ja heuristiikka esim. toEnd(alkio), toStart(alkio).

Keon tilavaativuus: O(n),miss� on n elementtien m��r�. T�m� on selke�, sill� jokaiseen alkioon liittyy vain vaiko m��r� muuttujia, jotka tarvitaan alkion kekoon liitt�miseen.

Keon operaatioiden tilavaativuus: Keossa k�ytett�v�t operaatiot insert, delete, find-min ja delete-min, joita A* tunnetusti k�ytt��, voidaan tehd� sill� tavalla, ett� niiden tilavaativuus on vakiollinen O(1).

Keon operaatioiden aikavaativuudet: Insert O(log(n)), delete O(log(n)), find-min O(1) ja delete-min O(log(n)) olettaen ett� kyseess� on min-heap, miss� n on keossa olevien elementtien m��r�.

Algoritmin Tavoitteellinen TilaVaativuus: Pahimmassa tapauksessa sama kuin taulukon tai verkon koko eli O(V) , miss� V on kaikki mahdolliset alkiot. Parhaimmassa reitin pituus. Yleisess� tapauksessa l�hemp�n� reitin pituutta kuin taulukon kokoa.

Algoritmin Tavoitteellinen Aikavaativuus: O((|V|+|E|)*log(|V|)) , miss� 'V' on kaikki mahdolliset alkiot.
(V+E)*log(V) kaikkien alkioiden lis��misest� keko tietorakenteeseen, jokaisen yhteyden E kohdalla, joka toimii aikavaativuudella O(log(n)).

L�hteet: http://en.wikipedia.org/wiki/A*_search_algorithm (tarviiko jonkun paremman l�hteen?)