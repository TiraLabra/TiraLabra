Aihe: lyhin polku verkossa

Toteutetaan lyhimmän polun etsintä käyttäen A*-algoritmia. 

Esimerkkiaineistona toimii HSL:n raitiovaunujen pysäkki- ja linjatiedot (JSON-muodossa). Aineiston pohjalta tehdystä verkosta käyttäjä esittää kyselyjä: etsi lyhin reitti, etsi nopein reitti pysäkiltä a pysäkille b. Pysäkit ovat verkon solmuja, raitiovaunut kaaria. Vastauksena palautetaan reitti. 

Toteutuskieli: Java

Toteutettavat algoritmit:
 
1)	A*

A*-haku etsii lyhintä reittiä verkosta lähtö- ja maalisolmun välillä. Solmujen käsittelyjärjestys määräytyy niiden hyvyysmitan suhteen: hyvyys(solmu)=kustannus(alku, solmu)+heuristiikka(solmu,maali). Kustannus(alku,solmu) on kuljetun matkan kustannus alkusolmusta käsiteltävään solmuun  ja heuristiikka(solmu, maali) on arvioitu kustannus maaliin asti. Parhaassa tapauksessa A* löytää maalin nopeasti (lähtee suoraan kohti kohdetta).

http://en.wikipedia.org/wiki/A*_search_algorithm, johdatus tekoälyyn

2)	Heuristiikka A*-hakua varten

Heuristiikka arvioi kahden solmun välistä etäisyyttä. Jotta A*-haku toimisi oikein, vaaditaan: heuristiikka(a,maali)<=etäisyys(a,b)+heuristiikka(b,maali). Etäisyyksiä ei siis saa yliarvioida. Verkon solmuissa pidetään kirjaa esimerkiksi sijainnista, joiden pohjalta voidaan laskea euklidinen etäisyys.

Tietorakenteet: 

1)	Prioriteettijono (A* varten)

A*-algoritmissa solmujen käsittelyjärjestys määräytyy niiden hyvyyden perusteella: pidetään kirjaa solmuun pääsyyn käytetystä kustannuksesta sekä arviota maaliin pääsyn kustannuksesta. Algoritmin suorituksen aikana jonoon lisätään enemmän arvoja kuin poistetaan.
Toteutustapana joko minimikeko (lisäys, ensimmäisen poisto O(log n)) tai parempi (Fibonacci-keko yms, jossa lisäys O(1)).

tietorakenteet, http://bigocheatsheet.com/

2)	Verkko

Verkko rakennetaan suorituksen alussa, jonka jälkeen A*-haku suorittaa siihen useita kyselyitä. Verkko koostuu solmuista ja niiden välisistä kaarista. Jos solmusta a on kaari solmuun b, on b a:n naapuri. 
Verkon paras toteutustapa riippuu siitä, miten tiheä verkko on (kaarien määrä suhteessa solmujen neliöön).

3)	Hajautustaulu

Verkon esityksessä tarvittava. Tässä tarvittavat lisäys ja etsintä O(1). Solmuille (kaarille) tarvitaan sopivat hajautusavaimet.

4)	Linkitetty lista (yhteen suuntaan, taaksepäin)

Reitin tallentamista varten. Solmut tallennetaan yksitellen listan eteen (O(1)), ensimmäistä tarvitaan suorituksessa (O(1)). Vasta lopuksi reitti käännetään (maali-alku --> alku-maali) (O(n)).

http://en.wikipedia.org/wiki/Linked_list#Singly_linked_lists

5)	(Dynaaminen/linkitetty) Lista, taulukko

Verkon vieruslistaan tarvittavat. Tavoitteena lisäys O(1) (, läpikäynti O(n)).