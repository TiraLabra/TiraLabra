Ohjelman yleisrakenne
=====================

* Ohjelman toiminnan kannalta tärkein luokka on game.Board. Se on vastuussa pelilaudan päivittämisestä ja liikkeiden asettamisesta laudalle. Board sisältää kaksiulotteisen Player -taulukon, missä Player enum kertoo minkä värinen nappula kussakin laudan koordinaatissa on. 
* board.game.AI luokka sisältää tekoälyn kaikki ominaisuudet. Tekoäly päättää kullakin vuorolla liikkeensä siihen liitetyn Board-olion mukaan, käyttäen 8:n syvyistä alfa-beta-pruunausta. Tekoälyn ensimmäiset 2 siirtoa (kullekin pelaajalle) ovat satunnaisia, jotta vältyttäisiin joka pelissä samoilta lopputuloksilta.
* utilities-kansio sisältää ohjelman käyttämät tietorakenteet ja staattiset metodit, kuten ArrayListien järjestäminen. Javan vastaavien luokkien mukaan nimetyt luokat tekevät täsmälleen mitä niiltä odottaakin. Mielenkiintoisempia ovat ZobristHash ja TranspositionTable -luokat. ZobristHash kykenee tuottamaan Player 2d-taulukolle uniikin 64 bittisen BigInteger-avaimen, kun taas TranspositionTable pitäisi pystyä tallentamaan noiden avainten perusteella hajautustauluun BoardValues-olioita, joissa on tallennettu kunkin tekoälyn käsittelemän pelilaudan uniikin vaiheen lasketut arvot. TranspositionTable ei tosin vielä ole kytketty tekoälyyn, mutta sen pitäisi testien perusteella olla toiminnallisuudeltaan valmis.
* App-luokan ainoa tehtävä on käynnistää ohjelma.
* Runnable ja TextUI välittävät yhdessä tekoälyn ja pelaajan siirtoja Board-oliolle. Luokat ovat toiminnaltaan hyvin yksinkertaisia, joten niitä ei olla testattu.

Aika- ja tilavaativuudet
========================
* Aikavaativuus on tekoälyllä sama kuin alfa-beta-pruunauksella yleensä, eli suurinpiirtein O(b^(d/2)), missä b on keskimääräinen lasten määrä per solmu ja d on on haun syvyys.
* Tilavaativuus on tekoälylle O(d*a), missä d on rekursiopuun syvyys, ja a on ArrayListiin keskimäärin talletettujen siirtojen lukumäärä.
* Tietorakenteiden perusoperaatioiden vaativuudet vastaavat teoreettisia.
* ArrayListien järjestämiseen on käytetty mergesorttia, sen aikavaativuus O(nlogn).

Puutteet- ja parannusehdotukset
===============================
* Tekoälyni toimintaperiaate, eri pisteiden arvon arviointi ennalta määritettyjen vakioiden perusteella, on toimiva, mutta huonompi vaihtoehto eri posiotioiden arviointiin. Tästä lisää viimeisessä lähteiden linkissä, otsikon Position evaluation alla.
* Transpositiotaulut eivät vielä toimi.

Lähteet
=======
* http://chessprogramming.wikispaces.com/
* http://en.wikipedia.org/wiki/Computer_Othello
* http://radagast.se/othello/howto.html
