Toteutusdokumentti

* Ohjelman yleisrakenne
* Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)
* Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen)
* Työn mahdolliset puutteet ja parannusehdotukset
* Lähteet


Ohjelman yleisrakenne

Ohjelma on jaettu neljään pakettiin: 
1) haku: A*-haku ja sen apuluokat
2) tira: Itse toteutetut tietorakenteet
3) verkko: Verkko ja sen apuluokat: kaari, solmu ja linja
	- verkko.esimerkki: Pysäkkiverkon lukeminen JSON-datasta
	- verkko.rajapinnat: rajapinnat verkoille
	- verkko.satunnainen: Satunnaisgeneroitu verkko
4) com.mycompany.tiralabra_maven: Käynnistysmetodi ja debug-metodeja App-luokassa

Saavutetut aika- ja tilavaativuudet

Omat tietorakenteet
Käytännössä kaikki toteutetut tietorakenteet toimivat kuten Javan vastaavat aikavaativuudeltaan. (24.11)

-PrioriteettijonoListalla
Käyttämällä tässä erikoistunutta prioriteettijonoa (http://en.wikipedia.org/wiki/Priority_queue#Specialized_heaps)ohjelman kannalta tarpeelliset toiminnot ovat nyt mahdollisimman tehokkaita:
* Lisäys O(1) jos samalla prioriteetillä on jo arvoja. Jos joudutaan luomaan uusi jono prioriteetille tai kasvattamaan jonon kokoa, on vaatimus O(k) jossa k on jonon koko.
* Seuraavan arvon lukeminen (poll) O(1) jos samalla prioriteetilla on arvoja. Jos siirrytään prioriteetista p1 prioriteettiin p2, on aikavaatimus O(p2-p1)
Tilavaatimus on vähimmillään oletuskoon kokoinen taulukko. Parhaimmillaan n-kokoinen prioriteettijono vaatii tilaa O(n): arvot n kappaletta on jaettu prioriteetin mukaan jonoihin (k kpl), joiden kokojen summa on n.

-Jono
Jonossa n alkiota, k jonon maksimikoko
* dequeue, enqueue, peek, poll O(1), jos jonoa joudutaan kasvattamaan O(kasvatuskerroin*k)
* contains O(n)

-Hajautustaulu, Hajautuslista
Taulukon koko on k, arvojen lukumäärä n.
* Lisäys, haku O(1+avaimen törmäykset). Törmäykset saattavat hidastaa toimintaa, joten joidenkin lisäysten jälkeen saattaa tarvita uudelleenhajautusta: aikavaatimus sille on O(uusi koko+n).
* Arvojen läpikäynti O(k+törmäykset).
Tilavaatimus O(k+törmäykset). 

-Pari
Hajautustaulussa käytettävät Avain-Arvo -parit. Lisäksi Pari-luokkaan on toteutettu yhteen suuntaan linkitetty lista. Pari-olioilla saadaan aikaan hajautustaulun käyttämät törmäyslistat: saman hashKey-arvon saavat avaimet talletetaan taulun samaan indeksiin listan jatkoksi.
Törmäyslistan pituus n.
* Lisäys O(n), korvaus, poisto, sisältääkö, haku  max O(n).
* Tilavaatimus n.



-Listat... 

Verkko... & Kaari, Solmu, (Linja)

-Verkko (pysäkkiverkko)

-SatunnainenVerkko

Haku

Toteutuksessani ReittiLaskin-oliot vastaavat verkossa kuljetun reitin (Reitti-oliot) kustannuksen ja arvioidun jäljellä olevan kustannuksen laskemisesta. Käyttämällä erilaisia laskimia voidaan AStar-hausta tehdä myös leveyssuuntainen haku.

-Aikavaatimus
* todo

-Tilavaatimus
* todo

