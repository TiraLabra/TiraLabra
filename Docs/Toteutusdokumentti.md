Toteutusdokumentti
==================

Yleisrakenne
------------

Työssä on toteutettu shakkitekoäly, ja sitä käyttävä graafisella käyttöliittymällä varustettu shakkiohjelma. Java-projekti koostuu oleelliesti neljästä paketista:

![Depth vs count](pics/package_diagram.png "Keskimääräinen hakusolmujen määrä")

Domain-paketissa on toteutettu abstraktio pelitilanteelle, shakin säännöt, tehokas siirtojen generointi, bittimaskien manipulointi yms. operaatiot. Tarkoitus on ollut rakentaa pohja, jonka päälle voisi mahdollisesti rakentaa useita eri shakkitekoälyjä.

Game-paketissa on toteutettu MVC-mallin mukainen lähestymistapa yksittäiselle pelille. Pelille annetaan kaksi pelaajaobjektia, jotka voivat olla joko käyttöliittymässä toteutettuja ihmispelaajia tai tietokonepelaajia. Observer-rajapinnan kautta annetaan tietoa pelin kulusta.

AI-paketissa on toteutettu varsinainen MinMax-tekoäly ja sen tarvitsemat komponentit.

GUI-paketissa on toteutettu ohjelman käyttöliittymä.

Toteutetut shakin ominaisuudet
------------------------------
Peli ja tekoäly tukee lähes kaikkia shakin sääntöjä ja nappuloiden siirtoja, mukaan lukien tornitukset, korotukset ja ohestalyönnit. Ainoa puuttuva sääntö on 50 siirron sääntö (pelin tulisi päättyä pattiin, jos ei 50 siirron kuluessa tapahdu peruuttamattomia siirtoja). Tällä hetkellä peli päättyy pattiin vasta, kun tietty siirtojen maksimimäärä on saavutettu. 

Tekoälyn toteutus
-----------------
...
- iteratiivisesti syvenevä
- minmax/negamax
- alfa-beeta-karsinta
- pvs
- transpositiotaulu
- siirtojen järjestäminen
- nappuloiden sijaintiin perustuva pisteytys
- inkrementaaliset päivitykset
- laudan ja sijaintien esitys bittimaskien avulla
- siirtojen generointi esilaskettujen bittimaskien avulla
- magic bitboards
- quiescence-haku
- nollasiirtoredusointi

Aika- ja tilavaativuudet
------------------------
Yleisen haun aikavaativuudeksi syvyyden suhteen saatiin satunnaisissa pelitilanteissa n. O(3,3^d). (Ks. testausdokumentti.) Tyypillisellä pöytäkoneella tämä tarkoittaa hakupuun läpikäymistä n. 7-30 siirron päähän tilanteesta riippuen.

Toteutuksen tilavaativuutta dominoi transpositiotaulu. Sen tilavaativuus on suoraan verrannollinen vierailtujen solmujen lukumäärään, eli se kasvaa eksponentiaalisesti hakusyvyyden funktiona. Transposititaulun maksimikoko on kuitenkin rajattu miljoonaan alkioon, jonka jälkeen sen muistikulutus
pysyy vakiona.
...

Tietorakenteet
--------------
...

Puutteet ja parannukset
-----------------------
... 

Lähteet:

- http://chessprogramming.wikispaces.com

- http://en.wikipedia.org/wiki/Computer_chess

- http://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
