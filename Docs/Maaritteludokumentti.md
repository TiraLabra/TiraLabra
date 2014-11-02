# Labyrintti

Tarkoituksena on luoda ohjelma, joka osaa löytää lyhyimmän polun alkupisteestä loppupisteeseen ascii-merkeistä koostuvassa labyrintissa. Työssä käytetään A*-reitinhakualgoritmia.

Ohjelmointikielenä on java. 

Ohjelman syötteenä toimii itse labyrintti, ohjelma "tulostaa" tai visualisoi lyhimmän reitin (esim. listan koordinaatteja) sekä askelten määrän.

Tavoitteena on päästä optimiaikavaatimukseen, joka A*:llä on O((|V| + |E|) log |V|) heuristiikkaa lukuunottamatta. Tilavaatimuksen tavoite on O(n). 

# Lähteet
http://www.policyalmanac.org/Finnish/astar_fin.pdf
http://www.redblobgames.com/pathfinding/a-star/introduction.html
http://en.wikipedia.org/wiki/A*_search_algorithm
