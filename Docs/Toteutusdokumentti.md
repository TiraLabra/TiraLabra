# Ohjelman toteutus

## Ohjelman rakenne

Ohjelman luokat ja rakenteet on jaettu eri header tiedostoihin, joita kutsutaan main.cpp
ohjelmasta. Koodi on jaoteltu seuraavanlaisesti:

- algorithms.hpp: tarvittavien algoritmien toteutuksia, eli l�hinn� minmax
- structures.hpp: itse toteutetut tietorakenteet kuten linkitetty lista ja puu rakenne
- ai.hpp: teko�lyn p��t�ksentekoon liittyv�t luokat ja rakenteet
- chess.hpp: shakkipelin pelitilanteen esitt�miseen ja s��nt�jen tarkistamiseen vaadittavat luokat
- main.cpp: p��ohjelma ja yksikk�testit

## Aika ja tilavaativuudet

Minimax algoritmin aikavaativuus on O(b^m), jossa b on solmun lapsisolmujen m��r�
(haarautumiskerroin) ja m haun syvyys. Tilavaativuus on O(bm). Alfa-beta karsinnalla
aikavaativuus voi parhaassa tapauksessa olla O( b^(m/2) ). Minimax hakuun kuluva aika
osoittautui hyvin pieneksi verrattuna pelipuun luomiseen kuluvaan aikaan.

Tietorakenteissa linkitettyyn listaan lis��minen on O(1) sill� listarakenne yll�pit��
osoitinta listan viimeiseen olioon ja yksinkertaisesti liitt�� lis�tt�v�n olion
siihen. Puurakenne k�ytt�� sis�isesti linkitetty� listaa, joten t�h�n lis��minen pit�isi
my�s olla O(1).

## Puutteet ja parannusideat

T�ll� hetkell� teko�ly luo uuden pelipuun alusta jokaisen siirron j�lkeen. Olisi
huomattavasti tehokkaampaa s�ilytt�� puurakenne valitun siirron osalta, ja jatkaa
laskentaa sen perusteella seuraavalla vuorolla. Teko�ly voisi my�skin suorittaa laskentaa
jatkuvasti, my�s sill� aikaa kun pelaaja miettii omaa siirtoaan.

Pelitilanteen arvotusfunktio on my�s melko yksinkertainen. Esimerkiksi lis��m�ll� erilaisi
tarkistuksia kuten se ett� saatiinko vastapuolen nappuloita kaapattua jne parantaisi
teko�ly�, ja t�t� tietoa voitaisiin my�skin k�ytt�� alfa-beta karsinnassa hyv�ksi.

Shakin s��nt�jen oikeaoppinen tarkistus osoittautui melkoiseksi haasteeksi, ja yh� on
muutamia mahdollisia siirtoja joita ohjelma ei n�yt� l�yt�v�n. Puuttuvia siirtoja on tosin
hyvin v�h�n, ja teko�ly kykenee kyll� pelaamaan hyvin, mutta kaikki siirrot olisi t�rke��
l�yt��.

## L�hteet

- Tietorakenteet ja algoritmit -kurssi
- http://en.wikipedia.org/wiki/Minimax
- http://en.wikipedia.org/wiki/Alpha-beta_pruning
- http://courses.cs.washington.edu/courses/cse573/12au/slides/04-minmax.pdf
