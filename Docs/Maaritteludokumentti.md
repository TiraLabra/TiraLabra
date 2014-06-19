Polunetsintä (A*)


Ongelma:

	TiraLabrassa toteutan ohjelman, jolla etsitään optimaalinen (mahdollisimman lyhyt) reitti läpi ASCII-kentän. 
	Kentässä voi myös olla asteikolla 1-9 painotettuja pisteitä (kustannus), jotka vaikuttavat reitin valintaan.
	Valitsin aiheen lähinnä oman mielenkiinnon mukaan. Lisäksi ongelma ja sen ratkaisu tuntui jo lähtökohtaisesti 
	helposti ymmärrettävältä ja toteutettavalta, jolloin pääsen keskittymään itse tietorakenteiden ja algoritmien 
	ohjelmoimiseen ja käyttämiseen, eikä aikaa kulu liialti ratkaisun omaksumiseen.


Algoritmit ja tietorakenteet:
	
	Polunetsinnässä käytän otsikon mukaan A* -algoritmia. Heuristiikassa lasketaan tämänhetkiseen noodiin kuljettu 
	matka lisättynä maalinoodiin oleva matka "linnuntienä" sekä mahdollinen noodin kustanus. 
	
	Noodi lisätään minimikekoon, jolloin itse algoritmin ei tarvitse huolehtia noodien prioriteeteista vaan seuraava
	noodi (korkein prioriteetti) saadaan suoraan ottamalla keon päällimmäinen. Minimikeko on tehokas tietorakenne, 
	joka yksinkertaistaa ohjelman eri osien toteutusta.
	


Syötteet ja niiden käyttö:

	Syötteenä ohjelmalle annetaan ASCII -kartta tekstitiedostossa, jonka tulee noudattaa tiettyjä esiehtoja. Esiehdot
	ja mallikartta on saatavilla käyttöohjeessa.
	
Aikavaativuus:
	A*: O(|E|) = O(b^d) (Wikipedia)

Lähteet:

	Wikipedia - A*: en.wikipedia.org/wiki/A*_search_algorithm