Tammitekoäly
=======

Tammi (English draughts) on shakkia hieman yksinkertaisempi kahden pelaajan lautapeli, jota pelataan 8x8 ruudun shakkilaudalla ja 12+12 identtisellä nappulalla. Nappulat liikkuvat vain mustilla ruuduilla ja niitä liikutetaan vuorotellen ruutu kerrallaan etuviistoon. Vastustajan nappulan voi syödä hyppäämällä sen yli etuviistoon sen takana olevaan tyhjään ruutuun. Samalla vuorolla voi syödä useita nappuloita, jos hyppelyä on mahdollista jatkaa. Pelaajasta katsottuna viimeiselle riville asti edennyt nappula kruunataan kuninkaaksi ja se pystyy liikkumaan myös takaviistoon. Peli päättyy, kun toisen pelaajan kaikki nappulat on syöty tai hän ei pysty enää tekemään yhtään siirtoa.[1] Tammen säännöistä on paljon erilaisia versioita, mutta nämä ovat ehkä yleisimmin käytössä olevat.


Työn tavoite
-----------

Työn tavoitteena on toteuttaa tammipeli ja tekoäly sille. Peliä on sitten mahdollista pelata tekoälyä vastaan tai antaa kahden tekoälyn pelata keskenään. Tämä mahdollistaa myös kahden erilaisen tekoälyn vertailun, jolloin voidaan esimerkiksi kehittää entistä parempaa tekoälyä.


Tarvittavat algoritmit
-----------

Tekoälyn mahdollisista siirroista lasketaan kullakin siirtovuorolla tietyn syvyinen pelipuu, jossa puun solmut vastaavat aina yksittäisiä pelitilanteita. Puun syvyys taas kertoo, kuinka monta siirtoa eteenpäin tilannetta lasketaan. Tammessa eri siirtovaihtoehtoja on niin paljon, ettei pelipuuta ole mahdollista järkevässä ajassa laskea kokonaan. (Tammi on tosin ratkaistu laskemalla kaikki mahdolliset siirrot, jolloin havaittiin sen päättyvän tasapeliin, jos molemmat pelaavat täydellisesti.[4]) Pelipuuta voidaan laskea esimerkiksi niin pitkälle, kun se on tietyssä ajassa mahdollista. Tekoälyn toimintaa voidaan kokeilla erisyvyisillä pelipuilla, jotta löydetään sopiva kompromissi nopeuden ja hyvyyden välillä. Puun kasvattamisen pitäisi yleensä parantaa todennäköisyyttä parhaan mahdollisen siirron valinnalle.[2]

Kunkin solmun arvo eli pelitilanteen hyvyys on kyettävä pisteyttämään jotenkin. Tätä varten on suunniteltava funktio, joka kuvaa pelitiannetta mahdollisimman hyvin, mutta jonka arvo on laskettavissa mahdollisimman nopeasti. Pisteitä saattaa saada esimerkiksi omista nappuloista laudalla, mahdollista siirroista, syötävissä olevista nappuloista, kruunautumismahdollisuuksista jne.[3]

Tekoälyn seuraava siirto etsitään pelipuun avulla käyttäen minimax-algoritmia, jota yleisesti käytetään tämäntyyppisissä tehtävissä. Toinen pelaaja pyrkii mahdollisimman suureen ja toinen mahdollisimman pieneen pistemäärään. Puun solmut käydään rekursiivisesti läpi ja lasketaan kullekin mahdolliselle siirrolle pistemäärä. Näistä sitten toteutetaan parhaan mahdollisen pistemäärän saanut siirto.[5]

Pelipuun laskettavien haarojen määrää karsimalla nopeutetaan minimax-algoritmin toimintaa. Karsimisessa käytetään alfa-beta -karsintaa, joka jättää tutkimatta sellaiset pelipuun haarat, joissa ei enää ole mahdollista saavuttaa parempaa tulosta kuin on jo saavutettu jossain toisessa haarassa.[6]

Alfa-beta -karsinnan toimintaa voi parantaa esimerkiksi aloittamalla pelipuun tutkimisen sellaisista siirroista joiden voisi olettaa olevan parempia kuin muiden siirtojen. Esimerkiksi nappulan kruunaaminen on varmasti useissa tilanteissa paras mahdollinen siirto, joten laskeminen kannattaa aloittaa siitä.

Tekoälyn toteutukseen valitut algoritmit ovat varmasti yleisimmin käytetyt algoritmit tämän tyyppisissä ongelmissa ja siksi tuskin ainakaan huonoin mahdollinen lähestymistapa tähän ongelmaan. Lisäksi minimax ja alfa-beta on esitelty eri kursseilla ja siksi jo ennestään tuttuja, mikä varmasti helpottaa työn toteutusta.


Ohjelman saamat syötteet
-----------

Ohjelma tarvitsee tiedon nykyisestä pelitilanteesta. Pelitilanne on mahdollista esittää esimerkiksi luettelemalla pelilaudan ruudut ja kertomalla onko niissä jokin nappula. Lisäksi tarvitaan tieto siitä, kumman pelaajan vuoro on seuraavaksi. Virallisesti peli esitetään vain luettelemalla tehdyt siirrot, mutta tällöin pitäisi lisäksi erikseen selvittää kunkin nappulan sen hetkinen sijainti, joten virallisen esitystavan käyttäminen tässä työssä ei ehkä ole järkevää.


Aika- ja tilavaativuustavoitteet
-----------

Minimax-algoritmin aikavaativuus on O(b^d), jossa b on haarautumiskerroin eli kunkin solmun lapsisolmujen määrä ja d haun syvyys. Jos alfa-beta -karsinnalla onnistuttaisiin aina laskemaan ensin paras mahdollinen siirto, olisi aikavaativuus O(b^(d/2)).[6] Aikavaativuus on silti eksponentiaalinen, minkä takia ei pelipuuta ei ole mahdollista laskea kovinkaan pitkälle eli puun syvyyden on oltava suhteellisen pieni.

Tilavaativuus taas on lineraarinen O(n) ja siihen vaikuttaa vain pelipuun syvyys. Tämä johtuu siitä, että pelipuusta lasketaan vain yksi haara kerrallaan, eikä muistia tarvita jo lasketuille haaroille, jos ne olivat nykyistä huonompia.


Lähteet
----------

[1] http://fi.wikipedia.org/wiki/Tammi_(peli) (3.8.2014)

[2] http://en.wikipedia.org/wiki/Game_tree (3.8.2014)

[3] http://en.wikipedia.org/wiki/Evaluation_function (3.8.2014)

[4] http://www.scientificamerican.com/article/computers-solve-checkers-its-a-draw/ (3.8.2014)

[5] http://en.wikipedia.org/wiki/Minimax (3.8.2014)

[6] http://en.wikipedia.org/wiki/Alpha-beta_pruning (3.8.2014)
