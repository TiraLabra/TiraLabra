#Määrittelydokumentti
Harjoitustyön aiheena on verkot ja polunesintä.

Ratkaistava ongelma on miten löytää tehokkaasti nopein reitti kahden joukkoliikenteen pysäkin välillä. Sovellus käyttää A*-algoritmia nopeimman reitin löytämiseksi. 

Ohjelma lukee pysäkkien tiedot XML-tiedostosta ja siihen syötetään lähtö- ja kohdepysäkin nimet, joilloin se tulostaa nopeimman reitin lähtöpysäkistä kohdepysäkkiin.

Ohjelman tavoitteellinen aikavaativuus on O((|E| + |V|) log |V|) ja tilavaativuus O(|E| + |V|), missä V on pysäkkien joukko ja E on rietit pysäkkien välillä.

##Lähteet
- [A*-algoritmi](http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html)
- [Tietorakenteet ja algoritmit](http://www.cs.helsinki.fi/u/floreen/tira2014/tira.pdf)
