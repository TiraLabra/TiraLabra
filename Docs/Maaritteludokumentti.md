=Tietorakenteiden harjoitustyö - Määrittelydokumentti=

=Perustiedot=

Tietorakenteiden harjoitustyöni käsittelee erilaisia polunetsintäalgoritmeja reittien löytämiseen ruutupohjaisella kartalla. Tavoitteenani on tehdä ohjelma, joka ohjaa roguelike-pelien henkeen 'hirviöitä' pelaajahahmoa kohti luolastossa tai labyrintissa.

Ohjelman käyttäjä voi valita hirviöiden käyttämän algoritmin ja hirviöiden ja pelaajahahmon toimintalogiikan useista eri vaihtoehdoista. Näiden tietojen pohjalta ohjelma 'pelaa' pelin ja ohjaa hirviöt kohteeseensa, jonka jälkeen käyttäjä saa tiedot algoritmin suorituksesta. Vaihtoehtoisesti käyttäjä voi seurata peliä askel askeleelta ja saada jokaisen liikkeen jälkeen algoritmin tiedot siltä vuorolta.

Ohjelmassa voi valita, pysyykö pelaajahahmo paikallaan, liikkuuko se itsenäisesti (ja millä tavoin), vai ohjaako hän sitä itse. Hirviöiden osalta on mahdollista valita, pyrkivätkö ne suorinta tietä pelaajan luo vai esim. katkaisemaan reitin suunnassa, johon kohde on liikkunut.

Algoritmien suoritustietoihin sisältyy tutkittujen ruutujen määrä, laskenta-aika, lyhimmän löydetyn polun pituus ja hahmon saavuttamiseen tarvitut askeleet.

=Algoritmit=

Pyrin toteuttamaan työssä muutamia tyypillisiä, toisistaan toiminta-ajatukseltaan ja tehokkuudeltaan poikkeavia polunetsintäalgoritmeja. Tavoitteena on valita algoritmit siten, että niiden erilaiset vahvuudet ja heikkoudet tulisivat ilmi vertailussa ja että otos edustaisi useita erilaisia polunetsintätapoja.

Lopullinen algoritmivalikoima riippuu siitä, kuinka monta niistä ehdin lopulta toteuttaa aikarajan puitteissa. Lista mahdollisesti toteutettavista algoritmeista:
- Breadth-first search
- Best-first search
- Bellman-Ford
- A*
- Jump-point search

=Aika- ja tilavaativuudet=

Eri polunetsintäalgoritmien aika- ja tilavaativuudet poikkeavat suuresti toisistaan. Pyrin toteuttamaan valitsemani algoritmit mahdollisimman tehokkaina versioina niiden tyypillisissä aika- ja tilavaatimusrajoissa. Tavoitteena on myös toteuttaa ainakin A*, jonka pahimman tapauksen aikavaatimus O(lyhimmän polun pituus²) on varsin nopea.
