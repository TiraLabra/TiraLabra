Toteutusdokumentti
==================

Ohjelman ytimessä ovat AStarSearch-luokka, jolle annetaan kaksiuloitteinen kokonaislukutaulukko, joka kuvastaa karttaa, josta ohjelma etsii reittejä. Taulukossa olevat luvut (ykkösestä ylöspäin) kuvastavat aikaa, joka kuluu kartan kohtaan siirtyessä. Kartalla ei voi liikkua diagonaalisesti, ainoastaan suoraan ylös- tai alaspäin tai vasemmalle tai oikealle. 

AStarSearch muodostaa haun edetessä taulukosta Nodeja, jotka kuvastavat yksittäisiä pisteitä kartalla. Nodet pitävät sisällään sijainnin lisäksi myös haulle tärkeää muuta tietoa, kuten f- ja g-arvot ja tiedon siitä, minkä Noden kautta kyseiseen Nodeen on reitillä saavuttu.

Haku järjestää Nodeja ClosedSet- ja MinHeap-luokkiin. ClosedSet (haussa jo käydyt Nodet) on toteutettu yksinkertaisesti kaksiulotteisena taulukkona, johon merkitään, jos haku on jo käynyt ko. karttapisteessä. Tämä mahdollistaa O(1) aikaisen lisäämisen ja hakemisen ClosedSetiin. Haussa vielä käymättömät Nodet ovat MinHeap-luokassa, eli minimikeossa. Tämä mahdollistaa keosta pienimmän f-arvon Noden hakemisen ajassa O(1) ja lisäämisen ajassa O(log n). Periaatteessa MinHeap on siis PriorityQueue.

Ohjelman tilavaativuus noudattaa kartan kokoa. Pahimmassa tapauksessa jokaiselle kartan alkiolle tulee luoda oma Nodensa, eli tilavaativuus on käytännössä O(n).

Ohjelma käynnistetään App-luokasta. Tällä hetkellä ohjelma luo automaattisesti 30x30-kokoisen kartan, jonka ruuduilla painoarvot vaihtelevat välillä 1-10.

Parannusehdotuksia: Ohjelmaan voisi mielellään esim. lisätä omia karttojaan, tällä hetkellä ohjelma vain satunnaisgeneroi niitä. Lisäksi ohjelman tulostus tökkii, jos kartan x- tai y-arvo on yli 100. Kirjoitushetkellä ohjelma ei myös toimi täysin optimaalisesti, vaan esim. heuristiikan voisi laskea mielekkäämminkin.

Lähteet:
Tietorakenteet ja algoritmit -kurssin materiaalit