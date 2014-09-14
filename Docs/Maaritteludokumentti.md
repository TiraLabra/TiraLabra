Tietorakennevertailu

 ======

Työ toteutetaan käyttäen javaa ja Mavenia. Työsä ei alustavasti ole tarkoitus käyttää mitään javaan oletuksena kuulumattomia kirjastoja.

Työssä toteutettavat tietorakenteet alustavasti binäärihakupuu, AVL-puu, punamusta puu sekä Splay puu. Puut toteutetaan alustavasti siten, että ne niiden solmut eivät sisällä mitään ylimääräistä tietoa (mitään mikä ei ole puun toiminnan kannalta oleellista).

Työssä tutkitaan edellä mainittujen tietorankenteiden suorituskykyä erilaisissa tilanteissa ja erilaisia alkiojoukkoja käsitellessä. Tarkoituksena on selvittää mikä tietorankenne soveltuu parhaiten mihinkin tilanteeseen ja mistä erot tietorankenteiden suorituskyvyssä johtuvat. Lisäksi tutkitaan miten tietorankenteiden suorituskykyä voitaisiin parantaa ja miten nämä parannukset vaikuttavat suorituskykyyn.

Työ keskittyy lähinnä kaikkien tietorakenteiden sisältämien haku, etsintä ja poisto -toimintojen tarkasteluun. Monimutkaisemmissa puissa tutkitaan myös niille ominaisten toimintojen suorituskykyä.

Ohjelmalle annetaan syötteenä erilaisia tietojoukkoja, joita tietorakenteet joutuvat sitten käsittelemään. Tulosteena on taulukko, jonka avulla voidaan verrata tietorakenteiden suoritusaikoja näitä joukkoja käsitellessä.
Tarkoituksena on myös antaa tarkempi erittely mihin tietorakenteet käyttävät aikaa ja miten, ja miksi, tietojoukon sisältämät alkiott vaikuttavat suorituskykyyn.

Teoreettiset aika- ja tilavaativuudet haku, poisto ja lisäysoperaatioille:

 --------------------

Hakupuiden suoritusajat ovat suhteessa puun korkeuteen.

Binäärihakupuun operaatiot:
keskimääräinen O( log(n) ), pahin O( n ).

Muut tutkittavat hakupuut:
keskimääräinen O( log(n) ), pahin O( log(n) ).

Tilavaativuus kaikille puille on O(n).

n on puun sisältämien alkioiden määrä.)

lähteet:

 --------------------

	* https://github.com/TiraLabra/Syksy-periodi1-2014/wiki/
	* http://fi.wikipedia.org/wiki/Bin%C3%A4%C3%A4rinen_hakupuu
	* http://fi.wikipedia.org/wiki/AVL-puu
	* http://fi.wikipedia.org/wiki/Punamusta_puu
	* http://fi.wikipedia.org/wiki/Keko_(tietorakenne)
	* http://fi.wikipedia.org/wiki/Splay-puu