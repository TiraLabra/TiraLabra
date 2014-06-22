#Käyttöohje#

Tekstikäyttöliittymää käytettäessä ohjelmalle syötetään polku lähdetiedostoon ja kohdetiedostoon. Tiedostopolut annetaan kokonaisina. Jos kohdetiedosto on jo olemassa, kirjoitetaan päälle. Muuten luodaan uusi tiedosto. Ohjelma ei tallenna tiedostoon tietoa käytetystä algoritmista tai asetuksista, joten kannattaa käyttää kuvaavaa tiedostonimeä.

Pakkausnopeuteen ja -suhteeseen vaikuttaa käytetty ikkunan tai hakemiston koko. Näitä voi muuttaa main-metodin muuttujista offsetBits(LZ77:n ikkunan koko), lengthBits(LZ77:n suurin sallittu kopioitavan merkkijonon pituus) ja entryBits(LZW:n hakemiston koko). Muuttujat ilmaisevat kunkin luvun koodaamiseen käytettävien bittien määrä, siis varsinainen koko on kahden potenssi muuttujasta. Vakiona nämä luvut ovat 16, 4, ja 14. Huom! testatut arvot ovat 16,4 ja 14 sekä 12, 4 ja 12. Muilla voi ilmetä ongelmia. 

