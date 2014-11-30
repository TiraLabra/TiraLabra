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



-Listat... 

Verkko... & Kaari, Solmu, (Linja)

-Verkko (pys�kkiverkko)

-SatunnainenVerkko

Haku

Toteutuksessani ReittiLaskin-oliot vastaavat verkossa kuljetun reitin (Reitti-oliot) kustannuksen ja arvioidun j�ljell� olevan kustannuksen laskemisesta. K�ytt�m�ll� erilaisia laskimia voidaan AStar-hausta tehd� my�s leveyssuuntainen haku.

-Aikavaatimus
* todo

-Tilavaatimus
* todo

