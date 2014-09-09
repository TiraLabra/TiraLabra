## Tiralabra / Hannu Honkanen, periodi 6/2014

## K�ytt�ohje

**Kansiot**

.jar-tiedosto ja testil�ht�tiedostoja kansiossa "TiraLabra/JarJaTestitiedostot".

.jar-tiedosto ja .bmp-tiedostot oltava samassa kansiossa.

**L�ht�tietotiedostot**

L�ht�tietotiedostot ovat .bmp-kuvatiedostoja.

L�ht�piste vihre� pikseli RGB(0,255,0) (voi my�s olla useita jolloin otetaan pikseli joka on eniten oikealla ja niist� se joka alimpana).

Maalipiste punainen pikseli RGB(255,0,0) (voi my�s olla useita jolloin otetaan pikseli joka on eniten oikealla ja niist� se joka alimpana).

Esteet (aikakustannus integermax/10) mustia pikseleit� RGB(0,0,0).

Vaikeakulkuinen maasto (ruutuun siirtymisen aikakustannus 5 aikayksikk��) harmaita pikseleit� RGB(128,128,128).

Tavallinen maasto (ruutuun siirtymisen aikakustannus 1 aikayksikk��) valkoisia pikseleit� RGB(255,255,255).

Esimerkkitiedostoissa on vasemmassa laidassa eriv�risi� alueita, joista my�s voi yll� mainitut v�rit kopioida.

**.jar-tiedostolle annettavat 3 parametria**

1. parametri: l�ht�tiedostojen nimen alkuosa
2. parametri: l�ht�tiedostojen lukum��r� n, jolloin l�ht�tiedostojen nimien loppuosat ovat 1...n
3. parametri: iteraatiokierrosten lukum��r�


**Esimerkki1:**

>java -jar DijkstraAstar.jar eiEsteita00 7 10

Suoritetaan ohjelma l�htotiedostoille: eiEsteita001.bmp, eiEsteita002.bmp, eiEsteita003.bmp, eiEsteita004.bmp, eiEsteita005.bmp, eiEsteita006.bmp ja eiEsteita007.bmp

Iteraatiokierroksia 10.

**Esimerkki2:**

>java -jar DijkstraAstar.jar eiEsteita00 1 20

Suoritetaan ohjelma l�htotiedostolle eiEsteita001.bmp

Iteraatiokierroksia 20.

Eli ohjelman voi ajaa vain yhdelle l�ht�tiedostolle mutta sen nimen on loputtava merkkiin "1".

**Tulokset**

Tuloksissa Astar/n (ja Dijkstra/n) tarkoittaa kuinka monesta viimeisest� iteraatiosta KESKIMAARAINEN ratkaisuaika on laskettu (ensimm�iset karkeasti n. 20 % iteraatioista ei vaikuta keskiarvoon).

Tulokset kuvina l�ytyv�t tiedostoista, joissa l�ht�tiedoston nimeen lis�tty ASTAR tai DIJKSTRA. TUloskuvissa paikat/solmut joissa on k�yty, on merkitty KELTAISELLA jos paikan v�ri oli alunperin valkoinen ja ORANSSILLA jos paikan v�ri oli alunperin harmaa. Nopein reitti on merkitty SINISILL� pikseleill�.
