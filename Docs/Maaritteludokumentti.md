# Labyrintti

Tarkoituksena on luoda ohjelma, joka osaa löytää lyhyimmän polun alkupisteestä loppupisteeseen ascii-merkeistä koostuvassa labyrintissa. Työssä käytetään A*-reitinhakualgoritmia.

Ohjelmointikielenä on java. 

Ohjelman syötteenä toimii itse labyrintti, ohjelma "tulostaa" tai visualisoi lyhimmän reitin (esim. listan koordinaatteja) sekä askelten määrän.

Tavoitteena on päästä optimiaikavaatimukseen, joka A*:llä on O((|V| + |E|) log |V|) heuristiikkaa lukuunottamatta. Tilavaatimuksen tavoite on O(n). 

# Tietorakenteet

Labyrintin teossa tullaan todennäköisesti käyttämään seuraavia tietorakenteita:

* Pino: Tila- ja aikavaativuus ovat molemmat O(1) eli vakio
* Jono: Tila- ja aikavaativuus ovat molemmat O(1)
* Keko: Aikavaativuus on useimmiten O(log n), tilavaativuus voi olla O(log n) tai O(1) riippuen käytetäänkö operaatioissa rekursiota.



# Lähteet
http://www.policyalmanac.org/Finnish/astar_fin.pdf
http://www.redblobgames.com/pathfinding/a-star/introduction.html
http://en.wikipedia.org/wiki/A*_search_algorithm
