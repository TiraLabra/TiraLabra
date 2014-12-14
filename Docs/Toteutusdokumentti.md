# Ohjelman toteutus

## Ohjelman rakenne

Ohjelman luokat ja rakenteet on jaettu eri header tiedostoihin, joita kutsutaan main.cpp
ohjelmasta. Koodi on jaoteltu seuraavanlaisesti:

- algorithms.hpp: tarvittavien algoritmien toteutuksia, eli lähinnä minmax
- structures.hpp: itse toteutetut tietorakenteet kuten linkitetty lista ja puu rakenne
- ai.hpp: tekoälyn päätöksentekoon liittyvät luokat ja rakenteet
- chess.hpp: shakkipelin pelitilanteen esittämiseen ja sääntöjen tarkistamiseen vaadittavat luokat
- main.cpp: pääohjelma ja yksikkötestit

## Aika ja tilavaativuudet

Minimax algoritmin aikavaativuus on O(b^m), jossa b on solmun lapsisolmujen määrä
(haarautumiskerroin) ja m haun syvyys. Tilavaativuus on O(bm). Alfa-beta karsinnalla
aikavaativuus voi parhaassa tapauksessa olla O( b^(m/2) ). Minimax hakuun kuluva aika
osoittautui hyvin pieneksi verrattuna pelipuun luomiseen kuluvaan aikaan.

Tietorakenteissa linkitettyyn listaan lisääminen on O(1) sillä listarakenne ylläpitää
osoitinta listan viimeiseen olioon ja yksinkertaisesti liittää lisättävän olion
siihen. Puurakenne käyttää sisäisesti linkitettyä listaa, joten tähän lisääminen pitäisi
myös olla O(1).

## Puutteet ja parannusideat

Tällä hetkellä tekoäly luo uuden pelipuun alusta jokaisen siirron jälkeen. Olisi
huomattavasti tehokkaampaa säilyttää puurakenne valitun siirron osalta, ja jatkaa
laskentaa sen perusteella seuraavalla vuorolla. Tekoäly voisi myöskin suorittaa laskentaa
jatkuvasti, myös sillä aikaa kun pelaaja miettii omaa siirtoaan.

Pelitilanteen arvotusfunktio on myös melko yksinkertainen. Esimerkiksi lisäämällä erilaisi
tarkistuksia kuten se että saatiinko vastapuolen nappuloita kaapattua jne parantaisi
tekoälyä, ja tätä tietoa voitaisiin myöskin käyttää alfa-beta karsinnassa hyväksi.

Shakin sääntöjen oikeaoppinen tarkistus osoittautui melkoiseksi haasteeksi, ja yhä on
muutamia mahdollisia siirtoja joita ohjelma ei näytä löytävän. Puuttuvia siirtoja on tosin
hyvin vähän, ja tekoäly kykenee kyllä pelaamaan hyvin, mutta kaikki siirrot olisi tärkeää
löytää.

## Lähteet

- Tietorakenteet ja algoritmit -kurssi
- http://en.wikipedia.org/wiki/Minimax
- http://en.wikipedia.org/wiki/Alpha-beta_pruning
- http://courses.cs.washington.edu/courses/cse573/12au/slides/04-minmax.pdf
