## Sovelluksen k‰yttˆ

Tavoitteena on tuottaa komentorivilt‰ k‰ytett‰v‰ tiedostojen pakkausohjelma.
Sovellukselle annetaan k‰ynnistysparametriksi -pakkaa tai -pura sek‰ tiedoston
polku ja sovellus k‰ytt‰ytyy t‰m‰n mukaan. Pakattu tai purettu tiedosto
tallennetaan sovelluksen ajokansioon.

## Sovelluksen toiminta

Tiedosto pakataan k‰ytt‰en Huffman-pakkausta (http://en.wikipedia.org/wiki/Huffman_coding).
Hyvin paljon kksinkertaistettuna: Tiedosto luetaan tavuina ja jokaiselle erilaiselle tavulle
luodaan uusi bittijonotunniste site, ett‰ yleisimmill‰ tavuilla on lyhyemm‰t tunnisteet kuin
harvemmin k‰ytett‰vill‰.

## Tietorakenteet

Apuna k‰ytet‰‰n Huffman-bin‰‰ripuuta sek‰ kekoa. K‰ytett‰v‰t tavut tulee myˆs j‰rjest‰‰
yleisyyden mukaan.

## Aikavaatimukset

Huffmanin puun rakentamiseen kuluu aikaa O(n log n), miss‰ n on eri tavujen lukum‰‰r‰.
Tiedoston pakkaukseen ja purkuun kuluu O(m log n), miss‰ n on eri tavujen lukum‰‰r‰ ja
m tavujen kokonaism‰‰r‰ tiedostossa.