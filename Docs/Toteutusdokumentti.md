Suunnitteludokumentti
====================
Yleisrakenne
------------
### Paketit ja luokat
Labyrintti
- Käynnistys: Käynnistää ohjelman
Labyrintti/osat
- Pohja: Kartta labyritistä
- Ruutu: Kartta koostuu ruuduista
Labyrintti/sovellus
- Etsijä: Laskee reitin A*:lla
- Minimikeko: Oma toteutus minimikeosta
Labyrintti/gui
- Käyttöliittymä: Avaa sovelluksen ikkunaan
- Napin kuuntelija: Käynnistää reitin etsimisen
- Piirtoalusta: Piirtää kartan ja reitin

### A*

talukko n riviä * m saraketta

aStar()
alustetaan minimikeko
while ei olla päästy maaliin
	valitse keosta ruutu r, jolle lähtöön[r] + maaliin[r] on pienin
	aseta r käydyksi true
	käy läpu r:n naapurit
	
alustus()
for kaikille ruuduille r
	maaliin[r] = arvio r-->m
	lähtöön[r] = ääretön
	reitti[r] = NIL
lähtöön[lähtö] = 0
lisää ruudut kekoon

käyLäpiNaapurit(r)
for kaikki r:n naapureille q
	if(lähtöön[q] > lähtöön[r]+arvo[q])
		lähtöön[q] = lähtöön[r]+arvo[q]
		reitti[q] = r
		
aStar()
alustetaan minimikeko			// O(m*n)
while ei olla päästy maaliin 	// ehdon tarkistaminen O(1)
								// koko while O(???)
	valitse keosta ruutu r, jolle lähtöön[r] + maaliin[r] on pienin
								// poll(keko) sisältää heapifyin, keon korkeus on O(log(n*m)),
								heapifyin aikavaat. O(log(n*m)), joten se on myös pollauksen 									aikavaatimus
	aseta r käydyksi true 		// O(1)
	käy läpi r:n naapurit		//
	

alustus()
for kaikille ruuduille r		// O(n*m)
	maaliin[r] = arvio r-->m	// O(1)
	lähtöön[r] = ääretön		// O(1)
	reitti[r] = NIL				// O(1)
lähtöön[lähtö] = 0				// O(1)
lisää ruudut kekoon				// O(n*m)


käyLäpiNaapurit(r)
for kaikki r:n naapureille q 	// O(4) = O(1)
	if(lähtöön[q] > lähtöön[r]+arvo[q]) // ehdon tarkistaminen O(1)
		lähtöön[q] = lähtöön[r]+arvo[q]	// O(1)
		reitti[q] = r					// O(1)
		
