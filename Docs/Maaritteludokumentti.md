Tietorakennevertailu

 ======

Työ toteutetaan käyttäen javaa ja Mavenia. Työ ei käytä mitään javaan oeltuksena kuulumattomia kirjastoja.

Työssä toteutettavat tietorakenteet ovat binäärihakupuu, AVL-puu, punamusta puu sekä Splay puu. Puut toteutetaan siten, että ne niiden solmut eivät sisällä mitään ylimääräistä tietoa (mitään mikä ei ole puun toiminnan kannalta oleellista).

Työssä tutkitaan edellä mainittujen tietorankenteiden suorituskykyä erilaisissa tilanteissa ja erilaisia alkiojoukkoja käsitellessä. Tarkoituksena on selvittää mikä tietorankenne soveltuu parhaiten mihinkin tilanteeseen ja mistä erot tietorankenteiden suorituskyvyssä johtuvat. Lisäksi tutkitaan miten tietorankenteiden suorituskykyä voitaisiin parantaa ja miten nämä parannukset vaikuttavat suorituskykyyn.

Työ keskittyy tietorakenteiden haku, etsintä ja poisto -toimintojen tarkasteluun. Tarkoituksena selvittää miten näiden toiminta eroaa ja miten nämä erot heijastuvat niiden suorituskyvyssä.

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
	* http://fi.wikipedia.org/wiki/Splay-puu