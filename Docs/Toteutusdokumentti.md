Toteutusdokumentti

* Ohjelman yleisrakenne
* Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)
* Suorituskyky- ja O-analyysivertailu (mik�li ty� vertailupainotteinen)
* Ty�n mahdolliset puutteet ja parannusehdotukset
* L�hteet


Ohjelman yleisrakenne

Ohjelma on jaettu nelj��n pakettiin: 
1) haku: A*-haku ja sen apuluokat
2) tira: Itse toteutetut tietorakenteet
3) verkko: Verkko ja sen apuluokat: kaari, solmu ja linja
	- verkko.esimerkki: Pys�kkiverkon lukeminen JSON-datasta
	- verkko.rajapinnat: rajapinnat verkoille
	- verkko.satunnainen: Satunnaisgeneroitu verkko
4) com.mycompany.tiralabra_maven: K�ynnistysmetodi ja debug-metodeja App-luokassa. Graafinen k�ytt�liittym� Gui-luokassa

Rajapinnat

-Verkko (Graph), laskin, reitti (Node):
AStar-haku on toteutettu k�ytt�en verkko- ja laskin-rajapintoja. Laskin vastaa kustannuksen laskemisesta ja heuristisesta arviosta: reitti tallennetaan Node-rajapinnan toteuttavaan olioon. 

-Value, Edge
Verkon solmut ja kaaret. Verkko toteuttaa toiminnot getNaapurit ja getKaaret, joiden avulla haussa kuljetaan solmusta toiseen kaaria pitkin.

-Lista
Lista-rajapinnan toteuttaa DynaaminenLista.

-Funktio
SatunnainenVerkko k�ytt�� rajapinnan toteuttavaa oliota verkon satunnaispainon laskemiseen.

Saavutetut aika- ja tilavaativuudet

Omat tietorakenteet
K�yt�nn�ss� kaikki toteutetut tietorakenteet toimivat kuten Javan vastaavat aikavaativuudeltaan. (24.11)

