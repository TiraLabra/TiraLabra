Aihe: lyhin polku verkossa

Toteutetaan lyhimm�n polun etsint� k�ytt�en A*-algoritmia. 

Esimerkkiaineistona toimii HSL:n raitiovaunujen pys�kki- ja linjatiedot (JSON-muodossa). Aineiston pohjalta tehdyst� verkosta k�ytt�j� esitt�� kyselyj�: etsi lyhin reitti, etsi nopein reitti pys�kilt� a pys�kille b. Pys�kit ovat verkon solmuja, raitiovaunut kaaria. Vastauksena palautetaan reitti. 

Toteutuskieli: Java

Toteutettavat algoritmit:
 
1)	A*

A*-haku etsii lyhint� reitti� verkosta l�ht�- ja maalisolmun v�lill�. Solmujen k�sittelyj�rjestys m��r�ytyy niiden hyvyysmitan suhteen: hyvyys(solmu)=kustannus(alku, solmu)+heuristiikka(solmu,maali). Kustannus(alku,solmu) on kuljetun matkan kustannus alkusolmusta k�sitelt�v��n solmuun  ja heuristiikka(solmu, maali) on arvioitu kustannus maaliin asti. Parhaassa tapauksessa A* l�yt�� maalin nopeasti (l�htee suoraan kohti kohdetta).

http://en.wikipedia.org/wiki/A*_search_algorithm, johdatus teko�lyyn

2)	Heuristiikka A*-hakua varten

Heuristiikka arvioi kahden solmun v�list� et�isyytt�. Jotta A*-haku toimisi oikein, vaaditaan: heuristiikka(a,maali)<=et�isyys(a,b)+heuristiikka(b,maali). Et�isyyksi� ei siis saa yliarvioida. Verkon solmuissa pidet��n kirjaa esimerkiksi sijainnista, joiden pohjalta voidaan laskea euklidinen et�isyys.

Tietorakenteet: 

1)	Prioriteettijono (A* varten)

A*-algoritmissa solmujen k�sittelyj�rjestys m��r�ytyy niiden hyvyyden perusteella: pidet��n kirjaa solmuun p��syyn k�ytetyst� kustannuksesta sek� arviota maaliin p��syn kustannuksesta. Algoritmin suorituksen aikana jonoon lis�t��n enemm�n arvoja kuin poistetaan.
Toteutustapana joko minimikeko (lis�ys, ensimm�isen poisto O(log n)) tai parempi (Fibonacci-keko yms, jossa lis�ys O(1)).

tietorakenteet, http://bigocheatsheet.com/

2)	Verkko

Verkko rakennetaan suorituksen alussa, jonka j�lkeen A*-haku suorittaa siihen useita kyselyit�. Verkko koostuu solmuista ja niiden v�lisist� kaarista. Jos solmusta a on kaari solmuun b, on b a:n naapuri. 
Verkon paras toteutustapa riippuu siit�, miten tihe� verkko on (kaarien m��r� suhteessa solmujen neli��n).

3)	Hajautustaulu

Verkon esityksess� tarvittava. T�ss� tarvittavat lis�ys ja etsint� O(1). Solmuille (kaarille) tarvitaan sopivat hajautusavaimet.

4)	Linkitetty lista (yhteen suuntaan, taaksep�in)

Reitin tallentamista varten. Solmut tallennetaan yksitellen listan eteen (O(1)), ensimm�ist� tarvitaan suorituksessa (O(1)). Vasta lopuksi reitti k��nnet��n (maali-alku --> alku-maali) (O(n)).

http://en.wikipedia.org/wiki/Linked_list#Singly_linked_lists

5)	(Dynaaminen/linkitetty) Lista, taulukko

Verkon vieruslistaan tarvittavat. Tavoitteena lis�ys O(1) (, l�pik�ynti O(n)).