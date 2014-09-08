Ohjelman käyttö

.jar-tiedosto ja .bmp-tiedostot oltava samassa kansiossa.

Esimerkki:

D:\Hannun_tiedot\opinnot\2014_kesa\TiraHT\testing>java -jar TiraLabra-1.0-SNAPSHOT-hannuntemppu.jar eiEsteita00 7 10

Lahtotiedosto                 Kuvan koko          KESKIMAARAINEN ratkaisuaika (ms)                            PIENIN ratkaisuaika (ms)      polunpituus (aikayksikkoa)
                              vaaka     pysty     Astar/aika     Astar/n        Dijkstra/aika  Dijkstra/n     Astar          Dijkstra       Astar          Dijkstra
eiEsteita001.bmp              300       200       14             7              89             7              10             30             170            170
eiEsteita002.bmp              450       300       40             7              82             7              20             70             255            255
eiEsteita003.bmp              600       400       59             7              333            7              40             140            340            340
eiEsteita004.bmp              750       500       167            7              524            7              80             220            425            425
eiEsteita005.bmp              900       600       590            7              1109           7              100            380            510            510
eiEsteita006.bmp              1050      700       692            7              1569           7              298            760            595            595
eiEsteita007.bmp              1200      800       741            7              2551           7              437            1267           680            680

D:\Hannun_tiedot\opinnot\2014_kesa\TiraHT\testing>java -jar TiraLabra-1.0-SNAPSHOT-hannuntemppu.jar monimutkaisuus00 9
monimutkaisuus001.bmp 78 20 38 38
monimutkaisuus002.bmp 10 10 43 43
monimutkaisuus003.bmp 10 10 142 142
monimutkaisuus004.bmp 0 0 53 53
monimutkaisuus005.bmp 10 10 275 275
monimutkaisuus006.bmp 0 10 174 174
monimutkaisuus007.bmp 10 0 224 224
monimutkaisuus008.bmp 10 10 178 178
monimutkaisuus009.bmp 0 10 178 178

1. parametri: lähtötiedostojen nimen alkuosa
2. parametri: lähtötiedostojen lukumäärä JA lähtötiedostojen nimen loppuosa
3. parametri: iteraatiokierrosten lukumäärä

Tuloksissa Astar/n tarkoittaa kuinka monesta viimeisestä iteraatiosta KESKIMAARAINEN ratkaisuaika on laskettu (ensimmäiset karkeasti n. 20 % kierroksista ei vaikuta keskiarvoon).

Tulokset kuvina löytyvät tiedostoista, joissa lähtötiedoston nimeen lisätty ASTAR tai DIJKSTRA.

.jar-tiedosta ja testilähtötiedostoja kansiossa "TiraLabra/JarJaTestitiedostot".