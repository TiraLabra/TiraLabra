Projektissa luodaan ohjelma, joka etsii lyhyimmän reitin kahden pisteen välillä: 
tätä ongelmaa ratkaistaan A* (A-star) algoritmilla.
Ohjelmalle annetaan syötteeksi piste A ja piste B, joiden välille reitti lasketaan.
Tavoitteena on luoda mahdollisimman tila- ja aikapihi ohjelma.

Pohjana reitinlaskennalle toimii karteesinen ruudukko, jossa on erityyppisiä maastoja ja teitä.
Algoritmi pyrkii kiertämään esteet ja hyödyntämään helppokulkuista maastoa reitin suunnittelussa.

Ohjelman vaativin kohta on A*-haun toteuttava metodi, jonka tilavaatimus on lineaarisesti kasvava, ja aikavaatimus neliöllisesti kasvava.

Lähteet:
http://www.boost.org/doc/libs/1_44_0/libs/graph/doc/dag_shortest_paths.html
http://www.personal.kent.edu/~rmuhamma/Algorithms/MyAlgorithms/GraphAlgor/dijkstraAlgor.htm
http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
http://stackoverflow.com/ http://en.literateprograms.org/Dijkstra's_algorithm_%28Java%29 https://code.google.com/p/a-star-java/source/browse/AStar/src/aStar/AStar.java?r=7
