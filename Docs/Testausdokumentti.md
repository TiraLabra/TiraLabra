Testausdokumentti
=================

Ohjelman oikeellisuutta on testattu melko kattavasti JUnit-testien avulla, ja testikattavuuden parantamisessa on apuna käytetty coberturaa. Ainoastaan käyttöliittymää ja suorituskykytestejä varten ei ole JUnit-testejä.

Lisäksi tekoälyä on peluutettu aikaisempia versioita vastaan satunnaisesti generoiduissa pelitilanteissa. Asettamalla siirtojen aikaraja riittävän pieneksi pystytään pelaamaan satoja pelejä, ja voittoprosentin avulla saadaan karkea kuva tekoälyn tason kehityksestä, ja varmistuttua etteivät muutokset heikennä tasoa. Nämä testit eivät kuitenkaan ole helposti toistettavissa vaan vaativat lähdekoodiin puukottamista.

Suorituskyky
------------

Suorituskykyä on testattu jatkuvasti kehityksen aikana käyttäen samaa suorituskykytestiä. Nämä testit löytyvät ohjelman valikosta Misc/Run test.

Testi generoi laillisia pelitilanteita, joissa nappuloiden sijainti ja lukumäärä laudalla on satunnainen. Jokaiseen pelitilanteeseen etsitään paras siirto ilman aikarajaa, mutta rajoittamalla haku tiettyyn syvyyteen. Suorituskerroista mitataan keskimääräinen aika, ja keskimääräinen vierailtujen solmujen lukumäärä iteratiivisesti syvenevän haun viimeisellä iteraatiolla. Jokaisella syvyydellä analysoidaan niin monta pelitilannetta kuin testin aikarajan puitteissa ehtii.

Nopeustestin tulokset on esitetty seuraavissa graafeissa. Testi on toistettu sekä ilman Quiescence-hakua (pelkkä PVS), että sen kanssa (PVS+QS). Jälkimmäinen kasvattaa hakusolmujen määrää huomattavasti varsinkin pienillä syvyyksillä.

![Depth vs count](pics/depth_vs_count.png "Keskimääräinen hakusolmujen määrä")

![Depth vs time](pics/depth_vs_time.png "Keskimääräinen hakuaika")

Syvyyksillä d > 10 otanta on niin pieni, että arvot eivät ole kovin luotettavia.

Tuloksista kuitenkin näkee hyvin aikavaativuuden eksponentiaalisuuden (O(b^d)). Solmujen lukumäärästä saadaan haun efektiiviseksi haarautumiskertoimeksi n. b=2,6.

Hakusyvyyden vaikutus tasoon
----------------------------

Tekoälyn asetusten vaikutusta on tutkittu erillisellä simulaatiolla, joka pelaa useita pelejä kahden tekoälypelaajan välillä. (Ajettavissa valikkokomennolla Misc/Run test/Simulation).

Kuvaajassa on esitetty keskimääräinen pistemäärä ja virhemarginaalit eri asetuksilla, kun vastustajana on keskitasoinen tekoäly (syvyys 5, ei aikarajaa, QS päällä).

![Winrates](pics/winrates.png "Keskimääräinen pistemäärä eri asetuksilla")


