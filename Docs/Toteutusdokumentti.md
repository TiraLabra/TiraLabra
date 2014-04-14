Toteutusdokumentti
====================
Yleisrakenne
------------
### Paketit ja luokat
Labyrintti
- Käynnistys: Käynnistää ohjelman

Labyrintti/osat
- Pohja: Kartta labyrintistä
- Ruutu: Kartta koostuu ruuduista

Labyrintti/sovellus
- Etsijä: Laskee reitin A*:lla
- Minimikeko: Oma toteutus minimikeosta

Labyrintti/gui
- Käyttöliittymä: Avaa sovelluksen ikkunaan
- Napin kuuntelija: Käynnistää reitin etsimisen
- Piirtoalusta: Piirtää kartan ja reitin

### A*

talukko n riviä * m saraketta, m*n = V solmua, E kpl kaaria

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

###Aikavaativuus

aStarin aikavaativuus kaiken kaikkiaan on O(V log V + E*V). Ajallisesti ei päästä määrittelydokumentin tavoitteeseen O((V + E) log V), koska naapureita tarkastettaessa keon päivitys vie O(V) eikä O(log V).
Tavoiteltuun aikavaativuuteen päästäisiin ainakin, jos solmut olisi tallennettu keossa HashMappiin 1-ulotteisen taulukon sijaan.
		
	aStar()
		alustetaan minimikeko			// O(V)
		while ei olla päästy maaliin 	// ehdon tarkistaminen O(1)
										// while toistetaan O(V)
			valitse keosta ruutu r, jolle lähtöön[r] + maaliin[r] on pienin
										// poll(keko) sisältää heapifyn, keon korkeus on O(log V), heapifyn aikavaat. O(log V)), joten se on myös pollauksen aikavaativuus
			aseta r käydyksi true 		// O(1)
			käy läpi r:n naapurit		// tutkitaan jokainen kaari, O(E*V)
	

	alustus()
		for kaikille ruuduille r		// O(V)
			maaliin[r] = arvio r-->m	// O(1)
			lähtöön[r] = ääretön		// O(1)
			reitti[r] = NIL				// O(1)
		lähtöön[lähtö] = 0				// O(1)
		lisää ruudut kekoon				// O(V)


	käyLäpiNaapurit(r)
		for kaikki r:n naapurit q		// O(4) = O(1)
			// relax
			if(lähtöön[q] > lähtöön[r]+arvo[q]) // ehdon tarkistaminen O(1)
				lähtöön[q] = lähtöön[r]+arvo[q]	// O(1)
				reitti[q] = r					// O(1)
				päivitä keko					// O(V)

###Tilavaativuus

2-ulotteinen pohjataulukko, samoin kuin 1-ulotteinen minimikeko vie tilaa O(V), koska sinne tallennetaan kaikki solmut.

Heapify on toteutettu rekursiivisesti, joten sen pino on pahimmillaan keon korkeus, siis O(log V).

aStarin tilavaativuudeksi tulee O(V) taulukoiden vuoksi.

Lähteet
---------
http://www.cs.helsinki.fi/u/floreen/tira2014/tira.pdf


