#Määrittelydokumentti
Harjoitustyön aiheena on verkot ja polunesintä.

Ratkaistava ongelma on miten löytää tehokkaasti nopein reitti kahden verkon solmun välillä. Sovellus käyttää A*-algoritmia nopeimman reitin löytämiseksi. 

Sovellus lukee pysäkkien tiedot ascii-tiedostosta ja siihen syötetään lähtö- ja kohdesolmut, joilloin se tulostaa nopeimman reitin.

Sovelluksen tavoitteellinen aikavaativuus on O((|E| + |V|) log |V|) ja tilavaativuus O(|E| + |V|), missä V on solmujen joukko ja E on rietit solmujen välillä.

##Lähteet
- [A*-algoritmi](http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html)
- [Tietorakenteet ja algoritmit](http://www.cs.helsinki.fi/courses/58131/2014/k/k/1)
