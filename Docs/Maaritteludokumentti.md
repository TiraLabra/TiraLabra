Tietorakennevertailu

 ======

Ty� toteutetaan k�ytt�en javaa ja Mavenia. Ty�s� ei alustavasti ole tarkoitus k�ytt�� mit��n javaan oletuksena kuulumattomia kirjastoja.

Ty�ss� toteutettavat tietorakenteet alustavasti bin��rihakupuu, AVL-puu, punamusta puu sek� Splay puu. Puut toteutetaan alustavasti siten, ett� ne niiden solmut eiv�t sis�ll� mit��n ylim��r�ist� tietoa (mit��n mik� ei ole puun toiminnan kannalta oleellista).

Ty�ss� tutkitaan edell� mainittujen tietorankenteiden suorituskyky� erilaisissa tilanteissa ja erilaisia alkiojoukkoja k�sitelless�. Tarkoituksena on selvitt�� mik� tietorankenne soveltuu parhaiten mihinkin tilanteeseen ja mist� erot tietorankenteiden suorituskyvyss� johtuvat. Lis�ksi tutkitaan miten tietorankenteiden suorituskyky� voitaisiin parantaa ja miten n�m� parannukset vaikuttavat suorituskykyyn.

Ty� keskittyy l�hinn� kaikkien tietorakenteiden sis�lt�mien haku, etsint� ja poisto -toimintojen tarkasteluun. Monimutkaisemmissa puissa tutkitaan my�s niille ominaisten toimintojen suorituskyky�.

Ohjelmalle annetaan sy�tteen� erilaisia tietojoukkoja, joita tietorakenteet joutuvat sitten k�sittelem��n. Tulosteena on taulukko, jonka avulla voidaan verrata tietorakenteiden suoritusaikoja n�it� joukkoja k�sitelless�.
Tarkoituksena on my�s antaa tarkempi erittely mihin tietorakenteet k�ytt�v�t aikaa ja miten, ja miksi, tietojoukon sis�lt�m�t alkiott vaikuttavat suorituskykyyn.

Teoreettiset aika- ja tilavaativuudet haku, poisto ja lis�ysoperaatioille:

 --------------------

Hakupuiden suoritusajat ovat suhteessa puun korkeuteen.

Bin��rihakupuun operaatiot:
keskim��r�inen O( log(n) ), pahin O( n ).

Muut tutkittavat hakupuut:
keskim��r�inen O( log(n) ), pahin O( log(n) ).

Tilavaativuus kaikille puille on O(n).

n on puun sis�lt�mien alkioiden m��r�.)

l�hteet:

 --------------------

	* https://github.com/TiraLabra/Syksy-periodi1-2014/wiki/
	* http://fi.wikipedia.org/wiki/Bin%C3%A4%C3%A4rinen_hakupuu
	* http://fi.wikipedia.org/wiki/AVL-puu
	* http://fi.wikipedia.org/wiki/Punamusta_puu
	* http://fi.wikipedia.org/wiki/Keko_(tietorakenne)
	* http://fi.wikipedia.org/wiki/Splay-puu