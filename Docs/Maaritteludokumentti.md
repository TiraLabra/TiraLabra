Tietorakennevertailu

 ======

Ty� toteutetaan k�ytt�en javaa ja Mavenia. Ty� ei k�yt� mit��n javaan oeltuksena kuulumattomia kirjastoja.

Ty�ss� toteutettavat tietorakenteet ovat bin��rihakupuu, AVL-puu, punamusta puu sek� Splay puu. Puut toteutetaan siten, ett� ne niiden solmut eiv�t sis�ll� mit��n ylim��r�ist� tietoa (mit��n mik� ei ole puun toiminnan kannalta oleellista).

Ty�ss� tutkitaan edell� mainittujen tietorankenteiden suorituskyky� erilaisissa tilanteissa ja erilaisia alkiojoukkoja k�sitelless�. Tarkoituksena on selvitt�� mik� tietorankenne soveltuu parhaiten mihinkin tilanteeseen ja mist� erot tietorankenteiden suorituskyvyss� johtuvat. Lis�ksi tutkitaan miten tietorankenteiden suorituskyky� voitaisiin parantaa ja miten n�m� parannukset vaikuttavat suorituskykyyn.

Ty� keskittyy tietorakenteiden haku, etsint� ja poisto -toimintojen tarkasteluun. Tarkoituksena selvitt�� miten n�iden toiminta eroaa ja miten n�m� erot heijastuvat niiden suorituskyvyss�.

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
	* http://fi.wikipedia.org/wiki/Splay-puu