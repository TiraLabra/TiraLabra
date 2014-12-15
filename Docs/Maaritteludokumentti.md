## Sovelluksen käyttö

Tavoitteena on tuottaa komentoriviltä käytettävä tiedostojen pakkausohjelma.
Sovellukselle annetaan käynnistysparametriksi "pakkaa" tai "pura" sekä tiedoston
polku ja sovellus käyttäytyy tämän mukaan. Pakattu tai purettu tiedosto
tallennetaan sovelluksen ajokansioon.

## Sovelluksen toiminta

Tiedosto pakataan käyttäen Huffman-pakkausta (http://en.wikipedia.org/wiki/Huffman_coding).
Hyvin paljon yksinkertaistettuna: Tiedosto luetaan tavuina ja jokaiselle erilaiselle tavulle
luodaan uusi bittijonotunniste siten, että yleisimmillä tavuilla on lyhyemmät tunnisteet kuin
harvemmin käytettävillä.

## Tietorakenteet

Apuna käytetään Huffman-binääripuuta sekä kekoa. Käytettävät tavut tulee myös järjestää
yleisyyden mukaan.

## Aikavaatimukset

Huffmanin puun rakentamiseen kuluu aikaa O(n log n), missä n on eri tavujen lukumäärä.
Tiedoston pakkaukseen ja purkuun kuluu O(m log n), missä n on eri tavujen lukumäärä ja
m tavujen kokonaismäärä tiedostossa. Koska tavuja on maksimissaan 256, on siis kokonaisaikavaativuus O(m).
