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




## Saavutetut aika- ja tilavaatimukset
## Suorituskyky- ja O-analyysivertailu

tulossa testauksen muodostamisen yhteydessä...

## Työn mahdolliset puutteet ja parannusehdotukset

## Lähteet



Kuvanlukijassa tarvittua algoritmia pikselin "tummuuden" laskemiseksi:

* http://www.nbdtech.com/Blog/archive/2008/04/27/Calculating-the-Perceived-Brightness-of-a-Color.aspx
* http://en.wikipedia.org/wiki/Luminance_(relative)
