Testausdokumentti
==================

Ohjelmaa on testattu Junit-testeillä. Ohjelman yleistä toimivuutta on testattu erilaisilla hauilla erilaisilla kartoilla ja varmistettu, että ohjelma tosiaan hakee nopeimman reitin kartalta. Samoin on testattu myös erilaisia virhetilanteita ja muita erikoistilanteita, kuten vääriä syötteitä ja reittiä, jonka pituus on 0.

Suorituskykytestaus on toteutettu myös Junit-testeillä. Näissä katsotaan, että kuinka kauan ohjelmalla kestää tehdä haku erilaisissa tilanteissa. Testeissä on käytetty sekä satunnaisgeneroituja että etukäteen määrättyjä karttoja ja reittejä. Toistaiseksi ohjelma toimii hyvin nopeasti pienillä kartoilla (saavuttaa jopa 5 ms nopeuden 10x10 ruudun kartalla mentäessä kartan päästä päähän), mutta kasvaa nopeasti kartan kasvaessa (100x100 kartalla päästä päähän menevän reitin etsiminen kestää n. 2,3 sekuntia). Kuten odotettua, reitin etsimisen nopeuteen vaikuttaakin eniten juuri reitin pituus.