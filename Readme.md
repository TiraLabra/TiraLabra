## Sanapuuro

### Yleiskuva

Tämä projekti on jatkoa Sanapuuro-pelille, jonka tein JavaLabraa varten. Tämä versio on karumpi, ts. siinä ei ole graafista käyttöliittymää, vaan interaktio hoidetaan terminaalissa. Yksinpeliä ei enää ole vaan se on korvattu kaksinpelillä. Toistaiseksi peliä pelataan lokaalisti tietokonetta vastaan.

#### Toteutuksesta

TiraLabran aiheena on tekoälyn toteutus toiseksi pelaajaksi kaksinpelissä. Tekoälyä vaatii tietorakenteen, jonka avulla sanojen haku hoituu nopeasti. Toteutettavaksi tietorakenteeksi on valittu hajautustaulukko. Hajautustaulukolle toteutetaan hajautusfunktio, joka laskee hajautusarvon String-objekteille. Toteutettava hajautusfunktion on yksinkertainen. Jokaisen kirjaimen ascii-arvo kerrotaan numerolla, joka on jokin vakio potenssiin kirjaimen paikkaindeksi merkkijonossa, ja lopuksi tulot summataan. Tulosta otetaan vielä jakojäännös, joka jää kun jaetaan taulukon koolla. Taulukon koko on myös valittava hyvin. Suositus on, että m on alkuluku, joka ei ole lähellä 2:n potenssia.

Lähde: http://www.cs.helsinki.fi/u/floreen/tira2014/tira.pdf

* * *

### Käyttöohjeet

Pelissä on kursori, jota voi liikutella 'w', 'a', 's' ja 'd' komentojen avulla. Kun halutaan syöttää sana kursorin osoittamasta kohdasta alkaen, annetaan komento 'q'. Peli pyytää sen jälkeen suunnan ja syötettävät kirjaimet. Pelaaja voi skipata vuoronsa syöttämällä tyhjän merkkijonon. Pelaajan pisteet sekä käytettävissä olevat kirjaimet ovat näkyvissä konsolitulosteessa. Peliä on paljon mielekkäämpää kokeilla terminaalissa, kuin esim. NetBeansissa. Ajettava jar löytyy [täältä](http://www.cs.helsinki.fi/u/skaipio/jars/Sanapuuro-1.0-SNAPSHOT.jar). Poistumiseen pelistä ei ole erillistä komentoa, vaan sen voi pysäyttää terminaalissa komennolla Ctrl+C.
