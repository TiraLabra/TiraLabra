#Toteutusdokumentaatio

##Ohjelman yleisrakenne
Ohjelman rakenne on mavenin määrittelemä projektirakenne. Kaikki työssä 
implementoidut hakupuut hyödyntävät Solmu-luokkaa. Solmulle annetaan arvoksi Obejct olio, mistä päätellään solmun avain.
Solmujen viitteet toisiin solmuihin päätellään puihin määriteltyjen ehtojen mukaisesti. 

Hakupuut on toteutettu tiedostoihin ```BinaarinenHakupuu.java```, ```AVLpuu.java```, ```PunaMustaPuu.java``` ja ```SplayPuu.java```. Lisäksi on tiedosto ```HakupuuRajapinta.java``` rajapinnalle, jonne on
määritelty hakupuun operaatiot lisäys,haku ja poisto. AVL, punamusta ja splaypuu ovat binäärisen hakupuun aliluokkia, koska ne hyödyntävät, laajentavat tai tehostavat binäärisen hakupuun metodeja.

##Saavutetut aika- ja tilavaativuudet
Alle on koottu suorituskykytestauksen tuloksia taulukoituina. Jokaiselle puun lisäys tuloksista on tehty kaavio, josta näkee tulosten käyrän muodon. Mittaustulokset ovat millisekunteja. Merkintä < 0 taulukoissa tarkoittaa tuloksen olevan välillä 0-1 millisekuntia.

Puut | Taulukoissa käytetyt lyhenteet 
----------------------|----
Binäärinen hakupuu    | Bst
AVL-puu               | Avl
Punamusta puu         | Rbt
Splay-puu             | Sp

100 syötettä  | Lisäys | Haku | Poisto
 ------------- | ------ |------|-------
 Bst | 5 | 2 | 1
 Avl | 4 | < 0 | 2
 Rbt | 2 | < 0 | 1
 SP | 1 | 1 | < 0
 
 500 syötettä  | Lisäys | Haku | Poisto
 ------------- | ------ |------|-------
 Bst | 13 | 5 | < 0
 Avl | 12 | 1 | 3
 Rbt | 7 | 1 | 2
 SP | 2 | 5 | 1
  
 1000 syötettä  | Lisäys | Haku | Poisto
 ------------- | ------ |------|-------
 Bst | 15 | 5 | 2
 Avl | 18 | < 0 | 4
 Rbt | 8 | 1 | 5
 SP | 3 | 8 | < 0
  
 5000 syötettä  | Lisäys | Haku | Poisto
 ------------- | ------ |------|-------
 Bst | 50 | 38 | 38
 Avl | 32 | 1 | 14
 Rbt | 18 | 1 | 12
 SP | 6 | 16 | 1
  
 10 000 syötettä  | Lisäys | Haku | Poisto
 ------------- | ------ |------|-------
 Bst | 163 | 139 | 148
 Avl | 38 | 3 | 20
 Rbt | 25 | 2 | 21
 SP | 15 | 25 | 4
  
 50 000 syötettä  | Lisäys | Haku | Poisto
 ------------- | ------ |------|-------
 Bst | 3496 | 3391 | 3408
 Avl | 44 | 5 | 29
 Rbt | 33 | 6 | 42
 SP | 24 | 53 | 6
  
 75 000 syötettä  | Lisäys | Haku | Poisto
 ------------- | ------ |------|-------
 Bst | 7806 | 7596 | 7620
 Avl | 48 | 8 | 31
 Rbt | 37 | 7 | 46
 SP | 25 | 51 | 6
  
 100 000 syötettä  | Lisäys | Haku | Poisto
 ------------- | ------ |------|-------
 Bst | 13 935 | 13 579 | 13 585
 Avl | 53 | 11 | 35
 Rbt | 39 | 9 | 52
 SP | 27 | 56 | 6
 
####Binäärisen hakupuun lisäys-operaation tulokset kaaviona
 
 ![BST](https://github.com/mihassin/TiraLabra/blob/master/Docs/pics/bst.jpg)
 
####AVL-puun lisäys-operaation tulokset kaaviona
 
 ![AVL](https://github.com/mihassin/TiraLabra/blob/master/Docs/pics/avl.jpg)
 
####Punamustan puun lisäys-operaation tulokset kaaviona
 
 ![RBT](https://github.com/mihassin/TiraLabra/blob/master/Docs/pics/rbt.jpg)
 
####Splay-puun lisäys-operaation tulokset kaaviona
 
 ![SPLAY](https://github.com/mihassin/TiraLabra/blob/master/Docs/pics/splay.jpg)
 
##Suorituskyky ja O-analyysivertailu

Yllä olevista taulukoista ja kaavioista nähdään, että teoreettisiin aikavaativuksiin on lähes päästy. Tosin binäärisen hakupuun kaavion kuvaaja ei ole suora vaan loivasti kasvava käyrä. Punamustan puun kaavio muistuttaa eniten aikavaativuutensa vastaavaa käyrää.

##Puutteet ja parannusehdotukset
Perintä ei välttämättä ole paras mahdollinen tapa välttää toistuvan koodin kirjoittamista. Jos työssäni tulisi muutos binäärisen hakupuun koodiin, niin se vaikuttaisi kaikkiin sen periviin luokkiin. Tämä voi muuttaa perivän olion koodin käyttäytymisen vääränlaiseksi. Pahimmassa tapauksessa tulee korjata kaikki oliot, jotka perivät binäärisen hakupuun. Toinen lähestysmistapa olisi ollut kirjoittaa toistuvat metodit erilliseen luokkaan ja asettaa kaikki hakupuut suoraan implementoimaan rajapintaa.

###Lähteet
* wikipedia.org
* Tietorakenteet ja algoritmit -kurssin materiaali
* https://noppa.oulu.fi/noppa/kurssi/811312a/luennot/811312A_opiskelumateriaali_-_perustietorakenteet.pdf
* http://archive.gamedev.net/archive/reference/programming/features/trees2/log.gif
