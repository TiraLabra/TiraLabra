Määrittelydokumentti
Tira harjoitustyö alkukesä 2014
Mikko Översti

Djikstran ja A* algoritmit yhtenäisissä verkoissa. A* tarvii heuristiikan, solmuilla voisi olla koordinaatit ja heuristiikka on tavallinen etäisyyden laskeminen 2D. Myös verkon rakennus algoritmi pelkästä listasta koordinaattipisteitä tai solmujen välisten etäisyyksien omaavasta matriisista. Binäärikeko tarvitaan myös toteuttaa djikstraa ja a* varten.

Luokat:
util.keko
binäärikekototeutus

verkko.solmu
solmu jossa hajautustaulukko <solmu,paino>

verkko.KoordinoituSolmu extends solmu
solmu jossa on myös vektori koordinaatteja

verkko.heuristiikka <interface>

verkko.euklidinenetaisyys
sqrt((x0-x1)^2+(y0-y1)^2) ja sitä rataa...

verkko.taksimiehenetäisyys
|x0-x1|+|y0-y1|...

verkko.astar
A* haku koordinoiduissa solmuissa

labyrintti.prim
primin algoritmilla generoidaan kaksiulotteisia labyrinttejä joita voi sitten ratkaista A*:lla esim kulmasta kulmaan.

myös:
Kauppamiehen ongelma ant colony optimizationilla olisi jees jos aikaa jää.
Roguelike peli libjcsi grafiikoilla jossa voi mennä a*:lla haluttuun pisteeseen.
