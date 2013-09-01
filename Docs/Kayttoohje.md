Käyttöohje
==========

Suoritettava jar-tiedosto löytyy repositoriosta sijainnista "Bin/chess-1.0-jar-with-dependencies.jar". Tämän voi myös generoida maven-projektin hakemistossa ("Chess") komennolla "mvn assembly:assembly", jolloin luotava jar on "Chess/target/chess-1.0-jar-with-dependencies.jar".

Seuraavassa on lueteltu ohjelman toiminnot:

Game-valikko
------------
* **New game** - Aloittaa uuden pelin käyttäen valittuja pelaajaasetuksia.
* **Random start position** - Nappuloiden sijainti pelin alussa arvotaan satunnaisesti.
* **Pause before AI move** - Mahdollistaa kahden tietokonepelaajan välisen pelin suorittamisen vuoro kerrallaan.
* **Execute AI move** - Suorittaa seuraavan siirron jos "pause before AI move" asetus on päällä.

Player-valikko
--------------
Määrittää asetukset kummallekin pelaajalle (Player 1 = valkoinen, Player 2 = musta).

* **AI player** - Määrittää, onko kyseinen pelaaja ihmisen vai tekoälyn ohjaama.
* **Time limit** - Aika, jonka tekoälypelaaja käyttää siirron laskemiseen.
* **Max search depth** - Yläraja hakusyvyydelle.
* **Quiescence search** - Jatkaa hakua kunnes löytyy "hiljainen" pelitilanne, jossa ei ole enää kannattavia lyöntejä.

Misc-valikko
------------
* **Run test/Performance test (5s)** - Suorittaa lyhyen suorituskykytestin, jossa yhden iteraation pituus on 5 sekuntia.
* **Run test/Performance test (60s)** - Pitkä suorituskykytesti.
* **Run test/Performance test (60s, no QS)** - Pitkä suorituskykytesti ilman Quiescence-hakua.
* **Run test/Simulation with selected players** - Simuloi useita satunnaisia pelitilanteita kahden tietokonepelaajan välillä, käyttäen valittuja tekoälyasetuksia. Jokainen peli pelataan kahdesti, niin että pelaajien puolet vaihdetaan välissä. Peliparista näytetään 1. pelaajan voittojen lukumäärä, ja lisäksi kaikista pelipareista lasketaan keskiarvo ja sen 95%:n luottamusväli.
* **Run test/Stop test** - Keskeyttää edellisen testin tai simulaation.
* **View search tree for last AI move** - Näyttää MinMax-tekoälyn hakupuun edelliselle siirrolle. Hakupuusta tallennetaan ainoastaan 3 ensimmäistä tasoa. Jokaisesta hakupuun solmusta näytetään vastaavat alfa- ja beeta-arvot, solmun palauttama pistearvo, sekä onko kyseessä tarkka arvo, alaraja vai yläraja (= tai >= tai <=).
* **Show debug info** - Näyttää jokaisesta tekoälysiirrosta sen löytämät parhaat siirrot kullakin syvyydellä, sekä solmujen lukumäärän ja transpositiotaulun tiedot viimeisellä iteraatiolla.
