Aihe: Reitinetsintä

Kieli: Java

Käytettävä algoritmi: A*

Tarkoituksena olisi ratkaista reitinetsintä ongelma, jossa tavoitteena löytää mahdollisimman nopeasti mahdollisimman lyhyt reitti lähtöpisteestä maaliin. Tähän tarkoitukseen valitsin A*-algoritmin koska se on hyvin tehokas ja tunnettu reitinetsintä algoritmi.

Tietorakenteet: Keko; taulukko  tai verkko, joka syötteenä;

Syöte:	Syötteenä n ulotteinen taulukko alueesta jonka läpi reitti tulee etsiä sekä lähtö ja maali alkiot.
	Reitti etsitään käyttäen taulukkoa. Reitti kulkee lähtö alkiosta maali alikoon.

tai
	lähtö ja maali alkiot alkoioiden verkosta ja heuristiikka esim. toEnd(alkio), toStart(alkio).

Keon tilavaativuus: O(n),missä on n elementtien määrä. Tämä on selkeä, sillä jokaiseen alkioon liittyy vain vaiko määrä muuttujia, jotka tarvitaan alkion kekoon liittämiseen.

Keon operaatioiden tilavaativuus: Keossa käytettävät operaatiot insert, delete, find-min ja delete-min, joita A* tunnetusti käyttää, voidaan tehdä sillä tavalla, että niiden tilavaativuus on vakiollinen O(1).

Keon operaatioiden aikavaativuudet: Insert O(log(n)), delete O(log(n)), find-min O(1) ja delete-min O(log(n)) olettaen että kyseessä on min-heap, missä n on keossa olevien elementtien määrä.

Algoritmin Tavoitteellinen TilaVaativuus: Pahimmassa tapauksessa sama kuin taulukon tai verkon koko eli O(V) , missä V on kaikki mahdolliset alkiot. Parhaimmassa reitin pituus. Yleisessä tapauksessa lähempänä reitin pituutta kuin taulukon kokoa.

Algoritmin Tavoitteellinen Aikavaativuus: O((|V|+|E|)*log(|V|)) , missä 'V' on kaikki mahdolliset alkiot.
(V+E)*log(V) kaikkien alkioiden lisäämisestä keko tietorakenteeseen, jokaisen yhteyden E kohdalla, joka toimii aikavaativuudella O(log(n)).

Lähteet: http://en.wikipedia.org/wiki/A*_search_algorithm (tarviiko jonkun paremman lähteen?)