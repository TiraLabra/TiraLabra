# Määrittely #

Aiheenani on parhaimman polun etsiminen A* algoritmillä itse piirretyssä labyrintissa. Paras polku tarkoittaa lyhyintä mahdollista reittiä alku- ja loppupisteen välillä.


Ohjelma antaa tyhjän 800*800 pikselin suuruisen kuvan johon voi piirtää esteitä vapaati ja josta valitaan alku- ja loppupiste. Kun käyttäjän sitten aloittaa reitinhaun niin tästä kuvasta ohjelma laati taulukon jossa jokaista pikseliä edustaa solmu. Tästä taulukosta/verkosta algoritmi etsii parhaimman reitin käyttäjän määrittelemien alku- ja loppupisteiden välillä. Pikseleiden välillä voi liikkua diagonaalisesti. Tulosteena on visuaalinen representaatio (viiva) polun reitistä.


Ohjelmassa on avoinlista(openset) joka on toteutettu minimikeolla. Suljettulista(closedset) on yksinkertaisesti boolean arvo solmussa.



Lähteet:
http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
http://en.wikipedia.org/wiki/A*_search_algorithm

