Määrittelydokumentti: TiraLabra

"Hike"

Tavoitteena on tehdä ohjelma, joka etsii nopeimman reitin käyttäjän piirtämän kuvan ylänurkasta alanurkkaan. Kuvan eri värit ovat vaikeampia ylittää, esimerkiksi valkoisen pikselin ylittäminen maksaa yhden pisteen ja keltaisen ylittäminen 5. Ohjelma siis etsii keveimmän polun suuntaamattomassa painotetussa verkossa. Kuvan kooksi alustavasti tulee 800*500 pikseliä, käyttäjä tallentaa kuvan .png muodossa. Kuva tallennettaan src kansion Hike kansioon nimellä map1.png.

Ohjelmaan tulee ainakin Dijkstran algoritmi ja siihen tarvittavat tietorakenteet kuten stack. Ajattelin myös toteuttaa A*-algoritmin ja vertailla algoritmien käyttämää aikaa ja laskutoimituksia. Suunnittelin myös Jump Point Search algoritmin tutkimusta, mutta sain selville sen toimivan huonosti painotetussa verkossa.

Dijkstra toteutetaan minimikeolla. Minimikeon solmut tietävät myös oman indeksinsä, tietty solmu löydetään keosta tarvittaessa vakioajassa.

Käytettävät rakenteet:

Dijkstra:
Minimikeolla, tavoitteena aikavaatimus O( (E+V) log V). Tilavaativuus O(v).

A*: 
Aikavaatimus on pahimmassa tapauksessa sama kuin Dijkstrassa. Heuristiikaksi valitaan Manhattan ja Diagonal Distance.

Minimikeko:
Tarvitaan Dijkstran nopeuttamiseen. O(N log N).
Teen minimikekoon vakioaikaisen ominaisuuden, jolla keossa olevat verkon solmut tietävät oman indeksinsä keossa, jotta reitinhakualgoritmit pystyisivät nopeasti muuttamaan solmujen naapureiden etäisyyksiä. Tilavaativuus O(n).

Linkitetty lista:
Solmujen naapurit ovat talletettuina linkitettyihin listoihin. Hitaimmat operaatiot ovat luokka O(n). Tilavaativuus O(n).

Pino:
Yksinkertainen pino jota käytetään valmiin reitin tulostukseen. Tilavaativuus O(n).




http://en.wikipedia.org/wiki/Dijkstra's_algorithm
http://en.wikipedia.org/wiki/A*_search_algorithm
http://zerowidth.com/2013/05/05/jump-point-search-explained.html