-PrioriteettijonoListalla
K�ytt�m�ll� t�ss� erikoistunutta prioriteettijonoa (http://en.wikipedia.org/wiki/Priority_queue#Specialized_heaps)ohjelman kannalta tarpeelliset toiminnot ovat nyt mahdollisimman tehokkaita:
* Lis�ys O(1) jos samalla prioriteetill� on jo arvoja. Jos joudutaan luomaan uusi jono prioriteetille tai kasvattamaan jonon kokoa, on vaatimus O(k) jossa k on jonon koko.
* Lis�ys O(n), jos heuristiikka pett��
* Seuraavan arvon lukeminen (poll) O(1) jos samalla prioriteetilla on arvoja. Jos siirryt��n prioriteetista p1 prioriteettiin p2, on aikavaatimus O(p2-p1)
Tilavaatimus on v�himmill��n oletuskoon kokoinen taulukko. Parhaimmillaan n-kokoinen prioriteettijono vaatii tilaa O(n): arvot n kappaletta on jaettu prioriteetin mukaan jonoihin (k kpl), joiden kokojen summa on n.

-Jono
Jonossa n alkiota, k jonon maksimikoko
* dequeue, enqueue, peek, poll O(1), jos jonoa joudutaan kasvattamaan O(kasvatuskerroin*k)
* contains O(n)

-Hajautustaulu, Hajautuslista
Taulukon koko on k, arvojen lukum��r� n.
* Lis�ys, haku O(1+avaimen t�rm�ykset). T�rm�ykset saattavat hidastaa toimintaa, joten joidenkin lis�ysten j�lkeen saattaa tarvita uudelleenhajautusta: aikavaatimus sille on O(n), tilavaatimus O(n).
* Arvojen l�pik�ynti O(k+t�rm�ykset).
Tilavaatimus O(k+t�rm�ykset). 

-Pari
Hajautustaulussa k�ytett�v�t Avain-Arvo -parit. Lis�ksi Pari-luokkaan on toteutettu yhteen suuntaan linkitetty lista. Pari-olioilla saadaan aikaan hajautustaulun k�ytt�m�t t�rm�yslistat: saman hashKey-arvon saavat avaimet talletetaan taulun samaan indeksiin listan jatkoksi.
T�rm�yslistan pituus n.
* Lis�ys O(n), korvaus, poisto, sis�lt��k�, haku  max O(n).
* Tilavaatimus n.

-Reitti, Polku: Node-rajapinnan toteuttavat
Molemmat on toteutettu yhteen suuntaan linkitettyin� listoina. Uusi verkon solmu lis�t��n aina eteen: aikavaatimus O(1). Tilavaatimus on O(n), jossa n on polun pituus. L�pik�ynti on aikavaativuudeltaan O(n): reitti k��nnet��n toisin p�in.

-DynaaminenLista: Lista-rajapinnan toteuttava
Tavallinen kokoaan kasvattava taulukko-muotoinen lista. 
* Lis�ys, arvon hakeminen O(1), poisto, sis�lt��k�, koon kasvatus O(n)

-Verkko (pys�kkiverkko): Graph-rajapinta
Verkon solmujen (pys�kki) naapurit on tallennettu listaan hajautustauluun. T�m� on kannattavampaa tilans��st�syist�: muutoin tarvittaisiin nxn -taulukko. Nyt tilavaatimuksena on nxk, jossa k on solmun naapurien lukum��r�. Naapurilistan hakeminen on hajautustaulusta aikavaatimukseltaan O(1), jos t�rm�yksi� ei ole. Verkon konstruktorissa hajautustaululle varataan pys�kkien lukum��r�n verran tilaa, joten t�rm�yksi� tuskin on monta.

Koska kyseess� on pys�kkiverkko, on mahdollista ett� pys�kilt� toiselle p��see useammalla kuin yhdell� linjalla. Sen t�hden pys�kin ja naapurin v�lill� olevat linjat on tallennettu listaksi hajautustauluun. T�m�n tilavaatimus on nxkxr, jossa r on kaarien lukum��r�. Listan hakeminen kest�� O(1).

Verkolla on lis�ksi useita apumuuttujia, joilla ei ole haun kannalta merkityst�.

-SatunnainenVerkko: Graph-rajapinta
Satunnaisessa Nxn-verkossa painot generoidaan annetun funktion mukaan. Verkon koko V=Nxn. Vieruslistat on tallennettu 2-ulotteiseen taulukkoon, tilavaatimus on O(V*k), jossa k on solmun naapurien lukum��r�. Haun aikavaatimus on O(1).

Solmuun saapumisen kustannus on sama riippumatta suunnasta: se on solmua vastaavan satunnaisgeneroitu paino. T�ten kahden solmun v�linen kaari voidaan esitt�� Nxn -taulukossa. Naapureihin kulkevat kaaret on tallennettu Nxnxr -taulukkoon, jossa r on kaarien lukum��r�. Kaarien hakemisen aikavaatimus on O(1).

Satunnaisgeneroidulla verkolla on lis�ksi apumuuttujia, joilla ei ole haun kannalta merkityst�.

AStar-haku

Toteutuksessani ReittiLaskin-oliot vastaavat verkossa kuljetun reitin (Reitti-oliot) kustannuksen ja arvioidun j�ljell� olevan kustannuksen laskemisesta. K�ytt�m�ll� erilaisia laskimia voidaan AStar-hausta tehd� my�s leveyssuuntainen haku Dijikstran algoritmin mallisesti. 

Pseudokoodi:
solmu = k�sittelyj�rjestyksess� seuraavan reitin solmu
jos ollaan maalissa, palautetaan reitti
jos solmu on jo k�sitelty, siirryt��n seuraavaan
kaikille solmun naapureille:
	lis�t��n reitti naapuriin k�sittelyj�rjestykseen
lis�t��n solmu k�siteltyihin

Jos jokaisella solmulla on k naapuria, k�sittelyvuorossa olevasta solmusta lis�t��n k�sittelyj�rjestykseen k uutta arvoa. Kukin lis�ys on parhaimmillaan O(1) k�ytt�en omaa prioriteettijonoa. Tarkastus onko solmu jo k�sitelty on parhaimmillaan O(1), samoin solmun lis��minen k�siteltyihin.

Parhaimmillaan haku l�htee suoraan kohti maalia ja k�sitellyksi tulee solmuja reitin pituuden d verran. T�ll�in k�sittelyj�rjestykseen lis�t��n jokaisen k�sitellyn solmun naapurit: yhteens� k*d lis�yst�, joten saadaan O(k*d). K�yt�nn�ss� kuitenkin k�siteltyjen solmujen m��r� n on suurempi kuin reitin pituus. T�ll�in saadaan suoritusaika O(k*n). 

K�sittelyj�rjestys saattaa sis�lt�� saman solmun monta kertaa: jos solmuun p��st��n jokaisesta sen naapurista k kpl, vain nopeimmin sinne saapuneesta jatketaan reitti�. Enimmill��n jokainen solmu n tulee k kertaa k�sitelt�v�ksi, mutta sen naapurit lis�t��n k�sittelyj�rjestykseen vain ensimm�isell� kerralla.

Pahimmassa tilanteessa alkusolmun j�lkeen k�sitell��n sen k naapuria. Jokaisesta naapurista k�sitell��n taas niiden naapurit k*k kappaletta. N�in jatkettaessa saadaan eksponentiaalinen aikavaatimus reitin pituuden suhteen O(k^d). Tilavaatimus t�ss� tilanteessa on k�sittelyj�rjestykselle O(k*n) ja k�sitellyille solmuille O(n).

K�yt�nn�n yl�rajana aikavaatimukselle on O(E), eli verkon kaarien lukum��r�. Tilavaatimuksen yl�raja on O(V): jokaisessa solmussa on k�yty ja ne on tallennettu k�siteltyihin.

Heuristiikka

Jotta heuristiikka toimisi, ei se saa yliarvioida kustannuksia: h(n) <= f(n,m)+h(m) kaikilla n, m. Johtuen haussa k�ytett�v�n prioriteettijonon tyypist�, heuristisen arvion pett�minen saattaa kaataa koko haun.

Ohjelmassa on k�yt�ss� verkon valinnasta riippuen erilaisia laskimia. Laskimen vastuulla on sek� reittien laskeminen, eli kustannus tiettyyn solmuun asti ett� heuristisen arvion laskeminen loppumatkalle. Laskin on toteutettu rajapintana. Laskimen toteutuksessa ReittiLaskin heuristiikan painoja voi vapaasti s��t�� konstruktorilla. Ohjelmassa valittavana on vaihtoehto BFS, joka antaa heuristiikalle arvon nolla. T�ll�in kyseess� on tavallinen Dijikstra-haku. Jos painot eiv�t ole nollia, heuristinen arvio pohjautuu euklidisen et�isyyden d funktiolle.

Satunnaisessa Nxn-verkossa heuristinen arvio valitaan verkon tyypin mukaan. Jos liikkuminen on sallittu vain akselien suuntaan, on heuristiikkana funktio h(a,b) = |a.x-b.x|+|a.y-b.y|. Jos liikkuminen on sallittu my�s sivuttain, on heuristiikan pohjalla funktio h(a,b) = max(|a.x-b.x|,|a.y-b.y|), koska tavallinen euklidinen ei toimisi. My�s satunnaisessa verkossa voi ohjelmassa valita Dijikstra-tyyppisen heuristiikan. 