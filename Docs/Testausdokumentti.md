Testausdokumentti
==================

Ohjelmaa on testattu Junit-testeillä. Ohjelman yleistä toimivuutta on testattu erilaisilla hauilla erilaisilla kartoilla ja varmistettu, että ohjelma tosiaan hakee nopeimman reitin kartalta. Samoin on testattu myös erilaisia virhetilanteita ja muita erikoistilanteita, kuten vääriä syötteitä ja reittiä, jonka pituus on 0.

Suorituskykytestaus on toteutettu myös Junit-testeillä. Näissä katsotaan, että kuinka kauan ohjelmalla kestää tehdä haku erilaisissa tilanteissa. Testeissä on käytetty sekä satunnaisgeneroituja että etukäteen määrättyjä karttoja ja reittejä. Ohjelma toimii kohtuullisen nopeasti, 100x100-kartoilla haku kestää alle 100 millisekuntia.

Myös MinHeap-luokan toimivuutta itsessään on testattu ja se todettu toimivaksi. ClosedSet-luokalle ei juuri testejä ole, koska pohjimmiltaan kyseessä on vain 2-ulotteinen taulukko, jonka alkioiden arvoja muokataan. Nämä toimivat ajassa O(1).

Testisyötteitä ja -tulosteita
=============================

Käytetään testeissä seuraavaa 5x4-kokoista karttaa:

   0 1 2 3 4 X
0  1 1 2 3 2
1  2 6 2 1 1
2  1 3 9 2 2
3  2 1 1 2 1
Y

Esim. hakusyötteellä (0,0,3,3) ohjelma osaa antaa seuraavan hakutuloksen:
(0, 0: time: 0) -> (0, 1: time: 2) -> (0, 2: time: 3) -> (0, 3: time: 5) -> (1, 3: time: 6) -> (2, 3: time: 7) -> (3, 3: time: 9)
joka on tosiaan nopein reitti pisteestä (0,0) pisteeseen (3,3).

Syötteellä (0,0,0,0), eli reitillä jonka pituus on 0, ohjelma antaa seuraavan tuloksen:
(0, 0: time: 0)

Myös oikeasta alakulmasta vasempaan yläkulmaan etsiminen toimii:
(4, 3: time: 0) -> (3, 3: time: 2) -> (2, 3: time: 3) -> (1, 3: time: 4) -> (0, 3: time: 6) -> (0, 2: time: 7) -> (0, 1: time: 9) -> (0, 0: time: 10)

Kartan ulkopuolelle annettu haku ei toimi laisinkaan. Esim syöte (0,0,10,10) antaa seuraavan tuloksen:
Search value(s) out of map range. Max X: 4, max Y: 3.