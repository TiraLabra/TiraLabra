# Määrittely #

Aiheenani on parhaimman polun etsiminen kuvassa. Paras polku lyhyimmäksi reitiksi alku- ja loppupisteen välillä. Ohjelma toteutetaan javalla.


Polun etsimiseen käytän A* algoritmia ja A* toteuttamista varten tarvitsen prioriteettijonon.


Ohjelman syötteenä on tarkoitus olla tyhjä kuva johon käyttäjä voi piirtää esteitä, tai jos aika ja motivaatio riittää niin mikä tahansa kuva josta etsitään vähiten tummia pikseleitä ylittävä reitti. Tästä kuvasta ohjelma sitten etsii parhaimman reitin käyttäjän määrittelemien alku- ja loppupisteiden välillä. Tulosteena olisi visuaalinen representaatio polun reitistä.

Tavoite aikavaativuus: O(n log n)

Tavoite tilavaativuus: O(n)

Lähteet:
http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
http://en.wikipedia.org/wiki/A*_search_algorithm