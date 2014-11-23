Toteutusdokumentti
==================

Ohjelman ytimessä ovat AStarSearch-luokka, jolle annetaan kaksiuloitteinen kokonaislukutaulukko, joka kuvastaa karttaa, josta ohjelma etsii reittejä. Taulukossa olevat luvut (ykkösestä ylöspäin) kuvastavat aikaa, joka kuluu kartan kohtaan siirtyessä. Kartalla ei voi liikkua diagonaalisesti, ainoastaan suoraan ylös- tai alaspäin tai vasemmalle tai oikealle. 

AStarSearch muodostaa haun edetessä taulukosta Nodeja, jotka kuvastavat yksittäisiä pisteitä kartalla. Nodet pitävät sisällään sijainnin lisäksi myös haulle tärkeää muuta tietoa, kuten f- ja g-arvot.

Haku järjestää Nodeja OrderedStack-luokan mukaan. OrderedStackissa Nodeista muodostetaan linkitetty lista, joka on järjestetty Nodejen f-arvon mukaan. Tällöin Nodet saa helposti popattua ulos stackista kun haku sitä vaatii.

Ohjelma käynnistetään App-luokasta. Tällä hetkellä ohjelma luo automaattisesti 30x30-kokoisen kartan, jonka ruuduilla painoarvot vaihtelevat välillä 1-15. 