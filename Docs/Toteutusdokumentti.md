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
4) com.mycompany.tiralabra_maven: Käynnistysmetodi ja debug-metodeja App-luokassa. Graafinen käyttöliittymä Gui-luokassa

Rajapinnat

-Verkko (Graph), laskin, reitti (Node):
AStar-haku on toteutettu käyttäen verkko- ja laskin-rajapintoja. Laskin vastaa kustannuksen laskemisesta ja heuristisesta arviosta: reitti tallennetaan Node-rajapinnan toteuttavaan olioon. 

-Value, Edge
Verkon solmut ja kaaret. Verkko toteuttaa toiminnot getNaapurit ja getKaaret, joiden avulla haussa kuljetaan solmusta toiseen kaaria pitkin.

-Lista
Lista-rajapinnan toteuttaa DynaaminenLista.

-Funktio
SatunnainenVerkko käyttää rajapinnan toteuttavaa oliota verkon satunnaispainon laskemiseen.

Saavutetut aika- ja tilavaativuudet

Omat tietorakenteet
Käytännössä kaikki toteutetut tietorakenteet toimivat kuten Javan vastaavat aikavaativuudeltaan. (24.11)

-PrioriteettijonoListalla
Käyttämällä tässä erikoistunutta prioriteettijonoa (http://en.wikipedia.org/wiki/Priority_queue#Specialized_heaps)ohjelman kannalta tarpeelliset toiminnot ovat nyt mahdollisimman tehokkaita:
* Lisäys O(1) jos samalla prioriteetillä on jo arvoja. Jos joudutaan luomaan uusi jono prioriteetille tai kasvattamaan jonon kokoa, on vaatimus O(k) jossa k on jonon koko.
* Lisäys O(n), jos heuristiikka pettää
* Seuraavan arvon lukeminen (poll) O(1) jos samalla prioriteetilla on arvoja. Jos siirrytään prioriteetista p1 prioriteettiin p2, on aikavaatimus O(p2-p1)
Tilavaatimus on vähimmillään oletuskoon kokoinen taulukko. Parhaimmillaan n-kokoinen prioriteettijono vaatii tilaa O(n): arvot n kappaletta on jaettu prioriteetin mukaan jonoihin (k kpl), joiden kokojen summa on n.

-Jono
Jonossa n alkiota, k jonon maksimikoko
* dequeue, enqueue, peek, poll O(1), jos jonoa joudutaan kasvattamaan O(kasvatuskerroin*k)
* contains O(n)

-Hajautustaulu, Hajautuslista
Taulukon koko on k, arvojen lukumäärä n.
* Lisäys, haku O(1+avaimen törmäykset). Törmäykset saattavat hidastaa toimintaa, joten joidenkin lisäysten jälkeen saattaa tarvita uudelleenhajautusta: aikavaatimus sille on O(n), tilavaatimus O(n).
* Arvojen läpikäynti O(k+törmäykset).
Tilavaatimus O(k+törmäykset). 

-Pari
Hajautustaulussa käytettävät Avain-Arvo -parit. Lisäksi Pari-luokkaan on toteutettu yhteen suuntaan linkitetty lista. Pari-olioilla saadaan aikaan hajautustaulun käyttämät törmäyslistat: saman hashKey-arvon saavat avaimet talletetaan taulun samaan indeksiin listan jatkoksi.
Törmäyslistan pituus n.
* Lisäys O(n), korvaus, poisto, sisältääkö, haku  max O(n).
* Tilavaatimus n.

-Reitti, Polku: Node-rajapinnan toteuttavat
Molemmat on toteutettu yhteen suuntaan linkitettyinä listoina. Uusi verkon solmu lisätään aina eteen: aikavaatimus O(1). Tilavaatimus on O(n), jossa n on polun pituus. Läpikäynti on aikavaativuudeltaan O(n): reitti käännetään toisin päin.

-DynaaminenLista: Lista-rajapinnan toteuttava
Tavallinen kokoaan kasvattava taulukko-muotoinen lista. 
* Lisäys, arvon hakeminen O(1), poisto, sisältääkö, koon kasvatus O(n)

-Verkko (pysäkkiverkko): Graph-rajapinta
Verkon solmujen (pysäkki) naapurit on tallennettu listaan hajautustauluun. Tämä on kannattavampaa tilansäästösyistä: muutoin tarvittaisiin nxn -taulukko. Nyt tilavaatimuksena on nxk, jossa k on solmun naapurien lukumäärä. Naapurilistan hakeminen on hajautustaulusta aikavaatimukseltaan O(1), jos törmäyksiä ei ole. Verkon konstruktorissa hajautustaululle varataan pysäkkien lukumäärän verran tilaa, joten törmäyksiä tuskin on monta.

Koska kyseessä on pysäkkiverkko, on mahdollista että pysäkiltä toiselle pääsee useammalla kuin yhdellä linjalla. Sen tähden pysäkin ja naapurin välillä olevat linjat on tallennettu listaksi hajautustauluun. Tämän tilavaatimus on nxkxr, jossa r on kaarien lukumäärä. Listan hakeminen kestää O(1).

Verkolla on lisäksi useita apumuuttujia, joilla ei ole haun kannalta merkitystä.

-SatunnainenVerkko: Graph-rajapinta
Satunnaisessa Nxn-verkossa painot generoidaan annetun funktion mukaan. Verkon koko V=Nxn. Vieruslistat on tallennettu 2-ulotteiseen taulukkoon, tilavaatimus on O(V*k), jossa k on solmun naapurien lukumäärä. Haun aikavaatimus on O(1).

Solmuun saapumisen kustannus on sama riippumatta suunnasta: se on solmua vastaavan satunnaisgeneroitu paino. Täten kahden solmun välinen kaari voidaan esittää Nxn -taulukossa. Naapureihin kulkevat kaaret on tallennettu Nxnxr -taulukkoon, jossa r on kaarien lukumäärä. Kaarien hakemisen aikavaatimus on O(1).

Satunnaisgeneroidulla verkolla on lisäksi apumuuttujia, joilla ei ole haun kannalta merkitystä.

AStar-haku

Toteutuksessani ReittiLaskin-oliot vastaavat verkossa kuljetun reitin (Reitti-oliot) kustannuksen ja arvioidun jäljellä olevan kustannuksen laskemisesta. Käyttämällä erilaisia laskimia voidaan AStar-hausta tehdä myös leveyssuuntainen haku Dijikstran algoritmin mallisesti. 

Pseudokoodi:
solmu = käsittelyjärjestyksessä seuraavan reitin solmu
jos ollaan maalissa, palautetaan reitti
jos solmu on jo käsitelty, siirrytään seuraavaan
kaikille solmun naapureille:
	lisätään reitti naapuriin käsittelyjärjestykseen
lisätään solmu käsiteltyihin

Jos jokaisella solmulla on k naapuria, käsittelyvuorossa olevasta solmusta lisätään käsittelyjärjestykseen k uutta arvoa. Kukin lisäys on parhaimmillaan O(1) käyttäen omaa prioriteettijonoa. Tarkastus onko solmu jo käsitelty on parhaimmillaan O(1), samoin solmun lisääminen käsiteltyihin.

Parhaimmillaan haku lähtee suoraan kohti maalia ja käsitellyksi tulee solmuja reitin pituuden d verran. Tällöin käsittelyjärjestykseen lisätään jokaisen käsitellyn solmun naapurit: yhteensä k*d lisäystä, joten saadaan O(k*d). Käytännössä kuitenkin käsiteltyjen solmujen määrä n on suurempi kuin reitin pituus. Tällöin saadaan suoritusaika O(k*n). 

Käsittelyjärjestys saattaa sisältää saman solmun monta kertaa: jos solmuun päästään jokaisesta sen naapurista k kpl, vain nopeimmin sinne saapuneesta jatketaan reittiä. Enimmillään jokainen solmu n tulee k kertaa käsiteltäväksi, mutta sen naapurit lisätään käsittelyjärjestykseen vain ensimmäisellä kerralla.

Pahimmassa tilanteessa alkusolmun jälkeen käsitellään sen k naapuria. Jokaisesta naapurista käsitellään taas niiden naapurit k*k kappaletta. Näin jatkettaessa saadaan eksponentiaalinen aikavaatimus reitin pituuden suhteen O(k^d). Tilavaatimus tässä tilanteessa on käsittelyjärjestykselle O(k*n) ja käsitellyille solmuille O(n).

Käytännön ylärajana aikavaatimukselle on O(E), eli verkon kaarien lukumäärä. Tilavaatimuksen yläraja on O(V): jokaisessa solmussa on käyty ja ne on tallennettu käsiteltyihin.

Heuristiikka

Jotta heuristiikka toimisi, ei se saa yliarvioida kustannuksia: h(n) <= f(n,m)+h(m) kaikilla n, m. Johtuen haussa käytettävän prioriteettijonon tyypistä, heuristisen arvion pettäminen saattaa kaataa koko haun.

Ohjelmassa on käytössä verkon valinnasta riippuen erilaisia laskimia. Laskimen vastuulla on sekä reittien laskeminen, eli kustannus tiettyyn solmuun asti että heuristisen arvion laskeminen loppumatkalle. Laskin on toteutettu rajapintana. Laskimen toteutuksessa ReittiLaskin heuristiikan painoja voi vapaasti säätää konstruktorilla. Ohjelmassa valittavana on vaihtoehto BFS, joka antaa heuristiikalle arvon nolla. Tällöin kyseessä on tavallinen Dijikstra-haku. Jos painot eivät ole nollia, heuristinen arvio pohjautuu euklidisen etäisyyden d funktiolle.

Satunnaisessa Nxn-verkossa heuristinen arvio valitaan verkon tyypin mukaan. Jos liikkuminen on sallittu vain akselien suuntaan, on heuristiikkana funktio h(a,b) = |a.x-b.x|+|a.y-b.y|. Jos liikkuminen on sallittu myös sivuttain, on heuristiikan pohjalla funktio h(a,b) = max(|a.x-b.x|,|a.y-b.y|), koska tavallinen euklidinen ei toimisi. Myös satunnaisessa verkossa voi ohjelmassa valita Dijikstra-tyyppisen heuristiikan. 