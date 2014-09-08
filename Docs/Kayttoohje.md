## Tiralabra / Hannu Honkanen, periodi 6/2014

## Käyttöohje

**Kansiot**

.jar-tiedosto ja testilähtötiedostoja kansiossa "TiraLabra/JarJaTestitiedostot".

.jar-tiedosto ja .bmp-tiedostot oltava samassa kansiossa.

**Lähtötietotiedostot**

Lähtötietotiedostot ovat .bmp-kuvatiedostoja.

Lähtöpiste vihreä pikseli RGB(0,255,0) (voi myös olla useita jolloin otetaan pikseli joka on eniten oikealla ja niistä se joka alimpana).

Maalipiste punainen pikseli RGB(255,0,0) (voi myös olla useita jolloin otetaan pikseli joka on eniten oikealla ja niistä se joka alimpana).

Esteet (aikakustannus integermax/10) mustia pikseleitä RGB(0,0,0).

Vaikeakulkuinen maasto (ruutuun siirtymisen aikakustannus 5 aikayksikköä) harmaita pikseleitä RGB(128,128,128).

Tavallinen maasto (ruutuun siirtymisen aikakustannus 1 aikayksikköä) valkoisia pikseleitä RGB(255,255,255).

Esimerkkitiedostoissa on vasemmassa laidassa erivärisiä alueita, joista myös voi yllä mainitut värit kopioida.

**.jar-tiedostolle annettavat 3 parametria**

1. parametri: lähtötiedostojen nimen alkuosa
2. parametri: lähtötiedostojen lukumäärä n, jolloin lähtötiedostojen nimien loppuosat ovat 1...n
3. parametri: iteraatiokierrosten lukumäärä


**Esimerkki1:**

>java -jar DijkstraAstar.jar eiEsteita00 7 10

Suoritetaan ohjelma lähtotiedostoille: eiEsteita001.bmp, eiEsteita002.bmp, eiEsteita003.bmp, eiEsteita004.bmp, eiEsteita005.bmp, eiEsteita006.bmp ja eiEsteita007.bmp

Iteraatiokierroksia 10.

**Esimerkki2:**

>java -jar DijkstraAstar.jar eiEsteita00 1 20

Suoritetaan ohjelma lähtotiedostolle eiEsteita001.bmp

Iteraatiokierroksia 20.

Eli ohjelman voi ajaa vain yhdelle lähtötiedostolle mutta sen nimen on loputtava merkkiin "1".

**Tulokset**

Tuloksissa Astar/n (ja Dijkstra/n) tarkoittaa kuinka monesta viimeisestä iteraatiosta KESKIMAARAINEN ratkaisuaika on laskettu (ensimmäiset karkeasti n. 20 % iteraatioista ei vaikuta keskiarvoon).

Tulokset kuvina löytyvät tiedostoista, joissa lähtötiedoston nimeen lisätty ASTAR tai DIJKSTRA.
