Toteutusdokumentti
==================

Ohjelman yleisrakenne
---------------------

Paketit ja luokat:

  - ```datastructures``` sisältää sovelluksen tietorakenteet
    - PriorityQueue	on tietorakenne, jota A* käyttää.
    - AbstractHeap ja sen aliluokat MaxHeap	ja MinHeap ovat kekoja ja Valuable on keon sisältämien olioden rajapinta.
    - List on linkitetty lista.
    - ListIterator on listan iteraattori.
    - Set on juokko.


  -  ```algorithm``` sisältää sovelluksen ydin-algoritmin A* ja
        siihen liittyvät tietorakenteet
    - Graph on verkon rajapinta.
    - Heuristic	on heurestiikka funktiota toteuttavan luokan rajapinta.
    - Search on etsintää toteuttavan luokan rajapinta.
    - State	on solmun tietoja sisältävän luokan rajapinta.
    - AStarSearch on A*-algoritmia toteuttavan luokan rajapinta.
    - Node on verkon solmu


  -  ```gui``` sisältää sovelluksen guin.
    - Window on sovelluksen pääikkuna.
    - GraphDrawer on verkkoa piirtävän luokan rajapinta.


  -  ```maze``` sisältää sokkelon.
    - Maze on sokkeloa toteuttavan luokan rajapinta.
    - AbstractMaze on sokkeloiden yliluokka.
    - SimpleMaze on sokkelo, jossa ei ole esteitä ja jonka painot ovat yksi.
    - ArrayMaze on taulukkona toteutttu sokkelo, jossa voi olla esteitä. Sen ruutuihin on mahdollista määritellä painot.
    - MazeNode on sokkelon ruutu.


  -  ```io``` sisältää sokkeloita lukevat ja kirjoittavat luokat.
    - Parser AsciiParser:in ja AsciiWithTabsParser:in yliluokka.
    - AsciiParser parseraa ascii merkkijonon taulukoksi.
    - AsciiWithTabsParser parseraa merkkijonon taulukoksi.
    - FileParser parseraa ascii tiedoston taulukoksi.
    - MazePrinter tulostaa sokkelon.


Saavutetut aika- ja tilavaativuudet
-----------------------------------
###A*-algoritmi

```
OPEN = priority queue containing START
CLOSED = empty set
while lowest rank in OPEN is not the GOAL:
  current = remove lowest rank item from OPEN
  add current to CLOSED
  for successor of current:
    cost = g(current) + movementcost(current, successor)
    if successor in OPEN and cost less than g(successor):
      remove successor from OPEN, because new path is better
    if successor in CLOSED and cost less than g(successor): **
      remove successor from CLOSED
    if successor not in OPEN and neighbor not in CLOSED:
      set g(successor) to cost
      add neighbor to OPEN
      set priority queue rank to g(successor) + h(successor)
      successor.parent = current

reconstruct reverse path from goal to start
by following parent pointers
```

Algoritmin aikavaativuus riippuu käytetystä heuristiikkafunktiosta.
Paras tapaus on O(|E| + |V|). Jos olettaa, että heuristiikkafunktio on 
laskettavissa vakioajassa, niin pahin tapaus on O((|E| + |V|) log |V|).

Algoritmin tilavaativuus riippuu käytetystä heuristiikkafunktiosta.
Paras tapaus on O(|E| + |V|). Jos olettaa, että heuristiikkafunktio on 
laskettavissa vakioajassa, niin pahin tapaus on O((|E| + |V|) log |V|).

##Työn parannusehdotukset
 - Sovelluksen käyttöliittymä kaipaa parantamista.
 - Sovellukseen voisi lisätä mahdollisuus editoida ja tallentaa sokkeloita.


##Lähteet
- [A*-algoritmi](http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html)
- [Tietorakenteet ja algoritmit](http://www.cs.helsinki.fi/courses/58131/2014/k/k/1)
