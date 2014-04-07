Polunetsintä (A*)


Ongelma:

	TiraLabrassa aion toteuttaa ohjelman, jossa etsitään optimaalinen (mahdollisimman nopea) reitti läpi ASCII-kentän. Kentässä mahdollisesti myös jollain tavalla painotettuja pisteitä, jotka vaikuttavat reitin valintaan.
	Valitsin aiheen lähinnä oman mielenkiinnon mukaan. Lisäksi ongelma ja sen ratkaisu tuntui jo lähtökohtaisesti helposti ymmärrettävältä ja toteutettavalta, jolloin pääsen keskittymään itse tietorakenteiden ja algoritmien ohjelmoimiseen ja käyttämiseen, eikä aikaa kulu liialti ratkaisun omaksumiseen.


Algoritmit ja tietorakenteet:
	
	Polunetsinnässä käytän otsikon mukaan A* -algoritmia. Reitin ylläpitoon riittävä tietorakenne on kaksiulotteinen taulukko, jonka alkiot kuvaavat maastoa. Jotta kuljettu reitti saadaan selville tarvitaan myös linkitettyä listaa, jonka alkioiksia talletetaan kuljetun polun solmut. Lista on luonnollisesti rakenteeltaan muuttuva niin kauan, kunnes optimaalinen reitti lähdöstä maaliin on löydetty.


Syötteet ja niiden käyttö:

	Syötteenä on tarkoituksena käyttää ASCII -merkeistä koostettua kenttää (kaksiulotteinen taulukko), josta algoritmi etsii itse toteutettuja tietorakenteita käyttäen optimaalisen reitin lähtösolmusta maalisolmuun.


Aika- ja tilavaativuudet:

Aika: O( ( |E|+|V| )log|V| )

Lähteet:

Wikipedia - A*: en.wikipedia.org/wiki/A*_search_algorithm
Tietorakenteet -kurssimateriaali - Helsingin yliopisto, Tietojenkäsittelytieteen laitos: http://www.cs.helsinki.fi/u/floreen/tira2012/tira.pdf
