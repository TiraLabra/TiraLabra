Yleiskuvaus
===========

Vertailen työssä salausalgoritmeja ja sitä, miten kuvan salaaminen erilaisilla algoritmeilla vaikuttaa lopputulokseen. Kun käytetään ns. block cipher -algoritmia, joka salaa datan erillisissä palasissa yksi kerrallaan, voidaan esimerkiksi pakkaamattomien kuvien tapauksessa päätellä salatusta kuvasta alkuperäisessä kuvassa esiintyviä muotoja. Tästä syystä on kehitetty block cipher -algoritmeihin erilaisia moodeja, jotka saavat palaset riippumaan toisistaan, jolloin salattu sisältö muistuttaa enemmän satunnaista dataa.

Toteutus
========

Toteutan työssä DES-salausalgoritmin, joka on vanhahko block cipher -algoritmi. Tälläisiä algoritmeja voidaan käyttää useissa erilaisissa moodeissa, joista yksinkertaisin on electronic codebook (ECB). Kyseisessä moodissa jokainen palanen (block) salataan erikseen samalla avaimella. ECB on huono esimerkiksi pakkaamattomien kuvien salaamiseen, sillä jos yhdeksi palaseksi otetaan vaikkapa pikseli tai muutamia pikseleitä, tulee jokaisen saman värisen pikselin salatusta versiosta myös saman värinen. Tämän estämiseksi on kehitetty muita moodeja, esimerkiksi cipher-block chaining sekä counter, joissa palasen salattu sisältö riippuu edellisestä. DES-salaukselle on alun perin määritelty ainakin electronic codebook, cipher block chaining, cipher feedback, sekä output feedback -moodit. Määrittelyn ulkopuolelta voisi toteuttaa myös counter-moodin.

Tietorakenteet
--------------

Itse DES-algoritmi ei vaadi taulukoita monimutkaisempia tietorakenteita, mutta palasien syöttämiseen salausalgoritmille voisin käyttää esimerkiksi jonoa. DES-algoritmissa ei varsinaisesti määritellä kuin yhden palasen salausoperaatio.

Syötteet
========

Syötteenä käytetään pakkaamattomia bittikarttoja, joista salataan pikselien tiedot, jolloin kuvia voi helposti tarkastella sekä vertailla suoraan ja tarvittaessa piirtää esimerkiksi Javan valmiilla kirjastoilla.

Aika- ja tilavaativuus
======================

DES-salaukselle annetaan kiinteän kokoinen palanen dataa salattavaksi ja käytetyt operaatiot ovat vakioaikaisia (mm. XOR, summa, sekä erilaiset kiinteät permutaatiot). Yhden palasen salaamisen aika- sekä tilavaativuus on siis O(1). Jos palaset salataan sarjassa, on aikavaativuus O(n), jossa n on salattavien palasien määrä. Koska vain yhtä palasta salataan kerrallaan, on tilavaativuus O(1).

Lähteet
=======
* [DES-salaus](http://csrc.nist.gov/publications/fips/fips46-3/fips46-3.pdf)
* [Salausmoodit DES:lle](http://www.itl.nist.gov/fipspubs/fip81.htm)
* [Yleisesti salausmoodeista](http://en.wikipedia.org/wiki/Block_cipher_mode_of_operation)
