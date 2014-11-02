# Shakkitekoäly

## Työn kuvaus

Työn tavoitteena on toteuttaa yksinkertainen tekstipohjainen shakkipeli sekä
sitä pelaava tekoäly. Ohjelmointikielenä käytetään C++ kieltä.

## Tarvittavat tietorakenteet ja algoritmit

Tekoäly laskee jokaisella vuorolla mahdollisten eri siirtojen tuottamat
pelitilanteet, ja muodostaa niistä puurakenteen. Puun kullekin solmulle
lasketaan arvo pisteytysfunktion avulla. Tekoäly etsii tämän jälkeen parhaan
siirron puusta minimax-algoritmin avulla. [1] Lisäksi hakua voidaan tehostaa
alfa-beta karsinnalla. [2]

## Ohjelman syötteet

Ohjelma toteutetaan tekstipohjaisena shakkipelinä. Ohjelma tulostaa shakkilaudan
tilanteen, ja kysyy pelaajalta siirtoa. Pelaaja syöttää siirron koordinaatit
(esim "a1 b1") jonka jälkeen tekoäly laskee siirtonsa.

## Aika- ja tilavaativuudet

Minimax algoritmin aikavaativuus on O(b^m), jossa b on solmun lapsisolmujen
määrä (haarautumiskerroin) ja m haun syvyys. Tilavaativuus on O(bm). Alfa-beta
karsinnalla aikavaativuus voi parhaassa tapauksessa olla O( b^(m/2) ). [3]

## Lähteet

[1] http://en.wikipedia.org/wiki/Minimax

[2] http://en.wikipedia.org/wiki/Alpha-beta_pruning

[3] http://courses.cs.washington.edu/courses/cse573/12au/slides/04-minmax.pdf
