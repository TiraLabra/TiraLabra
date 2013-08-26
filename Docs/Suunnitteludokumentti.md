Työn rakenne muodostuu kolmesta ryhmästä: 
Graafinen käyttöliittymä, polunetsintäalgoritmit ja tietorakenteet.

Polunetsintään käytetään A*-algoritmia, ja tietorakenteena toimiva
Jarjestysjono on eräänlainen ArrayListin ja PriorityQueuen hybridi.
Sen toimintaperiaate on sama kuin PriorityQueuella, mutta siitä voi
ArrayListin tavoin poimia tietyssä indeksissä sijaitsevan arvon.
Lisäksi ohjelmassa käytetään DFS-hakua vertailun vuoksi. DFS:llä on
edellämainittujen rakenteiden lisäksi myös Keko, joka ei varsinaisesti
ole "keko" termin varsinaisessa merkityksessä, mutta sen toimintaperiaate
on melko lähellä.

Ohjelman aikavaativuus on A* algoritmilla eksponentiaalinen. DFS-haun
aikavaativuus on laskelmien perusteella lineaarinen, mutta suurilla
syötteillä päädytään StackOverflow-virheeseen.

