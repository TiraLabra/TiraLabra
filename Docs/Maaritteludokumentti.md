# Shakkiteko�ly

## Ty�n kuvaus

Ty�n tavoitteena on toteuttaa yksinkertainen tekstipohjainen shakkipeli sek�
sit� pelaava teko�ly. Ohjelmointikielen� k�ytet��n C++ kielt�.

## Tarvittavat tietorakenteet ja algoritmit

Teko�ly laskee jokaisella vuorolla mahdollisten eri siirtojen tuottamat
pelitilanteet, ja muodostaa niist� puurakenteen. Puun kullekin solmulle
lasketaan arvo pisteytysfunktion avulla. Teko�ly etsii t�m�n j�lkeen parhaan
siirron puusta minimax-algoritmin avulla. [1] Lis�ksi hakua voidaan tehostaa
alfa-beta karsinnalla. [2]

## Ohjelman sy�tteet

Ohjelma toteutetaan tekstipohjaisena shakkipelin�. Ohjelma tulostaa shakkilaudan
tilanteen, ja kysyy pelaajalta siirtoa. Pelaaja sy�tt�� siirron koordinaatit
(esim "a1 b1") jonka j�lkeen teko�ly laskee siirtonsa.

## Aika- ja tilavaativuudet

Minimax algoritmin aikavaativuus on O(b^m), jossa b on solmun lapsisolmujen
m��r� (haarautumiskerroin) ja m haun syvyys. Tilavaativuus on O(bm). Alfa-beta
karsinnalla aikavaativuus voi parhaassa tapauksessa olla O( b^(m/2) ). [3]

## L�hteet

[1] http://en.wikipedia.org/wiki/Minimax

[2] http://en.wikipedia.org/wiki/Alpha-beta_pruning

[3] http://courses.cs.washington.edu/courses/cse573/12au/slides/04-minmax.pdf
