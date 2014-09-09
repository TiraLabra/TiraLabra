## Tiralabra / Hannu Honkanen, periodi 6/2014

## Toteutusdokumentti
**Ohjelman yleisrakenne**

Ohjelmassa on "p��paketti" com.mycompany.Tiralabra_maven, jossa on ajettava tiedosto App.java.

Paketissa com.mycompany.Tiralabra_maven.logiikka on 2 luokkaa. Luokka Paikka kuvaa yht� paikkaa kartalla, jossa liikutaan. T�t� luokkaa k�ytet��n kun algoritmeissa etsit��n nopeinta reitti�. Luokka Piste on vain l�ht�- ja maalipisteiden koordinaattien antoa varten.

Paketissa com.mycompany.Tiralabra_maven.logiikka.aStar on A*-algoritmin toteutus minimikekoa k�ytt�en.

Paketissa com.mycompany.Tiralabra_maven.logiikka.dijkstra on Dijkstran algoritmin toteutus minimikekoa k�ytt�en.

Paketissa com.mycompany.Tiralabra_maven.logiikka.paikkaKeko on toteutettu minimikeko, jonka alkiona on spesifisti Paikka-olio. Paketissa on (rajanpintana) peritt�v� luokka MinKekoAlkionaPaikka, jonka laajentavat/toteuttavat luokat OmaKekoAlkionaPaikka (oma toteutus) ja PriorityQueueKekoAlkionaPaikka (Javan toteutus).

Paketissa com.mycompany.Tiralabra_maven.logiikka.paikkaPino on toteutettu pino, jonka alkiona on spesifisti Paikka-olio. Paketissa on rajanpinta PinoAlkionaPaikka, jonka toteuttavat luokat OmaPinoAlkionaPaikka (oma toteutus) ja StackPinoAlkionaPaikka (Javan toteutus).

Paketissa com.mycompany.Tiralabra_maven.logiikka.bmpOperaatiot on toteutettu l�ht�tietojen luku bmp-tiedostoista sek� polun ja l�pik�ytyjen solmujen "piirt�minen" bmp-tulostiedostoon.

**Pinon oma toteutus**

Pino on toteutettu linkitettyn� listana, jossa pinoalkio (Paikka-olio) tuntee aina seuraavan eli pinossa itse��n alempana olevan pinoalkion.

**Minimikeon oma toteutus**

Minimikeko on toteutettu 1-ulotteisena taulukkona, johon talletetut kekoalkiot ovat Paikka-olioita. Huipulla keossa olevan alkion indeksi on 1. Kekoalkioiden avainten vertailut on toteutettu Paikka-luokan metodia compareTo k�ytt�en, jotta omaa minimikekoa ja Javan Priorityqueue:ta hy�dynt�v�� kekoa voidaan k�ytt�� samalla tavalla ohjelmaa muuttamatta kun siirryt��n Javan toteutuksesta minimikeon omaan toteutukseen.

**Algoritmit**

Algoritmit pyrittiin toteuttamaan niin ett� Dijkstran toteutuksen j�lkeen mahdollisimman pienin muutoksin saadaan aikaa A*. T�t� varten molemmat algoritmit k�ytt�v�t samaa Paikka-luokkaa, jossa on atribuutti etaisyysLoppuun, joka asetetaan konstrutorissa nollaksi. Dijkstrassa etaisyysLoppuun onkin aina nolla, kun taas A*:issa se lasketaan heurestiikkafunktiolla.

**Dijkstra**

Algoritmi on ahne algoritmi. Ahneus t�ss� tapauksessa tarkoittaa sit� ett� seuraavaksi k�sitelt�v�ksi solmuksi valitaan minimikeon huipulta aina se solmu joka on l�hinn� aloitussolmua eli sen etaisyysAlkuun on pienin.

Algoritmi on toteutettu hyvin pitk�lle kuten l�hteess�. P��algoritmi on metodissa ratkaise. My�s l�hteest� tutut metodit initialiseSingleSource ja relax l�ytyv�t ratkaisusta. Seuraavassa eroja l�hteen ratkaisuun:
- Metodi relax on tyyppi� boolean.
- Minimikeolla heap kutsutaan metodia heapDecreaseKey VAIN kun relaxoidaan (metodi relax palauttaa arvon true).

Lis�ksi mainittakoon ett� k�sitelt�v�n paikan (paikkaU) vieruspaikat (paikkaV) on talletettu omaan pinon toteutukseen (1. versiossa ne olivat viel� muistaakseni ArrayList:ss�). Omaa pinon toteutusta k�ytet��n nopeimman reitin tallentamiseen.

**Astar**

Algoritmi on Dijkstran laajennus, jossa reitin etsint�� nopeutetaan heurestiikkafunktiolla lasketun Paikka-olion et�isyysarvion maalisolmuun, etaisyysLoppuun, avulla. Seuraavaksi k�sitelt�v�ksi solmuksi valitaan minimikeon huipulta aina se solmu jonka etaisyysAlkuun + etaisyysLoppuun on pienin.

Algoritmi on hyvin pitk�lle kopio Dijkstrasta. Heurestiikkafunktio (Manhattan-et�isyys) on toteutettu metodissa initialiseAstar.

**Aika- ja tilavaativuudet**

Molemmilla algoritmeilla tunnetut (ja my�s l�hteess� esitetyt) vaativuudet ovat:
- aikavaativuus O ( (n+m) * log n )
- tilavaativuus on O (n)

**Suorituskyky- ja aikavaativuusanalyysivertailu**

T�m� on esitetty testausdokumentissa. Siell� n�kyv�t kuvaajissa my�s numeroarvot.

**Puutteet ja parannusehdotukset**

Nyt ohjelmassa ei ole erillist� k�ytt�liittym�luokkaa vaan kaikki on App-luokan main-metodissa, joka on turhan pitk�. Erillinen k�ytt�liittym�luokka olisi hyv� olla, ja siell� lyhyit� yhden asian hoitavia metodeja.

Jonkin verran j�i julkisia oliomuuttujia. Parempi olisi k�ytt�� gettereit� ja settereit�.

Virhetarkistuksia aivan liian v�h�n.

**L�hteet:**

Kurssin "58131 Tietorakenteet ja algoritmit" luentomateriaali, Kev�t 2014, Patrik Flor�en
