# Määrittely #

Aiheenani on parhaimman polun etsiminen matriisissa. Paras polku määritellään reitiksi alku- ja loppupisteen välillä, jossa polulla olevien lukujen summa on mahdollisimman pieni. Ohjelma toteutetaan javalla.


Polun etsimiseen käytän A* algoritmia ja A* toteuttamista varten tarvitsen prioriteettijonon.


Ohjelman syötteenä on tarkoitus olla jonkinlainen matriisi, tai jos aika ja motivaatio riittää niin kuva josta tehdään matriisi, jonka arvot määräytyvät pikseleiden tummuuden perusteella. Tästä matriisista ohjelma sitten etsii parhaimman reitin käyttäjän määrittelemien alku- ja loppupisteiden välillä. Tulosteena olisi visuaalinen representaatio polun reitistä.

Tavoite aikavaativuus: O(n log n)

Tavoite tilavaativuus: O(n)

Lähteet:
http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
http://en.wikipedia.org/wiki/A*_search_algorithm