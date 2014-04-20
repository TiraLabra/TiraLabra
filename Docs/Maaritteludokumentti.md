<h1>M��rittelydokumentti</h1>

Harjoitusty�ni tarkoituksena on jatkokehitt�� ohjelmoinnin harjoitusty�n� tekem��ni Pacman-peli�. T�m�n hetkisess� versiossa haamut liikkuvat k�yt�vi� pitkin satunnaisesti minne sattuu, aion muokata peli� siten, ett� jokaisella haamulla on oma tavoitteensa; eli ruutu johon ne jokaisella vuorollaan aina pyrkiv�t. Jokaisella haamulla vaihtuu tavoite ruutu, joka liikkumiskerralla, sill� ruutu riippuu Pacmanin sijainnista alustalla. T�m�n takia paras reitti haluttuun ruutuun t�ytyy laskea ennen jokaista liikkumiskertaa. 

Aion parhaimman mahdollisimman reitin selvitt�miseen k�ytt�� A*-algoritmia, koska algoritmi l�yt�� varmasti lyhimm�n reitin haluttuun ruutuun tehokkaasti.

Teen omina tietorakenteina myös ArrayList:ia simuloivat Listan ja hakualgoritmi käyttää apunaan lomitusjärjestämistä.

Käyttäjä ei anna ohjelmalle syötteitä, mutta ohjelma saa pelialustan tekstitiedostona, jonka perusteella pelialusta luodaan. Tekstitiedostossa pelialustan eri ominaisuudet on merkitty eri tunnuksin, mitkä koodi sitten tunnistaa ja asettaa ominaisuudet alustalle.

A*-algoritmin aikavaativuuden tavoite on tässä ohjelmassa O(|E|+|V|log|V|) ja tilavaativuuden tavoite on O(n^2).

Lähteet:
http://www.cs.helsinki.fi/u/floreen/tira2013/tira.pdf
