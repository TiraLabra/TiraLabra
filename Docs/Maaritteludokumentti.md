Määrittelydokumentti: TiraLabra

"Hike"

Tavoitteena on tehdä ohjelma, joka etsii nopeimman reitin käyttäjän piirtämän kuvan ylänurkasta alanurkkaan. Kuvan eri värit ovat vaikeampia ylittää, esimerkiksi valkoisen pikselin ylittäminen maksaa yhden pisteen ja keltaisen ylittäminen 5. Ohjelma siis etsii keveimmän polun suuntaamattomassa painotetussa verkossa. Kuvan kooksi alustavasti tulee 800*500 pikseliä, käyttäjä tallentaa kuvan .png muodossa.

Ohjelmaan tulee ainakin Dijkstran algoritmi ja siihen tarvittavat tietorakenteet kuten stack. Ajattelin myös toteuttaa A*-algoritmin ja mahdollisesti tutkia Jump Point Searchia ja vertailla algoritmien käyttämää aikaa ja laskutoimituksia.

Dijkstran toteutan ilman prioriteettijonoa, eli tavoitteena toimia O(V^2) ajassa.
A* ja JPS pyrin toteuttamaan lähteissä annetussa ajassa, mutta en vielä tunne niitä tarpeeksi arvioidakseni paremmin.




http://en.wikipedia.org/wiki/Dijkstra's_algorithm
http://en.wikipedia.org/wiki/A*_search_algorithm
http://zerowidth.com/2013/05/05/jump-point-search-explained.html
