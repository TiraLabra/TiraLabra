## Tiralabra / Hannu Honkanen, periodi 6/2014

## Toteutusdokumentti
**Ohjelman yleisrakenne**

Ohjelmassa on "pääpaketti" com.mycompany.Tiralabra_maven, jossa on ajettava tiedosto App.java.

Paketissa com.mycompany.Tiralabra_maven.logiikka on 2 luokkaa. Luokka Paikka kuvaa yhtä paikkaa kartalla, jossa liikutaan. Tätä luokkaa käytetään kun algoritmeissa etsitään nopeinta reittiä. Luokka Piste on vain lähtö- ja maalipisteiden koordinaattien antoa varten.

Paketissa com.mycompany.Tiralabra_maven.logiikka.aStar on A*-algoritmin toteutus minimikekoa käyttäen.

Paketissa com.mycompany.Tiralabra_maven.logiikka.dijkstra on Dijkstran algoritmin toteutus minimikekoa käyttäen.

Paketissa com.mycompany.Tiralabra_maven.logiikka.paikkaKeko on toteutettu minimikeko, jonka alkiona on spesifisti Paikka-olio. Paketissa on (rajanpintana) perittävä luokka MinKekoAlkionaPaikka, jonka laajentavat/toteuttavat luokat OmaKekoAlkionaPaikka (oma toteutus) ja PriorityQueueKekoAlkionaPaikka (Javan toteutus).

Paketissa com.mycompany.Tiralabra_maven.logiikka.paikkaPino on toteutettu pino, jonka alkiona on spesifisti Paikka-olio. Paketissa on rajanpinta PinoAlkionaPaikka, jonka toteuttavat luokat OmaPinoAlkionaPaikka (oma toteutus) ja StackPinoAlkionaPaikka (Javan toteutus).

Paketissa com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot on toteutettu lähtötietojen luku bmp-tiedostoista sekä polun ja läpikäytyjen solmujen "piirtäminen" bmp-tulostiedostoon.

**Pinon oma toteutus**

Pino on toteutettu linkitettynä listana, jossa pinoalkio (Paikka-olio) tuntee aina seuraavan eli pinossa itseään alempana olevan pinoalkion.

**Minimikeon oma toteutus**

Minimikeko on toteutettu 1-ulotteisena taulukkona, johon talletetut kekoalkiot ovat Paikka-olioita. Huipulla keossa olevan alkion indeksi on 1. Kekoalkioiden avainten vertailut on toteutettu Paikka-luokan metodia compareTo käyttäen, jotta omaa minimikekoa ja Javan Priorityqueue:ta hyödyntävää kekoa voidaan käyttää samalla tavalla ohjelmaa muuttamatta kun siirrytään Javan toteutuksesta minimikeon omaan toteutukseen.

**Algoritmit**

Algoritmit pyrittiin toteuttamaan niin että Dijkstran toteutuksen jälkeen mahdollisimman pienin muutoksin saadaan aikaa A*. Tätä varten molemmat algoritmit käyttävät samaa Paikka-luokkaa, jossa on atribuutti etaisyysLoppuun, joka asetetaan konstrutorissa nollaksi. Dijkstrassa etaisyysLoppuun onkin aina nolla, kun taas A*:issa se lasketaan heurestiikkafunktiolla.

**Dijkstra**

Algoritmi on ahne algoritmi. Ahneus tässä tapauksessa tarkoittaa sitä että seuraavaksi käsiteltäväksi solmuksi valitaan minimikeon huipulta aina se solmu joka on lähinnä aloitussolmua eli sen etaisyysAlkuun on pienin.

Algoritmi on toteutettu hyvin pitkälle kuten lähteessä. Pääalgoritmi on metodissa ratkaise. Myös lähteestä tutut metodit initialiseSingleSource ja relax löytyvät ratkaisusta. Seuraavassa eroja lähteen ratkaisuun:
- Metodi relax on tyyppiä boolean.
- Minimikeolla heap kutsutaan metodia heapDecreaseKey VAIN kun relaxoidaan (metodi relax palauttaa arvon true).

Lisäksi mainittakoon että käsiteltävän paikan (paikkaU) vieruspaikat (paikkaV) on talletettu omaan pinon toteutukseen (1. versiossa ne olivat vielä muistaakseni ArrayList:ssä). Omaa pinon toteutusta käytetään nopeimman reitin tallentamiseen.

**Astar**

Algoritmi on Dijkstran laajennus, jossa reitin etsintää nopeutetaan heurestiikkafunktiolla lasketun Paikka-olion etäisyysarvion maalisolmuun, etaisyysLoppuun, avulla. Seuraavaksi käsiteltäväksi solmuksi valitaan minimikeon huipulta aina se solmu jonka etaisyysAlkuun + etaisyysLoppuun on pienin.

Algoritmi on hyvin pitkälle kopio Dijkstrasta. Heurestiikkafunktio (Manhattan-etäisyys) on toteutettu metodissa initialiseAstar.

**Aika- ja tilavaativuudet**

Molemmilla algoritmeilla tunnetut (ja myös lähteessä esitetyt) vaativuudet ovat:
- aikavaativuus O ( (n+m) * log n )
- tilavaativuus on O (n)

**Suorituskyky- ja aikavaativuusanalyysivertailu**

Tämä on esitetty testausdokumentissa. Siellä näkyvät kuvaajissa myös numeroarvot.

**Puutteet ja parannusehdotukset**

Nyt ohjelmassa ei ole erillistä käyttöliittymäluokkaa vaan kaikki on App-luokan main-metodissa, joka on turhan pitkä. Erillinen käyttöliittymäluokka olisi hyvä olla, ja siellä lyhyitä yhden asian hoitavia metodeja.

Jonkin verran jäi julkisia oliomuuttujia. Parempi olisi käyttää gettereitä ja settereitä.

Virhetarkistuksia aivan liian vähän.

**Lähteet:**

Kurssin "58131 Tietorakenteet ja algoritmit" luentomateriaali, Kevät 2014, Patrik Floréen
