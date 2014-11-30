# Toteutusdokumentti

## Ohjelman yleisrakenne
Ohjelma käynnistetään ajamalla pääluokka App.java. Ohjelmaan toteutetut tietorakenteet ovat linkitetty lista sekä prioriteettijono.

## Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)
### Linked List
Harjoitustyöhön sisältyy toteutus linkitetystä listasta. Linkitetty lista koostuu MyList-luokasta jossa on listan implementaatio sekä ListNode-luokasta, joka on toteutus listan solmuille.
* search:
Aikavaativuus on O(n): listaa käydään läpi n solmua, ja palautetaan kunnes haluttu solmu on löydetty.
* add:
Aikavaativuus on O(1): uusi solmu tulee aina listan alkuun, jolloin aika on vakio.
* contains
Aikavaativuus on O(n): listaa käydään läpi n solmua, ja palautetaan kunnes haluttu solmu on löydetty.
* remove:
Aikavaativuus on O(n): remove kutsuu ensin search-metodia jonka avulla haluttu solmu löydetään. Tämän jälkeinen poisto-operaatio on aikavaatimuksiltaan vakio.

Listan tilavaativuus on O(n), listan koko riippuu sen alkioiden määrästä.

### Prioriteettijono

Toteutuksen alla.


## Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen)
Lisätään myöhemmin

## Työn mahdolliset puutteet ja parannusehdotukset
Työhön olisi helppo lisätä muita algoritmeja, joiden suoritusaikoja voisi vertailla. Lisäksi ohjelmalle pitää luoda main-luokka josta ohjelma käynnistettäisiin Astar-luokan sijaan.

## Lähteet
http://www.policyalmanac.org/Finnish/astar_fin.pdf

http://www.redblobgames.com/pathfinding/a-star/introduction.html

http://en.wikipedia.org/wiki/A*_search_algorithm

http://www.cokeandcode.com/main/tutorials/path-finding/

Tietorakenteet ja algoritmit-kurssin luentomateriaali
