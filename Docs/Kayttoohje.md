Käyttöohje
==========

Ohjelman suorittaminen
----------------------

Ohjelma suoritetaan avaamalla Tiralabra_maven-1.0-SNAPSHOT.jar -niminen tiedosto vähintään javan versiolla 7.


Ohjelman toiminnot
------------------

Uutta peliä varten on ensin tehtävä muutamia valintoja:
- Pelaajatyyppi erikseen mustille ja valkoisille nappuloille:
	- Ihminen tarkoittaa ohjelman käyttäjää, ihmispelaajaa. Tällöin käyttäjän on itse tehtävä haluamansa siirrot
	- EkaAI tarkoittaa tekoälypelaajaa, joka yksinkertaisesti valitsee aina ensimmäinen mahdollisen siirron kaikkien siirtojen listasta. Tällöin käyttäjä ei itse tee siirtoja, vaan ohjelma toteuttaa tekoälyn valitseman siirron
	- Minimax tarkoittaa tekoälypelaajaa, joka selvittää parhaan mahdollisen siirron käyttäen minimax-algoritmia ja heuristiikkaa, jonka avulla selvitetään eri tilanteiden arvot. Tällöin käyttäjä ei itse tee siirtoja, vaan ohjelma toteuttaa tekoälyn valitseman siirron.

- Syvyys tarkoittaa minimax-algoritmin kanssa käyttettyä hakusyvyyttä. Tämän arvon muuttamisella ei ole merkitystä ellei valittuna ole minimax-tekoäly. Mitä suurempi syvyys, sitä pidemmälle tekoäly laskee siirtoja ja sitä kauemmin toteutettavan siirron selvittämisessä kestää.

- Viive tarkoittaa aikaa, joka odotetaan aina ennen kuin tekoäly toteuttaa siirtonsa. Sen avulla käyttäjä voi keinotekoisesti hidastaa tekoälyn siirtojen suorittamista jotta yksittäiset siirrot on mahdollista havaita. Kenttään on kirjoitettava jokin positiivinen luku tai nolla.

- AI siirtää automaagisesti tarkoittaa sitä, että jos tämä on valittuna, AI tekee seuraavan siirtonsa aina automaattisesti joko heti kun on saanut sen selvitettyä tai sitten odotettuaan viive-kohdassa annetun ajan. Jos tämä ei ole valittuna, käyttäjän on itse käskettävä tekoälyä siirtämään painamalla AI-nappia.

Uusi peli aloitetaan yllä selitettyjen valintojen jälkeen painamalla Uusi peli -nappia. Aiemmin tehdyistä valinnoista riippuen käyttäjä joutuu vielä tekemään jotain tai sitten hän voi vain seurata peliä kunnes se on ohi.

Jos toinen tai molemmat pelaajat ovat ihmispelaajia, on käyttäjän tehtävä niiden siirrot. Mahdolliset siirrettävät nappulat on aina ympäröity sinisellä neliöllä. Kun jokin sallittu nappula on valittu, näytetään vihreillä neliöillä ruudut, joihin kyseisen nappulan voi liikuttaa.

JOS MUKANA ON TEKOÄLYPELAAJA, JOKA EI SIIRRÄ AUTOMAATTISESTI, ON KÄYTTÄJÄN PAINETTAVA AI-NAPPIA AINA KUN HALUAA NÄHDÄ TEKOÄLYPELAAJAN SIIRRON.

Pelin voi luovuttaa painamalla Luovuta-nappia. Tällöin peli päättyy vastustajan voittoon. Uuden pelin voi aloittaa milloin tahansa painamalla Uusi peli -nappia.
