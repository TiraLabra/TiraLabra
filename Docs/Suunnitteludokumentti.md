# Toteutusdokumentti

## Ohjelman yleisrakenne
Ohjelma käynnistetään ajamalla pääluokka App.java. Ohjelmaan toteutetut tietorakenteet ovat linkitetty lista sekä prioriteettijono.

## Saavutetut aika- ja tilavaativuudet
### Linked List
Harjoitustyöhön sisältyy toteutus linkitetystä listasta. Linkitetty lista koostuu MyList-luokasta jossa on listan implementaatio sekä ListNode-luokasta, joka on toteutus listan solmuille.

* search:
Aikavaativuus on `O(n)`: listaa käydään läpi n solmua, ja palautetaan kunnes haluttu solmu on löydetty.
* add:
Aikavaativuus on `O(1)`: uusi solmu tulee aina listan alkuun, jolloin aika on vakio.
* contains
Aikavaativuus on `O(n)`: listaa käydään läpi n solmua, ja palautetaan kunnes haluttu solmu on löydetty.
* remove:
Aikavaativuus on `O(n)`: remove kutsuu ensin search-metodia jonka avulla haluttu solmu löydetään. Tämän jälkeinen poisto-operaatio on aikavaatimuksiltaan vakio.

Listan tilavaativuus on `O(n), listan koko riippuu sen alkioiden määrästä.

### Prioriteettijono

A-star algoritmin avointen naapurisolmujen

* insert:
Aikavaativuus on `O(log n)`: listaa käydään läpi n solmua, ja palautetaan kunnes haluttu solmu on löydetty.
* min:
Aikavaativuus on `O(1)`: palautetaan vain jonon ensimmäinen alkio.
* heapify:
Aikavaativuus on `O(log n)`: pahimmassa tapauksessa binäärikekoa pitää käydä läpi sen koko korkeuden verran.
* contains:
Aikavaativuus on `O(n)`: Jonoa käydään läpi n indeksiä, kunnes oikea solmu löytyy.
* bubbleUp:
Aikavaativuus on `O(n)`: Jonoa käydään läpi n vanhempaa jotka ovat arvoltaan suurempia kuin käsiteltävä solmu.
* decreaseCost:
Aikavaativuus on `O(n)`: Arvon vaihtamisen aikavaativuus on A(1), mutta metodi kutsuu bubbleUp-metodia, jonka aikavaativuus on huonompi.
* removeNode:
Aikavaativuus on `O(log n)`: removeNode käyttää heapify-metodia, jonka aikavaativus on O(log n).
* deleteMinimum:
Aikavaativuus on `O(log n)`: deleteMinimum käyttää heapify-metodia.


Prioriteettijonon aikavaativuus on siis huonoimmassa tapauksessa `O(log n)`.



## Suorituskyky- ja O-analyysivertailu
Testausdokumentista löytyy testejä jotka on toteutettu algoritmin eri heuristiikoilla.

* Euclidean distance
Heuristiikka on hitaampi, mutta varma, sillä Manhattan- ja Diagonal distanceen verrattuna heuristiikka tekee ajallisesti
kalliimaan Math.abs-laskuoperaation.

* Manhattan distance
Tarkkojen laskentaoperaatioiden sijaan seuraavasta hyvästä siirrosta tehdään arvio, jonka mukaan edetään. On hieman epävarmempi
 kuin Euclidean, mutta nopeampi sillä kalliita laskuoperaatioita ei tehdä.

* Diagonal distance
On pääpiirteiltään sama kuin Manhattan distance, mutta on erikoistunut karttoihin, joissa on mahdollista liikkua vinosti.

* Dijkstra's algorithm
Dijkstran algoritmissa ei ole heuristiikkaa ollenkaan, joten algoritmi käy läpi kaikki Nodet yksitellen niin kauan, että
loppupiste löytyy.




## Työn mahdolliset puutteet ja parannusehdotukset
Työn voisi toteuttaa niin, että ohjelmaan voisi lisätä omia karttoja sekä alun App.java on tällä hetkellä toteutukseltaan hieman huono,
sillä kartan tai heuristiikan vaihtaminen onnistuu pelkästään muokkaamalla App.java-tiedostoa.


## Lähteet
http://www.policyalmanac.org/Finnish/astar_fin.pdf

http://www.redblobgames.com/pathfinding/a-star/introduction.html

http://en.wikipedia.org/wiki/A*_search_algorithm

http://www.cokeandcode.com/main/tutorials/path-finding/

Tietorakenteet ja algoritmit-kurssin luentomateriaali
