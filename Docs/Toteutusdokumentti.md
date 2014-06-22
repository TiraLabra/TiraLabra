#Toteutusdokumentti

##Ohjelman yleisrakenne

Reitinhaun lähtökohtana on hakualue, joka on käytännössä kaksiulotteinen taulukkorakenne. Alue-luokka sisältää hakualueeseen liittyvän tiedon. Luokassa on pari metodia yksinkertaisien pienten testialueiden luomiseen, joita käytettiin lähinnä aivan alustavassa testaustarkoituksessa.

Jokainen taulukon solu on Node-luokan olio. Olio sisältää sijaintitietojensa lisäksi reitinhakuun tarvittavia tietoja. Suurin osa sisällöstä on lähinnä yksinkertaisia gettereitä ja settereitä. Olennaisinta sisältöä on compareTo-metodi, joka huolehtii Nodejen keskinäisen "suuruusjärjestyksen" vertailusta, joka on olennainen AStarin käyttämän prioriteettijonon kannalta.

#### Kuvan lukeminen ja käsittely

Varsinainen työkalu hakualueiden muodostamiseen on Kuvalukija-luokka. Tarkoitus on, että mistä tahansa bmp-muodossa olevasta kuvatiedostosta voidaan generoida kelvollinen hakualue. Kuvatiedosto luetaan, ja jokainen pikseli käydään yksitellen läpi, luoden siitä Node-olio ja sijoittaen sopivasti taulukkoon. Huomioitavaa on, että tässä työssä alueen "koordinaatteja" tarkastellaan rivi-sarake-pareina, toisin kuin kuvatiedostosta puhuttaessa yleensä ajatellaan x-y-koordinaattijärjestyksessä.

Monimutkaisin asia kuvantulkinnassa on yksittäisten pisteiden kustannusarvojen tulkitseminen. Ajatuksena on se, että vaaleat alueet luodaan 0-kustannuksina, ja mustat tulkitaan seiniksi, jotka on määritelty kustannusarvoksi 9. 

Metodi kirkkausarvo laskee rbg-väriarvon komponenteista neliömällä ja arvioidun ihmissilmän aistiman painotuksen mukaan summaamalla saadun arvon kokonaiskirkkaudelle. Tämän kokonaiskirkkauden perusteella sitten tulkitaan kustannusarvo välille 0-5. Myöhemmin AStar-haussa tämän kustannusarvon perusteella painotetaan varsinaista siirtymäkustannusta laskuarvioissa.




#### Astar ja heuristiikat

AStar-haku käyttää jotain heuristiikkaa, jonka se saa rajapintana konstruktorissaan. Harjoitustyöhön on toteutettu kolme eri heuristiikkaa, Dijkstra (jota voisi ajatella myös kokonaan omana hakualgoritminaan), manhattan- ja euklidinen heuristiikka. Itse heuristiikat ovat yksinkertaisia, ne vain toteuttavat etäisyyden laskennan yhtenä metodina.

#### Omat perustietorakenteet

Tarvittaviksi perustietorakenteiksi Javan vastaavien korvaajaksi on toteutettu ArrayListOma-luokka, joka siis vastaa ArrayListia, niiltä ominaisuuksilta kuin projekti tarvitsee. Täyttä vastaavuutta kaikkien metodien osalta Javan kalustoon ei ole huomioitu. Olennaisin sisältö on tiedot tallentava taulukko, joka alustetaan jonkin kokoiseksi, ja aina uutta sisältöä lisättäessä tarkastetaan, jos taulukko on täyttynyt, ja tarvittaessa tuplataan taulukon koko.

Toinen kokonaan tehty oma tietorakenne Keko. Käytännössä nimestä huolimatta kyseessä on oikeastaan kekorakenteen avulla toteutettu prioriteettijono. Pohjana on vastaavata taulukkotallennus kuin ArrayListOma-luokassa.




## Saavutetut aika- ja tilavaatimukset, suorituskyky- ja O-analyysivertailu



Keon operaatioiden pitäisi noudattaa teoreettisia binäärikeon aikavaatimuksia. Minimin lukeminen on vakioaikaista. Minimin poisto, uuden syöttäminen, ja alkion prioriteerin pienentäminen pitäisi suoriutua O(log(n))-ajassa. Pienenä miinuksena on operaatio jossa etsitään halutun noden kohta indeksinä. Tämä tuli tehtyä aluksi vain taulukon läpikäyntinä, enkä ehtinyt muuttaa sitä mahdollisesti keko-ominaisuuksia hyödyntäväksi. Sen aikavaatimus taitaa olla siis O(n).

Suorituskykyvertailusta löytyy konkretiaa parhaiten testausdokumentista.



## Työn mahdolliset puutteet ja parannusehdotukset

Harmittavin puute tulee siitä, etten ehtinyt saada JARia toimivaksi kuvien polkujen osalta. Olisi varmaan triviaali korjata, kun tietäisi miten ne pitää määritellä.

Yleisesti ottaen käyttöliittymä on hyvin karu, sitä voisi parannella jos ohjelmasta haluaisi oikeasti käytettävän. Yksinkertainen graafinen käyttöliittymä, jossa voisi kuvasta klikkaamalla valita sekä lähtö- että maalipisteen toisi huimasti lisää käytettävyyttä. Mutta nämä eivät kuuluneet kurssin painopisteisiin, joten niihin en käyttänyt aikaa paljoa.

Kuvanluvussa kuljettavuusarvojen tulkintaa voisi viilailla paremmaksi. Nyt oli hieman haastavaa löytää hyviä valmiita karttakuvia, joissa olisi paljon eri painotuksia, ja riittävästi vaalea alueita, mutta myös selkeitä seiniä jotka pilkkovat hakualuetta.

Olisi ollut mielenkiintoista tehdä myös esim. fibonacci-toteutus kekoon, ja tehdä suorituskykyvertailuja sen kanssa.

## Lähteet

Määrittelydokementissa oli lähteitä, joita käytetty suunnitteluvaiheessa. Tässä listattu lisäksi joitain, jotka olivat apuna toteutusvaiheessa.


Kuvanlukijassa tarvittua algoritmia pikselin "tummuuden" laskemiseksi:

* http://www.nbdtech.com/Blog/archive/2008/04/27/Calculating-the-Perceived-Brightness-of-a-Color.aspx
* http://en.wikipedia.org/wiki/Luminance_(relative)

AStar-materiaalia mm:

* http://qiao.github.io/PathFinding.js/visual/
* http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html
* http://www.policyalmanac.org/games/aStarTutorial.htm
* http://wiki.gamegardens.com/Path_Finding_Tutorial


