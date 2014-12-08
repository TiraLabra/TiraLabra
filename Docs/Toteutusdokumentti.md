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
4) com.mycompany.tiralabra_maven: K�ynnistysmetodi ja debug-metodeja App-luokassa

Saavutetut aika- ja tilavaativuudet

Omat tietorakenteet
K�yt�nn�ss� kaikki toteutetut tietorakenteet toimivat kuten Javan vastaavat aikavaativuudeltaan. (24.11)

-PrioriteettijonoListalla
K�ytt�m�ll� t�ss� erikoistunutta prioriteettijonoa (http://en.wikipedia.org/wiki/Priority_queue#Specialized_heaps)ohjelman kannalta tarpeelliset toiminnot ovat nyt mahdollisimman tehokkaita:
* Lis�ys O(1) jos samalla prioriteetill� on jo arvoja. Jos joudutaan luomaan uusi jono prioriteetille tai kasvattamaan jonon kokoa, on vaatimus O(k) jossa k on jonon koko.
* Seuraavan arvon lukeminen (poll) O(1) jos samalla prioriteetilla on arvoja. Jos siirryt��n prioriteetista p1 prioriteettiin p2, on aikavaatimus O(p2-p1)
Tilavaatimus on v�himmill��n oletuskoon kokoinen taulukko. Parhaimmillaan n-kokoinen prioriteettijono vaatii tilaa O(n): arvot n kappaletta on jaettu prioriteetin mukaan jonoihin (k kpl), joiden kokojen summa on n.

-Jono
Jonossa n alkiota, k jonon maksimikoko
* dequeue, enqueue, peek, poll O(1), jos jonoa joudutaan kasvattamaan O(kasvatuskerroin*k)
* contains O(n)

-Hajautustaulu, Hajautuslista
Taulukon koko on k, arvojen lukum��r� n.
* Lis�ys, haku O(1+avaimen t�rm�ykset). T�rm�ykset saattavat hidastaa toimintaa, joten joidenkin lis�ysten j�lkeen saattaa tarvita uudelleenhajautusta: aikavaatimus sille on O(uusi koko+n).
* Arvojen l�pik�ynti O(k+t�rm�ykset).
Tilavaatimus O(k+t�rm�ykset). 

-Pari
Hajautustaulussa k�ytett�v�t Avain-Arvo -parit. Lis�ksi Pari-luokkaan on toteutettu yhteen suuntaan linkitetty lista. Pari-olioilla saadaan aikaan hajautustaulun k�ytt�m�t t�rm�yslistat: saman hashKey-arvon saavat avaimet talletetaan taulun samaan indeksiin listan jatkoksi.
T�rm�yslistan pituus n.
* Lis�ys O(n), korvaus, poisto, sis�lt��k�, haku  max O(n).
* Tilavaatimus n.



-DynaaminenLista
Tavallinen kokoaan kasvattava taulukko-muotoinen lista. 
* Lis�ys, arvon hakeminen O(1), poisto, sis�lt��k�, koon kasvatus O(n)

Verkko... & Kaari, Solmu, (Linja)

-Verkko (pys�kkiverkko)

-SatunnainenVerkko

Haku

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