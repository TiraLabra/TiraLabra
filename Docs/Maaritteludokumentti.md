Määrittelydokumentti
Tira harjoitustyö alkukesä 2014
Mikko Översti

A* algoritmi yhtenäisissä verkoissa. A* tarvii heuristiikan, solmuilla voisi olla koordinaatit ja heuristiikka on tavallinen etäisyyden laskeminen. Myös verkon rakennus algoritmi pelkästä listasta koordinaattipisteitä tai solmujen välisten etäisyyksien omaavasta matriisista. Binäärikeko tarvitaan myös toteuttaa a* varten. Recursive backtracking algoritmi generoi 2d labyrinttejä ratkottavaksi. Oma lista rakenne.

Luokat:

Keko
binäärikekototeutus, tilavaatimus O(n), lisäys pahimmillaan O(log n), poisto pahimmillaan O(log n)

Solmu
solmu jossa Listat naapurisolmuille ja painoille.

KoordinoituSolmu extends solmu
solmu jossa on myös taulukko koordinaatteja

Heuristiikka <interface>

Euklidinenetaisyys
sqrt((x0-x1)^2+(y0-y1)^2) ja sitä rataa...

Taksimiehenetäisyys
|x0-x1|+|y0-y1|...

Astar
A* haku koordinoiduissa solmuissa, tilavaatimus O(n), aikavaatimuksesta en tiedä 

Recursivebacktracker
tällä algoritmilla generoidaan kaksiulotteisia labyrinttejä joita voi sitten ratkaista A*:lla esim kulmasta kulmaan. Tilavaatimus O(n) aikavaatimus O(n).

Prim
Primin algoritmi generoi myös labyrinttejä

Lista
Lisäys O(1), haku indeksistä O(1), etsintä O(n).

Labyrintti2D
sisäinen 2d taulukko koordinoituja solmuja.


myös:
Kauppamiehen ongelma ant colony optimizationilla olisi jees jos aikaa jää.
Roguelike peli libjcsi grafiikoilla jossa voi mennä a*:lla haluttuun pisteeseen.
