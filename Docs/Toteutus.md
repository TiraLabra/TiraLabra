Toteutusdokumentti
Tira harjoitustyö alkukesä 2014
Mikko Översti

Yleisrakenne:

Paketti: labyrintti
-Labyrintitin
	Rajapinta labyrintitysalgoritmeille
-Labyrintti2D
	Käytännössä taulukko Solu olioita jotaka voidaan sitten labyrintittää
-Prim
	Primin algoritmi, labyrintitin
-RecursiveBacktracker
	Takaisinpalaava syvyyssuuntainen haku, labyrintitin

Paketti: verkko
-Verkko
	Maini, luo CLI
-Solmu
	Verkon solmu jossa sisäisenä tietona koordinaatit

Paketti: polunetsinta
-Astar
	A* haku kahden Solmu-olion välillä käyttäen jotakin Heuristiikka-olion tarjoamaa heuristiikkaa
-AstarKekoEntry
	Liittää Solmu-olioon liukuluvun jonka perusteella PriorityComparator järjestää
-Heuristiikka
	Rajapinta heuristiikalle
-NollaHeuristiikka
	Aina 0
-PriorityComparator
	Comparator AstarKekoEntry-olioille
-TaksimiehenEtaisyys
	abs(x0-x1)+abs(y0-y1) ja sitä rataa. 

Paketti: util
-Taulukko
	Utility luokka joka osaa taulukko-operaatioita
-Keko
	Binäärikeko
-Lista
	Oma ArrayList tyyppinen tietorakenne

Paketti: cli
-CLI
	Kyselee Labyrintin koon ja käytettävät heuristiikat ja labyrintittimen.
