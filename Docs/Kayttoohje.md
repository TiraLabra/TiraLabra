#K�ytt�ohje#

Tekstik�ytt�liittym�� k�ytett�ess� ohjelmalle sy�tet��n polku l�hdetiedostoon ja kohdetiedostoon. Tiedostopolut annetaan kokonaisina. Jos kohdetiedosto on jo olemassa, kirjoitetaan p��lle. Muuten luodaan uusi tiedosto. Ohjelma ei tallenna tiedostoon tietoa k�ytetyst� algoritmista tai asetuksista, joten kannattaa k�ytt�� kuvaavaa tiedostonime�.

Pakkausnopeuteen ja -suhteeseen vaikuttaa k�ytetty ikkunan tai hakemiston koko. N�it� voi muuttaa main-metodin muuttujista offsetBits(LZ77:n ikkunan koko), lengthBits(LZ77:n suurin sallittu kopioitavan merkkijonon pituus) ja entryBits(LZW:n hakemiston koko). Muuttujat ilmaisevat kunkin luvun koodaamiseen k�ytett�vien bittien m��r�, siis varsinainen koko on kahden potenssi muuttujasta. Vakiona n�m� luvut ovat 16, 4, ja 14. Huom! testatut arvot ovat 16,4 ja 14 sek� 12, 4 ja 12. Muilla voi ilmet� ongelmia. 

